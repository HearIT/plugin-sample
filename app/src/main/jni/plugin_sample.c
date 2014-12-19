#include <jni.h>
#include <stdlib.h>
#include "audio_module.h"

#define MAX_FACTOR 5

typedef struct{
    int factor;
} gain_data;

/**
 * Processing function.
 *
 * @param sample_rate
 * @param buffer_frames
 * @param input_channels
 * @param input_buffer
 * @param output_channels
 * @param output_buffer
 */
static void process_func(void *context, int sample_rate, int buffer_frames,
                         int input_channels, const float *input_buffer,
                         int output_channels, float *output_buffer){

    gain_data *data = (gain_data *) context;
    float factor = (float) __sync_fetch_and_or(&data->factor, 0) * MAX_FACTOR * 0.01f;
    int i, j;

    for (i = 0; i < input_channels; ++i) {
        for (j = 0; j < buffer_frames; ++j) {
          float value = input_buffer[j] * factor;
          output_buffer[j] = value;
          output_buffer[j + buffer_frames] = value;
        }

        input_buffer += buffer_frames;
        output_buffer += buffer_frames;
    }
}

/**
 * Processing function must be registered here.
 */
JNIEXPORT jlong JNICALL Java_info_hearit_pluginsample_PluginAudioModule_configureNativeComponents
  (JNIEnv * env, jobject obj, jlong handle, jint channels){
      gain_data *data = malloc(sizeof(gain_data));

      if(data){
          // registers the process function process_func.
          am_configure((void *) handle, process_func, data);
      } else {
          free(data);
          data = NULL;
      }
  }

JNIEXPORT void JNICALL Java_info_hearit_pluginsample_PluginAudioModule_release
  (JNIEnv *env, jobject obj, jlong handle){
      gain_data *data = (gain_data *) handle;
      free(data);
  }

JNIEXPORT void JNICALL Java_info_hearit_pluginsample_PluginAudioModule_setGainFactor
  (JNIEnv *env, jobject obj, jlong handle, jint factor){
      gain_data *data = (gain_data *) handle;
      __sync_bool_compare_and_swap(&data->factor, data->factor, factor);
  }

JNIEXPORT jint JNICALL Java_info_hearit_pluginsample_PluginAudioModule_getGainFactor
  (JNIEnv *env, jobject obj, jlong handle){

    gain_data *data = (gain_data *) handle;
    return data->factor;
  }