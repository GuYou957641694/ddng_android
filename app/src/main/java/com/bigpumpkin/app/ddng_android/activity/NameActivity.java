package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.utils.SpzUtilUser;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NameActivity extends AppCompatActivity {

    @BindView(R.id.et_change_password_mobile)
    EditText etChangePasswordMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        ButterKnife.bind(this);
        String name = SpzUtilUser.getString("name");
        etChangePasswordMobile.setText(name);
        new TitleXML(NameActivity.this, "修改名称", true, "保存").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        }, new TitleXML.OnRightTagClickListener() {
            @Override
            public void onClick() {

            }
        });

    }
}
