LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)


OpenCV_INSTALL_MODULES := on
OpenCV_CAMERA_MODULES := off

OPENCV_LIB_TYPE :=STATIC

ifeq ("$(wildcard $(OPENCV_MK_PATH))","")
include ..\..\..\..\native\jni\OpenCV.mk
else
include $(OPENCV_MK_PATH)
endif

LOCAL_MODULE := OpenCV

CPP_FILES := $(wildcard $(LOCAL_PATH)/*.cpp)

LOCAL_SRC_FILES := $(CPP_FILES:$(LOCAL_PATH)/%=%)

LOCAL_LDLIBS +=  -lm -llog -latomic

include $(BUILD_SHARED_LIBRARY)