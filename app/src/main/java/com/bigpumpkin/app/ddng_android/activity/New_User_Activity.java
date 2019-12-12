package com.bigpumpkin.app.ddng_android.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;

public class New_User_Activity extends BaseActivity implements MyView {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_new__user_;
    }

    @Override
    public void initView() {
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
       EditText etMobile =  findViewById(R.id.et_mobile);
       EditText etCode =  findViewById(R.id.et_code);
       Button btnLoginConfirm =  findViewById(R.id.btn_login_confirm);
        btnLoginConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.getpost(Contacts.zfb_Log, zfbheadmap, zfbmap, Zfb_Bean.class);
            }
        });
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
