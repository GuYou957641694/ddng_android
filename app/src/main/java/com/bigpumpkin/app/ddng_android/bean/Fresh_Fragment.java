package com.bigpumpkin.app.ddng_android.bean;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.FreshAdapter;
import com.bigpumpkin.app.ddng_android.base.LazyLoadFragment;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

public class Fresh_Fragment extends LazyLoadFragment implements MyView {

    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private RecyclerView recyclerView;

   /* @Override
    public int getLayout() {
        return R.layout.fresh_fragment;
    }

    @Override
    public void initViews(View view) {
        recyclerView = view.findViewById(R.id.rv_recommended);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("page", "1");
    }

    @Override
    public void loadData() {
        presenter.get(Contacts.Poultry_shops, headmap, map, FreshBean.class);
    }*/

    @Override
    protected void loadData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fresh_fragment;
    }

    @Override
    protected void initData() {
        recyclerView = view.findViewById(R.id.rv_recommended);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("page", "1");
        presenter.get(Contacts.Poultry_shops, headmap, map, FreshBean.class);

    }

    @Override
    public void success(Object data) {
        if (data instanceof FreshBean) {
            FreshBean freshBean = (FreshBean) data;
            List<FreshBean.DataBean.ShopBean> shop = freshBean.getData().getShop();
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            FreshAdapter freshAdapter = new FreshAdapter(getActivity(), shop);
            recyclerView.setAdapter(freshAdapter);
        }
    }

    @Override
    public void error(String error) {

    }
}
