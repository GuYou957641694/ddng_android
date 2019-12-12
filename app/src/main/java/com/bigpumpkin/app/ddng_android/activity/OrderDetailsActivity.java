package com.bigpumpkin.app.ddng_android.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.OrderDetailsAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.OrderDetailsBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.TimeUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

/**
 * 全部订单详情
 */
public class OrderDetailsActivity extends BaseActivity implements MyView {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private String appid;
    private String appsecret;
    private long time;
    private String sha1;
    private MyPresenterImpl presenter;
    private RecyclerView rc;
    private List<OrderDetailsBean.DataBean.ListBean.DetailsBean> details;
    private OrderDetailsBean.DataBean.ListBean list;
    private OrderDetailsBean orderDetailsBean;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_address;
    private TextView tv_fram_name;
    private TextView tv_order;
    private TextView tv_ctime;
    private TextView tv_stime;
    private TextView tv_make_invoice;
    private TextView tv_cancel_order;
    private TextView tv_apply_after;
    private TextView tv_pay;

    @Override
    public int intiLayout() {
        return R.layout.activity_order_details;
    }

    @Override
    public void initView() {
        new TitleXML(OrderDetailsActivity.this, "订单详情", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        //订单ID
        String id = getIntent().getStringExtra("id");
        rc = findViewById(R.id.recyclerview);
        tv_fram_name = findViewById(R.id.tv_fram_name);
        tv_name = findViewById(R.id.name);
        tv_phone = findViewById(R.id.phone);
        tv_address = findViewById(R.id.address);
        RelativeLayout ic_order_no = findViewById(R.id.ic_order_no);
        RelativeLayout ic_order_logistics = findViewById(R.id.ic_order_logistics);
        //订单号
        tv_order = findViewById(R.id.tv_order);
        //交易时间
        tv_ctime = findViewById(R.id.tv_ctime);
        //收货时间
        tv_stime = findViewById(R.id.tv_stime);
        //取消订单
        tv_cancel_order = findViewById(R.id.tv_cancel_order);
        //申请开票
        tv_make_invoice = findViewById(R.id.tv_make_invoice);
        //申请售后
        tv_apply_after = findViewById(R.id.tv_apply_after);
        //立即支付
        tv_pay = findViewById(R.id.tv_pay);
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
        map.put("id", id);
        Log.d(TAG, "initView: "+"appid"+appid+"appid"+appsecret+"time"+time+"sha1"+sha1);
        presenter.getpost(Contacts.order_detailss, headmap, map, OrderDetailsBean.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void success(Object data) {
        if (data instanceof OrderDetailsBean) {
            orderDetailsBean = (OrderDetailsBean) data;
            list = orderDetailsBean.getData().getList();
            if (list != null) {
                details = orderDetailsBean.getData().getList().getDetails();
                String name = list.getName();
                String tel = list.getTel();
                String sheng = list.getSheng();
                String shi = list.getShi();
                String qu = list.getQu();
                String address = list.getAddress();
                tv_name.setText(name);
                tv_phone.setText(tel);
                tv_address.setText(sheng + shi + qu + address);
                tv_fram_name.setText(list.getFarm_name());
                OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(details, this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                rc.setLayoutManager(layoutManager);
                rc.setAdapter(orderDetailsAdapter);
                tv_order.setText("订单编号：" + orderDetailsBean.getData().getList().getNumbering());
                long ctime = orderDetailsBean.getData().getList().getCtime();
                tv_ctime.setText("订单时间：" + TimeUtils.getDateTimeFromMillisecond(ctime));
                //订单状态
                int zt = list.getZt();
                if (zt == 3) {
                    //显示取消订单
                    tv_cancel_order.setVisibility(View.VISIBLE);
                    tv_pay.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void error(String error) {

    }
}
