package com.bigpumpkin.app.ddng_android.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    private static Toast mToast = null;


    public static void showShort(Context context, String info) {
        if (mToast == null) {
            mToast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(info);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showLong(Context context, String info) {
        if (mToast == null) {
            mToast = Toast.makeText(context, info, Toast.LENGTH_LONG);
        } else {
            mToast.setText(info);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

}
