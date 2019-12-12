package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.FarmTypeAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.FarmTypeBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

public class FarmTypeActivity extends BaseActivity implements MyView {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String id, name;
    private RecyclerView recyclerView;

    @Override
    public int intiLayout() {
        return R.layout.activity_farm_type;
    }

    @Override
    public void initView() {
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        new TitleXML(this, name, true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        recyclerView = findViewById(R.id.rv_farmtype);
    }

    @Override
    public void initData() {
        map.put("dimension", id);
        map.put("page", "1");
        ToastUtil.showShort(this, id);
        presenter.get(Contacts.Wisdomfarm_lists, headmap, map, FarmTypeBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof FarmTypeBean) {
            FarmTypeBean farmTypeBean = (FarmTypeBean) data;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            List<FarmTypeBean.DataBean> data1 = farmTypeBean.getData();
            FarmTypeAdapter farmTypeAdapter = new FarmTypeAdapter(this, data1);
            recyclerView.setAdapter(farmTypeAdapter);
            farmTypeAdapter.setListener(new FarmTypeAdapter.onListener() {
                @Override
                public void OnListener(int i) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", data1.get(i).getId());
                    IntentUtils.getIntents().Intent(FarmTypeActivity.this, FarmActivity.class, bundle);
                }
            });

        }
    }

    @Override
    public void error(String error) {

    }
}
