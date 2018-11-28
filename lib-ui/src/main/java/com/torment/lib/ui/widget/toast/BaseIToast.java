package com.torment.lib.ui.widget.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.torment.lib.ui.entity.toast.ToastEntity;
import com.torment.lib.ui.impl.IToastImpl;


/**
 * Created by Torment on 2016/9/9.
 */
public abstract class BaseIToast implements IToastImpl {
    protected Context mContext;
    protected Toast mToast;
    protected ToastEntity mToastEntity;

    public BaseIToast(Context context, ToastEntity toastEntity) {
        this.mContext = context;
        this.mToastEntity = toastEntity;
        createToast();
    }

    protected void createToast() {
        mToast = new Toast(mContext);
        mToast.setDuration(mToastEntity.toastTime);
        switch (mToastEntity.gravity) {
            case Gravity.BOTTOM:
                mToast.setGravity(Gravity.BOTTOM, mToast.getXOffset(), mToast.getYOffset());
                break;
            case Gravity.CENTER_VERTICAL:
                mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                break;
        }
        View view = inflaterToastView(mContext, mToast.getView(), mToastEntity);
        mToast.setView(view);
    }

    protected abstract View inflaterToastView(Context context, View view, ToastEntity toastEntity);

    @Override
    public Toast update(ToastEntity toastEntity) {
        inflaterToastView(mContext, mToast.getView(), toastEntity);
        mToast.show();
        return mToast;
    }

    @Override
    public void cancel() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }

    @Override
    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }
}
