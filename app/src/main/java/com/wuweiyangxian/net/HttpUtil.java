package com.wuweiyangxian.net;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.wuweiyangxian.BuildConfig;
import com.wuweiyangxian.app.App;
import com.wuweiyangxian.net.factory.MyGsonConverterFactory;
import com.wuweiyangxian.util.ConstUtil;
import com.wuweiyangxian.util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpUtil {

    private static HttpUtil instance;

    public static HttpUtil getInstance() {
        if (instance == null) {
            synchronized (HttpUtil.class) {
                if (instance == null) {
                    instance = new HttpUtil();
                }
            }
        }

        return instance;
    }

    public BaseService getApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstUtil.httpUrl)
                .client(initOkHttpClient())
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(BaseService.class);
    }

//    public ApiService getApiService(String baseUrl) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .client(initOkHttpClient())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        return retrofit.create(ApiService.class);
//    }

    /**
     * 初始化OkHttpClient
     */
    private OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder mClientBuilder = new OkHttpClient.Builder();
        //设置Http缓存
        Cache cache = new Cache(new File(App.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
        mClientBuilder.cache(cache)
                // 超时重连
                .retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            // 只在debug的时候打印日志
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new MyHttpLoggingInterceptor());
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mClientBuilder.addInterceptor(interceptor);

        }

        mClientBuilder.addInterceptor(new RequestHeaderInterceptor());
        return mClientBuilder.build();
    }

    private class RequestHeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            if (!TextUtils.isEmpty(App.getInstance().getToken())) {
                builder.addHeader("token", App.getInstance().getToken());
            }
//            builder.addHeader("clientId", AppConstant.CLIENT_ID);
//            builder.addHeader("business", "1");
//            builder.addHeader("app_versions", ApkUtil.getVersionName());
//            builder.addHeader("channel", SpUtil.getString(AppConstant.CHANNEL, "default"));
            builder.addHeader("device", "1");
//            builder.addHeader("device_brand", Build.BRAND);
//            builder.addHeader("device_model", Build.MODEL);
            Response response = chain.proceed(builder.build());
            return response;
        }
    }

    private class MyHttpLoggingInterceptor implements HttpLoggingInterceptor.Logger {
        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(@NonNull String message) {
            // 请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.delete(0, mMessage.length());
            }

            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            boolean isJson = (message.startsWith("{") && message.endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"));
            if (isJson) {
                message = JsonUtil.formatJson(JsonUtil.decodeUnicode(message));
            }
            mMessage.append(message.concat("\n"));
            // 响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                Log.d("lbw", mMessage.toString());
            }
        }
    }
}

