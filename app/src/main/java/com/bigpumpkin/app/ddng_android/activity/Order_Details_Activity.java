package com.bigpumpkin.app.ddng_android.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.PaymentAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.OrderlistsBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.TimeUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;
import com.hjq.toast.ToastUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 待付款详情
 */
public class Order_Details_Activity extends BaseActivity implements MyView {

    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String id;
    private RecyclerView rvPayment;
    private TextView tvFarmName, tvAddressName, tvAddressPhone, tvAddress, tvAmount, tvFreight, tvCoupons, tvWelfare, tvAccept, tvPayPrice, tv_order_no, tv_order_time, tv_mes, tvCancelOrder;
    private RelativeLayout rl_welfare, rl_accept, rl_coupons;
    private LinearLayout rl_mes;
    private TextView tvSeckillHour, tvSeckillMinute, tvSeckillSecond;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countDown();
            sendEmptyMessageDelayed(0, 1000);
        }

    };
    private OrderlistsBean orderlistsBean;
    private long ctime1;

    @Override
    public int intiLayout() {
        return R.layout.activity_order__details_;
    }

    @Override
    public void initView() {
        new TitleXML(Order_Details_Activity.this, "订单详情", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        id = getIntent().getStringExtra("id");
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
        //订单
        rvPayment = findViewById(R.id.rv_payment);
        tvAddressName = findViewById(R.id.tv_address_name);
        tvAddressPhone = findViewById(R.id.tv_address_phone);
        tvAddress = findViewById(R.id.tv_address);
        tvFarmName = findViewById(R.id.tv_farm_name);
        //倒计时
        tvSeckillHour = findViewById(R.id.tv_seckill_hour);
        tvSeckillMinute = findViewById(R.id.tv_seckill_minute);
        tvSeckillSecond = findViewById(R.id.tv_seckill_second);
        //商品金额
        tvAmount = findViewById(R.id.tv_amount);
        //运费
        tvFreight = findViewById(R.id.tv_freight);
        //优惠券
        tvCoupons = findViewById(R.id.tv_coupons);
        rl_coupons = findViewById(R.id.rl_coupons);
        //公益放生
        tvWelfare = findViewById(R.id.tv_welfare);
        rl_welfare = findViewById(R.id.rl_welfare);
        //准时宝
        tvAccept = findViewById(R.id.tv_accept);
        rl_accept = findViewById(R.id.rl_accept);
        //应付金额
        tvPayPrice = findViewById(R.id.tv_pay_price);
        //留言
        tv_mes = findViewById(R.id.tv_mes);
        rl_mes = findViewById(R.id.rl_mes);
        //订单编号
        tv_order_no = findViewById(R.id.tv_order_no);
        //订单时间
        tv_order_time = findViewById(R.id.tv_order_time);
        //取消订单
        tvCancelOrder = findViewById(R.id.tv_cancel_order);
        ToastUtils.show(id);
        handler.sendEmptyMessage(0);
    }

    @Override
    public void initData() {
        presenter.getpost(Contacts.pendingPaymentDetailss, headmap, map, OrderlistsBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof OrderlistsBean) {
            orderlistsBean = (OrderlistsBean) data;
            String farm_name = orderlistsBean.getData().getFarm_name();
            String name = orderlistsBean.getData().getName();
            String tel = orderlistsBean.getData().getTel();
            String sheng = orderlistsBean.getData().getSheng();
            String shi = orderlistsBean.getData().getShi();
            String qu = orderlistsBean.getData().getQu();
            String address = orderlistsBean.getData().getAddress();
            List<OrderlistsBean.DataBean.OrderlistBean> orderlist = orderlistsBean.getData().getOrderlist();
            if (name != null && tel != null && sheng != null && shi != null && qu != null && address != null && farm_name != null) {
                tvFarmName.setText(farm_name);
                tvAddressName.setText(name);
                tvAddressPhone.setText(tel);
                tvAddress.setText(sheng + shi + qu + address);
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rvPayment.setLayoutManager(linearLayoutManager);
            PaymentAdapter paymentAdapter = new PaymentAdapter(this, orderlist);
            rvPayment.setAdapter(paymentAdapter);

            //邮费
            String postage = orderlistsBean.getData().getPostage();
            if (postage != null) {
                tvFreight.setText("+¥" + postage);
            } else {
                tvFreight.setText("包邮");
            }

            //优惠券
            String jian = orderlistsBean.getData().getJian();
            if (jian != null) {
                tvCoupons.setText("-¥" + jian);
            } else {
                rl_coupons.setVisibility(View.GONE);
            }
            //放生公益
            String welfare = orderlistsBean.getData().getWelfare();
            if (welfare != null) {
                tvWelfare.setText("+¥" + welfare);
            } else {
                rl_welfare.setVisibility(View.GONE);
            }
            //准收宝
            String insurance = orderlistsBean.getData().getInsurance();
            if (insurance != null) {
                tvAccept.setText("+¥" + insurance);
            } else {
                rl_accept.setVisibility(View.GONE);
            }
            //留言
            String message = orderlistsBean.getData().getMessage();
            if (message.equals("0")) {
                //如果是0的话就说明没又留言
                rl_mes.setVisibility(View.GONE);
            } else {
                tv_mes.setText(message);
            }
            String show = orderlistsBean.getData().getShow();
            if (show.equals("1")) {

            } else {
                //没有
                rl_accept.setVisibility(View.GONE);
                rl_welfare.setVisibility(View.GONE);
            }
            String total_price = orderlistsBean.getData().getTotal_price();
            //商品总额
            tvAmount.setText("¥" + total_price);
            //应付价钱
            Tv_Price_Utils.initPrice(this, orderlistsBean.getData().getPrice(), tvPayPrice);
            //订单号
            String numbering = orderlistsBean.getData().getNumbering();
            tv_order_no.setText("订单编号：" + numbering);
            //创建订单时间
            long ctime = orderlistsBean.getData().getCtime();
            String dateTimeFromMillisecond = TimeUtils.getDateTimeFromMillisecond(ctime);
            tv_order_time.setText("下单时间：" + dateTimeFromMillisecond);

            tvCancelOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String order_id = orderlistsBean.getData().getOrder_id();
                    map.clear();

                }
            });
        }
    }

    private void countDown() {
        if (orderlistsBean != null) {
            ctime1 = orderlistsBean.getData().getCtime();
            //倒计时
            long ctimes = ctime1 * 1000;
            //当前时间
            long time = System.currentTimeMillis();
            //结束时间
            long day = ctimes + 1000 * 60 * 60 * 24;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = new Date(time);
            Date d2 = new Date(day);
            String format = df.format(d1);
            String format1 = df.format(d2);
            StringBuffer buffer = new StringBuffer();
            buffer.append(format1);
            String totime = buffer.toString();
            try {
                java.util.Date date = df.parse(totime);
                java.util.Date date1 = df.parse(format);
                long defferenttime = date.getTime() - date1.getTime();
                long days = defferenttime / (1000 * 60 * 60 * 24);
                long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                long seconds = defferenttime % 60000;
                long second = Math.round((float) seconds / 1000);
                if (hours >= 10) {
                    tvSeckillHour.setText(hours + "");
                } else {
                    tvSeckillHour.setText("0" + hours + "");
                }
                if (minute >= 10) {
                    tvSeckillMinute.setText(minute + "");
                } else {
                    tvSeckillMinute.setText("0" + minute + "");
                }
                if (second >= 10) {
                    tvSeckillSecond.setText(second + "");
                } else {
                    tvSeckillSecond.setText("0" + second + "");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void error(String error) {

    }
}
