package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Change_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordActivity extends BaseActivity implements MyView {

    @BindView(R.id.new_pass)
    EditText newPass;
    @BindView(R.id.news_pass)
    EditText newsPass;
    @BindView(R.id.btn_login_confirm)
    Button btnLoginConfirm;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String appid;
    private String appsecret;
    private String sha1;
    private long time;
    private String tel;

    @Override
    public int intiLayout() {
        return R.layout.activity_password;
    }

    @Override
    public void initView() {
        new TitleXML(this, "修改密码", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        tel = getIntent().getStringExtra("tel");
        presenter = new MyPresenterImpl(this);
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login_confirm)
    public void onViewClicked() {
        String pas = newPass.getText().toString();
        String pass = newsPass.getText().toString();
        if (pas != null && pas.length() > 0 && pass != null && pass.length() > 0 && pas.equals(pass)) {
            map.put("tel", tel);
            map.put("pass", pas);
            presenter.getpost(Contacts.Forget, headmap, map, Change_Bean.class);
        }
    }


    @Override
    public void success(Object data) {
        if (data instanceof Change_Bean) {
            Change_Bean change_bean = (Change_Bean) data;
            String code = change_bean.getCode();
            if (code.equals("200")) {
                ToastUtil.showShort(this, "修改成功");
                finish();
            }
        }
    }

    @Override
    public void error(String error) {

    }
}
