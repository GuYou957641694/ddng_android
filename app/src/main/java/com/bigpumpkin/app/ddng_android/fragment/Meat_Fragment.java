package com.bigpumpkin.app.ddng_android.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.MeatAdapter;
import com.bigpumpkin.app.ddng_android.base.LazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.MeatBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.List;

public class Meat_Fragment extends LazyLoadFragment implements MyView {

    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private RecyclerView recyclerView;

  /*  @Override
    public int getLayout() {
        return R.layout.meat_fragment;
    }

    @Override
    public void initViews(View view) {
        recyclerView = view.findViewById(R.id.rv_recommended);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("page", "1");
        map.put("type", "3");
    }

    @Override
    public void loadData() {
        presenter.get(Contacts.Poultry_shops, headmap, map, MeatBean.class);
    }
*/

    @Override
    public void success(Object data) {
        if (data instanceof MeatBean) {
            MeatBean meatBean = (MeatBean) data;
            if (meatBean != null) {
                List<MeatBean.DataBean.ShopBean> shop = meatBean.getData().getShop();
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                MeatAdapter meatAdapter = new MeatAdapter(getActivity(), shop);
                recyclerView.setAdapter(meatAdapter);
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void loadData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.meat_fragment;
    }

    @Override
    protected void initData() {
        recyclerView = view.findViewById(R.id.rv_recommended);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("page", "1");
        presenter.get(Contacts.Poultry_shops, headmap, map, MeatBean.class);

    }
}
