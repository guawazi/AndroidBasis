package me.wang.liang.web;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by wangliang on 2018/1/30.
 * 给 webview 默认的设置
 */

public class DefaultWebViewSetting {


    public static void init(AppCompatActivity context, WebView webView) {
        WebSettings settings = webView.getSettings();
//        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        // 存储(storage)
        // 启用HTML5 DOM storage API，默认值 false
        settings.setDomStorageEnabled(true);

        // 是否支持viewport属性，默认值 false
        // 页面通过`<meta name="viewport" ... />`自适应手机屏幕
        settings.setUseWideViewPort(true);

        // 是否使用overview mode加载页面，默认值 false
        // 当页面宽度大于WebView宽度时，缩小使页面宽度等于WebView宽度
        settings.setLoadWithOverviewMode(true);

        // 是否支持Javascript，默认值false
        settings.setJavaScriptEnabled(true);

        settings.setLoadsImagesAutomatically(true); // 是否自动加载图片
        settings.setUserAgentString(settings.getUserAgentString() + BuildConfig.WEB_USER_AGENT);
        settings.setBlockNetworkImage(false);
        webView.addJavascriptInterface(new JSInterface(context), "NativeAPI");//webview和js相关调用

        // 设置 webview http 和 https 混合加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.setWebViewClient(new DefaultWebViewClient(context));
        webView.setWebChromeClient(new DefaultChromeClient());
    }


}