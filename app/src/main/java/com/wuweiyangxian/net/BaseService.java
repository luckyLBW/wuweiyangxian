package com.wuweiyangxian.net;

import com.wuweiyangxian.bean.CodeBean;
import com.wuweiyangxian.bean.LoginBean;
import com.wuweiyangxian.bean.LoginResultBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BaseService {

    @POST("api/login/loginbyphone")
    Observable<ResultEntity> login(@Body LoginBean bean);

    @POST("api/login/send")
    Observable<ResultEntity> getCode(@Body CodeBean bean);

}
