package com.wuweiyangxian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.util.SpUtil;
import com.wuweiyangxian.util.StatusBarUtil;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_phone;
    private TextView tv_platform;
    private TextView tv_about;
    private TextView tv_power;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        RelativeLayout rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("设置");
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);

        tv_phone = findViewById(R.id.tv_phone);
        tv_platform = findViewById(R.id.tv_platform);
        tv_about = findViewById(R.id.tv_about);
        tv_power = findViewById(R.id.tv_power);
        tv_phone.setText(SpUtil.getString("phone", ""));


        iv_back.setOnClickListener(this);
        tv_platform.setOnClickListener(this);
        tv_about.setOnClickListener(this);
        tv_power.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_platform:
                startActivity(new Intent(SettingActivity.this, PlatformActivity.class));
                break;
            case R.id.tv_about:
                startActivity(new Intent(SettingActivity.this, AboutActivity.class));
                break;
            case R.id.tv_power:
                startActivity(new Intent(SettingActivity.this, PowerActivity.class));
                break;
        }

    }
}