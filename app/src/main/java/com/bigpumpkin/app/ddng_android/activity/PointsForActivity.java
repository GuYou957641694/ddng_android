package com.bigpumpkin.app.ddng_android.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;

import qiu.niorgai.StatusBarCompat;

//幸运转盘
public class PointsForActivity extends BaseActivity {

    private String appid;
    private String appsecret;
    private String sha1;
    private long time;
    private WebSettings webSettings = null;

    @Override
    public int intiLayout() {
        return R.layout.activity_points_for;
    }

    @Override
    public void initView() {
        StatusBarCompat.translucentStatusBar(this);
        String link = getIntent().getStringExtra("link");
        WebView wbCertExam = findViewById(R.id.web);
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
        wbCertExam.loadUrl(Urls.BASEURL + link);
        webSettings = wbCertExam.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(false);
        wbCertExam.setOverScrollMode(View.OVER_SCROLL_NEVER);//自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        wbCertExam.setHorizontalScrollBarEnabled(false);//水平不显示
        wbCertExam.setVerticalScrollBarEnabled(false); //垂直不显示
        wbCertExam.setWebChromeClient(client);
        wbCertExam.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 加载完成
                wbCertExam.loadUrl("javascript:Rotary('" + appid + "','" + appsecret + "','" + time + "','" + sha1 + "')");
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
