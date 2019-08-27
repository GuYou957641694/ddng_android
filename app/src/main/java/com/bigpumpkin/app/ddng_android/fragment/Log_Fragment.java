package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.AgreementActivity;
import com.bigpumpkin.app.ddng_android.activity.AllActivity;
import com.bigpumpkin.app.ddng_android.activity.Forget_pass_Activity;
import com.bigpumpkin.app.ddng_android.activity.RegisterActivity;
import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.bean.Log_Bean;
import com.bigpumpkin.app.ddng_android.bean.User_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.LoginUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Log_Fragment extends BaseFragment implements MyView {
    @BindView(R.id.et_change_password_mobile)
    EditText etChangePasswordMobile;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.et_change_password_code)
    EditText etChangePasswordCode;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.checked)
    CheckBox checked;
    @BindView(R.id.my)
    TextView my;
    @BindView(R.id.protocol)
    TextView protocol;
    @BindView(R.id.btn_login_confirm)
    Button btnLoginConfirm;
    @BindView(R.id.new_user)
    TextView newUser;
    @BindView(R.id.forget_pass)
    TextView forgetPass;
    Unbinder unbinder;
    private HashMap<String, Object> map;
    private HashMap<String, Object> user;
    private HashMap<String, Object> users;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;


    @Override
    protected int getLayoutId() {
        return R.layout.log_fragment;
    }

    @Override
    protected void init(View view) {
        presenter = new MyPresenterImpl(this);
        etChangePasswordCode.setTransformationMethod(PasswordTransformationMethod.getInstance()); //设置为密码输入框

    }

    @Override
    protected void loadData() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.protocol, R.id.btn_login_confirm, R.id.new_user, R.id.forget_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.protocol:
                //用户注册协议
                IntentUtils.getIntents().Intent(getActivity(), AgreementActivity.class, null);
                break;
            case R.id.btn_login_confirm:
                String mobile = etChangePasswordMobile.getText().toString();
                String password = etChangePasswordCode.getText().toString();
                if (checked.isChecked() == true) {
                    headmap = new HashMap<>();
                    map = new HashMap<>();
                    if (EmptyUtils.isNotEmpty(mobile) && EmptyUtils.isNotEmpty(password)) {
                        if (isChinaPhoneLegal(mobile)) {
                            map.put("tel", mobile);
                            map.put("password", password);
                            presenter.getpost(Contacts.Log, headmap, map, Log_Bean.class);
                          /*  if (PwdCheckUtil.isLetterDigit(password)) {
                            } else {
                                ToastUtil.showShort(getActivity(), "密码输入不符");
                            }*/
                        } else {
                            ToastUtil.showShort(getActivity(), "请重新输入手机号");
                        }
                    } else {
                        ToastUtil.showShort(getActivity(), "请重新输入");
                    }
                } else {
                    ToastUtil.showShort(getActivity(), "请选中用户注册协议");
                }
                break;
            case R.id.new_user:
                //新用户注册
                IntentUtils.getIntents().Intent(getActivity(), RegisterActivity.class, null);
                break;
            case R.id.forget_pass:
                //忘记密码
                IntentUtils.getIntents().Intent(getActivity(), Forget_pass_Activity.class, null);
                break;
        }
    }


    @Override
    public void success(Object data) {
        if (data instanceof Log_Bean) {
            Log_Bean log_bean = (Log_Bean) data;
            String code = log_bean.getCode();
            if (code != null) {
                if (code.equals("200")) {
                    Log_Bean.DataBean data1 = log_bean.getData();
                    String appid = data1.getAppid();
                    String appsecret = data1.getAppsecret();
                    if (EmptyUtils.isNotEmpty(appid) && EmptyUtils.isNotEmpty(appsecret)) {
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
                    } else {
                        ToastUtil.showShort(getActivity(), "请重新登录");
                    }
                } else if (code.equals("002")) {
                    ToastUtil.showShort(getActivity(), "手机号未注册");
                } else if (code.equals("001")) {
                    ToastUtil.showShort(getActivity(), "登录名或密码错误");
                } else if (code.equals("003")) {
                    ToastUtil.showShort(getActivity(), "账号已冻结，请联系客服");
                }
            }
        } else if (data instanceof User_Bean) {
            User_Bean user_bean = (User_Bean) data;
            if (user_bean != null) {
                LoginUtil.getInstance().saveData(App.appContext, user_bean.getData());
                IntentUtils.getIntents().Intent(getActivity(), AllActivity.class, null);
                ToastUtil.showShort(getActivity(), "登录成功");
            } else {
                ToastUtil.showShort(getActivity(), "请重新登录");
            }
        }
    }

    @Override
    public void error(String error) {

    }

    //手机号验证
    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
