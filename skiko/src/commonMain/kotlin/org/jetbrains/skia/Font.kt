@file:Suppress("NESTED_EXTERNAL_DECLARATION")
package org.jetbrains.skia

import org.jetbrains.skia.impl.Library.Companion.staticLoad
import org.jetbrains.skia.impl.Managed
import org.jetbrains.skia.impl.Native
import org.jetbrains.skia.impl.Stats
import org.jetbrains.skia.impl.reachabilityBarrier
import org.jetbrains.skia.ExternalSymbolName
import kotlin.jvm.JvmStatic

class Font : Managed {
    companion object {
        internal fun makeClone(ptr: Long): Font {
            Stats.onNativeCall()
            return Font(_nMakeClone(ptr))
        }

        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetFinalizer")
        external fun _nGetFinalizer(): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nMakeDefault")
        external fun _nMakeDefault(): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nMakeTypeface")
        external fun _nMakeTypeface(typefacePtr: Long): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nMakeTypefaceSize")
        external fun _nMakeTypefaceSize(typefacePtr: Long, size: Float): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nMakeTypefaceSizeScaleSkew")
        external fun _nMakeTypefaceSizeScaleSkew(typefacePtr: Long, size: Float, scaleX: Float, skewX: Float): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nMakeClone")
        external fun _nMakeClone(ptr: Long): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nEquals")
        external fun _nEquals(ptr: Long, otherPtr: Long): Boolean
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nIsAutoHintingForced")
        external fun _nIsAutoHintingForced(ptr: Long): Boolean
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nAreBitmapsEmbedded")
        external fun _nAreBitmapsEmbedded(ptr: Long): Boolean
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nIsSubpixel")
        external fun _nIsSubpixel(ptr: Long): Boolean
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nAreMetricsLinear")
        external fun _nAreMetricsLinear(ptr: Long): Boolean
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nIsEmboldened")
        external fun _nIsEmboldened(ptr: Long): Boolean
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nIsBaselineSnapped")
        external fun _nIsBaselineSnapped(ptr: Long): Boolean
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetAutoHintingForced")
        external fun _nSetAutoHintingForced(ptr: Long, value: Boolean)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetBitmapsEmbedded")
        external fun _nSetBitmapsEmbedded(ptr: Long, value: Boolean)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetSubpixel")
        external fun _nSetSubpixel(ptr: Long, value: Boolean)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetMetricsLinear")
        external fun _nSetMetricsLinear(ptr: Long, value: Boolean)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetEmboldened")
        external fun _nSetEmboldened(ptr: Long, value: Boolean)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetBaselineSnapped")
        external fun _nSetBaselineSnapped(ptr: Long, value: Boolean)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetEdging")
        external fun _nGetEdging(ptr: Long): Int
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetEdging")
        external fun _nSetEdging(ptr: Long, value: Int)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetHinting")
        external fun _nGetHinting(ptr: Long): Int
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetHinting")
        external fun _nSetHinting(ptr: Long, value: Int)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetTypeface")
        external fun _nGetTypeface(ptr: Long): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetTypefaceOrDefault")
        external fun _nGetTypefaceOrDefault(ptr: Long): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetSize")
        external fun _nGetSize(ptr: Long): Float
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetScaleX")
        external fun _nGetScaleX(ptr: Long): Float
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetSkewX")
        external fun _nGetSkewX(ptr: Long): Float
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetTypeface")
        external fun _nSetTypeface(ptr: Long, typefacePtr: Long)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetSize")
        external fun _nSetSize(ptr: Long, value: Float)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetScaleX")
        external fun _nSetScaleX(ptr: Long, value: Float)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nSetSkewX")
        external fun _nSetSkewX(ptr: Long, value: Float)
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetStringGlyphs")
        external fun _nGetStringGlyphs(ptr: Long, str: String?): ShortArray?
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetUTF32Glyph")
        external fun _nGetUTF32Glyph(ptr: Long, uni: Int): Short
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetUTF32Glyphs")
        external fun _nGetUTF32Glyphs(ptr: Long, uni: IntArray?): ShortArray
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetStringGlyphsCount")
        external fun _nGetStringGlyphsCount(ptr: Long, str: String?): Int
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nMeasureText")
        external fun _nMeasureText(ptr: Long, str: String?, paintPtr: Long): Rect
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nMeasureTextWidth")
        external fun _nMeasureTextWidth(ptr: Long, str: String?, paintPtr: Long): Float
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetWidths")
        external fun _nGetWidths(ptr: Long, glyphs: ShortArray?): FloatArray
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetBounds")
        external fun _nGetBounds(ptr: Long, glyphs: ShortArray?, paintPtr: Long): Array<Rect>
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetPositions")
        external fun _nGetPositions(ptr: Long, glyphs: ShortArray?, x: Float, y: Float): Array<Point>
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetXPositions")
        external fun _nGetXPositions(ptr: Long, glyphs: ShortArray?, x: Float): FloatArray
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetPath")
        external fun _nGetPath(ptr: Long, glyph: Short): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetPaths")
        external fun _nGetPaths(ptr: Long, glyphs: ShortArray?): Array<Path>
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetMetrics")
        external fun _nGetMetrics(ptr: Long): FontMetrics
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_Font__1nGetSpacing")
        external fun _nGetSpacing(ptr: Long): Float

        init {
            staticLoad()
        }
    }

