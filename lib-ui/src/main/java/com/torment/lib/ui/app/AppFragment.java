package com.torment.lib.ui.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ktjr.ddgz.eventbus.manage.EventBusManager;
import com.torment.lib.ui.R;
import com.torment.lib.ui.adapter.AppAdapter;
import com.torment.lib.ui.cenum.AdapterStateEnum;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import me.jessyan.autosize.utils.LogUtils;

public abstract class AppFragment extends Fragment implements EventBusManager.SubscriberListener {
    public static final int SWIPE_REFRESH_COLOR = R.color.text_color;

    protected AdapterStateEnum mRefreshState = AdapterStateEnum.INIT;
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBusManager.getInstance().register(this);
        return setContentView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view, savedInstanceState);
    }

    protected abstract View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void setupViews(View view, Bundle savedInstanceState);

    protected Toolbar getToolbar(Toolbar toolbar, int menuResId, boolean isDisplayHomeAsUpEnabled) {
        if (toolbar == null) {
            throw new RuntimeException("Toolbar is null");
        }
        if (isDisplayHomeAsUpEnabled) {
            toolbar.setNavigationIcon(R.drawable.ic_navigation_back);
            toolbar.setNavigationOnClickListener(v -> {
                // TODO 通知父容器
            });
        }
        if (menuResId > 0) {
            toolbar.inflateMenu(menuResId);
            toolbar.setOnMenuItemClickListener(AppFragment.this::onMenuItemClick);
        }
        return toolbar;
    }

    protected Boolean onMenuItemClick(MenuItem item) {
        return true;
    }

    protected RecyclerView getLinearRecyclerView(RecyclerView recyclerView, int orientation, boolean isItemDecoration) {
        if (recyclerView == null) {
            throw new RuntimeException("RecyclerView is null");
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(orientation);
        recyclerView.setLayoutManager(layoutManager);
        if (isItemDecoration) {
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        }
        return recyclerView;
    }

    protected RecyclerView getLinearRecyclerView(RecyclerView recyclerView, int orientation) {
        return getLinearRecyclerView(recyclerView, orientation, false);
    }

    protected SwipeRefreshLayout getSwipeRefreshLayout(SwipeRefreshLayout swipeRefresh, boolean isRefresh, boolean isShowRefresh) {
        if (swipeRefresh == null) {
            throw new RuntimeException("SwipeRefreshLayout is null");
        }
        swipeRefresh.setColorSchemeResources(SWIPE_REFRESH_COLOR);
        if (isRefresh) {
            if (isShowRefresh) {
                openSwipeRefreshLayoutRefresh(swipeRefresh);
                this.initList();
            }
        }
        swipeRefresh.setOnRefreshListener(this::refreshList);
        return swipeRefresh;
    }

    protected SwipeRefreshLayout getSwipeRefreshLayout(SwipeRefreshLayout swipeRefresh, boolean isRefresh) {
        return getSwipeRefreshLayout(swipeRefresh, isRefresh, false);
    }

    protected SwipeRefreshLayout getSwipeRefreshLayout(SwipeRefreshLayout swipeRefresh) {
        return getSwipeRefreshLayout(swipeRefresh, false, false);
    }

    /**
     * 开启下拉滑动提示
     */
    protected void openSwipeRefreshLayoutRefresh(SwipeRefreshLayout swipeRefresh) {
        setSwipeRefreshLayoutRefreshState(swipeRefresh, true);
    }

    /**
     * 关闭下来滑动提示
     */
    protected void closeSwipeRefreshLayoutRefresh(SwipeRefreshLayout swipeRefresh) {
        setSwipeRefreshLayoutRefreshState(swipeRefresh, false);
    }

    protected void setSwipeRefreshLayoutRefreshState(SwipeRefreshLayout swipeRefresh, boolean isRefresh) {
        swipeRefresh.setRefreshing(isRefresh);
    }

    /**
     * 初始化方法
     * 如果当前布局需要刷新功能的话，可以重写此方法
     */
    protected void initList() {
        mRefreshState = AdapterStateEnum.INIT;
    }

    /**
     * 刷新方法
     * 如果当前布局需要刷新功能的话，可以重写此方法
     */
    protected void refreshList() {
        mRefreshState = AdapterStateEnum.REFRESH;
    }

    /**
     * 加载方法
     * 如果当前布局需要刷新功能的话，可以重写此方法
     */
    protected void loadList() {
        mRefreshState = AdapterStateEnum.LOAD;
    }

    /**
     * 更新数据的方法
     * 要传入一个adapter，data
     */
    protected <T> void updateRecycleViewData(@NonNull AppAdapter<T> adapter, List<T> data) {
        LogUtils.d("${AppV2Fragment.TAG} refreshState: $mRefreshState  adapter: $adapter  data: $data");
        if (mRefreshState == AdapterStateEnum.INIT) {
            // 数据为空
            if (data == null || data.isEmpty()) {
                adapter.switchToEmptyView();
            } else {
                adapter.setNewData(data);
            }
        } else if (mRefreshState == AdapterStateEnum.REFRESH) {
            if (data != null || data.size() != 0) {
                adapter.setNewData(data);
            }
        } else if (mRefreshState == AdapterStateEnum.LOAD) {
            if (data != null) {
                adapter.addData(data);
                if (data.size() < AppAdapter.ADAPTER_PAGE_SIZE) {
                    adapter.loadMoreEnd();
                } else {
                    adapter.loadMoreComplete();
                }
            } else {
                // TODO 此有争议
                adapter.loadMoreEnd();
            }
        }
    }

    /**
     * 如果想接受参数，重写即可
     */
    @Override
    public void onEventHandler(EventBusManager.MessageEvent event) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusManager.getInstance().unRegister(this);
    }
}
