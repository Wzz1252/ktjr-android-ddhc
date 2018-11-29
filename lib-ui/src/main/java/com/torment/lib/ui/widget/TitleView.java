package com.torment.lib.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.torment.lib.ui.R;
import com.torment.lib.ui.widget.appview.AppLinearLayout;

import androidx.annotation.Nullable;

public class TitleView extends AppLinearLayout {

    private String mTitle;
    private View mRootView;
    private TextView mTitleView;

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView, 0, 0);
        mTitle = typedArray.getString(R.styleable.TitleView_title);
        typedArray.recycle();

        mRootView = LayoutInflater.from(context).inflate(R.layout.view_title, this, true);
        mTitleView = mRootView.findViewById(R.id.title);
        mTitleView.setText(mTitle);
    }
}
