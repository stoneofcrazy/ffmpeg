cmake_minimum_required(VERSION 3.4.1)

find_library( log-lib
              log )

#动态加载
#设置so文件的根路径
set(distribution_DIR ${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp)
add_library(libavcodec-56 SHARED IMPORTED)
set_target_properties(libavcodec-56  PROPERTIES IMPORTED_LOCATION
						${distribution_DIR}/${ANDROID_ABI}/libavcodec-56.so)

add_library(libavdevice-56 SHARED IMPORTED)
set_target_properties(libavdevice-56  PROPERTIES IMPORTED_LOCATION
						${distribution_DIR}/${ANDROID_ABI}/libavdevice-56.so)

add_library(libavfilter-5 SHARED IMPORTED)
set_target_properties(libavfilter-5  PROPERTIES IMPORTED_LOCATION
						${distribution_DIR}/${ANDROID_ABI}/libavfilter-5.so)

add_library(libavformat-56 SHARED IMPORTED)
set_target_properties(libavformat-56  PROPERTIES IMPORTED_LOCATION
						${distribution_DIR}/${ANDROID_ABI}/libavformat-56.so)

add_library(libavutil-54 SHARED IMPORTED)
set_target_properties(libavutil-54  PROPERTIES IMPORTED_LOCATION
						${distribution_DIR}/${ANDROID_ABI}/libavutil-54.so)

add_library(libswresample-1 SHARED IMPORTED)
set_target_properties(libswresample-1  PROPERTIES IMPORTED_LOCATION
						${distribution_DIR}/${ANDROID_ABI}/libswresample-1.so)

add_library(libswscale-3 SHARED IMPORTED)
set_target_properties(libswscale-3  PROPERTIES IMPORTED_LOCATION
						${distribution_DIR}/${ANDROID_ABI}/libswscale-3.so)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")
include_directories(src/main/cpp/include)
add_library( native-lib
             SHARED
             src/main/cpp/native-lib.cpp )
target_link_libraries( native-lib
                       ${log-lib} libavcodec-56 libavdevice-56 libavfilter-5 libavformat-56 libavutil-54 libswresample-1 libswscale-3 )
