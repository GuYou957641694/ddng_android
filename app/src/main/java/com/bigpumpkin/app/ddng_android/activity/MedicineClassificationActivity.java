package com.bigpumpkin.app.ddng_android.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

public class MedicineClassificationActivity extends BaseActivity {

    @Override
    public int intiLayout() {
        return R.layout.activity_medicine_classification;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void initView() {
        new TitleXML(MedicineClassificationActivity.this, "分类", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        String link = getIntent().getStringExtra("link");
        WebView mWeb = findViewById(R.id.wb);
        mWeb.loadUrl(link);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.getSettings().setDomStorageEnabled(false);//开启dom缓存就好了,显示弹框
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.addJavascriptInterface(this, "xyzz");
    }

    //商品详情
    @JavascriptInterface
    public void goodsDetails(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        IntentUtils.getIntents().Intent(this, Spell_DetailsActivity.class, bundle);
    }

    @Override
    public void initData() {

    }
}
