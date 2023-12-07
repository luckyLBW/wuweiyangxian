package com.wuweiyangxian.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.util.StatusBarUtil;

public class DetailsActivity extends BaseActivity {
    private ImageView iv_back;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        RelativeLayout rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        tv_title = findViewById(R.id.tv_title);
        iv_back = findViewById(R.id.iv_back);
        tv_title.setText("明细");
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);

        iv_back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }
        );
    }
}