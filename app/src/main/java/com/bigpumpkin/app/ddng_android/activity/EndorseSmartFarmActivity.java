package com.bigpumpkin.app.ddng_android.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;

//看人选园
public class EndorseSmartFarmActivity extends BaseActivity {

    private String url = "http://weilailingdi.weilailingdi.com/Web/Mall_index/xyzz/module/mysettled.html";
    private WebSettings webSettings = null;
    @Override
    public int intiLayout() {
        return R.layout.activity_endorse_smart_farm;
    }

    @Override
    public void initView() {
        WebView wbCertExam = findViewById(R.id.wb_id);
        wbCertExam.loadUrl(url);
        wbCertExam.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        webSettings = wbCertExam.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(false);
        wbCertExam.setWebChromeClient(client);
        wbCertExam.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 加载完成
               // wbCertExam.loadUrl("javascript:GetAppUser('" + appid + "','" + appsecret + "','" + time + "','" + sha1 + "')");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // 加载开始5
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
    }
    WebChromeClient client = new WebChromeClient() {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }
    };

    @Override
    public void initData() {

    }
}
