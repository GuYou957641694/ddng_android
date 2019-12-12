package com.bigpumpkin.app.ddng_android.activity;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

public class HundredActivity extends BaseActivity {


    @Override
    public int intiLayout() {
        return R.layout.activity_hundred;
    }

    @Override
    public void initView() {
        String id = getIntent().getStringExtra("id");
        ToastUtil.showShort(this, id + "");
        new TitleXML(this, "智慧农场", true, "").init().setListener(new TitleXML.TitleXMLClick() {
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
