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
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//收货地址
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
    private LoadingDialog dialog;

    @Override
    public int intiLayout() {
        return R.layout.activity_management_address;
    }

    @Override
    public void initView() {
        new TitleXML(this, "收货地址", true, "").init().setListener(new TitleXML.TitleXMLClick() {
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
    }

    @Override
    public void initData() {
        dialog = new LoadingDialog(this, "玩命加载中...");
        dialog.show();
        presenter.getpost(Contacts.My_address, headmap, map, Address_Bean.class);
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
            dialog.close();
            Address_Bean address_bean = (Address_Bean) data;
            if (address_bean.getData() != null) {
                data1 = address_bean.getData();
                recyclerview.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
                address_adapter = new Address_Adapter(data1, Management_addressActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerview.setLayoutManager(linearLayoutManager);
                address_adapter.setOnItemClickListener(new Address_Adapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("name", data1.get(position).getName());
                        bundle.putString("phone", data1.get(position).getTel());
                        bundle.putString("sheng", data1.get(position).getSheng());
                        bundle.putString("shi", data1.get(position).getShi());
                        bundle.putString("qu", data1.get(position).getQu());
                        bundle.putString("diqu", data1.get(position).getAddress());
                        bundle.putString("id", data1.get(position).getId());
                        bundle.putString("sheng_code", data1.get(position).getSheng_code());
                        bundle.putString("shi_code", data1.get(position).getShi_code());
                        bundle.putString("qu_code", data1.get(position).getQu_code());
                        bundle.putInt("is", data1.get(position).getIsindex());
                        IntentUtils.getIntents().Intent(Management_addressActivity.this, ModifyAddressActivity.class, bundle);
                    }
                });
                address_adapter.setOnItemdefaultClickListener(new Address_Adapter.OnItemdefaultClickListener() {
                    @Override
                    public void ondefaultClick(int position) {
                        i = position;
                        map.clear();
                        map.put("appid", appid);
                        map.put("appsecret", appsecret);
                        map.put("timestamp", time);
                        map.put("sign", sha1);
                        map.put("id", data1.get(position).getId());
                        presenter.getpost(Contacts.My_del_address, headmap, map, Zfb_Bean.class);
                    }
                });
                recyclerview.setAdapter(address_adapter);
            } else {
                recyclerview.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        } else if (data instanceof Zfb_Bean) {
            Zfb_Bean zfb_bean = (Zfb_Bean) data;
            if (zfb_bean.getCode().equals("200")) {
                data1.remove(i);
                address_adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        map.clear();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
