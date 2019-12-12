package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.Spell_DetailsActivity;
import com.bigpumpkin.app.ddng_android.adapter.RecommendedAdapter;
import com.bigpumpkin.app.ddng_android.base.LazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.RecommendedBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

public class Recommended_Fragment extends LazyLoadFragment implements MyView {

    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private RecyclerView recyclerView;

 /*   @Override
    public int getLayout() {
        return R.layout.recommended_fragment;
    }

    @Override
    public void initViews(View view) {
        recyclerView = view.findViewById(R.id.rv_recommended);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("page", "1");
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(true);
    }*/
/*
    @Override
    public void loadData() {
        presenter.get(Contacts.Poultry_shops, headmap, map, RecommendedBean.class);
    }*/

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.recommended_fragment;
    }

    @Override
    protected void initData() {
        recyclerView = view.findViewById(R.id.rv_recommended);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("page", "1");
        presenter.get(Contacts.Poultry_shops, headmap, map, RecommendedBean.class);

    }
    @Override
    public void success(Object data) {
        if (data instanceof RecommendedBean) {
            RecommendedBean recommendedBean = (RecommendedBean) data;
            if (recommendedBean != null) {
                List<RecommendedBean.DataBean.ShopBean> shop = recommendedBean.getData().getShop();
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                RecommendedAdapter recommendedAdapter = new RecommendedAdapter(getActivity(), shop);
                recyclerView.setAdapter(recommendedAdapter);
                recommendedAdapter.setOnItemClickListener(new RecommendedAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        String id = shop.get(position).getId();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id);
                        //跳转详情页
                        IntentUtils.getIntents().Intent(getActivity(), Spell_DetailsActivity.class, bundle);
                    }
                });
            }
        }
    }

    @Override
    public void error(String error) {

    }



}
