package com.torment.lib.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.torment.lib.ui.R;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

public abstract class AppAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    public static int ADAPTER_PAGE_SIZE = 20;

    protected Context mAppContext;
    protected int mLoadingViewRes = R.layout.adapter_empty_loading;
    protected int mErrorViewRes = R.layout.adapter_empty_error;
    protected int mEmptyViewRes = R.layout.adapter_empty_data;

    public AppAdapter(Context context, int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        this.mAppContext = context;
        setLoadMoreView(getLoadMoreView());
    }

    /**
     * 重写 getLoadMoreView 实现默认的加载中布局
     */
    protected LoadMoreView getLoadMoreView() {
        return new AppLoadMoreView();
    }

    protected void setEmptyLoadingView(int resId) {
        this.mLoadingViewRes = resId;
    }

    protected void setEmptyErrorView(int resId) {
        this.mErrorViewRes = resId;
    }

    protected void setEmptyDataView(int resId) {
        this.mEmptyViewRes = resId;
    }

    protected void switchCurrentShowView(int resId) {
        setEmptyView(LayoutInflater.from(mAppContext).inflate(resId, null));
    }

    public void switchToLoadingView() {
        setEmptyView(LayoutInflater.from(mAppContext).inflate(mLoadingViewRes, null));
    }

    public void switchToErrorView() {
        setEmptyView(LayoutInflater.from(mAppContext).inflate(mErrorViewRes, null));
    }

    public void switchToEmptyView() {
        setEmptyView(LayoutInflater.from(mAppContext).inflate(mEmptyViewRes, null));
    }
}
