package com.torment.lib.ui.utils;

import android.os.Build;

/**
 * Created by Torment on 2016/9/29.
 */
public class LUtils {

    public static boolean isEqualse21() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
