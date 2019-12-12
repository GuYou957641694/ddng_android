package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.GeneralMoreAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.MilkMoreBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.SpaceItemDecorations;

import java.util.HashMap;
import java.util.List;

public class GeneralMoreActivity extends BaseActivity implements MyView {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String name;
    private RecyclerView recyclerview;

    @Override
    public int intiLayout() {
        return R.layout.activity_general_more;
    }

    @Override
    public void initView() {
        ImageView ivBack = findViewById(R.id.iv_back);
        recyclerview = findViewById(R.id.recyclerview);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        presenter.get(Contacts.Poultry_milk_lists, headmap, map, MilkMoreBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof MilkMoreBean){
            MilkMoreBean milkMoreBean  = (MilkMoreBean) data;
            List<MilkMoreBean.DataBean> data1 = milkMoreBean.getData();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            recyclerview.setLayoutManager(gridLayoutManager);
            recyclerview.addItemDecoration(new SpaceItemDecorations(25));
            GeneralMoreAdapter generalMoreAdapter = new GeneralMoreAdapter(this, data1);
            recyclerview.setAdapter(generalMoreAdapter);
            generalMoreAdapter.setOnItemClickListener(new GeneralMoreAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    String id = data1.get(position).getId();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    //跳转详情页
                    IntentUtils.getIntents().Intent(GeneralMoreActivity.this, Spell_DetailsActivity.class, bundle);
                }
            });
        }
    }

    @Override
    public void error(String error) {

    }
}
