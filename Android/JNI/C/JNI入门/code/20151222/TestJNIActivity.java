package cn.yuguo.mydoctor.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.yuguo.mydoctor.R;
import cn.yuguo.mydoctor.framework.BaseActivity;
import cn.yuguo.mydoctor.utils.ToastUtils;

/**
 * Created by shiyunlong.
 */
public class TestJNIActivity extends BaseActivity implements View.OnClickListener {

    static {
        System.loadLibrary("jniYuGuo");
    }

    @Override
    protected int getLayoutID() {
        //类似于setContentView
        return R.layout.a_test_jni;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleView();
        initView();
    }

    private void initView() {
        Button btnJustPrint = (Button) this.findViewById(R.id.btn_just_print);
        btnJustPrint.setOnClickListener(this);

        Button btnFzPrint = (Button) this.findViewById(R.id.btn_fz_print);
        btnFzPrint.setOnClickListener(this);
    }

    protected void initTitleView() {
        navigationBar.showBackButton();
        navigationBar.setTitle("测试JNI");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_just_print:
                ToastUtils.show(mContext, getStringFromNative());
                break;
            case R.id.btn_fz_print:
                ToastUtils.show(mContext, getStringFromNativeWithString("复杂输出语句"));
                break;
        }
    }

    /**
     * 从c得到输出语句
     *
     * @return
     */
    public native String getStringFromNative();

    /**
     * 复杂输出语句
     *
     * @param string
     * @return
     */
    public native String getStringFromNativeWithString(String string);
}
