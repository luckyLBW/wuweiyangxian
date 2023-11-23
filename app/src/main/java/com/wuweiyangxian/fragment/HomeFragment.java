package com.wuweiyangxian.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.adapter.HomeShopAdapter;
import com.wuweiyangxian.bean.HomeShopBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initList();
        rv_content = inflate.findViewById(R.id.rv_content);
        adapter = new HomeShopAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_content.setLayoutManager(manager);
        rv_content.setAdapter(adapter);
        adapter.setData(list);
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
}