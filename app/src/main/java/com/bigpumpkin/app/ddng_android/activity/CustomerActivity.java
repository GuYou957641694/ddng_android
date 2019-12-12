package com.bigpumpkin.app.ddng_android.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.utils.CopyButtonLibrary;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;

public class CustomerActivity extends BaseActivity {


    @Override
    public int intiLayout() {
        return R.layout.activity_customer;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        TextView tv_official_phone = findViewById(R.id.tv_official_phone);
        TextView tv_official_wx = findViewById(R.id.tv_official_wx);
        tv_official_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "13812342345";
                Intent Intent = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));//跳转到拨号界面，同时传递电话号码
                startActivity(Intent);
            }
        });
        tv_official_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传入需要复制的文字的控件
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(), tv_official_wx);
                copyButtonLibrary.init();
            }
        });
    }

    @Override
    public void initData() {

    }
}
