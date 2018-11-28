package com.torment.lib.core.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.torment.lib.core.entity.AppEntity;
import com.torment.lib.core.impl.ObserverCallback;

import rx.Observable;
import rx.Subscription;

public class AppRetrofitRequest<BASE_ENTITY> {
    private static final String TAG = "AppRetrofitRequest";

    protected Context mContext;
    protected Observable<AppEntity<BASE_ENTITY>> mSourceObservable;

    public boolean mIsShowLoading = false;
    public boolean mIsShowSuccessToast = false;
    public boolean mIsShowFailureToast = false;

    protected Subscription mSubscription = null;
    protected RetrofitRequestObserver mObserver = null;
    protected ObserverCallback<BASE_ENTITY> mObserverCallback = null;

    AppRetrofitRequest(Context context, Observable<AppEntity<BASE_ENTITY>> sourceObservable) {
        this.mContext = context;
        this.mSourceObservable = sourceObservable;
    }

    private class RetrofitRequestObserver extends AppObserver<AppEntity<BASE_ENTITY>, BASE_ENTITY> {

        @Override
        public void onSuccess(BASE_ENTITY data) {
            Log.d(TAG, "onSuccess data: " + data);
//            if (mIsShowLoading) {
//                DialogUtils.closeLoadingDialog();
//            }
            if (mIsShowSuccessToast) {
                Toast.makeText(mContext, "请求成功", Toast.LENGTH_SHORT).show();
            }
            mObserverCallback.onSuccess(data);
        }

        @Override
        public void onFailure(int code, String message, boolean isHttpError) {
            Log.d(TAG, "onFailure code: " + code + " message: " + message + " isHttpError: " + isHttpError);
//            if (mIsShowLoading) {
//                DialogUtils.closeLoadingDialog();
//            }
            if (mIsShowSuccessToast) {
                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
