cmake_minimum_required(VERSION 3.4.1)

## JNI stubs

include_directories( src/main/jniLibs/include )

set ( jniLibs_DIR ${CMAKE_CURRENT_SOURCE_DIR}/src/main/jniLibs )
 
add_library(
  jnopensles SHARED
  src/main/jni/jnopensles/org_jitsi_impl_neomedia_device_OpenSLESSystem.c
  src/main/jni/jnopensles/org_jitsi_impl_neomedia_jmfext_media_protocol_opensles_DataSource.c
  src/main/jni/jnopensles/org_jitsi_impl_neomedia_jmfext_media_renderer_audio_OpenSLESRenderer.c )
target_link_libraries( jnopensles log OpenSLES )

add_library(
  jnawtrenderer SHARED
  src/main/jni/jnawtrenderer/JAWTRenderer_Android.c
  src/main/jni/jnawtrenderer/org_jitsi_impl_neomedia_jmfext_media_renderer_video_JAWTRenderer.c )
target_link_libraries( jnawtrenderer EGL GLESv1_CM log )

add_library(
  jnopenmax SHARED
  src/main/jni/jnopenmax/org_jitsi_impl_neomedia_codec_video_h264_OMXDecoder.c )
target_link_libraries( jnopenmax log )
set_target_properties( jnopenmax PROPERTIES
  INCLUDE_DIRECTORIES ${CMAKE_CURRENT_SOURCE_DIR}/src/main/jniLibs/include/openmax )
