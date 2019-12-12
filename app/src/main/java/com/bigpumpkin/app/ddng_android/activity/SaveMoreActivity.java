package com.bigpumpkin.app.ddng_android.activity;

import android.support.v7.widget.RecyclerView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Save_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Save_More_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

public class SaveMoreActivity extends BaseActivity implements MyView {

    private RecyclerView recyclerview;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_save_more;
    }

    @Override
    public void initView() {
        new TitleXML(this, "救助农民", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        recyclerview = findViewById(R.id.save);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        presenter.get(Contacts.save_more, headmap, map, Save_More_Bean.class);

    }

    @Override
    public void initData() {

    }

    @Override
    public void success(Object data) {
        if (data instanceof Save_More_Bean) {
            Save_More_Bean save_more_bean = (Save_More_Bean) data;
            List<Save_More_Bean.DataBean.ListBean> list = save_more_bean.getData().getList();
            Save_Adapter save_adapter = new Save_Adapter(list, SaveMoreActivity.this);
            recyclerview.setAdapter(save_adapter);
        }
    }

    @Override
    public void error(String error) {

    }
}
