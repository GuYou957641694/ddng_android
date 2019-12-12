package com.bigpumpkin.app.ddng_android.orders;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.AdoptLogisticsActivity;
import com.bigpumpkin.app.ddng_android.activity.GeneralLogisticsActivity;
import com.bigpumpkin.app.ddng_android.activity.PendingReceiptDetailssActivity;
import com.bigpumpkin.app.ddng_android.adapter.Googs_OrderAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.For_GoodsBean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.hjq.toast.ToastUtils;

import java.util.HashMap;
import java.util.List;

//待收货
public class For_GoodsFragment extends BaseLazyLoadFragment implements MyView {

    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RelativeLayout relativeLayout;
    private Googs_OrderAdapter googs_orderAdapter;
    private ExpandableListView evForGoods;
    private long time;
    private String sha1;

    @Override
    public int getLayout() {
        return R.layout.forgoodsfragment;
    }

    @Override
    public void initViews(View view) {
        relativeLayout = view.findViewById(R.id.no_orders);
        evForGoods = view.findViewById(R.id.ev_for_goods);
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
        presenter.getpost(Contacts.My_cargo, headmap, map, For_GoodsBean.class);
    }


    @Override
    public void success(Object data) {
        if (data instanceof For_GoodsBean) {
            For_GoodsBean for_goodsBean = (For_GoodsBean) data;
            List<For_GoodsBean.DataBean> data1 = for_goodsBean.getData();
            if (data1.size() > 0 && data1 != null) {
                googs_orderAdapter = new Googs_OrderAdapter(data1, getActivity());
                evForGoods.setAdapter(googs_orderAdapter);

                //查看物流
                googs_orderAdapter.setListener(new Googs_OrderAdapter.onListener() {
                    @Override
                    public void OnListener(int order_status, String orderid) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", orderid);
                        //1普通商品物流页面 2认养物流页面
                        if (order_status == 1) {
                            IntentUtils.getIntents().Intent(getActivity(), GeneralLogisticsActivity.class, bundle);
                        } else {
                            IntentUtils.getIntents().Intent(getActivity(), AdoptLogisticsActivity.class, bundle);
                        }
                    }
                });

                //确认收货
                googs_orderAdapter.setcancelListener(new Googs_OrderAdapter.oncancelListener() {
                    @Override
                    public void OncancelListener(int order_status, String orderid) {
                        map.clear();
                        map.put("appid", appid);
                        map.put("appsecret", appsecret);
                        map.put("timestamp", time);
                        map.put("sign", sha1);
                        map.put("order_id", orderid);
                        presenter.getpost(Contacts.my_confirm, headmap, map, Zfb_Bean.class);
                    }
                });

                //售后
                googs_orderAdapter.setsalesListener(new Googs_OrderAdapter.onsalesListener() {
                    @Override
                    public void OnsalesListener(int order_status, String orderid) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", orderid);
                        IntentUtils.getIntents().Intent(getActivity(), PendingReceiptDetailssActivity.class, bundle);
                    }
                });

                //展開
                for (int i = 0; i < data1.size(); i++) {
                    evForGoods.expandGroup(i);
                }

                //不能点击收缩
                evForGoods.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        return true;
                    }
                });
                relativeLayout.setVisibility(View.GONE);
                evForGoods.setVisibility(View.VISIBLE);

            } else {
                //提示用户没有订单
                relativeLayout.setVisibility(View.VISIBLE);
                evForGoods.setVisibility(View.GONE);
            }
        } else if (data instanceof Zfb_Bean) {
            Zfb_Bean zfb_bean = (Zfb_Bean) data;
            String code = zfb_bean.getCode();
            if (code.equals("200")) {
                ToastUtils.show("收货成功");
                map.clear();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                presenter.getpost(Contacts.My_cargo, headmap, map, For_GoodsBean.class);
            }
        }
    }

    @Override
    public void error(String error) {

    }

}
