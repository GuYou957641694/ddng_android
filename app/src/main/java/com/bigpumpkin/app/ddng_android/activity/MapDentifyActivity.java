package com.bigpumpkin.app.ddng_android.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.hjq.toast.ToastUtils;

public class MapDentifyActivity extends BaseActivity {

    private String url = "http://weilailingdi.weilailingdi.com/Web/Mall_index/xyzz/th/map.html";
    private WebSettings webSettings = null;

    @Override
    public int intiLayout() {
        return R.layout.activity_map_dentify;
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
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
        webSettings.setDomStorageEnabled(false);
        wbCertExam.setWebChromeClient(new WebChromeClient());
        wbCertExam.addJavascriptInterface(this, "xyzz");
        wbCertExam.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 加载完成
             //   wbCertExam.loadUrl("javascript:GetAppUser('" + appid + "','" + appsecret + "','" + time + "','" + sha1 + "')");
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


    //产区
    @JavascriptInterface
    public void initrRegione(String name) {
        ToastUtils.show(name);
    }
    @Override
    public void initData() {

    }
}
