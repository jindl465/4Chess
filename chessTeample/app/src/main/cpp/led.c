//
// Created by NIMNIM on 2018-12-13.
//

#include <jni.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <android/log.h>
#include <string.h>
#include <errno.h>

#define LED_MAGIC   0xBD
#define LED_OFF     _IOW(LED_MAGIC, 0, unsigned char)
#define LED_ON      _IOW(LED_MAGIC, 1, unsigned char)

JNIEXPORT jint JNICALL
Java_com_cnlab_caucse_chessteample_GameActivity_ledWrite(JNIEnv *jenv, jobject self, jint data) {
    int dev;
    unsigned char buf;

    if ((dev = open("/dev/led", O_WRONLY | O_SYNC)) < 0) {
        //__android_log_print(ANDROID_LOG_ERROR, "LED", "failed to open /dev/led: %s\n", strerror(errno));
        return 1;
    }

    buf = (unsigned char) data;
    write(dev, &buf, sizeof(buf));
    close(dev);

    return 0;
}

JNIEXPORT jint JNICALL
Java_com_cnlab_caucse_chessteample_GameActivity_ledOff(JNIEnv *jenv, jobject self, jint num) {
    int dev;
    unsigned char buf;

    if (num < 0 || num >= 8) {
        //__android_log_print(ANDROID_LOG_ERROR, "LED", "invalid led number\n");
        return 1;
    }
    if ((dev = open("/dev/led", O_WRONLY | O_SYNC)) < 0) {
        //__android_log_print(ANDROID_LOG_ERROR, "LED", "failed to open /dev/led: %s\n", strerror(errno));
        return 1;
    }

    buf = (unsigned char) num;
    ioctl(dev, LED_OFF, &buf, _IOC_SIZE(LED_OFF));
    close(dev);

    return 0;
}

JNIEXPORT jint JNICALL
Java_com_cnlab_caucse_chessteample_GameActivity_ledOn(JNIEnv *jenv, jobject self, jint num) {
    int dev;
    unsigned char buf;

    if (num < 0 || num >= 8) {
        //__android_log_print(ANDROID_LOG_ERROR, "LED", "invalid led number\n");
        return 1;
    }
    if ((dev = open("/dev/led", O_WRONLY | O_SYNC)) < 0) {
        //__android_log_print(ANDROID_LOG_ERROR, "LED", "failed to open /dev/led: %s\n", strerror(errno));
        return 1;
    }

    buf = (unsigned char) num;
    ioctl(dev, LED_ON, &buf, _IOC_SIZE(LED_ON));
    close(dev);

    return 0;
}
