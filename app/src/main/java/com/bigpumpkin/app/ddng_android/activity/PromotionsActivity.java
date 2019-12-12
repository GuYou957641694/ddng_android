package com.bigpumpkin.app.ddng_android.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
//升官发财
public class PromotionsActivity extends BaseActivity {

    private String uri = "http://weilailingdi.weilailingdi.com/Web/Mall_index/xyzz/Make/make.html";
    private String appid;
    private String appsecret;
    private String sha1;
    private long time;
    private WebSettings webSettings = null;
    @Override
    public int intiLayout() {
        return R.layout.activity_promotions;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        WebView wbCertExam = findViewById(R.id.wb_id);
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
                wbCertExam.loadUrl("javascript:GetAppUser('" + appid + "','" + appsecret + "','" + time + "','" + sha1 + "')");
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
