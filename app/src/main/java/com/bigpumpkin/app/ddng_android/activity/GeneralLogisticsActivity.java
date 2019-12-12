package com.bigpumpkin.app.ddng_android.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.GeneralLogisticsAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.GeneralLogisticsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

import qiu.niorgai.StatusBarCompat;

//1普通商品物流页面
public class GeneralLogisticsActivity extends BaseActivity implements MyView {

    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private ImageView iv_pic;
    private TextView tv_waybill;
    private TextView tv_company;
    private TextView tv_phone;
    private RecyclerView recy;

    @Override
    public int intiLayout() {
        return R.layout.activity_general_logistics;
    }

    @Override
    public void initView() {
        StatusBarCompat.translucentStatusBar(this);
        String id = getIntent().getStringExtra("id");
        iv_pic = findViewById(R.id.iv_pic);
        tv_waybill = findViewById(R.id.tv_waybill);
        tv_company = findViewById(R.id.tv_company);
        tv_phone = findViewById(R.id.tv_phone);
        recy = findViewById(R.id.recy);
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
        map.put("order_id", id);
    }

    @Override
    public void initData() {
        presenter.getpost(Contacts.logistics_detailss, headmap, map, GeneralLogisticsBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof GeneralLogisticsBean) {
            GeneralLogisticsBean generalLogisticsBean = (GeneralLogisticsBean) data;
            List<GeneralLogisticsBean.DataBeanX.ListBean.DataBean> data1 = generalLogisticsBean.getData().getList().getData();
            GeneralLogisticsAdapter generalLogisticsAdapter = new GeneralLogisticsAdapter(this, data1);
            recy.setAdapter(generalLogisticsAdapter);
            recy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            tv_waybill.setText("快递单号：" + generalLogisticsBean.getData().getList().getNu());
            tv_company.setText("物理公司：" + generalLogisticsBean.getData().getExpress_title());
            tv_phone.setText("官方电话：" + generalLogisticsBean.getData().getExpress_tel());
            GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + generalLogisticsBean.getData().getExpress_pic(), iv_pic);
        }
    }

    @Override
    public void error(String error) {

    }
}
