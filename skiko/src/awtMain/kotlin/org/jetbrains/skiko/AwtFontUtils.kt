@file:Suppress("PrivatePropertyName") // Reflection-based properties have more meaningful names

package org.jetbrains.skiko

import org.jetbrains.annotations.NonNls
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Predicate

@InternalSkikoApi
object AwtFontUtils {

    init {
        InternalSunApiOpener.ensureAccessToSunFontPackage()
    }

    private val fontManagerFactoryClass = Class.forName("sun.font.FontManagerFactory")

    private val fontManagerClass = Class.forName("sun.font.FontManager")
    private val font2DClass = Class.forName("sun.font.Font2D")

    // FontManagerFactory methods
    private val FontManagerFactory_getInstanceMethod =
        getDeclaredMethodOrNull(fontManagerFactoryClass, "getInstance")

    // FontManager methods and fields
    private val FontManager_findFont2DMethod = getDeclaredMethodOrNull(
        fontManagerClass,
        "findFont2D",
        String::class.java, // Font name
        Int::class.javaPrimitiveType!!, // Font style (e.g., Font.BOLD)
        Int::class.javaPrimitiveType!! // Fallback (one of the FontManager.*_FALLBACK values)
    )

    // Font2D methods and fields
    private val Font2D_getTypographicFamilyNameMethod =
        getFont2DMethodOrNull("getTypographicFamilyName")
    private val Font2D_handleField =
        findFieldInHierarchy(font2DClass) { it.name == "handle" }

    // Font2DHandle fields
    private val Font2DHandle_font2DField =
        findFieldInHierarchy(Class.forName("sun.font.Font2DHandle")) {
            it.name == "font2D"
        }

    // Copy of FontManager.LOGICAL_FALLBACK
    private const val LOGICAL_FALLBACK = 2

    private val font2DHandlesCache = ConcurrentHashMap<Font, Any>()

    /**
     * Indicate whether the current JVM is able to resolve font family names
     * accurately or not.
     *
     * This value will be `true` if using the JetBrains Runtime. It will be
     * `false` otherwise, indicating that this class is not able to return
     * valid values.
     *
     * If the return value is `false`, you should assume all APIs in this class
     * will return `null` as we can't obtain the necessary information.
     *
     * On other JVMs running on Windows and Linux, the AWT implementation is
     * not enumerating font families correctly. E.g., you may have these entries
     * for JetBrains Mono, instead of a single entry: _JetBrains Mono, JetBrains
     * Mono Bold, JetBrains Mono ExtraBold, JetBrains Mono ExtraLight, JetBrains
     * Mono Light, JetBrains Mono Medium, JetBrains Mono SemiBold, JetBrains
     * Mono Thin_.
     *
     * On the JetBrains Runtime, there are additional APIs that provide the
     * necessary information needed to list the actual font families as single
     * entries, as one would expect.
     */
    @DependsOnJBR
    val isAbleToResolveFontFamilyNames: Boolean
        get() = Font2D_getTypographicFamilyNameMethod != null

    /**
     * Try to resolve a font family name, that could be a logical font
     * face (e.g., [Font.DIALOG]), to the actual physical font family
     * it is an alias for.
     *
     * @param fontName The name of the font face
     * @param style The desired font name; must be one of the styles
     * supported by the AWT [Font]. It is [Font.PLAIN] by default.
     *
     * @return The resolved physical font name, or `null` if it can't
     * be resolved (either it's unknown, or the system doesn't
     * support resolving font family names)
     *
     * @see isAbleToResolveFontFamilyNames
     */
    internal fun resolvePhysicalFontNameOrNull(
        fontName: String,
        style: Int = Font.PLAIN
    ): String? {
        if (!isAbleToResolveFontFamilyNames) return null

        val fontManager = awtFontManager()
        val font2D =
            checkNotNull(FontManager_findFont2DMethod) { "FontManager#findFont2DMethod() is not available" }
                .invoke(fontManager, fontName, style, LOGICAL_FALLBACK)

        return when (font2D.javaClass.name) {
            "sun.font.CompositeFont" -> {
                // For Windows and Linux
                val physicalFontObject =
                    getDeclaredMethodOrNull(
                        clazz = Class.forName("sun.font.CompositeFont"),
                        name = "getSlotFont",
                        Int::class.javaPrimitiveType!!
                    )?.invoke(font2D, 0)

                getFont2DMethodOrNull("getFamilyName", Locale::class.java)
                    ?.invoke(physicalFontObject, Locale.getDefault()) as String?
            }

            "sun.font.CFont" -> {
                // For macOS
                val clazz = Class.forName("sun.font.CFont")
                getFieldValueOrNull(clazz, font2D, String::class.java, "nativeFontName")
            }

            else -> error("Unsupported Font2D subclass: ${font2D.javaClass.name}")
        }
    }

