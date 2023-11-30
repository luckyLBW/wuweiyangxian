package com.wuweiyangxian.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.adapter.OrderAdapter;
import com.wuweiyangxian.adapter.OrderTitleAdapter;
import com.wuweiyangxian.adapter.StoreWarningAdapter;
import com.wuweiyangxian.bean.OrderTitleBean;
import com.wuweiyangxian.bean.StoreWaringBean;
import com.wuweiyangxian.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class StoreWarningActivity extends BaseActivity {

    private TextView tv_none;
    private RecyclerView rv_title;
    private RecyclerView rv_content;
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_right;
    private List<OrderTitleBean> listTitle;
    private StoreWarningAdapter warningAdapter;
    private List<StoreWaringBean> list;
    private OrderTitleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_warning);

        tv_none = findViewById(R.id.tv_none);
        rv_title = findViewById(R.id.rv_title);
        rv_content = findViewById(R.id.rv_content);

        RelativeLayout rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_right = findViewById(R.id.tv_right);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("预警设置");
        tv_title.setText("已核销");
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);

        initList();
        adapter = new OrderTitleAdapter(this);
        rv_title.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_title.setAdapter(adapter);
        adapter.setData(listTitle);

        warningAdapter = new StoreWarningAdapter(this);
        rv_content.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        rv_content.setAdapter(warningAdapter);
        warningAdapter.setData(list);
        adapterClick();
    }

    private void adapterClick() {
        adapter.setOnItemClickListener(new OrderTitleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                for (int i = 0; i < listTitle.size(); i++) {
                    if (i == position) {
                        listTitle.get(i).setSelect(true);
                    } else {
                        listTitle.get(i).setSelect(false);
                    }
                }
                adapter.setData(listTitle);
                updateView(position);
            }
        });
    }

    private void updateView(int position) {
        if (listTitle.get(position).getName().equals("全部")){
            rv_content.setVisibility(View.VISIBLE);
            tv_none.setVisibility(View.GONE);
            warningAdapter.setData(list);
        }else if (listTitle.get(position).getName().equals("库存")){
            rv_content.setVisibility(View.VISIBLE);
            tv_none.setVisibility(View.GONE);
            List<StoreWaringBean> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                StoreWaringBean bean = new StoreWaringBean();
                if (i == 0){
                    bean.setName("库存");
                    bean.setTime("上午9:00");
                    bean.setDes("羊肉剩余已不足100份");
                }else if (i == 1){
                    bean.setName("库存");
                    bean.setTime("昨天");
                    bean.setDes("羊肉剩余已不足100份");
                }else{
                    bean.setName("库存");
                    bean.setTime("前天");
                    bean.setDes("羊肉剩余已不足100份");
                }
                list.add(bean);
            }
            warningAdapter.setData(list);
        }else if (listTitle.get(position).getName().equals("超时")){
            rv_content.setVisibility(View.VISIBLE);
            tv_none.setVisibility(View.GONE);
            List<StoreWaringBean> list = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                StoreWaringBean bean = new StoreWaringBean();
                bean.setName("超时");
                if (i == 0){
                    bean.setTime("上午7:00");
                }else{
                    bean.setTime("上午5:43");
                }
                bean.setDes("羊肉剩余已不足100份");
                list.add(bean);
            }
            warningAdapter.setData(list);
        }else if (listTitle.get(position).getName().equals("食材")){
            rv_content.setVisibility(View.VISIBLE);
            tv_none.setVisibility(View.GONE);
            List<StoreWaringBean> list = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                StoreWaringBean bean = new StoreWaringBean();
                bean.setName("食材");
                bean.setTime("上午3:00");
                bean.setDes("羊肉剩余已不足100份");
                list.add(bean);
            }
            warningAdapter.setData(list);
        }else if (listTitle.get(position).getName().equals("监控")){
            rv_content.setVisibility(View.GONE);
            tv_none.setVisibility(View.VISIBLE);
        }
    }

    private void initList() {
        listTitle = new ArrayList<OrderTitleBean>();
        for (int i = 0; i < 6; i++) {
            OrderTitleBean bean = new OrderTitleBean();
            if (i == 0) {
                bean.setName("全部");
                bean.setSelect(true);
            } else if (i == 1) {
                bean.setName("库存");
                bean.setSelect(false);
            } else if (i == 2) {
                bean.setName("超时");
                bean.setSelect(false);
            } else if (i == 3) {
                bean.setName("食材");
                bean.setSelect(false);
            } else if (i == 4) {
                bean.setName("监控");
                bean.setSelect(false);
            }
            listTitle.add(bean);
        }

        list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            StoreWaringBean bean = new StoreWaringBean();
            if (i == 0){
                bean.setName("库存");
                bean.setTime("上午9:00");
                bean.setDes("羊肉剩余已不足100份");
            }else if (i == 1){
                bean.setName("超时");
                bean.setTime("上午7:00");
                bean.setDes("羊肉剩余已不足100份");
            }else if (i == 2){
                bean.setName("超时");
                bean.setTime("上午5:43");
                bean.setDes("羊肉剩余已不足100份");
            }else if (i == 3){
                bean.setName("食材");
                bean.setTime("上午3:00");
                bean.setDes("羊肉剩余已不足100份");
            }else if (i == 4){
                bean.setName("库存");
                bean.setTime("昨天");
                bean.setDes("羊肉剩余已不足100份");
            }else{
                bean.setName("库存");
                bean.setTime("前天");
                bean.setDes("羊肉剩余已不足100份");
            }
            list.add(bean);
        }
    }
}