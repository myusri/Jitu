LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := avformat
LOCAL_SRC_FILES := ../$(TARGET_ARCH_ABI)/libavformat/libavformat.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avcodec
LOCAL_SRC_FILES := ../$(TARGET_ARCH_ABI)/libavcodec/libavcodec.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avfilter
LOCAL_SRC_FILES := ../$(TARGET_ARCH_ABI)/libavfilter/libavfilter.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avutil
LOCAL_SRC_FILES := ../$(TARGET_ARCH_ABI)/libavutil/libavutil.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := swscale
LOCAL_SRC_FILES := ../$(TARGET_ARCH_ABI)/libswscale/libswscale.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libswresample
LOCAL_SRC_FILES := ../$(TARGET_ARCH_ABI)/libswresample/libswresample.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := jnffmpeg
LOCAL_SRC_FILES := org_jitsi_impl_neomedia_codec_FFmpeg.c
LOCAL_LDLIBS := -llog -lm -lz
LOCAL_STATIC_LIBRARIES := avformat avfilter avcodec swscale swresample avutil
LOCAL_C_INCLUDES := . $(TARGET_ARCH_ABI) $(TARGET_ARCH_ABI)/ffmpeg
include $(BUILD_SHARED_LIBRARY)
