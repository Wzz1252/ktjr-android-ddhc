package com.torment.lib.ui.widget.toast;

import android.content.Context;
import android.view.Gravity;

import com.torment.lib.ui.entity.toast.ToastEntity;
import com.torment.lib.ui.impl.IToastImpl;

/**
 * Created by Torment on 2016/9/7.
 */
public class ToastFactory {
    private static ToastType sOldType = ToastType.WEAK;
    private static IToastImpl mIToastInterface;

    public static void showToast(Context context, ToastEntity toastEntity) {
        if (toastEntity.toastType == sOldType) {
            if (mIToastInterface == null) {
                mIToastInterface = show(context, toastEntity);
                if (mIToastInterface != null)
                    mIToastInterface.show();
            } else {
                mIToastInterface.update(toastEntity);
            }
        } else {
            if (mIToastInterface != null) {
                mIToastInterface.cancel();
            }

            mIToastInterface = show(context, toastEntity);
            if (mIToastInterface != null) {
                mIToastInterface.show();
            }

            sOldType = toastEntity.toastType;
        }
    }

    public static IToastImpl show(Context context, ToastEntity toastEntity) {
        switch (toastEntity.toastType) {
            case WEAK:
                return new WeakIToast(context, toastEntity);
            case STANDARD:
                return new StandardIToast(context, toastEntity);
            case MULTILINE:
                return new MultiLineIToast(context, toastEntity);
        }
        return null;
    }

    /**
     * 多行提示
     */
    public static void showMultLine(Context context, String title, String subTitle) {
        ToastEntity model = new ToastEntity();
        model.toastType = ToastType.MULTILINE;
        model.title = title;
        model.subTitle = subTitle;
        model.gravity = Gravity.CENTER_VERTICAL;
        showToast(context, model);
    }

    /**
     * 标准提示
     */
    public static void showStandard(Context context, String title, int iconResId) {
        ToastEntity model = new ToastEntity();
        model.toastType = ToastType.STANDARD;
        model.title = title;
        model.iconResId = iconResId;
        model.gravity = Gravity.CENTER_VERTICAL;
        showToast(context, model);
    }

    /**
     * 弱提示
     */
    public static void showWeak(Context context, String title) {
        ToastEntity model = new ToastEntity();
        model.toastType = ToastType.WEAK;
        model.title = title;
        model.gravity = Gravity.BOTTOM;
        showToast(context, model);
    }
}