    internal constructor(ptr: Long) : super(ptr, _FinalizerHolder.PTR)

    internal constructor(ptr: Long, managed: Boolean) : super(ptr, _FinalizerHolder.PTR, managed)

    /**
     * Returns Font initialized with default values
     */
    constructor() : this(_nMakeDefault()) {
        Stats.onNativeCall()
    }

    /**
     * Returns Font with Typeface and default size
     *
     * @param typeface  typeface and style used to draw and measure text. Pass null for default
     */
    constructor(typeface: Typeface?) : this(_nMakeTypeface(getPtr(typeface))) {
        Stats.onNativeCall()
        reachabilityBarrier(typeface)
    }

    /**
     * @param typeface  typeface and style used to draw and measure text. Pass null for default
     * @param size      typographic size of the text
     */
    constructor(typeface: Typeface?, size: Float) : this(_nMakeTypefaceSize(getPtr(typeface), size)) {
        Stats.onNativeCall()
        reachabilityBarrier(typeface)
    }

    /**
     * Constructs Font with default values with Typeface and size in points,
     * horizontal scale, and horizontal skew. Horizontal scale emulates condensed
     * and expanded fonts. Horizontal skew emulates oblique fonts.
     *
     * @param typeface  typeface and style used to draw and measure text. Pass null for default
     * @param size      typographic size of the text
     * @param scaleX    text horizonral scale
     * @param skewX     additional shear on x-axis relative to y-axis
     */
    constructor(typeface: Typeface?, size: Float, scaleX: Float, skewX: Float) : this(
        _nMakeTypefaceSizeScaleSkew(
            getPtr(typeface), size, scaleX, skewX
        )
    ) {
        Stats.onNativeCall()
        reachabilityBarrier(typeface)
    }

    /**
     * Compares fonts, and returns true if they are equivalent.
     * May return false if Typeface has identical contents but different pointers.
     */
    override fun _nativeEquals(other: Native?): Boolean {
        return try {
            _nEquals(_ptr, getPtr(other))
        } finally {
            reachabilityBarrier(this)
            reachabilityBarrier(other)
        }
    }

