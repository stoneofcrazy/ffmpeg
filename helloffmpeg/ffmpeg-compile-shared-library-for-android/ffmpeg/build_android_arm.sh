#!/bin/bash
export TMPDIR=/home/zhaowei/ffmpeg_github/ffmpeg-compile-shared-library-for-android/source/ffmpeg/ffmpegtmp
NDK=/home/zhaowei/ndk/android-ndk-r14b
SYSROOT=$NDK/platforms/android-14/arch-arm/
TOOLCHAIN=/home/zhaowei/ndk/android-ndk-r14b/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64

CPU=arm
PREFIX=/home/zhaowei/ffmpeg/ffmpegtemp
ADDI_CFLAGS="-marm"

function build_one
{
./configure \
--prefix=$PREFIX \
--enable-shared \
--disable-static \
--disable-doc \
--disable-ffmpeg \
--disable-ffplay \
--disable-ffprobe \
--disable-ffserver \
--disable-doc \
--disable-symver \
--enable-small \
--cross-prefix=$TOOLCHAIN/bin/arm-linux-androideabi- \
--target-os=linux \
--arch=arm \
--enable-cross-compile \
--sysroot=$SYSROOT \
--extra-cflags="-Os -fpic $ADDI_CFLAGS" \
--extra-ldflags="$ADDI_LDFLAGS" \
$ADDITIONAL_CONFIGURE_FLAG
make clean
make
make install
}

build_one
