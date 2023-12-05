package com.wuweiyangxian.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.activity.DataStatisticsActivity;
import com.wuweiyangxian.activity.MonitorActivity;
import com.wuweiyangxian.activity.StoreWarningActivity;
import com.wuweiyangxian.activity.WalletActivity;
import com.wuweiyangxian.adapter.HomeShopAdapter;
import com.wuweiyangxian.bean.HomeShopBean;
import com.wuweiyangxian.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private LinearLayout ll_number_statistics;
    private TextView tv_store_warning;
    private TextView tv_wallet;
    private TextView tv_monitor;
    private RecyclerView rv_content;
    private HomeShopAdapter adapter;
    private List list;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initList();
        ll_number_statistics = inflate.findViewById(R.id.ll_number_statistics);
        tv_store_warning = inflate.findViewById(R.id.tv_store_warning);
        tv_wallet = inflate.findViewById(R.id.tv_wallet);
        tv_monitor = inflate.findViewById(R.id.tv_monitor);
        rv_content = inflate.findViewById(R.id.rv_content);
        View top_view = inflate.findViewById(R.id.top_view);
        ViewGroup.LayoutParams layoutParams = top_view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getContext());
        top_view.setLayoutParams(layoutParams);
        adapter = new HomeShopAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_content.setLayoutManager(manager);
        rv_content.setAdapter(adapter);
        adapter.setData(list);

        ll_number_statistics.setOnClickListener(this);
        tv_store_warning.setOnClickListener(this);
        tv_wallet.setOnClickListener(this);
        tv_monitor.setOnClickListener(this);
        return inflate;
    }

    private void initList() {
        list = new ArrayList<HomeShopBean>();
        for (int i = 0; i < 4; i++) {
            HomeShopBean bean = new HomeShopBean();
            if (i == 0) {
                bean.setTitle("库存");
                bean.setName("手切羊肉");
                bean.setNumber("100");
                bean.setTime("刚刚");
            } else if (i == 1) {
                bean.setTitle("超时");
                bean.setName("#98");
                bean.setNumber("10");
                bean.setTime("16:10");
            } else if (i == 2) {
                bean.setTitle("食材");
                bean.setName("冷冻羊肉");
                bean.setNumber("3");
                bean.setTime("昨天");
            } else {
                bean.setTitle("监控");
                bean.setName("走廊");
                bean.setNumber("离线");
                bean.setTime("昨天");
            }
            list.add(bean);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_number_statistics:
                //跳转数据统计
                startActivity(new Intent(getActivity(), DataStatisticsActivity.class));
                break;
            case R.id.tv_store_warning:
                //跳转店铺预警
                startActivity(new Intent(getActivity(), StoreWarningActivity.class));
                break;
            case R.id.tv_wallet:
                //跳转钱包
                startActivity(new Intent(getActivity(), WalletActivity.class));
                break;
            case R.id.tv_monitor:
                //跳转监控
                startActivity(new Intent(getActivity(), MonitorActivity.class));
                break;
        }
    }
}