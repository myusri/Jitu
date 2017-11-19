Libjitsi Java Native Interfaces (JNI) have too much dependency on FFmpeg
version 1.0.10. Assuming
we are in the `jnffmpeg` project folder, clone the right release branch
of the FFmpeg library into both `x86` and `armeabi-v7a` target folders:

    git clone -b release/1.0 https://github.com/FFmpeg/FFmpeg.git armeabi-v7a/ffmpeg
    ln -s ../armeabi-v7a/ffmpeg x86

For the `x86` target folder, we have chosen to use soft link to refer to
the library source folder. Since the repo is big, we may want to use
FFmpeg download archive instead:

http://www.ffmpeg.org/releases/ffmpeg-1.0.10.tar.gz

First, edit the `configure` script , find the lines with variable names below
and replaced it like shown:

    SLIBNAME_WITH_VERSION='$(SLIBNAME)'
    SLIBNAME_WITH_MAJOR='$(SLIBNAME)'

Next, configure for `armeabi-v7a` target. In the `armeabi-v7a` folder:
~~~~~~~~~~~~~
ffmpeg/configure --disable-symver --enable-static --disable-shared --enable-pic \
 --target-os=linux --arch=armv7-a --cpu=armv7-a --disable-asm --disable-stripping\
 --prefix=../libs\
 --libdir=../libs/armeabi-v7a\
 --cross-prefix=$NDK/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-\
 --extra-cflags="-O3 -Wno-attributes -pipe -ffast-math -fstrict-aliasing\
 -Wno-psabi -Wa,--noexecstack -DANDROID -DNDEBUG -march=armv7-a"\
 --sysroot=$NDK/platforms/android-16/arch-arm/\
 --disable-ffmpeg\
 --disable-ffplay\
 --disable-ffprobe\
 --disable-ffserver\
 --disable-doc
~~~~~~~~~~~~~

Assuming we will be building on Mac OS. If not change `darwin` accordingly.
Build for `armeabi-v7a` target.

    make

Now configure for `x86` in the `x86` target folder:
~~~~~~~~~~~~~
ffmpeg/configure --disable-symver --enable-static --disable-shared --enable-pic \
 --target-os=linux --arch=x86 --disable-asm --disable-stripping\
 --prefix=../libs\
 --libdir=../libs/x86\
 --cross-prefix=$NDK/toolchains/x86-4.9/prebuilt/darwin-x86_64/bin/i686-linux-android-\
 --extra-cflags="-O3 -Wno-attributes -pipe -ffast-math -fstrict-aliasing\
 -Wno-psabi -Wa,--noexecstack -DANDROID -DNDEBUG\
 -march=i686 -mtune=intel -mssse3 -mfpmath=sse -m32"\
 --sysroot=$NDK/platforms/android-16/arch-x86/\
 --disable-ffmpeg\
 --disable-ffplay\
 --disable-ffprobe\
 --disable-ffserver\
 --disable-doc
~~~~~~~~~~~~~

Build for `x86` target:

    make

Now you should be able to build `jnffmpeg` using Gradle/Android Studio. To
build using Gradle, in the top project folder, issue this command:

    ./gradlew :jnffmpeg:build
