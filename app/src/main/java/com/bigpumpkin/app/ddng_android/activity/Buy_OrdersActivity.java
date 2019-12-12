package com.bigpumpkin.app.ddng_android.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Address_Success_Bean;
import com.bigpumpkin.app.ddng_android.bean.PayResult;
import com.bigpumpkin.app.ddng_android.bean.WxPayBean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Alipay_Bean;
import com.bigpumpkin.app.ddng_android.config.WXConFig;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

public class Buy_OrdersActivity extends BaseActivity implements MyView {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private String appid;
    private String appsecret;
    private long time;
    private String sha1;
    private MyPresenterImpl presenter;
    private static final int SDK_PAY_FLAG = 1;
    private int TYPE = 0;
    private int i = 0;
    private String number, boby;
    private String prices;
    private String type;
    private IWXAPI api;

    @Override
    public int intiLayout() {
        return R.layout.activity_buy__orders;
    }

    @Override
    public void initView() {
        new TitleXML(Buy_OrdersActivity.this, "确认支付", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        LinearLayout zfb = findViewById(R.id.zfb);
        LinearLayout wx = findViewById(R.id.wx);
        LinearLayout yue = findViewById(R.id.yue);
        final CheckBox zfbPay = findViewById(R.id.zfb_pay);
        final CheckBox wxPay = findViewById(R.id.wx_pay);
        final CheckBox defaultsss = findViewById(R.id.defaultsss);
        TextView pay_price = findViewById(R.id.pay_price);
        Button pay = findViewById(R.id.pay);
        String ids = getIntent().getExtras().getString("id");
        String mes = getIntent().getExtras().getString("mes");
        prices = getIntent().getExtras().getString("price");
        type = getIntent().getExtras().getString("type");
        //微信用的订单号
        number = getIntent().getExtras().getString("number");
        boby = getIntent().getExtras().getString("boby");
        presenter = new MyPresenterImpl(this);
        pay_price.setText(prices);
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        Log.d(TAG, "initView: "+"appid"+appid+"appsecret"+appsecret+"timestamp"+time+"sign"+sha1);
        headmap = new HashMap<>();
        map = new HashMap<>();
        if (type != null && type.equals("1")) {

        } else {
            map.put("arr", ids);
        }
        if (mes != null) {
            //  map.put("leave_message", mes);
        } else {
            //map.put("leave_message", "null");
        }
        zfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zfbPay.setChecked(true);
                defaultsss.setChecked(false);
                wxPay.setChecked(false);
                //map.put("payment_method", "2");
                TYPE = 2;
                i = 2;
            }
        });
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxPay.setChecked(true);
                defaultsss.setChecked(false);
                zfbPay.setChecked(false);
                //map.put("payment_method", "3");
                TYPE = 3;
                i = 3;
            }
        });
        yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultsss.setChecked(true);
                wxPay.setChecked(false);
                zfbPay.setChecked(false);
                // map.put("payment_method", "1");
                TYPE = 1;
                i = 1;
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TYPE == 0) {
                    ToastUtil.showShort(Buy_OrdersActivity.this, "请选择支付方式");
                } else {
                    if (type != null && type.equals("1")) {
                        //从详情进入点击立即购买
                        if (TYPE == 1) {

                        } else if (TYPE == 2) {
                            //支付宝
                            map.clear();
                            map.put("appid", appid);
                            map.put("appsecret", appsecret);
                            map.put("timestamp", time);
                            map.put("sign", sha1);
                            map.put("number", number);
                            map.put("body", boby);
                            map.put("price", prices);
                            map.put("appid", appid);
                            map.put("appsecret", appsecret);
                            presenter.getpost(Contacts.ZFB_Pays, headmap, map, Zfb_Alipay_Bean.class);
                        } else if (TYPE == 3) {
                            //微信
                            map.clear();
                            map.put("appid", appid);
                            map.put("appsecret", appsecret);
                            map.put("timestamp", time);
                            map.put("sign", sha1);
                            map.put("number", number);
                            map.put("body", boby);
                            map.put("price", prices);
                            map.put("appid", appid);
                            map.put("appsecret", appsecret);
                            presenter.getpost(Contacts.WeChat_Pays, headmap, map, WxPayBean.class);
                        }
                    } else {
                        //购物车购买
                        map.put("payment_method", i);
                        presenter.getpost(Contacts.total_settlements, headmap, map, Address_Success_Bean.class);
                    }

                }

            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public void success(Object data) {
        if (data instanceof Zfb_Alipay_Bean) {
            Zfb_Alipay_Bean zfb_alipay_bean = (Zfb_Alipay_Bean) data;
            if (zfb_alipay_bean.getData() != null) {
                final String data1 = zfb_alipay_bean.getData();
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(Buy_OrdersActivity.this);
                        Map<String, String> result = alipay.payV2(data1, true);
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                        //跳转到支付成功的页面啊
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        } else if (data instanceof WxPayBean) {
            WxPayBean wxPayBean = (WxPayBean) data;
            String code = wxPayBean.getCode();
            if (code.equals("200")) {
                final IWXAPI msgApi = WXAPIFactory.createWXAPI(App.appContext, null);
                // 将该app注册到微信
                msgApi.registerApp(WXConFig.APP_ID);
                api = WXAPIFactory.createWXAPI(App.appContext, WXConFig.APP_ID, false);
                PayReq request = new PayReq();
                request.appId = wxPayBean.getData().getAppid();
                request.partnerId = wxPayBean.getData().getPartnerid();
                request.prepayId = wxPayBean.getData().getPrepayid();
                request.nonceStr = wxPayBean.getData().getNoncestr();
                request.timeStamp = wxPayBean.getData().getTimestamp();
                request.packageValue = "Sign=WXPay";
                request.sign = wxPayBean.getData().getSign();
                api.sendReq(request);
                finish();
            }
        }
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        payCallback(courseID);
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtil.showShort(App.appContext, "支付成功");

                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtil.showShort(App.appContext, "支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    public void error(String error) {
        Log.d(TAG, "error: " + error);
    }
}
