package com.wuweiyangxian.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConstUtil {
    //演示版
//    public static String httpUrl = "http://43.140.247.202:10010";
    //测试
    public static String httpUrl = "http://43.140.247.202:10303/";
    public static String webUrl = "http://43.140.247.202:10027/h5/#/";
    public static final String PHONE = "^1[3-9]\\d{9}$";
    //线上版
//    public final static String httpUrl = "https://www.yunbianwuzhan.com";

    public static boolean isMatcher(String patternStr, CharSequence input) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}
