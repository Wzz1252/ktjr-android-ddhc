package com.torment.lib.ui.entity.tab;

import com.torment.lib.ui.impl.TabCallBack;

/**
 * Created by Torment on 2016/9/27.
 */
public class TabEntity {
    public int iconResId;
    public int iconSelectResId;
    public int titleResId;
    public int redPoint;
    public Class<? extends TabCallBack> callBack;

    public TabEntity(int iconResId, int titleResId) {
        this.iconResId = iconResId;
        this.titleResId = titleResId;
    }

    public TabEntity(int iconResId, int titleResId, int redPoint) {
        this.iconResId = iconResId;
        this.titleResId = titleResId;
        this.redPoint = redPoint;
    }

    public TabEntity(int iconResId, int iconSelectResId, int titleResId, int redPoint, Class<? extends TabCallBack> callBack) {
        this.iconResId = iconResId;
        this.iconSelectResId = iconSelectResId;
        this.titleResId = titleResId;
        this.redPoint = redPoint;
        this.callBack = callBack;
    }
}