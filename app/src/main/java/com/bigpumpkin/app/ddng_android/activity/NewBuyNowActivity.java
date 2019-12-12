package com.bigpumpkin.app.ddng_android.activity;

import android.content.Intent;
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
import com.bigpumpkin.app.ddng_android.bean.NewBuyNowBean;
import com.bigpumpkin.app.ddng_android.bean.NewBuyNowOrdersBean;
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
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;
import com.hjq.toast.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//所有商品的立即购买
public class NewBuyNowActivity extends BaseActivity implements MyView, View.OnClickListener {

    private String id, num, v_id, gg_id;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String sha1, appid, appsecret;
    private long time;
    private RelativeLayout rlAddressFales, rlAddress, rvDefault, numJian, rlJia, rlCoupons, rlAdopt, rlWelfare, rlAccept, rlAoptionAgreement;
    private TextView tvName, tvPhone, tvAddress, tvFarmName, tvTitles, tvGg, tvPrice, tvNum, tvWelfare, tvFreight, tvInsurance, tvCoupons, vRealPay, rlNums, tvAdopt;
    private ImageView tvHeadPic;
    private String addressid;
    private String isindex;
    private LoadingDialog dialog;
    private NewBuyNowBean.DataBean.AddressBean address;
    private Switch switchs;
    private int accept = 1;
    private NestedScrollView scroll;
    private Button btSubmitOrders;
    private String pay_price;
    private EditText etMessage;
    private String orde_id, maintain, option_adopt;
    private static final int SDK_PAY_FLAG = 1;
    private IWXAPI api;
    private List<NewBuyNowBean.DataBean.MemberCouponBean> member_coupon;
    private View view, view_welfare, view_accept, viewAoptionAgreement, viewMessage;

    @Override
    public int intiLayout() {
        return R.layout.activity_new_buy_now;
    }

