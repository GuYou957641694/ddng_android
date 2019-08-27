package com.bigpumpkin.app.ddng_android.activity;

import android.support.design.bottomappbar.BottomAppBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.CollectionsAdapter;
import com.bigpumpkin.app.ddng_android.adapter.CouponsApadter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Coupons_Bean;
import com.bigpumpkin.app.ddng_android.bean.Focus_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

/**
 * 优惠券
 */
public class CouponsActivity extends BaseActivity implements MyView {


    private RecyclerView recyclerView;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_coupons;
    }

    @Override
    public void initView() {
        new TitleXML(CouponsActivity.this, "我的优惠券", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        recyclerView = findViewById(R.id.my_coupons);
        presenter = new MyPresenterImpl(this);
        long time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        String sha1 = EncryptUtils.getSHA(sha);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        presenter.getpost(Contacts.My_Coupons, headmap, map, Coupons_Bean.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void success(Object data) {
        Coupons_Bean coupons_bean = (Coupons_Bean) data;
        String code = coupons_bean.getCode();
        List<Coupons_Bean.DataBean.ListBean> list = coupons_bean.getData().getList();
        if (EmptyUtils.isNotEmpty(code)) {
            if (code.equals("200")) {
                CouponsApadter couponsApadter = new CouponsApadter(list,CouponsActivity.this);
                //添加布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(couponsApadter);
            } else if (code.equals("201")) {
                ToastUtil.showShort(this, "查询失败");
            }
        }
    }

    @Override
    public void error(String error) {

    }
}
