package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.utils.CleanMessageUtil;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtilUser;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {

    @BindView(R.id.shipping_address)
    RelativeLayout shippingAddress;
    @BindView(R.id.security)
    RelativeLayout security;
    @BindView(R.id.remove)
    RelativeLayout remove;
    @BindView(R.id.version)
    RelativeLayout version;
    @BindView(R.id.exit)
    RelativeLayout exit;

    @Override
    public int intiLayout() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {
        new TitleXML(this, "设置", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.shipping_address, R.id.security, R.id.remove, R.id.version, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shipping_address:
                IntentUtils.getIntents().Intent(this, Management_addressActivity.class, null);
                break;
            case R.id.security:
                IntentUtils.getIntents().Intent(this, SecurityActivity.class, null);
                break;
            case R.id.remove:
                try {
                    String totalCacheSize = CleanMessageUtil.getTotalCacheSize(this);
                    Log.d(TAG, "onViewClicked: " + totalCacheSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.version:
                break;
            case R.id.exit:
                SpzUtilUser.clear();
                SpzUtils.clear();
                IntentUtils.getIntents().Intent(this, AllActivity.class, null);
                break;
        }
    }
}
