package com.wuweiyangxian.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuweiyangxian.R;
import com.wuweiyangxian.util.StatusBarUtil;

public class MonitorDetailActivity extends BaseActivity {

    private ImageView iv_back;
    private ImageView iv_monitor;
    private TextView tv_title;
    private TextView tv_state;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_detail);

        number = getIntent().getIntExtra("number", 0);
        RelativeLayout rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        iv_back = findViewById(R.id.iv_back);
        iv_monitor = findViewById(R.id.iv_monitor);
        tv_title = findViewById(R.id.tv_title);
        tv_state = findViewById(R.id.tv_state);
        tv_title.setText("监控");
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);

        if (number == 0 || number == 2) {
            tv_state.setText("在线");
            tv_state.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.mipmap.icon_monitor_green), null, null, null);
            if (number == 0) {
                Glide.with(this).load(getDrawable(R.mipmap.icon_monitor_one)).into(iv_monitor);
            } else {
                Glide.with(this).load(getDrawable(R.mipmap.icon_monitor_three)).into(iv_monitor);
            }
        } else {
            tv_state.setText("离线");
            tv_state.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.mipmap.icon_monitor_grey), null, null, null);
            Glide.with(this).load(getDrawable(R.mipmap.icon_monitor_two)).into(iv_monitor);
        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}