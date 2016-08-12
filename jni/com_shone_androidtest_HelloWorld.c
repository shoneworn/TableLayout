#include <jni.h>
#define LOG_TAG "HelloWorld"
#include <utils/Log.h>
/* Native interface, it will be call in java code */
JNIEXPORT jstring JNICALL Java_com_shone_androidtest_HelloWorld_printJNI(JNIEnv *env, jobject obj,jstring inputStr)
{
����LOGI("dufresne Hello World From libhelloworld.so!");
����// �� instring �ַ���ȡ��ָ���ַ��� UTF �����ָ��
����const char *str =
����(const char *)(*env)->GetStringUTFChars( env,inputStr, JNI_FALSE );
����LOGI("dufresne--->%s",(const char *)str);
����// ֪ͨ��������ش��벻����Ҫͨ�� str ���� Java �ַ�����
����(*env)->ReleaseStringUTFChars(env, inputStr, (const char *)str );
����return (*env)->NewStringUTF(env, "Hello World! I am Native interface");
}

/* This function will be call when the library first be load.
* You can do some init in the libray. return which version jni it support.
*/
jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
����void *venv;
����LOGI("dufresne----->JNI_OnLoad!");
����if ((*vm)->GetEnv(vm, (void**)&venv, JNI_VERSION_1_4) != JNI_OK) {
��������LOGE("dufresne--->ERROR: GetEnv failed");
��������return -1;
����}
����return JNI_VERSION_1_4;
}