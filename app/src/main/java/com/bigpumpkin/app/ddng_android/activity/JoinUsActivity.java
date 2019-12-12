package com.bigpumpkin.app.ddng_android.activity;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;

import java.util.ArrayList;
import java.util.List;

public class JoinUsActivity extends BaseActivity {

    String url = "http://weilailingdi.weilailingdi.com/Web/Mall_index/xyzz/module/endorsement.html";
    private FrameLayout contentPanel;
    private List<String> urlList = new ArrayList<>();
    private int childCount = 0;

    @Override
    public int intiLayout() {
        return R.layout.activity_join_us;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        contentPanel = findViewById(R.id.framelayout_main);
        addWeb(url);
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    private void addWeb(String url) {
        WebView mWeb = new WebView(JoinUsActivity.this);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.getSettings().setDomStorageEnabled(false);
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.setWebViewClient(new MyWebViewClient());
        mWeb.addJavascriptInterface(this, "xyzz");
        mWeb.loadUrl(url);
        contentPanel.addView(mWeb);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        mWeb.setLayoutParams(params);
    }

    //截获跳转
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!urlList.contains(url)) {
                addWeb(url);
                urlList.add(url);
                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }
    }
    @Override
    public void initData() {

    }

    @Override
    public void onBackPressed() {
        childCount = contentPanel.getChildCount();
        if (childCount > 1) {
            contentPanel.removeViewAt(childCount - 1);
            urlList.remove(urlList.size() - 1);
        } else {
            super.onBackPressed();
        }
    }
}
