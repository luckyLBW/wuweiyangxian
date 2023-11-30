package com.wuweiyangxian.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.net.BaseRxObserver;
import com.wuweiyangxian.net.HttpUtil;
import com.wuweiyangxian.net.ResultEntity;
import com.wuweiyangxian.util.StatusBarUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VerificationSuccessfulActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_successful);

        RelativeLayout rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("已核销");
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        writeOff(id);
    }

    private void writeOff(String id) {
        HttpUtil.getInstance().getApiService().writeOff(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseRxObserver<ResultEntity>() {
                    @Override
                    public void onNextImpl(ResultEntity result) {

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {

                    }
                });
    }
}