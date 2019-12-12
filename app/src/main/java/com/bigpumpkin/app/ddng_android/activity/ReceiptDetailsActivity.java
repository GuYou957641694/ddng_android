package com.bigpumpkin.app.ddng_android.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.ReceiptAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Address_Success_Bean;
import com.bigpumpkin.app.ddng_android.bean.DetailassGoodBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.TimeUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

/**
 * 待收货详情
 */
public class ReceiptDetailsActivity extends BaseActivity implements MyView {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private String appid;
    private String appsecret;
    private long time;
    private String sha1;
    private MyPresenterImpl presenter;
    private TextView name, phone, address;
    private TextView tv_check_the_logistics;
    private TextView tv_order;
    private TextView tv_ctime;
    private RecyclerView recyclerview;
    private ReceiptAdapter receiptAdapter;
    private DetailassGoodBean.DataBean.ListBean list;
    private int j;


    @Override
    public int intiLayout() {
        return R.layout.activity_receipt_details;
    }

    @Override
    public void initView() {
        new TitleXML(ReceiptDetailsActivity.this, "订单详情", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        String id = getIntent().getExtras().getString("id");
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        //查看物流
        tv_check_the_logistics = findViewById(R.id.tv_check_the_logistics);
        tv_order = findViewById(R.id.tv_order);
        tv_ctime = findViewById(R.id.tv_ctime);
        recyclerview = findViewById(R.id.recyclerview);
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
        presenter.getpost(Contacts.My_detailss, headmap, map, DetailassGoodBean.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void success(Object data) {
        if (data instanceof DetailassGoodBean) {
            DetailassGoodBean detailassGoodBean = (DetailassGoodBean) data;
            DetailassGoodBean.DataBean data1 = detailassGoodBean.getData();
            if (data1 != null) {
                list = data1.getList();
                String names = list.getName();
                String tels = list.getTel();
                String shengs = list.getSheng();
                String shis = list.getShi();
                String qus = list.getQu();
                String price = list.getPrice();
                long pay_time = list.getPay_time();
                String coupon = list.getCoupon();
                String farm_name = list.getFarm_name();
                String total_price = list.getTotal_price();
                String numbering = list.getNumbering();
                name.setText(names);
                phone.setText(tels);
                address.setText(shengs + shis + qus);
                tv_order.setText("订单编号：" + numbering);
                tv_ctime.setText("交易时间：" + TimeUtils.getDateTimeFromMillisecond(pay_time));
                List<DetailassGoodBean.DataBean.ListBean.DetailsBean> details = list.getDetails();
                receiptAdapter = new ReceiptAdapter(details, this);
                recyclerview.setAdapter(receiptAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerview.setLayoutManager(linearLayoutManager);
                //判断单个农场里面有多少个商品
                int size = details.size();
                if (size > 1) {

                } else {
                }
                //确认收货
                receiptAdapter.setListener(new ReceiptAdapter.onListener() {
                    @Override
                    public void OnListener(int i) {
                        j = i;
                        map.clear();
                        map.put("appid", appid);
                        map.put("appsecret", appsecret);
                        map.put("timestamp", time);
                        map.put("sign", sha1);
                        map.put("id", list.getDetails().get(i).getId());
                        map.put("single", list.getSingle());
                        presenter.getpost(Contacts.my_confirm, headmap, map, Address_Success_Bean.class);
                    }
                });
                receiptAdapter.setListeners(new ReceiptAdapter.onListeners() {
                    @Override
                    public void OnListeners(int i) {
                        j = i;
                        map.clear();
                        map.put("appid", appid);
                        map.put("appsecret", appsecret);
                        map.put("timestamp", time);
                        map.put("sign", sha1);
                        map.put("id", list.getDetails().get(i).getId());
                        map.put("single", list.getSingle());
                        presenter.getpost(Contacts.my_confirm, headmap, map, Address_Success_Bean.class);
                    }
                });
            }
        } else if (data instanceof Address_Success_Bean) {
            Address_Success_Bean address_success_bean = (Address_Success_Bean) data;
            String code = address_success_bean.getCode();
            if (code.equals("200")) {
                ToastUtil.showShort(this, "收货成功");
                receiptAdapter.removeData(j);
            }
        }
    }

    @Override
    public void error(String error) {

    }
}
