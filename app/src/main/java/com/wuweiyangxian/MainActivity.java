package com.wuweiyangxian;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wuweiyangxian.activity.BaseActivity;
import com.wuweiyangxian.app.App;
import com.wuweiyangxian.fragment.HomeFragment;
import com.wuweiyangxian.fragment.MessageFragment;
import com.wuweiyangxian.fragment.MineFragment;
import com.wuweiyangxian.fragment.OrderFragment;
import com.wuweiyangxian.util.CheckPermissionsUtil;
import com.wuweiyangxian.util.StatusBarUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private FragmentManager fm;
    private FrameLayout view_pager;
    private TextView home;
    private TextView order;
    private TextView message;
    private TextView mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckPermissionsUtil checkPermissionsUtil = new CheckPermissionsUtil();
        checkPermissionsUtil.checkPermissions(getBaseContext(), this);

        view_pager = findViewById(R.id.view_pager);
        home = findViewById(R.id.tv_home);
        order = findViewById(R.id.tv_order);
        message = findViewById(R.id.tv_message);
        mine = findViewById(R.id.tv_mine);

        home.setOnClickListener(this);
        order.setOnClickListener(this);
        message.setOnClickListener(this);
        mine.setOnClickListener(this);

        homeFragment = HomeFragment.newInstance();
        orderFragment = OrderFragment.newInstance();
        messageFragment = MessageFragment.newInstance();
        mineFragment = MineFragment.newInstance();

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.view_pager, homeFragment)
                .add(R.id.view_pager, orderFragment)
                .add(R.id.view_pager, messageFragment)
                .add(R.id.view_pager, mineFragment)
                .hide(orderFragment)
                .hide(messageFragment)
                .hide(mineFragment)
                .show(homeFragment).commit();
        showFragment();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showFragment();
    }

    private void showFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        int fragmentFlag = getIntent().getIntExtra("fragment_flag", 0);
        switch (fragmentFlag) {
            case 0:
                ft.hide(orderFragment)
                        .hide(messageFragment)
                        .hide(mineFragment)
                        .show(homeFragment);
                changeDrawable(R.id.tv_home);
                break;
            case 1:
                ft.hide(messageFragment)
                        .hide(mineFragment)
                        .hide(homeFragment)
                        .show(orderFragment);
                changeDrawable(R.id.tv_order);
                break;
            case 2:
                ft.hide(mineFragment)
                        .hide(orderFragment)
                        .hide(homeFragment)
                        .show(messageFragment);
                changeDrawable(R.id.tv_message);

                break;
            case 3:
                ft.hide(orderFragment)
                        .hide(messageFragment)
                        .hide(homeFragment)
                        .show(mineFragment);
                changeDrawable(R.id.tv_mine);
                break;
        }
        ft.commit();
    }

    private void changeDrawable(int id) {
        if (id == R.id.tv_home) {
            home.setTextColor(getResources().getColor(R.color.red_e5));
            home.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.mipmap.icon_home_select), null, null);
        } else {
            home.setTextColor(getResources().getColor(R.color.black_99));
            home.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.mipmap.icon_home), null, null);
        }

        if (id == R.id.tv_message) {
            message.setTextColor(getResources().getColor(R.color.red_e5));
            message.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.mipmap.icon_message_select), null, null);
        } else {
            message.setTextColor(getResources().getColor(R.color.black_99));
            message.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.mipmap.icon_message), null, null);
        }

        if (id == R.id.tv_order) {
            order.setTextColor(getResources().getColor(R.color.red_e5));
            order.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.mipmap.icon_order_select), null, null);
        } else {
            order.setTextColor(getResources().getColor(R.color.black_99));
            order.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.mipmap.icon_order), null, null);
        }

        if (id == R.id.tv_mine) {
            mine.setTextColor(getResources().getColor(R.color.red_e5));
            mine.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.mipmap.icon_mine_select), null, null);
        } else {
            mine.setTextColor(getResources().getColor(R.color.black_99));
            mine.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.mipmap.icon_mine), null, null);
        }

    }

    /**
     * * 两次返回退出程序
     */
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                App.getInstance().finishActivity(this);
            } else {
                Toast.makeText(getBaseContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.tv_home:
                ft.hide(orderFragment)
                        .hide(messageFragment)
                        .hide(mineFragment)
                        .show(homeFragment);
                changeDrawable(R.id.tv_home);
                break;
            case R.id.tv_order:
                ft.hide(messageFragment)
                        .hide(mineFragment)
                        .hide(homeFragment)
                        .show(orderFragment);
                changeDrawable(R.id.tv_order);
                break;
            case R.id.tv_message:
                ft.hide(mineFragment)
                        .hide(orderFragment)
                        .hide(homeFragment)
                        .show(messageFragment);
                changeDrawable(R.id.tv_message);
                break;
            case R.id.tv_mine:
                ft.hide(orderFragment)
                        .hide(messageFragment)
                        .hide(homeFragment)
                        .show(mineFragment);
                changeDrawable(R.id.tv_mine);
                break;
        }
        ft.commit();
    }
}