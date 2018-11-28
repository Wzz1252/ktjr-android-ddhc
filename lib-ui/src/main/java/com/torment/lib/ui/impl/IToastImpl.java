package com.torment.lib.ui.impl;

import android.widget.Toast;

import com.torment.lib.ui.entity.toast.ToastEntity;

public interface IToastImpl {
    public void show();

    public void cancel();

    public Toast update(ToastEntity entity);
}
