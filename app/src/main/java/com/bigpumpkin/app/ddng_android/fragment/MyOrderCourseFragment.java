package com.bigpumpkin.app.ddng_android.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.MyOrderCourseAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.base.LazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Orders_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

public class MyOrderCourseFragment extends LazyLoadFragment implements MyView {

    RecyclerView rvMyCollections;
    SwipeRefreshLayout mSflMyCollections;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RelativeLayout relativeLayout;
    private MyOrderCourseAdapter order_adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collection_course_fragment;
    }

    @Override
    protected void initData() {
        relativeLayout = view.findViewById(R.id.collection_course);
        rvMyCollections = view.findViewById(R.id.rv_my_collections);
        mSflMyCollections = view.findViewById(R.id.sfl_my_collections);
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

        //刷新
        mSflMyCollections.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getpost(Contacts.My_order, headmap, map, Orders_Bean.class);
                mSflMyCollections.setRefreshing(false);
            }
        });
    }


    @Override
    protected void loadData() {
        presenter.getpost(Contacts.My_order, headmap, map, Orders_Bean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof Orders_Bean) {
            Orders_Bean order_bean = (Orders_Bean) data;
            if (order_bean.getData().getList().size() > 0 && order_bean.getData() != null) {
                if (order_bean.getCode().equals("200")) {
                    List<Orders_Bean.DataBean.ListBean> list = order_bean.getData().getList();
                    order_adapter = new MyOrderCourseAdapter(list, getActivity());
                    //添加布局管理器
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    rvMyCollections.setLayoutManager(linearLayoutManager);
                    rvMyCollections.setAdapter(order_adapter);
                    rvMyCollections.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.GONE);
                }
            } else {
                rvMyCollections.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        }


    }

    @Override
    public void error(String error) {

    }
}
