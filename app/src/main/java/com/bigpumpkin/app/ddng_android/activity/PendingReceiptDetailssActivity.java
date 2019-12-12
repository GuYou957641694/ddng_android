package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.PendingReceiptDetailssAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.PendingReceiptDetailssBean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.TimeUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.hjq.toast.ToastUtils;

import java.util.HashMap;
import java.util.List;

//待收货详情
public class PendingReceiptDetailssActivity extends BaseActivity implements MyView {

    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String id;
    private TextView tvStatu, tvLogisticsSstatus, tv_remind,tv_confirm, tvLogisticsTime, sales, tv_courier_company, tv_courier_number, tvAddressName, tvAddressPhone, tv_goods_accept, tvAddress, tvGoodsCheckLogistics, tv_payment_price, tvStoreName, tv_order_no, tv_order_time, tv_goods_welfare, tv_commodity_price, tv_goods_freight, tv_goods_coupons;
    private LinearLayout linLogistics;
    private RecyclerView evPendingReceipt;
    private RelativeLayout rl_goods_accept, rl_goods_welfare, rl_goods_freight, rl_goods_coupons, rl_courier_number, rl_courier_company;
    private String sha1;
    private long time;
    private PendingReceiptDetailssAdapter pendingReceiptDetailssAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_pending_receipt_detailss;
    }

    @Override
    public void initView() {
        id = getIntent().getStringExtra("id");
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        tvStatu = findViewById(R.id.tv_statu);
        tvLogisticsSstatus = findViewById(R.id.tv_logistics_status);
        tvLogisticsTime = findViewById(R.id.tv_logistics_time);
        linLogistics = findViewById(R.id.lin_logistics);
        tvAddressName = findViewById(R.id.tv_address_name);
        tvAddressPhone = findViewById(R.id.tv_address_phone);
        tvAddress = findViewById(R.id.tv_address);
        evPendingReceipt = findViewById(R.id.ev_pending_receipt);
        tvStoreName = findViewById(R.id.tv_store_name);
        tv_order_no = findViewById(R.id.tv_order_no);
        tv_order_time = findViewById(R.id.tv_order_time);
        tv_commodity_price = findViewById(R.id.tv_commodity_price);
        tv_goods_freight = findViewById(R.id.tv_goods_freight);
        tv_goods_coupons = findViewById(R.id.tv_goods_coupons);
        tv_goods_welfare = findViewById(R.id.tv_goods_welfare);
        tv_goods_accept = findViewById(R.id.tv_goods_accept);
        rl_goods_accept = findViewById(R.id.rl_goods_accept);
        rl_goods_welfare = findViewById(R.id.rl_goods_welfare);
        rl_goods_freight = findViewById(R.id.rl_goods_freight);
        rl_goods_coupons = findViewById(R.id.rl_goods_coupons);
        tv_payment_price = findViewById(R.id.tv_payment_price);
        tvGoodsCheckLogistics = findViewById(R.id.tv_goods_check_logistics);
        tv_confirm = findViewById(R.id.tv_confirm);
        tv_courier_company = findViewById(R.id.tv_courier_company);
        tv_courier_number = findViewById(R.id.tv_courier_number);
        rl_courier_number = findViewById(R.id.rl_courier_number);
        rl_courier_company = findViewById(R.id.rl_courier_company);
        sales = findViewById(R.id.sales);
        tv_remind = findViewById(R.id.tv_remind);

    }

    @Override
    public void initData() {
        map.put("order_id", id);
        ToastUtils.show(id);
        presenter.getpost(Contacts.pendingPeceiptDetailss, headmap, map, PendingReceiptDetailssBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof PendingReceiptDetailssBean) {
            PendingReceiptDetailssBean pendingReceiptDetailssBean = (PendingReceiptDetailssBean) data;
            PendingReceiptDetailssBean.DataBean data1 = pendingReceiptDetailssBean.getData();

            //1待发货 2部分发货 3代收货
            int statu = data1.getStatu();
            if (statu == 1) {
                tvStatu.setText("待发货");
            } else if (statu == 2) {
                tvStatu.setText("部分发货");
            } else if (statu == 3) {
                tvStatu.setText("卖家已发货");
            }
            PendingReceiptDetailssBean.DataBean.LogisticsBean logistics = data1.getLogistics();
            if (logistics != null) {
                if (logistics.getCode().equals("200")) {
                    tvLogisticsSstatus.setText(logistics.getContext());
                    tvLogisticsTime.setText(logistics.getTime());
                } else {

                }
            } else {
                linLogistics.setVisibility(View.GONE);
            }
            //地址
            tvAddressName.setText(data1.getName());
            tvAddressPhone.setText(data1.getTel());
            tvAddress.setText(data1.getSheng() + data1.getShi() + data1.getQu());
            tvStoreName.setText(data1.getFarm_name());
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
            if (data1.getCoupon() != null) {
                //说明有优惠券
                tv_goods_coupons.setText("¥" + data1.getCoupon());
            } else {
                //没有优惠券
                rl_goods_coupons.setVisibility(View.GONE);
            }
            //公益放生
            if (data1.getWelfare() != null) {
                tv_goods_welfare.setText("+¥" + data1.getWelfare());
            } else {
                rl_goods_welfare.setVisibility(View.GONE);
            }
            //准收宝
            if (data1.getInsurance() != null) {
                tv_goods_accept.setText("+¥" + data1.getInsurance());
            } else {
                rl_goods_accept.setVisibility(View.GONE);
            }
            //订单号、时间
            tv_order_no.setText("订单编号：" + data1.getNumbering());
            long ctime = data1.getCtime();
            String timet = TimeUtils.getDateTimeFromMillisecond(ctime);
            tv_order_time.setText("下单时间：" + timet);
            //商品列表
            List<PendingReceiptDetailssBean.DataBean.ListBean> list = data1.getList();
            pendingReceiptDetailssAdapter = new PendingReceiptDetailssAdapter(this, list);
            evPendingReceipt.setAdapter(pendingReceiptDetailssAdapter);
            evPendingReceipt.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            //实付价格
            tv_payment_price.setText("¥" + data1.getPrice());
            //查看物流	1待发货 2部分发货 3代收货
            int statu1 = data1.getStatu();
            if (statu1 == 1) {
                tvGoodsCheckLogistics.setVisibility(View.GONE);
                tv_confirm.setVisibility(View.GONE);
                sales.setVisibility(View.VISIBLE);
                //提示发货按钮显示 只有在代发货的时候
                tv_remind.setVisibility(View.VISIBLE);
            } else if (statu == 2) {
                tvGoodsCheckLogistics.setVisibility(View.VISIBLE);
                tv_confirm.setVisibility(View.GONE);
            } else if (statu == 3) {
                tv_confirm.setVisibility(View.VISIBLE);
            }
            if (data1.getTracking_title() != null && data1.getTracking_number() != null) {
                tv_courier_number.setText("快递公司：" + data1.getTracking_title());
                tv_courier_number.setText("快递单号：" + data1.getTracking_number());
            } else {
                rl_courier_number.setVisibility(View.GONE);
                rl_courier_company.setVisibility(View.GONE);
            }

            if (list.size() > 1) {
                //如果size大于1 底部申请售后就显示批量售后
                sales.setText("批量售后");
                sales.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentUtils.getIntents().Intent(PendingReceiptDetailssActivity.this, BatchCusomerActivity.class, null);
                    }
                });
            } else {
                sales.setText("申请售后");
                sales.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentUtils.getIntents().Intent(PendingReceiptDetailssActivity.this, CusomerActivity.class, null);
                    }
                });
            }
            //判断底部按钮是否隐藏
            String after = data1.getList().get(0).getAfter();
            if (after!=null){
                if(after.equals("0")){
                    sales.setVisibility(View.GONE);
                }
            }



            pendingReceiptDetailssAdapter.setListener(new PendingReceiptDetailssAdapter.onListener() {
                @Override
                public void OnListener(int i) {
                    Bundle bundle = new Bundle();
                    bundle.putString("order_id", data1.getOrder_id());
                    bundle.putString("orderlist_id", list.get(i).getOrderlist_id());
                    IntentUtils.getIntents().Intent(PendingReceiptDetailssActivity.this, CusomerActivity.class, bundle);
                }
            });

            //点击物流
            tvGoodsCheckLogistics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String order_id = data1.getOrder_id();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", order_id);
                    IntentUtils.getIntents().Intent(PendingReceiptDetailssActivity.this, GeneralLogisticsActivity.class, bundle);
                }
            });
            linLogistics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String order_id = data1.getOrder_id();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", order_id);
                    IntentUtils.getIntents().Intent(PendingReceiptDetailssActivity.this, GeneralLogisticsActivity.class, bundle);
                }
            });

            //确认收货
            tv_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String order_id = data1.getOrder_id();
                    map.clear();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("order_id", order_id);
                    presenter.getpost(Contacts.my_confirm, headmap, map, Zfb_Bean.class);
                }
            });

        } else if (data instanceof Zfb_Bean) {
            Zfb_Bean zfb_bean = (Zfb_Bean) data;
            String code = zfb_bean.getCode();
            if (code.equals("200")) {
                ToastUtils.show("成功");
            }
        }
    }

    @Override
    public void error(String error) {

    }
}
