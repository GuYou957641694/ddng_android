package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Wisdoms_Areas_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Wisdoms_AreasBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

public class WisdomsFragmnet extends BaseLazyLoadFragment implements MyView {

    private static final String TAG = "AllFragment";
    private View view;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RecyclerView recyclerView;

    @Override
    public int getLayout() {
        return R.layout.winsdoms_fragment;
    }

    @Override
    public void initViews(View view) {
        recyclerView = view.findViewById(R.id.rv);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
    }


    @Override
    public void loadData() {
        Bundle bundle = getArguments();
        String url = bundle.getString("url").toString();
        map.put("area", "1");
        map.put("page", "1");
        presenter.get(Contacts.Wisdomfarm_Areas, headmap, map, Wisdoms_AreasBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof Wisdoms_AreasBean) {
            Wisdoms_AreasBean wisdoms_areasBean = (Wisdoms_AreasBean) data;
            List<Wisdoms_AreasBean.DataBean> data1 = wisdoms_areasBean.getData();
            Wisdoms_Areas_Adapter wisdoms_areas_adapter = new Wisdoms_Areas_Adapter(getActivity(),data1);
            recyclerView.setAdapter(wisdoms_areas_adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
    }

    @Override
    public void error(String error) {

    }
}
