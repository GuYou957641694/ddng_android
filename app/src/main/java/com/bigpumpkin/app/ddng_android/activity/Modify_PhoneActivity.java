package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Change_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.CountDownTextUtils;
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
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static com.bigpumpkin.app.ddng_android.activity.RegisterActivity.isChinaPhoneLegal;

public class Modify_PhoneActivity extends BaseActivity implements MyView ,CountDownTextUtils.CountDownListener {
    @BindView(R.id.et_change_password_mobile)
    EditText etChangePasswordMobile;
    @BindView(R.id.et_change_password_code)
    EditText etChangePasswordCode;
    @BindView(R.id.btn_change_password_get_code)
    TextView btnChangePasswordGetCode;
    @BindView(R.id.down)
    TextView down;
    @BindView(R.id.btn_login_confirm)
    Button btnLoginConfirm;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String appid;
    private String appsecret;
    private String sha1;
    private CountDownTextUtils mCountDownTextUtils;
    private String mobile;

    @Override
    public int intiLayout() {
        return R.layout.activity_modify__phone;
    }

    @Override
    public void initView() {
        new TitleXML(this, "修改手机号", true, "").init().setListener(new TitleXML.TitleXMLClick() {
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
    public void success(Object data) {
        if (data instanceof Change_Bean) {
            Change_Bean change_bean = (Change_Bean) data;
            if (change_bean.getCode().equals("200")) {
                ToastUtil.showShort(this, "修改成功");
                finish();
            }else {
                ToastUtil.showShort(this, "修改失败");
            }
        }
    }

    @Override
    public void error(String error) {

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
                // 注册一个事件回调，用于处理SMSSDK接口请求的结果
                SMSSDK.registerEventHandler(eventHandler);
                //验证码
                mobile = etChangePasswordMobile.getText().toString();
                if (isChinaPhoneLegal(mobile)) {
                    SMSSDK.getVerificationCode("86", mobile);
                    down.setTextColor(getResources().getColor(R.color.theme_orange));
                    down.setVisibility(View.VISIBLE);
                    btnChangePasswordGetCode.setVisibility(View.INVISIBLE);
                    mCountDownTextUtils = new CountDownTextUtils(60000, 1000, this);
                    mCountDownTextUtils.start();
                } else {
                    ToastUtil.showShort(Modify_PhoneActivity.this, "请重新输入手机号");
                }
                break;
            case R.id.btn_login_confirm:
                String s = etChangePasswordCode.getText().toString();
                mobile = etChangePasswordMobile.getText().toString();
                // 提交验证码，其中的code表示验证码，如“1357”
                if (EmptyUtils.isNotEmpty(s)) {
                    SMSSDK.submitVerificationCode("86", mobile, s);
                } else {
                    ToastUtil.showShort(Modify_PhoneActivity.this, "请输入手机号");
                }
                break;
        }
    }


    /**
     * 短信验证码
     */
    EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            long time = System.currentTimeMillis();
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
                            map.put("tel", mobile);
                            Log.d(TAG, "handleMessage: "+time);
                            presenter.getpost(Contacts.My_change, headmap, map, Change_Bean.class);
                        } else {
                            // TODO 处理错误的结果
                            ToastUtil.showShort(Modify_PhoneActivity.this, "请重新输入验证码");
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }
            }).sendMessage(msg);
        }
    };

    @Override
    public void onCountDown(long currentMill) {
        try {
            long tmpMills = currentMill / 1000;
            String strTmp = String.format("%ss", String.valueOf(tmpMills));
            down.setText(strTmp);
        } catch (Exception e) {
            Log.e(TAG, "onCountDown: ", e.getCause());
        }
    }

    @Override
    public void onCountDownFinish() {
        try {
            down.setTextColor(getResources().getColor(R.color.gray_c));
            btnChangePasswordGetCode.setText(R.string.login_verification_code);
            down.setVisibility(View.VISIBLE);
            down.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            Log.e(TAG, "onCountDownFinish: ", e.getCause());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
