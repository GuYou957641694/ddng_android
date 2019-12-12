package com.bigpumpkin.app.ddng_android.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.IntegralAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.IntegralBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.HashMap;

public class IntegralFragment extends BaseLazyLoadFragment implements MyView {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RecyclerView recyclerView;
    private String sha1;
    private long time;
    private String appid;
    private String appsecret;
    private int position;
    private IntegralAdapter integralAdapter;

    @Override
    public int getLayout() {
        return R.layout.integralfragment;
    }

    @Override
    public void initViews(View view) {
        IntegralBean integralBean = (IntegralBean) getArguments().getSerializable("bean");
        position = getArguments().getInt("position");
        recyclerView = view.findViewById(R.id.recyclerview);
        integralAdapter = new IntegralAdapter(getActivity(), integralBean, position);
        recyclerView.setAdapter(integralAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
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
        if (position ==1){
            map.put("type", 3);
            map.put("page", 1);
            presenter.getpost(Contacts.MembersIntegrations, headmap, map, IntegralBean.class);
        }else if (position ==2){
            map.put("type", 2);
            map.put("page", 1);
            presenter.getpost(Contacts.MembersIntegrations, headmap, map, IntegralBean.class);
        }
    }

    @Override
    public void loadData() {

    }

    @Override
    public void success(Object data) {
        if (data instanceof IntegralBean) {
            IntegralBean integralBean = (IntegralBean) data;
            integralAdapter = new IntegralAdapter(getActivity(), integralBean, position);
            recyclerView.setAdapter(integralAdapter);
        }
    }

    @Override
    public void error(String error) {

    }
}
