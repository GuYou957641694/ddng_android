package com.bigpumpkin.app.ddng_android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.AuthTask;
import com.bigpumpkin.app.ddng_android.activity.AllActivity;
import com.bigpumpkin.app.ddng_android.activity.Bind_phoneActivity;
import com.bigpumpkin.app.ddng_android.activity.ForgotPasswordctivity;
import com.bigpumpkin.app.ddng_android.activity.New_User_Activity;
import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Log_Bean;
import com.bigpumpkin.app.ddng_android.bean.User_Bean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
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
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class MainActivity extends BaseActivity implements MyView, View.OnClickListener, CountDownTextUtils.CountDownListener {

    private int SDK_AUTH_FLAG = 1;
    ImageView logQq;
    ImageView logWx;
    private HashMap<String, Object> maps;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> user;
    private HashMap<String, Object> users;
    private MyPresenterImpl presenter;
    private String auth;
    private String appid;
    private String appsecret;
    private String sex;
    private String headimgurl;
    private String openid;
    private String nickname;
    private TextView log_new_user, log_sms_user, tv_forgot_password, btn_change_password_get_code, down, tv_user_registration;
    private EditText et_password_code, et_code, et_change_password_mobile;
    private RelativeLayout rl_id;
    private Button btn_login_confirm;
    private int type = 2;
    private MyPresenterImpl presenters;
    private CountDownTextUtils mCountDownTextUtils;


    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
       /* listFragment.add(new Faster_Fragment());
        listFragment.add(new Log_Fragment());
        unicornCourseMainPagerAdapter = new UnicornCourseMainPagerAdapter(getSupportFragmentManager(), listFragment);
        unicornLv.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlUnicornCourseMain));
        tlUnicornCourseMain.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(unicornLv));
        unicornLv.setAdapter(unicornCourseMainPagerAdapter);*/
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        maps = new HashMap<>();
        //获得用户的信息
        user = new HashMap<>();
        users = new HashMap<>();
        presenters = new MyPresenterImpl(this);
        logQq = findViewById(R.id.log_qq);
        logWx = findViewById(R.id.log_wx);
        log_new_user = findViewById(R.id.log_new_user);
        log_sms_user = findViewById(R.id.log_sms_user);
        et_password_code = findViewById(R.id.et_password_code);
        et_code = findViewById(R.id.et_code);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        rl_id = findViewById(R.id.rl_id);
        tv_user_registration = findViewById(R.id.tv_user_registration);
        //验证码
        btn_change_password_get_code = findViewById(R.id.btn_change_password_get_code);
        down = findViewById(R.id.down);
        btn_login_confirm = findViewById(R.id.btn_login_confirm);
        et_change_password_mobile = findViewById(R.id.et_change_password_mobile);
        rl_id.setOnClickListener(this);
        btn_login_confirm.setOnClickListener(this);
        btn_change_password_get_code.setOnClickListener(this);
        logQq.setOnClickListener(this);
        logWx.setOnClickListener(this);
        tv_user_registration.setOnClickListener(this);
        log_new_user.setOnClickListener(this);
        log_sms_user.setOnClickListener(this);
        tv_forgot_password.setOnClickListener(this);
        et_password_code.setTransformationMethod(PasswordTransformationMethod.getInstance()); //设置为密码输入框

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.log_qq:
                //支付宝登录
                MyPresenterImpl presenters = new MyPresenterImpl(this);
                HashMap<String, Object> zfbheadmap = new HashMap<>();
                HashMap<String, Object> zfbmap = new HashMap<>();
                presenters.getpost(Contacts.zfb_Log, zfbheadmap, zfbmap, Zfb_Bean.class);
                break;
            case R.id.log_wx:
                //微信登录
                UMShareAPI.get(MainActivity.this).getPlatformInfo((Activity) MainActivity.this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {

                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        nickname = map.get("name");
                        openid = map.get("openid");
                        headimgurl = map.get("profile_image_url");
                        sex = map.get("sex");
                        maps.clear();
                        maps.put("id", openid);
                        presenter.getpost(Contacts.WX_Log, headmap, maps, Log_Bean.class);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
                break;
            case R.id.log_new_user:
                //密码登录
                type = 1;
                log_new_user.setVisibility(View.GONE);
                log_sms_user.setVisibility(View.VISIBLE);
                et_code.setVisibility(View.GONE);
                et_password_code.setVisibility(View.VISIBLE);
                tv_forgot_password.setVisibility(View.VISIBLE);
                rl_id.setVisibility(View.GONE);
                et_code.setText("");
                break;
            case R.id.log_sms_user:
                //验证码登录
                type = 2;
                log_new_user.setVisibility(View.VISIBLE);
                log_sms_user.setVisibility(View.GONE);
                et_code.setVisibility(View.VISIBLE);
                et_password_code.setVisibility(View.GONE);
                tv_forgot_password.setVisibility(View.GONE);
                rl_id.setVisibility(View.VISIBLE);
                et_password_code.setText("");
                break;
            case R.id.btn_login_confirm:
                String phone = et_change_password_mobile.getText().toString();
                if (phone != null) {
                    //验证码
                    if (type == 1) {
                        //密码登录
                        String password = et_password_code.getText().toString();
                        if (password != null) {
                            maps.clear();
                            maps.put("tel", phone);
                            maps.put("password", password);
                            presenter.getpost(Contacts.Log, headmap, maps, Log_Bean.class);
                        } else {
                            ToastUtil.showShort(this, "请输入密码");
                        }
                    } else if (type == 2) {
                        //验证码登录
                        String smscode = et_code.getText().toString();
                        if (EmptyUtils.isNotEmpty(smscode) && smscode.length() == 6) {
                            SMSSDK.submitVerificationCode("86", phone, smscode);
                        } else {
                            ToastUtil.showShort(this, "请输入验证码");
                        }
                    }
                } else {
                    ToastUtil.showShort(this, "请输入手机号");
                }
                break;
            case R.id.rl_id:

                break;
            case R.id.tv_forgot_password:
                //忘记密码
                IntentUtils.getIntents().Intent(this, ForgotPasswordctivity.class, null);
                break;
            case R.id.btn_change_password_get_code:
                String me = et_change_password_mobile.getText().toString();
                ToastUtil.showShort(this, me);
                SMSSDK.registerEventHandler(eventHandler);
                if (isChinaPhoneLegal(me)) {
                    SMSSDK.getVerificationCode("86", me);
                    down.setTextColor(getResources().getColor(R.color.theme_orange));
                    down.setVisibility(View.VISIBLE);
                    rl_id.setVisibility(View.INVISIBLE);
                    mCountDownTextUtils = new CountDownTextUtils(60000, 1000, this);
                    mCountDownTextUtils.start();
                } else {
                    ToastUtil.showShort(this, "请重新输入手机号");
                }
                break;
            case R.id.tv_user_registration:
                IntentUtils.getIntents().Intent(this, New_User_Activity.class, null);
                break;
        }
    }

    @Override
    public void setState(boolean ishow) {
        super.setState(ishow);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
                }
            }
        } catch (Exception e) {
            Log.w(TAG, "onPause: ", e);
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof Log_Bean) {
            Log_Bean log_bean = (Log_Bean) data;
            Log_Bean.DataBean data1 = log_bean.getData();
            String code = log_bean.getCode();
            if (log_bean.getData() != null) {
                if (code.equals("200")) {
                    appid = data1.getAppid();
                    appsecret = data1.getAppsecret();
                    SpzUtils.putString("appid", appid);
                    SpzUtils.putString("appsecret", appsecret);
                    //获得当前时间戳
                    long time = System.currentTimeMillis();
                    String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                    String sha1 = EncryptUtils.getSHA(sha);
                    //获得用户的信息
                    user.clear();
                    user.put("appid", appid);
                    user.put("appsecret", appsecret);
                    user.put("timestamp", time);
                    user.put("sign", sha1);
                    presenters.getpost(Contacts.User, users, user, User_Bean.class);
                } else if (code.equals("005")) {
                    //005是没有绑定手机
                    Bundle bundle = new Bundle();
                    appid = data1.getAppid();
                    appsecret = data1.getAppid();
                    String id = data1.getId();
                    String pic = data1.getPic();
                    String name = data1.getName();
                    String sex = data1.getSex();
                    String type = data1.getType();
                    bundle.putString("type", type);
                    //判断是不是微信，如果是需要拿微信返的传值
                    if (type.equals("WeChat")) {
                        bundle.putString("id", openid);
                        bundle.putString("pic", headimgurl);
                        bundle.putString("name", nickname);
                        bundle.putString("sex", "未知");
                    } else {
                        bundle.putString("id", id);
                        bundle.putString("pic", pic);
                        bundle.putString("name", name);
                        bundle.putString("sex", sex);
                    }
                    IntentUtils.getIntents().Intent(this, Bind_phoneActivity.class, bundle);
                }
            }
        } else if (data instanceof Zfb_Bean) {
            if (data instanceof Zfb_Bean) {
                Zfb_Bean zfb_bean = (Zfb_Bean) data;
                if (zfb_bean.getCode().equals("200")) {
                    if (EmptyUtils.isNotEmpty(zfb_bean.getData())) {
                        auth = zfb_bean.getData();
                        Runnable authRunnable = new Runnable() {
                            @Override
                            public void run() {
                                AuthTask authTask = new AuthTask(MainActivity.this);
                                // 调用授权接口，获取授权结果
                                Map<String, String> result = authTask.authV2(auth, true);
                                Message msg = new Message();
                                msg.what = SDK_AUTH_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };
                        // 必须异步调用
                        Thread authThread = new Thread(authRunnable);
                        authThread.start();
                    }
                }
            }
        } else if (data instanceof User_Bean) {
            User_Bean user_bean = (User_Bean) data;
            if (user_bean != null) {
                if (user_bean.getCode().equals("200")) {
                    LoginUtil.getInstance().saveData(App.appContext, user_bean.getData());
                    IntentUtils.getIntents().Intent(this, AllActivity.class, null);
                    ToastUtil.showShort(this, "登录成功");
                    finish();
                }
            } else {
                ToastUtil.showShort(this, "请重新登录");
            }
        } else if (data instanceof Log_Bean) {
            Log_Bean log_bean = (Log_Bean) data;
            Log_Bean.DataBean data1 = log_bean.getData();
            String code = log_bean.getCode();
            if (code != null) {
                if (code.equals("200")) {
                    String appid = data1.getAppid();
                    String appsecret = data1.getAppsecret();
                    if (EmptyUtils.isNotEmpty(appid) && EmptyUtils.isNotEmpty(appsecret)) {
                        SpzUtils.putString("appid", appid);
                        SpzUtils.putString("appsecret", appsecret);
                        //获得当前时间戳
                        long time = System.currentTimeMillis();
                        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                        String sha1 = EncryptUtils.getSHA(sha);
                        user.clear();
                        user.put("appid", appid);
                        user.put("appsecret", appsecret);
                        user.put("timestamp", time);
                        user.put("sign", sha1);
                        presenters.getpost(Contacts.User, users, user, User_Bean.class);
                    }
                } else {
                    ToastUtil.showShort(this, "请重新登录");
                }
            }
        }
    }

    @Override
    public void error(String error) {

    }


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
            rl_id.setVisibility(View.VISIBLE);
            down.setVisibility(View.GONE);
        } catch (Exception e) {
            Log.e(TAG, "onCountDownFinish: ", e.getCause());
        }
    }


    //调用支付宝SDK获取authcode;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    Log.d(TAG, "handleMessage: " + resultStatus);
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        ToastUtil.showShort(MainActivity.this, "授权成功");
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value， 传入，则支付账户为该授权账户
                        //开发者自己的方法，把code传给后台同事，他们拿code换token,这里是后台自己去获取用户信息
                        String authCode = authResult.getAuthCode();
                        HashMap<String, Object> zfb = new HashMap<>();
                        HashMap<String, Object> zfbs = new HashMap<>();
                        zfbs.put("code", authCode);
                        MyPresenterImpl presenters = new MyPresenterImpl(MainActivity.this);
                        presenters.getpost(Contacts.zfb_Logs, zfb, zfbs, Log_Bean.class);
                    } else {
                        // 其他状态值则为授权失败
                        if (TextUtils.isEmpty(authResult.getAuthCode())) {
                            ToastUtil.showShort(MainActivity.this, "授权取消");
                        } else {
                            ToastUtil.showShort(MainActivity.this, String.format("授权失败_authCode:%s", authResult.getAuthCode()));
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    public static class AuthResult {

        private String resultStatus;
        private String result;
        private String memo;
        private String resultCode;
        private String authCode;
        private String alipayOpenId;

        public AuthResult(Map<String, String> rawResult, boolean removeBrackets) {
            if (rawResult == null) {
                return;
            }

            for (String key : rawResult.keySet()) {
                if (TextUtils.equals(key, "resultStatus")) {
                    resultStatus = rawResult.get(key);
                } else if (TextUtils.equals(key, "result")) {
                    result = rawResult.get(key);
                } else if (TextUtils.equals(key, "memo")) {
                    memo = rawResult.get(key);
                }
            }

            String[] resultValue = result.split("&");
            for (String value : resultValue) {
                if (value.startsWith("alipay_open_id")) {
                    alipayOpenId = removeBrackets(getValue("alipay_open_id=", value), removeBrackets);
                    continue;
                }
                if (value.startsWith("auth_code")) {
                    authCode = removeBrackets(getValue("auth_code=", value), removeBrackets);
                    continue;
                }
                if (value.startsWith("result_code")) {
                    resultCode = removeBrackets(getValue("result_code=", value), removeBrackets);
                    continue;
                }
            }

        }

        private String removeBrackets(String str, boolean remove) {
            if (remove) {
                if (!TextUtils.isEmpty(str)) {
                    if (str.startsWith("\"")) {
                        str = str.replaceFirst("\"", "");
                    }
                    if (str.endsWith("\"")) {
                        str = str.substring(0, str.length() - 1);
                    }
                }
            }
            return str;
        }

        @Override
        public String toString() {
            return "resultStatus={" + resultStatus + "};memo={" + memo + "};result={" + result + "}";
        }

        private String getValue(String header, String data) {
            return data.substring(header.length(), data.length());
        }

        /**
         * @return the resultStatus
         */
        public String getResultStatus() {
            return resultStatus;
        }

        /**
         * @return the memo
         */
        public String getMemo() {
            return memo;
        }

        /**
         * @return the result
         */
        public String getResult() {
            return result;
        }

        /**
         * @return the resultCode
         */
        public String getResultCode() {
            return resultCode;
        }

        /**
         * @return the authCode
         */
        public String getAuthCode() {
            return authCode;
        }

        /**
         * @return the alipayOpenId
         */
        public String getAlipayOpenId() {
            return alipayOpenId;
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

                            IntentUtils.getIntents().Intent(MainActivity.this, AllActivity.class, null);
                        } else {
                            // TODO 处理错误的结果
                            ToastUtil.showShort(MainActivity.this, "请重新输入验证码");
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

}
