package me.wang.liang.web;

import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.guawazi.common.ui.base.BaseActivity;

@Route(path = "/web/main")
public class WebActivity extends BaseActivity {

    WebView mWebView;

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initEventAndData() {
        mWebView = findViewById(R.id.webview);
        DefaultWebViewSetting.init(this,mWebView);
        mWebView.loadUrl("https://www.baidu.com");
    }
}
