package me.wang.liang.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by wangliang on 2018/1/15.
 */

public class DefaultChromeClient extends WebChromeClient {

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

}