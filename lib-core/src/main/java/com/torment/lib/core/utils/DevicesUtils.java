package com.torment.lib.core.utils;

public class DevicesUtils {
//    /**
//     * 获取设备imsi号
//     */
//    public static String getImsi(Context context) {
//        String imeiString = "";
//
//        try {
//            TelephonyManager telephonyManager = (TelephonyManager) context
//                    .getSystemService(Context.TELEPHONY_SERVICE);
//            imeiString = telephonyManager.getSubscriberId();
//        } catch (Exception e) {
//            Log.d(TAG, "获取设备imsi号失败");
//        }
//
//        if (imeiString == null) {
//            return "";
//        }
//
//        return imeiString;
//    }
//    /**
//     * 获取设备imei号
//     */
//    public static String getImei(Context context) {
//        String imeiString = "";
//
//        try {
//            TelephonyManager telephonyManager = (TelephonyManager) context
//                    .getSystemService(Context.TELEPHONY_SERVICE);
//            imeiString = telephonyManager.getDeviceId();
//        } catch (Exception e) {
//            LogUtils.debug(TAG, "获取设备imei号失败");
//        }
//
//        if (imeiString == null) {
//            return "";
//        }
//
//        return imeiString;
//    }
//    /**
//     * 获取mac地址
//     */
//    public static String getMac(Context context) {
//        String mac = "";
//        try {
//            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//            WifiInfo info = wifi.getConnectionInfo();
//            mac = info.getMacAddress();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (mac == null) {
//            return "";
//        }
//
//        return mac;
//    }
//    /**
//     * 获取版本号
//     */
//    public static int getVersionCode(Context context) {
//        try {
//            PackageManager manager = context.getPackageManager();
//            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
//            return info.versionCode;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    /**
//     * 获取版本名称
//     */
//    public static String getVersionName(Context context) {
//        String versionName = "";
//        try {
//            // 获取软件版本名称
//            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return versionName;
//    }
}
