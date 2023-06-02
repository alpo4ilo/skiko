#ifdef SK_METAL

#import <jawt.h>
#import <jawt_md.h>

#import <QuartzCore/CAMetalLayer.h>
#import <Metal/Metal.h>
#import <GrDirectContext.h>
#import <mtl/GrMtlBackendContext.h>
#import <mtl/GrMtlTypes.h>

#import "MetalDevice.h"

extern "C"
{
JNIEXPORT jlong JNICALL Java_org_jetbrains_skiko_context_MetalContextHandler_makeMetalContext(
    JNIEnv* env, jobject contextHandler, jlong devicePtr)
{
    @autoreleasepool {
        MetalDevice *device = (__bridge MetalDevice *) (void*) devicePtr;
        GrMtlBackendContext backendContext = {};
        backendContext.fDevice.retain((__bridge GrMTLHandle) device.adapter);
        backendContext.fQueue.retain((__bridge GrMTLHandle) device.queue);
        return (jlong) GrDirectContext::MakeMetal(backendContext).release();
    }
}

JNIEXPORT jlong JNICALL Java_org_jetbrains_skiko_context_MetalContextHandler_makeMetalRenderTarget(
    JNIEnv* env, jobject contextHandler, jlong devicePtr, jlong drawablePtr, jint width, jint height)
{
    @autoreleasepool {
        MetalDevice *device = (__bridge MetalDevice *) (void *) devicePtr;
        id<CAMetalDrawable> drawable = (__bridge_transfer id<CAMetalDrawable>) (void *) drawablePtr;
        if (!drawable) {
            return NULL;
        }

        GrMtlTextureInfo info;
        info.fTexture.retain((__bridge GrMTLHandle) drawable.texture);
        return (jlong) new GrBackendRenderTarget(width, height, 0, info);
    }
}

} // extern C
#endif