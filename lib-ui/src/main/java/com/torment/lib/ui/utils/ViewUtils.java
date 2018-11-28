package com.torment.lib.ui.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.torment.lib.ui.R;

import androidx.annotation.NonNull;

/**
 * 更新View的常用工具类
 * Created by Torment on 2016/9/27.
 */
public class ViewUtils {
    // 获得标准ActivitBar的高度
    private static int sActionBarSize = -1;

    // 获得顶部状态栏高度
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    // 获得底部导航栏的高度
//    public static int getNavigationBarHeight(Context context) {
//        Resources resources = context.getResources();
//        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
//        int height = resources.getDimensionPixelSize(resourceId);
//        Log.v("dbw", "Navi height:" + height);
//        return height;
//    }

    public static int getActionBarSize(Context context) {
        return (int) context.getResources().getDimension(R.dimen.abc_action_bar_default_height_material);
    }

//    public static int getNavigationBarHeight(Context context) {
//        return (int) context.getResources().getDimension(R.dimen.bottom_navtion_height) ;
//    }

    /**
     * Determine if the navigation bar will be on the bottom of the screen, based on logic in
     * PhoneWindowManager.
     */
    public static boolean isNavBarOnBottom(@NonNull Context context) {
        final Resources res = context.getResources();
        final Configuration cfg = context.getResources().getConfiguration();
        final DisplayMetrics dm = res.getDisplayMetrics();
        boolean canMove = (dm.widthPixels != dm.heightPixels && cfg.smallestScreenWidthDp < 600);
        return (!canMove || dm.widthPixels < dm.heightPixels);
    }

}
