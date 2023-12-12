package com.wuweiyangxian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.util.StatusBarUtil;

public class RuleActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_platform;
    private TextView tv_merchant_agreement;
    private TextView tv_privacy_policy;
    private TextView tv_merchant_protection;
    private LinearLayout ll_pay;
    private LinearLayout ll_action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        RelativeLayout rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("关于午未羊鲜");
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);
        tv_platform = findViewById(R.id.tv_platform);
        tv_merchant_agreement = findViewById(R.id.tv_merchant_agreement);
        tv_privacy_policy = findViewById(R.id.tv_privacy_policy);
        tv_merchant_protection = findViewById(R.id.tv_merchant_protection);
        ll_pay = findViewById(R.id.ll_pay);
        ll_action = findViewById(R.id.ll_action);

        iv_back.setOnClickListener(this);
        tv_platform.setOnClickListener(this);
        tv_merchant_agreement.setOnClickListener(this);
        tv_privacy_policy.setOnClickListener(this);
        tv_merchant_protection.setOnClickListener(this);
        ll_pay.setOnClickListener(this);
        ll_action.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_platform:
                startActivity(new Intent(RuleActivity.this, WebActivity.class).putExtra("showTitle", true).putExtra("name", "平台规则").putExtra("url", "file:///android_asset/platform.html"));
                break;
            case R.id.tv_merchant_agreement:
                startActivity(new Intent(RuleActivity.this, WebActivity.class).putExtra("showTitle", true).putExtra("name", "商家协议").putExtra("url", "file:///android_asset/merchant_agreement.html"));
                break;
            case R.id.tv_privacy_policy:
                startActivity(new Intent(RuleActivity.this, WebActivity.class).putExtra("showTitle", true).putExtra("name", "隐私政策").putExtra("url", "file:///android_asset/privacy_policy.html"));
                break;
            case R.id.tv_merchant_protection:
                startActivity(new Intent(RuleActivity.this, WebActivity.class).putExtra("showTitle", true).putExtra("name", "商家保障").putExtra("url", "file:///android_asset/merchant_protection.html"));
                break;
            case R.id.ll_pay:
                startActivity(new Intent(RuleActivity.this, WebActivity.class).putExtra("showTitle", true).putExtra("name", "通知").putExtra("url", "file:///android_asset/pay.html"));
                break;
            case R.id.ll_action:
                startActivity(new Intent(RuleActivity.this, WebActivity.class).putExtra("showTitle", true).putExtra("name", "通知").putExtra("url", "file:///android_asset/action.html"));
                break;
        }
    }
}