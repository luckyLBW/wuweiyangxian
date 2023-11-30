package com.wuweiyangxian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.adapter.OrderAdapter;
import com.wuweiyangxian.adapter.WriteOffAdapter;
import com.wuweiyangxian.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class WriteOffActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_title;
    private RecyclerView rv_write_off;
    private List<String> list;
    private WriteOffAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_off);

        RelativeLayout rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        rv_write_off = findViewById(R.id.rv_write_off);
        tv_title.setText("已核销");
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);

        adapter = new WriteOffAdapter(getBaseContext(), WriteOffActivity.this);
        rv_write_off.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
        rv_write_off.setAdapter(adapter);
        initList();
        adapter.setData(list);

        adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int state, String title) {
                startActivity(new Intent(WriteOffActivity.this, OrderDetailActivity.class).putExtra("state", state).putExtra("title", title));

            }
        });
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add("");
        }
    }
}