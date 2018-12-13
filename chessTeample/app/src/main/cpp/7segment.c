//
// Created by NIMNIM on 2018-12-13.
//

#include <jni.h>
#include <fcntl.h>
#include <android/log.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <stdlib.h>

JNIEXPORT jint JNICALL
Java_com_cnlab_caucse_chessteample_GameActivity_SSegmentWrite(JNIEnv *jenv, jobject self, jint data)
{
    int dev, num, idx;

    if ((dev = open("/dev/7segment", O_WRONLY | O_SYNC)) < 0){
        __android_log_print(ANDROID_LOG_ERROR, "SSegment", "Failed to open /dev/7segment\n");
        return -1;
    }

    write(dev, &data, sizeof(data));

    close(dev);

    return 0;
}