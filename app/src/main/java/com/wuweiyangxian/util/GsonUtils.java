package com.wuweiyangxian.util;

import com.google.gson.Gson;

public class GsonUtils {

    private static Gson mGson = new Gson();

    public static <T> T json2Bean(String json, Class<T> clazz) {
        try {
            return mGson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }
}
