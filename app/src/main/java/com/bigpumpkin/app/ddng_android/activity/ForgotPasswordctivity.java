package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;

public class ForgotPasswordctivity extends BaseActivity {


    @Override
    public int intiLayout() {
        return R.layout.activity_forgot_passwordctivity;
    }

    @Override
    public void initView() {
        Button bttNext = findViewById(R.id.btn_next);
        EditText etmobile = findViewById(R.id.et_change_password_mobile);
        bttNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = etmobile.getText().toString();
                if (mobile!=null){
                    Bundle bundle = new Bundle();
                    bundle.putString("mobile",mobile);
                    IntentUtils.getIntents().Intent(ForgotPasswordctivity.this,ForgotActivity.class,bundle);
                }
            }
        });
    }

    @Override
    public void initData() {

    }
}
