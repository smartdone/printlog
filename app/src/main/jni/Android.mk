LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE    := slog
LOCAL_SRC_FILES := \
    slog.cpp
LOCAL_LDLIBS	:= -pthread

include $(BUILD_SHARED_LIBRARY)