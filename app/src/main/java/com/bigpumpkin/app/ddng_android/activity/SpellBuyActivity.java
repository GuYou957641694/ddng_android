package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
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
import com.bigpumpkin.app.ddng_android.bean.PayResult;
import com.bigpumpkin.app.ddng_android.bean.SpellBuyBean;
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
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;
import com.bumptech.glide.Glide;
import com.hjq.toast.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpellBuyActivity extends BaseActivity implements MyView {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    //网络请求
    private String user, id, sha1, appid, appsecret;
    private long time;
    //拼单
    private String gg_id;
    private String group;
    private String choose_time;
    private String peoplenumber;
    private RelativeLayout rl_address, rl_address_fales, rv_default,relativelayout;
    private TextView tv_name, tv_farm_name, tv_title, tv_gg, tv_price, v_real_pay, tv_welfare, tv_phone, addresss, tv_number_of, tv_insurance, tv_freight;
    private ImageView tv_head_pic, iv_one, iv_two, iv_there, iv_frou, iv_five;
    private Button bt_submit_orders;
    private CircleImageView iv_head_portrait;
    private SpellBuyBean.DataBean.AddressBean address;
    private SpellBuyBean.DataBean.ShopBean shop;
    private String spell;
    private CollagesBean.DataBean data1;
    private static final int SDK_PAY_FLAG = 1;
    private IWXAPI api;
    private Switch switchs;
    int TYPE = 1;
    private String substring;
    private String addressid;
    private int isindex;
    private EditText et_message;
    private LoadingDialog dialog;
    private NestedScrollView scroll;

    @Override
    public int intiLayout() {
        return R.layout.activity_spell_buy;
    }


    @Override
    public void initView() {
        new TitleXML(SpellBuyActivity.this, "确认订单", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String pic = sharedPreferences.getString("pic", "");
        scroll = findViewById(R.id.scroll);
        relativelayout = findViewById(R.id.relativelayout);
        et_message = findViewById(R.id.et_message);
        rv_default = findViewById(R.id.rv_default);
        iv_head_portrait = findViewById(R.id.iv_head_portrait);
        rl_address_fales = findViewById(R.id.rl_address_fales);
        rl_address = findViewById(R.id.rl_address);
        tv_name = findViewById(R.id.tv_name);
        tv_head_pic = findViewById(R.id.tv_head_pic);
        tv_title = findViewById(R.id.tv_titles);
        tv_farm_name = findViewById(R.id.tv_farm_name);
        tv_gg = findViewById(R.id.tv_gg);
        tv_price = findViewById(R.id.tv_price);
        bt_submit_orders = findViewById(R.id.bt_submit_orders);
        v_real_pay = findViewById(R.id.v_real_pay);
        tv_phone = findViewById(R.id.tv_phone);
        addresss = findViewById(R.id.address);
        tv_number_of = findViewById(R.id.tv_number_of);
        iv_one = findViewById(R.id.iv_one);
        iv_two = findViewById(R.id.iv_two);
        iv_there = findViewById(R.id.iv_there);
        iv_frou = findViewById(R.id.iv_frou);
        iv_five = findViewById(R.id.iv_five);
        tv_welfare = findViewById(R.id.tv_welfare);
        tv_insurance = findViewById(R.id.tv_insurance);
        switchs = findViewById(R.id.switchs);
        tv_freight = findViewById(R.id.tv_freight);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        id = getIntent().getExtras().getString("id");
        //拼单
        gg_id = getIntent().getExtras().getString("gg_id");
        //拼单昵称
        group = getIntent().getExtras().getString("group");
        //拼单时间
        choose_time = getIntent().getExtras().getString("choose_time");
        //拼单人数
        peoplenumber = getIntent().getExtras().getString("peoplenumber");
        //私密公开
        spell = getIntent().getExtras().getString("spell");
        tv_number_of.setText(peoplenumber + "人拼单，立即支付开始拼单");
        Glide.with(this).load(pic).into(iv_head_portrait);
        if (peoplenumber.equals("2")) {
            iv_two.setVisibility(View.GONE);
            iv_there.setVisibility(View.GONE);
            iv_frou.setVisibility(View.GONE);
            iv_five.setVisibility(View.GONE);
        } else if (peoplenumber.equals("3")) {
            iv_there.setVisibility(View.GONE);
            iv_frou.setVisibility(View.GONE);
            iv_five.setVisibility(View.GONE);
        } else if (peoplenumber.equals("4")) {
            iv_frou.setVisibility(View.GONE);
            iv_five.setVisibility(View.GONE);
        } else if (peoplenumber.equals("5")) {
            iv_five.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        dialog = new LoadingDialog(this,"玩命加载中...");
        dialog.show();
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        map.put("s_id", id);
        map.put("g_id", gg_id);
        Log.d(TAG, "initData: " + gg_id);
        map.put("num", peoplenumber);
        presenter.getpost(Contacts.Initiate_list_shows, headmap, map, SpellBuyBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof SpellBuyBean) {
            SpellBuyBean spellBuyBean = (SpellBuyBean) data;
            String code = spellBuyBean.getCode();
            if (code.equals("200")) {
                dialog.close();
                relativelayout.setVisibility(View.VISIBLE);
                scroll.setVisibility(View.VISIBLE);
                address = spellBuyBean.getData().getAddress();
                if (address != null) {
                    tv_name.setText(address.getName());
                    tv_phone.setText(address.getTel());
                    addresss.setText(address.getSheng() + address.getShi() + address.getQu() + address.getAddress());
                    addressid = address.getId();
                    rl_address.setVisibility(View.VISIBLE);
                    rl_address.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转用意图
                            Intent in = new Intent(SpellBuyActivity.this, SpellAddressActivity.class);
                            in.putExtra("id", addressid);
                            startActivityForResult(in, 1);
                        }
                    });
                } else {
                    rl_address_fales.setVisibility(View.VISIBLE);
                    rl_address_fales.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(SpellBuyActivity.this, SpellAddressActivity.class);
                            startActivityForResult(in, 1);
                        }
                    });
                }
                shop = spellBuyBean.getData().getShop();
                GlideUtils.loadRoundCircleImagetwo(this, Urls.BASEURL + shop.getPic(), tv_head_pic);
                tv_farm_name.setText(shop.getFidname());
                tv_title.setText(shop.getTitle());
                tv_gg.setText(shop.getSpecifications());
                Tv_Price_Utils.initPriceTv(this, shop.getPrice(), tv_price);
                v_real_pay.setText("¥" + shop.getPrice_pay());
                tv_welfare.setText(shop.getWelfare() + "元");
                tv_insurance.setText(shop.getInsurance() + "元");
                if (shop.getPostage_price().equals("0")) {
                    tv_freight.setText("包邮");
                } else {
                    tv_freight.setText("运费：" + shop.getPostage_price());
                }
            } else if (code.equals("014")) {
                ToastUtils.show("该地区暂无货");
                finish();
            }
        } else if (data instanceof CollagesBean) {
            CollagesBean collagesBean = (CollagesBean) data;
            String code = collagesBean.getCode();
            if (code.equals("200")) {
                initSpecifcations();
                data1 = collagesBean.getData();
            }
        } else if (data instanceof Zfb_Bean) {
            Zfb_Bean zfb_bean = (Zfb_Bean) data;
            String code = zfb_bean.getCode();
            if (code.equals("200")) {
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(SpellBuyActivity.this);
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
        switchs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //开的时候准收宝加上
                    v_real_pay.setText("¥" + shop.getPrice_pay());
                    tv_insurance.setText(shop.getInsurance() + "元");
                    TYPE = 1;
                } else {
                    //关的时候减去
                    double price_pay = shop.getPrice_pay();
                    double insurance = shop.getInsurance();
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
                map.put("a_id", address.getId());
                map.put("s_id", id);
                map.put("Specifications_id", shop.getSpecifications_id());
                map.put("Product_id", shop.getProduct_id());
                map.put("title", group);
                map.put("time", choose_time);
                map.put("num", peoplenumber);
                map.put("type", spell);
                map.put("nsurance", TYPE);
                map.put("price", substring);
                if (et_message.getText().toString() != null) {
                    map.put("text", et_message.getText().toString());
                }
                presenter.getpost(Contacts.Initiate_collages, headmap, map, CollagesBean.class);
            }
        });
    }

    @Override
    public void error(String error) {
        Log.d(TAG, "error: " + error);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                isindex = data.getExtras().getInt("isindex", 0);
                String sheng = data.getExtras().getString("sheng");
                String shi = data.getExtras().getString("shi");
                String qu = data.getExtras().getString("qu");
                String address = data.getExtras().getString("address");
                String name = data.getExtras().getString("name");
                String tel = data.getExtras().getString("tel");
                addressid = data.getExtras().getString("id");
                if (sheng != null && shi != null && qu != null && address != null && name != null && tel != null) {
                    tv_name.setText(name);
                    tv_phone.setText(tel);
                    addresss.setText(sheng + shi + qu + address);
                    if (isindex == 1) {
                        rv_default.setVisibility(View.VISIBLE);
                    } else if (isindex == 2) {
                        rv_default.setVisibility(View.GONE);
                    }
                }
                map.put("a_id", addressid);
                presenter.getpost(Contacts.Initiate_list_shows, headmap, map, SpellBuyBean.class);
            }
        }

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
                    ToastUtil.showShort(SpellBuyActivity.this, "余额");
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
                    ToastUtil.showShort(SpellBuyActivity.this, "请选择支付方式");
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
                        IntentUtils.getIntents().Intent(SpellBuyActivity.this, PaySuccessActivity.class, null);
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
