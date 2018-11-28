package com.torment.lib.core.utils;

import java.text.DecimalFormat;

/**
 * 格式化工具类
 * Created by Torment on 2016/8/10.
 */
public class FormatUtils {

    /**
     * 价格格式化，小数点后两位
     *
     * @param price
     * @return
     */
    public static String formatPrice(String price) {
        double priceNum = 0;
        try {
            priceNum = Double.parseDouble(price);
        } catch (Exception e) {
            priceNum = 0;
        }
        return formatPrice(priceNum);
    }

    public static String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        return df.format(price);
    }
}
