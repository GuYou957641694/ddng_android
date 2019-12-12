package com.bigpumpkin.app.ddng_android.orders;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.alipay.sdk.app.PayTask;
import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.Order_Details_Activity;
import com.bigpumpkin.app.ddng_android.adapter.EvPendingAdapter;
import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Address_Success_Bean;
import com.bigpumpkin.app.ddng_android.bean.PayResult;
import com.bigpumpkin.app.ddng_android.bean.PendingPay_Bean;
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
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
import com.hjq.toast.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//代付款
public class For_CollectionFragment extends BaseLazyLoadFragment implements MyView {

    private ExpandableListView recyclerview;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RelativeLayout relativeLayout;
    private String sha1;
    private long time;
    private String numbering;
    private PopupWindow mPopupWindowtwo;
    private PendingPay_Bean pendingPay_bean;
    private LoadingDialog dialog;
    private static final int SDK_PAY_FLAG = 1;
    private IWXAPI api;


    @Override
    public int getLayout() {
        return R.layout.for_collectionfragment;
    }

    @Override
    public void initViews(View view) {
        relativeLayout = view.findViewById(R.id.no_orders);
        recyclerview = view.findViewById(R.id.recyclerview);
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
    }

    @Override
    public void loadData() {
        dialog = new LoadingDialog(getActivity(), "玩命加载中...");
        dialog.show();
        presenter.getpost(Contacts.My_payment, headmap, map, PendingPay_Bean.class);
    }


    @Override
    public void success(Object data) {
        if (data instanceof PendingPay_Bean) {
            pendingPay_bean = (PendingPay_Bean) data;
            dialog.close();
            List<PendingPay_Bean.DataBean> data1 = pendingPay_bean.getData();
            if (data1.size() > 0 && data1 != null) {
                EvPendingAdapter evPendingAdapter = new EvPendingAdapter(getActivity(), data1);
                recyclerview.setAdapter(evPendingAdapter);
                relativeLayout.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
                //立即支付
                evPendingAdapter.setListener(new EvPendingAdapter.onListener() {
                    @Override
                    public void OnListener(int groupposition, int childposition) {
                        String fid = data1.get(groupposition).getList().get(childposition).getFid();
                        String num_price = data1.get(groupposition).getNum_price();
                        initSpecifcations(fid,num_price);
                    }
                });
                //取消支付
                evPendingAdapter.setListenercancel(new EvPendingAdapter.onListenercancel() {
                    @Override
                    public void OnListenercancel(int groupposition, int childposition) {
                        String fid = data1.get(groupposition).getList().get(childposition).getFid();
                        cancel(fid);
                    }
                });
                //详情
                evPendingAdapter.setListenerdetails(new EvPendingAdapter.onListenerdetails() {
                    @Override
                    public void OnListenerdetails(int groupposition, int childposition) {
                        String fid = data1.get(groupposition).getList().get(childposition).getFid();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", fid);
                        IntentUtils.getIntents().Intent(getActivity(), Order_Details_Activity.class, bundle);
                    }
                });

                //展開
                for (int i = 0; i < data1.size(); i++) {
                    recyclerview.expandGroup(i);
                }

                //不能点击收缩
                recyclerview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        return true;
                    }
                });
            } else {
                relativeLayout.setVisibility(View.VISIBLE);
            }
        } else if (data instanceof Address_Success_Bean) {
            Address_Success_Bean address_success_bean = (Address_Success_Bean) data;
            String code = address_success_bean.getCode();
            if (code.equals("200")) {
                map.clear();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                presenter.getpost(Contacts.My_payment, headmap, map, PendingPay_Bean.class);
                ToastUtils.show("成功");
            }
        }else if (data instanceof Zfb_Bean) {
            Zfb_Bean zfb_bean = (Zfb_Bean) data;
            String code = zfb_bean.getCode();
            if (code.equals("200")) {
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(getActivity());
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
            }else {
                ToastUtils.show(code);
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
            }
        }
    }

    @Override
    public void error(String error) {

    }


    private void cancel(String fid) {
        View view = getLayoutInflater().inflate(R.layout.mpopupwindowcancel, null);
        mPopupWindowtwo = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button confirm = view.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.clear();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                map.put("order_id", fid);
                map.put("Cancel_order", "测试");
                presenter.getpost(Contacts.my_cancel, headmap, map, Address_Success_Bean.class);
                mPopupWindowtwo.dismiss();
            }
        });
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().setAttributes(lp);
        mPopupWindowtwo.setFocusable(true);// 取得焦点
        mPopupWindowtwo.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindowtwo.setOutsideTouchable(true);
        mPopupWindowtwo.setTouchable(true);
        mPopupWindowtwo.setAnimationStyle(R.style.mypopwindow_anim_style);
        mPopupWindowtwo.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        mPopupWindowtwo.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }


    private void initSpecifcations(String fid,String num_price) {
        View view = getLayoutInflater().inflate(R.layout.pop_pay_layout, null);
        PopupWindow popspecifcations = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView iv_dismiss = view.findViewById(R.id.iv_dismiss);
        CheckBox cb_balance = view.findViewById(R.id.cb_balance);
        CheckBox cb_wx = view.findViewById(R.id.cb_wx);
        CheckBox cb_zfb = view.findViewById(R.id.cb_zfb);
        Button bt_submit_orders = view.findViewById(R.id.bt_submit_orders);
        bt_submit_orders.setText("确定付款￥" + num_price);
        iv_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popspecifcations.dismiss();
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
                    ToastUtil.showShort(getActivity(), "余额");
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
                    map.put("order_id", fid);
                    map.put("pay_type", "WeChat");
                    map.put("terminal_pay", "Android");
                    presenter.getpost(Contacts.OutstandingOrders, headmap, map, WxPayBean.class);
                    popspecifcations.dismiss();
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
                    map.put("order_id", fid);
                    map.put("pay_type", "Alipay");
                    map.put("terminal_pay", "Android");
                    presenter.getpost(Contacts.OutstandingOrders, headmap, map, Zfb_Bean.class);
                    popspecifcations.dismiss();
                } else {
                    ToastUtils.show("请选择支付方式");
                }
            }
        });

        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().setAttributes(lp);
        popspecifcations.setFocusable(true);
        popspecifcations.setBackgroundDrawable(new BitmapDrawable());
        popspecifcations.setOutsideTouchable(false);
        popspecifcations.setTouchable(true);
        popspecifcations.setAnimationStyle(R.style.mypopwindow_anim_style);
        popspecifcations.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
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
                        map.clear();
                        map.put("appid", appid);
                        map.put("appsecret", appsecret);
                        map.put("timestamp", time);
                        map.put("sign", sha1);
                        presenter.getpost(Contacts.My_payment, headmap, map, PendingPay_Bean.class);
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
    };
}
