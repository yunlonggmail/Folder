# 第1章 开始启程，你的第一行Android代码
省略部分简介。

## 1.1 了解全貌，Android王国简介

Android 从面世以来到现在已经发布了近二十个版本了。在这几年的发展过程中，谷歌为Android王国建立了一个完整的生态系统。手机厂商、开发者、用户之间相互依存，共同推进着Android的蓬勃发展。开发者在其中扮演着不可获取的角色，因为再优秀的操作系统没有开发者来制作丰富的应用程序也是难以得到大众用户喜爱的，相信没有多少人能够忍受没有QQ、微信的手机吧？而谷歌推出的Google Play更是给开发者带来了大量的机遇，只要你能制作出优秀的产品，在Google Play 上获得了用户的认可，你就完全可有得到不错的经济回报，从而成为一名独立的开发者，甚至是成功创业！

那我们现在就以一个开发者的角度，去了解一下这个操作系统吧。纯理论型的东西也比较无聊，怕你看睡着了，因此我只挑重点介绍，这些东西跟你以后的开发工作都是息息相关的。

### 1.1.1 Android系统架构

为了让你能够更好地理解Android系统是怎么工作的，我们先来看一下它的系统架构。Android大致可以分为4层架构，五块区域。

#### 1. Linux内核层
 
Android 系统是基于Linux2.6内核的，这一层为Android设备的各种硬件提供了底层的驱动，如显示驱动、音频驱动、照相机驱动、蓝牙驱动、Wi-Fi驱动、电源管理等。

#### 2. 系统运行库层

这一层通过一些C/C++来为Android系统提供了主要的特性支持。如果SQLite库提供了数据库的支持，OpenGL|ES库提供了3D绘图的支持，Webkit库提供了浏览器内核的支持等。

同样在这一层还有Android运行时库，它主要提供了一些核心库，能够允许开发者使用Java语言来编写Android应用。另外Android运行时库中还包含了Dalvik虚拟机，它使得每一个Android应用都能运行在独立的进程当中，并且拥有一个自己的Dalvik虚拟机实例。相较于Java虚拟机，Dalvik是专门为移动设备定制的，它针对手机内存、CPU性能有限等情况做了优化处理。

#### 3.应用框架层

这一层主要提供了构建应用程序时可能用到的各种API，Android自带的一些核心应用就是使用这些API完成的，开发者也可以通过使用这些API来构建自己的应用程序。

#### 4.应用层

所有安装在手机上的应用程序都是属于这一层的，比如系统自带的联系人、短信等程序，或者是你从Google Play上下载的小游戏，当然还包括你自己开发的程序。

集合图1.1你将会理解的更加深刻，图片源自维基百科。

![image](images/Android系统架构.pngs)

### 1.1.2 Android已发布的版本

2008年9月，谷歌正式发布了Android1.0系统，这也是Android系统最早的版本。随后的几年，谷歌以惊人的速度不断地更新Android系统，2.1、2.2、2.3系统的推出使Android占据了大量的市场。2011年2月，谷歌发布了Android3.0系统，这个系统版本是专门为平板电脑设计的，但也是Android为数不多的比较失败的版本，推出之后一直不见什么起色，市场份额也少的可怜。不过很快，在同年10月，谷歌又发布了Android4.0系统，这个版本不再对手机和平板进行差异化区分，既可以应用在手机上也可以应用在平板上，除此之外还引入了不少新特性。目前最新的系统版本已经是4.4 KitKat.

