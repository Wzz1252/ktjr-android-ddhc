package com.torment.lib.ui.widget.tab;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.torment.lib.ui.R;
import com.torment.lib.ui.entity.tab.TabEntity;

/**
 * TabLayout中的每个布局
 * Created by Torment on 2016/9/27.
 */
public class TabView extends RelativeLayout {
    private TextView mTitle;
    private ImageView mIcon;
    private TextView mRedPoint;

    private TabEntity mTabEntity;

    public TabView(Context context) {
        super(context);
        init(context);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.tab_view, this);

        mTitle = (TextView) findViewById(R.id.title);
        mIcon = (ImageView) findViewById(R.id.icon);
        mRedPoint = (TextView) findViewById(R.id.red_point);

    }

    public void setUpData(TabEntity tab) {
        mTitle.setText(tab.titleResId);
        mIcon.setImageResource(tab.iconResId);

        if (tab.redPoint <= 0) {
            mRedPoint.setVisibility(GONE);
        } else {
            mRedPoint.setVisibility(VISIBLE);
            mRedPoint.setText(tab.redPoint + "");
        }
    }

    public ImageView getIconView() {
        return mIcon;
    }

    public void setUpTabViewStyle(int checkedFilter, int checkedFilterTitle) {
        if (checkedFilter != -1) {
            mIcon.setColorFilter(getContext().getResources().getColor(checkedFilter), PorterDuff.Mode.MULTIPLY);
        }
        mTitle.setTextColor(getContext().getResources().getColor(checkedFilterTitle));
    }

    public void setUpTabViewRedPoint(TabEntity tab) {
        if (tab == null) {
            throw new RuntimeException("传入的Tab为NULL");
        }
        mTabEntity = tab;
        if (mTabEntity.redPoint <= 0) {
            mRedPoint.setVisibility(GONE);
        } else {
            mRedPoint.setVisibility(VISIBLE);
            mRedPoint.setText(String.valueOf(mTabEntity.redPoint));
        }
    }
}