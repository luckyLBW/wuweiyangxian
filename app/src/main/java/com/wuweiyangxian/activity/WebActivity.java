package com.wuweiyangxian.activity;

import static com.shuhao.webchromeclient.UploadMessage.FILE_CHOOSER_RESULT_CODE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuhao.webchromeclient.FileChooserWebChromeClient;
import com.wuweiyangxian.MainActivity;
import com.wuweiyangxian.R;
import com.wuweiyangxian.util.SpUtil;
import com.wuweiyangxian.util.StatusBarUtil;

public class WebActivity extends BaseActivity {

    private WebView webView;
    private ProgressBar progress;
    private String url;
    private boolean paySuccess = false;
    FileChooserWebChromeClient fileChooserWebChromeClient;
    private boolean showTitle;
    private RelativeLayout rl_top;
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_none;

    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        StatusBarUtil.setImmersiveStatusBar(this, true);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        showTitle = intent.getBooleanExtra("showTitle", false);

        iv_back = findViewById(R.id.iv_back);
        rl_top = findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getColor(R.color.white));
        View view = findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getBaseContext());
        view.setLayoutParams(layoutParams);
        tv_title = findViewById(R.id.tv_title);
        tv_none = findViewById(R.id.tv_none);
        webView = findViewById(R.id.webView);
        progress = findViewById(R.id.progress);

        if (showTitle) {
            rl_top.setVisibility(View.VISIBLE);
            tv_title.setText(intent.getStringExtra("name"));
        } else {
            rl_top.setVisibility(View.GONE);
        }
        tv_title.setTextColor(getColor(R.color.black));
        iv_back.setImageResource(R.mipmap.icon_black_left_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initSetting();
    }

    private void initSetting() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setDisplayZoomControls(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        webView.buildDrawingCache();
        webView.buildLayer();

        fileChooserWebChromeClient = FileChooserWebChromeClient.createBuild(new FileChooserWebChromeClient.ActivityCallBack() {
            @Override
            public void FileChooserBack(Intent intent) {
                startActivityForResult(intent, FILE_CHOOSER_RESULT_CODE);
            }
        });

        webView.setWebChromeClient(fileChooserWebChromeClient);
        webView.addJavascriptInterface(new NativeFunction(), "android");
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            fileChooserWebChromeClient.getUploadMessage().onActivityResult(requestCode, resultCode, data);
        }
    }

    public class NativeFunction {

        @SuppressLint("JavascriptInterface")
        @JavascriptInterface
        public void getBack() {
            //用户点击返回
            finish();
        }


        @SuppressLint("JavascriptInterface")
        @JavascriptInterface
        public void getBackHome() {
            //用户点击返回
            Intent intent = new Intent();
            intent.setClass(WebActivity.this, MainActivity.class);
            startActivity(intent);
        }

        @SuppressLint("JavascriptInterface")
        @JavascriptInterface
        public void getUserInfo() {
            //用户点击返回
            String jsonString = "{\"userId\":\"" + SpUtil.getInteger("userId", 0) + "\", \"token\":\"" + SpUtil.getString("token", "") + "\"}";
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:getUserInfo(" + jsonString + ")");
                }
            });

        }

        @SuppressLint("JavascriptInterface")
        @JavascriptInterface
        public void toLogin() {
            //跳转登录
            startActivity(new Intent(WebActivity.this, LoginActivity.class));
            finish();
        }

        @SuppressLint("JavascriptInterface")
        @JavascriptInterface
        public void toShopCar() {
            //跳转购物车
            startActivity(new Intent(WebActivity.this, MainActivity.class).putExtra("fragment_flag", 3));
            finish();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (paySuccess) {
            paySuccess = false;
            webView.loadUrl("javascript:payResult()");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.clearHistory();
        webView.destroy();
    }

}