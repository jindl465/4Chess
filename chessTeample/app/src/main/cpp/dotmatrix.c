//
// Created by NIMNIM on 2018-12-13.
//


#include <zconf.h>
#include <jni.h>
#include <android/log.h>
#include <fcntl.h>

JNIEXPORT jint JNICALL
Java_com_cnlab_caucse_chessteample_GameActivity_DotmatrixWrite(JNIEnv *jenv, jobject self, jint data)
{
    int dev, num;

    if ((dev = open("/dev/chess", O_WRONLY | O_SYNC)) < 0){
        //__android_log_print(ANDROID_LOG_ERROR, "D", "Failed to open /dev/7segment\n");
        return -1;
    }

    num = data;
    write(dev, &num, sizeof(num));

    close(dev);

    return 0;
}
