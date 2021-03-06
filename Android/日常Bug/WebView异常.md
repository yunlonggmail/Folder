# 1.WebView中报异常（ZoomButtonsController）

android.app.IntentReceiverLeaked: Activity cn.yuguo.mydoctor.activity.AdvertiseActivity has leaked IntentReceiver android.widget.ZoomButtonsController$1@6cac35e that was originally registered here. Are you missing a call to unregisterReceiver()?

主要产生原因：

WebView中包含一个ZoomButtonsController，当使用web.getSettings().setBuiltInZoomControls(true);启用后，用户一旦触摸屏幕，就会出现缩放控制图标。这个图标过上几秒会自动消失，但在3.X系统上，如果图标自动消失前退出当前Activity的话，就会报上面的这些异常。

根据异常信息再参考一下WebView的源码就可以知道ZoomButtonsController有一个register和unregister的过程。但是这两个过程是我们控制不了的，WebView有显示控制的API但我们访问不过。我们能访问到的只有这几个和ZoomButtonsController的控制相关：
void setBuiltInZoomControls(boolean enabled)
void setDefaultZoom(WebSettings.ZoomDensity zoom)
void setDisplayZoomControls(boolean enabled)
void setSupportZoom(boolean support)
试过了一遍都不管用。

解决方式：

在Activity的onDestroy里面加上这么一句：web.setVisibility(View.GONE);把WebView设置为GONE就可以了。

# 2.WebView Destroy异常

java.lang.Throwable: Error: WebView.destroy() called while still attached

这个错误从字面意思来说是当你结束webview的时候，Webview还依附在父控件下，使用解决这个问题
就是在WebView.destroy()前要解除他们之间的依附关系。一般会碰上这个问题应该是这样对webview进行了操作：
	
将其在父类View中移除。

	llWebViewParent.removeView(webView);
    webView.stopLoading();
    webView.removeAllViews();
    webView.clearCache(true);
    webView.setVisibility(View.GONE);
    webView.destroy();

#3. Android M webview问题。

现象：webview在打开新的Activity后，前面加载的页面无法滚动，重新加载也不行。

问题分析：webview 在Activiy onPause执行时如果调用webview.onPause()，那么在Activity.onResume的时候webview依旧是onPause状态，所以页面完全无法滚动。

解决方式：在Activity.onResume的时候，调用WebView的时候调用onPause就行了。