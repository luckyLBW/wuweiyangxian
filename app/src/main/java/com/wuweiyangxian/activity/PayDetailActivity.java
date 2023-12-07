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
import com.wuweiyangxian.adapter.PayDetailAdapter;
import com.wuweiyangxian.bean.PayDetailBean;
import com.wuweiyangxian.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class PayDetailActivity extends BaseActivity {

    private RecyclerView rv_pay_detail;
    private ImageView iv_back;
    private TextView tv_title;
    private List<PayDetailBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_detail);

        RelativeLayout rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        rv_pay_detail = findViewById(R.id.rv_pay_detail);
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

        initList();
        PayDetailAdapter adapter = new PayDetailAdapter(this);
        rv_pay_detail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv_pay_detail.setAdapter(adapter);
        adapter.setData(list);

        adapter.setOnItemClickListener(new PayDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(PayDetailActivity.this,DetailsActivity.class));
            }
        });
    }

    private void initList() {
        list = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            PayDetailBean bean = new PayDetailBean();
            List<PayDetailBean.PayChildBean> listChild = new ArrayList<>();
            PayDetailBean.PayChildBean childBean = new PayDetailBean.PayChildBean();
            if (i == 0) {
                for (int j = 0; j < 5; j++) {
                    if (j == 0) {
                        childBean.setName("提现");
                        childBean.setPrice("-864.50");
                        childBean.setTime("11月29日 14:32");
                    } else if (j == 1) {
                        childBean.setName("余额提现-到建设银行卡（3212）");
                        childBean.setPrice("-264.50");
                        childBean.setTime("11月17日 14:32");
                    } else if (j == 2) {
                        childBean.setName("提现");
                        childBean.setPrice("-2864.50");
                        childBean.setTime("11月11日 14:32");
                    } else if (j == 3) {
                        childBean.setName("提现");
                        childBean.setPrice("-864.50");
                        childBean.setTime("11月10日 14:32");
                    } else {
                        childBean.setName("提现");
                        childBean.setPrice("-1864.50");
                        childBean.setTime("11月08日 14:32");
                    }
                    listChild.add(childBean);
                }
                bean.setTime("2023年11月");
            } else {
                for (int j = 0; j < 5; j++) {
                    if (j == 0) {
                        childBean.setName("提现");
                        childBean.setPrice("-864.50");
                        childBean.setTime("10月29日 14:32");
                    } else if (j == 1) {
                        childBean.setName("余额提现-到建设银行卡（3212）");
                        childBean.setPrice("-264.50");
                        childBean.setTime("10月17日 14:32");
                    } else if (j == 2) {
                        childBean.setName("提现");
                        childBean.setPrice("-2864.50");
                        childBean.setTime("10月11日 14:32");
                    } else if (j == 3) {
                        childBean.setName("提现");
                        childBean.setPrice("-864.50");
                        childBean.setTime("10月10日 14:32");
                    } else {
                        childBean.setName("提现");
                        childBean.setPrice("-1864.50");
                        childBean.setTime("10月08日 14:32");
                    }
                    listChild.add(childBean);
                }
                bean.setTime("2023年10月");
            }
            bean.setExpenditure("支出 ¥12864.50");
            bean.setIncome("收入 ¥864.50");
            bean.setList(listChild);
            list.add(bean);
        }
    }
}