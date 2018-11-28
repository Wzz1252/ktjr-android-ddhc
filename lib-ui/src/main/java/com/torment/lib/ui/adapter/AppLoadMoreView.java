package com.torment.lib.ui.adapter;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.torment.lib.ui.R;

public class AppLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.adapter_app_load_layout;
    }

    @Override
    protected int getLoadingViewId() {
        return 0;
    }

    @Override
    protected int getLoadFailViewId() {
        return 0;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }
}
