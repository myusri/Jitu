ROOT := $(call my-dir)
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_LDLIBS    :=
LOCAL_MODULE    := jng722
LOCAL_SRC_FILES := \
    org/jitsi/impl/neomedia/codec/audio/g722/g722.c \
    org/jitsi/impl/neomedia/codec/audio/g722/JNIDecoder.c \
    org/jitsi/impl/neomedia/codec/audio/g722/JNIEncoder.c \
    org/jitsi/impl/neomedia/codec/audio/g722/vector_int.c

include $(BUILD_SHARED_LIBRARY)
