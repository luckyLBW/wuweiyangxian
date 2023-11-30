package com.wuweiyangxian.activity;

import androidx.appcompat.app.AppCompatActivity;

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

import com.wuweiyangxian.R;
import com.wuweiyangxian.util.StatusBarUtil;

public class DataStatisticsActivity extends BaseActivity implements View.OnClickListener{

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_channel;
    private TextView tv_cycle;
    private LinearLayout ll_top;
    private PopupWindow popupWindowChannel;
    private PopupWindow popupWindowCycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_statistics);

        ll_top = findViewById(R.id.ll_top);
        tv_channel = findViewById(R.id.tv_channel);
        tv_cycle = findViewById(R.id.tv_cycle);
        RelativeLayout rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("数据统计");
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);

        tv_channel.setOnClickListener(this);
        tv_cycle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_channel:
                //渠道
                View pop_channel = getLayoutInflater().inflate(R.layout.pop_channel, null, false);
                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                popupWindowChannel = new PopupWindow(pop_channel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                //设置可以获取焦点
                popupWindowChannel.setFocusable(true);
                //设置可以触摸弹出框以外的区域
                popupWindowChannel.setOutsideTouchable(true);
                //放在具体控件下方
                popupWindowChannel.showAsDropDown(ll_top, Gravity.CENTER, Gravity.RIGHT);
                // 这里是位置显示方式,在屏幕的侧
//                popupWindow.showAtLocation(iv_screen, Gravity.BOTTOM, 0, 0);
                // 点击其他地方消失
                //！！重要注意-如果这里写完依旧无效！那么按照下面“常见问题”的第一条重新编写此处代码！！
                pop_channel.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (popupWindowChannel != null && popupWindowChannel.isShowing()) {
                            popupWindowChannel.dismiss();
                            popupWindowChannel = null;
                        }
                        return false;
                    }
                });

                break;
            case R.id.tv_cycle:
                //周期
                View pop_cycle = getLayoutInflater().inflate(R.layout.pop_cycle, null, false);
                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                popupWindowCycle = new PopupWindow(pop_cycle, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                //设置可以获取焦点
                popupWindowCycle.setFocusable(true);
                //设置可以触摸弹出框以外的区域
                popupWindowCycle.setOutsideTouchable(true);
                //放在具体控件下方
                popupWindowCycle.showAsDropDown(ll_top, Gravity.CENTER, Gravity.RIGHT);
                // 这里是位置显示方式,在屏幕的侧
//                popupWindow.showAtLocation(iv_screen, Gravity.BOTTOM, 0, 0);
                // 点击其他地方消失
                //！！重要注意-如果这里写完依旧无效！那么按照下面“常见问题”的第一条重新编写此处代码！！
                pop_cycle.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (popupWindowCycle != null && popupWindowCycle.isShowing()) {
                            popupWindowCycle.dismiss();
                            popupWindowCycle = null;
                        }
                        return false;
                    }
                });

                break;
        }

    }
}