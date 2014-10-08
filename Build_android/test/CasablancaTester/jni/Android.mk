LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := cpprest
LOCAL_SRC_FILES := libcpprest.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := iconv
LOCAL_SRC_FILES := libiconv.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := common_utilities
LOCAL_SRC_FILES := libcommon_utilities.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := httptest_utilities
LOCAL_SRC_FILES := libhttptest_utilities.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := pplx_test
LOCAL_SRC_FILES := libpplx_test.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := json_test
LOCAL_SRC_FILES := libjson_test.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := uri_test
LOCAL_SRC_FILES := liburi_test.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := httpclient_test
LOCAL_SRC_FILES := libhttpclient_test.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := httplistener_test
LOCAL_SRC_FILES := libhttplistener_test.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := streams_test
LOCAL_SRC_FILES := libstreams_test.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := utils_test
LOCAL_SRC_FILES := libutils_test.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := websocketclient_test
LOCAL_SRC_FILES := libwebsocketclient_test.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := websockettest_utilities
LOCAL_SRC_FILES := libwebsockettest_utilities.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := unittestpp
LOCAL_SRC_FILES := libunittestpp.so

include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := CasablancaTester
LOCAL_SRC_FILES := CasablancaTester.cpp
LOCAL_CPPFLAGS += -I ${LOCAL_PATH}/../../../../Release/include
LOCAL_CPPFLAGS += -I ${LOCAL_PATH}/../../../../Release/tests/common/UnitTestpp
LOCAL_CPPFLAGS += -I ${LOCAL_PATH}/../../../build/Boost-for-Android/build/include/boost-1_55
#LOCAL_CPPFLAGS += -I D:/casablanca.git-tfs/casablanca/packages/boost.1.55.0.16/lib/native/include
LOCAL_CPPFLAGS += -fexceptions -frtti
LOCAL_LDLIBS := -llog
LOCAL_SHARED_LIBRARIES := unittestpp cpprest

include $(BUILD_SHARED_LIBRARY)
