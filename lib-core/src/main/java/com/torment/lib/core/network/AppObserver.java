package com.torment.lib.core.network;

import android.util.Log;

import com.torment.lib.core.configs.AppConfigs;
import com.torment.lib.core.entity.AppEntity;

import rx.Observer;

public abstract class AppObserver<T extends AppEntity<R>, R> implements Observer<T> {

    private static final String TAG = "AppObserver";

    @Override
    public void onCompleted() {
        Log.d(TAG, "onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        String errorMessage;
        if (AppConfigs.getInstance().getDebug()) {
            errorMessage = e.getMessage();
        } else {
            errorMessage = "好像出现了点小问题，请重试";
        }
        onFailure(NetWorkStatusCode.NETWORK_SERVICE_EXCEPTION.code, errorMessage, true);
    }

    @Override
    public void onNext(T t) {
        if (t.code == NetWorkStatusCode.NETWORK_SUCCESS.code) {
            onSuccess(t.data);
        } else {
            String errorMessage = "";
            if (AppConfigs.getInstance().getDebug()) {
                errorMessage = t.code + t.message;
            } else {
                errorMessage = "好像出现了点小问题，请重试";
            }
            onFailure(t.code, errorMessage, false);
        }
    }

    /**
     * 成功回调
     *
     * @param data 接口返回的具体数据
     */
    abstract public void onSuccess(R data);

    /**
     * 失败回调
     *
     * @param code        错误码
     * @param message     错误信息
     * @param isHttpError 是否为HTTP错误
     */
    abstract public void onFailure(int code, String message, boolean isHttpError);
}
