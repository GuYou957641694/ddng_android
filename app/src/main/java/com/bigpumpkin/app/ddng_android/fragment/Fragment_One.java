package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.Spell_DetailsActivity;
import com.bigpumpkin.app.ddng_android.adapter.Bazaar_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Bazaar_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.EndlessRecyclerOnScrollListener;
import com.bigpumpkin.app.ddng_android.weight.SpaceItemDecoration;

import java.util.HashMap;
import java.util.List;

public class Fragment_One extends BaseLazyLoadFragment implements MyView {

    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private RecyclerView  recyclerView;
    private Bazaar_Adapter bazaar_adapter;
    private List<Bazaar_Bean.DataBean.ShopBean> shop;
    @Override
    public int getLayout() {
        return R.layout.fragment_one;
    }

    @Override
    public void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
    }

    @Override
    public void loadData() {
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        presenter.get(Contacts.xingyun, headmap, map, Bazaar_Bean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof Bazaar_Bean) {
            Bazaar_Bean bazaar_bean = (Bazaar_Bean) data;
            shop = bazaar_bean.getData().getShop();
            if (shop != null) {
                bazaar_adapter = new Bazaar_Adapter(shop, getActivity());
                recyclerView.setAdapter(bazaar_adapter);
                recyclerView.addItemDecoration(new SpaceItemDecoration(10, 20));
                bazaar_adapter.setListener(new Bazaar_Adapter.onListener() {
                    @Override
                    public void OnListener(int id) {
                        //商品id
                        String id1 = shop.get(id).getId();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id1);
                        //跳转详情页
                        IntentUtils.getIntents().Intent(getActivity(), Spell_DetailsActivity.class, bundle);
                    }
                });
                recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
                    @Override
                    public void onLoadMore() {
                        bazaar_adapter.setLoadState(bazaar_adapter.LOADING_END);
                    }
                });
            }
        }

    }

    @Override
    public void error(String error) {

    }


}
