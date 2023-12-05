package com.wuweiyangxian.bean;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class MonitorBean {
    private int state;

    private Drawable img;

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
