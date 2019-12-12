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
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;

public class HauntedActivity extends BaseActivity {

    private String uri = "http://weilailingdi.weilailingdi.com/Web/Mall_index/xyzz/animation/index3.html";
    private WebSettings webSettings = null;

    @Override
    public int intiLayout() {
        return R.layout.activity_haunted;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        WebView wbCertExam = findViewById(R.id.web);
        wbCertExam.loadUrl(uri);
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
                //  wbCertExam.loadUrl("javascript:GetAppUser('" + appid + "','" + appsecret + "','" + time + "','" + sha1 + "')");
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
