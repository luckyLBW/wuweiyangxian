package com.wuweiyangxian.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CheckPermissionsUtil {

    //定义需要的权限列表
    String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.CAMERA
    };


    /**
     * 申请权限
     */
    public void checkPermissions(Context context, Activity activity) {
        //如果系统大于android6.0，进行动态权限申请
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int i = ContextCompat.checkSelfPermission(context, permissions[0]);
            int l = ContextCompat.checkSelfPermission(context, permissions[1]);
            int m = ContextCompat.checkSelfPermission(context, permissions[2]);
            int f = ContextCompat.checkSelfPermission(context, permissions[3]);
            int a = ContextCompat.checkSelfPermission(context, permissions[4]);
            int b = ContextCompat.checkSelfPermission(context, permissions[5]);
            int c = ContextCompat.checkSelfPermission(context, permissions[6]);
            int d = ContextCompat.checkSelfPermission(context, permissions[7]);
            int e = ContextCompat.checkSelfPermission(context, permissions[8]);
            int g = ContextCompat.checkSelfPermission(context, permissions[9]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝，判断需要的权限列表中是否有权限还没拥有
            if (i != PackageManager.PERMISSION_GRANTED ||
                    l != PackageManager.PERMISSION_GRANTED ||
                    m != PackageManager.PERMISSION_GRANTED ||
                    g != PackageManager.PERMISSION_GRANTED ||
                    f != PackageManager.PERMISSION_GRANTED ||
                    e != PackageManager.PERMISSION_GRANTED ||
                    d != PackageManager.PERMISSION_GRANTED ||
                    c != PackageManager.PERMISSION_GRANTED ||
                    b != PackageManager.PERMISSION_GRANTED ||
                    a != PackageManager.PERMISSION_GRANTED) {
                // 如果有权限没有授予，就去提示用户请求
                startRequestPermission(activity);
            }
        }
    }

    /**
     * 通过权限列表，提示用户赋予或禁止当前还未拥有的权限
     */
    public void startRequestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, permissions, 321);
    }

}
