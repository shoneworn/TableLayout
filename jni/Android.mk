LOCAL_PATH:= $(call my-dir)
# һ������ģ�����
include $(CLEAR_VARS)
LOCAL_SRC_FILES:=com_shone_androidtest_HelloWorld.c
LOCAL_C_INCLUDES := $(JNI_H_INCLUDE)
LOCAL_MODULE := libHelloWorldJni
LOCAL_SHARED_LIBRARIES := libutils
LOCAL_PRELINK_MODULE := false
LOCAL_MODULE_TAGS :=optional
include $(BUILD_SHARED_LIBRARY)