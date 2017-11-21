To build `jnspeex`, we need Speex and SpeexDSP. Download from:

https://www.speex.org/downloads/

Speex 1.2.0 and SpeexDSP 1.2rc1 seems to work.
Expand Speex into `jni/speex` and SpeexDSP `jni/speexdsp`. Rename
the expanded folders as necessary.

May need to fix jni/speex/libspeex/nb_celp.c starting from line 518
from:

         bw_lpc(0.9, interp_lpc, bw_lpc1, NB_ORDER);
         bw_lpc(0.55, interp_lpc, bw_lpc2, NB_ORDER);

to:

         bw_lpc(QCONST16(0.9f,15), interp_lpc, bw_lpc1, NB_ORDER);
         bw_lpc(QCONST16(0.55f,15), interp_lpc, bw_lpc2, NB_ORDER);

We have been using guide from:

http://developer.samsung.com/galaxy/others/porting-speex-library-in-android-with-jni

To build Speex for Android. And also:

https://github.com/illuspas/libspeex-Android

