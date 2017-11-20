This `jnopus` module, which includes building `libopus`, is adapted from:

https://github.com/shenyaocn/libopus-Android.mk

Get `libopus` 1.2.1 from:

https://archive.mozilla.org/pub/opus/opus-1.2.1.tar.gz

And expand it into:

    jnopus/jni/libopus

You should be able to build the whole `jnopus` module including `libopus`
using Gradle/Android Studio. To build using Gradle, in the top level project:

    ./gradlew :jnopus:build

Original README from the Github link at the top follows.

# libopus-Android.mk
A build Android NDK script for libopus with NEON and SSE optimization.

As far as I know that building libopus without NEON and SSE optimization may lead to some compatibility issues (e.g. Chrome may unable to decode).

This script was modified from https://android.googlesource.com/platform/external/libopus/+/master .
It was used to build opus codec for IP Camera app and USB Camera app.
