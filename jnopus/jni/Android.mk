LOCAL_PATH := jni/libopus
include $(CLEAR_VARS)

include jni/libopus/celt_sources.mk
include jni/libopus/opus_sources.mk
include jni/libopus/silk_sources.mk
LOCAL_MODULE    := libopus

LOCAL_C_INCLUDES += jni/libopus/include jni/libopus/src jni/libopus/silk \
                    jni/libopus/celt jni/libopus/silk/fixed $(OGG_DIR)/include
LOCAL_C_INCLUDES += $(TARGET_ARCH_ABI)
LOCAL_SRC_FILES := $(CELT_SOURCES) $(SILK_SOURCES) $(SILK_SOURCES_FIXED) \
                   $(OPUS_SOURCES) $(OPUS_SOURCES_FLOAT) src/repacketizer_demo.c
LOCAL_CFLAGS        := -DNULL=0 -DSOCKLEN_T=socklen_t -DLOCALE_NOT_USED \
                       -D_LARGEFILE_SOURCE=1 -D_FILE_OFFSET_BITS=64 \
                       -Drestrict='' -D__EMX__ -DOPUS_BUILD -DFIXED_POINT \
                       -DUSE_ALLOCA -DHAVE_LRINT -DHAVE_LRINTF -O2 -fno-math-errno
LOCAL_CPPFLAGS      := -DBSD=1 -ffast-math -O2 -funroll-loops
# Note: OPUS enhanced DSP/NEON implementation is not yet compatible with arm64.
# Only add the appropriate defines for 32-bit arm architecture.
ifeq ($(TARGET_ARCH_ABI),$(filter $(TARGET_ARCH_ABI), armeabi-v7a))
LOCAL_ARM_NEON := true
LOCAL_SRC_FILES += $(CELT_SOURCES_ARM)
LOCAL_CFLAGS += -DOPUS_ARM_ASM -DOPUS_ARM_INLINE_ASM \
                    -DOPUS_ARM_MAY_HAVE_EDSP -DOPUS_ARM_INLINE_EDSP \
                    -DOPUS_ARM_MAY_HAVE_MEDIA -DOPUS_ARM_INLINE_MEDIA \
                    -DOPUS_HAVE_RTCD
# DSP, MEDIA and NEON instructions are in the same assembler file - thus we
# need to include it even if NEON is not supported on target platform.
LOCAL_SRC_FILES += $(subst .s,-gnu.s,$(CELT_SOURCES_ARM_ASM))

LOCAL_SRC_FILES += $(CELT_SOURCES_ARM_NEON_INTR) \
                       $(SILK_SOURCES_ARM_NEON_INTR) \
                       $(SILK_SOURCES_FIXED_ARM_NEON_INTR)

LOCAL_CFLAGS += -DOPUS_ARM_MAY_HAVE_NEON -DOPUS_ARM_MAY_HAVE_NEON_INTR \
                    -DOPUS_ARM_PRESUME_NEON -DOPUS_ARM_INLINE_NEON
endif

ifeq ($(TARGET_ARCH_ABI),$(filter $(TARGET_ARCH_ABI), x86 x86_64))

#ifeq ($(ARCH_X86_HAVE_SSSE3),true)
LOCAL_SRC_FILES += $(CELT_SOURCES_SSE) $(CELT_SOURCES_SSE2)
LOCAL_CFLAGS += -DOPUS_X86_MAY_HAVE_SSE -DOPUS_X86_PRESUME_SSE \
                      -DOPUS_X86_MAY_HAVE_SSE2 -DOPUS_X86_PRESUME_SSE2
#endif
ifeq ($(ARCH_X86_HAVE_SSE4_1),true)
LOCAL_SRC_FILES += $(CELT_SOURCES_SSE4_1) \
                   $(SILK_SOURCES_SSE4_1) $(SILK_SOURCES_FIXED_SSE4_1)
LOCAL_CFLAGS += -DOPUS_X86_MAY_HAVE_SSE4_1 -DOPUS_X86_PRESUME_SSE4_1
endif

endif
include $(BUILD_STATIC_LIBRARY)

LOCAL_PATH := jni
include $(CLEAR_VARS)
LOCAL_MODULE := jnopus
LOCAL_SRC_FILES := org_jitsi_impl_neomedia_codec_audio_opus_Opus.c
LOCAL_STATIC_LIBRARIES := libopus
LOCAL_C_INCLUDES := . $(TARGET_ARCH_ABI) jni/libopus/include
include $(BUILD_SHARED_LIBRARY)