    @Override
    public void initView() {
        new TitleXML(NewBuyNowActivity.this, "确认订单", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        //商品id
        id = getIntent().getStringExtra("id");
        //数量
        num = getIntent().getStringExtra("num");
        //品种id
        v_id = getIntent().getStringExtra("v_id");
        //规格id
        gg_id = getIntent().getStringExtra("gg_id");
        //定制认养id
        maintain = getIntent().getStringExtra("maintain");
        //定制认养文字
        option_adopt = getIntent().getStringExtra("optionAdopt");

        scroll = findViewById(R.id.scroll);
        rlAddressFales = findViewById(R.id.rl_address_fales);
        rlAddress = findViewById(R.id.rl_address);
        tvName = findViewById(R.id.tv_name);
        tvPhone = findViewById(R.id.tv_phone);
        tvAddress = findViewById(R.id.address);
        tvFarmName = findViewById(R.id.tv_farm_name);
        tvHeadPic = findViewById(R.id.tv_head_pic);
        tvTitles = findViewById(R.id.tv_titles);
        tvGg = findViewById(R.id.tv_gg);
        tvPrice = findViewById(R.id.tv_price);
        tvNum = findViewById(R.id.tv_num);
        tvWelfare = findViewById(R.id.tv_welfare);
        tvFreight = findViewById(R.id.tv_freight);
        tvInsurance = findViewById(R.id.tv_insurance);
        tvCoupons = findViewById(R.id.tv_coupons);
        vRealPay = findViewById(R.id.v_real_pay);
        rlNums = findViewById(R.id.num);
        rvDefault = findViewById(R.id.rv_default);
        numJian = findViewById(R.id.num_jian);
        rlJia = findViewById(R.id.rl_jia);
        switchs = findViewById(R.id.switchs);
        btSubmitOrders = findViewById(R.id.bt_submit_orders);
        etMessage = findViewById(R.id.et_message);
        rlCoupons = findViewById(R.id.rl_coupons);
        view = findViewById(R.id.view);
        rlAdopt = findViewById(R.id.rl_adopt);
        rlWelfare = findViewById(R.id.rl_welfare);
        view_welfare = findViewById(R.id.view_welfare);
        rlAccept = findViewById(R.id.rl_accept);
        view_accept = findViewById(R.id.view_accept);
        tvAdopt = findViewById(R.id.tv_adopt);
        rlAoptionAgreement = findViewById(R.id.rl_aoption_agreement);
        viewAoptionAgreement = findViewById(R.id.view_aoption_agreement);
        viewMessage = findViewById(R.id.view_message);
        btSubmitOrders.setOnClickListener(this);
        rlAddressFales.setOnClickListener(this);
        rlAddress.setOnClickListener(this);
        numJian.setOnClickListener(this);
        rlJia.setOnClickListener(this);
        switchs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //选中准收宝
                    accept = 1;
                    String nun = rlNums.getText().toString();
                    map.put("num", nun);
                    map.put("insurance_enable", accept);
                    if (addressid != null) {
                        map.put("a_id", addressid);
                        presenter.getpost(Contacts.new_buy_nows, headmap, map, NewBuyNowBean.class);
                    } else {
                        presenter.getpost(Contacts.new_buy_nows, headmap, map, NewBuyNowBean.class);
                    }
                } else {
                    //没选中
                    accept = 2;
                    String nun = rlNums.getText().toString();
                    map.put("num", nun);
                    map.put("insurance_enable", accept);
                    if (addressid != null) {
                        map.put("a_id", addressid);
                        presenter.getpost(Contacts.new_buy_nows, headmap, map, NewBuyNowBean.class);
                    } else {
                        presenter.getpost(Contacts.new_buy_nows, headmap, map, NewBuyNowBean.class);
                    }
                }
            }
        });
    }

    @Override
    public void initData() {
        dialog = new LoadingDialog(this, "玩命加载中...");
        dialog.show();
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
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
        map.put("v_id", v_id);
        map.put("sp_id", gg_id);
        map.put("num", num);
        map.put("insurance_enable", accept);
        Log.d(TAG, "initData: " + "v_id" + v_id + "gg_id" + gg_id);
        presenter.getpost(Contacts.new_buy_nows, headmap, map, NewBuyNowBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof NewBuyNowBean) {
            scroll.setVisibility(View.VISIBLE);
            NewBuyNowBean newBuyNowBean = (NewBuyNowBean) data;
            String code = newBuyNowBean.getCode();
            if (code.equals("200")) {
                address = newBuyNowBean.getData().getAddress();
                member_coupon = newBuyNowBean.getData().getMember_coupon();
                NewBuyNowBean.DataBean.ShopBean shop = newBuyNowBean.getData().getShop();
                String jian = shop.getJian();
                if (address != null) {
                    //在有默认地址的情况赋值
                    rlAddress.setVisibility(View.VISIBLE);
                    rlAddressFales.setVisibility(View.GONE);
                    tvName.setText(address.getName());
                    tvPhone.setText(address.getTel());
                    tvAddress.setText(address.getSheng() + address.getShi() + address.getQu() + address.getAddress());
                    addressid = address.getId();
                    isindex = address.getIsindex();
                    if (isindex.equals("1")) {
                        rvDefault.setVisibility(View.VISIBLE);
                    } else if (isindex.equals("2")) {
                        rvDefault.setVisibility(View.GONE);
                    }
                } else {
                    //提示去添加地址
                    rlAddressFales.setVisibility(View.VISIBLE);
                    rlAddress.setVisibility(View.GONE);
                }
                if (member_coupon != null && member_coupon.size() > 0 && jian != null) {
                    //说明有优惠券
                    rlCoupons.setVisibility(View.VISIBLE);
                    view.setVisibility(View.VISIBLE);
                    tvCoupons.setText("-" + jian);
                } else {
                    //没有优惠券就不显示
                    rlCoupons.setVisibility(View.GONE);
                    view.setVisibility(View.GONE);
                }
                if (shop.getLanmu().equals("1")) {
                    view_accept.setVisibility(View.VISIBLE);
                    view_welfare.setVisibility(View.VISIBLE);
                    rlAdopt.setVisibility(View.VISIBLE);
                    rlWelfare.setVisibility(View.VISIBLE);
                    rlAccept.setVisibility(View.VISIBLE);
                    viewAoptionAgreement.setVisibility(View.VISIBLE);
                    tvWelfare.setText("+" + shop.getWelfare() + "元");
                } else if (shop.getLanmu().equals("6")) {
                    view_accept.setVisibility(View.VISIBLE);
                    view_welfare.setVisibility(View.VISIBLE);
                    rlAdopt.setVisibility(View.VISIBLE);
                    rlWelfare.setVisibility(View.VISIBLE);
                    rlAccept.setVisibility(View.VISIBLE);
                    viewAoptionAgreement.setVisibility(View.VISIBLE);
                    tvWelfare.setText("+" + shop.getWelfare() + "元");
                } else {
                    view_accept.setVisibility(View.GONE);
                    view_welfare.setVisibility(View.GONE);
                    rlAdopt.setVisibility(View.GONE);
                    rlWelfare.setVisibility(View.GONE);
                    rlAccept.setVisibility(View.GONE);
                    viewAoptionAgreement.setVisibility(View.GONE);
                    rlAoptionAgreement.setVisibility(View.GONE);
                    viewMessage.setVisibility(View.GONE);
                }
                if (option_adopt != null) {
                    tvAdopt.setText(option_adopt);
                } else {
                    tvAdopt.setText("默认常规维护");
                }
                if (shop.getInsurance() != null) {
                    tvInsurance.setText("+" + shop.getInsurance() + "元");
                } else {
                    tvInsurance.setText("");
                }

                tvFarmName.setText(shop.getEssay_name());
                tvTitles.setText(shop.getTitle());
                tvGg.setText("已选：" + shop.getVariety() + shop.getSp_title());
                tvNum.setText("×" + shop.getSum());
                rlNums.setText(shop.getSum());
                Tv_Price_Utils.initPriceTv(this, shop.getPrice(), tvPrice);
                GlideUtils.loadRoundCircleImagethere(this, Urls.BASEURL + shop.getPic(), tvHeadPic);
                if (shop.getPostage_price() != null) {
                    if (shop.getPostage_price().equals("0.00")) {
                        tvFreight.setText("包邮");
                    } else {
                        tvFreight.setText("邮费：" + shop.getPostage_price());
                    }
                } else {
                    tvFreight.setText("包邮");
                }

                pay_price = shop.getPay_price();
                vRealPay.setText(pay_price);
                dialog.close();
            } else if (code.equals("014")) {
                ToastUtils.show("该地区暂无货 ");
                finish();
            }
        } else if (data instanceof NewBuyNowOrdersBean) {
            NewBuyNowOrdersBean newBuyNowOrdersBean = (NewBuyNowOrdersBean) data;
            ToastUtils.show(newBuyNowOrdersBean.getCode() + "");
            if (newBuyNowOrdersBean.getCode().equals("200")) {
                initSpecifcations();
                orde_id = newBuyNowOrdersBean.getData().getOrde_id();
            } else if (newBuyNowOrdersBean.getCode().equals("004")) {

            }
        } else if (data instanceof Zfb_Bean) {
            Zfb_Bean zfb_bean = (Zfb_Bean) data;
            String code = zfb_bean.getCode();
            Log.d(TAG, "success: " + zfb_bean.getData());
            if (code.equals("200")) {
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(NewBuyNowActivity.this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_address:
                //跳转用意图
                Intent in = new Intent(NewBuyNowActivity.this, SpellAddressActivity.class);
                in.putExtra("id", addressid);
                startActivityForResult(in, 1);
                break;
            case R.id.rl_address_fales:
                Intent intent = new Intent(NewBuyNowActivity.this, SpellAddressActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.num_jian:
                String nunb = rlNums.getText().toString();
                if (nunb.equals("1")) {
                    ToastUtils.show("最低数量为1");
                } else {
                    int number = Integer.parseInt(nunb);
                    --number;
                    map.put("num", number);
                    map.put("insurance_enable", accept);
                    dialog = new LoadingDialog(this, "玩命加载中...");
                    dialog.show();
                    if (addressid != null) {
                        map.put("a_id", addressid);
                        presenter.getpost(Contacts.new_buy_nows, headmap, map, NewBuyNowBean.class);
                    } else {
                        presenter.getpost(Contacts.new_buy_nows, headmap, map, NewBuyNowBean.class);
                    }
                }
                break;
            case R.id.rl_jia:
                dialog = new LoadingDialog(this, "玩命加载中...");
                dialog.show();
                String nun = rlNums.getText().toString();
                int number = Integer.parseInt(nun);
                ++number;
                map.put("num", number);
                map.put("insurance_enable", accept);
                if (addressid != null) {
                    map.put("a_id", addressid);
                    presenter.getpost(Contacts.new_buy_nows, headmap, map, NewBuyNowBean.class);
                } else {
                    presenter.getpost(Contacts.new_buy_nows, headmap, map, NewBuyNowBean.class);
                }
                break;
            case R.id.bt_submit_orders:
                map.clear();
                String ordersnun = rlNums.getText().toString();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                map.put("a_id", addressid);
                map.put("s_id", id);
                map.put("v_id", v_id);
                map.put("sp_id", gg_id);
                map.put("num", ordersnun);
                map.put("price", pay_price);
                map.put("insurance_enable", accept);
                map.put("message", etMessage.getText().toString());
                map.put("terminal", "Android");
                presenter.getpost(Contacts.DirectSubmitOrders, headmap, map, NewBuyNowOrdersBean.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                addressid = data.getExtras().getString("id");
                map.put("a_id", addressid);
                map.put("num", rlNums.getText().toString());
                map.put("insurance_enable", accept);
                dialog = new LoadingDialog(this, "玩命加载中...");
                dialog.show();
                presenter.getpost(Contacts.new_buy_nows, headmap, map, NewBuyNowBean.class);
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
                    ToastUtil.showShort(NewBuyNowActivity.this, "余额");
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
                    map.put("orde_id", orde_id);
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
                    map.put("orde_id", orde_id);
                    map.put("pay_type", "Alipay");
                    map.put("terminal_pay", "Android");
                    presenter.getpost(Contacts.DirectPayments, headmap, map, Zfb_Bean.class);
                } else {
                    ToastUtils.show("请选择支付方式");
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
                        IntentUtils.getIntents().Intent(NewBuyNowActivity.this, PaySuccessActivity.class, null);
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
    };

}
