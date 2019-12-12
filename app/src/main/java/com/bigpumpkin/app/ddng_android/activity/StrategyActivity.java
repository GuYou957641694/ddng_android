package com.bigpumpkin.app.ddng_android.activity;

import android.webkit.WebView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

public class StrategyActivity extends BaseActivity {

    private String uri = "http://weilailingdi.weilailingdi.com/Web/Mall_index/xyzz/th/raiders.html";

    @Override
    public int intiLayout() {
        return R.layout.activity_strategy;
    }

    @Override
    public void initView() {
        new TitleXML(StrategyActivity.this, "攻略", true).init().setListener(new TitleXML.TitleXMLClick() {
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
