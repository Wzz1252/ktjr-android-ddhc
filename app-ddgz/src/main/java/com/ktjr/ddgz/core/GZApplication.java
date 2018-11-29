package com.ktjr.ddgz.core;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class GZApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Configs.init();

        initARouter();
        initSharePreference();
    }

    private void initARouter() {
        if (Configs.getInstance().getDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private void initSharePreference() {
        SharePreferenceManager.newInstance(getApplicationContext());
    }
}
