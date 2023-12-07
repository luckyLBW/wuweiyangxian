package com.wuweiyangxian.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wuweiyangxian.R;
import com.wuweiyangxian.app.App;
import com.wuweiyangxian.util.StatusBarUtil;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        App.getInstance().addActivity(this);
        StatusBarUtil.setImmersiveStatusBar(this, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().finishActivity(this);
    }
}