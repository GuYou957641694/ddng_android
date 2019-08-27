package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Log_Bean;
import com.bigpumpkin.app.ddng_android.bean.Registration_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.persenter.PresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.CountDownTextUtils;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.PwdCheckUtil;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity implements MyView, CountDownTextUtils.CountDownListener {


    @BindView(R.id.et_password_mobile)
    EditText etPasswordMobile;
    @BindView(R.id.et_change_password_code)
    EditText etChangePasswordCode;
    @BindView(R.id.btn_change_password_get_code)
    TextView btnChangePasswordGetCode;
    @BindView(R.id.new_pass)
    EditText newPass;
    @BindView(R.id.invitation_code)
    EditText invitationCode;
    @BindView(R.id.checked)
    CheckBox checked;
    @BindView(R.id.protocol)
    TextView protocol;
    @BindView(R.id.btn_login_confirm)
    Button btnLoginConfirm;
    @BindView(R.id.log_qq)
    ImageView logQq;
    @BindView(R.id.log_wx)
    ImageView logWx;
    @BindView(R.id.down)
    TextView down;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;

    private IWXAPI api;
    private String tel;
    private CountDownTextUtils mCountDownTextUtils;
    private String password;
    private String code;

    @Override
    public int intiLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);
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

    @OnClick({R.id.btn_change_password_get_code, R.id.protocol, R.id.btn_login_confirm, R.id.log_qq, R.id.log_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_password_get_code:
                //获取验证码
                tel = etPasswordMobile.getText().toString();
                if (isChinaPhoneLegal(tel)) {
                    SMSSDK.getVerificationCode("86", tel);
                    down.setTextColor(getResources().getColor(R.color.theme_orange));
                    down.setVisibility(View.VISIBLE);
                    btnChangePasswordGetCode.setVisibility(View.INVISIBLE);
                    mCountDownTextUtils = new CountDownTextUtils(60000, 1000, this);
                    mCountDownTextUtils.start();
                } else {
                    ToastUtil.showShort(this, "请重新输入手机号");
                }
                break;
            case R.id.protocol:
                //用户注册协议
                IntentUtils.getIntents().Intent(this, AgreementActivity.class, null);
                break;
            case R.id.btn_login_confirm:
                //注册
                tel = etPasswordMobile.getText().toString();
                password = newPass.getText().toString();
                code = etChangePasswordCode.getText().toString();
                if (checked.isChecked() == true) {
                    if (EmptyUtils.isNotEmpty(tel) && EmptyUtils.isNotEmpty(password) && EmptyUtils.isNotEmpty(code)) {
                        if (isChinaPhoneLegal(tel)) {
                            if (PwdCheckUtil.isLetterDigit(password)) {
                                SMSSDK.submitVerificationCode("86", tel, code);
                            } else {
                                ToastUtil.showShort(this, "密码必须包含数字或字母");
                            }
                        } else {
                            ToastUtil.showShort(this, "请重新输入手机号");
                        }
                    } else {
                        ToastUtil.showShort(this, "请输入完整");
                    }
                } else {
                    ToastUtil.showShort(this, "请选中用户注册协议");
                }


                break;
            case R.id.log_qq:
                //QQ
                break;
            case R.id.log_wx:
                //微信
                break;
        }
    }


    @Override
    public void success(Object data) {
        if (data instanceof Registration_Bean) {
            Registration_Bean registration_bean = (Registration_Bean) data;
            String code = registration_bean.getCode();
            if (EmptyUtils.isNotEmpty(code)) {
                if (code.equals("200")) {
                    ToastUtil.showShort(this, "注册成功");
                    finish();
                } else if (code.equals("001")) {
                    ToastUtil.showShort(this, "注册成功");
                } else if (code.equals("004")) {
                    ToastUtil.showShort(this, "手机号已注册");
                }
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
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
                            headmap = new HashMap<>();
                            map = new HashMap<>();
                            map.put("tel", tel);
                            map.put("password", password);
                            presenter.getpost(Contacts.Registration, headmap, map, Registration_Bean.class);
                        } else {
                            // TODO 处理错误的结果
                            ToastUtil.showShort(RegisterActivity.this, "请重新输入验证码");
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }
            }).sendMessage(msg);
        }
    };

    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 倒计时中
     *
     * @param
     */
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

}
