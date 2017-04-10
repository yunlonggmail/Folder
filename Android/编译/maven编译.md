# 一、Maven编译错误：No service of type Factory

## 问题描述

```
Error:No service of type Factory<LoggingManagerInternal> available in ProjectScopeServices.
```

## 问题说明

Android Studio 升级版本后clean工程出错，主要原因是Maven版本过低。

## 解决方式

升级到相应新版本即可。

## 参考

1、[简书文字](http://www.jianshu.com/p/c4f4894ad215)

2、[Google Issue 219692](https://code.google.com/p/android/issues/detail?id=219692)
