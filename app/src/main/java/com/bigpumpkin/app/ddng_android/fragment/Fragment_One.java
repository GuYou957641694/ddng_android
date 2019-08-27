package com.bigpumpkin.app.ddng_android.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Bazaar_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.bean.Bazaar_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

public class Fragment_One extends BaseFragment implements MyView {
    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    protected void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        presenter.get(Contacts.xingyun, headmap, map, Bazaar_Bean.class);
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void success(Object data) {
        if (data instanceof Bazaar_Bean) {
            Bazaar_Bean bazaar_bean = (Bazaar_Bean) data;
            List<Bazaar_Bean.DataBean.ShopBean> shop = bazaar_bean.getData().getShop();
            if (shop != null) {
                Bazaar_Adapter bazaar_adapter = new Bazaar_Adapter(shop, getActivity());
                recyclerView.setAdapter(bazaar_adapter);
            }
        }

    }

    @Override
    public void error(String error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
