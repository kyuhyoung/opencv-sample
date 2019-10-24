cmake_minimum_required(VERSION 3.4.1)

#set(pathOPENCV D:/OpenCV-android-sdk/sdk)
#set(pathLIBOPENCV_JAVA ${pathOPENCV}/native/libs/${ANDROID_ABI}/libopencv_java4.so)
set(pathLIBOPENCV_JAVA ../../../../src/main/cpp/JniLibs/${ANDROID_ABI}/libopencv_java4.so)
#set(pathLIBOPENCV_JAVA src/main/jniLibs/${ANDROID_ABI}/libopencv_java4.so)

#set(CMAKE_VERBOSE_MAKEFILE on)
#set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

add_library( native-lib SHARED src/main/cpp/native-lib.cpp )

find_library( jnigraphics-lib jnigraphics )

# Configure OpenCV
#include_directories(SYSTEM $ENV{VENDOR}/opencv/include )
#include_directories(${pathOPENCV}/native/jni/include)
include_directories(src/main/cpp/JniInclude)


add_library( lib_opencv SHARED IMPORTED )
set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ${pathLIBOPENCV_JAVA})


#add_library( cv_core-lib SHARED IMPORTED)
#set_target_properties( cv_core-lib
#        PROPERTIES IMPORTED_LOCATION
#        $ENV{VENDOR}/opencv/lib/${ANDROID_ABI}/libopencv_core.so )

#add_library( cv_imgproc-lib SHARED IMPORTED)
#set_target_properties( cv_imgproc-lib
#        PROPERTIES IMPORTED_LOCATION
#        $ENV{VENDOR}/opencv/lib/${ANDROID_ABI}/libopencv_imgproc.so )

#add_library( cv_imgcodecs-lib SHARED IMPORTED)
#set_target_properties( cv_imgcodecs-lib
#        PROPERTIES IMPORTED_LOCATION
#        $ENV{VENDOR}/opencv/lib/${ANDROID_ABI}/libopencv_imgcodecs.so )

#target_link_libraries( native-lib ${jnigraphics-lib} cv_core-lib cv_imgproc-lib cv_imgcodecs-lib)
target_link_libraries( native-lib ${jnigraphics-lib} lib_opencv)