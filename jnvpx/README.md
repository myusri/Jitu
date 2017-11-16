Clone `libvpx` in the `jni` directory:

    git clone https://chromium.googlesource.com/webm/libvpx

We want to build for both x86 and ARM targets. There are two target
directories available in the `jnvpx` project, namely, `x86` and
`armeabi-v7a`. In each of the target directory, clone the `libvpx`
project:

    git clone https://chromium.googlesource.com/webm/libvpx armeabi-v7a/libvpx
    git clone https://chromium.googlesource.com/webm/libvpx x86/libvpx

We could've also create a soft link in `x86` directory to the cloned
`libvpx` in `armeabi-v7a` directory.

The `jni/Android.mk` will include the appropriate `Android.mk` in the
target directories depending on the target currently being built.

In `armeabi-v7a`, configure to build `libvpx` for ARM:

    libvpx/configure --target=armv7-android-gcc --enable-pic\
     --disable-runtime-cpu-detect\
     --disable-examples --disable-tools --disable-docs\
     --sdk-path=$NDK --extra-cflags="-mfloat-abi=softfp -mfpu=neon"

Assuming `$NDK` refers to Android NDK directory (typically the `ndk-bundle`
in the SDK directory). `Configure` will create these files:

* `Makefile`
* `libs-`*target*`-android-gcc.mk`
* `config.log`
* `config.mk`
* `vpx_config.c`
* `vpx_config.h`

Now in the `x86` directory, configure to build `libvpx` for x86:

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

Run the `ndk-build` in the project directory (the parent of the `jni` directory).

    $NDK/ndk-build APP_ABI=armeabi-v7a

Ideally, building `jnvpx` using Gradle/Android Studio would just work
but it won't. Some target dependent files are created at the right places
and some would be dropped in the project directory as can be seen below.
~~~~~~~~~~~~~~
:jnvpx:externalNativeBuildDebug
Build jnvpx x86
    [CREATE] /Users/myusri/work/Jitu/jnvpx/jni/../x86/vp8_rtcd.h
    [CREATE] vp9_rtcd.h
    [CREATE] /Users/myusri/work/Jitu/jnvpx/jni/../x86/vpx_scale_rtcd.h
    [CREATE] /Users/myusri/work/Jitu/jnvpx/jni/../x86/vpx_dsp_rtcd.h
    [CREATE] vpx_config.asm
~~~~~~~~~~~~~~

Which will lead to failure when building for ARM target. Move
`vp9_rtcd.h` and `vpx_config.asm` into the `x86` directory and build
again.

Copy `vp9_rtcd.h` and `vpx_config.asm` to `x86` target folder. There
will also be target files related to ARM generated in `jnvpx` project
folder as can be seen below.
~~~~~~~~~~~~~~
Build jnvpx armeabi-v7a
    [CREATE] /Users/myusri/work/Jitu/jnvpx/jni/../armeabi-v7a/vp8_rtcd.h
    [CREATE] vpx_scale_rtcd.h
    [CREATE] vpx_dsp_rtcd.h
~~~~~~~~~~~~~~

Move `vpx_scale_rtcd.h` and `vpx_dsp_rtcd.h` to `armeabi-v7a` target
directory. It is best also to create a soft link `libvpx` in the project
directory to `libvpx` directory in either of the targets. This has
something to do with path to `ads2gas.pl` used in building
`vpx_config.asm`.

Now build `jnvpx` project again. Everything should build fine.
Copy `vp9_rtcd.h` and `vpx_config.asm` in the project folder to
`armeabi-v7a` target folder. There will also be target files related to
ARM generated in `jnvpx` project folder as can be seen below.


