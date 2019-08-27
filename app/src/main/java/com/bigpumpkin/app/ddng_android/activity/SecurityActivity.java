package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecurityActivity extends BaseActivity {

    @BindView(R.id.phone)
    RelativeLayout phone;
    @BindView(R.id.pass)
    RelativeLayout pass;

    @Override
    public int intiLayout() {
        return R.layout.activity_security;
    }

    @Override
    public void initView() {
        new TitleXML(this, "账号安全", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.phone, R.id.pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone:
                IntentUtils.getIntents().Intent(this, Modify_PhoneActivity.class, null);
                break;
            case R.id.pass:
                IntentUtils.getIntents().Intent(this, Modify_PassActivity.class, null);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
