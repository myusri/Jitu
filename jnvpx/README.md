Clone `libvpx` in the `jni` directory:

    git clone https://chromium.googlesource.com/webm/libvpx

We want to build for both x86 and ARM targets.

In the `jni` directory, first run `configure` for ARM:

    ../jni/libvpx/configure --target=armv7-android-gcc --enable-pic\
     --disable-runtime-cpu-detect\
     --disable-examples --disable-tools --disable-docs\
     --sdk-path=$NDK --extra-cflags="-mfloat-abi=softfp -mfpu=neon"

Assuming `$NDK` refers to Android NDK directory (typically the `ndk-bundle`
in the SDK directory).

Confirm this file exists in the cloned `libvpx` directory:

    build/make/Android.mk

Link `jni/libvpx` in the project directory to avoid problem with some
ASM conversion script being not found:

   ln -s jni/libvpx .

Run the `ndk-build` in the project directory (the parent of the `jni` directory).

    $NDK/ndk-build APP_ABI=armeabi-v7a

Now run `configure` for x86 in the `jni` directory:

    export PATH=$NDK/toolchains/x86-4.9/prebuilt/linux-x86_64/bin:$PATH
    CROSS=i686-linux-android-\
     LDFLAGS="--sysroot=$NDK/platforms/android-19/arch-x86"\
     ../libvpx/configure --target=x86-android-gcc\
      --extra-cflags="--sysroot=$NDK/platforms/android-19/arch-x86"\
      --disable-runtime-cpu-detect\
      --disable-ssse3 --disable-sse4_1 --disable-avx2\
      --disable-examples --disable-tools --disable-docs\
      --enable-pic

Change `PATH` according to your machine platform. For example for MAC OS/X,
do this instead:

    export PATH=$NDK/toolchains/x86-4.9/prebuilt/darwin-x86_64/bin:$PATH

Run the `ndk-build` in the project directory.

    $NDK/ndk-build APP_ABI=x86 clean
    $NDK/ndk-build APP_ABI=x86
