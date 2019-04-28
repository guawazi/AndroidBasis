package com.github.guawazi.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.guawazi.common.BaseApplication;


public class SPUtils {
    private static SharedPreferences sharedPreferences;

    private static final String PREFERENCE_FILE_NAME = "mysp";
    static {
        sharedPreferences = BaseApplication.getContext()
                .getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultString) {
        return sharedPreferences.getString(key, defaultString);
    }

    public static void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static void setBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }
}
