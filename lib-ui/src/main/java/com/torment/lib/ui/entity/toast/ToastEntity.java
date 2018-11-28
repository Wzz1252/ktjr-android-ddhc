package com.torment.lib.ui.entity.toast;

import android.view.Gravity;
import android.widget.Toast;

import com.torment.lib.ui.widget.toast.ToastType;

/**
 * Created by Torment on 2016/9/7.
 */
public class ToastEntity {
    public ToastType toastType;
    public int iconResId; // 资源图标

    public String title; // 标题
    public String subTitle; // 副标题

    public int titleColor; // 标题颜色
    public int titleSize; // 标题大小
    public int sudTitleColor;

    public int backgroundResId; // 背景颜色

    public int gravity = Gravity.BOTTOM; // 显示的位置

    public int toastTime = Toast.LENGTH_SHORT; // toast的时间长度
}