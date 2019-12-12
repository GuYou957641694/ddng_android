package com.bigpumpkin.app.ddng_android.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.MyFarmBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class My_FarmActivity extends BaseActivity implements MyView {

    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private String appid;
    private String appsecret;
    private String sha1;
    private long time;
    private FrameLayout contentPanel;
    private List<String> urlList = new ArrayList<>();
    private int childCount = 0;
    private WebView mWeb;

    @Override
    public int intiLayout() {
        return R.layout.activity_my__farm;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        contentPanel = findViewById(R.id.framelayout_main);
        //首页
        SharedPreferences sharedPreferences = getSharedPreferences("user",
                Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("zt", "");
        if (TextUtils.isEmpty(id.trim())) {

        } else {
            time = System.currentTimeMillis();
            appid = SpzUtils.getString("appid");
            appsecret = SpzUtils.getString("appsecret");
            String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
            sha1 = EncryptUtils.getSHA(sha);
            map.put("appid", appid);
            map.put("appsecret", appsecret);
            map.put("timestamp", time);
            map.put("sign", sha1);
        }
    }

    @Override
    public void initData() {
        presenter.getpost(Contacts.frma, headmap, map, MyFarmBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof MyFarmBean) {
            MyFarmBean myFarmBean = (MyFarmBean) data;
            String data1 = myFarmBean.getData();
            addWeb(data1);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    private void addWeb(String url) {
        mWeb = new WebView(My_FarmActivity.this);
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

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // 加载完成
            mWeb.loadUrl("javascript:GetAppFun(" + appid + " ，" + appsecret + "，" + time + "，" + sha1 + ")");
        }
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

    @Override
    public void error(String error) {

    }

}
