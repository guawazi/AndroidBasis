package me.wang.liang.web;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by wangliang on 2018/1/15.
 * 默认的 webview client
 */

public class DefaultWebViewClient extends WebViewClient {


    private final AppCompatActivity mActivity;

    public DefaultWebViewClient(AppCompatActivity activity) {
        mActivity = activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    // 此方法添加于API21，调用于非UI线程
    // 拦截资源请求并返回数据，返回null时WebView将继续加载资源
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    // 将要加载资源(url)
    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    // 此方法添加于API23
// 加载资源时出错，通常意味着连接不到服务器
// 由于所有资源加载错误都会调用此方法，所以此方法应尽量逻辑简单
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        // 部分图片加载不出来会展示错误页面
    //        RxBus.get().post(Constant.EVENT_WEBVIEW_PAGE_ERROR, "");
    }

    // 此方法添加于API23
    // 在加载资源(iframe,image,js,css,ajax...)时收到了 HTTP 错误(状态码>=400)
    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
    }

    // 加载资源时发生了一个SSL错误，应用必需响应(继续请求或取消请求)
    // 处理决策可能被缓存用于后续的请求，默认行为是取消请求
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }

}