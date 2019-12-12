package com.bigpumpkin.app.ddng_android.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.CollagesBean;
import com.bigpumpkin.app.ddng_android.bean.ParticipateDetailsBean;
import com.bigpumpkin.app.ddng_android.bean.PayResult;
import com.bigpumpkin.app.ddng_android.bean.WxPayBean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.config.WXConFig;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

public class ParticipateDetailsActivity extends BaseActivity implements MyView {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    //网络请求
    private String s_id, id, sha1, appid, appsecret;
    private long time;
    private RelativeLayout rl_address_fales, rl_address;
    private TextView tv_farm_name, tv_titles, tv_gg, tv_price, tv_welfare, tv_insurance, v_real_pay, tv_name, tv_phone, addres;
    private Button bt_submit_orders;
    private ImageView tv_head_pic;
    private Switch switchs;
    private int TYPE = 1;
    private EditText et_message;
    private String substring;
    private CollagesBean.DataBean data1;
    private static final int SDK_PAY_FLAG = 1;
    private IWXAPI api;

    @Override
    public int intiLayout() {
        return R.layout.activity_participate_details;
    }

    @Override
    public void initView() {
        new TitleXML(ParticipateDetailsActivity.this, "确认订单", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        id = getIntent().getStringExtra("id");
        s_id = getIntent().getStringExtra("s_id");
        tv_head_pic = findViewById(R.id.tv_head_pic);
        et_message = findViewById(R.id.et_message);
        bt_submit_orders = findViewById(R.id.bt_submit_orders);
        switchs = findViewById(R.id.switchs);
        rl_address_fales = findViewById(R.id.rl_address_fales);
        rl_address = findViewById(R.id.rl_address);
        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_phone);
        addres = findViewById(R.id.address);
        tv_farm_name = findViewById(R.id.tv_farm_name);
        tv_titles = findViewById(R.id.tv_titles);
        tv_gg = findViewById(R.id.tv_gg);
        tv_price = findViewById(R.id.tv_price);
        tv_welfare = findViewById(R.id.tv_welfare);
        tv_insurance = findViewById(R.id.tv_insurance);
        v_real_pay = findViewById(R.id.v_real_pay);
    }

