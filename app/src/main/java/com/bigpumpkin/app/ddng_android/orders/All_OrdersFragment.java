package com.bigpumpkin.app.ddng_android.orders;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.Buy_OrdersActivity;
import com.bigpumpkin.app.ddng_android.adapter.All_OrderAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Address_Success_Bean;
import com.bigpumpkin.app.ddng_android.bean.All_OrdersBean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Alipay_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;

import java.util.HashMap;
import java.util.List;

public class All_OrdersFragment extends BaseLazyLoadFragment implements MyView {

    private static final String TAG = "All_OrdersFragment";
    RecyclerView all;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RelativeLayout relativeLayout;
    private String sha1;
    private long time;
    private List<All_OrdersBean.DataBean> data1;
    private All_OrderAdapter all_orderAdapter;
    private int j;
    private PopupWindow mPopupWindowtwo;
    private String id;
    private LoadingDialog dialog;
    private boolean isGetData = false;

    @Override
    public int getLayout() {
        return R.layout.all_orders;
    }

    @Override
    public void initViews(View view) {
        relativeLayout = view.findViewById(R.id.no_orders);
        all = view.findViewById(R.id.all);
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
        presenter.getpost(Contacts.My_ALLorder, headmap, map, All_OrdersBean.class);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "All_OrdersFragment: onCreate");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getpost(Contacts.My_ALLorder, headmap, map, All_OrdersBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof All_OrdersBean) {
            dialog.close();
            All_OrdersBean all_ordersBean = (All_OrdersBean) data;
            data1 = all_ordersBean.getData();
            if (data1.size() > 0 && data1 != null) {
                all_orderAdapter = new All_OrderAdapter(data1, getActivity());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                all.setLayoutManager(linearLayoutManager);
                all.setAdapter(all_orderAdapter);
                relativeLayout.setVisibility(View.GONE);
                all.setVisibility(View.VISIBLE);
                //删除订单
                all_orderAdapter.setListener(new All_OrderAdapter.onListener() {

                    @Override
                    public void OnListener(int i) {
                        j = i;
                        map.clear();
                        map.put("appid", appid);
                        map.put("appsecret", appsecret);
                        map.put("timestamp", time);
                        map.put("sign", sha1);
                        map.put("id", data1.get(i).getId());
                        presenter.getpost(Contacts.delete_orders, headmap, map, Address_Success_Bean.class);
                    }
                });
                //取消订单
                all_orderAdapter.setListeners(new All_OrderAdapter.onListeners() {
                    @Override
                    public void OnListeners(int i) {
                        id = data1.get(i).getId();
                        cancel();
                    }
                });
                //立即支付
                all_orderAdapter.setListeners(new All_OrderAdapter.onListeners() {
                    @Override
                    public void OnListeners(int i) {
                        id = data1.get(i).getId();
                        IntentUtils.getIntents().Intent(getActivity(), Buy_OrdersActivity.class, null);
                    }
                });
            } else {
                //提示用户没有订单
                relativeLayout.setVisibility(View.VISIBLE);
                all.setVisibility(View.GONE);
            }
        } else if (data instanceof Address_Success_Bean) {
            Address_Success_Bean address_success_bean = (Address_Success_Bean) data;
            String code = address_success_bean.getCode();
            if (code.equals("200")) {
                ToastUtil.showShort(getActivity(), "删除成功");
                map.clear();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                data1.clear();
                presenter.getpost(Contacts.My_ALLorder, headmap, map, All_OrdersBean.class);
            }
        } else if (data instanceof Zfb_Alipay_Bean) {
            Zfb_Alipay_Bean zfb_alipay_bean = (Zfb_Alipay_Bean) data;
            if (zfb_alipay_bean.getCode().equals("200")) {
                ToastUtil.showShort(getActivity(), "取消成功");
                map.clear();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                data1.clear();
                presenter.getpost(Contacts.My_ALLorder, headmap, map, All_OrdersBean.class);
            }
        }
    }

    @Override
    public void error(String error) {

    }

    private void cancel() {
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
                map.put("id", id);
                presenter.getpost(Contacts.my_cancel, headmap, map, Zfb_Alipay_Bean.class);
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
}
