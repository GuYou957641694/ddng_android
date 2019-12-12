package com.bigpumpkin.app.ddng_android.activity;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;

import qiu.niorgai.StatusBarCompat;

//开发票
public class InvoiceActivity extends BaseActivity {

    @Override
    public int intiLayout() {
        return R.layout.activity_invoice;
    }

    @Override
    public void initView() {


        StatusBarCompat.translucentStatusBar(this);
    }

    @Override
    public void initData() {

    }
}
