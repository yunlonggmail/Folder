# 学习信息

学什么？怎么学？学到什么程度？

1. 学习的目标：Dalvik虚拟机，JVM虚拟机，ART虚拟机
2. 学习方法：原理配合实现
3. 程度：对于Dalvik虚拟机

Java VM有一套规范。

[Java SE 规范](https://docs.oracle.com/javase/specs/index.html)

有Java语言规范和Java虚拟机规范，从 SE 6 到 SE 10

# 二、Class、dex、odex文件结构

## 2.1 Class结构总览

1. x.java文件是人编写的，给人看的。
2. x.class是通过工具处理x.java文件后的产物，它是给vm看的，给VM操作的。

Class File信息，使用C的数据结构表达了class文件结构

```
ClassFile{
    u4              majic;//4个字节，唯一的取值为 0xCAFEBABE
    u2              minor_version;//次要版本
    u2              minor_version;//主要版本  这两个版本表示的是Class文件的版本号，和Java编译器有够
    u2              constant_pool_count;//常量池数量
    cp_info         constant_pool[constant_pool_count-1];//大名鼎鼎的常量池，说白了就是一个cp_info数组，其元素类型是cp_info，cp_info可以存储于字符串的等不同类型的东西，类型由cp_info.tag表示
    u2              access_flags;//此class的类型，取值有哪些呢，参考下面的表格？注意，标识是可以或起来的
    u2              this_class;
    u2              super_class;
    u2              interface_count;//代表了这个类定义了多少个Interface
    u2              interface[interface_count];//这些接口都是哪些
    u2              fields_count;//和下面的字段一起表示这个类定义的变量，不论是static还是非static的
    field_info      fields[fields_count];
    u2              methods_count;//和下面的字段一起表示这个类定义了多少个方法，一个方法对应的信息由一个methods数组的元素来表示
    method_info     methods[methods_count];
    u2              attributes_count;//属性，比如一个方法编译后对应的字节码就是Code属性来描述的。源码中的注解也是一种属性，包括原文件的信息等属于属性
    attribute_info  attributes[attributes_count];
}

cp_info{
   u1 tag;
   u1 info[];
}
```

class的类型信息，并不全

取值     | 标志位名
------ | -------------
0x0001 | ACC_PUBLIC
0x0010 | ACC_FINAL
0x0200 | ACC_INTERFACE

- 类无论是public的还是final的，还是interface，就由access_flags来表示。其具体取值无须管，代码用ACC_XXXXX这样的表示，一看就知道是什么东西。
- Java类中定义的域（成员变量），方法都有对应的数据结构来表达，而且还是数组
- 唯一有点特别之处的是常量池。什么东西会放在常量池呢？字符串。。。这个Java源码中的类名，方法名，变量名，都是以字符串的形式放置在常量池中的。所以this_class和super_class分别指向两个字符串，代表本类的名字和基类的名字。这两个字符串存储在常量池中，所以this_class和super_class的类型都是u2(索引，代表长度为2个字节)

使用命令行编译class文件 javap -verbose xxx.class

```
javap -verbose /Users/shiyunlong/learn/LearnJava/eclipse/workspace/Demo/bin/com/syl/jvm/TestMain.class
Classfile /Users/shiyunlong/learn/LearnJava/eclipse/workspace/Demo/bin/com/syl/jvm/TestMain.class
 Last modified 2018-4-10; size 557 bytes
 MD5 checksum 9369e8769eab81f718f8f4941e081c30
 Compiled from "TestMain.java"
public class com.syl.jvm.TestMain
 SourceFile: "TestMain.java"
 minor version: 0
 major version: 51
 flags: ACC_PUBLIC, ACC_SUPER
Constant pool://常量池
  #1 = Class              #2             //  com/syl/jvm/TestMain
  #2 = Utf8               com/syl/jvm/TestMain
  #3 = Class              #4             //  java/lang/Object
  #4 = Utf8               java/lang/Object
  #5 = Utf8               mX
  #6 = Utf8               I
  #7 = Utf8               main
  #8 = Utf8               ([Ljava/lang/String;)V
  #9 = Utf8               Code
 #10 = Methodref          #1.#11         //  com/syl/jvm/TestMain."<init>":()V
 #11 = NameAndType        #12:#13        //  "<init>":()V
 #12 = Utf8               <init>//实例初始化方法，在实例创建出来时调用
 #13 = Utf8               ()V
 #14 = Methodref          #1.#15         //  com/syl/jvm/TestMain.test:()V
 #15 = NameAndType        #16:#13        //  test:()V
 #16 = Utf8               test
 #17 = Utf8               LineNumberTable
 #18 = Utf8               LocalVariableTable
 #19 = Utf8               args
 #20 = Utf8               [Ljava/lang/String;
 #21 = Utf8               testMainObject
 #22 = Utf8               Lcom/syl/jvm/TestMain;
 #23 = Methodref          #3.#11         //  java/lang/Object."<init>":()V
 #24 = Fieldref           #1.#25         //  com/syl/jvm/TestMain.mX:I
 #25 = NameAndType        #5:#6          //  mX:I
 #26 = Utf8               this
 #27 = Utf8               SourceFile
 #28 = Utf8               TestMain.java
{
 public int mX;
   flags: ACC_PUBLIC

 public static void main(java.lang.String[]);
   flags: ACC_PUBLIC, ACC_STATIC
   Code:
     stack=2, locals=2, args_size=1
        0: new           #1                  // class com/syl/jvm/TestMain
        3: dup           
        4: invokespecial #10                 // Method "<init>":()V
        7: astore_1      
        8: aload_1       
        9: invokevirtual #14                 // Method test:()V
       12: return        
     LineNumberTable:
       line 8: 0
       line 9: 8
       line 10: 12
     LocalVariableTable:
       Start  Length  Slot  Name   Signature
              0      13     0  args   [Ljava/lang/String;
              8       5     1 testMainObject   Lcom/syl/jvm/TestMain;

 public com.syl.jvm.TestMain();
   flags: ACC_PUBLIC
   Code:
     stack=2, locals=1, args_size=1
        0: aload_0       
        1: invokespecial #23                 // Method java/lang/Object."<init>":()V
        4: aload_0       
        5: iconst_0      
        6: putfield      #24                 // Field mX:I
        9: return        
     LineNumberTable:
       line 13: 0
       line 5: 4
       line 15: 9
     LocalVariableTable:
       Start  Length  Slot  Name   Signature
              0      10     0  this   Lcom/syl/jvm/TestMain;

 public void test();
   flags: ACC_PUBLIC
   Code:
     stack=0, locals=1, args_size=1
        0: return        
     LineNumberTable:
       line 18: 0
     LocalVariableTable:
       Start  Length  Slot  Name   Signature
              0       1     0  this   Lcom/syl/jvm/TestMain;
}
```

### 2.1.1 常量池的介绍

constant_pool_count是常量池的数组的长度+1

javap 解析class文件的时候，常量池的索引从1算起，0默认是VM自己用的，一般不限速0这一项。所以真正有用的元素是从cout_poll[1]到常量池的末尾的，上面的代码中就有28个常量池元素。

常量池数组的元素类型由下面的代码表示

```
cp_info{//特别注意，这是介绍的cp_info是相关元素类型的通用表达
    u1 tag; //tag为1个字节长。不论cp_info具体是哪种，第一个字节一定代表tag
    u1 info[]; // 其他信息，长度随tag不同而不同
}
// tag取值，先列几个简单的，关于tag的取值可以参考JVM开发规范

tag=7   <== info 代表这个info是CONATANT_Class_info结构体
tag=9   <== info 代表CONSTANT_Fieldref_info结构体
tag=10  <== info 代表CONSTANT_Methodref_info结构体
tag=8   <== info 代表CONSTANT_String_info结构体
tag=1   <== info 代表CONSTANT_Utf8_info结构体
```

上述的tag值可参考[链接](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.1)

在JVM规范中，真正代表字符串的数据结构是CONSTANT_Utf8_info结构体，它的结构如下面的代码所示

```
CONSTANT_Utf8_info {
    u1 tag;
    u2 length;  //下面就是存储UTF8字符串的地方了
    u1 bytes[length];
}
```

此时犹如上述常量池中代码

```
  #2 = Utf8               com/syl/jvm/TestMain
```

下面是几个常用的常量池元素类型

#### CONSTANT_Class_info

这个类型是用来描述类型信息的

```
CONSTANT_Class_info{
    u1  tag; //tag取值为7，代表CONSTANT_Class_info
    u2  name_index; //name_index表示代表自己类型的字符串位于常量池数组中的哪个元素。
}

#1 = Class              #2 //  com/syl/jvm/TestMain
#2 = Utf8               com/syl/jvm/TestMain

从这两行代码中可以看出来，
1\. 第一个元素类型为Class_info，它对应的name_index取值为2，表示使用第2个元素
2\. 常量池第二个元素类型为Utf8 ，内容为“com/syl/jvm/TestMain”
3\. //表示注释
```

#### CONSTANT_NameAndType_Info

它是用来描述方法/成员名以及类型信息的。在JNI中，一个类的成员函数或成员变量都可以有这个类名字符串+函数字符串+参数类型字符串+返回值类型来确定。既然是字符串，那么NameAndType_Info也就是存储了对应字符串在常量池数组中的索引。

```
CONSTANT_NameAndType_Info{
    u1 tag;
    u2 name_index;//方法名或域名对应的字符串索引
    u2 descriptor_index;//方法信息（参数+返回值），或者成员变量的信息（类型）对应的字符串索引
}
```

#### CONSTANT_Methodref_info、CONSTANT_Fieldref_info、CONSTANT_InterfaceMethodref_info

他们三个用于描述方法、成员变量和接口信息。刚才的NameAndType_Inf其实已经描述了方法和成员变量信息的一部分，唯一还缺的就是没有地方描述它们属于哪个类。而这三个兄弟不全了的这些信息。

它们的数据结构如下

```
CONSTANT_Methodref_info{
  u1              tag;//标签,标识
  u2              class_index;//对应的类序号
  u2              name_and_type_index;//名称和类型的序号
}

CONSTANT_Fieldref_info{
  u1 tag;//标签,标识
  u2 class_index;//对应的类序号
  u2 name_and_type_index;//名称和类型的序号
}

CONSTANT_InterfaceMethodref_info{
  u1 tag;//标签,标识
  u2 class_index;//对应的类序号
  u2 name_and_type_index;//名称和类型的序号
}
```

### 2.1.2 Field和Method描述

在Java VM中，方法和成员变量的完整描述有下面的数据结构来表达的

具体的信息可以参考[链接](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.6)，这里介绍了Field_Info，紧随其后介绍了Method_Info

```
Methodref_info{
  u2              access_flags;//描述诸如final，static，public这样的访问标志
  u2              name_index;//方法或成员变量名在常量池中对应的索引，类型是Utf8_Info
  u2              descriptor_index;//
  u2              attributes_count;
  attribute_info  attributes[attribute_count];
}

Fieldref_info{
  u2              access_flags;//描述诸如final，static，public这样的访问标志
  u2              name_index;//方法或成员变量名在常量池中对应的索引，类型是Utf8_Info
  u2              descriptor_index;//
  u2              attributes_count;
  attribute_info  attributes[attribute_count];
}

attribute_info: 是域或方法中很重要的信息，在下一个小结中提到
```

在Java VM 的规范中 Filed_Info, volatile 表示不能被缓存，在Feildref_Info中 ACC_VOLATILE 是其一个标识。

### 2.1.3 attribute_info介绍

attribute_info的数据结构如下

```
//特别主要，这里描述的attribute_info结构体也是具体属性数据结构通用表达
attribute_info{
  u2 attribute_name_index; //attribute_info的描述，指向常量池的字符串
  u4 attribute_length; //具体的内容由info数组来描述
  u2 info[attribute_length];
}
```

在JavaVM规范中，attribute类型比较多，重点有以下几个

#### Code属性

```
Code_attribute{
  u2 attribute_name_index;
  u4 attribute_length;
  u2 max_stack;
  u2 max_locals;
  u4 code_length;
  u1 code[codelegth];
  u2 exception_table_length
  {
    u2 start_pc;
    u2 end_pc;
    u2 handler_pc;
    u2 catch_type;
  }exception_table[exception_table_length];
  u2 attributes_count;
  attribute_info attributes[attributes_count]
}
```

- 前两个成员变量属于attribute的头6个字节，分别指向代表属性名字符串的常量池元素已经后续属性数据的长度。注意: Code属性的attribute_name_index所指向的那个Utf8常量池元素对应的字符串内容就是"Code"
- max_stack和max_locals: 虚拟机在执行一个函数的时候，会为它建立一个操作数栈。执行过程中的参数，一些计算值等都会压入栈中。max_stack就标识该函数执行时，这个栈的最大深度。这是编译时就能确定的。max_locals用于描述这个方法最大栈数和最大的本地变量个数。本地变量个数包括传入的参数。
- code_length和code:这两个表示这个函数编译成Java自己码后对应的字节码长度和内容。
- exception_table_length: 用来描述该方法对应异常处理的信息。start_pc和end_pc分别表示异常处理时候从此方法对应字节码从哪里开始的
- Code属性本身还包含一些属性，这是由attributes_count和attributes数组决定的。

另外，Android SDK build Tools中的dx工具dump class文件得到的信息更全，大家可以试试。

使用方法是：dx --dump --debug xxx.class。
