#include <jni.h>
#include <string>
#include <stdio.h>
#include "android/log.h"

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,"chao.qin" ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) //


void mprint();

JNIEXPORT jint JNICALL
Java_com_tool_chao_numberic_MainActivity_toInteger(JNIEnv *env, jobject instance,
                                                   jbyteArray bytes_) {
    jbyte *bytes = env->GetByteArrayElements(bytes_, NULL);
    const u_int8_t b = (const u_int8_t) *bytes;

    mprint();
    // TODO

    env->ReleaseByteArrayElements(bytes_, bytes, 0);
    return b;
}

void mprint() {

    LOGD("byte: %d",byte);
}

extern "C"
jstring
Java_com_tool_chao_numberic_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    mprint();
    return env->NewStringUTF(hello.c_str());
}



