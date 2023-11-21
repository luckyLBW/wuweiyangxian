package com.wuweiyangxian.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.wuweiyangxian.MainActivity;
import com.wuweiyangxian.R;
import com.wuweiyangxian.util.SpUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 启动页
 */
public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(SpUtil.getString("token",""))) {
                    startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
                }else {
                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                }
                timer.cancel();
            }
        },2000);
    }
}