package com.bigpumpkin.app.ddng_android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Agreement_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.net.Contract;
import com.bigpumpkin.app.ddng_android.persenter.PresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AgreementActivity extends BaseActivity implements Contract.View {


    private PresenterImpl presenter;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> map;

    @Override
    public int intiLayout() {
        return R.layout.activity_agreement;
    }

    @Override
    public void initView() {
        headmap = new HashMap<>();
        map = new HashMap<>();
        presenter = new PresenterImpl(this);
        presenter.get(Contacts.Agreement, headmap, map, Agreement_Bean.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void success(Object success) {
        if (success instanceof Agreement_Bean) {
            Agreement_Bean agreement_bean = (Agreement_Bean) success;
            String code = agreement_bean.getCode();
            if (code.equals("200")) {
                ToastUtil.showShort(this, "成功");
            } else {
                ToastUtil.showShort(this, "shibai ");

            }
        }
    }

    @Override
    public void error(String error) {

    }

    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}
