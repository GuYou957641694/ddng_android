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
import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Logs_Bean;
import com.bigpumpkin.app.ddng_android.bean.User_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.CountDownTextUtils;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.LoginUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Bind_phoneActivity extends BaseActivity implements MyView, CountDownTextUtils.CountDownListener {


    @BindView(R.id.et_password_mobile)
    EditText etPasswordMobile;
    @BindView(R.id.et_change_password_code)
    EditText etChangePasswordCode;
    @BindView(R.id.btn_login_confirm)
    Button btnLoginConfirm;
    @BindView(R.id.down)
    TextView down;
    @BindView(R.id.btn_change_password_get_code)
    TextView btnChangePasswordGetCode;
    private String id;
    private String pic;
    private String name;
    private String sex;
    private String type;
    private CountDownTextUtils mCountDownTextUtils;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private String mobile;
    private MyPresenterImpl presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initView() {
        new TitleXML(this, "绑定手机号", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        id = getIntent().getStringExtra("id");
        pic = getIntent().getStringExtra("pic");
        name = getIntent().getStringExtra("name");
        sex = getIntent().getStringExtra("sex");
        type = getIntent().getStringExtra("type");
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
    }

    @OnClick({R.id.btn_login_confirm, R.id.btn_change_password_get_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_password_get_code:
                mobile = etPasswordMobile.getText().toString();
                // 注册一个事件回调，用于处理SMSSDK接口请求的结果
                SMSSDK.registerEventHandler(eventHandler);
                if (isChinaPhoneLegal(mobile)) {
                    SMSSDK.getVerificationCode("86", mobile);
                    down.setTextColor(getResources().getColor(R.color.theme_orange));
                    down.setVisibility(View.VISIBLE);
                    btnChangePasswordGetCode.setVisibility(View.INVISIBLE);
                    mCountDownTextUtils = new CountDownTextUtils(60000, 1000, this);
                    mCountDownTextUtils.start();
                } else {
                    ToastUtil.showShort(this, "请重新输入手机号");
                }
                break;
            case R.id.btn_login_confirm:
                String code = etChangePasswordCode.getText().toString();
                if (EmptyUtils.isNotEmpty(mobile) && EmptyUtils.isNotEmpty(code)) {
                    SMSSDK.submitVerificationCode("86", mobile, code);
                } else {
                    ToastUtil.showShort(this, "请输入完整");
                }
                break;
        }

    }

    @Override
    public void initData() {

    }

    @Override
    public void success(Object data) {
        if (data instanceof Logs_Bean) {
            Logs_Bean log_bean = (Logs_Bean) data;
            if (log_bean.getCode().equals("200")) {
                String appid = log_bean.getData().getAppid();
                String appsecret = log_bean.getData().getAppsecret();
                SpzUtils.putString("appid", appid);
                SpzUtils.putString("appsecret", appsecret);
                //获得当前时间戳
                long time = System.currentTimeMillis();
                String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                String sha1 = EncryptUtils.getSHA(sha);
                //获得用户的信息
                map.clear();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                presenter.getpost(Contacts.User, headmap, map, User_Bean.class);
            }
        } else if (data instanceof User_Bean) {
            User_Bean user_bean = (User_Bean) data;
            if (user_bean != null) {
                LoginUtil.getInstance().saveData(App.appContext, user_bean.getData());
                IntentUtils.getIntents().Intent(this, AllActivity.class, null);
                ToastUtil.showShort(this, "登录成功");
                finish();
            } else {
                ToastUtil.showShort(this, "请重新登录");
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
                            map.clear();
                            map.put("type", type);
                            map.put("tel", mobile);
                            map.put("id", id);
                            map.put("pic", pic);
                            map.put("name", name);
                            if (sex!=null){
                                map.put("sex", sex);
                            }
                            presenter.getpost(Contacts.Bing_Phone, headmap, map, Logs_Bean.class);
                        } else {
                            // TODO 处理错误的结果
                            ToastUtil.showShort(Bind_phoneActivity.this, "请重新输入验证码");
                        }
                    }
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
            down.setText(R.string.login_verification_code);
            btnChangePasswordGetCode.setVisibility(View.VISIBLE);
            down.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            Log.e(TAG, "onCountDownFinish: ", e.getCause());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