    @Override
    public void initData() {
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        map.put("id", id);
        map.put("s_id", s_id);
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        presenter.getpost(Contacts.Initiate_participation_list_shows, headmap, map, ParticipateDetailsBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof ParticipateDetailsBean) {
            ParticipateDetailsBean participateDetailsBean = (ParticipateDetailsBean) data;
            ParticipateDetailsBean.DataBean data1 = participateDetailsBean.getData();
            ParticipateDetailsBean.DataBean.AddressBean address = data1.getAddress();
            ParticipateDetailsBean.DataBean.ShopBean shop = data1.getShop();
            if (address != null) {
                rl_address.setVisibility(View.VISIBLE);
                rl_address_fales.setVisibility(View.GONE);
                String name = address.getName();
                String tel = address.getTel();
                String sheng = address.getSheng();
                String shi = address.getShi();
                String qu = address.getQu();
                String address1 = address.getAddress();
                tv_name.setText(name);
                tv_phone.setText(tel);
                addres.setText(sheng + shi + qu + address1);
            } else {
                rl_address_fales.setVisibility(View.VISIBLE);
                rl_address.setVisibility(View.GONE);
            }
            GlideUtils.loadImage(this, Urls.BASEURL + shop.getImg_pic(), tv_head_pic);
            tv_farm_name.setText(shop.getNc_title());
            tv_titles.setText(shop.getCp_title());
            tv_gg.setText("已选：" + data1.getFruit_tree_varietylist().getTitle());
            Tv_Price_Utils.initPriceTv(this, shop.getPrice(), tv_price);
            tv_welfare.setText(shop.getWelfare() + "元");
            tv_insurance.setText("+" + data1.getLnsurance() + "元");
            v_real_pay.setText("¥" + data1.getPrice());

            switchs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //开的时候准收宝加上
                        v_real_pay.setText("¥" + data1.getLnsurance());
                        tv_insurance.setText(data1.getLnsurance() + "元");
                        TYPE = 1;
                    } else {
                        //关的时候减去
                        double price_pay = data1.getPrice();
                        double insurance = data1.getLnsurance();
                        double d = price_pay - insurance;
                        tv_insurance.setText("0元");
                        v_real_pay.setText("¥" + d);
                        TYPE = 2;
                    }
                }
            });

            bt_submit_orders.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String addres = address.getId();
                    if (addres != null) {
                        String price = v_real_pay.getText().toString();
                        substring = price.substring(1);
                        map.clear();
                        time = System.currentTimeMillis();
                        appid = SpzUtils.getString("appid");
                        appsecret = SpzUtils.getString("appsecret");
                        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                        sha1 = EncryptUtils.getSHA(sha);
                        map.put("appid", appid);
                        map.put("appsecret", appsecret);
                        map.put("timestamp", time);
                        map.put("sign", sha1);
                        map.put("a_id", addres);
                        map.put("s_id", s_id);
                        map.put("id", id);
                        map.put("lnsurance", TYPE);
                        map.put("price", substring);
                        if (et_message.getText().toString() != null) {
                            map.put("text", et_message.getText().toString());
                        }
                        presenter.getpost(Contacts.Participation_submit_collages, headmap, map, CollagesBean.class);
                    } else {
                        ToastUtil.showShort(ParticipateDetailsActivity.this, "请添加地址");
                    }
                }
            });
        } else if (data instanceof CollagesBean) {
            CollagesBean collagesBean = (CollagesBean) data;
            String code = collagesBean.getCode();
            if (code.equals("200")) {
                data1 = collagesBean.getData();
                initSpecifcations();
            }
        } else if (data instanceof Zfb_Bean) {
            Zfb_Bean zfb_bean = (Zfb_Bean) data;
            String code = zfb_bean.getCode();
            if (code.equals("200")) {
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(ParticipateDetailsActivity.this);
                        Map<String, String> result = alipay.payV2(zfb_bean.getData(), true);
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
                WxPayBean.DataBean data2 = wxPayBean.getData();
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

    @Override
    public void error(String error) {

    }


    private void initSpecifcations() {
        View view = getLayoutInflater().inflate(R.layout.pop_pay_layout, null);
        PopupWindow popspecifcations = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView iv_dismiss = view.findViewById(R.id.iv_dismiss);
        CheckBox cb_balance = view.findViewById(R.id.cb_balance);
        CheckBox cb_wx = view.findViewById(R.id.cb_wx);
        CheckBox cb_zfb = view.findViewById(R.id.cb_zfb);
        Button bt_submit_orders = view.findViewById(R.id.bt_submit_orders);
        bt_submit_orders.setText("确定付款￥" + substring);
        iv_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popspecifcations.dismiss();
                finish();
                //不支付跳转待支付订单
            }
        });
        cb_balance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_wx.setChecked(false);
                    cb_zfb.setChecked(false);
                }
            }
        });
        cb_wx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_balance.setChecked(false);
                    cb_zfb.setChecked(false);
                }
            }
        });
        cb_zfb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_balance.setChecked(false);
                    cb_wx.setChecked(false);
                }
            }
        });
        bt_submit_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_balance.isChecked()) {
                    ToastUtil.showShort(ParticipateDetailsActivity.this, "余额");
                } else if (cb_wx.isChecked()) {
                    map.clear();
                    time = System.currentTimeMillis();
                    appid = SpzUtils.getString("appid");
                    appsecret = SpzUtils.getString("appsecret");
                    String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                    sha1 = EncryptUtils.getSHA(sha);
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("class", data1.getClassX());
                    map.put("pay_type", "WeChat");
                    map.put("pay_terminal", "Android");
                    map.put("id", data1.getId());
                    presenter.getpost(Contacts.Pay_Choices, headmap, map, WxPayBean.class);
                } else if (cb_zfb.isChecked()) {
                    map.clear();
                    time = System.currentTimeMillis();
                    appid = SpzUtils.getString("appid");
                    appsecret = SpzUtils.getString("appsecret");
                    String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                    sha1 = EncryptUtils.getSHA(sha);
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("class", data1.getClassX());
                    map.put("pay_type", "Alipay");
                    map.put("pay_terminal", "Android");
                    map.put("id", data1.getId());
                    presenter.getpost(Contacts.Pay_Choices, headmap, map, Zfb_Bean.class);
                } else {
                    ToastUtil.showShort(ParticipateDetailsActivity.this, "请选择支付方式");
                }
            }
        });
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        popspecifcations.setFocusable(true);
        popspecifcations.setBackgroundDrawable(new BitmapDrawable());
        popspecifcations.setOutsideTouchable(false);
        popspecifcations.setTouchable(true);
        popspecifcations.setAnimationStyle(R.style.mypopwindow_anim_style);
        popspecifcations.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        popspecifcations.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        payCallback(courseID);
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtil.showShort(App.appContext, "支付成功");
                        IntentUtils.getIntents().Intent(ParticipateDetailsActivity.this, PaySuccessActivity.class, null);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtil.showShort(App.appContext, "支付失败");
                        finish();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
}
