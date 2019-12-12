package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.DetailsActivity;
import com.bigpumpkin.app.ddng_android.adapter.Fragment_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Fragment_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;


public class Fragment_Two extends BaseLazyLoadFragment implements MyView {
    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private RecyclerView recyclerView;
    private List<Fragment_Bean.DataBean.ShopBean> shop;


    @Override
    public int getLayout() {
        return R.layout.fragment_two;
    }

    @Override
    public void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerviews);
    }

    @Override
    public void loadData() {
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("id", "1");
        presenter.get(Contacts.xingyun, headmap, map, Fragment_Bean.class);
    }


    @Override
    public void success(Object data) {
        if (data instanceof Fragment_Bean) {
            Fragment_Bean bazaar_bean = (Fragment_Bean) data;
            shop = bazaar_bean.getData().getShop();
            if (shop != null) {
                Fragment_Adapter fragment_adapter = new Fragment_Adapter(shop, getActivity());
                recyclerView.setAdapter(fragment_adapter);
                fragment_adapter.setListener(new Fragment_Adapter.onListener() {
                    @Override
                    public void OnListener(int id) {
                        //商品id
                        String id1 = shop.get(id).getId();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id1);
                        //跳转详情页
                        IntentUtils.getIntents().Intent(getActivity(), DetailsActivity.class, bundle);
                    }
                });
            }
        }
    }

    @Override
    public void error(String error) {

    }
}
