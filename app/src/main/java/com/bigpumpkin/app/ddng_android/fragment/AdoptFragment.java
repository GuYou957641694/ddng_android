package com.bigpumpkin.app.ddng_android.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.AdoptAdapter;
import com.bigpumpkin.app.ddng_android.bean.AdoptBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdoptFragment extends BaseLazyLoadFragment implements MyView {

    private List<String> list = new ArrayList<>();
    private RecyclerView rv;
    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;


    @Override
    public int getLayout() {
        return R.layout.adopt_layout;
    }

    @Override
    public void initViews(View view) {
        for (int i = 0; i < 30; i++) {
            list.add("测试" + i);
        }
        rv = view.findViewById(R.id.rv);

    }

    @Override
    public void loadData() {
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("Dimension", "1");
        map.put("page", "1");
        presenter.get(Contacts.Poultry_shop_lists, headmap, map, AdoptBean.class);
        AdoptAdapter adoptAdapter = new AdoptAdapter(getActivity(), list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(adoptAdapter);
    }

    @Override
    public void success(Object data) {
        if (data instanceof AdoptBean){
            AdoptBean adoptBean = (AdoptBean) data;

        }
    }

    @Override
    public void error(String error) {

    }
}
