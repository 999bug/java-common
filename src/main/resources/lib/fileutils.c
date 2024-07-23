#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <jni.h>

JNIEXPORT jint JNICALL Java_com_ncst_jni_FileUtils_open(JNIEnv *env, jobject obj, jstring filePath) {
    const char *path = (*env)->GetStringUTFChars(env, filePath, NULL);
    FILE *file = fopen(path, "ab"); // 以追加模式打开文件
    if (!file) {
        printf("Failed to open file for writing.\n");
        return -1;
    }
    return fileno(file);
}

JNIEXPORT jbyteArray JNICALL Java_com_ncst_jni_FileUtils_readFile(JNIEnv *env, jobject obj, jint fd) {
    FILE *file = fdopen(fd, "rb");
    if (!file) {
        printf("Failed to open file for reading.\n");
        return NULL;
    }

    fseek(file, 0, SEEK_END);
    long fileSize = ftell(file);
    rewind(file);

    jbyteArray byteArray = (*env)->NewByteArray(env, fileSize);
    if (!byteArray) {
        fclose(file);
        return NULL;
    }

    jbyte *buffer = (*env)->GetByteArrayElements(env, byteArray, NULL);
    fread(buffer, fileSize, 1, file);
    (*env)->ReleaseByteArrayElements(env, byteArray, buffer, 0);

    fclose(file);
    return byteArray;
}

JNIEXPORT void JNICALL Java_com_ncst_jni_FileUtils_write(JNIEnv *env, jobject obj, jint fd, jbyteArray data, jint off, jint len) {
    FILE *file = fdopen(fd, "ab"); // 以追加模式打开文件
    if (!file) {
        printf("Failed to open file for writing.\n");
        return;
    }

    jbyte *buffer = (*env)->GetByteArrayElements(env, data, NULL);
    fwrite(buffer + off, len, 1, file); // 写入指定偏移和长度的数据
    (*env)->ReleaseByteArrayElements(env, data, buffer, 0);

    fclose(file);
}

JNIEXPORT void JNICALL Java_com_ncst_jni_FileUtils_close(JNIEnv *env, jobject obj, jint fd) {
    close(fd); // 关闭文件描述符
}
