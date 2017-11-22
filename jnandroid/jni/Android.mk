#
# Jitsi, the OpenSource Java VoIP and Instant Messaging client.
#
# Copyright @ 2015 Atlassian Pty Ltd
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

ROOT := $(call my-dir)
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_LDLIBS    := -lEGL -lGLESv1_CM -llog
LOCAL_MODULE    := jnawtrenderer
LOCAL_SRC_FILES := JAWTRenderer_Android.c org_jitsi_impl_neomedia_jmfext_media_renderer_video_JAWTRenderer.c
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_CFLAGS    := -Isrc/include/openmax
LOCAL_LDLIBS    := -llog
LOCAL_MODULE    := jnopenmax
LOCAL_SRC_FILES := org_jitsi_impl_neomedia_codec_video_h264_OMXDecoder.c
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_LDLIBS    := -lOpenSLES -llog
LOCAL_MODULE    := jnopensles
LOCAL_SRC_FILES := org_jitsi_impl_neomedia_device_OpenSLESSystem.c org_jitsi_impl_neomedia_jmfext_media_protocol_opensles_DataSource.c org_jitsi_impl_neomedia_jmfext_media_renderer_audio_OpenSLESRenderer.c
include $(BUILD_SHARED_LIBRARY)
