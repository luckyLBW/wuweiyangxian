package com.wuweiyangxian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.adapter.FoodAdapter;
import com.wuweiyangxian.bean.FoodBean;
import com.wuweiyangxian.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView rv_food;
    private List<FoodBean> list;
    private FoodAdapter adapter;
    private LinearLayout ll_self_pickup;
    private LinearLayout ll_number_type;
    private LinearLayout ll_distance;
    private LinearLayout ll_notes;
    private LinearLayout ll_address;
    private LinearLayout ll_rider;
    private LinearLayout ll_to_be_served;
    private LinearLayout ll_button;
    private LinearLayout ll_delivery_fee;
    private TextView tv_completed;
    private TextView tv_title;
    private TextView tv_des;
    private TextView tv_delivery;
    private TextView tv_good_time;
    private TextView tv_contact_users;
    private TextView tv_name;
    private TextView tv_type;
    private ImageView iv_back;
    private String title;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        state = intent.getIntExtra("state", 0);
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);

        iv_back = findViewById(R.id.iv_back);
        tv_completed = findViewById(R.id.tv_completed);
        tv_type = findViewById(R.id.tv_type);
        tv_name = findViewById(R.id.tv_name);
        tv_title = findViewById(R.id.tv_title);
        tv_des = findViewById(R.id.tv_des);
        tv_contact_users = findViewById(R.id.tv_contact_users);
        tv_delivery = findViewById(R.id.tv_delivery);
        tv_good_time = findViewById(R.id.tv_good_time);
        ll_button = findViewById(R.id.ll_button);
        ll_delivery_fee = findViewById(R.id.ll_delivery_fee);
        ll_number_type = findViewById(R.id.ll_number_type);
        ll_to_be_served = findViewById(R.id.ll_to_be_served);
        ll_self_pickup = findViewById(R.id.ll_self_pickup);
        ll_rider = findViewById(R.id.ll_rider);
        ll_distance = findViewById(R.id.ll_distance);
        ll_notes = findViewById(R.id.ll_notes);
        ll_address = findViewById(R.id.ll_address);
        rv_food = findViewById(R.id.rv_food);
        setState();

        adapter = new FoodAdapter(getBaseContext());
        rv_food.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        rv_food.setAdapter(adapter);
        initList();
        adapter.setData(list);
        tv_type.setText(title);

        if (title.equals("外卖")) {
            tv_type.setTextColor(getColor(R.color.red_e6));
            tv_type.setBackgroundColor(getColor(R.color.red_10_e6));
        } else if (title.equals("自提")) {
            tv_type.setTextColor(getColor(R.color.yellow_ff_6b));
            tv_type.setBackgroundColor(getColor(R.color.yellow_10_ff_6b));
        }

        iv_back.setOnClickListener(this);
    }

    private void setState() {
        switch (state) {
            case 0:
                tv_name.setText("待接单");
                tv_des.setText("3分钟后订单将默认接单，请尽快确认");
                break;
            case 1:
                tv_delivery.setVisibility(View.VISIBLE);
                tv_completed.setVisibility(View.VISIBLE);
                ll_to_be_served.setVisibility(View.GONE);
                tv_name.setText("待出餐");
                tv_des.setText("请合理安排出餐时间");
                SpannableString content = new SpannableString("商家配送");
                UnderlineSpan underlinespan = new UnderlineSpan();
                content.setSpan(underlinespan, 0, content.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                tv_delivery.setText(content);
                break;
            case 2:
                //商家配送
                tv_name.setText("待出餐");
                tv_des.setText("商家正在配送");
                ll_button.setVisibility(View.GONE);
                break;
            case 3:
                //骑手配送
                tv_name.setText("待配送");
                tv_des.setText("当前骑手正在马不停蹄的赶来");
                ll_button.setVisibility(View.GONE);
                ll_distance.setVisibility(View.GONE);
                ll_notes.setVisibility(View.GONE);
                ll_rider.setVisibility(View.VISIBLE);
                break;
            case 4:
                tv_name.setText("待出餐");
                tv_des.setText("请合理安排出餐时间");
                ll_to_be_served.setVisibility(View.GONE);
                ll_self_pickup.setVisibility(View.VISIBLE);
                ll_number_type.setVisibility(View.VISIBLE);
                ll_distance.setVisibility(View.GONE);
                ll_address.setVisibility(View.GONE);
                ll_delivery_fee.setVisibility(View.GONE);
                tv_good_time.setVisibility(View.GONE);
                break;
            case 5:
                //自提已完成
                tv_name.setText("订单已完成");
                tv_des.setText("用户取货码已核销完成");
                ll_number_type.setVisibility(View.VISIBLE);
                tv_contact_users.setVisibility(View.VISIBLE);
                ll_distance.setVisibility(View.GONE);
                ll_address.setVisibility(View.GONE);
                ll_delivery_fee.setVisibility(View.GONE);
                tv_good_time.setVisibility(View.GONE);
                ll_to_be_served.setVisibility(View.GONE);
                break;
        }
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            FoodBean bean = new FoodBean();
            bean.setName("手把羊肉");
            list.add(bean);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}