package com.bigpumpkin.app.ddng_android.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Fragment_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.bean.Fragment_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

public class Fragment_Two extends BaseFragment implements MyView {

    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_two;
    }

    @Override
    protected void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerviews);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("id", "1");
        presenter.get(Contacts.xingyun, headmap, map, Fragment_Bean.class);
    }


    @Override
    protected void loadData() {

    }


    @Override
    public void success(Object data) {
        if (data instanceof Fragment_Bean) {
            Fragment_Bean bazaar_bean = (Fragment_Bean) data;
            List<Fragment_Bean.DataBean.ShopBean> shop = bazaar_bean.getData().getShop();
            if (shop != null) {
                Fragment_Adapter fragment_adapter = new Fragment_Adapter(shop, getActivity());
                recyclerView.setAdapter(fragment_adapter);
            }
        }
    }

    @Override
    public void error(String error) {

    }
}
