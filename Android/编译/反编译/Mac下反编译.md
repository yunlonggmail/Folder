#1、下载apktool dex2jar jd-gui

文件路径：

[apktool](反编译文件/apktool)

[dex2jar](反编译文件/dex2jar-0.0.9.8)

[jd-gui](反编译文件/jd-gui.dmg)

#2、安装apktool

打开Finder窗口，前往->文件夹->输入“/user/local/bin”，快捷键(command+shift+g)

然后将apktool文件夹下的文件复制过来。三个文件分别是：aapt、apktool、apktool.jar

这样就配置好路径了。

最后打开终端输入 apktool，出现如下代码即可。

	COMMANDs are:

    d[ecode] [OPTS] <file.apk> [<dir>]
        Decode <file.apk> to <dir>.

        OPTS:

        -s, --no-src
            Do not decode sources.
        -r, --no-res
            Do not decode resources.
        -d, --debug
            Decode in debug mode. Check project page for more info.
        -b, --no-debug-info
            Baksmali -- don't write out debug info (.local, .param, .line, etc.)
        -f, --force
            Force delete destination directory.
        -t <tag>, --frame-tag <tag>
            Try to use framework files tagged by <tag>.
        --frame-path <dir>
            Use the specified directory for framework files
        --keep-broken-res
            Use if there was an error and some resources were dropped, e.g.:
            "Invalid config flags detected. Dropping resources", but you
            want to decode them anyway, even with errors. You will have to
            fix them manually before building.

    b[uild] [OPTS] [<app_path>] [<out_file>]
        Build an apk from already decoded application located in <app_path>.

        It will automatically detect, whether files was changed and perform
        needed steps only.

        If you omit <app_path> then current directory will be used.
        If you omit <out_file> then <app_path>/dist/<name_of_original.apk>
        will be used.

        OPTS:

        -f, --force-all
            Skip changes detection and build all files.
        -d, --debug
            Build in debug mode. Check project page for more info.
        -a, --aapt
            Loads aapt from specified location.

    if|install-framework <framework.apk> [<tag>] --frame-path [<location>] 
        Install framework file to your system.

	For additional info, see: http://code.google.com/p/android-apktool/
	For smali/baksmali info, see: http://code.google.com/p/smali/

注意在使用
	
	apktool -d <file.apk路径> <解压文件夹路径>

在使用命令行过程中出现如下异常：
	
	Exception in thread "main" brut.androlib.AndrolibException: Could not decode arsc file

表示apktool.jar不是最新的，去[官网](http://ibotpeaches.github.io/Apktool/)下载即可

将最新的apktool.jar替换掉即可。就可以使用上述命令解压文件了，此时只能看到相应的资源文件。


# 3、反编译classes.dex

将apk文件手动改为zip文件，将zip解压，然后获取到解压后的classes.dex

将classes.dex文件放到：dex2jar-0.0.9.8文件夹中。

切换到这个文件夹，然后执行以下命令
	
	sh dex2jar.sh classes.dex

之后就会得到一个classes_dex2jar.jar的jar文件

如果已经安装过gd-gui了就可以打开jar包了。


参考文章

1、[使用apktool工具遇到could not decode arsc file的解决办法](http://www.sxrczx.com/pages/www.cnblogs.com/sage-blog/p/4323049.html)

2、[ApkTool在Mac上的安装和使用](http://blog.csdn.net/wirelessqa/article/details/8997168)

3、[Android-Mac电脑如何进行APK反编译-使用apktool、jd-gui](http://blog.csdn.net/hanhailong726188/article/details/42368295)

