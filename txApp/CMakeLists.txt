cmake_minimum_required(VERSION 3.4.1)

## libopus

set( opus_src_DIR src/main/jni/ext/libopus )
set( opus_cmake_DIR src/main/jni/cmake/libopus )
#set( opus_build_DIR ${opus_cmake_DIR}/output )
#file ( MAKE_DIRECTORY ${opus_build_DIR} )
add_subdirectory( ${opus_cmake_DIR} )

## libspeex

set( speex_src_DIR src/main/jni/ext/speex )
set( speex_cmake_DIR src/main/jni/cmake/speex )
#set( speex_build_DIR ${speex_cmake_DIR}/output )
#file ( MAKE_DIRECTORY ${speex_build_DIR} )
add_subdirectory( ${speex_cmake_DIR} )

## JNI stubs

include_directories( src/main/jniLibs/include )
include_directories( ${opus_src_DIR}/include )
include_directories( ${speex_src_DIR}/include )

add_library(
  jnspeex SHARED
  src/main/jni/jnspeex/org_jitsi_impl_neomedia_codec_audio_speex_Speex.c )
target_link_libraries( jnspeex speex )

add_library(
  jnopus SHARED
  src/main/jni/jnopus/org_jitsi_impl_neomedia_codec_audio_opus_Opus.c )
target_link_libraries( jnopus opus )

set ( jniLibs_DIR ${CMAKE_CURRENT_SOURCE_DIR}/src/main/jniLibs )
 
add_library ( avcodec SHARED IMPORTED )
add_library ( avformat SHARED IMPORTED )
add_library ( avfilter SHARED IMPORTED )
add_library ( swscale SHARED IMPORTED )
add_library ( avutil SHARED IMPORTED )

set_target_properties ( avcodec
  PROPERTIES IMPORTED_LOCATION ${jniLibs_DIR}/${ANDROID_ABI}/libavcodec.so )
set_target_properties ( avformat
  PROPERTIES IMPORTED_LOCATION ${jniLibs_DIR}/${ANDROID_ABI}/libavformat.so )
set_target_properties ( avfilter
  PROPERTIES IMPORTED_LOCATION ${jniLibs_DIR}/${ANDROID_ABI}/libavfilter.so )
set_target_properties ( swscale
  PROPERTIES IMPORTED_LOCATION ${jniLibs_DIR}/${ANDROID_ABI}/libswscale.so )
set_target_properties ( avutil
  PROPERTIES IMPORTED_LOCATION ${jniLibs_DIR}/${ANDROID_ABI}/libavutil.so )

add_library(
  jnffmpeg SHARED
  src/main/jni/jnffmpeg/org/jitsi/impl/neomedia/codec/FFmpeg.c )
target_link_libraries( jnffmpeg avformat avcodec avfilter avutil swscale )
set( jnffmpeg_INCLUDE_DIRS
  ${CMAKE_CURRENT_SOURCE_DIR}/src/main/jni/ext/ffmpeg-1.0.10
  ${CMAKE_CURRENT_SOURCE_DIR}/src/main/jni/jnffmpeg )
set_target_properties( jnffmpeg PROPERTIES INCLUDE_DIRECTORIES "${jnffmpeg_INCLUDE_DIRS}" )

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
