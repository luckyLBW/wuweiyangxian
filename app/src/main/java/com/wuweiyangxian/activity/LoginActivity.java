package com.wuweiyangxian.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wuweiyangxian.MainActivity;
import com.wuweiyangxian.R;
import com.wuweiyangxian.bean.LoginBean;
import com.wuweiyangxian.bean.LoginResultBean;
import com.wuweiyangxian.net.BaseRxObserver;
import com.wuweiyangxian.net.HttpUtil;
import com.wuweiyangxian.net.ResultEntity;
import com.wuweiyangxian.util.ConstUtil;
import com.wuweiyangxian.util.SpUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录页
 */
public class LoginActivity extends BaseActivity {

    private TextView tv_login;
    private EditText et_login_phone;
    private EditText et_login_code;
    private TextView tv_register_get_code;
    private CheckBox cb_is_select;

    private TimeCount time = new TimeCount(60 * 1000 + 300, 1000);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_login = findViewById(R.id.tv_login);
        et_login_phone = findViewById(R.id.et_login_phone);
        et_login_code = findViewById(R.id.et_login_code);
        cb_is_select = findViewById(R.id.cb_is_select);
        tv_register_get_code = findViewById(R.id.tv_register_get_code);

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_is_select.isChecked()) {
                    Toast.makeText(getBaseContext(), "请先勾选我已阅读并同意《用户协议》和《隐私政策》", Toast.LENGTH_SHORT).show();
                } else {
                    LoginBean bean = new LoginBean();
                    bean.setPhone(et_login_phone.getText().toString().trim());
                    bean.setCode(et_login_code.getText().toString().trim());
                    bean.setIs_keeplogin(true);
                    login(bean);
                }
            }
        });

        tv_register_get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ConstUtil.isMatcher(ConstUtil.PHONE, et_login_phone.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "手机号码格式错误！", Toast.LENGTH_LONG).show();
                } else {
                    //走接口
                    getCode(et_login_phone.getText().toString().trim());
                    time.start();
                }
            }
        });
    }

    private void login(LoginBean bean) {
        HttpUtil.getInstance().getApiService().login(bean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseRxObserver<ResultEntity>() {
                    @Override
                    public void onNextImpl(ResultEntity result) {
                        if (result.getCode() == 1) {
                            try {
                                LoginResultBean bean = JSON.parseObject(JSON.toJSONString(result.getData()), LoginResultBean.class);
//                                EventBus.getDefault().post(new EventMessage("login", bean));
                                SpUtil.putString("token", bean.getToken());
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            finish();
                        } else {
                            Toast.makeText(getBaseContext(), result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {

                    }
                });
    }

    private void getCode(String phone) {
        HttpUtil.getInstance().getApiService().getCode(phone, "login")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseRxObserver<ResultEntity>() {
                    @Override
                    public void onNextImpl(ResultEntity result) {
                        Toast.makeText(getBaseContext(), result.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {

                    }
                });
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tv_register_get_code.setText("获取验证码");
            tv_register_get_code.setTextColor(getResources().getColor(R.color.red_e6));
            tv_register_get_code.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tv_register_get_code.setClickable(false);
            tv_register_get_code.setTextColor(getResources().getColor(R.color.black_99));
            tv_register_get_code.setText("重新获取" + (millisUntilFinished / 1000) + "s");
        }
    }

}