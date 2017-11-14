# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)
enable_language(C)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             opus

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
             ../../ext/libopus/src/opus_encoder.c
             ../../ext/libopus/celt/celt_encoder.c
             ../../ext/libopus/celt/bands.c
             ../../ext/libopus/celt/entcode.c
             ../../ext/libopus/celt/entdec.c
             ../../ext/libopus/celt/entenc.c
             ../../ext/libopus/celt/mathops.c
             ../../ext/libopus/celt/vq.c
             ../../ext/libopus/celt/cwrs.c
             ../../ext/libopus/celt/celt.c
             ../../ext/libopus/celt/mdct.c
             ../../ext/libopus/celt/kiss_fft.c
             ../../ext/libopus/celt/bands.c
             ../../ext/libopus/celt/pitch.c
             ../../ext/libopus/celt/celt_lpc.c
             ../../ext/libopus/celt/quant_bands.c
             ../../ext/libopus/celt/laplace.c
             ../../ext/libopus/celt/modes.c
             ../../ext/libopus/celt/rate.c
             ../../ext/libopus/silk/lin2log.c
             ../../ext/libopus/silk/enc_API.c
             ../../ext/libopus/silk/resampler.c
             ../../ext/libopus/silk/resampler_private_IIR_FIR.c
             ../../ext/libopus/silk/resampler_private_up2_HQ.c
             ../../ext/libopus/silk/resampler_private_down_FIR.c
             ../../ext/libopus/silk/resampler_private_AR2.c
             ../../ext/libopus/silk/resampler_rom.c
             ../../ext/libopus/silk/float/encode_frame_FLP.c
             ../../ext/libopus/silk/gain_quant.c
             ../../ext/libopus/silk/log2lin.c
             ../../ext/libopus/silk/encode_pulses.c
             ../../ext/libopus/silk/code_signs.c
             ../../ext/libopus/silk/tables_pulses_per_block.c
             ../../ext/libopus/silk/tables_other.c
             ../../ext/libopus/silk/shell_coder.c
             ../../ext/libopus/silk/encode_indices.c
             ../../ext/libopus/silk/tables_LTP.c
             ../../ext/libopus/silk/tables_pitch_lag.c
             ../../ext/libopus/silk/NLSF_unpack.c
             ../../ext/libopus/silk/tables_gain.c
             ../../ext/libopus/silk/float/wrappers_FLP.c
             ../../ext/libopus/silk/quant_LTP_gains.c
             ../../ext/libopus/silk/VQ_WMat_EC.c
             ../../ext/libopus/silk/NSQ.c
             ../../ext/libopus/silk/LPC_analysis_filter.c
             ../../ext/libopus/silk/NSQ_del_dec.c
             ../../ext/libopus/silk/process_NLSFs.c
             ../../ext/libopus/silk/NLSF2A.c
             ../../ext/libopus/silk/bwexpander_32.c
             ../../ext/libopus/silk/LPC_inv_pred_gain.c
             ../../ext/libopus/silk/table_LSF_cos.c
             ../../ext/libopus/silk/NLSF_encode.c
             ../../ext/libopus/silk/NLSF_decode.c
             ../../ext/libopus/silk/NLSF_stabilize.c
             ../../ext/libopus/silk/sort.c
             ../../ext/libopus/silk/NLSF_VQ_weights_laroia.c
             ../../ext/libopus/silk/NLSF_del_dec_quant.c
             ../../ext/libopus/silk/NLSF_VQ.c
             ../../ext/libopus/silk/interpolate.c
             ../../ext/libopus/silk/float/wrappers_FLP.c
             ../../ext/libopus/silk/A2NLSF.c
             ../../ext/libopus/silk/float/process_gains_FLP.c
             ../../ext/libopus/silk/float/find_pred_coefs_FLP.c
             ../../ext/libopus/silk/float/residual_energy_FLP.c
             ../../ext/libopus/silk/float/energy_FLP.c
             ../../ext/libopus/silk/float/LPC_analysis_filter_FLP.c
             ../../ext/libopus/silk/float/find_LPC_FLP.c
             ../../ext/libopus/silk/float/burg_modified_FLP.c
             ../../ext/libopus/silk/float/inner_product_FLP.c
             ../../ext/libopus/silk/float/scale_copy_vector_FLP.c
             ../../ext/libopus/silk/float/LTP_analysis_filter_FLP.c
             ../../ext/libopus/silk/float/LTP_scale_ctrl_FLP.c
             ../../ext/libopus/silk/float/find_LTP_FLP.c
             ../../ext/libopus/silk/float/scale_vector_FLP.c
             ../../ext/libopus/silk/float/regularize_correlations_FLP.c
             ../../ext/libopus/silk/float/corrMatrix_FLP.c
             ../../ext/libopus/silk/float/noise_shape_analysis_FLP.c
             ../../ext/libopus/silk/float/bwexpander_FLP.c
             ../../ext/libopus/silk/float/LPC_inv_pred_gain_FLP.c
             ../../ext/libopus/silk/float/autocorrelation_FLP.c
             ../../ext/libopus/silk/float/warped_autocorrelation_FLP.c
             ../../ext/libopus/silk/float/apply_sine_window_FLP.c
             ../../ext/libopus/silk/float/find_pitch_lags_FLP.c
             ../../ext/libopus/silk/float/pitch_analysis_core_FLP.c
             ../../ext/libopus/silk/pitch_est_tables.c
             ../../ext/libopus/silk/float/sort_FLP.c
             ../../ext/libopus/silk/resampler_down2.c
             ../../ext/libopus/silk/resampler_down2_3.c
             ../../ext/libopus/silk/float/k2a_FLP.c
             ../../ext/libopus/silk/float/schur_FLP.c
             ../../ext/libopus/silk/LP_variable_cutoff.c
             ../../ext/libopus/silk/biquad_alt.c
             ../../ext/libopus/silk/VAD.c
             ../../ext/libopus/silk/sigm_Q15.c
             ../../ext/libopus/silk/ana_filt_bank_1.c
             ../../ext/libopus/silk/control_SNR.c
             ../../ext/libopus/silk/stereo_encode_pred.c
             ../../ext/libopus/silk/stereo_LR_to_MS.c
             ../../ext/libopus/silk/stereo_quant_pred.c
             ../../ext/libopus/silk/stereo_find_predictor.c
             ../../ext/libopus/silk/inner_prod_aligned.c
             ../../ext/libopus/silk/sum_sqr_shift.c
             ../../ext/libopus/silk/HP_variable_cutoff.c
             ../../ext/libopus/silk/control_codec.c
             ../../ext/libopus/silk/tables_NLSF_CB_NB_MB.c
             ../../ext/libopus/silk/tables_NLSF_CB_WB.c
             ../../ext/libopus/silk/control_audio_bandwidth.c
             ../../ext/libopus/silk/init_encoder.c
             ../../ext/libopus/silk/check_control_input.c
             ../../ext/libopus/src/analysis.c
             ../../ext/libopus/src/repacketizer.c
             ../../ext/libopus/src/opus.c
             ../../ext/libopus/src/opus_decoder.c
             ../../ext/libopus/celt/celt_decoder.c
             ../../ext/libopus/silk/dec_API.c
             ../../ext/libopus/silk/stereo_MS_to_LR.c
             ../../ext/libopus/silk/decode_frame.c
             ../../ext/libopus/silk/PLC.c
             ../../ext/libopus/silk/bwexpander.c
             ../../ext/libopus/silk/CNG.c
             ../../ext/libopus/silk/decode_core.c
             ../../ext/libopus/silk/decode_parameters.c
             ../../ext/libopus/silk/decode_pitch.c
             ../../ext/libopus/silk/decode_pulses.c
             ../../ext/libopus/silk/decode_indices.c
             ../../ext/libopus/silk/stereo_decode_pred.c
             ../../ext/libopus/silk/decoder_set_fs.c
             ../../ext/libopus/silk/init_decoder.c
             ../../ext/libopus/src/mlp.c
             ../../ext/libopus/src/mlp_data.c
             ../../ext/libopus/silk/LPC_fit.c )

include_directories(
    ../../ext/libopus/src
    ../../ext/libopus/include
    ../../ext/libopus/celt
    ../../ext/libopus/silk
    ../../ext/libopus/silk/float)

add_definitions(-DOPUS_BUILD -DSTDC_HEADERS -DVAR_ARRAYS)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because system libraries are included in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in the
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       opus

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
