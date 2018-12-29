package com.ktjr.ddgz.ui;

import android.content.Intent;
import android.os.Bundle;

import com.ktjr.ddgz.ktjr_android_ddhc.R;
import com.ktjr.ddgz.login.ui.LoginActivity;
import com.ktjr.ddgz.route.module.RouterLogin;
import com.ktjr.ddgz.route.module.RouterSales;
import com.torment.lib.ui.app.AppActivity;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppActivity {
    private Toolbar mToolbar;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        mToolbar = getToolbar(findViewById(R.id.toolbar_layout), 0, false);
        mToolbar.setTitle("多多关照");

        findViewById(R.id.btn_test).setOnClickListener(v -> RouterSales.startSalesModuleHomeActivity());
        findViewById(R.id.btn_start).setOnClickListener(v ->
//                startActivity(new Intent(this, LoginActivity.class))
                RouterLogin.startLoginModuleLoginActivity()
        );
    }
}