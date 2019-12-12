package com.bigpumpkin.app.ddng_android.activity;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.CusomerAapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.CusomerBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.hjq.toast.ToastUtils;

import java.util.HashMap;
import java.util.List;

//一小时申请售后
public class CusomerActivity extends BaseActivity implements MyView, View.OnClickListener {

    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String sha1;
    private long time;
    private ImageView ivRefundBack;
    private TextView tvRefundPrice;
    private TextView tvRefundReason;
    private EditText etRefundInstructions;
    private TextView tvRefundSubmit;
    private TextView tv_refund_rules;
    private RecyclerView recyclerView;
    private String order_id;
    private String orderlist_id;


    @Override
    public int intiLayout() {
        return R.layout.activity_cusomer;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        order_id = getIntent().getStringExtra("order_id");
        orderlist_id = getIntent().getStringExtra("orderlist_id");
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
        map.put("order_id", order_id);
        map.put("orderlist_id", orderlist_id);
        map.put("order_type", "order");
        recyclerView = findViewById(R.id.recyclerview);
        ivRefundBack = findViewById(R.id.iv_refund_back);
        tvRefundPrice = findViewById(R.id.tv_refund_price);
        tvRefundReason = findViewById(R.id.tv_refund_reason);
        etRefundInstructions = findViewById(R.id.et_refund_instructions);
        tvRefundSubmit = findViewById(R.id.tv_refund_submit);
        tv_refund_rules = findViewById(R.id.tv_refund_rules);
        ivRefundBack.setOnClickListener(this);
        tv_refund_rules.setOnClickListener(this);
        tvRefundReason.setOnClickListener(this);
        tvRefundSubmit.setOnClickListener(this);
    }

    @Override
    public void initData() {
        presenter.getpost(Contacts.Faster_refunds, headmap, map, CusomerBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof CusomerBean) {
            CusomerBean cusomerBean = (CusomerBean) data;
            List<CusomerBean.DataBean> data1 = cusomerBean.getData();
            CusomerAapter cusomerAapter = new CusomerAapter(this, data1);
            recyclerView.setAdapter(cusomerAapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            tvRefundPrice.setText("¥" + data1.get(0).getPay_price());
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_refund_back:
                finish();
                break;
            case R.id.tv_refund_rules:

                break;
            case R.id.tv_refund_reason:
                //退款原因
                initWhy();
                break;
            case R.id.tv_refund_submit:
                map.clear();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                map.put("order_id", order_id);
                map.put("orderlist_id", orderlist_id);
                map.put("order_type", "order");
                map.put("desc", "原因");
                map.put("device_type", "Android");
                presenter.getpost(Contacts.Faster_executions, headmap, map, CusomerBean.class);
                break;
        }
    }


    private void initWhy() {
        //退款原因
        View view = getLayoutInflater().inflate(R.layout.hour_return_reason_item, null);
        PopupWindow mPopupWindowthere = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv_sure = view.findViewById(R.id.tv_sure);
        RelativeLayout rv_one = view.findViewById(R.id.rv_one);
        RelativeLayout rv_two = view.findViewById(R.id.rv_two);
        RelativeLayout rv_three = view.findViewById(R.id.rv_three);
        RelativeLayout rv_four = view.findViewById(R.id.rv_four);
        CheckBox cb_one = view.findViewById(R.id.cb_one);
        CheckBox cb_two = view.findViewById(R.id.cb_two);
        CheckBox cb_three = view.findViewById(R.id.cb_three);
        CheckBox cb_four = view.findViewById(R.id.cb_four);

        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_one.isChecked() || cb_two.isChecked() || cb_three.isChecked() || cb_four.isChecked()) {
                    if (cb_one.isChecked()) {
                        ToastUtils.show("1");
                    } else if (cb_two.isChecked()) {
                        ToastUtils.show("2");
                    } else if (cb_three.isChecked()) {
                        ToastUtils.show("3");
                    } else if (cb_four.isChecked()) {
                        ToastUtils.show("4");
                    }
                } else {
                    ToastUtils.show("请选择");
                }
            }
        });

        rv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_one.setChecked(true);
                cb_two.setChecked(false);
                cb_three.setChecked(false);
                cb_four.setChecked(false);
            }
        });
        rv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_one.setChecked(false);
                cb_two.setChecked(true);
                cb_three.setChecked(false);
                cb_four.setChecked(false);
            }
        });

        rv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_one.setChecked(false);
                cb_two.setChecked(false);
                cb_three.setChecked(true);
                cb_four.setChecked(false);
            }
        });

        rv_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_one.setChecked(false);
                cb_two.setChecked(false);
                cb_three.setChecked(false);
                cb_four.setChecked(true);
            }
        });
        //产生背景变暗效果
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindowthere.setFocusable(true);// 取得焦点
        mPopupWindowthere.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindowthere.setOutsideTouchable(true);
        mPopupWindowthere.setTouchable(true);
        mPopupWindowthere.setAnimationStyle(R.style.mypopwindow_anim_style);

        mPopupWindowthere.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindowthere.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }
}
