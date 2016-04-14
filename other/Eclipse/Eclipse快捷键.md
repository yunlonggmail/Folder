# 整理Eclipse常用快捷键

开发环境切换到Mac下后原来Window下的快捷键很大一部分是不相容的，习惯了快捷键的生活忽然哪天快捷键不起作用了，跟着的就是开发效率明显降低，频繁录入错误的快捷键让Eclipse都不知道你想要什么了。
 
以下内容是我整理的经常使用的Eclipse快捷键，主要依据首个按键进行了分类。
文档末尾是收集了Mac系统下对文本编辑当标跳转或选中的快捷键。
 
##一、Command类
|快捷键|说明|
|--|--|
|Command+1|快速修复|
|Command+d|删除当前行|
|Command+Option+↓|复制当前行到下一行|
|Command+Option+↑|复制当前行到上一行|
|Command+←|移动光标至当前行的行首|
|Command+→|移动光标至当前行的行尾|
|Command+t|快速显示当前类的结构|
|Command+w|关闭当前编辑页|
|Command+Option+←|前一个编辑的页面|
|Command+Option+→|后一个编辑的页面|
|Command+k|参考当前编辑页选中的关键字向下搜索|
|Command+e|显示当前编辑页面列表可选择页面进行切换|
|Command+/|注释或反注释当前行|
|Command+Shift+e|显示Editor管理器，可选择切换editor|
|Command+j|正向查找，在当前编辑页中查找录入的字符，注意Eclipse状态栏的提示|
|Command+Shift+J|反向查找，使用方式与正向查找类似|
|Command+Shift+W|关闭所有打开的Editor|
|Command+Shift+P|定位匹配符，适用于代码规模比较大的场景，如在while(){}循环体的末尾}处，想要跳转到while(){处。|
|Command+[|向后导航到上一个编辑的文件|
|Command+]|向前导航到下一个编辑的文件|


##二、Option类
|快捷键|说明|
|--|--|
|Option+↓|向下移动当前行|
|Option+↑|向上移动当前行|
|Option+回车|显示当前选择资源的属性|
|Option+/|代码助手“智能提示”|
|Option+Command+R|重命名|
|Option+Command+C|修改函数结构，适用重构|
|Option+Command+L|抽取本地变量|

##三、Control类
|快捷键|说明|
|--|--|
|Control+M|最大化或还原当前editor或view|
 
##四、Shift类
|快捷键|说明|
|--|--|
|Shift+Command+↑|选中光标至全部文本的开头|
|Shift+Command+↓|选中光标至全部文本的结尾|
|Shift+Command+→|选中光标至当前行的结尾|
|Shift+Command+←|选中光标至当前行的开头|
 
##五、补充说明
Eclipse对于文本编辑跳转和选中跳转这块基本和Mac系统一致是通用的，以下内容是Mac系统对文本选中或中或跳转这块的支持。


###5.1.文本位置跳转快捷键
|快捷键|说明|
|--|--|
|Command+左箭头|跳转到一行的开头：|
|Command+右箭头|跳转到一行的末尾：|
|Option+左箭头|跳转到当前单词的开头(适合英文、拼音)：|
|Option+右箭头|跳转到当前单词的末尾(适合英文、拼音)：|
|Command+上箭头|跳转到全部文本的开头：|
|Command+下箭头|跳转到全部文本的末尾：|

###5.2.文本选中快捷键
在以上快捷键中加入Shift，则可以扩展成为选中文本效果的快捷键
|快捷键|说明|
|--|--|
|Shift+Command+左箭头|选中光标到本行开头的文本：|
|Shift+Command+右箭头|选中光标到本行末尾的文本：|
|Shift+Option+左箭头|选中光标到当前单词的开头(适合英文、拼音)：|
|Shift+Option+右箭头|选中光标到当前单词的末尾(适合英文、拼音)：|
|Shift+Command+上箭头|选中光标到全部文本的开头：|
|Shift+Command+下箭头|选中光标到全部文本的末尾：|
###5.3.vim中一些快捷键
|快捷键|说明|
|--|-
|行尾|Shift+4|
|行首|Shift+6|
|文档末尾|Shift+G|
|文档头|gg|

##六、Terminal光标相关
|快捷键|说明|
|--|--|
|Ctrl+u|删除光标到行首的字符|
|Ctrl+k|删除光标到行尾的字符|
|Ctrl+h|删除一个字符(退格删除)|
|Ctrl+c|取消当前行输入的命令|
|Ctrl+a|光标移到行首|
|Ctrl+e|光标移动行尾|
|Ctrl+l|清屏（与clear类似）|
|Ctrl+p|调出命令历史中上一条(与↑类似)|
|Ctrl+n|调出命令历史中下一条(与↓类似)|
|Ctrl+w|删除当前光标前的一个单词|
|Ctrl+y|粘贴(Ctrl+w)删除的单词|
|Alt+←|单词间移动向左|
|Alt+→|单词间移动向右|

##七、参考资料

1.manreadline

2.http://www.macx.cn/thread-2037724-1-1.html

3.http://blog.csdn.net/ybygjy/article/details/40109069
