package com.torment.lib.core.manage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Torment on 2016/11/6.
 */

public class AppSPManager {
    @SuppressLint("StaticFieldLeak")
    private static AppSPManager sSharedPreferencesManager;

    private Context mContext;
    private String mPassword;
    private HashMap<String, SharedPreferences> mPreferencesMap;
    private HashMap<String, SharedPreferences.Editor> mEditorMap;

    private AppSPManager(Context context, String password) {
        mContext = context;
        mPassword = password;

        mPreferencesMap = new HashMap<>();
        mEditorMap = new HashMap<>();
    }

    public static AppSPManager getInstance(Context context, String password) {
        if (sSharedPreferencesManager == null) {
            sSharedPreferencesManager = new AppSPManager(context, password);
        }
        return sSharedPreferencesManager;
    }

    private SharedPreferences initSharedPreferences(String fileName) {
        SharedPreferences preferences = mPreferencesMap.get(fileName);
        if (preferences == null) {
            preferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            mPreferencesMap.put(fileName, preferences);
        }
        return preferences;
    }

    private SharedPreferences.Editor initSharedPreferencesEdit(String fileName) {
        SharedPreferences.Editor editor = mEditorMap.get(fileName);
        if (editor == null) {
            editor = initSharedPreferences(fileName).edit();
            mEditorMap.put(fileName, editor);
        }
        return editor;
    }

    /**
     * @param key    键
     * @param object 值
     */
    public void setParam(String fileName, String key, Object object) {
        if (object == null) {
            return;
        }

        String type = object.getClass().getSimpleName();
        SharedPreferences.Editor editor = initSharedPreferencesEdit(fileName);

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }
        editor.commit();
    }

    /**
     * @param key           键
     * @param defaultObject 值
     * @return 获得的对象
     */
    public Object getParam(String fileName, String key, Object defaultObject) {
        if (defaultObject == null) {
            return null;
        }

        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sharedPreferences = initSharedPreferences(fileName);

        if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        }
        return null;
    }
}
