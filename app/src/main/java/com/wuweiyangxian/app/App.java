package com.wuweiyangxian.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.Stack;

public class App extends Application {

    private static App mInstance;

    private static final Stack<Activity> activityStack = new Stack<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static App getInstance() {
        return mInstance;
    }

//    public String getToken() {
//        return SpUtil.getString("token", "");
//    }

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }

    /**
     * add Activity 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
//            ToastUtils.cancel();
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 判断指定的activity是否存在
     */
    public boolean activityExisted(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
//        RichText.recycle();
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
//        ToastUtils.cancel();
        activityStack.clear();
    }


    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    /**
     * 退出app
     */
    public void exitApp() {
        synchronized (activityStack) {
            for (Activity act : activityStack) {
                if (null != act) {
                    act.finish();
                }
            }
        }
    }

}
