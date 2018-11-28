package com.torment.lib.ui.widget.toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.torment.lib.ui.R;
import com.torment.lib.ui.entity.toast.ToastEntity;

/**
 * 弱提示
 * Created by Torment on 2016/9/7.
 */
public class WeakIToast extends BaseIToast {

    public WeakIToast(Context context, ToastEntity toastEntity) {
        super(context, toastEntity);
    }

    protected View inflaterToastView(Context context, View view, ToastEntity toastEntity) {
        View inflaterView;
        if (view == null) {
            inflaterView = LayoutInflater.from(context).inflate(R.layout.toast_weak, null);
        } else {
            inflaterView = view;
        }
        TextView title = (TextView) inflaterView.findViewById(R.id.toast_title);
        if (toastEntity != null) {
            title.setText(toastEntity.title);
            if (toastEntity.titleColor != 0) {
                title.setTextColor(toastEntity.titleColor);
            }
            if (toastEntity.titleSize > 0) {
                title.setTextSize(toastEntity.titleSize);
            }
        } else {
            throw new RuntimeException(context.getString(R.string.string_toast_inflater_exception));
        }
        return inflaterView;
    }
}