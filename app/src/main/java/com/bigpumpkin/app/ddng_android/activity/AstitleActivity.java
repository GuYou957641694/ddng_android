package com.bigpumpkin.app.ddng_android.activity;

import android.annotation.SuppressLint;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

public class AstitleActivity extends BaseActivity {

    @Override
    public int intiLayout() {
        return R.layout.activity_astitle;
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    public void initView() {
        String name = getIntent().getStringExtra("name");
        String uri = getIntent().getStringExtra("uri");
        new TitleXML(AstitleActivity.this, name, true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        WebView mWeb = findViewById(R.id.web);
        mWeb.loadUrl(uri);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.getSettings().setDomStorageEnabled(false);//开启dom缓存就好了,显示弹框
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.addJavascriptInterface(this, "xyzz");
    }

    @Override
    public void initData() {

    }
}
