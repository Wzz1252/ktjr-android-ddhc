package com.ktjr.ddgz.route.module;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ktjr.ddgz.route.url.RouterLoginUrl;
import com.ktjr.ddgz.route.url.RouterSalesUrl;

/**
 * 销售相关路由
 */
public class RouterLogin {

    /**
     * 销售首页
     */
    public static void startLoginModuleLoginActivity() {
        ARouter.getInstance().build(RouterLoginUrl.URL_LOGIN_LOGIN).navigation();
    }
}
