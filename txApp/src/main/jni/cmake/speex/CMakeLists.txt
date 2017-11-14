# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)
enable_language(C)

# Creates and names a library sets it as either STATIC
# or SHARED and provides the relative paths to its source code.
# You can define multiple libraries and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             speex

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.

            ../../ext/speex/libspeex/mdf.c
            ../../ext/speex/libspeex/preprocess.c
            ../../ext/speex/libspeex/filterbank.c
            ../../ext/speex/libspeex/fftwrap.c
            ../../ext/speex/libspeex/smallft.c
            ../../ext/speex/libspeex/resample.c
            ../../ext/speex/libspeex/bits.c
            ../../ext/speex/libspeex/cb_search.c
            ../../ext/speex/libspeex/exc_10_16_table.c
            ../../ext/speex/libspeex/exc_10_32_table.c
            ../../ext/speex/libspeex/exc_20_32_table.c
            ../../ext/speex/libspeex/exc_5_256_table.c
            ../../ext/speex/libspeex/exc_5_64_table.c
            ../../ext/speex/libspeex/exc_8_128_table.c
            ../../ext/speex/libspeex/fftwrap.c
            ../../ext/speex/libspeex/filterbank.c
            ../../ext/speex/libspeex/filters.c
            ../../ext/speex/libspeex/gain_table.c
            ../../ext/speex/libspeex/gain_table_lbr.c
            ../../ext/speex/libspeex/hexc_10_32_table.c
            ../../ext/speex/libspeex/hexc_table.c
            ../../ext/speex/libspeex/high_lsp_tables.c
            ../../ext/speex/libspeex/kiss_fft.c
            ../../ext/speex/libspeex/kiss_fftr.c
            ../../ext/speex/libspeex/lpc.c
            ../../ext/speex/libspeex/lsp.c
            ../../ext/speex/libspeex/lsp_tables_nb.c
            ../../ext/speex/libspeex/ltp.c
            ../../ext/speex/libspeex/mdf.c
            ../../ext/speex/libspeex/modes.c
            ../../ext/speex/libspeex/modes_wb.c
            ../../ext/speex/libspeex/nb_celp.c
            ../../ext/speex/libspeex/preprocess.c
            ../../ext/speex/libspeex/quant_lsp.c
            ../../ext/speex/libspeex/sb_celp.c
            ../../ext/speex/libspeex/smallft.c
            ../../ext/speex/libspeex/speex.c
            ../../ext/speex/libspeex/speex_callbacks.c
            ../../ext/speex/libspeex/speex_header.c
            ../../ext/speex/libspeex/vbr.c
            ../../ext/speex/libspeex/vq.c
            ../../ext/speex/libspeex/window.c
            ../../ext/speex/libspeex/buffer.c
            ../../ext/speex/libspeex/resample.c
            ../../ext/speex/libspeex/scal.c
           )

include_directories( ../../ext/speex/include )

add_definitions(
        -DEXPORT=
        -DFLOATING_POINT
        -DUSE_SMALLFT
        -DVAR_ARRAYS
        -DRESAMPLE_FORCE_FULL_SINC_TABLE
        )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because system libraries are included in the search path by
# default you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries such as libraries you define in the
# build script prebuilt third-party libraries or system libraries.

target_link_libraries( # Specifies the target library.
                       speex

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
