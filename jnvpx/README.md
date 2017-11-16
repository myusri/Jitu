We want to build for both x86 and ARM targets. There are two target
folders available in the `jnvpx` project, namely, `x86` and
`armeabi-v7a`. Clone the `libvpx` project:

    git clone https://chromium.googlesource.com/webm/libvpx armeabi-v7a/libvpx
    ln -s ../armeabi-v7a/libvpx x86
    ln -s armeabi-v7a/libvpx .

The last command is there to avoid failure to build `vpx_config.asm`.

In `armeabi-v7a`, configure to build `libvpx` for ARM:

    libvpx/configure --target=armv7-android-gcc\
     --enable-pic\
     --disable-runtime-cpu-detect\
     --disable-examples --disable-tools --disable-docs\
     --sdk-path=$NDK --extra-cflags="-mfloat-abi=softfp -mfpu=neon"

Assuming `$NDK` refers to Android NDK folder (typically the `ndk-bundle`
in the SDK folder). `Configure` will create these files:

* `Makefile`
* `libs-armv7-android-gcc.mk`
* `config.log`
* `config.mk`
* `vpx_config.c`
* `vpx_config.h`

Edit `libs-armv7-android-gcc.mk` and set `BUILD_PFX` to:

    BUILD_PFX=armeabi-v7a/

Now in the `x86` folder, configure to build `libvpx` for x86:

    export PATH=$NDK/toolchains/x86-4.9/prebuilt/linux-x86_64/bin:$PATH
    CROSS=i686-linux-android-\
     LDFLAGS="--sysroot=$NDK/platforms/android-19/arch-x86"\
     libvpx/configure --target=x86-android-gcc\
      --extra-cflags="--sysroot=$NDK/platforms/android-19/arch-x86"\
      --disable-runtime-cpu-detect\
      --disable-ssse3 --disable-sse4_1 --disable-avx2\
      --disable-examples --disable-tools --disable-docs\
      --enable-pic

Change `PATH` according to your machine platform. For example for MAC OS/X,
do this instead:

    export PATH=$NDK/toolchains/x86-4.9/prebuilt/darwin-x86_64/bin:$PATH

Edit `libs-x86-android-gcc.mk` and set `BUILD_PFX` to:

    BUILD_PFX=x86/

We need to modify `build/make/Android.mk` a little bit because we are
building using custom `BUILD_PFX` for each target. Locate this line:

    LOCAL_CODEC_SRCS_C = $(filter-out vpx_config.c %_neon.c, $(CODEC_SRCS_C))

And change it to:

    LOCAL_CODEC_SRCS_C = $(filter-out %vpx_config.c %_neon.c, $(CODEC_SRCS_C))

Now you should be able to build `jnvpx` using Gradle/Android Studio. To
build using Gradle, in the top project folder, issue this command:

    ./gradlew :jnvpx:build
