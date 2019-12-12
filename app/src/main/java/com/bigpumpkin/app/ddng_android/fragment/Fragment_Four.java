package com.bigpumpkin.app.ddng_android.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Fragment_ForuAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.FragmentFor_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

public class Fragment_Four extends BaseLazyLoadFragment implements MyView {
    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private RecyclerView recyclerView;

    @Override
    public int getLayout() {
        return R.layout.fragment_four;
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
        map.put("id", "3");
        presenter.get(Contacts.xingyun, headmap, map, FragmentFor_Bean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof FragmentFor_Bean) {
            FragmentFor_Bean fragmentFor_bean = (FragmentFor_Bean) data;
            List<FragmentFor_Bean.DataBean.ShopBean> shop = fragmentFor_bean.getData().getShop();
            if (shop != null) {
                Fragment_ForuAdapter fragmentForuAdapter = new Fragment_ForuAdapter(shop, getActivity());
                recyclerView.setAdapter(fragmentForuAdapter);
            }
        }
    }

    @Override
    public void error(String error) {

    }
}
