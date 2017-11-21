LOCAL_PATH:= jni/speex
include $(CLEAR_VARS)

LOCAL_MODULE:= libspeex

LOCAL_C_INCLUDES := jni/speex/include src/include/speex

LOCAL_SRC_FILES := \
	libspeex/bits.c                         \
	libspeex/cb_search.c                    \
	libspeex/exc_10_16_table.c              \
	libspeex/exc_10_32_table.c              \
	libspeex/exc_20_32_table.c              \
	libspeex/exc_5_256_table.c              \
	libspeex/exc_5_64_table.c               \
	libspeex/exc_8_128_table.c              \
	libspeex/filters.c                      \
	libspeex/gain_table.c                   \
	libspeex/gain_table_lbr.c               \
	libspeex/hexc_10_32_table.c             \
	libspeex/hexc_table.c                   \
	libspeex/high_lsp_tables.c              \
	libspeex/lpc.c                          \
	libspeex/lsp.c                          \
	libspeex/lsp_tables_nb.c                \
	libspeex/ltp.c                          \
	libspeex/modes.c                        \
	libspeex/modes_wb.c                     \
	libspeex/nb_celp.c                      \
	libspeex/quant_lsp.c                    \
	libspeex/sb_celp.c                      \
	libspeex/speex.c                        \
	libspeex/speex_callbacks.c              \
	libspeex/speex_header.c                 \
	libspeex/stereo.c                       \
	libspeex/vbr.c                          \
	libspeex/vq.c                           \
	libspeex/window.c

LOCAL_CFLAGS := -O2 -DFIXED_POINT -DUSE_KISS_FFT -DEXPORT=""

ifeq ($(TARGET_ARCH_ABI),armeabi)
    LOCAL_CFLAGS += -mfpu=vfp -mfloat-abi=softfp
endif

ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
    LOCAL_CFLAGS += -mfpu=neon -mfloat-abi=softfp
endif

include $(BUILD_STATIC_LIBRARY)

LOCAL_PATH:= jni/speexdsp
include $(CLEAR_VARS)

LOCAL_MODULE := libspeexdsp

LOCAL_C_INCLUDES := jni/speexdsp/include  src/include/speex

LOCAL_SRC_FILES := \
	libspeexdsp/buffer.c                       \
	libspeexdsp/fftwrap.c                      \
	libspeexdsp/filterbank.c                   \
	libspeexdsp/jitter.c                       \
	libspeexdsp/mdf.c                          \
	libspeexdsp/preprocess.c                   \
	libspeexdsp/resample.c                     \
	libspeexdsp/scal.c                         \
	libspeexdsp/smallft.c

LOCAL_CFLAGS := -O2 -DFIXED_POINT -DUSE_KISS_FFT -DEXPORT=""

ifeq ($(TARGET_ARCH_ABI),armeabi)
    LOCAL_CFLAGS += -mfpu=vfp -mfloat-abi=softfp
endif

ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
    LOCAL_CFLAGS += -mfpu=neon -mfloat-abi=softfp
endif

include $(BUILD_STATIC_LIBRARY)

LOCAL_PATH := jni
include $(CLEAR_VARS)
LOCAL_MODULE := jnspeex
LOCAL_SRC_FILES := org_jitsi_impl_neomedia_codec_audio_speex_Speex.c
LOCAL_STATIC_LIBRARIES := libspeex libspeexdsp
LOCAL_C_INCLUDES := . src/include/speex jni/speex/include jni/speexdsp/include
include $(BUILD_SHARED_LIBRARY)
