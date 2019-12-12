package com.bigpumpkin.app.ddng_android.activity;

import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Ngz_Bean;
import com.bigpumpkin.app.ddng_android.bean.PayResult;
import com.bigpumpkin.app.ddng_android.bean.WxPayBean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Alipay_Bean;
import com.bigpumpkin.app.ddng_android.config.WXConFig;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

public class RechargeActivity extends BaseActivity implements View.OnClickListener, MyView {

    private CheckBox mCheckbox1;
    private CheckBox mCheckbox2;
    private CheckBox mCheckbox3;
    private CheckBox mCheckbox4;
    private CheckBox mCheckbox5;
    private CheckBox mCheckbox6, wx, zfb;
    private String html, htmlzfb, htmlwx;
    private String html2;
    private String html3;
    private String html4;
    private String html5;
    private String html6;
    ImageView back;
    private Button accout;
    private int chargetypeid;
    private static final int SDK_PAY_FLAG = 1;
    private MyPresenterImpl presenter;
    private TextView yu;
    private LinearLayout pay_rank, lin_up_rules;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> map;
    private long time;
    private String appid;
    private String appsecret;
    private String sha;
    private String sha1;

    @Override
    public int intiLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initView() {
        headmap = new HashMap<>();
        map = new HashMap<>();
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        presenter = new MyPresenterImpl(this);
        yu = findViewById(R.id.yu);
        pay_rank = findViewById(R.id.pay_rank);
        mCheckbox1 = (CheckBox) findViewById(R.id.checkbox1);
        mCheckbox1.setOnClickListener(this);
        mCheckbox2 = (CheckBox) findViewById(R.id.checkbox2);
        mCheckbox2.setOnClickListener(this);
        mCheckbox3 = (CheckBox) findViewById(R.id.checkbox3);
        mCheckbox3.setOnClickListener(this);
        mCheckbox4 = (CheckBox) findViewById(R.id.checkbox4);
        mCheckbox4.setOnClickListener(this);
        mCheckbox5 = (CheckBox) findViewById(R.id.checkbox5);
        mCheckbox5.setOnClickListener(this);
        mCheckbox6 = (CheckBox) findViewById(R.id.checkbox6);
        wx = (CheckBox) findViewById(R.id.wx);
        wx.setOnClickListener(this);
        zfb = (CheckBox) findViewById(R.id.zfb);
        zfb.setOnClickListener(this);
        mCheckbox6.setOnClickListener(this);
        accout = findViewById(R.id.account_btn);
        accout.setOnClickListener(this);
        lin_up_rules = findViewById(R.id.lin_up_rules);
        lin_up_rules.setOnClickListener(this);

        htmlzfb = "<font color=\"#85D253\" >" + "支付宝" + "</font>";
        htmlwx = "<font color=\"#85D253\" >" + "微信" + "</font>";
        html = "<font color=\"#85D253\" >" + "100元" + "</font>";
        html2 = "<font color=\"#85D253\" >" + "500元" + "</font>";
        html3 = "<font color=\"#85D253\" >" + "1000元" + "</font>";
        html4 = "<font color=\"#85D253\" >" + "2000元" + "</font>";
        html5 = "<font color=\"#85D253\" >" + "5000元" + "</font>";
        html6 = "<font color=\"#85D253\" >" + "10000元" + "</font>";
        mCheckbox1.setChecked(true);
        mCheckbox2.setChecked(false);
        mCheckbox3.setChecked(false);
        mCheckbox4.setChecked(false);
        mCheckbox5.setChecked(false);
        mCheckbox6.setChecked(false);
        chargetypeid = 1;
        pay_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getIntents().Intent(RechargeActivity.this, PayRankActivity.class, null);
            }
        });
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        //查询余额
        presenter.getpost(Contacts.ngz, headmap, map, Ngz_Bean.class);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.checkbox1:
                mCheckbox1.setChecked(true);
                mCheckbox2.setChecked(false);
                mCheckbox3.setChecked(false);
                mCheckbox4.setChecked(false);
                mCheckbox5.setChecked(false);
                mCheckbox6.setChecked(false);
                if (mCheckbox1.isChecked() == true) {
                    String htmlb = "<font color=\"#ffffff\" >" + "100元" + "</font>";
                    htmlb = htmlb.replace("\r\n", "<br />");
                    mCheckbox1.setText(Html.fromHtml(htmlb));
                    mCheckbox2.setText(Html.fromHtml(html2));
                    mCheckbox3.setText(Html.fromHtml(html3));
                    mCheckbox4.setText(Html.fromHtml(html4));
                    mCheckbox5.setText(Html.fromHtml(html5));
                    mCheckbox6.setText(Html.fromHtml(html6));

                } else if (mCheckbox1.isChecked() == false) {
                    mCheckbox1.setText(Html.fromHtml(html));
                }
                chargetypeid = 1;
                break;
            case R.id.checkbox2:
                mCheckbox1.setChecked(false);
                mCheckbox2.setChecked(true);
                mCheckbox3.setChecked(false);
                mCheckbox4.setChecked(false);
                mCheckbox5.setChecked(false);
                mCheckbox6.setChecked(false);
                if (mCheckbox2.isChecked() == true) {
                    String html2b = "<font color=\"#ffffff\" >" + "500元" + "</font>";
                    html2b = html2b.replace("\r\n", "<br />");
                    mCheckbox2.setText(Html.fromHtml(html2b));
                    mCheckbox1.setText(Html.fromHtml(html));
                    mCheckbox3.setText(Html.fromHtml(html3));
                    mCheckbox4.setText(Html.fromHtml(html4));
                    mCheckbox5.setText(Html.fromHtml(html5));
                    mCheckbox6.setText(Html.fromHtml(html6));

                } else if (mCheckbox2.isChecked() == false) {
                    mCheckbox2.setText(Html.fromHtml(html2));
                }
                chargetypeid = 2;
                break;
            case R.id.checkbox3:
                mCheckbox1.setChecked(false);
                mCheckbox2.setChecked(false);
                mCheckbox3.setChecked(true);
                mCheckbox4.setChecked(false);
                mCheckbox5.setChecked(false);
                mCheckbox6.setChecked(false);
                if (mCheckbox3.isChecked() == true) {
                    String html3b = "<font color=\"#ffffff\" >" + "1000元" + "</font>";
                    html3b = html3b.replace("\r\n", "<br />");
                    mCheckbox3.setText(Html.fromHtml(html3b));

                    mCheckbox1.setText(Html.fromHtml(html));
                    mCheckbox2.setText(Html.fromHtml(html2));
                    mCheckbox4.setText(Html.fromHtml(html4));
                    mCheckbox5.setText(Html.fromHtml(html5));
                    mCheckbox6.setText(Html.fromHtml(html6));

                } else if (mCheckbox3.isChecked() == false) {
                    mCheckbox3.setText(Html.fromHtml(html3));

                }
                chargetypeid = 3;
                break;
            case R.id.checkbox4:
                mCheckbox1.setChecked(false);
                mCheckbox2.setChecked(false);
                mCheckbox3.setChecked(false);
                mCheckbox4.setChecked(true);
                mCheckbox5.setChecked(false);
                mCheckbox6.setChecked(false);
                if (mCheckbox4.isChecked() == true) {
                    String html4b = "<font color=\"#ffffff\" >" + "2000元" + "</font>";
                    html4b = html4b.replace("\r\n", "<br />");
                    mCheckbox4.setText(Html.fromHtml(html4b));

                    mCheckbox1.setText(Html.fromHtml(html));
                    mCheckbox2.setText(Html.fromHtml(html2));
                    mCheckbox3.setText(Html.fromHtml(html3));
                    mCheckbox5.setText(Html.fromHtml(html5));
                    mCheckbox6.setText(Html.fromHtml(html6));


                } else if (mCheckbox4.isChecked() == false) {
                    mCheckbox4.setText(Html.fromHtml(html4));
                }
                chargetypeid = 4;
                break;
            case R.id.checkbox5:
                mCheckbox1.setChecked(false);
                mCheckbox2.setChecked(false);
                mCheckbox3.setChecked(false);
                mCheckbox4.setChecked(false);
                mCheckbox5.setChecked(true);
                mCheckbox6.setChecked(false);
                if (mCheckbox5.isChecked() == true) {
                    String html5b = "<font color=\"#ffffff\" >" + "5000元" + "</font>";
                    html5b = html5b.replace("\r\n", "<br />");
                    mCheckbox5.setText(Html.fromHtml(html5b));
                    mCheckbox1.setText(Html.fromHtml(html));
                    mCheckbox2.setText(Html.fromHtml(html2));
                    mCheckbox3.setText(Html.fromHtml(html3));
                    mCheckbox4.setText(Html.fromHtml(html4));
                    mCheckbox6.setText(Html.fromHtml(html6));
                } else if (mCheckbox5.isChecked() == false) {
                    mCheckbox5.setText(Html.fromHtml(html5));
                }
                chargetypeid = 5;
                break;
            case R.id.checkbox6:
                mCheckbox1.setChecked(false);
                mCheckbox2.setChecked(false);
                mCheckbox3.setChecked(false);
                mCheckbox4.setChecked(false);
                mCheckbox5.setChecked(false);
                mCheckbox6.setChecked(true);
                if (mCheckbox6.isChecked() == true) {
                    String html6b = "<font color=\"#ffffff\" >" + "10000元" + "</font>";
                    html6b = html6b.replace("\r\n", "<br />");
                    mCheckbox6.setText(Html.fromHtml(html6b));
                    mCheckbox1.setText(Html.fromHtml(html));
                    mCheckbox2.setText(Html.fromHtml(html2));
                    mCheckbox3.setText(Html.fromHtml(html3));
                    mCheckbox5.setText(Html.fromHtml(html5));
                    mCheckbox4.setText(Html.fromHtml(html4));
                } else if (mCheckbox6.isChecked() == false) {
                    mCheckbox6.setText(Html.fromHtml(html6));
                }
                chargetypeid = 6;
                break;
            case R.id.wx:
                wx.setChecked(true);
                zfb.setChecked(false);
                if (wx.isChecked() == true) {
                    String html6b = "<font color=\"#ffffff\" >" + "微信" + "</font>";
                    html6b = html6b.replace("\r\n", "<br />");
                    wx.setText(Html.fromHtml(html6b));
                    zfb.setText(Html.fromHtml(htmlzfb));
                } else if (wx.isChecked() == false) {
                    wx.setText(Html.fromHtml(htmlwx));
                }
                break;
            case R.id.zfb:
                zfb.setChecked(true);
                wx.setChecked(false);
                if (zfb.isChecked() == true) {
                    String html6b = "<font color=\"#ffffff\" >" + "支付宝" + "</font>";
                    html6b = html6b.replace("\r\n", "<br />");
                    zfb.setText(Html.fromHtml(html6b));
                    wx.setText(Html.fromHtml(htmlwx));
                } else if (zfb.isChecked() == false) {
                    wx.setText(Html.fromHtml(htmlzfb));
                }
                break;
            case R.id.account_btn:
                if (wx.isChecked() == false && zfb.isChecked() == false) {
                    ToastUtil.showShort(this, "请选择支付方式");
                } else if (wx.isChecked() == true) {
                    map.clear();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("body", "Android");
                    //微信支付
                    if (chargetypeid == 1) {
                        map.put("amount", "100");
                        presenter.getpost(Contacts.WeChat_charges, headmap, map, WxPayBean.class);
                    } else if (chargetypeid == 2) {
                        map.put("amount", "500");
                        presenter.getpost(Contacts.WeChat_charges, headmap, map, WxPayBean.class);
                    } else if (chargetypeid == 3) {
                        map.put("amount", "1000");
                        presenter.getpost(Contacts.WeChat_charges, headmap, map, WxPayBean.class);
                    } else if (chargetypeid == 4) {
                        map.put("amount", "2000");
                        presenter.getpost(Contacts.WeChat_charges, headmap, map, WxPayBean.class);
                    } else if (chargetypeid == 5) {
                        map.put("amount", "5000");
                        presenter.getpost(Contacts.WeChat_charges, headmap, map, WxPayBean.class);
                    } else if (chargetypeid == 6) {
                        map.put("amount", "10000");
                        presenter.getpost(Contacts.WeChat_charges, headmap, map, WxPayBean.class);
                    }
                } else if (zfb.isChecked() == true) {
                    map.clear();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("body", "Android");
                    //支付宝支付

                    if (chargetypeid == 1) {
                        map.put("amount", "100");
                        presenter.getpost(Contacts.zfb_alipay, headmap, map, Zfb_Alipay_Bean.class);
                    } else if (chargetypeid == 2) {
                        map.put("amount", "500");
                        presenter.getpost(Contacts.zfb_alipay, headmap, map, Zfb_Alipay_Bean.class);
                    } else if (chargetypeid == 3) {
                        map.put("amount", "1000");
                        presenter.getpost(Contacts.zfb_alipay, headmap, map, Zfb_Alipay_Bean.class);
                    } else if (chargetypeid == 4) {
                        map.put("amount", "2000");
                        presenter.getpost(Contacts.zfb_alipay, headmap, map, Zfb_Alipay_Bean.class);
                    } else if (chargetypeid == 5) {
                        map.put("amount", "5000");
                        presenter.getpost(Contacts.zfb_alipay, headmap, map, Zfb_Alipay_Bean.class);
                    } else if (chargetypeid == 6) {
                        map.put("amount", "10000");
                        presenter.getpost(Contacts.zfb_alipay, headmap, map, Zfb_Alipay_Bean.class);
                    }
                }
                break;
            case R.id.lin_up_rules:
                IntentUtils.getIntents().Intent(this, Up_RulesActivity.class, null);
                break;
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof Zfb_Alipay_Bean) {
            Zfb_Alipay_Bean zfb_alipay_bean = (Zfb_Alipay_Bean) data;
            if (zfb_alipay_bean.getData() != null) {
                if (zfb_alipay_bean.getCode().equals("200")) {
                    final String data1 = zfb_alipay_bean.getData();
                    Runnable payRunnable = new Runnable() {
                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(RechargeActivity.this);
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
            }
        } else if (data instanceof Ngz_Bean) {
            Ngz_Bean ngz_bean = (Ngz_Bean) data;
            if (ngz_bean.getCode().equals("200")) {
                String balance = ngz_bean.getData().getBalance();
                yu.setText(balance);
            } else {
                yu.setVisibility(View.GONE);
            }
        } else if (data instanceof WxPayBean) {
            WxPayBean wxPayBean = (WxPayBean) data;
            if (wxPayBean.getData() != null) {
                WxPayBean.DataBean data1 = wxPayBean.getData();
                final IWXAPI msgApi = WXAPIFactory.createWXAPI(App.appContext, null);
                // 将该app注册到微信
                msgApi.registerApp(WXConFig.APP_ID);
                IWXAPI api = WXAPIFactory.createWXAPI(App.appContext, WXConFig.APP_ID, false);
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
