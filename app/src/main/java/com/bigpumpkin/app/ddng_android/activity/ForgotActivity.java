package com.bigpumpkin.app.ddng_android.activity;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;

public class ForgotActivity extends BaseActivity {

    @Override
    public int intiLayout() {
        return R.layout.activity_forgot;
    }

    @Override
    public void initView() {
        String mobile = getIntent().getStringExtra("mobile");
    }

    @Override
    public void initData() {

    }
}
