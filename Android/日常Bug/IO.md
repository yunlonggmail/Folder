## 1.java.io.IOException: open failed: EINVAL (Invalid argument)

错误分析：

保存文件时，读取文件失败，具体原因是文件路径中含有“:”

解决方式：

处理文件路径时将冒号给替换掉即可

参考：

[http://blog.csdn.net/caiwenfeng_for_23/article/details/20060389](http://blog.csdn.net/caiwenfeng_for_23/article/details/20060389)




