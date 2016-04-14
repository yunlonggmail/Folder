# 1.Proguard

## 1.1、在根目录下创建proguard-rules.pro的文件

这个文件其实可以任意命名，别太离谱就行。

这个文件的主要作用是写代码混淆规则的。

## 1.2、配置Proguard

在应用根目录下的project.properties文件中，写入如下代码：
	
	proguard.config=proguard-rules.pro

表示Proguard的配置指向了“proguard-rules.pro”文件。

## 1.3、配置build.gradle文件

	android{
		***
		buildTypes {
        	debug {
           		***
           		//是否开启混淆模式
            	minifyEnabled true
            	proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            	***
        	}
        	release{
        		***
        		//混淆  dex突破65535的限制
            	minifyEnabled true
            	//加载默认混淆配置文件，progudard-android.txt在sdk目录里面，proguard-rules.pro是我们自己配的混淆文件
            	proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            	***
        	}
	}
	
## 1.4、混淆规则
	
	# Add project specific ProGuard rules here.
	# By default, the flags in this file are appended to flags specified
	# in D:\Program Files (x86)\Android\android-studio\sdk/tools/proguard/proguard-android.txt
	# You can edit the include path and order by changing the proguardFiles
	# directive in build.gradle.
	#
	# For more details, see
	#   http://developer.android.com/guide/developing/tools/proguard.html

	# Add any project specific keep options here:

	# If your project uses WebView with JS, uncomment the following
	# and specify the fully qualified class name to the JavaScript interface
	# class:
	#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
	#   public *;
	#}

	# 代码混淆压缩比，在0和7之间，默认为5，一般不需要改

	-optimizationpasses 5
	
	# 混淆时不使用大小写混合，混淆后的类名为小写
	-dontusemixedcaseclassnames

	# 指定不去忽略非公共的库的类

	-dontskipnonpubliclibraryclasses
	
	# 指定不去忽略非公共的库的类的成员

	-dontskipnonpubliclibraryclassmembers

	# 不做预校验，preverify是proguard的4个步骤之一

	# Android不需要preverify，去掉这一步可加快混淆速度

	-dontpreverify

	# 有了verbose这句话，混淆后就会生成映射文件

	# 包含有类名->混淆后类名的映射关系

	# 然后使用printmapping指定映射文件的名称

	-verbose

	-printmapping proguardMapping.txt


	# 指定混淆时采用的算法，后面的参数是一个过滤器

	# 这个过滤器是谷歌推荐的算法，一般不改变

	-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

	# 保护代码中的Annotation不被混淆，这在JSON实体映射时非常重要，比如fastJson

	-keepattributes *Annotation*

	# 避免混淆泛型，这在JSON实体映射时非常重要，比如fastJson

	-keepattributes Signature

	#//抛出异常时保留代码行号，在第6章异常分析中我们提到过

	-keepattributes SourceFile,LineNumberTable

	# 保留所有的本地native方法不被混淆

	-keepclasseswithmembernames class * {

    	native <methods>;

	}

	# 保留了继承自Activity、Application这些类的子类

	# 因为这些子类，都有可能被外部调用

	# 比如说，第一行就保证了所有Activity的子类不要被混淆

	-keep public class * extends android.app.Activity

	-keep public class * extends android.app.Application

	-keep public class * extends android.app.Service

	-keep public class * extends android.content.BroadcastReceiver

	-keep public class * extends android.content.ContentProvider

	-keep public class * extends android.app.backup.BackupAgentHelper

	-keep public class * extends android.preference.Preference

	-keep public class * extends android.view.View

	-keep public class com.android.vending.licensing.ILicensingService

	# 如果有引用android-support-v4.jar包，可以添加下面这行

	-keep public class com.youngheart.app.ui.fragment.** {*;}

	# 保留在Activity中的方法参数是view的方法，

	# 从而我们在layout里面编写onClick就不会被影响

	-keepclassmembers class * extends android.app.Activity {

    	public void *(android.view.View);

	}

	# 枚举类不能被混淆

	-keepclassmembers enum * {

		public static **[] values();

		public static ** valueOf(java.lang.String);
		
	}

	# 保留自定义控件（继承自View）不被混淆

	-keep public class * extends android.view.View {

    	*** get*();

    	void set*(***);

    	public <init>(android.content.Context);

    	public <init>(android.content.Context, android.util.AttributeSet);

    	public <init>(android.content.Context, android.util.AttributeSet, int);
	}

	# 保留Parcelable序列化的类不被混淆

	-keep class * implements android.os.Parcelable {

    	public static final android.os.Parcelable$Creator *;

	}

	# 保留Serializable序列化的类不被混淆

	-keepclassmembers class * implements java.io.Serializable {

    	static final long serialVersionUID;

    	private static final java.io.ObjectStreamField[] serialPersistentFields;

    	private void writeObject(java.io.ObjectOutputStream);

    	private void readObject(java.io.ObjectInputStream);

    	java.lang.Object writeReplace();

    	java.lang.Object readResolve();

	}

	# 对于R（资源）下的所有类及其方法，都不能被混淆

	-keep class **.R$* {

   		*;

	}

	# 对于带有回调函数onXXEvent的，不能被混淆

	-keepclassmembers class * {

    	void *(**On*Event);

	}


	# 对WebView的处理

	-keepclassmembers class * extends android.webkit.webViewClient {

    	public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);

    	public boolean *(android.webkit.WebView, java.lang.String);

	}

	-keepclassmembers class * extends android.webkit.webViewClient {

    	public void *(android.webkit.webView, java.lang.String);

	}

	# 针对android-support-v4.jar的解决方案

	-dontwarn android.support.v4.**

	-keep class android.support.v4.**  { *; }

	-keep interface android.support.v4.app.** { *; }

	-keep public class * extends android.support.v4.**

	-keep public class * extends android.app.Fragment

	#至此上方的代码可以当做公共代码

	#------异步Http请求相关-------
	-keep class com.loopj.android.http.**{*;}
	-keep interface com.loopj.android.http.**{*;}

	#----------百度统计混淆-------------
	#-keep class com.baidu.kirin.**{*;}
	#-keep class com.baidu.mobstat.**{*;}

	#----------百度地图混淆-------------
	#-keep class com.baidu.android.bbalbs.common.**{*;}
	#-keep class com.baidu.lbsapi.auth.**{*;}
	#-keep class com.baidu.location.**{*;}
	#-keep class com.baidu.mapapi.**{*;}
	#-keep class com.baidu.platform.**{*;}
	-keep class com.baidu.**{*;}
	-keep interface com.baidu.**{*;}
	-keep class vi.com.gdi.bgl.android.java.**{*;}
	-keep interface vi.com.gdi.bgl.android.java.**{*;}

	#----------友盟相关的混淆-----------
	-keep class com.umeng.**{*;}
	-keep interface com.umeng.**{*;}

	-keep class com.squareup.wire.**{*;}
	-keep interface com.squareup.wire.**{*;}

	-keep class com.ta.utdid2.**{*;}
	-keep interface com.ta.utdid2.**{*;}

	-keep class com.ut.device.**{*;}
	-keep interface com.ut.device.**{*;}

	-keep class org.android.**{*;}
	-keep interface org.android.**{*;}

	-keep class com.alimama.mobile.**{*;}
	-keep interface com.alimama.mobile.**{*;}

	#--------环信的混淆 开始--------

	-keep class com.easemob.** {*;}
	-keep interface com.easemob.** {*;}

	-keep class com.novell.sasl.client.**{*;}
	-keep interface com.novell.sasl.client.**{*;}

	-keep class de.measite.smack.**{*;}
	-keep interface de.measite.smack.**{*;}

	-keep class internal.org.apache.http.entity.mime.**{*;}
	-keep interface internal.org.apache.http.entity.mime.**{*;}

	-keep class org.apache.** {*;}
	-keep interface org.apache.** {*;}

	-keep class org.jivesoftware.** {*;}
	-keep interface org.jivesoftware.** {*;}

	-dontwarn  com.easemob.**
	#2.0.9后的不需要加下面这个keep
	#-keep class org.xbill.DNS.** {*;}
	#另外，demo中发送表情的时候使用到反射，需要keep SmileUtils
	-keep class com.easemob.chatuidemo.utils.SmileUtils {*;}
	#注意前面的包名，如果把这个类复制到自己的项目底下，比如放在com.example.utils底下，应该这么写(实际要去掉#)
	#-keep class com.example.utils.SmileUtils {*;}
	#如果使用easeui库，需要这么写
	-keep class com.easemob.easeui.utils.EaseSmileUtils {*;}

	#2.0.9后加入语音通话功能，如需使用此功能的api，加入以下keep
	-dontwarn ch.imvs.**
	-dontwarn org.slf4j.**
	-keep class org.ice4j.** {*;}
	-keep class net.java.sip.** {*;}
	-keep class org.webrtc.voiceengine.** {*;}
	-keep class org.bitlet.** {*;}
	-keep class org.slf4j.** {*;}
	-keep class ch.imvs.** {*;}

	-keep class bolts.**{*;}
	-keep interface bolts.**{*;}

	#--------环信的混淆 结束--------
	#----------GooglePlayService混淆----
	-keep class com.google.android.gms.**{*;}
	-keep interface com.google.android.gms.**{*;}
	-dontwarn com.google.android.gms.**

	#----------信鸽推送混淆-------------
	-keep class com.tencent.android.tpush.**{*;}
	-keep interface com.tencent.android.tpush.**{*;}

	-keep class com.qq.**{*;}
	-keep interface com.qq.**{*;}

	-keep class src.com.qq.**{*;}
	-keep interface src.com.qq.**{*;}

	-keep class com.jg.**{*;}
	-keep interface com.jg.**{*;}

	-keep class com.tencent.mid.**{*;}
	-keep interface com.tencent.mid.**{*;}

	#--------------mframework的混淆---------
	-keep class m.framework.**{*;}
	-keep interface m.framework.**{*;}

	#----------小米相关混淆-------------
	-keep class com.xiaomi.**{*;}
	-keep interface com.xiaomi.**{*;}
	-dontwarn com.xiaomi.**

	#-----------ShareSDK相关的混淆------
	-keep class com.mob.logcollector.**{*;}
	-keep interface com.mob.logcollector.**{*;}

	-keep class com.mob.tools.**{*;}
	-keep interface com.mob.tools.**{*;}

	-keep class cn.sharesdk.**{*;}
	-keep interface cn.sharesdk.**{*;}

	-keep class com.sina.**{*;}
	-keep interface com.sina.**{*;}
	-dontwarn cn.sharesdk.**
	
	#---------Parse的混淆----------
	-keep class com.parse.**{*;}
	-keep interface com.parse.**{*;}
	-dontwarn com.parse.**

	#---------拼音的混淆-----------
	-keep class com.hp.hpl.sparata.**{*;}
	-keep interface com.hp.hpl.sparata.**{*;}

	-keep class demo.**{*;}
	-keep class net.sourceforge.pinyin4j.**{*;}
	-dontwarn demo.**

	#----------七牛的混淆-----------
	-keep class com.qiniu.android.**{*;}
	-keep interface com.qiniu.android.**{*;}
	-dontwarn com.qiniu.android.**

	#----------短信相关的混淆---------
	-keep class cn.smssdk.**{*;}
	-keep interface cn.smssdk.**{*;}

	#----------TestIn相关的混淆------
	-keep class com.testin.agent.**{*;}
	-keep interface com.testin.agent.**{*;}

	#----------ImageLoader的混淆-----
	-keep class com.nostra13.universalimageloader.**{*;}
	-keep interface com.nostra13.universalimageloader.**{*;}

	#----------其他Jar包相关的混淆-----
	-keep class com.nineoldandroids.**{*;}
	-keep interface com.nineoldandroids.**{*;}

	-keep class com.github.chrisbanes.photoview.**{*;}
	-keep interface com.github.chrisbanes.photoview.**{*;}

	#----------框架的混淆----------
	-dontwarn com.chinaway.framework.**

	#----------代码的混淆   Start---------------

	#环信表情使用的工具类
	-keep class cn.yuguo.mydoctor.easemob.utils.SmileUtils {*;}

	# 保留实体类和成员不被混淆
	-keep class cn.yuguo.mydoctor.framework.Entity{*;}

	# 保留实体类YGAdvertisement中rank和position信息
	-keep class cn.yuguo.mydoctor.model.**{*;}
	-keep class cn.yuguo.mydoctor.easemob.domain.**{*;}

	# 保留ViewCell的子类onClick方法就，此方法采用字符拼接和反射的方法处理过。
	-keep class * extends cn.yuguo.mydoctor.framework.ViewCell{
    	public void onClick**(***);
	}

	#育果卡滚动时间事件分发
	-keep class de.greenrobot.event.**{*;}

	-keep class cn.yuguo.mydoctor.activity.YuguoCardActivity{
    	public void onEvent(***);
	}
	-keep class cn.yuguo.mydoctor.activity.InsureDetailActivity{
    	public void onEvent(***);
	}
	#----------代码的混淆   End---------------

