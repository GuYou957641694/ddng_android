package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Message_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.net.Contract;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MessageActivity extends BaseActivity implements MyView {


    @BindView(R.id.leave)
    EditText leave;
    @BindView(R.id.btn_login_confirm)
    Button btnLoginConfirm;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_message;
    }

    @Override
    public void initView() {
        new TitleXML(this, "留言", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        presenter = new MyPresenterImpl(this);
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

    @OnClick(R.id.btn_login_confirm)
    public void onViewClicked() {
        String str = leave.getText().toString();
        long time = System.currentTimeMillis();
        String appid = SpzUtils.getString("appid");
        String appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        String sha1 = EncryptUtils.getSHA(sha);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        map.put("des", str);
        if (EmptyUtils.isNotEmpty(str)) {
            presenter.getpost(Contacts.My_message, headmap, map, Message_Bean.class);
        } else {
            ToastUtil.showShort(this, "请输入留言");
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof Message_Bean) {
            Message_Bean message_bean = (Message_Bean) data;
            if (message_bean.getCode().equals("200")) {
                ToastUtil.showShort(this, "留言成功");
                finish();
            } else {
                ToastUtil.showShort(this, "留言失败");
            }
        }
    }

    @Override
    public void error(String error) {

    }
}
