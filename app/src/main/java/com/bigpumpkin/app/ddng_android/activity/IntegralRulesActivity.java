package com.bigpumpkin.app.ddng_android.activity;

import android.webkit.WebView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

public class IntegralRulesActivity extends BaseActivity {

    private String uri = "http://weilailingdi.weilailingdi.com/Web/Mall_index/xyzz/th/rintegral_rule.html";

    @Override
    public int intiLayout() {
        return R.layout.activity_integral_rules;
    }

    @Override
    public void initView() {
        new TitleXML(IntegralRulesActivity.this, "积分规则", true).init().setListener(new TitleXML.TitleXMLClick() {
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
