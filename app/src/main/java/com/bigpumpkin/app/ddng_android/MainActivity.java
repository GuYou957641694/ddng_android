package com.bigpumpkin.app.ddng_android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.alipay.sdk.app.AuthTask;
import com.bigpumpkin.app.ddng_android.activity.AllActivity;
import com.bigpumpkin.app.ddng_android.activity.Bind_phoneActivity;
import com.bigpumpkin.app.ddng_android.adapter.UnicornCourseMainPagerAdapter;
import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Log_Bean;
import com.bigpumpkin.app.ddng_android.bean.User_Bean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
import com.bigpumpkin.app.ddng_android.fragment.Faster_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Log_Fragment;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MyView {

    private int SDK_AUTH_FLAG = 1;
    @BindView(R.id.tl_unicorn_course_main)
    TabLayout tlUnicornCourseMain;
    @BindView(R.id.unicorn_lv)
    ViewPager unicornLv;
    @BindView(R.id.log_qq)
    ImageView logQq;
    @BindView(R.id.log_wx)
    ImageView logWx;
    private List<Fragment> listFragment;
    private UnicornCourseMainPagerAdapter unicornCourseMainPagerAdapter;
    private HashMap<String, Object> maps;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> user;
    private HashMap<String, Object> users;
    private MyPresenterImpl presenter;
    private String auth;
    private String appid;
    private String appsecret;
    private String str = "apiname=com.alipay.account.auth&app_id=2019080966113968&app_name=WEILAILINGDI&auth_type=AUTHACCOUNT&biz_type=openservice&method=alipay.open.auth.sdk.code.get&pid=2088431983598828&product_id=APP_FAST_LOGIN&scope=kuaijie&sign_type=RSA2&target_id=c246f9ad32c42bbcb2a02d0c7c62e108&sign=apiname%3Dcom.alipay.account.auth%26app_id%3D2019080966113968%26app_name%3DWEILAILINGDI%26auth_type%3DAUTHACCOUNT%26biz_type%3Dopenservice%26method%3Dalipay.open.auth.sdk.code.get%26pid%3D2088431983598828%26product_id%3DAPP_FAST_LOGIN%26scope%3Dkuaijie%26sign_type%3DRSA2%26target_id%3Dc246f9ad32c42bbcb2a02d0c7c62e108";

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        listFragment = new ArrayList<>();
        listFragment.add(new Faster_Fragment());
        listFragment.add(new Log_Fragment());
        unicornCourseMainPagerAdapter = new UnicornCourseMainPagerAdapter(getSupportFragmentManager(), listFragment);
        unicornLv.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlUnicornCourseMain));
        tlUnicornCourseMain.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(unicornLv));
        unicornLv.setAdapter(unicornCourseMainPagerAdapter);
        presenter = new MyPresenterImpl(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setState(boolean ishow) {
        super.setState(ishow);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.log_qq, R.id.log_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                        String nickname = map.get("name");
                        String openid = map.get("openid");
                        String headimgurl = map.get("profile_image_url");
                        headmap = new HashMap<>();
                        maps = new HashMap<>();
                        maps.put("openid", openid);
                        maps.put("name", nickname);
                        maps.put("pic", headimgurl);
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
        }
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
            Log.d(TAG, "success: " + code);
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
                    user = new HashMap<>();
                    users = new HashMap<>();
                    user.put("appid", appid);
                    user.put("appsecret", appsecret);
                    user.put("timestamp", time);
                    user.put("sign", sha1);
                    MyPresenterImpl presenters = new MyPresenterImpl(this);
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
                    bundle.putString("id", id);
                    bundle.putString("pic", pic);
                    bundle.putString("name", name);
                    bundle.putString("sex", sex);
                    bundle.putString("type", type);
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
        }
    }

    @Override
    public void error(String error) {

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
                        Log.d(TAG, "handleMessage: " + authCode);
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


}
