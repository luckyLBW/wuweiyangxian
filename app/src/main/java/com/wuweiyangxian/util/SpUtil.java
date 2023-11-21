package com.wuweiyangxian.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.wuweiyangxian.app.App;


/**
 * @author fans
 * @description SharedPreferences工具类
 */
public class SpUtil {

    private static String CONFIG = "wuweiyangxian_app";

    public static String getConfigName() {
        return CONFIG;
    }

    /**
     * 存储boolean类型的属性
     */
    public static void putBoolean(String key, boolean value) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 取得boolean类型的属性值
     */
    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }


    /**
     * 存储String类型的属性
     */
    public static void putString(String key, String value) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    /**
     * 取得String类型的属性值
     */
    public static String getString(String key, String defValue) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }


    /**
     * 存储int类型的属性
     */
    public static void putInteger(String key, int value) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    /**
     * 取得int类型的属性值
     */
    public static int getInteger(String key, int defValue) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }


    /**
     * 存储float类型的属性
     */
    public static void putFloat(String key, float value) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sp.edit().putFloat(key, value).commit();
    }

    /**
     * 取得float类型的属性
     */
    public static float getFloate(String key, float defValue) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }


    /**
     * 删除指定key的数据
     */
    public static void removeSpKey(String key) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    /**
     * 清除所有数据
     */
    public static void clearSp() {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
