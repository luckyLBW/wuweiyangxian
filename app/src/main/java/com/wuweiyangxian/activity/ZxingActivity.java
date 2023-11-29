package com.wuweiyangxian.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.wuweiyangxian.R;
import com.wuweiyangxian.util.StatusBarUtil;

import java.util.Objects;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ZxingActivity extends BaseActivity implements QRCodeView.Delegate {

    private ZXingView view;
    private View viewTop;
    private TextView tv_title;
    private TextView tv_right;
    private TextView tv_write_off;
    private ImageView iv_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);

        view = findViewById(R.id.zv_xing);
        iv_back = findViewById(R.id.iv_back);
        tv_write_off = findViewById(R.id.tv_write_off);
        tv_title = findViewById(R.id.tv_title);
        tv_right = findViewById(R.id.tv_right);
        tv_right.setVisibility(View.VISIBLE);
        tv_title.setText("扫码核销");
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);
        viewTop = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = viewTop.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        viewTop.setLayoutParams(layoutParams);

        view.setDelegate(this);

        tv_write_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
                final AlertDialog dialog = builder.create();
                View dialogView = View.inflate(getBaseContext(), R.layout.alert_write_off, null);
                dialog.setView(dialogView);
                dialog.show();
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.color.transparent);
                Window window = dialog.getWindow();
                if (window != null) {
                    window.setBackgroundDrawableResource(android.R.color.transparent);//设置背景透明
                    window.setGravity(Gravity.BOTTOM);
                }

            }
        });

        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZxingActivity.this,WriteOffActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.startCamera();
        view.startSpotAndShowRect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        view.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
        //扫描成功之后携带数据跳转页面
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}