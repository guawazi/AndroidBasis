package com.github.guawazi.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by wangliang on 2018/6/8.
 * desc:
 */

public class BaseApplication extends Application {

    private static BaseApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
    /**
     * init Method.
     */
    private void init() {
        //Logger init
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("base")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    //for multidex
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return app;
    }
}
