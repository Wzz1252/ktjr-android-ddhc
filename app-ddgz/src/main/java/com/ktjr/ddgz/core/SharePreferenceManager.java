package com.ktjr.ddgz.core;

import android.annotation.SuppressLint;
import android.content.Context;

import com.torment.lib.core.manage.AppSPManager;

public class SharePreferenceManager {
    private static final String TAG = "SharePreferenceManager";
    private static final String NAME_USER_FILE = "user_file";

    @SuppressLint("StaticFieldLeak")
    private static SharePreferenceManager sSharePreferenceManager;

    private Context mContext;
    private AppSPManager mAppSPManager;

    private SharePreferenceManager(Context context) {
        this.mContext = context;
        mAppSPManager = AppSPManager.getInstance(context, "");
    }

    public static SharePreferenceManager newInstance(Context applicationContext) {
        if (sSharePreferenceManager == null) {
            sSharePreferenceManager = new SharePreferenceManager(applicationContext);
        }
        return sSharePreferenceManager;
    }

    /**
     * TODO ----------- 用户TOKEN -----------
     */
    public void setUserToken(String token) {
        mAppSPManager.setParam(NAME_USER_FILE, "user_token", token);
    }

    public String getUserToken() {
        return (String) mAppSPManager.getParam(NAME_USER_FILE, "user_token", "");
    }
}
