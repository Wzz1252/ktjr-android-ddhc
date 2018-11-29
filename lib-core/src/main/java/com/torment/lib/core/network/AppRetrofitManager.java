package com.torment.lib.core.network;

import android.util.Log;

import com.torment.lib.core.entity.AppEntity;
import com.torment.lib.core.network.interceptor.HttpLoggingInterceptor;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AppRetrofitManager {
    private static final String TAG = "AppRetrofitManager";

    private static AppRetrofitManager sInstance;

    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;

    protected AppRetrofitManager() {
    }

    public static AppRetrofitManager getInstance() {
        if (sInstance == null) {
            sInstance = new AppRetrofitManager();
        }
        return sInstance;
    }

    protected Retrofit createRetrofit(String baseUrl) {
        return getRetrofit(baseUrl);
    }

    protected Retrofit getRetrofit() {
        return createRetrofit("https://dev.duoduohaoche.com/");
    }

    protected Retrofit getRetrofit(String baseUrl) {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            setBuilder(builder);
            builder.addInterceptor(buildHttpLoggingInterceptor());
            mOkHttpClient = builder.build();

            mRetrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    protected Interceptor buildHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Log.d(TAG, message));
    }

    protected void setBuilder(OkHttpClient.Builder builder) {
    }

    protected <T> Observable<AppEntity<T>> transformation(Observable<AppEntity<T>> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}