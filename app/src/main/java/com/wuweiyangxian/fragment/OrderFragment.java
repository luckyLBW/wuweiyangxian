package com.wuweiyangxian.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.adapter.OrderAdapter;
import com.wuweiyangxian.adapter.OrderTitleAdapter;
import com.wuweiyangxian.bean.OrderBean;
import com.wuweiyangxian.bean.OrderTitleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    private RecyclerView rv_title;
    private RecyclerView rv_content;
    private ImageView iv_screen;
    private LinearLayout ll_order_top;
    private List<OrderTitleBean> listTitle;
    private OrderTitleAdapter adapter;
    private OrderAdapter orderAdapter;
    private List<OrderBean> list;

    public OrderFragment() {
    }

    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
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
        View inflate = inflater.inflate(R.layout.fragment_order, container, false);
        initList();
        ll_order_top = inflate.findViewById(R.id.ll_order_top);
        rv_title = inflate.findViewById(R.id.rv_title);
        rv_content = inflate.findViewById(R.id.rv_content);
        iv_screen = inflate.findViewById(R.id.iv_screen);
        adapter = new OrderTitleAdapter(getContext());
        rv_title.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_title.setAdapter(adapter);
        adapter.setData(listTitle);

        orderAdapter = new OrderAdapter(getContext(), getActivity());
        rv_content.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv_content.setAdapter(orderAdapter);
        orderAdapter.setData(list);
        adapterClick();
        initPop();
        return inflate;
    }

    private void initPop() {
        iv_screen.setOnClickListener(new View.OnClickListener() {
            private PopupWindow popupWindow;

            @Override
            public void onClick(View view) {
                View popupWindow_view = getLayoutInflater().inflate(R.layout.order_pop, null, false);
                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                //设置可以获取焦点
                popupWindow.setFocusable(true);
                //设置可以触摸弹出框以外的区域
                popupWindow.setOutsideTouchable(true);
                //放在具体控件下方
                popupWindow.showAsDropDown(ll_order_top, Gravity.CENTER, Gravity.RIGHT);
                // 这里是位置显示方式,在屏幕的侧
//                popupWindow.showAtLocation(iv_screen, Gravity.BOTTOM, 0, 0);
                // 点击其他地方消失
                //！！重要注意-如果这里写完依旧无效！那么按照下面“常见问题”的第一条重新编写此处代码！！
                popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                        return false;
                    }
                });


            }
        });
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
                showDetails(position);
            }
        });
    }

    private void showDetails(int position) {
        if (listTitle.get(position).getName().contains("待接单") || listTitle.get(position).getName().contains("全部")) {
            orderAdapter.setData(list);
        } else if (listTitle.get(position).getName().contains("待出餐")) {
            List list = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                OrderBean bean = new OrderBean();
                if (i == 0) {
                    bean.setTitle("外卖");
                    bean.setName("待出餐");
                    bean.setTime("1:26:00");
                    bean.setNumber("22");
                    bean.setDes("请合理安排出餐时间");
                    bean.setMoney("666");
                    bean.setState(1);
                }
                list.add(bean);
            }
            orderAdapter.setData(list);
        } else if (listTitle.get(position).getName().contains("待取餐")) {
            List list = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                OrderBean bean = new OrderBean();
//                if (i == 0){
                bean.setTitle("外卖");
                bean.setName("配送中");
                bean.setTime("1:26:00");
                bean.setNumber("22");
                bean.setDes("商家正在配送中");
                bean.setMoney("666");
                bean.setState(2);
//                }
                list.add(bean);
            }
            orderAdapter.setData(list);
        } else if (listTitle.get(position).getName().contains("待收货")) {
            List list = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                OrderBean bean = new OrderBean();
//                if (i == 0){
                bean.setTitle("外卖");
                bean.setName("配送中");
                bean.setTime("1:26:00");
                bean.setNumber("22");
                bean.setDes("骑手正在配送中");
                bean.setMoney("666");
                bean.setState(3);
//                }
                list.add(bean);
            }
            orderAdapter.setData(list);
        } else if (listTitle.get(position).getName().contains("待自提")) {
            List list = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                OrderBean bean = new OrderBean();
//                if (i == 0){
                bean.setTitle("自提");
                bean.setName("待自提");
                bean.setTime("1:26:00");
                bean.setNumber("22");
                bean.setDes("等待商家接单");
                bean.setMoney("666");
                bean.setState(4);
//                }
                list.add(bean);
            }
            orderAdapter.setData(list);
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
                bean.setName("待接单 (2)");
                bean.setSelect(false);
            } else if (i == 2) {
                bean.setName("待出餐 (1)");
                bean.setSelect(false);
            } else if (i == 3) {
                bean.setName("待取餐 (6)");
                bean.setSelect(false);
            } else if (i == 4) {
                bean.setName("待收货 (2)");
                bean.setSelect(false);
            } else if (i == 5) {
                bean.setName("待自提 (2)");
                bean.setSelect(false);
            }
            listTitle.add(bean);
        }


        list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            OrderBean bean = new OrderBean();
            if (i == 0) {
                bean.setTitle("外卖");
                bean.setName("#66");
                bean.setTime("1:26:00");
                bean.setNumber("22");
                bean.setDes("等待商家接单");
                bean.setMoney("666");
                bean.setState(0);
            } else if (i == 1) {
                bean.setTitle("自提");
                bean.setName("#66");
                bean.setTime("1:26:00");
                bean.setNumber("22");
                bean.setDes("等待商家接单");
                bean.setMoney("666");
                bean.setState(0);
            }
            list.add(bean);
        }
    }
}