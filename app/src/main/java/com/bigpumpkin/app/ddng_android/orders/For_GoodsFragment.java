package com.bigpumpkin.app.ddng_android.orders;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.All_OrderAdapter;
import com.bigpumpkin.app.ddng_android.adapter.Googs_OrderAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.bean.All_OrdersBean;
import com.bigpumpkin.app.ddng_android.bean.For_GoodsBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class For_GoodsFragment extends BaseFragment implements MyView {

    @BindView(R.id.all)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RelativeLayout relativeLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.all_orders;
    }

    @Override
    protected void init(View view) {
        relativeLayout = view.findViewById(R.id.no_orders);
        presenter = new MyPresenterImpl(this);
        long time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        String sha1 = EncryptUtils.getSHA(sha);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        presenter.getpost(Contacts.My_cargo, headmap, map, For_GoodsBean.class);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void success(Object data) {
        if (data instanceof For_GoodsBean) {
            For_GoodsBean for_goodsBean = (For_GoodsBean) data;
            if (for_goodsBean.getCode().equals("200")) {
                For_GoodsBean.DataBean data1 = for_goodsBean.getData();
                if (data1.getList().size() > 0) {
                    List<For_GoodsBean.DataBean.ListBean> list = for_goodsBean.getData().getList();
                    Googs_OrderAdapter googs_orderAdapter = new Googs_OrderAdapter(list, getActivity());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerview.setLayoutManager(linearLayoutManager);
                    recyclerview.setAdapter(googs_orderAdapter);
                    relativeLayout.setVisibility(View.GONE);
                    recyclerview.setVisibility(View.VISIBLE);
                } else {
                    //提示用户没有订单
                    relativeLayout.setVisibility(View.VISIBLE);
                    recyclerview.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
