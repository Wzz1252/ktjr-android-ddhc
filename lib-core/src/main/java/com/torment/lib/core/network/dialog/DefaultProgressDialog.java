package com.torment.lib.core.network.dialog;

import android.app.ProgressDialog;
import android.content.Context;

import com.torment.lib.core.impl.DialogProgressImpl;

/**
 * 使用系统模式的Dialog作为加载中的样式
 * Created by Torment on 2016/9/30.
 */
public class DefaultProgressDialog implements DialogProgressImpl {
    private ProgressDialog sLoadingDialog;
    private Context mContext;
    private String mMessage;

    public DefaultProgressDialog(Context context, String message) {
        this.mContext = context;
        this.mMessage = message;
    }

    /**
     * 显示Dialog
     */
    public void showLoadingDialog() {
        if (!isLoadingDialogShowing()) {
            sLoadingDialog = ProgressDialog.show(mContext, "", mMessage);
        } else {
            sLoadingDialog.setMessage(mMessage);
        }
        sLoadingDialog.show();
    }

    /**
     * 关闭当前显示的Dialog
     */
    public void closeLoadingDialog() {
        if (isLoadingDialogShowing()) {
            try {
                sLoadingDialog.dismiss();
                sLoadingDialog = null;
            } catch (Exception e) {
                sLoadingDialog = null;
            }
        }
    }

    /**
     * 判断当前Dialog是否为显示状态
     */
    public boolean isLoadingDialogShowing() {
        if (sLoadingDialog != null && sLoadingDialog.isShowing()) {
            return true;
        }
        return false;
    }

    @Override
    public void showDialog() {
        showLoadingDialog();
    }

    @Override
    public void hideDialog() {
        closeLoadingDialog();
    }
}
