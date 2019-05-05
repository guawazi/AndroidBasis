package me.wang.liang.web;

import android.webkit.CookieManager;

/**
 * Created by wangliang on 2018/1/16.
 * 用于全局管理 cookie
 */

public class CookieUtils {
    public static void setCookie(String cookie) {
        // 设置 cookie 必须设置 domain 和 path ，只有当前网页的 domain 和 path 匹配时，才会携带 cookie
        cookie = cookie + ";path=/;domain=" + BuildConfig.DOMAIN+";new=true";
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
        cookieManager.setCookie(BuildConfig.WEB_ROOT_URL, cookie);
    }

    public static void clearCookie() {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }

    public static String getCookie(String url) {
        String cookie = "";
        if (CookieManager.getInstance() != null) {
            cookie = CookieManager.getInstance().getCookie(url);
        }
        return cookie;
    }

}