# 1. Your first app : Meet android

**So you're thinking: "What makes Android so special？"** Android is a **free and open oprating system** from **Google** that runs on all kinds of devices from phones, to tablets and even televisions. That's a ton of different devices you can target with just one platform. (And the market share is gaining too). Google provides everything you need to get started building Android apps for free. And you can build your Android apps on either Mac, Windows, or Unix and publish your apps for next to nothing(and with no need for anyone's approval). Ready to get started? Great！ You're going to start building your first Android app, but first there are a few things to setup... 

### So you want to build an Android app...

Maybe your an Android user, your already know Java and want to get in on the mobile craze, or you just love the open operating system and hardware distribution choices of Android . Whatever your reason, you've come to the right place.

### Android already runs on a TON of different devices!

With careful planning, your app can run on all of these Android powered devices. From phones and tablets, to TVs and even home automation, Android is spreading quickly.

### And it's growing!

**“Over 500,000 Android devices [are] activated every day”** --Google's Head of Android, Andy Rubin, via Twitter.

### Just check out the Android Market

The Android Market has a ton of apps. There are of course games(because we all love playing games on our phones), but also really great apps that just **just make our lives better** like navigation and commuting schedule apps.

There are a lot of mobile platforms out there, but with Android's presence and growth, **everyone** is building out their Android apps. Welcome to Android, it's a great place to be!

**Before you dig into your first app, let's take a look at exactly what Android is and who's responsiable for it...**

### So tell me about Android...
Android is a **mobile operating system**, but it's a lot more than that too. There is a whole **ecosystem**, a complete platform, and community that supports Android apps getting built and on to new Android based hardware devices.

#### 1. Google maintains Android
	
Google maintains Android, but it's free to use. Device manufacturers and carriers can modify me, and developers can build apps for free.

#### 2. Hardware manufactures build a device.

Hardware manufactures can use the Android operating system and build special harware around it. Manufactures can even modify Android to implement custom functionality for their devices

#### 3. Google gives you the tools

Google freely distributes the tools for you to build your own Android apps. And you can build your apps on multiple platforms: Mac, Windows, Linux...

#### 4. Google also runs a Market

This is where your users can download their apps right to their phones. Google runs one market, but there are also others run by Amazon, and Verizon for example. But the biggest one is still Google's.

### Are you ready to get started?

**In practice, it's not so bad!**<br>
It's true that there are a bunch of different Android devices out there, from all kinds of different manufacturers running different modifications of Android. Sounds crazy right? While it definitely takes some care tuning your apps for these different devices, you can get started building basic phone apps really easily. And that's what you're going to do right now.

Later on in the book, you'll learn strategies for dealing with different types of devices like phone with different resolutions and even designing for phones and tablets in the same app.

**Let's get started.**

### Getting started

Just asking you to build an app isn't a lot to go on. So the **Pajama Death** made a napkin sketch of what they want the app to look like. It's an app showing the haiku, with each line of haiku on a new line.

#### First you've got some setup to do.

Since this is your first Android app, you'll need to setup your development environment. Let's start with a quick look at what you need in your development environment to build Android apps. From there, you'll install your own development environment, then build the app for **Pajama Death** 

### Meet the android development environment

The Android development environment is made up of serveral parts that seamlessly work together for you to build Android apps. Lets's take a closer look at each one.

#### 1.Eclipse Integrated Developmet Environment(IDE)

The Eclipse Integrated Development Environment(IDE for short) is where you'll write your code. Eclipse is a generic IDE, not specific to Android development. It's managed by the Eclipse foundation.

#### 2. Android Development Tools(ADT)

The Android Development Tools(ADT) is and Eclipse plugin that adds Android specific functionality to Eclipse.

#### 3. Software Development Kit(SDK)

The Android Software Development Kit(SDK) contains all of the lower level tools to build, run and test your  Android apps. The ADT is really just a user interface, and the guts of the app building all happens here in the ADT.

#### 4. Android Packages

You can develop and support multiple versions of Android from the same development environment. These packages and functionality to the base SDK to let you develop for that Android.

### Choosing your IDE

Eclipse may be a fine IDE, but what if you don't want to use it. You may have your own IDE of choice that you'd rather use...

#### You don't have to user Eclipse.

But it certainly makes things easier. The full integrated Android development environment works well as a whole to help you easily build Android apps.

But every thing you need to build and test your Android apps is the **Android SDK and Android Packages.** If you really cant live without your favorite development environment, you can use it in conjunction with the SDK without Eclipse and still build Android apps.

**Even though you can use the SDK without Eclipse, all of the examples in this books will use Eclipse and the ADT plugin.**

There's some major app construction projects up ahead. Don't go any further until you've intalled your IDE.

#### Set up your development environemnt

You won't be able to build your apps until your development environment is setup! Follow our nifty Android development environment setup instructions over the next few pages and you'll be ready to build your apps.

**Turn the page for instructions on setting up your own Android development envionment...**

### Download, install and launch eclipse

Eclipse is a free and open source IDE managed by the Eclipse foundation(started and managed by IBM, but a very community). You can download Eclipse for free from the **[eclipse.org](http://www.eclipse.org/downloads)**. There are a number of different versions of Eclipse optimized for different type of development. You should download the latest versions of **Eclipse Classic** for your Operating System.

After you download Eclipse, follow the installation instrctions for your platform and launch Eclipse. When your launch Eclipse for the first time, you will be prompted to enter a **workspace location;** a directory where all of you Eclipse projects and settings will be stored. Feel free to use the default to enter your own.

### Download and install the SDK

The Android SDK contains the core tools needed to build and run Android apps. This includes the Android emulator, builder, docs and more. You can download the SDK from [developer.android.com](http://developer.android.com/sdk/index.html#Other)

Once you download the SDK zip file, unzip it to your hard drive and the SDK is ready to go.

Now let's setup the ADT...

### Install the ADT

The Android Development Tools(ADT) are the glue that seamlessly connects the Android specific SDK with Eclipse. The ADT is an Eclipse plugin, and it installs through the standard Eclipse plugin installation mechanism(so this should look very familiar if you're an experienced Eclipse user).

From your Eclipse window, select Help->Install new software. This will bring up the  Available Software window. Since this is being installed form search, you'll need to create a new site for the ADT.

Look at the image:

![image](images/installadt.pngs)

### Configure the ADT 

The ADT is just the glue between the SDK and Eclipse, so the ADT needs to know where the SDK is installed.

Set the SDK location in the ADT by going to Window->Preferences in Eclipse, selecting Android from the left panel, and selecting the directory where you installed the Android SDK.

![image](images/configureadt.pngs)

	Geek Bits
	It's a good idea to add the <SDK-install-directory>/tools directory to your path. The SDK includes a number of command line tools and it's convenient to be able to launch them without having to type in complete paths.

### Install android packages

The SDK is designed to allow you to work with multiple versions of Android in the same development environment. To keep downloads small, the SDK version packages are separated from the SDK.(This also allows you to update to new versions of Android without having to redownload the entire **SDK. Pretty slick!**)

You can configure the installed packages in the SDK from the Android SDK and AVD Manager(another added bonus of the ADT). Open the manager by selecting Window->Android SDK and AVD Manager, From the left pane, select "Avalilable Packages".

![image](images/AndroidSdkAndAVDManager.pngs)

When you expand the tree node, you'll see a combination of SDK Tools, SDK platforms, samples documentation and more. These are all plugins to the SDK that your can add to expand the functionality of the SDK. (This way you can download and install the SDK once and keep adding new functionality to it as new versions come out).

![image](images/SelectAndroidPlatform.pngs)

### Questions

#### 1. What about the samples should I install those?

A: Google put together a set of sample apps that show off a bunch of features and techniques in the platform. They won't be used in the book, but they are extremely useful. If you want to learn about something not covered in the book , the samples are a great place to start.

#### 2. And what about Tools? should I install thoose too?

The tools inside the SDK can also get updated as a new functionality is released in the Android platform. It's a good idea to keep these up to date.


--------

1. a bunch of：一群；一束；一堆

1. approval: 英 [ə'pruːv(ə)l]  美 [əˈpruvl] <br/>
n, 批准；认可；赞成

2. automation：英 [ɔːtə'meɪʃ(ə)n]  美 [,ɔtə'meʃən]<br/> 
n, 自动化；自动操作

3. but what：而不…；但是…

3. careful planning：仔细规划

4. certainly：英 ['sɜːt(ə)nlɪ; -tɪn-]  美 ['sɝtnli] <br/>
adv, 当然；行（用于回答）；必定

5. combination：英 [kɒmbɪ'neɪʃ(ə)n]  美 [,kɑmbɪ'neʃən] <br/>
n, 结合；组合；联合；[化学] 化合

4. commuting：英 [kə'mju:tiŋ]<br/>
n, 乘公交车上下班；经常往来

5. community：英 [kə'mjuːnɪtɪ]  美 [kə'mjʊnəti] <br/>
n, 社区；[生态] 群落；共同体；团体<br/>
[ 复数 communities ]

6. configure：英 [kən'fɪgə]  美 [kən'fɪɡjɚ] <br/>
vt, 安装；使成形 <br/>
[ 过去式 configured 过去分词 configured 现在分词 configuring ]

6. conjunction：英 [kən'dʒʌŋ(k)ʃ(ə)n]  美 [kən'dʒʌŋkʃən] <br/>
n, 结合；[语] 连接词；同时发生

7. construction：英 [kən'strʌkʃ(ə)n]  美 [kən'strʌkʃən] <br/>
n. 建设；建筑物；解释；造句

8. convenient：英 [kən'viːnɪənt]  美 [kən'vinɪənt] <br/>
adj, 方便的；[废语]适当的；[口语]近便的；实用的

9. covered：美 ['kʌvɚd] 
adj, 覆盖了的；隐蔽着的；有屋顶的
v， 覆盖；包括；掩护（cover的过去分词）

8. core：英 [kɔː]  美 [kɔr] <br/>
n, 核心；要点；果心；[计] 磁心<br/>
vt, 挖...的核<br/>
n, (Core)人名；(英)科尔；(西、意)科雷<br/>
[ 过去式 cored 过去分词 cored 现在分词 coring ]

2. craze：英 [kreɪz]  美 [krez] <br/>
n, 狂热<br/>
vi, 发狂；产生纹裂<br/>
vt, 使发狂；使产生纹裂<br/>
[ 过去式 crazed 过去分词 crazed 现在分词 crazing ]

6. custom：英 ['kʌstəm]  美 ['kʌstəm] <br/>
n, 习惯，惯例；风俗；海关，关税；经常光顾；[总称]（经常性的）顾客<br/>
adj, （衣服等）定做的，定制的

7. dealing with：处理；对待

7. definitely：英 ['defɪnɪtlɪ]  美 ['dɛfɪnətli] <br/>
adv, 清楚地，当然；明确地，肯定地<br/>
[ 比较级 more definitely 最高级 most definitely ]

8. designing：英 [dɪ'zaɪnɪŋ]  美 [dɪ'zaɪnɪŋ] 
adj, 有计划的；狡猾的
n, 设计；阴谋
v, 计划；企图（design的现在分词）

7. distribute：英 [dɪ'strɪbjuːt; 'dɪstrɪbjuːt]  美 [dɪ'strɪbjut] <br/>
vt, 分配；散布；分开；把…分类<br/>
[ 过去式 distributed 过去分词 distributed 现在分词 distributing ]

3. distribution：英 [dɪstrɪ'bjuːʃ(ə)n]  美 ['dɪstrə'bjʊʃən] <br/>
n, 分布；分配

4. extremely：英 [ɪk'striːmlɪ; ek-]  美 [ɪk'strimli] 
adv, 非常，极其；极端地

4. entire：英 [ɪn'taɪə; en-]  美 [ɪn'taɪɚ] <br/>
adj， 全部的，整个的；全体的
n, (Entire)人名；(英)恩泰尔

4. even though：虽然，即使

5. features：美 [fitʃɚ] <br/>
n, 产品特点，特征；容貌；嘴脸（feature的复数）<br/>
v, 是…的特色，使突出（feature的第三人称单数） 

4. foundation：英 [faʊn'deɪʃ(ə)n]  美 [faʊn'deʃən] <br/>
n, 基础；地基；基金会；根据；创立

4. functionality：英 [fʌŋkʃə'nælətɪ]  美 [,fʌŋkʃə'næləti] <br/>
n, 功能；[数] 泛函性，函数性

5. further：英 ['fɜːðə]  美 ['fɝðɚ] <br/>
adv, 进一步地；而且；更远地 <br/>
adj, 更远的；深一层的 <br/>
vt, 促进，助长；增进

6. glue：英 [gluː]  美 [ɡlu] <br/>
vt, 粘合；似胶般固着于 <br/>
n, 胶；各种胶合物 <br/>
[ 过去式 glued 过去分词 glued 现在分词 gluing ]

5. guts：英 [ɡʌts]  <br/>
n, 内脏；飞碟游戏（比赛双方每组5人，相距15码，互相掷接飞碟）；狭道；贪食者（gut的复数）<br/>
v, 取出…的内脏；毁坏…的内部；贪婪地吃（gut的第三人称单数）<br/>
n, (Guts)人名；(德)古茨

5. integrated：英 ['ɪntɪgreɪtɪd]  美 ['ɪntɪɡretɪd] <br/>
adj, 综合的；完整的；互相协调的<br/>
v, 整合；使…成整体（integrate的过去分词）<br/>

6. kit：英 [kɪt] <br/>
n, 工具箱；成套工具<br/>
vt, 装备<br/>
vi, 装备<br/>
n, (Kit)人名；(俄)基特；(东南亚国家华语)吉；(英)基特，姬特(女名)(教名 Christopher、Katherine 的昵称)<br/>
[ 过去式 kitted 过去分词 kitted 现在分词 kitting ]

7. launch：英 [lɔːntʃ]  美 [lɔntʃ] <br/>
vt, 发射（导弹、火箭等）；发起，发动；使…下水 <br/>
vi, 开始；下水；起飞 <br/>
n, 发射；发行，投放市场；下水；汽艇 <br/>

4. look at exactly：看看到底

5. major：英 ['meɪdʒə]  美 ['medʒɚ] <br/>
adj, 主要的；重要的；主修的；较多的 <br/>
n, [人类] 成年人；主修科目；陆军少校 <br/>
vi, 主修 <br/>
n, (Major)人名；(西)马霍尔；(法)马若尔；(捷、德、塞、瑞典)马约尔；(英)梅杰

1. market share：[贸易] 市场占有率

2. maintain：英 [meɪn'teɪn; mən'teɪn]  美 [men'ten] <br/>
vt, 维持；继续；维修；主张；供养

3. manufactures： n, 制成品（manufacture的复数）<br/>
v, 制造（manufacture的第三人称单数）

4. mechanism：英 ['mek(ə)nɪz(ə)m]  美 ['mɛkənɪzəm] 
n, 机制；原理，途径；进程；机械装置；技巧

2. navigation:英 [nævɪ'geɪʃ(ə)n]  美 ['nævə'geʃən] <br/>
n, 航行；航海

3. nifty：英 ['nɪftɪ]  美 ['nɪfti] <br/>
adj, 俏皮的；漂亮的 <br/>
n, 俏皮话 <br/>
[ 比较级 niftier 最高级 niftiest ]

4. node：英 [nəʊd]  美 [nod] <br/>
n, 节点；瘤；[数] 叉点 <br/>
n, (Node)人名；(法)诺德

1. operating: 英 ['ɒpəreɪtɪŋ]  美 ['ɑpəretɪŋ] <br/>
adj, 操作的；[外科] 外科手术的<br/>
v, 操作（operate的ing形式）；动手术

2. optimized：英 ['ɒptɪmaɪzd]  美 ['ɒptɪmaɪzd] <br/>
adj, 最佳化的；尽量充分利用

4. panel：英 ['pæn(ə)l]  美 ['pænl] ：
n, 仪表板；嵌板；座谈小组，全体陪审员
vt, 嵌镶板
n, (Panel)人名；(英、法)帕内尔
[ 过去式 paneled或-elled 过去分词 paneled或-elled 现在分词 eling 或 elling ]

3. plugin：英 [plʌgɪn]  美 [plʌgɪn] <br/>
n, 插件；相关插件<br/>
n, (Plugin)人名；(俄)普卢金

2. presence：英 ['prez(ə)ns]  美 ['prɛzns] <br/>
n, 存在；出席；参加；风度；仪态

3. prompted：v, [计] 提示（prompt的过去时，过去分词）；引起

3. responsible：英 [rɪ'spɒnsɪb(ə)l]  美 [rɪ'spɑnsəbl] <br/>
adj, 负责的，可靠的；有责任的<br/>
[ 比较级 more responsible 最高级 most responsible ]

4. show off ：炫耀；卖弄

4. slick：英 [slɪk]  美 [slɪk] <br/>
adj, 光滑的；华而不实的；聪明的；熟练的；老套的 <br/>
n, [机] 平滑器；[机] 修光工具；通俗杂志 <br/>
adv, 灵活地；聪明地 <br/>
vt, 使光滑；使漂亮 <br/>
vi, 打扮整洁 <br/>
n, (Slick)人名；(英)斯利克

2. schedule：英 ['ʃedjuːl; 'sked-]  美 ['skɛdʒul] <br/>
vt, 安排，计划；编制目录；将……列入计划表<br/>
n, 时间表；计划表；一览表<br/>
[ 过去式 scheduled 过去分词 scheduled 现在分词 scheduling ]

3. seamlessly:英 ['si:mlisli]  美 
adv. 无缝地

4. separated：英 ['sepəreɪtɪd]  美 ['sɛpəretɪd] 
adj, 分开的；分居；不在一起生活的
v, 分开；隔开（separate的过去式和过去分词）

3. strategies：美 ['strætədʒi] <br/>
n, 策略，战略（strategy的复数形式）

2. spreading：英 ['sprediŋ]  
v, 传播（spread的ing形式）；摊开

3. standard：英 ['stændəd]  美 ['stændɚd] <br/>
n, 标准；水准；旗；度量衡标准 <br/>
adj, 标准的；合规格的；公认为优秀的 <br/>
n, (Standard)人名；(英)斯坦达德；(德)施坦达德

3. tuning：英 ['tjuːnɪŋ]  美 ['tjʊnɪŋ] <br/>
n, [电子][通信] 调谐；调音，起弦；协调一致；起音，定音
