package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Rarm_Rencommended_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Rarm_RencommendedBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

public class Farm_Recommended_Fragment extends BaseLazyLoadFragment implements MyView {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String id;
    private RecyclerView recyclerview;

    public static Farm_Recommended_Fragment getInstance(String id) {
        Farm_Recommended_Fragment farm_recommended_fragment = new Farm_Recommended_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        farm_recommended_fragment.setArguments(bundle);
        return farm_recommended_fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.farm_recommended_fragment;
    }

    @Override
    public void initViews(View view) {
        Bundle arguments = this.getArguments();
        id = arguments.getString("id");
        recyclerview = view.findViewById(R.id.recyclerview);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
    }

    @Override
    public void loadData() {
        map.put("fid", id);
        presenter.get(Contacts.Farm_index_signboards, headmap, map, Rarm_RencommendedBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof Rarm_RencommendedBean) {
            Rarm_RencommendedBean rarm_rencommendedBean = (Rarm_RencommendedBean) data;
            List<Rarm_RencommendedBean.DataBean.ShopBean> shop = rarm_rencommendedBean.getData().getShop();
            StaggeredGridLayoutManager staggeredGridLayoutManager =
                    new StaggeredGridLayoutManager(2,
                            StaggeredGridLayoutManager.VERTICAL);
            recyclerview.setLayoutManager(staggeredGridLayoutManager);
            Rarm_Rencommended_Adapter rarm_rencommended_adapter = new Rarm_Rencommended_Adapter(shop, getActivity());
            recyclerview.setAdapter(rarm_rencommended_adapter);
        }
    }

    @Override
    public void error(String error) {

    }
}