##	1.5、在混淆时遇到的问题。
	
### 1.5.1、java.io.IOException:
	
	Error:Execution failed for task ':app:shrink_91baiduDebugMultiDexComponents'.
	> java.io.IOException: The output jar [/Users/shiyunlong/othergit/GitLab/	client-android/app/build/intermediates/multi-dex/_91baidu/debug/	componentClasses.jar] must be specified after an input jar, or it will be empty.

在该问题上方一般有cann't find (super)class，或者  references 之类的问题，此时依次将对应jar中的class不混淆和不提示即可。
	
	#保持包下面的类不混淆
	-keep class de.greenrobot.event.**{*;}
	#保持包下面的方法不混淆
	-keep interface de.greenrobot.event.**{*;}
	#类整体不混淆
	-keep class de.greenrobot.event.waht{*;}
	#类中的某一方法不混淆，当然也可是对应的field什么的。
	-keep class cn.yuguo.mydoctor.activity.YuguoCardActivity{
    	public void onEvent(***);
	}

### 1.5.2、Warning:can't write resource [.readme] (Duplicate zip entry [classes.jar:.readme])

原因是代码中的.readme文件无法加入到jar文件中，此时采用手动打包的方式。

在build.gradle中配置如下代码
	
	android{
		packagingOptions {
        	***
            exclude '.readme'
            ***
    	}
	}


##参考

1、[APP研发录：第7章](http://www.cnblogs.com/Jax/p/4639221.html)

2、[Android proguard 详解](http://blog.csdn.net/dai_zhenliang/article/details/42423575)

3、[android proguard使用心得和遇到的若干问题以及解决思路](http://my.oschina.net/zxcholmes/blog/312627?fromerr=RHqQIJGR)