下表中列出了目前市场上主要的一些Android系统版本及其详细信息。你看到这张表时，数据可能已经发生了变化，查看最新的数据可以访问[http://developer.android.com/about/dashboards](http://developer.android.com/about/dashboards)。

版本号 | 系统代号 | API
------|--------|-----
1.0 | 无 | 1
1.1 | Petit Four | 2
1.5 | Cupcake | 3
1.6 | Donut | 4
2.0/2.0.1/2.1 | Éclair | 5-7
2.2 | Froyo | 8
2.3 - 2.3.2 | Gingerbread | 9
2.3.3-2.3.7 | Gingerbread | 10
3.0 | Honeycomb | 11
3.1 | Honeycomb | 12
3.2 | Honeycomb | 13
Android 4.0 - 4.0.2 | Ice Cream Sandwich | 14
4.0.3 - 4.0.4 | Ice Cream Sandwich | 15
4.1 | Jelly Bean | 16
4.2 | Jelly Bean | 17
4.3 | Jelly Bean | 18
4.4 | KitKat | 19
4.4W| 无 |20
5.0| Lollipop | 21
5.1 | Lollipop | 22 
6.0 | Marshmallow |23

### 1.1.3 Android 应用开发特色

预告一下，你马上就要开始真正的Android开发旅程了。不过先别急，在开始之前我们再来看一看，Android系统到底提供了哪些东西，供我们可以开发出优秀的应用程序。

#### 1.四大组件

Android系统四大组件分别是活的(Activity)、服务（Service）、广播接收器（Broadcase Receiver）和内容提供者（Content Provider）。其中活动是所有Android应用程序的门面，凡是在应用中你看得到的东西，都是放在活动中的。而服务就比较低调了，你无法看到它，但它会一直在后台默默运行，即使用户退出了应用，服务仍然是可以继续运行的。广播接收器可以允许你的应用接收来自各处的广播消息，比如电话、短信等，当然你的应用同样也可有向外发出广播消息。内容提供器则为应用程序之间共享数据提供了可能，比如你想要读取系统电话薄中的联系人，就需要通过内容提供器来实现。

#### 2.丰富的系统控件

Android系统为开发者提供了丰富的系统控件，使得我们可以很轻松地编写出漂亮的界面。当然如果你品位比较高，不满足于系统自带的控件效果，也完全可有定制属于自己的控件。

#### 3.SQLite数据库

Android系统还自带了这种轻量级、运算速度极快的嵌入式关系型数据库。它不仅支持标准的SQL语法，还可以通过Android封装好的API进行操作，让存储和读取数据变得非常方便。

#### 4.地理位置定位

移动设备和PC相比起来，地理位置定位功能应该可以算是很大的一个亮点。现在的Android手机都内置有GPS，走到哪儿都可以定位到自己的位置，发挥你的想象就可以做出创意十足的应用，如果在结合上功能强大的地图功能，LBS这一领域潜力无限。

#### 5.强大的多媒体

Android系统还提供了丰富的多媒体服务，如音乐、视频、录音、拍照、闹铃等待，这一切你都可以在程序中通过代码进行控制，让你的应用变得更加丰富多彩。

#### 6.传感器

Android手机中都会内置多种传感器，如加速度传感器、方向传感器等，这也算是移动设备的一大特点。通过灵活地使用这些传感器，你可以做出很多在PC上根本无法实现的应用。

既然有Android这样出色的系统给我们提供了这么丰富的工具，你还用担心做不出优秀的应用吗？好了，纯理论的东西也就介绍到这里，我知道你已经迫不及待想要开始真正的开发之旅了，那么我们就开始启程吧！

## 1.3 创建你的第一个Android项目

任何一个编程语言写出的第一个程序毫无疑问都会是Hello World,这已经是自20世纪70年代一直流传下来的传统，在编程界已成为永恒的经典，那我们当然也不会搞例外了。

### 1.3.3 分析你的第一个Android程序

还是回到Eclipse中，首先展开HelloWorld项目，你会看到如下图所示的目录结构:

![image](images/Eclipse项目文件目录.png1)

一开始看到这么多陌生的东西，你一定会感到有点儿头晕吧。别担心，我现在就对上图中的内容一一讲解，你很快再看这张图就不会感到那么吃力了。

#### 1. src

毫无疑问，src目录是放置我们所有Java代码的敌方，它在这里的含义和普通Java项目下的src目录是完全一样的，展开之后你讲看到我们刚才创建的HelloWorldActivity文件就在里面。

#### 2.gen

这个目录里的内容都是自动生成的，主要有一个R.java文件，你在项目中添加的任何资源都会在其中生成一个相应的资源ID，这个文件永远不要手动去修改它。

#### 3.assets

这个目录用得不多，主要可以存放一些随程序打包的文件，在你的程序运行时可以动态读取到这些文件的内容。另外，如果你的程序中使用到了WebView加载本地网页功能，所有网页相关的文件也都放在这个目录下。

#### 4.bin

这个目录你也不需要过多关注，它主要包含了一些在编译时自动产生的文件。其中会有一个你当前项目编译好的安装包，展开bin目录你会看到HelloWorld.apk，把这个文件拷到手机上就可以直接安装了。

#### 5.libs

如果你的项目中使用到了第三方Jar包，就需要把这些Jar包都放在libs目录下，放在这个目录下的Jar包都会被自动添加到构建路径里区。你可以展开上图中的Android4.0、Android Private Libraries、Android Dependencies这些库，其中显示的Jar包都是已经被添加到构建路径里的。

#### 6. res

这个目录下的内容就有点多了，简单点说，就是在你的项目中使用到的所有图片、布局、字符串等资源都要存放在这个目录下，前面提到的R.java中的内容也是根据这个目录下的文件自动生成的。当然在这个目录下还有很多的子目录，图片放在drawable目录下，布局放在layout目录下，字符串放在values目录下，所以你不用担心整个res目录弄得乱糟糟的。

#### 7. AndroidManifest.xml

这是你整个Android项目的配置文件，你在程序中定义的所有四大组件都需要在这个文件里注册。另外你还可以在这个文件中给应用程序添加权限声明，也可以重新制定你创建项目时指定的程序最低兼容版本和目标版本。由于这个文件以后会经常用到，我们用到的时候在做详细说明。

#### 8. project.properties

这个文件非常地简单，就是通过一行代码指定了编译程序时所使用的SDK版本。

我们的HelloWorld项目使用的是API 14, 你也可以在这里改成其他版本试一试。

这样整个项目的目录结构就都介绍完了，如果你还不能完全理解的话也很正常，毕竟里面有太多的动向你都还没有接触过。不用担心，这并不会影响到你后面的学习。想法，等你学完整本书后再回来看这个目录结构图时，你会觉得特别地清晰和简单。

接下来我们一起分写一下HelloWorld项目究竟是怎么运行起来的把。首先打开AndroidManifest.xml文件，从中可以找到如下代码：

	<activity android:name=".HelloWorldActivity" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
    </activity>

这段代码表示对MainActivity这个活动的注册，没有在AndroidManafest.xml里注册的活动(Activity)是不能使用的。其中intent-filter里的两行代码非常重要。
	
	<action android:name="android.intent.action.MAIN" />

    <category android:name="android.intent.category.LAUNCHER" />
 
表示这个MainActivity是这个项目的主活动，在手机上点击应用图标，首先启动的就是这个活动。

**注：**

1. **这个一般设置为SplashActivity，因为在闪屏页面我们可以进行应用介绍、业务数据初始化、植入广告等，然后才是主页面，主页面一般是放置核心展示业务逻辑或者所有核心业务逻辑入口。**

2. **主Activity可以有多个，也就是说一个应用可以有多个入口。如：通话记录、联系人。**

那HelloWorldActivity具体又有什么作用呢？我在介绍Android四大组件的时候所过，活动是Android应用程序的门面，凡是在应用中你看得到的动向，都是放在活动中的。因此你在图1.15中看到的界面，其实就是HelloWorldActivity这个活动。那我们快去看一下它的代码吧，打开HelloWorldActivity，代码如下所示

	public class HelloWorldActivity extends Activity {
    	/**
     	* 创建的时候
     	*
     	* @param savedInstanceState
     	*/
    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.hello_world_layout);
    	}

    	/**
     	* 创建菜单的时候
     	* 现在非常不常用
     	* @param menu
     	* @return
     	*/
    	@Override
    	public boolean onCreateOptionsMenu(Menu menu) {
        	return super.onCreateOptionsMenu(menu);
    	}
	}
	
