package com.bigpumpkin.app.ddng_android.utils;

import android.Manifest;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class PermissionsUtils {

    public static String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE};

    private static final int PERMISSION_REQUEST_CODE = 110;


    //请求权限
    public static void requestPermission(Activity context, String tip) {
        EasyPermissions.requestPermissions(context, tip, PermissionsUtils.PERMISSION_REQUEST_CODE
                , permissions);
    }

    public static void requestPermission(Fragment context, String tip) {
        EasyPermissions.requestPermissions(context, tip, PermissionsUtils.PERMISSION_REQUEST_CODE
                , permissions);
    }

    //拒绝权限后到设置界面去，让用户手动开启
    public static void SettingPermissions(Activity context, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(context, perms)) {
            new AppSettingsDialog.Builder(context).setTitle("提示").setRationale(
                    "幸运种子需要访问权限，请到“应用信息->权限”中设置!").build().show();
        }
    }

    public static void SettingPermissions(Fragment context, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(context, perms)) {
            new AppSettingsDialog.Builder(context).setTitle("手动设置权限").build().show();
        }
    }

}
