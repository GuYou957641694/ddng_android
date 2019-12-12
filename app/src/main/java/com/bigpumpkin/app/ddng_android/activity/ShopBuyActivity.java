package com.bigpumpkin.app.ddng_android.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.ShopBuyAdapter;
import com.bigpumpkin.app.ddng_android.adapter.ShopBuyCouponsAdapter;
import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.BuySubmitOrders;
import com.bigpumpkin.app.ddng_android.bean.PayResult;
import com.bigpumpkin.app.ddng_android.bean.ShopBuyBean;
import com.bigpumpkin.app.ddng_android.bean.WxPayBean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
import com.bigpumpkin.app.ddng_android.config.WXConFig;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.MyExpandableListView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;
import com.hjq.toast.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopBuyActivity extends BaseActivity implements MyView, View.OnClickListener {

    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private String[] lists;
    String id = "";
    private String appid;
    private String appsecret;
    private long time;
    private String sha1, addressId, isindex;
    private TextView name, phone, address, tvAllNum, tvAllPrice;
    private RelativeLayout rlDz, rlAddressFales, rlDefault;
    private ShopBuyBean.DataBean.AddressBean address1;
    private MyExpandableListView elConfirm;
    private Button btSubmit;
    private boolean type;   //是否第一次加载数据
    private ShopBuyBean shopBuyBean;  //总数据
    private String subaccept;   //准收宝
    private String subcoupons; //店铺优惠券优惠卷id
    private List<ShopBuyBean.DataBean.ShopBean> shop;
    private String substring;
    private String substring1;
    private String data1;
    private String pay_price;
    private static final int SDK_PAY_FLAG = 1;
    private IWXAPI api;
    private ShopBuyBean.DataBean.ShopBean.MemberCouponUseBean member_coupon_use;
    private PopupWindow popspecifcations;

    @Override
    public int intiLayout() {
        return R.layout.activity_shop_buy;
    }

    @Override
    public void initView() {
        new TitleXML(ShopBuyActivity.this, "确认订单", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        rlDz = findViewById(R.id.rl_dz);
        rlAddressFales = findViewById(R.id.rl_address_fales);
        rlDefault = findViewById(R.id.rl_default);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        elConfirm = findViewById(R.id.el_id);
        tvAllNum = findViewById(R.id.tv_all_num);
        tvAllPrice = findViewById(R.id.tv_all_price);
        btSubmit = findViewById(R.id.bt_submit);
        btSubmit.setOnClickListener(this);
        rlDz.setOnClickListener(this);
        rlAddressFales.setOnClickListener(this);
        //购物车传过来的二级列表ID
        lists = getIntent().getExtras().getStringArray("list");
        if (lists != null) {
            List<String> list = Arrays.asList(lists);
            for (int i = 0; i < lists.length; i++) {
                if (id == "") {
                    id = list.get(i);
                } else {
                    id = id + "," + list.get(i);
                }
            }
        }
        Log.d(TAG, "initView: " + id);
    }

    @Override
    public void initData() {
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        map.put("c_id", id);
        Log.d(TAG, "initData: " + "appid" + appid + "appsecret" + appsecret + "timestamp" + time + "sign" + sha1 + "id" + id);
        presenter.getpost(Contacts.BuyConfirmOrders, headmap, map, ShopBuyBean.class);
        type = true;
    }

    @Override
    public void success(Object data) {
        if (data instanceof ShopBuyBean) {
            shopBuyBean = (ShopBuyBean) data;
            address1 = shopBuyBean.getData().getAddress();
            shop = shopBuyBean.getData().getShop();
            //头部地址
            initAddress();
            pay_price = shopBuyBean.getData().getPay_price();
            //这里需要判断是否第一次 加载 第一次的话需要都设置为true
            if (type == true) {
                for (int i = 0; i < shop.size(); i++) {
                    //这里for循环是要把说有的带准收宝默认为true
                    String f_num_insurance = shop.get(i).getF_num_insurance();
                    if (f_num_insurance != null) {
                        //不等于null的话说明有准收宝
                        shop.get(i).setInsurance(true);
                    }
                }
            } else {
                //不是第一次加载
                for (int i = 0; i < shop.size(); i++) {
                    //这里for循环是要把说有的带准收宝默认为true
                    String f_num_insurance = shop.get(i).getF_num_insurance();
                    if (f_num_insurance != null) {
                        //不等于null的话说明有准收宝
                        if (shop.get(i).getInsurance_enable_e() == 1) {
                            //要准收宝、
                            shop.get(i).setInsurance(true);
                        } else {
                            //不要准收宝
                            shop.get(i).setInsurance(false);
                        }
                    }
                }
            }
            ShopBuyAdapter shopBuyAdapter = new ShopBuyAdapter(shop, this, shopBuyBean.getData().getTotal_Postage());
            elConfirm.setAdapter(shopBuyAdapter);
            tvAllPrice.setText(pay_price + "元");
            //展開
            for (int i = 0; i < shopBuyAdapter.getGroupCount(); i++) {
                elConfirm.expandGroup(i);
            }
            type = false;
            //准收宝
            shopBuyAdapter.setListener(new ShopBuyAdapter.onListener() {
                @Override
                public void OnListener(int group, int child, boolean isChecked) {
                    StringBuilder strcoupons = new StringBuilder();
                    //要取优惠券给后台
                    //循环所有店铺优惠券
                    for (int i = 0; i < shop.size(); i++) {
                        ShopBuyBean.DataBean.ShopBean.MemberCouponUseBean member_coupon_use = shop.get(i).getMember_coupon_Use();
                        if (member_coupon_use != null) {
                            //这里判断优惠券是否有优惠卷
                            strcoupons.append(shop.get(i).getF_id() + "-" + shop.get(i).getMember_coupon_Use().getId() + ",");
                        }
                    }
                    subcoupons = strcoupons.substring(0, strcoupons.length() - 1);

                    map.clear();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("c_id", id);

                    //这里也可以写成for循环判断switchs是否选中也可以
                    if (isChecked == false) {
                        //准收宝
                        shop.get(group).setInsurance(true);
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < shop.size(); i++) {
                            //这里for循环是要把说有的带准收宝默认为true
                            String f_num_insurance = shop.get(i).getF_num_insurance();
                            if (f_num_insurance != null) {
                                //不等于null的话说明有准收宝,然后去拼接
                                if (shop.get(i).isInsurance() == true) {
                                    //这里拼也需要判断，判断其他店铺准收宝的状态 if为true他是选中的准收宝 else是不选择的
                                    stringBuilder.append(shop.get(i).getF_id() + "-1,");
                                } else {
                                    stringBuilder.append(shop.get(i).getF_id() + "-2,");
                                }
                            }
                        }
                        subaccept = stringBuilder.substring(0, stringBuilder.length() - 1);
                        map.put("id", addressId);
                        //优惠券id
                        map.put("shop_coupon_id", subcoupons);
                        map.put("insurance_enable", subaccept);
                        presenter.getpost(Contacts.BuyConfirmOrders, headmap, map, ShopBuyBean.class);
                    } else {
                        shop.get(group).setInsurance(false);
                        StringBuilder insurance_enable = new StringBuilder();
                        for (int i = 0; i < shop.size(); i++) {
                            //这里for循环是要把说有的带准收宝默认为true
                            String f_num_insurance = shop.get(i).getF_num_insurance();
                            if (f_num_insurance != null) {
                                //不等于null的话说明有准收宝,然后去拼接
                                if (shop.get(i).isInsurance() == true) {
                                    //这里拼也需要判断，判断其他店铺准收宝的状态 if为true他是选中的准收宝 else是不选择的
                                    insurance_enable.append(shop.get(i).getF_id() + "-1,");
                                } else {
                                    insurance_enable.append(shop.get(i).getF_id() + "-2,");
                                }
                            }
                        }
                        subaccept = insurance_enable.substring(0, insurance_enable.length() - 1);
                        map.put("id", addressId);
                        //优惠券id
                        map.put("shop_coupon_id", subcoupons);
                        map.put("insurance_enable", subaccept);
                        presenter.getpost(Contacts.BuyConfirmOrders, headmap, map, ShopBuyBean.class);
                    }
                }
            });

            //优惠券
            shopBuyAdapter.setOnRightClickListener(new ShopBuyAdapter.OnRightClickListener() {
                @Override
                public void onClick(int group, int child) {
                    initSinglePurchase(group);
                }
            });
            //不能点击收缩
            elConfirm.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    return true;
                }
            });
        } else if (data instanceof BuySubmitOrders) {
            BuySubmitOrders buySubmitOrders = (BuySubmitOrders) data;
            if (buySubmitOrders.getCode().equals("200")) {
                data1 = buySubmitOrders.getData();
                initSpecifcations();
            } else {
                ToastUtils.show("订单异常" + buySubmitOrders.getCode());
            }
        } else if (data instanceof Zfb_Bean) {
            Zfb_Bean zfb_bean = (Zfb_Bean) data;
            String code = zfb_bean.getCode();
            Log.d(TAG, "success: " + zfb_bean.getData());
            if (code.equals("200")) {
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(ShopBuyActivity.this);
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


    private void initAddress() {
        //判断有没有地址
        if (address != null) {
            rlDz.setVisibility(View.VISIBLE);
            rlAddressFales.setVisibility(View.GONE);
            name.setText(address1.getName());
            phone.setText(address1.getTel());
            address.setText(address1.getSheng() + address1.getShi() + address1.getQu() + address1.getAddress());
            addressId = address1.getId();
            isindex = address1.getIsindex();
            if (isindex.equals("1")) {
                rlDefault.setVisibility(View.VISIBLE);
            } else if (isindex.equals("2")) {
                rlDefault.setVisibility(View.GONE);
            }
        } else {
            rlDz.setVisibility(View.GONE);
            rlAddressFales.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_dz:
                //切换地址
                //跳转用意图
                Intent in = new Intent(ShopBuyActivity.this, SpellAddressActivity.class);
                in.putExtra("id", addressId);
                startActivityForResult(in, 1);
                break;
            case R.id.rl_address_fales:
                //没有地址去添加地址
                //跳转用意图
                Intent ins = new Intent(ShopBuyActivity.this, SpellAddressActivity.class);
                ins.putExtra("id", addressId);
                startActivityForResult(ins, 1);
                break;
            case R.id.bt_submit:
                //提交订单
                initSuBit();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                addressId = data.getExtras().getString("id");
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < shop.size(); i++) {
                    //这里for循环是要把说有的带准收宝默认为true
                    String f_num_insurance = shop.get(i).getF_num_insurance();
                    if (f_num_insurance != null) {
                        //不等于null的话说明有准收宝,然后去拼接
                        if (shop.get(i).isInsurance() == true) {
                            //这里拼也需要判断，判断其他店铺准收宝的状态 if为true他是选中的准收宝 else是不选择的
                            stringBuilder.append(shop.get(i).getF_id() + "-1,");
                        } else {
                            stringBuilder.append(shop.get(i).getF_id() + "-2,");
                        }
                    }
                }
                if (stringBuilder.length() > 0) {
                    substring = stringBuilder.substring(0, stringBuilder.length() - 1);
                }
                StringBuilder strcoupon = new StringBuilder();
                //循环所有店铺优惠券 要循环使用的 因为刚刚存的是使用的实体类
                for (int i = 0; i < shop.size(); i++) {
                    ShopBuyBean.DataBean.ShopBean.MemberCouponUseBean member_coupon_use = shop.get(i).getMember_coupon_Use();
                    if (member_coupon_use != null) {
                        //这里判断优惠券是否有优惠卷
                        strcoupon.append(shop.get(i).getF_id() + "-" + shop.get(i).getMember_coupon_Use().getId() + ",");
                    }
                }
                if (strcoupon.length() > 0) {
                    substring1 = strcoupon.substring(0, strcoupon.length() - 1);
                }
                map.clear();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                map.put("c_id", id);
                map.put("id", addressId);
                //优惠券
                if (substring1.length() > 0 && substring1 != null) {
                    map.put("shop_coupon_id", substring1);
                }
                //准收宝
                if (substring.length() > 0 && substring != null) {
                    map.put("insurance_enable", substring);
                }
                presenter.getpost(Contacts.BuyConfirmOrders, headmap, map, ShopBuyBean.class);
            }
        }
    }


    private void initSuBit() {
        //提交订单
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < shop.size(); i++) {
            //这里for循环是要把说有的带准收宝默认为true
            String f_num_insurance = shop.get(i).getF_num_insurance();
            if (f_num_insurance != null) {
                //不等于null的话说明有准收宝,然后去拼接
                if (shop.get(i).isInsurance() == true) {
                    //这里拼也需要判断，判断其他店铺准收宝的状态 if为true他是选中的准收宝 else是不选择的
                    stringBuilder.append(shop.get(i).getF_id() + "-1,");
                } else {
                    stringBuilder.append(shop.get(i).getF_id() + "-2,");
                }
            }
        }
        if (stringBuilder.length() > 0) {
            substring = stringBuilder.substring(0, stringBuilder.length() - 1);
        }
        StringBuilder strcoupon = new StringBuilder();
        //循环所有店铺优惠券
        for (int i = 0; i < shop.size(); i++) {
            member_coupon_use = shop.get(i).getMember_coupon_Use();
            if (member_coupon_use != null) {
                //这里判断优惠券是否有优惠卷
                strcoupon.append(shop.get(i).getMember_coupon_Use().getId() + ",");
            }
        }
        if (strcoupon != null && strcoupon.length() > 0) {
            substring1 = strcoupon.substring(0, strcoupon.length() - 1);
            Log.d(TAG, "initSuBit: " + strcoupon);
        }
        map.clear();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        map.put("cart_id", id);
        map.put("a_id", addressId);
        map.put("price", pay_price);
        //优惠券
        if (substring1 != null) {
            map.put("coupon_id", substring1);
        }
        //准收宝
        if (substring != null) {
            map.put("insurance_enable", substring);
        }
        presenter.getpost(Contacts.BuySubmitOrders, headmap, map, BuySubmitOrders.class);
    }

    private void initSpecifcations() {
        View view = getLayoutInflater().inflate(R.layout.pop_pay_layout, null);
        popspecifcations = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView iv_dismiss = view.findViewById(R.id.iv_dismiss);
        CheckBox cb_balance = view.findViewById(R.id.cb_balance);
        CheckBox cb_wx = view.findViewById(R.id.cb_wx);
        CheckBox cb_zfb = view.findViewById(R.id.cb_zfb);
        Button bt_submit_orders = view.findViewById(R.id.bt_submit_orders);
        bt_submit_orders.setText("确定付款￥" + pay_price);
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
                    ToastUtil.showShort(ShopBuyActivity.this, "余额");
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
                    map.put("orde_id", data1);
                    map.put("pay_type", "WeChat");
                    map.put("terminal_pay", "Android");
                    presenter.getpost(Contacts.DirectPayments, headmap, map, WxPayBean.class);
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
                    map.put("orde_id", data1);
                    map.put("pay_type", "Alipay");
                    map.put("terminal_pay", "Android");
                    presenter.getpost(Contacts.DirectPayments, headmap, map, Zfb_Bean.class);
                } else {
                    ToastUtils.show("请选择支付方式");
                }
            }
        });
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    popspecifcations.dismiss();
                    finish();
                    return true;
                }
                return false;
            }
        });
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        popspecifcations.setFocusable(false);
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

    private void initSinglePurchase(int group) {
        List<ShopBuyBean.DataBean.ShopBean.MemberCouponBean> member_coupon = shop.get(group).getMember_coupon();
        View view = getLayoutInflater().inflate(R.layout.shop_buy_pop, null);
        PopupWindow mPopupWindowthere = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView recyclerView = view.findViewById(R.id.rv);
        Button btNotUse = view.findViewById(R.id.bt_not_use);
        ShopBuyCouponsAdapter shopBuyCouponsAdapter = new ShopBuyCouponsAdapter(this, shop.get(group).getMember_coupon());
        recyclerView.setAdapter(shopBuyCouponsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShopBuyActivity.this, LinearLayoutManager.VERTICAL, false));
        shopBuyCouponsAdapter.setListener(new ShopBuyCouponsAdapter.onListener() {
            @Override
            public void OnListener(int location) {
                //准收宝
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < shop.size(); i++) {
                    //这里for循环是要把说有的带准收宝默认为true
                    String f_num_insurance = shop.get(i).getF_num_insurance();
                    if (f_num_insurance != null) {
                        //不等于null的话说明有准收宝,然后去拼接
                        if (shop.get(i).isInsurance() == true) {
                            //这里拼也需要判断，判断其他店铺准收宝的状态 if为true他是选中的准收宝 else是不选择的
                            stringBuilder.append(shop.get(i).getF_id() + "-1,");
                        } else {
                            stringBuilder.append(shop.get(i).getF_id() + "-2,");
                        }
                    }
                }
                if (stringBuilder.length() > 0) {
                    substring = stringBuilder.substring(0, stringBuilder.length() - 1);
                }
                String id1 = member_coupon.get(location).getId();
                //这里将id先存入实体类  注意这里减的优惠价格并没有存还是之前的 这里会直接请求接口并没有影响
                shop.get(group).getMember_coupon_Use().setId(id1);
                StringBuilder strcoupon = new StringBuilder();
                //循环所有店铺优惠券 要循环使用的 因为刚刚存的是使用的实体类
                for (int i = 0; i < shop.size(); i++) {
                    ShopBuyBean.DataBean.ShopBean.MemberCouponUseBean member_coupon_use = shop.get(i).getMember_coupon_Use();
                    if (member_coupon_use != null) {
                        //这里判断优惠券是否有优惠卷
                        strcoupon.append(shop.get(i).getF_id() + "-" + shop.get(i).getMember_coupon_Use().getId() + ",");
                    }
                }
                if (strcoupon.length() > 0) {
                    substring1 = strcoupon.substring(0, strcoupon.length() - 1);
                }
                map.clear();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                map.put("c_id", id);
                map.put("id", addressId);
                //优惠券
                if (substring1.length() > 0 && substring1 != null) {
                    map.put("shop_coupon_id", substring1);
                }
                //准收宝
                if (substring.length() > 0 && substring != null) {
                    map.put("insurance_enable", substring);
                }
                presenter.getpost(Contacts.BuyConfirmOrders, headmap, map, ShopBuyBean.class);
                mPopupWindowthere.dismiss();
            }
        });

        //点击不使用
        btNotUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //准收宝
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < shop.size(); i++) {
                    //这里for循环是要把说有的带准收宝默认为true
                    String f_num_insurance = shop.get(i).getF_num_insurance();
                    if (f_num_insurance != null) {
                        //不等于null的话说明有准收宝,然后去拼接
                        if (shop.get(i).isInsurance() == true) {
                            //这里拼也需要判断，判断其他店铺准收宝的状态 if为true他是选中的准收宝 else是不选择的
                            stringBuilder.append(shop.get(i).getF_id() + "-1,");
                        } else {
                            stringBuilder.append(shop.get(i).getF_id() + "-2,");
                        }
                    }
                }
                String substring = stringBuilder.substring(0, stringBuilder.length() - 1);
                //这里将id先存入实体类  注意这里减的优惠价格并没有存还是之前的 这里会直接请求接口并没有影响
                shop.get(group).getMember_coupon_Use().setId("0");
                StringBuilder strcoupon = new StringBuilder();
                //循环所有店铺优惠券 要循环使用的 因为刚刚存的是使用的实体类
                for (int i = 0; i < shop.size(); i++) {
                    ShopBuyBean.DataBean.ShopBean.MemberCouponUseBean member_coupon_use = shop.get(i).getMember_coupon_Use();
                    if (member_coupon_use != null) {
                        //这里判断优惠券是否有优惠卷
                        strcoupon.append(shop.get(i).getF_id() + "-" + shop.get(i).getMember_coupon_Use().getId() + ",");
                    }
                }
                String substring1 = strcoupon.substring(0, strcoupon.length() - 1);
                map.clear();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                map.put("c_id", id);
                map.put("id", addressId);
                //优惠券
                map.put("shop_coupon_id", substring1);
                //准收宝
                map.put("insurance_enable", substring);
                presenter.getpost(Contacts.BuyConfirmOrders, headmap, map, ShopBuyBean.class);
                mPopupWindowthere.dismiss();
            }
        });
        //产生背景变暗效果
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindowthere.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        mPopupWindowthere.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        mPopupWindowthere.setOutsideTouchable(true);
        //设置可以点击
        mPopupWindowthere.setTouchable(true);
        //mPopupWindow，指定刚才定义的style
        mPopupWindowthere.setAnimationStyle(R.style.mypopwindow_anim_style);

        //设置popupWindow消失时的监听
        mPopupWindowthere.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindowthere.showAtLocation(view, Gravity.BOTTOM, 0, 0);
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
                        IntentUtils.getIntents().Intent(ShopBuyActivity.this, PaySuccessActivity.class, null);
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
