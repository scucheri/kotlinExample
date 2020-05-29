#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_bytedance_myapplication_MainActivity_stringFromJNI(
        JNIEnv *env) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_bytedance_myapplication_MainActivity_testNativeCrash(
        JNIEnv *env) {
std::string hello = "shushanyouluqinweijing";
std::int64_t a = 1/0;
abort();
return env->NewStringUTF(hello.c_str());
}

