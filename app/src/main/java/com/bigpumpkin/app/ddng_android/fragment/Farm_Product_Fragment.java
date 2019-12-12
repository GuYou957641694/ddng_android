package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Farm_ProductAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Farm_index_productBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Farm_Product_Fragment extends BaseLazyLoadFragment implements MyView {

    private RecyclerView rv;
    private ImageView tv_switch;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private List<String> list = new ArrayList<>();
    int lastPostion;
    private Farm_ProductAdapter farm_productAdapter;
    private String id;
    private RadioGroup radio;
    private boolean type = true;

    public static Farm_Product_Fragment getInstance(String id) {
        Farm_Product_Fragment farm_product_fragment = new Farm_Product_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        farm_product_fragment.setArguments(bundle);
        return farm_product_fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.farm_product_fragment;
    }

    @Override
    public void initViews(View view) {
        Bundle arguments = this.getArguments();
        id = arguments.getString("id");
        rv = view.findViewById(R.id.rv);
        tv_switch = view.findViewById(R.id.tv_switch);
        radio = view.findViewById(R.id.radio);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
    }

    @Override
    public void loadData() {
        map.put("fid", id);
        map.put("page", "1");
        presenter.get(Contacts.Farm_index_products, headmap, map, Farm_index_productBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof Farm_index_productBean) {
            Farm_index_productBean farm_index_productBean = (Farm_index_productBean) data;
            farm_index_productBean.setViewType(Farm_ProductAdapter.FOOT);
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            farm_productAdapter = new Farm_ProductAdapter(getActivity(), farm_index_productBean);
            rv.setAdapter(farm_productAdapter);
            tv_switch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rv.getLayoutManager() instanceof GridLayoutManager) {
                        lastPostion = ((GridLayoutManager) rv.getLayoutManager()).findFirstVisibleItemPosition();
                        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                        tv_switch.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.mipmap.icon_classification));
                        farm_index_productBean.setViewType(Farm_ProductAdapter.FOOT);
                        rv.setAdapter(farm_productAdapter);
                        rv.scrollToPosition(lastPostion);
                    } else {
                        lastPostion = ((LinearLayoutManager) rv.getLayoutManager()).findFirstVisibleItemPosition();
                        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                        tv_switch.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.mipmap.icon_classifications));
                        farm_index_productBean.setViewType(Farm_ProductAdapter.BODY);
                        rv.setAdapter(farm_productAdapter);
                        rv.scrollToPosition(lastPostion);
                    }
                }
            });
        }
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.niu1:
                        map.clear();
                        map.put("fid", id);
                        map.put("page", "1");
                        presenter.get(Contacts.Farm_index_products, headmap, map, Farm_index_productBean.class);
                        break;
                    case R.id.niu2:
                        map.clear();
                        map.put("fid", id);
                        map.put("page", "1");
                        map.put("type", "Adoption");
                        presenter.get(Contacts.Farm_index_products, headmap, map, Farm_index_productBean.class);
                        break;
                    case R.id.niu3:
                        map.clear();
                        map.put("fid", id);
                        map.put("page", "1");
                        map.put("sorting", "sales");
                        presenter.get(Contacts.Farm_index_products, headmap, map, Farm_index_productBean.class);
                        break;
                    case R.id.niu4:
                        if (type==true){
                            map.clear();
                            map.put("fid", id);
                            map.put("page", "1");
                            map.put("sorting", "salesL");
                            presenter.get(Contacts.Farm_index_products, headmap, map, Farm_index_productBean.class);
                            type=false;
                        }else {
                            map.clear();
                            map.put("fid", id);
                            map.put("page", "1");
                            map.put("sorting", "salesD");
                            presenter.get(Contacts.Farm_index_products, headmap, map, Farm_index_productBean.class);
                            type=true;
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void error(String error) {

    }
}
