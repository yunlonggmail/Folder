# 1.Local path doesn't exist.

这种情况产生的主要原因是缺失安装的apk文件。

产生这种现象原因：

1. build.gradle中对version进行升级，未编译。
2. 手动删除了对应的apk文件夹。

解决方法【针对原因1、2】：

1. Build->Rebuild Project，然后再次运行。如果仍然有问题，执行2.
2. Build->Clean Project，然后再次运行。如果仍然有问题，执行3.
3. 删除app下build文件夹，然后执行2。如果还有问题就Google一下。


# 2. Android 查询项目中所有文件的中文

使用下方的正则表达式。

^((?!(\*|//)).)+[\u4e00-\u9fa5]

# 3. Android Studio清除未使用到的资源

