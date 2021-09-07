@file:Suppress("NESTED_EXTERNAL_DECLARATION")
package org.jetbrains.skia.paragraph

import org.jetbrains.skia.impl.Library.Companion.staticLoad
import org.jetbrains.skia.impl.RefCnt
import org.jetbrains.skia.*
import org.jetbrains.skia.impl.Stats
import org.jetbrains.skia.impl.reachabilityBarrier
import org.jetbrains.skia.ExternalSymbolName
import kotlin.jvm.JvmStatic

class FontCollection internal constructor(ptr: Long) : RefCnt(ptr) {
    companion object {
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nMake")
        external fun _nMake(): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nGetFontManagersCount")
        external fun _nGetFontManagersCount(ptr: Long): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nSetAssetFontManager")
        external fun _nSetAssetFontManager(ptr: Long, fontManagerPtr: Long): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nSetDynamicFontManager")
        external fun _nSetDynamicFontManager(ptr: Long, fontManagerPtr: Long): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nSetTestFontManager")
        external fun _nSetTestFontManager(ptr: Long, fontManagerPtr: Long): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nSetDefaultFontManager")
        external fun _nSetDefaultFontManager(ptr: Long, fontManagerPtr: Long, defaultFamilyName: String?): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nGetFallbackManager")
        external fun _nGetFallbackManager(ptr: Long): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nFindTypefaces")
        external fun _nFindTypefaces(ptr: Long, familyNames: Array<String?>?, fontStyle: Int): LongArray
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nDefaultFallbackChar")
        external fun _nDefaultFallbackChar(ptr: Long, unicode: Int, fontStyle: Int, locale: String?): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nDefaultFallback")
        external fun _nDefaultFallback(ptr: Long): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nSetEnableFallback")
        external fun _nSetEnableFallback(ptr: Long, value: Boolean): Long
        @JvmStatic
        @ExternalSymbolName("org_jetbrains_skia_FontCollection__1nGetParagraphCache")
        external fun _nGetParagraphCache(ptr: Long): Long

        init {
            staticLoad()
        }
    }

    constructor() : this(_nMake()) {
        Stats.onNativeCall()
    }

    val fontManagersCount: Long
        get() = try {
            Stats.onNativeCall()
            _nGetFontManagersCount(_ptr)
        } finally {
            reachabilityBarrier(this)
        }

    fun setAssetFontManager(fontMgr: FontMgr?): FontCollection {
        return try {
            Stats.onNativeCall()
            _nSetAssetFontManager(_ptr, getPtr(fontMgr))
            this
        } finally {
            reachabilityBarrier(fontMgr)
        }
    }

    fun setDynamicFontManager(fontMgr: FontMgr?): FontCollection {
        return try {
            Stats.onNativeCall()
            _nSetDynamicFontManager(
                _ptr,
                getPtr(fontMgr)
            )
            this
        } finally {
            reachabilityBarrier(fontMgr)
        }
    }

    fun setTestFontManager(fontMgr: FontMgr?): FontCollection {
        return try {
            Stats.onNativeCall()
            _nSetTestFontManager(_ptr, getPtr(fontMgr))
            this
        } finally {
            reachabilityBarrier(fontMgr)
        }
    }

    fun setDefaultFontManager(fontMgr: FontMgr?): FontCollection {
        return setDefaultFontManager(fontMgr, null)
    }

    fun setDefaultFontManager(fontMgr: FontMgr?, defaultFamilyName: String?): FontCollection {
        return try {
            Stats.onNativeCall()
            _nSetDefaultFontManager(
                _ptr,
                getPtr(fontMgr),
                defaultFamilyName
            )
            this
        } finally {
            reachabilityBarrier(fontMgr)
        }
    }

    val fallbackManager: FontMgr?
        get() = try {
            Stats.onNativeCall()
            val ptr = _nGetFallbackManager(_ptr)
            if (ptr == 0L) null else FontMgr(ptr)
        } finally {
            reachabilityBarrier(this)
        }

    fun findTypefaces(familyNames: Array<String?>?, style: FontStyle): Array<Typeface?> {
        return try {
            Stats.onNativeCall()
            val ptrs = _nFindTypefaces(_ptr, familyNames, style._value)
            val res = arrayOfNulls<Typeface>(ptrs.size)
            for (i in ptrs.indices) res[i] = Typeface(ptrs[i])
            res
        } finally {
            reachabilityBarrier(this)
        }
    }

    fun defaultFallback(unicode: Int, style: FontStyle, locale: String?): Typeface? {
        return try {
            Stats.onNativeCall()
            val ptr = _nDefaultFallbackChar(_ptr, unicode, style._value, locale)
            if (ptr == 0L) null else Typeface(ptr)
        } finally {
            reachabilityBarrier(this)
        }
    }

    fun defaultFallback(): Typeface? {
        return try {
            Stats.onNativeCall()
            val ptr = _nDefaultFallback(_ptr)
            if (ptr == 0L) null else Typeface(ptr)
        } finally {
            reachabilityBarrier(this)
        }
    }

    fun setEnableFallback(value: Boolean): FontCollection {
        Stats.onNativeCall()
        _nSetEnableFallback(_ptr, value)
        return this
    }

    val paragraphCache: ParagraphCache
        get() = try {
            Stats.onNativeCall()
            ParagraphCache(this, _nGetParagraphCache(_ptr))
        } finally {
            reachabilityBarrier(this)
        }
}