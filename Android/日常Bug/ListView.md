## 1.ListView setAdapter has already been called 异常

错误分析：

不能添加Header View 因为已经调用了设置适配器.

解决方式：

在适配执行之前设置HeaderView

参考：[ListView setAdapter has already been called 异常](http://blog.csdn.net/androiddevelop/article/details/8474939)

## 2. ListView界面在重复打开的时候会自动滚动到展示位置

错误分析：

在加载数据的过程中，每一次都是展示到数据最下方的位置。这样在使用OnScroll监听最后一条数据展示的过程中，就会导致多次请求网络。

其实是在ListView设置了（android:transcriptMode="normal"）属性。

解决方式：解除设置。

参考：[android:transcriptMode用法](http://my.oschina.net/smalant/blog/41060)



