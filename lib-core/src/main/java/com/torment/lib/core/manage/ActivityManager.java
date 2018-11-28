package com.torment.lib.core.manage;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Activity 管理器
 */
public class ActivityManager {

    private static String TAG = "ActivityManager";
    private static ActivityManager sInstance = null;

    private ArrayList<Activity> mActivityList = new ArrayList<>();

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (sInstance == null) {
            sInstance = new ActivityManager();
        }
        return sInstance;
    }

    public void addActivity(Activity activity) {
        mActivityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            mActivityList.remove(activity);
        }
    }

    public boolean isActivityExist(String activityClassName) {
        if (activityClassName == null) {
            return false;
        }
        for (int i = 0; i < mActivityList.size(); i++) {
            Activity activity = mActivityList.get(i);
            if (activity == null) {
                continue;
            }
            String shortClassName = activity.getComponentName().getClassName();
            if (shortClassName.equals(activityClassName)) {
                return true;
            }
        }
        return false;
    }

    public void exitAllActivity() {
        for (int i = 0; i < mActivityList.size(); i++) {
            Activity activity = mActivityList.get(i);
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
