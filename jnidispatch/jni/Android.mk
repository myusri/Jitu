LOCAL_PATH := jni
include $(CLEAR_VARS)
LOCAL_MODULE := jnidispatch
LOCAL_SRC_FILES := $(TARGET_ARCH_ABI)/libjnidispatch.so
include $(PREBUILT_SHARED_LIBRARY)
