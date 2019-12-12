package com.bigpumpkin.app.ddng_android.activity;

import android.webkit.WebView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

public class PoultryRulesActivity extends BaseActivity {

    private String uri = "http://weilailingdi.weilailingdi.com/Web/Mall_index/xyzz/th/adoption_rules.html";

    @Override
    public int intiLayout() {
        return R.layout.activity_poultry_rules;
    }

    @Override
    public void initView() {
        new TitleXML(PoultryRulesActivity.this, "规则", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        WebView webView = findViewById(R.id.web);
        webView.loadUrl(uri);
    }

    @Override
    public void initData() {

    }
}
