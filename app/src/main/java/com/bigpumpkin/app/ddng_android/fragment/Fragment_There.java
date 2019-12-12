package com.bigpumpkin.app.ddng_android.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.FragmentThere_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;

public class Fragment_There extends BaseLazyLoadFragment implements MyView {

    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private RecyclerView recyclerView;

    @Override
    public int getLayout() {
        return R.layout.fragment_there;
    }

    @Override
    public void initViews(View view) {
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("id", "2");
        presenter.get(Contacts.xingyun, headmap, map, FragmentThere_Bean.class);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void success(Object data) {
        if (data instanceof FragmentThere_Bean) {
            FragmentThere_Bean fragmentThere_bean = (FragmentThere_Bean) data;

        }
    }

    @Override
    public void error(String error) {

    }
}
