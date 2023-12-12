package com.wuweiyangxian.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.adapter.VersionAdapter;
import com.wuweiyangxian.bean.VersionBean;
import com.wuweiyangxian.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class VersionActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_title;
    private List<VersionBean> list;
    private RecyclerView rv_version;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

        RelativeLayout rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("版本介绍");
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initList();

        rv_version = findViewById(R.id.rv_version);
        VersionAdapter adapter = new VersionAdapter(this);
        rv_version.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rv_version.setAdapter(adapter);
        adapter.setData(list);

    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            VersionBean bean = new VersionBean();
            if (i == 0) {
                bean.setVersion("V1.0.0");
                bean.setTime("2023-11-11");
            } else if (i == 1) {
                bean.setVersion("V1.0.1");
                bean.setTime("2023-11-27");
            }
            list.add(bean);
        }
    }
}