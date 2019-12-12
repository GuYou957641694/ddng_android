package com.bigpumpkin.app.ddng_android.activity;

import android.webkit.WebView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

public class Up_RulesActivity extends BaseActivity {

    private String uri = "http://weilailingdi.weilailingdi.com/Web/Mall_index/xyzz/th/recharge_agreement.html";

    @Override
    public int intiLayout() {
        return R.layout.activity_up__rules;
    }

    @Override
    public void initView() {
        new TitleXML(Up_RulesActivity.this, "充值规则", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        WebView web = findViewById(R.id.web);
        web.loadUrl(uri);
    }

    @Override
    public void initData() {

    }
}
