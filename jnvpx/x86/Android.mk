MY_LOCAL_PATH := $(call my-dir)
LOCAL_PATH := $(MY_LOCAL_PATH)
include $(CLEAR_VARS)
LOCAL_C_INCLUDES := . $(LOCAL_PATH)
include $(TARGET_ARCH_ABI)/libvpx/build/make/Android.mk

LOCAL_PATH := $(MY_LOCAL_PATH)/../jni
include $(CLEAR_VARS)
LOCAL_MODULE := jnvpx
LOCAL_SRC_FILES := stubs.c org_jitsi_impl_neomedia_codec_video_VPX.c
LOCAL_STATIC_LIBRARIES := vpx
LOCAL_C_INCLUDES := . $(TARGET_ARCH_ABI) $(TARGET_ARCH_ABI)/libvpx
include $(BUILD_SHARED_LIBRARY)
