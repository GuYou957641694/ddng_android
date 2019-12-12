package com.bigpumpkin.app.ddng_android.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.bean.MyFarmBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;

public class My_Farm_Fragment extends BaseFragment implements MyView {

    private static final String TAG = "My_Farm_Fragment";
    WebView wbCertExam;
    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private String appid;
    private String appsecret;
    private String sha1;
    private long time;
    private WebSettings webSettings = null;

    @Override
    protected int getLayoutId() {
        return R.layout.my_farm_fragment;
    }

    @Override
    protected void init(View view) {
        presenter = new MyPresenterImpl(this);
        wbCertExam = view.findViewById(R.id.mywebview);
        setStatusBarColor(getActivity(), Color.WHITE);
        headmap = new HashMap<>();
        map = new HashMap<>();
        //首页
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user",
                Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("zt", "");
        if (TextUtils.isEmpty(id.trim())) {
            presenter.get(Contacts.frma, headmap, map, MyFarmBean.class);
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
            presenter.getpost(Contacts.frma, headmap, map, MyFarmBean.class);
        }

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        setStatusBarColor(getActivity(), Color.WHITE);
        map.clear();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user",
                Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("zt", "");
        if (TextUtils.isEmpty(id.trim())) {
            presenter.get(Contacts.frma, headmap, map, MyFarmBean.class);
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
            presenter.getpost(Contacts.frma, headmap, map, MyFarmBean.class);
        }
    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //状态栏改变颜色。
            window.setStatusBarColor(color);
        }
    }


    @Override
    public void success(Object data) {
        if (data instanceof MyFarmBean) {
            MyFarmBean myFarmBean = (MyFarmBean) data;
            String data1 = myFarmBean.getData();
            wbCertExam.loadUrl(data1);
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
                    wbCertExam.loadUrl("javascript:GetAppFun(" + appid + " ，" + appsecret + "，" + time + "，" + sha1 + ")");
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    // 加载开始5
                }
            });
        }
    }

    WebChromeClient client = new WebChromeClient() {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }
    };

    @Override
    public void error(String error) {
    }


}