    /**
     * The list of font family names available through AWT. This list should
     * be used instead of the one provided by [GraphicsEnvironment.getAvailableFontFamilyNames]
     * since it will provide consistent results across platforms.
     *
     * Will return `null` if [isAbleToResolveFontFamilyNames] is `false`.
     *
     * See [fontFamilyName] for further details.
     *
     * @see fontFamilyName
     * @see isAbleToResolveFontFamilyNames
     */
    fun fontFamilyNamesOrNull(
        graphicsEnvironment: GraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment()
    ): SortedSet<String>? {
        if (!isAbleToResolveFontFamilyNames) return null

        return graphicsEnvironment.allFonts.map { font -> font.fontFamilyName!! }
            .toSortedSet()
    }

    /**
     * The preferred font family name, which should be used instead of the
     * [Font.getFamily] and `Font2D.familyName`.
     *
     * On Windows, and potentially in other cases, the family name as reported
     * by AWT can contain the style and weight of the [Font] in addition to the
     * _actual_ font family name. This can cause issues when trying to match up
     * AWT fonts with Skia typefaces, and if used for listing font families,
     * will result in multiple entries being present in the list.
     *
     * You can use [fontFamilyNamesOrNull] to enumerate the actual family names
     * available via AWT.
     *
     * @see fontFamilyNamesOrNull
     */
    val Font.fontFamilyName: String?
        get() {
            if (!isAbleToResolveFontFamilyNames) return null

            val font2D = obtainFont2D()
            return checkNotNull(Font2D_getTypographicFamilyNameMethod) { "Font2D#getTypographicFamilyName() is not available" }
                .invoke(font2D) as String
        }

    private fun Font.obtainFont2D(): Any {
        // Don't store the Font2D instance directly, in case the handle may be changed
        // later on. Logic adopted from java.awt.Font#getFont2D()
        val handle = font2DHandlesCache.getOrPut(this) {
            val fontManager = awtFontManager()
            val font2D =
                checkNotNull(FontManager_findFont2DMethod) { "FontManager#findFont2DMethod() is not available" }
                    .invoke(fontManager, name, style, LOGICAL_FALLBACK)
            checkNotNull(Font2D_handleField) { "Font2D#handle is not available" }
                .get(font2D)
        }

        return checkNotNull(Font2DHandle_font2DField) { "Font2DHandle#font2D is not available" }
            .get(handle)
    }

    private fun awtFontManager() =
        checkNotNull(FontManagerFactory_getInstanceMethod) { "FontManagerFactory#getInstanceMethod() not available" }
            .invoke(null)

    private fun getFont2DMethodOrNull(
        methodName: String,
        vararg parameters: Class<*>
    ): Method? =
        try {
            getDeclaredMethodOrNull(font2DClass, methodName, *parameters)
        } catch (e: Throwable) {
            null
        }

    private fun getDeclaredMethodOrNull(
        clazz: Class<*>,
        name: String,
        vararg parameters: Class<*>
    ): Method? =
        try {
            clazz.getDeclaredMethod(name, *parameters)
                .apply { isAccessible = true }
        } catch (e: NoSuchMethodException) {
            null
        }

    private fun <T> getFieldValueOrNull(
        objectClass: Class<*>,
        `object`: Any?,
        fieldType: Class<T>?,
        fieldName: String
    ): T? =
        try {
            val field: Field = findAssignableField(objectClass, fieldType, fieldName)
            getFieldValue<T>(field, `object`)
        } catch (e: NoSuchFieldException) {
            null
        }

    private fun findAssignableField(
        clazz: Class<*>,
        fieldType: Class<*>?,
        @NonNls fieldName: String
    ): Field {
        val result = findFieldInHierarchy(
            clazz
        ) { field: Field ->
            fieldName == field.name && (fieldType == null || fieldType.isAssignableFrom(
                field.type
            ))
        }
        if (result != null) {
            return result
        }
        throw NoSuchFieldException("Class: $clazz fieldName: $fieldName fieldType: $fieldType")
    }

    private fun findFieldInHierarchy(
        rootClass: Class<*>,
        checker: Predicate<in Field>
    ): Field? {
        var aClass: Class<*>? = rootClass
        while (aClass != null) {
            for (field in aClass.declaredFields) {
                if (checker.test(field)) {
                    field.isAccessible = true
                    return field
                }
            }
            aClass = aClass.superclass
        }

        return processInterfaces(rootClass.interfaces, HashSet(), checker)
    }

    private fun processInterfaces(
        interfaces: Array<Class<*>>,
        visited: MutableSet<in Class<*>>,
        checker: Predicate<in Field>
    ): Field? {
        for (anInterface in interfaces) {
            if (!visited.add(anInterface)) {
                continue
            }
            for (field in anInterface.declaredFields) {
                if (checker.test(field)) {
                    field.isAccessible = true
                    return field
                }
            }
            val field = processInterfaces(anInterface.interfaces, visited, checker)
            if (field != null) {
                return field
            }
        }
        return null
    }

    private fun <T> getFieldValue(field: Field, instance: Any?): T? =
        try {
            @Suppress("UNCHECKED_CAST")
            field[instance] as T
        } catch (e: IllegalAccessException) {
            null
        }
}