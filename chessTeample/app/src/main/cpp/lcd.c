//
// Created by NIMNIM on 2018-12-13.
//

#include <zconf.h>
#include <jni.h>
#include <android/log.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <memory.h>

#define LCD_MAGIC		0xBC
#define LCD_SET_CURSOR_POS	_IOW(LCD_MAGIC, 0, int)
#define LCD_CLEAN		_IO(LCD_MAGIC, 1)

JNIEXPORT jint JNICALL
Java_com_cnlab_caucse_chessteample_GameActivity_LcdWrite(JNIEnv *jenv, jobject self, jstring argv1, jstring argv2)
{
    int dev, num, pos, i;

    if ((dev = open("/dev/lcd", O_WRONLY | O_SYNC)) < 0){
        //__android_log_print(ANDROID_LOG_ERROR, "SSegment", "Failed to open /dev/7segment\n");
        return -1;
    }

    char* buf1 = (*jenv)->GetStringUTFChars(jenv, argv1, 0);
    char* buf2 = (*jenv)->GetStringUTFChars(jenv, argv2, 0);


    pos = 0;
    ioctl(dev, LCD_CLEAN, &pos, _IOC_SIZE(LCD_CLEAN));
    pos = 0;
    ioctl(dev, LCD_SET_CURSOR_POS, &pos, _IOC_SIZE(LCD_SET_CURSOR_POS));
    write(dev, buf1 , strlen(buf1));
    pos = 16;
    ioctl(dev, LCD_CLEAN, &pos, _IOC_SIZE(LCD_CLEAN));
    pos = 16;
    ioctl(dev, LCD_SET_CURSOR_POS, &pos, _IOC_SIZE(LCD_SET_CURSOR_POS));
    write(dev, buf2, strlen(buf2));
    close(dev);

    (*jenv)->ReleaseStringUTFChars(jenv, argv1, buf1);
    (*jenv)->ReleaseStringUTFChars(jenv, argv2, buf2);

    return 0;
}
