package com.torment.lib.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.torment.lib.ui.R;
import com.torment.lib.ui.widget.appview.AppEditText;
import com.torment.lib.ui.widget.appview.AppImageView;
import com.torment.lib.ui.widget.appview.AppLinearLayout;

import java.util.Objects;

/**
 * 关照标准输入框
 */
public class StandardEditText extends AppLinearLayout {
    private int mLeftIconRes;
    private int mRightIconRes;
    private String mText;
    private String mHint;
    private boolean mIsClean;

    private AppLinearLayout mRootView;
    private AppImageView mLeftIconView;
    private AppImageView mRightIconView;
    private AppEditText mEditTextView;
    private AppImageView mCleanIconView;

    public StandardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        @SuppressLint("CustomViewStyleable")
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.StandardEditView, 0, 0);
        mLeftIconRes = arr.getResourceId(R.styleable.StandardEditView_left_icon, 0);
        mRightIconRes = arr.getResourceId(R.styleable.StandardEditView_right_icon, 0);
        mText = arr.getString(R.styleable.StandardEditView_text);
        mHint = arr.getString(R.styleable.StandardEditView_hint);
        mIsClean = arr.getBoolean(R.styleable.StandardEditView_is_clean, false);
        arr.recycle();

        mRootView = (AppLinearLayout) LayoutInflater.from(context).inflate(R.layout.view_standard_edit_text, this, true);
        mLeftIconView = mRootView.findViewById(R.id.left_icon);
        mRightIconView = mRootView.findViewById(R.id.right_icon);
        mEditTextView = mRootView.findViewById(R.id.edit_text);
        mCleanIconView = mRootView.findViewById(R.id.clean_icon);

        mEditTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!mIsClean) {
                    return;
                }
                if (mCleanIconView != null) {
                    mCleanIconView.setVisibility("".contentEquals(s) ? GONE : VISIBLE);
                }
            }
        });

        mCleanIconView.setVisibility(mIsClean ? VISIBLE : GONE);
        mCleanIconView.setOnClickListener(v -> {
            if (mEditTextView != null) {
                mEditTextView.setText("");
                mCleanIconView.setVisibility(GONE);
            }
        });

        setLeftIcon(mLeftIconRes);
        setRightIcon(mRightIconRes);
        setText(mText);
        setHint(mHint);
    }

    public void setLeftIcon(int resId) {
        if (mLeftIconView != null) {
            mLeftIconView.setVisibility(resId <= 0 ? GONE : VISIBLE);
            mLeftIconView.setBackgroundResource(resId);
        }
    }

    public void setRightIcon(int resId) {
        if (mRightIconView != null) {
            mRightIconView.setVisibility(resId <= 0 ? GONE : VISIBLE);
            mRightIconView.setBackgroundResource(resId);
        }
    }

    public void setText(String text) {
        if (mEditTextView != null) {
            mEditTextView.setText(text);
        }
    }

    public String getText() {
        if (mEditTextView == null) {
            return "";
        }
        return Objects.requireNonNull(mEditTextView.getText()).toString();
    }

    public void setHint(String hint) {
        if (mEditTextView != null) {
            mEditTextView.setHint(hint);
        }
    }
}
