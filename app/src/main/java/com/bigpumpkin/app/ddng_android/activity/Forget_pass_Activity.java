package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.net.Contract;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Forget_pass_Activity extends BaseActivity implements Contract.View {


    @BindView(R.id.et_change_password_mobile)
    EditText etChangePasswordMobile;
    @BindView(R.id.et_change_password_code)
    EditText etChangePasswordCode;
    @BindView(R.id.btn_change_password_get_code)
    TextView btnChangePasswordGetCode;
    @BindView(R.id.new_pass)
    EditText newPass;
    @BindView(R.id.news_pass)
    EditText newsPass;
    @BindView(R.id.btn_login_confirm)
    Button btnLoginConfirm;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> map;

    @Override
    public int intiLayout() {
        return R.layout.activity_forget_pass_;
    }

    @Override
    public void initView() {

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

    @OnClick({R.id.btn_change_password_get_code, R.id.btn_login_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_password_get_code:

                break;
            case R.id.btn_login_confirm:
                headmap = new HashMap<>();
                map = new HashMap<>();
                String mobile = etChangePasswordMobile.getText().toString();
                String code = etChangePasswordCode.getText().toString();
                String pass = newPass.getText().toString();
                String passs = newsPass.getText().toString();
                if (EmptyUtils.isNotEmpty(mobile) && EmptyUtils.isNotEmpty(pass) && EmptyUtils.isNotEmpty(code) && EmptyUtils.isNotEmpty(passs)) {
                    map.put("", mobile);
                    map.put("", code);
                    map.put("", pass);
                    map.put("", passs);

                } else {

                }
                break;
        }
    }

    @Override
    public void success(Object success) {

    }

    @Override
    public void error(String error) {

    }
}
