LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := audiomodule-prebuilt
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/includes

ifeq ($(TARGET_ARCH_ABI), armeabi-v7a)
    LOCAL_SRC_FILES := static_libs/armeabi-v7a/libaudiomodule.a
else
    LOCAL_SRC_FILES := static_libs/armeabi/libaudiomodule.a
endif

include $(PREBUILT_STATIC_LIBRARY)
include $(CLEAR_VARS)

LOCAL_MODULE := plugin_sample
LOCAL_LDLIBS := -llog
LOCAL_SRC_FILES := plugin_sample.c
LOCAL_STATIC_LIBRARIES := audiomodule-prebuilt

include $(BUILD_SHARED_LIBRARY)