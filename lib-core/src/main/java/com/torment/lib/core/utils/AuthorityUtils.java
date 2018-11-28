package com.torment.lib.core.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;

import androidx.core.app.ActivityCompat;

/**
 * 权限管理
 * Created by Torment on 2016/6/23.
 */
public class AuthorityUtils {

    /**
     * 权限检查及申请
     *
     * @param activity
     * @param permissions
     * @param requestCode
     * @return
     */
    public static boolean permissionChecksRequest(Activity activity, String[] permissions, int requestCode) {
        // 判断传入的权限是否全部通过
        boolean isPermission = false;

        // 拒绝的权限下标
        ArrayList<String> refusePermissionIndex = new ArrayList<>();

        // 只有当API>23时，才检查权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        // 检查权限
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                refusePermissionIndex.add(permission);
            }
        }

        // 申请权限
        if (refusePermissionIndex.size() <= 0) {
            isPermission = true;
        } else {
            ActivityCompat.requestPermissions(activity, refusePermissionIndex.toArray(new String[0]), requestCode);
        }
        return isPermission;
    }

    public static boolean permissionChecks(Activity activity, String permissions, int requestCode) {
        // 判断传入的权限是否全部通过
        boolean isPermission = false;

        // 拒绝的权限下标
        ArrayList<String> refusePermissionIndex = new ArrayList<>();

        // 只有当API>23时，才检查权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        // 检查权限
        if (ActivityCompat.checkSelfPermission(activity, permissions)
                == PackageManager.PERMISSION_DENIED) {
            // 权限失败
            return false;
        }
        return true;
    }
}
