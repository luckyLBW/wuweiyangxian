package com.wuweiyangxian.net;


import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author
 * @date 2023/5/23
 * @description 自己的Observer，减少实现不必要的回调
 */
public abstract class BaseRxObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        onSubscribeImpl(d);
    }

    @Override
    public void onNext(T result) {
        onNextImpl(result);
    }

    @Override
    public void onError(Throwable e) {

        onErrorImpl(e.getMessage());
        if (e instanceof ConnectException ||
                e instanceof SocketTimeoutException ||
                e instanceof TimeoutException) {
        }
//        if (!TextUtils.isEmpty(e.getMessage()) && e.getMessage().contains("401")) {
//            EventBus.getDefault().post(new MessageEvent(AppConstant.CODE_ERROR_NO_LOGIN));
//        }


    }

    @Override
    public void onComplete() {
        onCompleteImpl();
    }

    public void onSubscribeImpl(Disposable d) {

    }

    public void onCompleteImpl() {

    }

    /**
     * onNext 抽象方法，必须实现
     *
     * @param result 接口返回数据
     */
    public abstract void onNextImpl(T result);

    /**
     * onError
     *
     * @param errorMessage 错误信息
     */
    public abstract void onErrorImpl(String errorMessage);

}