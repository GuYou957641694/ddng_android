package com.bigpumpkin.app.ddng_android.activity;

import android.support.v7.widget.RecyclerView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.All_EvaluationAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;

public class All_EvaluationActivity extends BaseActivity implements MyView {

    private RecyclerView rv;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String sha1;
    private long time;
    private String id;

    @Override
    public int intiLayout() {
        return R.layout.activity_all__evaluation;
    }

    @Override
    public void initView() {
        new TitleXML(All_EvaluationActivity.this, "全部评价", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        id = getIntent().getStringExtra("id");
        rv = findViewById(R.id.rv_evaluation);
        presenter = new MyPresenterImpl(this);
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
    }

    @Override
    public void initData() {

    }

    @Override
    public void success(Object data) {
        All_EvaluationAdapter all_evaluationAdapter = new All_EvaluationAdapter();
    }

    @Override
    public void error(String error) {

    }
}
