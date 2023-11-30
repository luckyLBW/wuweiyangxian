package com.wuweiyangxian.net;

import com.wuweiyangxian.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BaseService {

    @POST("api/login/loginbyphone")
    Observable<ResultEntity> login(@Body LoginBean bean);

    @GET("api/login/send")
    Observable<ResultEntity> getCode(@Query("phone") String phone, @Query("scene") String scene);

    @GET("api/service.order/exchange")
    Observable<ResultEntity> writeOff(@Query("code") String code);

}
