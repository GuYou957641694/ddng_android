package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
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
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.utils.CountDownTextUtils;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Faster_Fragment extends BaseFragment implements CountDownTextUtils.CountDownListener {

    private static final String TAG = "Faster_Fragment";
    @BindView(R.id.et_change_password_mobile)
    EditText etChangePasswordMobile;
    @BindView(R.id.et_change_password_code)
    EditText etChangePasswordCode;
    @BindView(R.id.btn_change_password_get_code)
    TextView btnChangePasswordGetCode;
    @BindView(R.id.checked)
    CheckBox checked;
    @BindView(R.id.protocol)
    TextView protocol;
    @BindView(R.id.btn_login_confirm)
    Button btnLoginConfirm;
    @BindView(R.id.log_new_user)
    TextView logNewUser;
    @BindView(R.id.forgot_password)
    TextView forgotPassword;
    Unbinder unbinder;
    @BindView(R.id.down)
    TextView down;
    private CountDownTextUtils mCountDownTextUtils;
    private String mobile;

    @Override
    protected int getLayoutId() {
        return R.layout.faster_fragment;
    }

    @Override
    protected void init(View view) {

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


    @OnClick({R.id.btn_change_password_get_code, R.id.checked, R.id.protocol, R.id.btn_login_confirm, R.id.log_new_user, R.id.forgot_password})
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
                    ToastUtil.showShort(getActivity(), "请重新输入手机号");
                }
                break;
            case R.id.checked:

                break;
            case R.id.protocol:
                //用户注册协议
                IntentUtils.getIntents().Intent(getActivity(), AgreementActivity.class, null);
                break;
            case R.id.btn_login_confirm:
                //登录
                String s = etChangePasswordCode.getText().toString();
                mobile = etChangePasswordMobile.getText().toString();
                // 提交验证码，其中的code表示验证码，如“1357”
                if (checked.isChecked() == true) {
                    if (EmptyUtils.isNotEmpty(s)) {
                        SMSSDK.submitVerificationCode("86", mobile, s);
                    } else {
                        ToastUtil.showShort(getActivity(), "请输入手机号");
                    }
                } else {
                    ToastUtil.showShort(getActivity(), "请选中用户注册协议");
                }
                break;
            case R.id.log_new_user:
                //新用户注册
                IntentUtils.getIntents().Intent(getActivity(), RegisterActivity.class, null);
                break;
            case R.id.forgot_password:
                //忘记密码
                IntentUtils.getIntents().Intent(getActivity(), Forget_pass_Activity.class, null);
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

                            IntentUtils.getIntents().Intent(getActivity(), AllActivity.class, null);
                        } else {
                            // TODO 处理错误的结果
                            ToastUtil.showShort(getActivity(), "请重新输入验证码");
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
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
}
