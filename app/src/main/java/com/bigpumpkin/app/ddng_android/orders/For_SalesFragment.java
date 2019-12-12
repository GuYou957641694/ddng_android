package com.bigpumpkin.app.ddng_android.orders;

import android.view.View;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;

public class For_SalesFragment extends BaseLazyLoadFragment implements MyView {

    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RelativeLayout relativeLayout;
    private String sha1;
    private long time;
    private String numbering;

    @Override
    public int getLayout() {
        return R.layout.forselesfragment;
    }

    @Override
    public void initViews(View view) {
        relativeLayout = view.findViewById(R.id.no_orders);
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
    public void loadData() {

    }

    @Override
    public void success(Object data) {

    }

    @Override
    public void error(String error) {

    }

}
