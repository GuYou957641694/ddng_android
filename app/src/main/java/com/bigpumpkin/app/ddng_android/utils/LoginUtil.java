package com.bigpumpkin.app.ddng_android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bigpumpkin.app.ddng_android.MainActivity;
import com.bigpumpkin.app.ddng_android.bean.User_Bean;

public class LoginUtil {

    private static LoginUtil mLoginUtil;

    private LoginUtil() {

    }

    public static LoginUtil getInstance() {
        if (null == mLoginUtil) {
            synchronized (LoginUtil.class) {
                if (null == mLoginUtil) {
                    mLoginUtil = new LoginUtil();
                }
            }
        }
        return mLoginUtil;
    }

    /**
     * 检查登录状态
     * 未登陆时跳转至登陆
     *
     * @param context
     * @return boolean
     */
    public boolean checkLoginStatus(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user",
                Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("zt", "");
        if (TextUtils.isEmpty(id.trim())) {
            ToastUtil.showShort(context, "请先登录");
            IntentUtils.getIntents().Intent(context, MainActivity.class, null);
            return false;
        } else {
            return true;
        }
    }
    public boolean checkLoginStatuss(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user",
                Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("zt", "");
        if (TextUtils.isEmpty(id.trim())) {
            ToastUtil.showShort(context, "请先登录");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 保存用户数据
     *
     * @param context
     * @param data    用户信息
     */
    public void saveData(Context context, User_Bean.DataBean data) {
        SharedPreferences.Editor editor = context.getSharedPreferences("user",
                Context.MODE_PRIVATE).edit();
        editor.putString("zt", data.getZt());
        editor.putString("pic", data.getPic());
        editor.putString("name", data.getName());
        editor.putString("integral", data.getIntegral());
        editor.putString("birth", data.getDate_birth());
        editor.apply();
    }


    /**
     * 获取用戶是否是场主
     *
     * @param context
     * @return 获取用戶是否是场主
     */
    public String getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user",
                Context.MODE_PRIVATE);
        return String.valueOf(sharedPreferences.getInt("zt", 0));
    }
}
