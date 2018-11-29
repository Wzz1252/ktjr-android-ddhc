package com.ktjr.ddgz.login.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ktjr.ddgz.login.R;
import com.ktjr.ddgz.route.url.RouterLoginUrl;
import com.torment.lib.ui.app.AppActivity;

import androidx.appcompat.widget.Toolbar;

@Route(path = RouterLoginUrl.URL_LOGIN_LOGIN)
public class LoginActivity extends AppActivity {
    private Toolbar mToolbar;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        mToolbar = getToolbar(findViewById(R.id.toolbar_layout), 0, true);
        mToolbar.setTitle("登录");
    }
}
