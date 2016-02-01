# 添加SSH key

1、切换到~/.ssh

2、打开git bash, 输入命令 ls -al ~/.ssh

![image](images/SSH列表.png)

检查是否显示有id_rsa.pub或者id_dsa.pub，如果存在请调至第四步

3、在git bash中键入 ssh-keygan -t rsa -C "yunlong.shi@..." ,注意将这里的邮箱地址换成自己的邮箱地址。

![image](images/SSH创建.png)

注意这里会提示让你输入文件名字和密码，就是id_rsa的名称。

最后把密码记一下，别输完就忘了。

4、用记事本之类的软件打开id_rsa.pub文件，并且复制全部内容。

![image](images/id_rsa文件.png)

5、在你的gitlab或者github的账户，打开SSH key标签。

![image](images/MySSHKey.png)

6、然后选择Add SSH key按钮，将刚刚复制的内容粘贴进去即可，然后点击add key。

![image](images/AddSSHKey.png)