package com.wuweiyangxian.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.adapter.MonitorAdapter;
import com.wuweiyangxian.bean.MonitorBean;
import com.wuweiyangxian.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class MonitorActivity extends BaseActivity {

    private LinearLayout ll_order_top;
    private RecyclerView rv_monitor;
    private ImageView iv_screen;
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_add;
    private List<MonitorBean> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        RelativeLayout rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        ll_order_top = findViewById(R.id.ll_order_top);
        rv_monitor = findViewById(R.id.rv_monitor);
        iv_screen = findViewById(R.id.iv_screen);
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_add = findViewById(R.id.tv_add);
        tv_title.setText("监控");
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);

        initList();
        MonitorAdapter adapter = new MonitorAdapter(this);
        rv_monitor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_monitor.setAdapter(adapter);
        adapter.setData(list);

        click();
    }

    private void click() {
        iv_screen.setOnClickListener(new View.OnClickListener() {
            private PopupWindow popupWindow;

            @Override
            public void onClick(View view) {
                View popupWindow_view = getLayoutInflater().inflate(R.layout.pop_order, null, false);
                TextView tv_title = popupWindow_view.findViewById(R.id.tv_title);
                tv_title.setText("时间");
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

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MonitorActivity.this,AddMonitorActivity.class));
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MonitorBean bean = new MonitorBean();
            if (i == 0 || i == 2) {
                bean.setState(1);
                if (i == 0) {
                    bean.setImg(getDrawable(R.mipmap.icon_monitor_one));
                } else {
                    bean.setImg(getDrawable(R.mipmap.icon_monitor_two));
                }
            } else {
                bean.setState(0);
                bean.setImg(getDrawable(R.mipmap.icon_monitor_three));
            }
            list.add(bean);
        }
    }
}