package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Order_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.bean.Order_Bean;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyOrderActivityFragment extends BaseFragment implements MyView {


    @BindView(R.id.rv_my_collections)
    RecyclerView rvMyCollections;
    @BindView(R.id.sfl_my_collections)
    SwipeRefreshLayout sflMyCollections;
    Unbinder unbinder;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RelativeLayout collection_activity;
    private Order_Adapter order_adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collection_course_fragment;
    }

    @Override
    protected void init(View view) {
        collection_activity = view.findViewById(R.id.collection_activity);
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
        //presenter.getpost(Contacts.My_participate, headmap, map, Order_Bean.class);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void success(Object data) {
        if (data instanceof Order_Bean) {
            Order_Bean order_bean = (Order_Bean) data;
            if (order_bean.getData().getList().size() > 0 && order_bean.getData() != null) {
                if (order_bean.getCode().equals("200")) {
                    List<Order_Bean.DataBean.ListBean> list = order_bean.getData().getList();
                    order_adapter = new Order_Adapter(list, getActivity());
                    //添加布局管理器
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    rvMyCollections.setLayoutManager(linearLayoutManager);
                    rvMyCollections.setAdapter(order_adapter);
                }
            } else {
                rvMyCollections.setVisibility(View.GONE);
                collection_activity.setVisibility(View.VISIBLE);
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
