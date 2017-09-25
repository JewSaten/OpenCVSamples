//#include "io_jewsaten_opencvsamples_OpenCVUtil.h"
#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <opencv2/opencv.hpp>

#define CLASS_PATH_NAME "io/jewsaten/opencvsamples/OpenCVUtil"
#define CLASS io_jewsaten_opencvsamples_OpenCVUtil
#define JNI_FUNC(FUN) Java_##CLASS##_##FUN

using namespace cv;

extern "C" {

//静态注册
//JNIEXPORT jintArray JNICALL Java_io_jewsaten_opencvsamples_OpenCVUtil_gray
//(JNIEnv *env, jclass clazz, jintArray buf, jint w, jint h){
//
//    jint *cbuf;
//    cbuf = env->GetIntArrayElements(buf, JNI_FALSE);
//    if (cbuf == NULL) {
//        return 0;
//    }
//
//    Mat imgData(h, w, CV_8UC4, (unsigned char *) cbuf);
//
//    uchar* ptr = imgData.ptr(0);
//    for(int i = 0; i < w*h; i ++){
//        int grayScale = (int)(ptr[4*i+2]*0.299 + ptr[4*i+1]*0.587 + ptr[4*i+0]*0.114);
//        ptr[4*i+1] = grayScale;
//        ptr[4*i+2] = grayScale;
//        ptr[4*i+0] = grayScale;
//    }
//
//    int size = w * h;
//    jintArray result = env->NewIntArray(size);
//    env->SetIntArrayRegion(result, 0, size, cbuf);
//    env->ReleaseIntArrayElements(buf, cbuf, 0);
//    return result;
//
//}

//动态注册
JNIEXPORT jintArray JNICALL gray(JNIEnv *env, jclass clazz, jintArray buf, jint w, jint h){

    jint *cbuf;
    cbuf = env->GetIntArrayElements(buf, JNI_FALSE);
    if (cbuf == NULL) {
        return 0;
    }

    Mat imgData(h, w, CV_8UC4, (unsigned char *) cbuf);

    uchar* ptr = imgData.ptr(0);
    for(int i = 0; i < w*h; i ++){
        int grayScale = (int)(ptr[4*i+2]*0.299 + ptr[4*i+1]*0.587 + ptr[4*i+0]*0.114);
        ptr[4*i+1] = grayScale;
        ptr[4*i+2] = grayScale;
        ptr[4*i+0] = grayScale;
    }

    int size = w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, cbuf);
    env->ReleaseIntArrayElements(buf, cbuf, 0);
    return result;

}

static JNINativeMethod gMethods[] = {
		{"gray", "([III)[I", (void*)gray},
};

static int registerNativeMethods(JNIEnv* env, const char* className,
		JNINativeMethod* gMethods, int numMethods) {
	jclass clazz;
	clazz = env->FindClass(className);
	if (clazz == NULL) {
		return JNI_FALSE;
	}
	if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
		env->DeleteLocalRef(clazz);
		return JNI_FALSE;
	}
	env->DeleteLocalRef(clazz);
	return JNI_TRUE;
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {
	JNIEnv *env;
	if (vm->GetEnv((void **) &env, JNI_VERSION_1_6)) {
		return JNI_ERR;
	}
	if (registerNativeMethods(env, CLASS_PATH_NAME, gMethods,
			sizeof(gMethods) / sizeof(gMethods[0])) != JNI_TRUE) {
		return JNI_ERR;
	}
	return JNI_VERSION_1_6;
}

JNIEXPORT void JNICALL JNI_OnUnload(JavaVM* vm, void* reserved) {
	JNIEnv *env;
	jclass cls;
	if (vm->GetEnv((void **) &env, JNI_VERSION_1_6)) {
		return;
	}
	cls = env->FindClass(CLASS_PATH_NAME);
	env->UnregisterNatives(cls);
	return;
}

}
