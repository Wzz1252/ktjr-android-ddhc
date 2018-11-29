package com.ktjr.ddgz.sales.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ktjr.ddgz.route.url.RouterSalesUrl;
import com.ktjr.ddgz.sales.R;
import com.torment.lib.ui.app.AppActivity;

import androidx.appcompat.widget.Toolbar;

@Route(path = RouterSalesUrl.URL_SALES_HOME)
public class HomeActivity extends AppActivity {
    private Toolbar mToolbar;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_sales_home;
    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        mToolbar = getToolbar(findViewById(R.id.toolbar_layout), 0, true);
        mToolbar.setTitle("销售首页");
    }
}
