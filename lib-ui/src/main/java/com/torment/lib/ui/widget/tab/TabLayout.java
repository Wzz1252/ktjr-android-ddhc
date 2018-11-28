package com.torment.lib.ui.widget.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.torment.lib.ui.entity.tab.TabEntity;
import com.torment.lib.ui.impl.IFragmentTab;
import com.torment.lib.ui.impl.OnTabClickListener;

import java.util.List;

import androidx.fragment.app.FragmentManager;

/**
 * 封装好的底部的导航栏
 * Created by Torment on 2016/9/27.
 */
public class TabLayout extends LinearLayout implements View.OnClickListener {
    private LinearLayout mBaseLayout;
    private OnTabClickListener mOnTabClickListeners;

    private List<TabEntity> mTabEntityList;

    private int mCheckedFilter = -1;
    private int mUnCheckedFilter = -1;
    private int mCheckedFilterTitle = -1;
    private int mUnCheckedFilterTitle = -1;

    private int mSelectPostion;
    private int mDefaultSelectPostion = 0;
    private int mLastSelectPostion = mDefaultSelectPostion;


    public TabLayout(Context context) {
        super(context);
        init(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
    }

    public void setUpData(List<TabEntity> tabs, FragmentManager fragmentManager, int fragmentId) {
        setUpData(tabs, new IFragmentTab(fragmentManager, fragmentId));
    }

    public void setUpData(List<TabEntity> tabs, OnTabClickListener listener) {
        this.mTabEntityList = tabs;
        this.mOnTabClickListeners = listener;

        if (getChildCount() > 0) {
            removeAllViews();
        }

        boolean isClick = false;
        if (listener == null) {
            throw new RuntimeException("OnTabClickListener is null");
        }
        mOnTabClickListeners = listener;
        isClick = true;

        if (mTabEntityList == null || mTabEntityList.size() == 0) {
            throw new RuntimeException("tabs is null");
        }

        for (int i = 0; i < mTabEntityList.size(); i++) {
            TabEntity tab = mTabEntityList.get(i);
            TabView tabView = new TabView(getContext());
            tabView.setTag(i);
            tabView.setUpData(tab);
            LayoutParams lp = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;
            lp.gravity = Gravity.CENTER;

            if (isClick) {
                tabView.setOnClickListener(this);
            }

            int filterRes = mDefaultSelectPostion == i ? mCheckedFilter : mUnCheckedFilter;
            int filterTitleRes = mDefaultSelectPostion == i ? mCheckedFilterTitle : mUnCheckedFilterTitle;
            int iconRes = mDefaultSelectPostion == i ? tab.iconResId : tab.iconSelectResId;
            tabView.getIconView().setImageResource(iconRes);
            tabView.setUpTabViewStyle(filterRes, filterTitleRes);

            if (mDefaultSelectPostion == i) {
                mOnTabClickListeners.tabClick(tab, i);
            }
            addView(tabView, lp);
        }
    }

    public void setCheckedFilter(int filterResId) {
        this.mCheckedFilter = filterResId;
    }

    public void setUnCheckedFilter(int unFilterResId) {
        this.mUnCheckedFilter = unFilterResId;
    }

    public void setCheckedFilterTitle(int filterResId) {
        this.mCheckedFilterTitle = filterResId;
    }

    public void setUnCheckedFilterTitle(int unFilterResId) {
        this.mUnCheckedFilterTitle = unFilterResId;
    }

    @Override
    public void onClick(View view) {
        int index = (int) view.getTag();
        mSelectPostion = index;

        if (mSelectPostion == mLastSelectPostion) {
            return;
        }

        setUpdateTabView(mSelectPostion, mCheckedFilter, mCheckedFilterTitle,
                mTabEntityList.get(mSelectPostion).iconResId);
        if (mLastSelectPostion != -1) {
            setUpdateTabView(mLastSelectPostion, mUnCheckedFilter, mUnCheckedFilterTitle,
                    mTabEntityList.get(mLastSelectPostion).iconSelectResId);
        }
        mLastSelectPostion = mSelectPostion;

        TabEntity tab = mTabEntityList.get(mSelectPostion);
        mOnTabClickListeners.tabClick(tab, mSelectPostion);

        // setUpTabViewRedPoint(mSelectPostion, tab);
    }

    public void setUpdateTabView(int postion, int checkedFilter, int checkedFilterTitle, int iconResId) {
        TabView tabView = getTabView(postion);
        tabView.setUpTabViewStyle(checkedFilter, checkedFilterTitle);
        tabView.getIconView().setImageResource(iconResId);
    }

    public void setUpTabViewRedPoint(int postion, TabEntity tab) {
        TabView tabView = getTabView(postion);
        tabView.setUpTabViewRedPoint(tab);
    }

    public TabView getTabView(int postion) {
        View childView = getChildAt(postion);
        TabView tabView = null;
        if (childView instanceof TabView) {
            tabView = (TabView) childView;
        }

        if (tabView == null) {
            throw new RuntimeException("tabView is null");
        }
        return tabView;
    }
}