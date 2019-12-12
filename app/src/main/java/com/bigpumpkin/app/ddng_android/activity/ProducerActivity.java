package com.bigpumpkin.app.ddng_android.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.AddShoppingBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.LoginUtil;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProducerActivity extends BaseActivity implements MyView {

    private String link;
    private String sha1, appid, appsecret;
    private long time;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private FrameLayout contentPanel;
    private List<String> urlList = new ArrayList<>();
    private int childCount = 0;


    @Override
    public int intiLayout() {
        return R.layout.activity_producer;
    }


    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        contentPanel = findViewById(R.id.framelayout_main);
        link = getIntent().getStringExtra("link");
        //wbCertExam = findViewById(R.id.web_id);
        String str = "http://weilailingdi.weilailingdi.com/" + link;
        Log.d(TAG, "initView: " + link);
        //这里需要判断是不是积分商城 这里需要登录
        if (link.equals("/Web/Mall_index/xyzz/integral_mall/home.html")) {
            if (!LoginUtil.getInstance().checkLoginStatus(this)) {
                finish();
                return;
            }
        }
        //判断我来代言需要跳转别的页面
        if (link.equals("/Web/Mall_index/xyzz/module/mysettled.html")) {
            IntentUtils.getIntents().Intent(this, Farm_inActivity.class, null);
            return;
        }
        addWeb(str);

        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();

    }

    @Override
    public void initData() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void addWeb(String url) {
        WebView mWeb = new WebView(ProducerActivity.this);
        mWeb.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mWeb.setHorizontalScrollBarEnabled(false);//水平不显示
        mWeb.setVerticalScrollBarEnabled(false); //垂直不显示
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

    //农场首页
    @JavascriptInterface
    public void initFarmHome(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        IntentUtils.getIntents().Intent(this, FarmActivity.class, bundle);
    }

    //更多农场
    @JavascriptInterface
    public void initFarmMore(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        IntentUtils.getIntents().Intent(this, FarmMoreActivity.class, bundle);
    }

    //视频播放
    @JavascriptInterface
    public void initVideo(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        IntentUtils.getIntents().Intent(this, VideoActivity.class, bundle);
    }

    //更多视频播放
    @JavascriptInterface
    public void initVideoMore(String id, String name) {
        //果农说1植物认养（果农说） 2家禽认养 4药食同源（种果人说） 5公益放生（发起人说） 6生产者说
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("name", name);
        IntentUtils.getIntents().Intent(this, GrowerActivity.class, bundle);

    }

    //搜索页面
    @JavascriptInterface
    public void medicineSearch() {
        IntentUtils.getIntents().Intent(this, HomeSearchActivity.class, null);
    }

    //产区
    @JavascriptInterface
    public void medicineRegion(String title, String link, String id, String type) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("link", link);
        bundle.putString("id", id);
        bundle.putString("type", type);
        IntentUtils.getIntents().Intent(this, Custom_SelectionActivity.class, bundle);
    }

    //商品详情
    @JavascriptInterface
    public void goodsDetails(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        IntentUtils.getIntents().Intent(this, Spell_DetailsActivity.class, bundle);
    }

    //药食同源分类跳页面
    @JavascriptInterface
    public void classification(String link) {
        Bundle bundle = new Bundle();
        bundle.putString("link", link);
        IntentUtils.getIntents().Intent(this, MedicineClassificationActivity.class, bundle);
    }

    //公共H5页面+标题
    @JavascriptInterface
    public void BasePublic(String name, String uri) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("uri", uri);
        IntentUtils.getIntents().Intent(this, AstitleActivity.class, bundle);
    }

    //返回
    @JavascriptInterface
    public void initfinish() {
        finish();
    }


    //加入购物车
    @JavascriptInterface
    public void initAddCart(String gg_id) {
        if (!LoginUtil.getInstance().checkLoginStatus(ProducerActivity.this)) {
            return;
        }
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        map.put("num", "1");
        map.put("gg_id", gg_id);
        presenter.getpost(Contacts.add_shopping_carts, headmap, map, AddShoppingBean.class);
    }

    //积分明细
    @JavascriptInterface
    public void initMyPoints() {
        IntentUtils.getIntents().Intent(this, IntegralActivity.class, null);
    }

    //幸运转盘
    @JavascriptInterface
    public void initRotary(String link) {
        Bundle bundle = new Bundle();
        bundle.putString("link", link);
        IntentUtils.getIntents().Intent(this, PointsForActivity.class, bundle);
    }

    //生产者说产区甄选
    @JavascriptInterface
    public void initProducerVew(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        IntentUtils.getIntents().Intent(this, ProducerVewActivity.class, bundle);
    }

    @Override
    public void success(Object data) {
        if (data instanceof AddShoppingBean) {
            AddShoppingBean addShoppingBean = (AddShoppingBean) data;
            String code = addShoppingBean.getCode();
            if (code.equals("200")) {
                ToastUtils.show("加入成功");
            }
        }
    }

    @Override
    public void error(String error) {

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
