package me.wang.liang.web;

import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * Created by wangliang on 2018/1/30.
 * JS 注入接口
 */

public class JSInterface{

    private final Context mContext;

    public JSInterface(Context context) {
        mContext = context;
    }
    @JavascriptInterface
    public void Test(){

    }
}