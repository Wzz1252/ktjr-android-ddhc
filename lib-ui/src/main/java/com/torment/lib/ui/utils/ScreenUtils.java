package com.torment.lib.ui.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import me.jessyan.autosize.utils.LogUtils;

/**
 * 屏幕相关工具类
 */
public class ScreenUtils {
    public static final String TAG = "DevicesUtils";

    /**
     * 获取屏幕宽度
     */
    public static int getDisplayWidth(Context context) {
        Display display = ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);

        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getDisplayHeight(Context context) {
        Display display = ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);

        return dm.heightPixels;
    }

    /**
     * 获取屏幕尺寸
     *
     * @return 0:width，1:height
     */
    public static int[] getDisplaySize(Context context) {
        Display display = ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return new int[]{dm.widthPixels, dm.heightPixels};
    }

    /**
     * 判断是否锁屏状态
     *
     * @return true:屏幕处于亮的状态，false：屏幕处于暗的状态
     */
    public static boolean isScreenOn(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        return pm.isScreenOn();
    }
}
