package com.bigpumpkin.app.ddng_android.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

public class AboutActivity extends BaseActivity {


    @Override
    public int intiLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        new TitleXML(AboutActivity.this, "关于", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        LinearLayout lv = findViewById(R.id.lv_share);
        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initData() {

    }
}
