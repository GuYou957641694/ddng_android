package com.bigpumpkin.app.ddng_android.activity;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

public class PaySuccessActivity extends BaseActivity {


    @Override
    public int intiLayout() {
        return R.layout.activity_pay_success;
    }

    @Override
    public void initView() {
        new TitleXML(PaySuccessActivity.this, "支付成功", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });


    }

    @Override
    public void initData() {

    }
}
