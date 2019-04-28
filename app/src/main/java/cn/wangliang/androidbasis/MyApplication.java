package cn.wangliang.androidbasis;

import android.support.multidex.MultiDexApplication;

import com.github.guawazi.common.BaseApplication;


/**
 * Application
 * extends MultiDexApplication and override
 * {@link MultiDexApplication#attachBaseContext}
 * function for support MultiDex
 * <p>
 */

public class MyApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
