Libjitsi implements its JNI using the JNA project, which is hosted at:

https://github.com/java-native-access/jna

We do not have to built this project. The project includes prebuilt
libraries for doing JNI on various platform targets (JNIDispatch library).
For Android, we just need to expand the target jars we want to support,
which is located at:

https://github.com/java-native-access/jna/tree/master/dist

For this `jnidispatch` module, for convenience, `android-armv7.jar` and
`android-x86.jar` have been expanded into `jni/armeabi-v7a` and `jni/x86`
folders, respectively (from version 4.5.0). This module exposes these
files as prebuilt libraries.

See also:

https://github.com/java-native-access/jna/blob/master/www/AndroidDevelopmentEnvironment.md
