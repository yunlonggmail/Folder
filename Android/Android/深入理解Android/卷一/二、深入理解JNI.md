# 概述

- 学习的目标：什么是JNI？Android系统中JNI的实现？
- 学习的方法：深入理解Android(卷一)，相关开发文档
- 学习的程度：能够自己大体描述JNI，并思考JNI的使用

# 一、JNI概述

JNI是Java Native Interface 的缩写。中文译为"Java本地调用"，它是一种特殊的技术，通过这种技术可以实现以下两点。

- Java程序中的函数可以调用Native语言写的函数，Native一般指的是C/C++编写的函数。
- Native程序中的函数可以调用Java层的函数，也就是说在C/C++程序中可以调用Java的函数。

## 1.1 为啥要用JNI

1. 承载Java世界的虚拟机是用Native语言写的，而虚拟机又运行在具体的平台上，所以虚拟机本身无法做到平台无关，有了JNI可以屏蔽Java调用不同平台之间的差异。
2. 早起的很多代码都是使用C或C++写的，而且非常成熟，可以直接调用它们。
3. Java和C/C++不同，它不会直接编译成平台机器码，而是编译成虚拟机可以运行的Java字节码的.class文件，通过JIT技术即时编译成本地机器码，所以效率上比不上C/C++代码，通过JNI调用C/C++代码，可以解决Java效率这个痛点。

## 1.2 JNI到底是啥

可以将JNI理解成将Native(C/C++)世界和Java世界间的天堑变通途的桥。

参考下图：2-1 Android平台中JNI示意图

<div align="center"><img src="image/2-1 Android平台中JNI的示意图.png" alt="image">
  <br>
  <br>
  <b>2-2 JNIEnv内部结构简图</b></div>

## 1.3 JNI与NDK的区别

- JNI: JNI是一套编程接口，用来实现Java代码与本地的C/C++代码进行交互

- NDK: NDK是Google开发的一套开发和编译工具集，可以生成动态链接库，主要用于Android的JNI开发。

## 1.4 JNI作用

- **扩展**：JNI扩展了JVM的能力，可以在手机上进行更好的工作
- **高效**：Native(C/C++)，效率高。C语言可以灵活操作内存。
- **复用**：相当一部分功能可以直接复用C平台上的代码，避免重复发明轮子。
- **特殊**：产品的核心技术可以采用JNI开发，不易破解;

JNI在Android中的作用：

JNI可以调用本地代码库（C/C++代码），并通过Dalvik虚拟机与应用层和应用框架层进行交互，Android中JNi代码主要位于应用层和应用框架层；

- 应用层：该层是由JNI开发，主要使用标志JNI编程模型
- 应用框架层：使用的是Android中自定义的一套JNI编程模型，该自定义的JNi编程模型弥补了标志JNI编程模型的不足;

# 二、JNI应用

这一章节主要讲解JNI的应用，另外由于Gradle升级到3.0，Android Studio升级到3.0涉及了C/C++语言的编译方式改变，这里主要讲解一下。

## 2.1 加载JNI库

由于Android是基于Linux平台的，所以Native语言写的代码最终会被编译成.so文件打包进入APK中。而Android的so文件通常是lib_.so，其中_指代的就是对应模块名称了，Android可以同时打包多个so文件。而每一个so文件就对应一个JNI库，而*也是JNI的库名。

如果Java要调用native函数，就必须通过一个位于JNI层的动态库来实现。故名思义，动态库就是运行时加载的库，而这个库通常是在类的static函数中加载，调用System.loadLibrary方法就可以。其实动态库的加载没有统一标准，只需要在对应Native方法调用之前就可以了。关于在static函数中加载是为了方便记忆和处理，防止忘记加载JNI库这种事情的发生。

**Java的native函数与总结**

在Java代码中只需要完成两项工作就可以了，这两项工作是

- 加载对应的JNI库。
- 声明由关键字native修饰的函数。

## 2.2 注册JNI函数

注册JNI函数主要是为了将JNI的函数和Java中Native函数做关联，这样系统才能知道你调用Native方法时，使用哪个对应的JNI函数。

