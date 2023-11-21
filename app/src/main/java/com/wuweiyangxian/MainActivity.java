package com.wuweiyangxian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.wuweiyangxian.app.App;
import com.wuweiyangxian.fragment.HomeFragment;
import com.wuweiyangxian.fragment.MessageFragment;
import com.wuweiyangxian.fragment.MineFragment;
import com.wuweiyangxian.fragment.OrderFragment;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private FragmentManager fm;
    private FrameLayout view_pager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_pager = findViewById(R.id.view_pager);

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
}