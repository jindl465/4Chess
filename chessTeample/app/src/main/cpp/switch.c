//
// Created by NIMNIM on 2018-12-13.
//

#include <jni.h>
#include <asm/fcntl.h>
#include <fcntl.h>
#include <bits/ioctl.h>
#include <sys/ioctl.h>
#include <zconf.h>
#include <android/log.h>
#include <memory.h>
#include <errno.h>
#include <malloc.h>

static unsigned char fontmap_cheonji_eng[9][4] = {
        {'a', 'b', 'c', '\0'}, {'d', 'e', 'f', '\0'}, {'h', 'i', 'j', '\0'},

        {'k', 'l', 'm', 'n'}, {'o', 'p', 'q', 'r'}, {'s', 't', 'u', 'v'},

        {'w', 'x', 'y', 'z'}, {'\0', '\0', '\0', '\0'}, {'\0', '\0', '\0', '\0'}
                                        //shift			enter
};

char* decoder(int* storage, int size);
int keydata_to_index(int key_data);

JNIEXPORT jstring JNICALL
Java_com_cnlab_caucse_chessteample_GameActivity_getKeyboard(JNIEnv *jenv, jobject self) {

    int dev;
    int end_loop = 0;
    int key_idx, key_data = 0;
    int in;
    int idx_storage[100];
    int idx_storage_size = 0;

    int check_enter = 0;
    char* buf;

    while (!end_loop){
        if ((dev = open("/dev/switch", O_RDONLY)) < 0) {
            //__android_log_print(ANDROID_LOG_ERROR, "SSegment", "Failed to open /dev/7segment\n");
            return (*jenv)->NewStringUTF(jenv, strerror(errno));
        }
        read (dev, &key_data, sizeof(key_data));
        close(dev);
        key_idx = keydata_to_index(key_data);

        if (key_idx == 8)
            check_enter++;
        else
            check_enter = 0;
        if (check_enter == 3)
            end_loop = 1;

        idx_storage[idx_storage_size] = key_idx;
        idx_storage_size++;

    }

    idx_storage_size--;
    buf = decoder(idx_storage, idx_storage_size);

    return (*jenv)->NewStringUTF(jenv, buf);

}

char* decoder(int* storage, int size){
    int i;
    int shift_flag = 0;

    char* string;
    string = (char*)malloc(sizeof(char) * 100);
    int string_size = 0;

    int duplicate = 0;

    char character;

    for (i = 0; i < size - 1; ++i){

        if (storage[i] == storage[i + 1]){
            if (duplicate == 3)
                duplicate = 0;
            else
                duplicate++;
        }

        switch (storage[i]){
            case 7:
                if (shift_flag == 0)
                    shift_flag = 1;
                else
                    shift_flag = 0;
                break;
            case 8:
                if (duplicate == 1){
                    string[string_size] = ' ';
                    string_size++;
                    duplicate = 0;
                }

                break;
            default:
                character = fontmap_cheonji_eng[storage[i]][duplicate];
                if (character == '\0')
                    duplicate = 0;
                if (storage[i] != storage[i + 1]){

                    if (shift_flag == 1){}
                        //string[string_size] = character - 32;
                    else
                        string[string_size] = character;
                    duplicate = 0;
                    string_size++;
                }
                break;
        }
    }
    string[string_size] = '\0';
    return string;
}

int keydata_to_index(int key_data){
    int idx, pow = 1;

    for (idx = 0; key_data / pow != 1; ++idx){
        pow *= 10;
    }

    return 8 - idx;
}