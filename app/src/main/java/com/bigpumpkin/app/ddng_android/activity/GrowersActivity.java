package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.GrowersAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Farm_AttentionsBean;
import com.bigpumpkin.app.ddng_android.bean.GrowersBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.LoginUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

public class GrowersActivity extends BaseActivity implements MyView, View.OnClickListener {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String id;
    private RecyclerView recyclerview;
    private String appid;
    private String appsecret;
    private String sha1;
    private long time;
    private int Fid;
    private List<GrowersBean.DataBean> data1;
    private GrowersAdapter growersAdapter;
    private ImageView iv_back;
    private String zt;

    @Override
    public int intiLayout() {
        return R.layout.activity_growers;
    }

    @Override
    public void initView() {
        //判断有没有登录
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        zt = sharedPreferences.getString("zt", "");
        recyclerview = findViewById(R.id.recyclerview);
        iv_back = findViewById(R.id.iv_back);
        id = getIntent().getStringExtra("id");
        iv_back.setOnClickListener(this);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        map.put("type", id);
        map.put("page", "1");
        presenter.get(Contacts.See_farmers, headmap, map, GrowersBean.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void success(Object data) {
        if (data instanceof GrowersBean) {
            GrowersBean growersBean = (GrowersBean) data;
            data1 = growersBean.getData();
            recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            growersAdapter = new GrowersAdapter(this, data1);
            recyclerview.setAdapter(growersAdapter);
            growersAdapter.setListener(new GrowersAdapter.onListener() {
                @Override
                public void OnListener(int i) {
                    if (!LoginUtil.getInstance().checkLoginStatus(GrowersActivity.this)) {
                        return;
                    }
                    time = System.currentTimeMillis();
                    appid = SpzUtils.getString("appid");
                    appsecret = SpzUtils.getString("appsecret");
                    String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                    sha1 = EncryptUtils.getSHA(sha);
                    map.clear();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("fid", data1.get(i).getFid());
                    presenter.getpost(Contacts.Farm_Attentions, headmap, map, Farm_AttentionsBean.class);
                    Fid = i;
                }
            });
        } else if (data instanceof Farm_AttentionsBean) {
            Farm_AttentionsBean farm_attentionsBean = (Farm_AttentionsBean) data;
            Farm_AttentionsBean.DataBean data2 = farm_attentionsBean.getData();
            String code = data2.getCode();
            if (code.equals("1")) {
                ToastUtil.showShort(this, "关注成功");
                data1.get(Fid).setAttention("1");
                growersAdapter.notifyDataSetChanged();
            } else {
                ToastUtil.showShort(this, "取消成功");
                data1.get(Fid).setAttention("2");
                growersAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
