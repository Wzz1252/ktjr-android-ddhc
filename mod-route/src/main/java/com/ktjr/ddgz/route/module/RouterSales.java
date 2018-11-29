package com.ktjr.ddgz.route.module;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ktjr.ddgz.route.url.RouterSalesUrl;

/**
 * 销售相关路由
 */
public class RouterSales {

    /**
     * 销售首页
     */
    public static void startSalesModuleHomeActivity() {
        ARouter.getInstance().build(RouterSalesUrl.URL_SALES_HOME).navigation();
    }
}
