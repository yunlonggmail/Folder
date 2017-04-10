# 1.关于多渠道打包

## 1.1 输出对应渠道包

```
android{
    ...
    buildTypes{
        ...
        release{
            ...
            applicationVariants.all { variant ->
            variant.outputs.each { output ->
                def outputFile = output.outputFile
                if (outputFile != null && outputFile.name.endsWith('.apk')) {
                    // 输出apk名称为yuguo_v1.0_wandoujia.apk
                    def fileName = "yuguo_v${defaultConfig.versionName}_${variant.productFlavors[0].name}.apk"
                    output.outputFile = new File(outputFile.parent, fileName)
                    }
                }
            }
        }
    }
}
```

## 1.2配置渠道包信息

其中可以自定义变量，根据变量值进行打包。

其中 manifestPlaceholders表示对应AndroidManifest.xml文件中使用的变量。

在AndroidManifest.xml中使用${channel_value}，来表示对应包的对应值

```
// 友盟多渠道打包，渠道flavors
```

```
    productFlavors {
        boolean isProductionEnvironment = true;
        _91baidu {
            manifestPlaceholders = [channel_value: "91baidu", easemob_appkey_value: isProductionEnvironment ? "yuguo#yuguo-prod" : "yuguo#yuguo-dev"]
        }
     }

    productFlavors.all { flavor ->
        flavor.manifestPlaceholders = [channel_value: name, easemob_appkey_value: name]
    }
```

# 2、编译问题：`android.dexOptions.incremental` property

## 问题描述

```
Warning:The `android.dexOptions.incremental` property is deprecated and it has no effect on the build process.
```

## 问题描述

build gradle及Android Studio升级后不再需要配置参数

## 解决方式

删除build.gradle中部分代码

```
dexOptions {
        incremental true
        jumboMode = true
        javaMaxHeapSize "4g"
        preDexLibraries = false
}
```

## 参考

1、[Android Studio V2.0问题](http://blog.csdn.net/lablenet/article/details/52606480)

# 3、编译问题：com.android.ide.common.process.ProcessException:Failed to execute aapt

## 问题描述

```
com.android.ide.common.process.ProcessException:Failed to execute aapt
```

## 问题说明：

Android assert packaging tool出现问题，无法正常编译，资源无法正常打包

## 解决方式

如下图所示，更新Android SDK Build Tools并勾选

![img](/img/sdk-tools.png)