首先我们可以看到，HelloWorldActivity是继承自Activity的。Activity是Android系统提供的一个活动基类，我们项目中所有的活动都必须要继承它才能拥有活动的特性。然后可以看到HelloWorldActivity中的两个办法，onCreateOptionsMenu()这个方法是用于创建菜单的，我们可以先无视它，主要看下onCreate()方法。onCreate方法是一个活动被创建时必定要执行的方法，其中只有两行代码，并且没有Hello world字样。那么途中显示的HelloWorld是在哪里定义的呢？

其实Android程序的设计讲究逻辑和视图分离，因此是不推荐在活动中直接编写界面的，更加通用的一种做法是，在布局文件中编写界面，然后再活动中引入进来。你可以看到，在onCreate()方法的第二行调用了setContentView()方法，就是这个方法给当前的活动引入了一个hello_world_layout布局，那Hello World！一定就是在这里定义的了！我们快打开这个文件看一看。

布局文件文件都是定在res/layout目录下的，当你展开layout目录，你会看到hello_world_layout.xml这个文件。打开之后代码如下所示：

	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	xmlns:tools="http://schemas.android.com/tools"
    	android:layout_width="match_parent"
    	android:layout_height="match_parent"
    	android:paddingBottom="@dimen/activity_vertical_margin"
    	android:paddingLeft="@dimen/activity_horizontal_margin"
    	android:paddingRight="@dimen/activity_horizontal_margin"
    	android:paddingTop="@dimen/activity_vertical_margin"
    	tools:context=".HelloWorldActivity">

    	<TextView
        	android:layout_width="wrap_content"
        	android:layout_height="wrap_content"
        	android:text="@string/hello_world" />
	</RelativeLayout>

