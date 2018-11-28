package com.torment.lib.ui.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 屏幕操控类
 * Created by torment on 16-7-15.
 */
public class DimensUtils {

    /**
     * 获得屏幕像素宽度
     */
    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获得屏幕像素高度
     */
    public static int getScreentHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    /**
     * 获得屏幕参数信息
     */
    static DisplayMetrics sDisplay = null;

    public static DisplayMetrics getDisplayMetrics(Context context) {
        if (sDisplay == null) {
            sDisplay = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(sDisplay);
        }
        return sDisplay;
    }

    /**
     * 像素单位转dip单位
     *
     * @return 像素单位的长度
     */
    public static int pix2dip(Context context, int pixs) {
        int densityDpi = context.getResources().getDisplayMetrics().densityDpi;
        return (pixs * 160) / densityDpi;
    }

    /**
     * dip单位转像素单位
     *
     * @return 像素单位的长度
     */
    public static int dip2pix(Context context, int dips) {
        int densityDpi = context.getResources().getDisplayMetrics().densityDpi;
        return (dips * densityDpi) / 160;
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}