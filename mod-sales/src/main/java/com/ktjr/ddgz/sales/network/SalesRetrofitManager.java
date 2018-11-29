package com.ktjr.ddgz.sales.network;

import com.torment.lib.core.network.AppRetrofitManager;
import com.torment.lib.core.network.interceptor.HttpLoggingInterceptor;

import java.io.IOException;

import me.jessyan.autosize.utils.LogUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class SalesRetrofitManager extends AppRetrofitManager {

    private static SalesRetrofitManager sManager;

    public static SalesRetrofitManager newInstance() {
        if (sManager == null) {
            sManager = new SalesRetrofitManager();
        }
        return sManager;
    }

    private Interceptor buildGeneralHeader() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request()
                        .newBuilder()
                        // .addHeader()
                        .build());
            }
        };
    }

    @Override
    protected void setBuilder(OkHttpClient.Builder builder) {
        builder.addInterceptor(buildGeneralHeader());
    }

    @Override
    protected Interceptor buildHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(LogUtils::d);
    }
}

