package com.torment.lib.ui.widget.toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.torment.lib.ui.R;
import com.torment.lib.ui.entity.toast.ToastEntity;

/**
 * 标准提示 图标带文字
 * Created by Torment on 2016/9/7.
 */
public class StandardIToast extends BaseIToast {

    public StandardIToast(Context context, ToastEntity toastEntity) {
        super(context, toastEntity);
    }

    /**
     * 填充Toast的自定义布局
     *
     * @param context
     * @param view
     * @param modth
     * @return
     */
    protected View inflaterToastView(Context context, View view, ToastEntity modth) {
        View inflaterView;
        if (view == null) {
            inflaterView = LayoutInflater.from(context).inflate(R.layout.toast_standard, null);
        } else {
            inflaterView = view;
        }
        TextView title = (TextView) inflaterView.findViewById(R.id.toast_title);
        ImageView icon = (ImageView) inflaterView.findViewById(R.id.toast_icon);
        if (modth != null) {
            title.setText(modth.title);
            icon.setImageResource(modth.iconResId);
            if (modth.titleColor > 0) {
                title.setTextColor(modth.titleColor);
            }
        } else {
            throw new RuntimeException(context.getString(R.string.string_toast_inflater_exception));
        }
        return inflaterView;
    }
}