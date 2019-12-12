package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.FrmaPeople_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.FrmaPeopleBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomGardenActivity extends BaseActivity implements MyView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_custom_garden;
    }

    @Override
    public void initView() {
        new TitleXML(this, "看人选园", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });

    }

    @Override
    public void initData() {
        //首页
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("type", "1");
        map.put("page", "1");
        presenter.get(Contacts.frmapop, headmap, map, FrmaPeopleBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof FrmaPeopleBean) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerview.setLayoutManager(linearLayoutManager);
            FrmaPeopleBean frmaPeopleBean = (FrmaPeopleBean) data;
            List<FrmaPeopleBean.DataBean> data1 = frmaPeopleBean.getData();
            FrmaPeople_Adapter frmaPeople_adapter = new FrmaPeople_Adapter(data1, this);
            recyclerview.setAdapter(frmaPeople_adapter);

        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