而在注册JNI函数时有两种注册方式，分别是静态注册和动态注册，具体操作方式可以参考链接 [JNI 两种注册过程实战](https://juejin.im/entry/5885b019128fe1006c3f0149)

## 2.2.1 静态注册

当Java层调用native方法时，它会从对应的JNI库中寻找对应的JNI函数，如果没有就会报错，如果找到，则会为JNI函数和Java函数建立一个关联关系，其实就是保存JNI层函数对应的函数指针。以后在调用native方法时，直接使用这个对应的指针就可以调用对应的JNI函数了。这些操作都是有虚拟机完成的。

静态注册的重点是：根据JNI中的函数名来建立Java函数和JNI函数之间的关联关系的，而且它要求JNI层函数的名字必须遵循特定的格式，这种方式有以下几个弊端：

- 需要编译所有生命了native函数的Java类，每个所生成的class文件都得用javah生成一个头文件
- javah生成的JNI层函数名特别长，书写起来很不方便。
- 初次调用native函数时要根据函数名字搜索对应的JNI层函数来建立管理关系，这样会影响效率
- 使用这种方式创建多个JNI库时，创建的过程会更加复杂。

## 2.2.2 动态注册

动态注册的重点是：当Java层通过System.loadLibrary加载完成JNI库后，紧接着会查找该库中一个叫JNI_OnLoad的函数。如果有，就调用它，而动态注册的工作就是在这个函数中完成的。

而且动态注册避免了静态注册的弊端，具体的操作方参考上面的类

### 2.2.3 CMAKE

在Android Studio 升级到3.0以后，C/C++文件的编译方式发生了改变

**android.useDeprecatedNdk这个方法不在使用，改用了CMKE和LLDB来共同处理C/C++文件的编译**

Module "app" 下的 build.gradle

```
android {
      defaultConfig {

        ndk {
                moduleName = 'bspatch'
            }
        externalNativeBuild {
            cmake {
                cppFlags ""
                //生成多个版本的so文件
                abiFilters 'arm64-v8a','armeabi-v7a','x86','x86_64'
            }
        }
      }


      ...
      externalNativeBuild {
          cmake {
              path "CMakeLists.txt"  // 设置所要编写的c源码位置，以及编译后so文件的名字
          }
      }

}
```

贴一个自己的CMakeLists.txt

```
#CMakeLists.txt
cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

# 创建一个引用，指向文件夹下的所有文件
aux_source_directory(src/main/jni/bzip2 BZIP_2)

add_library( # Sets the name of the library.
      # 设置so文件名称.
       bspatch

       # Sets the library as a shared library.
       SHARED
       # 设置这个so文件为共享.

       # Provides a relative path to your source file(s).
       # 设置这个so文件为共享.
       src/main/jni/bspatch.c
       # 添加这个库的源文件引用，因为是文件夹引用所以可以直接使用这个引用，而不用一个个写
       ${BZIP_2})

#导入bzip2文件夹下的所有头文件
include_directories(src/main/jni/bzip2)

# 添加第二个Library
add_library(
        JNI_Test
        SHARED
        src/main/jni/JNITest.c
    )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
       log-lib

       # Specifies the name of the NDK library that
       # you want CMake to locate.
       log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
            # 制定目标库.
            bspatch

            # Links the target library to the log library
            # included in the NDK.
            ${log-lib} )
# 链接目标库，或者说将找到的库、第三方库链接到自己的库中
target_link_libraries( # Specifies the target library.
            # 制定目标库.
            JNI_Test

            # Links the target library to the log library
            # included in the NDK.
            ${log-lib} )
```

参考链接

- [Android studio 3.0 and Gradle3.0 JNI](https://blog.csdn.net/gd6321374/article/details/78996339)
- [AndroidStudio项目CMakeLists解析](https://www.cnblogs.com/chenxibobo/p/7678389.html)
- [使用CMake方式进行JNI/NDK开发](https://blog.csdn.net/yulianlin/article/details/53168350)
- [CMakeLists.txt 链接第三方库](https://blog.csdn.net/lxb00321/article/details/79797933)

## 3 JNIEnv 介绍

JNIEnv是一个与线程相关的代表JNI环境的结构体，如图[2-2]所示

<div align="center"><img src="image/2-2 JNIEnv内部结构简图.png" alt="image">
  <br>
  <br>
  <b>2-2 JNIEnv内部结构简图</b></div>

从上图可知，JNIEnv实际上就是提供了一些JNI系统函数。通过这些函数可以做到

- 调用Java函数
- 操作jobject对象等

另外的**重点**就是在**JNI中回调Java函数**

已知条件：JNIEnv跟线程相关，JNIEnv可以通过操作jobject来回调Java

解法：

1. 获取JNIEnv对象，如果当前线程有传入该对象，可以直接使用。
2. 如果当前线程没有传入该对象，可以通过JavaVM来获取该对象。而且JavaVM在每一个手机上只有一份。
3. 通过JavaVM来获取JNIEnv

  - 调用JavaVM的AttachCurrentThread函数，就可得到这个线程的JNIEnv结构体。这样就可以回调Java函数了
  - 另外，在后台线程退出前，需要调用JavaVM的DetachCurrentThread函数来释放对应的资源

具体操作步骤可以参考以下代码

```
/frameworks/base/core/jni/android/graphics/SurfaceTexture.cpp

JNIEnv* JNISurfaceTextureContext::getJNIEnv(bool* needsDetach) {
    *needsDetach = false;
    //调用AndroidRuntime的getJNIEnv方法
    JNIEnv* env = AndroidRuntime::getJNIEnv();
    if (env == NULL) {//如果加载的Env为空
        //获取参数信息
        JavaVMAttachArgs args = {
            JNI_VERSION_1_4, "JNISurfaceTextureContext", NULL };
        //获取JavaVM对象
        JavaVM* vm = AndroidRuntime::getJavaVM();
        //获取JNIEnv对象，使当前线程信息接触到env。或者说使Env接触到当前线程
        int result = vm->AttachCurrentThread(&env, (void*) &args);
        //接触成功
        if (result != JNI_OK) {
            ALOGE("thread attach failed: %#x", result);
            return NULL;
        }
        *needsDetach = true;
    }
    return env;
}

void JNISurfaceTextureContext::detachJNI() {
    //获取VM
    JavaVM* vm = AndroidRuntime::getJavaVM();
    //释放触摸操作
    int result = vm->DetachCurrentThread();
    if (result != JNI_OK) {
        ALOGE("thread detach failed: %#x", result);
    }
}
```

参考上面的代码可知，最重要的一个类就是AndroidRuntime

AndroidRuntime 一共有两个

1. /frameworks/base/include/android_runtime/AndroidRuntime.h
2. /frameworks/base/core/jni/AndroidRuntime.cpp

```
/frameworks/base/include/android_runtime/AndroidRuntime.h

/** return a pointer to the VM running in this process */
static JavaVM* getJavaVM() { return mJavaVM; }
```

```
/frameworks/base/core/jni/AndroidRuntime.cpp

/*
 * Get the JNIEnv pointer for this thread.
 *
 * Returns NULL if the slot wasn't allocated or populated.
 */
/*static*/ JNIEnv* AndroidRuntime::getJNIEnv()
{
    JNIEnv* env;
    JavaVM* vm = AndroidRuntime::getJavaVM();
    assert(vm != NULL);

    if (vm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK)
        return NULL;
    return env;
}
```

JNIEnv的结构体

针对不同版本的Android中可能有不同的JNIEnv结构体

```
/development/ndk/platforms/android-3/include/jni.h


struct _JNIEnv {
    /* do not rename this; it does not seem to be entirely opaque */
    const struct JNINativeInterface* functions;

#if defined(__cplusplus)

    jint GetVersion()
    { return functions->GetVersion(this); }

    jclass DefineClass(const char *name, jobject loader, const jbyte* buf,
        jsize bufLen)
    { return functions->DefineClass(this, name, loader, buf, bufLen); }

    jclass FindClass(const char* name)
    { return functions->FindClass(this, name); }

....
```

JavaVM的结构

针对不同版本的Android可能有不同的JavaVM结构

```
/development/ndk/platforms/android-3/include/jni.h
/*
 * C++ version.
 */
struct _JavaVM {
    const struct JNIInvokeInterface* functions;

#if defined(__cplusplus)
    jint DestroyJavaVM()
    { return functions->DestroyJavaVM(this); }
    jint AttachCurrentThread(JNIEnv** p_env, void* thr_args)
    { return functions->AttachCurrentThread(this, p_env, thr_args); }
    jint DetachCurrentThread()
    { return functions->DetachCurrentThread(this); }
    jint GetEnv(void** env, jint version)
    { return functions->GetEnv(this, env, version); }
    jint AttachCurrentThreadAsDaemon(JNIEnv** p_env, void* thr_args)
    { return functions->AttachCurrentThreadAsDaemon(this, p_env, thr_args); }
#endif /*__cplusplus*/
};
```

### 3.1 通过JNIEnv操作jobject

一个Java对象是由什么组成的？当然是它的成员变量和成员函函数，那么操作jobject的本质就应当是操作这些对象的成员变量和成员函数。

### 3.2 jfieldID 和 jmethodID介绍

成员变量和成员函数都是由类定义的，它们是类的属性，所以在JNI规则中，用jfieldID和jmethodID来表示Java类的成员变量和成员函数，可以通过下面两个函数得到：

```
/development/ndk/platforms/android-3/include/jni.h
struct _JNIEnv

jmethodID GetMethodID(jclass clazz, const char* name, const char* sig)
    { return functions->GetMethodID(this, clazz, name, sig); }

jfieldID GetFieldID(jclass clazz, const char* name, const char* sig)
    { return functions->GetFieldID(this, clazz, name, sig); }
```

其中jclass代表Java类，name表示成员函数和成员变量的名字，sig为这个函数和变量的签名信息。签名信息参考下面的信息。

运行效率知识点

**如果每次操作jobject前都去查询jmethodID或jfieldID，那么将会影响程序运行的效率，所以在初始化的时候可以取出一些ID并保存起来供后续使用是一个较好的方案。**

### 3.3 使用jfieldID和jmethodID

使用jmethodID，通过以下方法

```
NativeType Call<type>MethodV(jobject obj, jmethodID methodID, va_list args)
    { functions->Call<type>MethodV(this, obj, methodID, args); }
    //functions 则是JNINativeInterface对象
    //type 对应JNI中参数的类型。
    //this 指代JNIEnv这个对象
```

使用jfieldID，通过以下方法

```
NativeType Get<type>Field(jobject obj, jfieldID fieldID)
    { return functions->Get<type>Field(this, obj, fieldID); }

void Set<type>Field(jobject obj, jfieldID fieldID, NativeType value)
       { functions->Set<type>Field(this, obj, fieldID, value); }
```

```
struct JNINativeInterface {

NativeType     (*Call<type>Method)(JNIEnv*, jobject, jmethodID, ...);

NativeType   (*Get<type>Field)(JNIEnv*, jclass, jfieldID);

NativeType   (*Set<type>Field)(JNIEnv*, jclass, jfieldID, jobject);
}
```

### 3.4 jstring介绍

Java中的String也是引用类型，不过由于它的使用频率较高，所以在JNI规范中单独创建了一个jstring类型来表示Java中的String类型。虽然jstring是一种独立的数据类型，但是它并没有提供成员函数以便操作。

所以JNIEnv提供了一些函数来操作jstring，具体如下

- 调用JNIEnv的NewString函数，可以从Native的字符串得到一个jstring对象。其实可以把一个jstring对象看成是Java中String对象在JNI层的代表，也就是说，jstring就是一个Java String。但是由于Java String存储的是Unicode字符，所以NewString函数的参数也必须是Unicode字符串
- 调用JNIEnv的NewStringUTF将根据Native的一个UTF-8字符串得到一个jstring对象。在实际工作中这个函数用的最多。
- 上面两个函数将本地字符串转换成了Java的String对象，JNIEnv还提供了GetStringChars函数和GetStringUTFChars函数，它们可以将Java String对象转换成本地字符串。其中GetStringChars得到一个Unicode字符串，而GetStringUTFChars得到一个UTF-8字符串。
- 如果代码中调用了上面的几个函数，在做完相关工作后，就需要调用ReleaseStringChars函数或ReleaseStringUTFChars函数来对应的释放资源，否则会导致JVM内存泄露。

```
struct _JNIEnv
    /* do not rename this; it does not seem to be entirely opaque */
    const struct JNINativeInterface* functions;

jstring NewString(const jchar* unicodeChars, jsize len)
{ return functions->NewString(this, unicodeChars, len); }
const jchar* GetStringChars(jstring string, jboolean* isCopy)
{ return functions->GetStringChars(this, string, isCopy); }

void ReleaseStringChars(jstring string, const jchar* chars)
{ functions->ReleaseStringChars(this, string, chars); }

jstring NewStringUTF(const char* bytes)
{ return functions->NewStringUTF(this, bytes); }
const char* GetStringUTFChars(jstring string, jboolean* isCopy)
{ return functions->GetStringUTFChars(this, string, isCopy); }
void ReleaseStringUTFChars(jstring string, const char* utf)
{ functions->ReleaseStringUTFChars(this, string, utf); }
```

```
struct JNINativeInterface

jstring     (*NewString)(JNIEnv*, const jchar*, jsize);
jsize       (*GetStringLength)(JNIEnv*, jstring);
const jchar* (*GetStringChars)(JNIEnv*, jstring, jboolean*);
void        (*ReleaseStringChars)(JNIEnv*, jstring, const jchar*);
jstring     (*NewStringUTF)(JNIEnv*, const char*);
jsize       (*GetStringUTFLength)(JNIEnv*, jstring);
/* JNI spec says this returns const jbyte*, but that's inconsistent */
const char* (*GetStringUTFChars)(JNIEnv*, jstring, jboolean*);
void        (*ReleaseStringUTFChars)(JNIEnv*, jstring, const char*);
```

## 4 JNI签名介绍

JNI 签名信息指的是由参数类型和返回值类型共同组成。不过为什么需要这个签名信息呢？

**这个问题的答案比较简单。因为Java支持函数重载，也就是说，可以定义同名但不同参数的函数。但仅仅根据函数名是没法找到具体函数的。为了解决这个问题，JNI技术中就将参数类型和返回值类型的组合作为了一个函数的签名信息，有了签名信息和函数名，就能很顺利地找到Java中的函数了**

JNI规范定义的函数签名信息格式如下：

{参数1类型表示参数2类型表示......参数n类型表示}返回值类型表示

参见的类型标识参考下表

类型标识                 | Java类型
-------------------- | --------
Z                    | boolean
B                    | byte
C                    | char
S                    | short
I                    | int
J                    | long
F                    | float
D                    | double
L/java/lang/string;  | String
[I                   | int[]
[L/java/lang/object; | object[]

## 5 垃圾回收

在JNI中使用普通的赋值操作，并不会增加引用计数，而如果JNI中的对象指向了Java中的对象，那么Java中的对象被回收后，JNI中的引用会成为一个野指针，无指向的指针，这样就会导致很严重的后果。

JNI技术提供了三种类型的引用来处理涉及到的这类问题。它们分别是：

- Local Reference：本地引用。在JNI层函数中使用的费全局引用对象都是Local Reference，它包括函数调用时传入的jobject和在JNI层函数中创建的jobject。Local Reference 最大的特点就是，一旦JNI层函数返回，这些object就可能被垃圾回收。
- Global Reference：全局引用，这种对象如不主动释放，它永远不会被垃圾回收。
- Weak Global Reference：弱全局引用，一种特殊的Global Reference，在运行过程中可能会被垃圾回收。所以在使用它之前，需要调用JNIEnv的IsSameObject判断它是否被回收了。

<font color="red">
  <b>没有及时回收Local Reference或许是进程占用内存过多的一个原因</b>
</font>

```
jboolean IsSameObject(jobject ref1, jobject ref2)
    { return functions->IsSameObject(this, ref1, ref2); }
```

```
 jboolean    (*IsSameObject)(JNIEnv*, jobject, jobject);
```

## 6 异常处理

JNI函数可以在代码中捕获和修改这些异常，JNIEnv 提供了三个函数给予帮助：

- ExceptionOccured 函数，用来判断是否发生异常
- ExceptionClear 函数，用来清理当前JNI层中发生的异常。
- ThrowNew 函数，用来想Java层抛出异常。

```
jthrowable ExceptionOccurred() { return functions->ExceptionOccurred(this); }
void ExceptionClear() { functions->ExceptionClear(this); }
jint ThrowNew(jclass clazz, const char* message) { return functions->ThrowNew(this, clazz, message); }
```

```
jthrowable (_ExceptionOccurred)(JNIEnv_);
void (_ExceptionClear)(JNIEnv_);
jint        (*ThrowNew)(JNIEnv *, jclass, const char *);
```

## 7 本章小结

- JNI函数的注册方法。
- Java和JNI层数据类型的转换
- JNIEnv和jstring的使用方法，以及JNI中的类型签名。
- 垃圾回收在JNI层中的使用，以及异常处理方面的知识

[Android JNI Tips](https://developer.android.com/training/articles/perf-jni)
