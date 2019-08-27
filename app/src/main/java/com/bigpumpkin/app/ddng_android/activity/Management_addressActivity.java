package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Address_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Address_Bean;
import com.bigpumpkin.app.ddng_android.bean.Del_Address_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Management_addressActivity extends BaseActivity implements MyView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.add)
    Button add;
    private RelativeLayout relativeLayout;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> defaults;
    private HashMap<String, Object> defaultss;
    private HashMap<String, Object> del;
    private HashMap<String, Object> dels;
    private MyPresenterImpl presenter;
    private Address_Adapter address_adapter;
    private List<Address_Bean.DataBean> data1;
    private String sha1;
    private long time;
    private int i;

    @Override
    public int intiLayout() {
        return R.layout.activity_management_address;
    }

    @Override
    public void initView() {
        new TitleXML(this, "管理收货地址", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        relativeLayout = findViewById(R.id.no_address);
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
        presenter.getpost(Contacts.My_address, headmap, map, Address_Bean.class);

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.add)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add:
                IntentUtils.getIntents().Intent(this, AddressActivity.class, null);
                break;
        }
    }


    @Override
    public void success(Object data) {
        if (data instanceof Address_Bean) {
            Address_Bean address_bean = (Address_Bean) data;
            String code = address_bean.getCode();
            if (code.equals("200")) {
                int size = address_bean.getData().size();
                if (size > 0) {
                    data1 = address_bean.getData();
                    recyclerview.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.GONE);
                    //进行筛选默认地址
                    Collections.sort(data1, new Comparator<Address_Bean.DataBean>() {
                        @Override
                        public int compare(Address_Bean.DataBean o1, Address_Bean.DataBean o2) {
                            return (int) (o1.getIsindex() - o2.getIsindex());
                        }
                    });
                    for (Address_Bean.DataBean dataBean : data1) {
                        address_adapter = new Address_Adapter(data1, Management_addressActivity.this);
                        //添加布局管理器
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        recyclerview.setLayoutManager(linearLayoutManager);
                        recyclerview.setAdapter(address_adapter);
                    }
                } else {
                    recyclerview.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            }
            //删除
            address_adapter.setOnItemClickListener(new Address_Adapter.OnItemClickListener() {

                @Override
                public void onClick(int position) {
                    String uid = data1.get(position).getId();
                    del = new HashMap<>();
                    dels = new HashMap<>();
                    dels.put("appid", appid);
                    dels.put("appsecret", appsecret);
                    dels.put("timestamp", time);
                    dels.put("sign", sha1);
                    dels.put("id", uid);
                    presenter.getpost(Contacts.My_del_address, del, dels, Del_Address_Bean.class);
                    i = position;
                }
            });
            //设置默认
            address_adapter.setOnItemdefaultClickListener(new Address_Adapter.OnItemdefaultClickListener() {
                @Override
                public void ondefaultClick(int position) {
                    defaults = new HashMap<>();
                    defaultss = new HashMap<>();
                    defaultss.put("appid", appid);
                    defaultss.put("appsecret", appsecret);
                    defaultss.put("timestamp", time);
                    defaultss.put("sign", sha1);
                    defaultss.put("id", data1.get(position).getId());
                    presenter.getpost(Contacts.My_default_address, defaults, defaultss, Address_Bean.class);
                }
            });
        } else if (data instanceof Del_Address_Bean) {
            Del_Address_Bean del_address_bean = (Del_Address_Bean) data;
            String code = del_address_bean.getCode();
            if (code.equals("200")) {
                data1.remove(i);
                address_adapter.notifyItemRemoved(i);
                address_adapter.notifyAll();
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getpost(Contacts.My_address, headmap, map, Address_Bean.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
