# <declare-styleable> FontFamilyFont</declare-styleable>

Ionic ERROR: In

<declare-styleable> FontFamilyFont, unable to find attribute android:fontVariationSettings [duplicate]</declare-styleable>

解决方式，

1. 将support：v4包，升级到com.android.support:support-v4:27.1.0
2. 在app/build.gradle中加入以下代码,在gradle的最后

```
configurations.all {
    resolutionStrategy {
        force 'com.android.support:support-v4:27.1.0'
    }
}
```

## Multiple dex files

define Landroid/support/design/widget/CoordinatorLayout$Behavior;

解决方式，将 所有的android导入包修改为统一版本

参考[链接](https://stackoverflow.com/questions/49028119/multiple-dex-files-define-landroid-support-design-widget-coordinatorlayoutlayou)

```
Update the library versions to 27.1.0 solve the isssue for me.

compile 'com.android.support:appcompat-v7:26.1.0'
compile 'com.android.support:design:26.1.0'
compile 'com.android.support:appcompat-v7:26.1.0'
compile 'com.android.support:mediarouter-v7:26.1.0'
compile 'com.android.support:recyclerview-v7:26.1.0'
compile 'com.android.support:cardview-v7:26.1.0'
compile 'com.android.support:support-v13:26.1.0'
compile 'com.android.support:support-v4:26.1.0'
To:

compile 'com.android.support:appcompat-v7:27.1.0'
compile 'com.android.support:design:27.1.0'
compile 'com.android.support:appcompat-v7:27.1.0'
compile 'com.android.support:mediarouter-v7:27.1.0'
compile 'com.android.support:recyclerview-v7:27.1.0'
compile 'com.android.support:cardview-v7:27.1.0'
compile 'com.android.support:support-v13:27.1.0'
compile 'com.android.support:support-v4:27.1.0'
```

## 安装包已损坏

使用Android Studio 右侧(可能在其他位置)Gradle窗口中

'app应用名'->':app'->'install'->'install**'

解决方式[链接](https://blog.csdn.net/qq_30552993/article/details/79786139)

## 3.0相关信息问题

<https://www.jianshu.com/p/02a62574d9a1>