现在还看不懂？没关系，后面我会对布局进行详细讲解的，你现在只需要看到上面的代码中有一个TextView，这是Android系统提供的一个空间，用于在布局中显示文件的。然后你终于在TextView中看到了hello world的字样，哈哈终于找到了，原来是通过android:text="@string/hello_world"这句代码定义的！咦？感觉不对劲啊，好像图1.15中显示的是Hellow world！，这感叹号怎么没了，大小写也不太一样。

其实你还是被欺骗了，真正的Hello world！字符串也不是在布局文件中定义的。Android不推荐在程序中对字符串进行硬编码，更好的做法一般是把字符串定义在res/values/strings.xml里，然后就可以在布局文件或代码中引用。那我们现在打开strings.xml看一下，里面的内容如下：

	<resources>
    	<string name="app_name">Hello World</string>
    	<string name="action_settings">Settings</string>
    	<string name="hello_world">Hello world!</string>
	</resources>
	
这下没有什么在逃出你的法眼了，Hello world！字符串就是定义在这个文件里的。并且字符串的定义都是使用键值对的形式，Hello world！值对应了一个叫做hello_world的键，因此在hello_world_layout.xml布局文件中就是通过引用了hello_world这个键，才找到了相应的值。

这个时候我无意中瞄到了这个文件中还有一个叫做app_name的键。你猜对了，我们还可以在这里通过修改app_name对应的值来改变此应用程序的名称。那到底哪里引用了app_name这个键呢？打开AndroidManifest.xml文件自己找吧！

### 1.3.4 详解项目中的资源

如果你展开res目录看一下，其实里面东西还是挺多的，很容易让人看得眼花缭乱，如下图所示：

老的资源目录：

![image](images/老的资源目录.png1)

新的资源目录：

![image](images/新的资源目录.png1)

看到这么多的文件夹不用害怕，其实归纳一下，res目录就变得非常简单了。所有以Drawable开头的文件夹都是用来放图片的，所有以values开头的文件夹都是放字符串的，layout文件夹是用来放布局文件的，menu文件夹是用来放菜单文件的。怎么样，是不是突然感觉清晰了许多？之所以有这么多的drawabl开头的文件夹，其实主要是为了让程序能够兼容更多的设备。在制作程序的时候最好能够给同一张图片提供几个不同分辨率的高低选择加载哪个文件夹下的图片。当然这只是理想情况，更多的时候美工只会提供给我们一份图片，这时候你就把所有图片都放在drawable-hdpi文件下就好了。

知道了res目录下每个文件夹的含义，我们再来看一下如何去使用这些资源吧。比如刚刚在strings.xml中找到的Hello world!字符串，我们有两种方式可以引用它：

1. 在代码通过R.string.hello_world可以获得该字符串的引用；

2. 在XML中通过@string/hello_world可以获得该字符串的引用。

基本的语法就是上面两种方式，其中string部分是可以替换的，如果是引用的图片资源就可以替换成drawable，如果引用的布局文件就可以替换成layout，依次类推。这里就不再给出具体的例子了，因为后面你会在项目中大量地使用各种资源，到时候例子多的是呢。另外跟你小透漏一下，HelloWorld项目的图标就是在AndroidManifest.xml中通过android:icon="@drawable/ic_launcher"来指定的，ic_launcher这张图片就在drawable文件夹下，如果想要通过修改项目的图标你就应该知道怎么办了吧！ 


**注：**

1. **关于Drawable，不只是放图片，如果不带有后缀，一般用来放置xml文件，xml文件其实是图形。调用方式等同于图像**



