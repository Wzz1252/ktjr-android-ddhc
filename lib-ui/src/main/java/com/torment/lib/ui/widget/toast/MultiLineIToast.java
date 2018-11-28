package com.torment.lib.ui.widget.toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.torment.lib.ui.R;
import com.torment.lib.ui.entity.toast.ToastEntity;

/**
 * 多行提示
 * Created by Torment on 2016/9/8.
 */
public class MultiLineIToast extends BaseIToast {
    private Toast mToast;

    public MultiLineIToast(Context context, ToastEntity toastEntity) {
        super(context, toastEntity);
    }

    protected View inflaterToastView(Context context, View view, ToastEntity toastEntity) {
        View inflaterView;
        if (view == null) {
            inflaterView = LayoutInflater.from(context).inflate(R.layout.toast_mult_line, null);
        } else {
            inflaterView = view;
        }
        TextView title = (TextView) inflaterView.findViewById(R.id.toast_title);
        TextView subTitle = (TextView) inflaterView.findViewById(R.id.toast_subtitle);
        if (toastEntity != null) {
            title.setText(toastEntity.title);
            subTitle.setText(toastEntity.subTitle);
        } else {
            throw new RuntimeException(context.getString(R.string.string_toast_inflater_exception));
        }
        return inflaterView;
    }
}