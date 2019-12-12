package com.bigpumpkin.app.ddng_android.activity;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.GoodsBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;

public class SaveDietActivity extends BaseActivity implements MyView {

    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;

    @Override
    public int intiLayout() {
        return R.layout.activity_save_diet;
    }

    @Override
    public void initView() {
        new TitleXML(this, "植物食疗", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        headmap = new HashMap<>();
        map = new HashMap<>();
        presenter.get(Contacts.save_diet, headmap, map, GoodsBean.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void success(Object data) {

    }

    @Override
    public void error(String error) {

    }
}
