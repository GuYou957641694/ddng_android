package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.OrdinaryAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Ordinary;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

import qiu.niorgai.StatusBarCompat;

//待评价详情
public class OrdinaryActivity extends BaseActivity implements MyView {

    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String sha1;
    private long time;
    private TextView tv_logistics_status;
    private TextView tv_logistics_time;
    private TextView tv_address_name;
    private TextView tv_address_phone;
    private TextView tv_address;
    private TextView tv_store_name;
    private TextView tv_order_no;
    private TextView tv_order_time;
    private TextView tv_courier_company;
    private TextView tv_courier_number;
    private TextView courier_company;
    private TextView courier_number;
    private TextView tv_payment_price;
    private TextView tv_goods_check_logistics;
    private TextView tv_commodity_price, tv_goods_coupons, tv_goods_welfare, tv_goods_freight, tv_confirm, sales;
    private RecyclerView ev_pending_receipt;
    private RelativeLayout rl_goods_freight;

    @Override
    public int intiLayout() {
        return R.layout.activity_ordinary;
    }

    @Override
    public void initView() {
        StatusBarCompat.translucentStatusBar(this);
        String id = getIntent().getStringExtra("id");
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
        map.put("order_id", id);
        tv_logistics_status = findViewById(R.id.tv_logistics_status);
        tv_logistics_time = findViewById(R.id.tv_logistics_time);
        tv_address_name = findViewById(R.id.tv_address_name);
        tv_address_phone = findViewById(R.id.tv_address_phone);
        tv_address = findViewById(R.id.tv_address);
        tv_store_name = findViewById(R.id.tv_store_name);
        tv_order_no = findViewById(R.id.tv_order_no);
        tv_order_time = findViewById(R.id.tv_order_time);
        tv_courier_company = findViewById(R.id.tv_courier_company);
        tv_courier_number = findViewById(R.id.tv_courier_number);
        courier_company = findViewById(R.id.courier_company);
        courier_number = findViewById(R.id.courier_number);
        ev_pending_receipt = findViewById(R.id.ev_pending_receipt);
        tv_payment_price = findViewById(R.id.tv_payment_price);
        tv_commodity_price = findViewById(R.id.tv_commodity_price);
        rl_goods_freight = findViewById(R.id.rl_goods_freight);
        tv_goods_coupons = findViewById(R.id.tv_goods_coupons);
        tv_goods_welfare = findViewById(R.id.tv_goods_welfare);
        tv_goods_freight = findViewById(R.id.tv_goods_freight);
        tv_goods_check_logistics = findViewById(R.id.tv_goods_check_logistics);
        tv_confirm = findViewById(R.id.tv_confirm);
        sales = findViewById(R.id.sales);
    }

    @Override
    public void initData() {
        presenter.getpost(Contacts.pending_comment_lists, headmap, map, Ordinary.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof Ordinary) {
            Ordinary ordinary = (Ordinary) data;
            if (ordinary.getData().getLogistics() != null) {
                String context = ordinary.getData().getLogistics().getContext();
                String ftime = ordinary.getData().getLogistics().getFtime();
                tv_logistics_status.setText(context);
                tv_logistics_time.setText(ftime);
            }
            Ordinary.DataBean data1 = ordinary.getData();
            //地址
            tv_address_name.setText(data1.getName());
            tv_address_phone.setText(data1.getTel());
            tv_address.setText(data1.getSheng() + data1.getShi() + data1.getQu());
            tv_store_name.setText(data1.getFarm_name());
            List<Ordinary.DataBean.ListBean> list = data1.getList();
            OrdinaryAdapter ordinaryAdapter = new OrdinaryAdapter(this, list);
            ev_pending_receipt.setAdapter(ordinaryAdapter);
            ev_pending_receipt.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            //商品
            tv_commodity_price.setText("¥" + data1.getTotal_price());
            if (data1.getPostage() != null) {
                if (data1.getPostage().equals("0.00")) {
                    //没有邮费
                    rl_goods_freight.setVisibility(View.GONE);
                } else {
                    //有邮费
                    tv_goods_freight.setText("¥" + data1.getPostage());
                }
            }
           /* if (data1.getCoupon() != null) {
                //说明有优惠券
                tv_goods_coupons.setText("¥" + data1.getCoupon());
            } else {
                //没有优惠券
                rl_goods_coupons.setVisibility(View.GONE);
            }*/
            //公益放生
            /*if (data1.getWelfare() != null) {
                tv_goods_welfare.setText("+¥" + data1.getWelfare());
            } else {
                rl_goods_welfare.setVisibility(View.GONE);
            }*/
            //准收宝
         /*   if (data1.getInsurance() != null) {
                tv_goods_accept.setText("+¥" + data1.getInsurance());
            } else {
                rl_goods_accept.setVisibility(View.GONE);
            }*/
            tv_payment_price.setText("¥" + data1.getPrice());
            //订单
            tv_order_no.setText("订单编号：" + data1.getNumbering());
            tv_order_time.setText("下单时间：" + data1.getCtime());
            tv_courier_company.setText("支付时间：" + data1.getPay_time());
            tv_courier_number.setText("收货时间：" + data1.getReceipt_time());
            courier_company.setText("快递公司：" + data1.getTracking_title());
            courier_number.setText("订单编号：" + data1.getTracking_number());

            //开发票
            tv_goods_check_logistics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.getIntents().Intent(OrdinaryActivity.this, InvoiceActivity.class, null);
                }
            });

            //申请售后
            sales.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.getIntents().Intent(OrdinaryActivity.this, AfterSalesActivity.class, null);
                }
            });

            //评价晒单
            tv_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", data1.getOrder_id());
                    IntentUtils.getIntents().Intent(OrdinaryActivity.this, EvaluationDetailsActivity.class, bundle);
                }
            });
        }
    }

    @Override
    public void error(String error) {

    }
}
