//
// Created by shiyunlong on 15/12/18.
//
#include <stdio.h>
#include <jni.h>
#include <string.h>
#include <android/log.h>

//导入日志头文件

#define  LOG_TAG    "jniInAndroid"
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

jstring
Java_cn_yuguo_mydoctor_activity_TestJNIActivity_getStringFromNative(JNIEnv *env,
                                                                    jobject thiz) {
    LOGE("JNIYUGUO_____%s", "20");
    return (*env)->NewStringUTF(env, "what the fuck");
}


jstring
Java_cn_yuguo_mydoctor_activity_TestJNIActivity_getStringFromNativeWithString(JNIEnv *env,
                                                                              jobject thiz,
                                                                              jstring jstr) {

    const char *str = (*env)->GetStringUTFChars(env, jstr, 0);
    LOGE("JNIYUGUO_____%s", "28");
    const char *demo = (*env)->GetStringUTFChars(env, jstr, 0);
    LOGE("JNIYUGUO_____%s", "30");
    char *result = malloc(strlen(str) + strlen(demo) + 1);
    LOGE("JNIYUGUO_____%s", "32");
    strcpy(result, str);
    LOGE("JNIYUGUO_____%s", "34");
    strcat(result, demo);
    LOGE("JNIYUGUO_____%s", "36");
    (*env)->ReleaseStringUTFChars(env, jstr, str);
    LOGE("JNIYUGUO_____%s", "38");
    (*env)->ReleaseStringUTFChars(env, jstr, demo);
    LOGE("JNIYUGUO_____%s", "40");
    return (*env)->NewStringUTF(env, result);
}