    /**
     * If true, instructs the font manager to always hint glyphs.
     * Returned value is only meaningful if platform uses FreeType as the font manager.
     *
     * @return  true if all glyphs are hinted
     */
    val isAutoHintingForced: Boolean
        get() = try {
            Stats.onNativeCall()
            _nIsAutoHintingForced(_ptr)
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * @return  true if font engine may return glyphs from font bitmaps instead of from outlines
     */
    fun areBitmapsEmbedded(): Boolean {
        return try {
            Stats.onNativeCall()
            _nAreBitmapsEmbedded(_ptr)
        } finally {
            reachabilityBarrier(this)
        }
    }

    /**
     * @return  true if glyphs may be drawn at sub-pixel offsets
     */
    val isSubpixel: Boolean
        get() = try {
            Stats.onNativeCall()
            _nIsSubpixel(_ptr)
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * @return  true if font and glyph metrics are requested to be linearly scalable
     */
    fun areMetricsLinear(): Boolean {
        return try {
            Stats.onNativeCall()
            _nAreMetricsLinear(_ptr)
        } finally {
            reachabilityBarrier(this)
        }
    }

    /**
     * Returns true if bold is approximated by increasing the stroke width when creating glyph
     * bitmaps from outlines.
     *
     * @return  true if bold is approximated through stroke width
     */
    val isEmboldened: Boolean
        get() = try {
            Stats.onNativeCall()
            _nIsEmboldened(_ptr)
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * Returns true if baselines will be snapped to pixel positions when the current transformation
     * matrix is axis aligned.
     *
     * @return  true if baselines may be snapped to pixels
     */
    val isBaselineSnapped: Boolean
        get() = try {
            Stats.onNativeCall()
            _nIsBaselineSnapped(_ptr)
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * Sets whether to always hint glyphs. If forceAutoHinting is set, instructs the font manager to always hint glyphs. Only affects platforms that use FreeType as the font manager.
     *
     * @param value  setting to always hint glyphs
     * @return       this
     */
    fun setAutoHintingForced(value: Boolean): Font {
        Stats.onNativeCall()
        _nSetAutoHintingForced(_ptr, value)
        return this
    }

    /**
     * Requests, but does not require, to use bitmaps in fonts instead of outlines.
     *
     * @param value  setting to use bitmaps in fonts
     * @return       this
     */
    fun setBitmapsEmbedded(value: Boolean): Font {
        Stats.onNativeCall()
        _nSetBitmapsEmbedded(_ptr, value)
        return this
    }

    /**
     * Requests, but does not require, that glyphs respect sub-pixel positioning.
     *
     * @param value  setting for sub-pixel positioning
     * @return       this
     */
    fun setSubpixel(value: Boolean): Font {
        Stats.onNativeCall()
        _nSetSubpixel(_ptr, value)
        return this
    }

    /**
     * Requests, but does not require, linearly scalable font and glyph metrics.
     *
     * For outline fonts 'true' means font and glyph metrics should ignore hinting and rounding.
     * Note that some bitmap formats may not be able to scale linearly and will ignore this flag.
     *
     * @param value  setting for linearly scalable font and glyph metrics.
     * @return       this
     */
    fun setMetricsLinear(value: Boolean): Font {
        Stats.onNativeCall()
        _nSetMetricsLinear(_ptr, value)
        return this
    }

    /**
     * Increases stroke width when creating glyph bitmaps to approximate a bold typeface.
     *
     * @param value  setting for bold approximation
     * @return       this
     */
    fun setEmboldened(value: Boolean): Font {
        Stats.onNativeCall()
        _nSetEmboldened(_ptr, value)
        return this
    }

    /**
     * Requests that baselines be snapped to pixels when the current transformation matrix is axis
     * aligned.
     *
     * @param value  setting for baseline snapping to pixels
     * @return       this
     */
    fun setBaselineSnapped(value: Boolean): Font {
        Stats.onNativeCall()
        _nSetBaselineSnapped(_ptr, value)
        return this
    }

    /**
     * Whether edge pixels draw opaque or with partial transparency.
     */
    val edging: FontEdging
        get() = try {
            Stats.onNativeCall()
            FontEdging.values().get(_nGetEdging(_ptr))
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * Requests, but does not require, that edge pixels draw opaque or with
     * partial transparency.
     */
    fun setEdging(value: FontEdging): Font {
        Stats.onNativeCall()
        _nSetEdging(_ptr, value.ordinal)
        return this
    }

    /**
     * @return  level of glyph outline adjustment
     */
    val hinting: FontHinting
        get() = try {
            Stats.onNativeCall()
            FontHinting.values().get(_nGetHinting(_ptr))
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * Sets level of glyph outline adjustment. Does not check for valid values of hintingLevel.
     */
    fun setHinting(value: FontHinting): Font {
        Stats.onNativeCall()
        _nSetHinting(_ptr, value.ordinal)
        return this
    }

    /**
     * Returns a font with the same attributes of this font, but with the specified size.
     */
    fun makeWithSize(size: Float): Font {
        return Font(typeface, size, scaleX, skewX)
    }

    /**
     * @return [Typeface] if set, or null
     */
    val typeface: Typeface?
        get() = try {
            Stats.onNativeCall()
            val ptr = _nGetTypeface(_ptr)
            if (ptr == 0L) null else Typeface(ptr)
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * @return [Typeface] if set, or the default typeface.
     */
    val typefaceOrDefault: Typeface
        get() = try {
            Stats.onNativeCall()
            Typeface(_nGetTypefaceOrDefault(_ptr))
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * @return  text size in points
     */
    val size: Float
        get() = try {
            Stats.onNativeCall()
            _nGetSize(_ptr)
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * @return  text scale on x-axis. Default value is 1
     */
    val scaleX: Float
        get() = try {
            Stats.onNativeCall()
            _nGetScaleX(_ptr)
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * @return  text skew on x-axis. Default value is 0
     */
    val skewX: Float
        get() = try {
            Stats.onNativeCall()
            _nGetSkewX(_ptr)
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * Sets Typeface to typeface. Pass null to use the default typeface.
     */
    fun setTypeface(typeface: Typeface?): Font {
        return try {
            Stats.onNativeCall()
            _nSetTypeface(
                _ptr,
                getPtr(typeface)
            )
            this
        } finally {
            reachabilityBarrier(typeface)
        }
    }

    /**
     * Sets text size in points. Has no effect if value is not greater than or equal to zero.
     */
    fun setSize(value: Float): Font {
        Stats.onNativeCall()
        _nSetSize(_ptr, value)
        return this
    }

    /**
     * Sets text scale on x-axis. Default value is 1.
     */
    fun setScaleX(value: Float): Font {
        Stats.onNativeCall()
        _nSetScaleX(_ptr, value)
        return this
    }

    /**
     * Sets text skew on x-axis. Default value is 0.
     */
    fun setSkewX(value: Float): Font {
        Stats.onNativeCall()
        _nSetSkewX(_ptr, value)
        return this
    }

    /**
     * Converts text into glyph indices.
     *
     * @return  the corresponding glyph ids for each character.
     */
    fun getStringGlyphs(s: String): ShortArray {
        return getUTF32Glyphs(s.intCodePoints())
    }

    /**
     * Given an array of UTF32 character codes, return their corresponding glyph IDs.
     *
     * @return  the corresponding glyph IDs for each character.
     */
    fun getUTF32Glyphs(uni: IntArray?): ShortArray {
        return try {
            Stats.onNativeCall()
            _nGetUTF32Glyphs(_ptr, uni)
        } finally {
            reachabilityBarrier(this)
        }
    }

    /**
     * @return  the glyph that corresponds to the specified unicode code-point (in UTF32 encoding). If the unichar is not supported, returns 0
     */
    fun getUTF32Glyph(unichar: Int): Short {
        return try {
            Stats.onNativeCall()
            _nGetUTF32Glyph(_ptr, unichar)
        } finally {
            reachabilityBarrier(this)
        }
    }

    /**
     * @return  number of glyphs represented by text
     */
    fun getStringGlyphsCount(s: String?): Int {
        return try {
            Stats.onNativeCall()
            _nGetStringGlyphsCount(_ptr, s)
        } finally {
            reachabilityBarrier(this)
        }
    }
    /**
     * @param p  stroke width or PathEffect may modify the advance with
     * @return   the bounding box of text
     */
    /**
     * @return  the bounding box of text
     */
    fun measureText(s: String?, p: Paint? = null): Rect {
        return try {
            Stats.onNativeCall()
            _nMeasureText(_ptr, s, getPtr(p))
        } finally {
            reachabilityBarrier(this)
            reachabilityBarrier(p)
        }
    }

    fun measureTextWidth(s: String?): Float {
        Stats.onNativeCall()
        return measureTextWidth(s, null)
    }

    fun measureTextWidth(s: String?, p: Paint?): Float {
        return try {
            Stats.onNativeCall()
            _nMeasureTextWidth(
                _ptr,
                s,
                getPtr(p)
            )
        } finally {
            reachabilityBarrier(this)
            reachabilityBarrier(p)
        }
    }

    /**
     * Retrieves the advances for each glyph
     */
    fun getWidths(glyphs: ShortArray?): FloatArray {
        return try {
            Stats.onNativeCall()
            _nGetWidths(_ptr, glyphs)
        } finally {
            reachabilityBarrier(this)
        }
    }

    /**
     * Retrieves the bounds for each glyph
     */
    fun getBounds(glyphs: ShortArray?): Array<Rect> {
        return getBounds(glyphs, null)
    }

    /**
     * Retrieves the bounds for each glyph
     */
    fun getBounds(glyphs: ShortArray?, p: Paint?): Array<Rect> {
        return try {
            Stats.onNativeCall()
            _nGetBounds(
                _ptr,
                glyphs,
                getPtr(p)
            )
        } finally {
            reachabilityBarrier(this)
            reachabilityBarrier(p)
        }
    }

    /**
     * Retrieves the positions for each glyph.
     */
    fun getPositions(glyphs: ShortArray?): Array<Point> {
        return try {
            Stats.onNativeCall()
            _nGetPositions(_ptr, glyphs, 0f, 0f)
        } finally {
            reachabilityBarrier(this)
        }
    }

    /**
     * Retrieves the positions for each glyph, beginning at the specified origin.
     */
    fun getPositions(glyphs: ShortArray?, offset: Point): Array<Point> {
        return try {
            Stats.onNativeCall()
            _nGetPositions(_ptr, glyphs, offset.x, offset.y)
        } finally {
            reachabilityBarrier(this)
        }
    }

    /**
     * Retrieves the x-positions for each glyph.
     */
    fun getXPositions(glyphs: ShortArray?): FloatArray {
        return try {
            Stats.onNativeCall()
            _nGetXPositions(_ptr, glyphs, 0f)
        } finally {
            reachabilityBarrier(this)
        }
    }

    /**
     * Retrieves the x-positions for each glyph, beginning at the specified origin.
     */
    fun getXPositions(glyphs: ShortArray?, offset: Float): FloatArray {
        return try {
            Stats.onNativeCall()
            _nGetXPositions(_ptr, glyphs, offset)
        } finally {
            reachabilityBarrier(this)
        }
    }

    /**
     * If the glyph has an outline, returns it. The glyph outline may be empty.
     * Degenerate contours in the glyph outline will be skipped. If glyph is described by a bitmap, returns null.
     */
    fun getPath(glyph: Short): Path? {
        return try {
            Stats.onNativeCall()
            val ptr = _nGetPath(_ptr, glyph)
            if (ptr == 0L) null else Path(ptr)
        } finally {
            reachabilityBarrier(this)
        }
    }

    /**
     * Return glyph outlines, some of which might be null.
     */
    fun getPaths(glyphs: ShortArray?): Array<Path> {
        return try {
            Stats.onNativeCall()
            _nGetPaths(_ptr, glyphs)
        } finally {
            reachabilityBarrier(this)
        }
    }

    /**
     * Returns FontMetrics associated with Typeface. Results are scaled by text size but does not take into account
     * dimensions required by text scale, text skew, fake bold, style stroke, and [PathEffect].
     */
    val metrics: FontMetrics
        get() = try {
            Stats.onNativeCall()
            _nGetMetrics(_ptr)
        } finally {
            reachabilityBarrier(this)
        }

    /**
     * Returns the recommended spacing between lines: the sum of metrics descent, ascent, and leading.
     * Result is scaled by text size but does not take into account dimensions required by stroking and SkPathEffect.
     */
    val spacing: Float
        get() = try {
            Stats.onNativeCall()
            _nGetSpacing(_ptr)
        } finally {
            reachabilityBarrier(this)
        }

    private object _FinalizerHolder {
        val PTR = _nGetFinalizer()
    }
}