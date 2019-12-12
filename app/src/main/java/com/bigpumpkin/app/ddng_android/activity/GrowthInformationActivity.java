package com.bigpumpkin.app.ddng_android.activity;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

public class GrowthInformationActivity extends BaseActivity {


    @Override
    public int intiLayout() {
        return R.layout.activity_growth_information;
    }

    @Override
    public void initView() {
        new TitleXML(GrowthInformationActivity.this, "信息", true).init().setListener(new TitleXML.TitleXMLClick() {
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
