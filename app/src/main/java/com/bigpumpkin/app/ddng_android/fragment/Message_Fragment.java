package com.bigpumpkin.app.ddng_android.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;

public class Message_Fragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.message_fragment;
    }

    @Override
    protected void init(View view) {
        setStatusBarColor(getActivity(), Color.WHITE);
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        setStatusBarColor(getActivity(), Color.WHITE);
    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //状态栏改变颜色。
            window.setStatusBarColor(color);
        }
    }
}
