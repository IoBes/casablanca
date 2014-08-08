NDK_TOOLCHAIN_VERSION := 4.8
#APP_STL := c++_shared
APP_STL := gnustl_shared
APP_CPPFLAGS += -fexceptions -frtti -std=c++11
APP_PLATFORM := android-10
APP_OPTIM := debug
#LIBCXX_FORCE_REBUILD := true
APP_ABI := armeabi-v7a
