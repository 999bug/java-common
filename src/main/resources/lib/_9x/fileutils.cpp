#include <jni.h>
#include "com_example_FileUtils.h" // JNI生成的头文件
#include <iostream>

JNIEXPORT void JNICALL Java_com_example_FileUtils_open(JNIEnv *env, jobject obj, jobject dccOption) {
    // 获取 DccBackupOption 对象的类
    jclass dccOptionClass = env->GetObjectClass(dccOption);

    // 获取 getJobId 方法的ID
    jmethodID getJobIdMethod = env->GetMethodID(dccOptionClass, "getJobId", "()Ljava/lang/String;");
    if (getJobIdMethod == NULL) {
        std::cerr << "Failed to find getJobId method." << std::endl;
        return;
    }

    // 调用 getJobId 方法获取作业ID
    jstring jobIdObj = (jstring)env->CallObjectMethod(dccOption, getJobIdMethod);
    const char *jobId = env->GetStringUTFChars(jobIdObj, NULL);
    std::cout << "Job ID: " << jobId << std::endl;
    env->ReleaseStringUTFChars(jobIdObj, jobId);

    // 获取 BackupSet 对象
    jmethodID getBackupSetMethod = env->GetMethodID(dccOptionClass, "getBackupSet", "()Lcom/example/BackupSet;");
    if (getBackupSetMethod == NULL) {
        std::cerr << "Failed to find getBackupSet method." << std::endl;
        return;
    }
    jobject backupSetObj = env->CallObjectMethod(dccOption, getBackupSetMethod);

    // 获取 BackupSet 对象的类
    jclass backupSetClass = env->GetObjectClass(backupSetObj);

    // 获取 setClientId 方法的ID
    jmethodID setClientIdMethod = env->GetMethodID(backupSetClass, "setClientId", "(Ljava/lang/String;)Lcom/example/BackupSet;");
    if (setClientIdMethod == NULL) {
        std::cerr << "Failed to find setClientId method." << std::endl;
        return;
    }

    // 创建一个客户端ID字符串
    jstring clientId = env->NewStringUTF("clinetUUID");

    // 调用 setClientId 方法来设置客户端ID
    env->CallObjectMethod(backupSetObj, setClientIdMethod, clientId);

    std::cout << "Client ID set successfully." << std::endl;
}
