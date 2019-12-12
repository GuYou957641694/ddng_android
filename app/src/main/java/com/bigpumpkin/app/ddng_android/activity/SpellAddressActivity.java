package com.bigpumpkin.app.ddng_android.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.SpellAddressAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Address_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

public class SpellAddressActivity extends BaseActivity implements MyView {

    RecyclerView recyclerview;
    private RelativeLayout relativeLayout;
    private String appid;
    private String appsecret;
    private String sha1;
    private long time;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String id;
    private Button btAdd;

    @Override
    public int intiLayout() {
        return R.layout.activity_spell_address;
    }

    @Override
    public void initView() {
        new TitleXML(this, "收货地址", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        id = getIntent().getStringExtra("id");
        relativeLayout = findViewById(R.id.no_address);
        recyclerview = findViewById(R.id.recyclerview);
        btAdd = findViewById(R.id.bt_add);
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
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getIntents().Intent(SpellAddressActivity.this, AddressActivity.class, null);
            }
        });

    }

    @Override
    public void initData() {
        presenter.getpost(Contacts.My_address, headmap, map, Address_Bean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof Address_Bean) {
            Address_Bean address_bean = (Address_Bean) data;
            if (address_bean.getData().size() > 0) {
                relativeLayout.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerview.setLayoutManager(linearLayoutManager);
                List<Address_Bean.DataBean> data1 = address_bean.getData();
                SpellAddressAdapter spellAddressAdapter = new SpellAddressAdapter(data1, this, id);
                recyclerview.setAdapter(spellAddressAdapter);
                //返回确认订单页面
                spellAddressAdapter.setOnItemClickListener(new SpellAddressAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        Intent intent = new Intent();
                        String sheng = data1.get(position).getSheng();
                        String shi = data1.get(position).getShi();
                        String qu = data1.get(position).getQu();
                        String address = data1.get(position).getAddress();
                        String name = data1.get(position).getName();
                        String tel = data1.get(position).getTel();
                        String id = data1.get(position).getId();
                        int isindex = data1.get(position).getIsindex();
                        intent.putExtra("sheng", sheng);
                        intent.putExtra("shi", shi);
                        intent.putExtra("qu", qu);
                        intent.putExtra("address", address);
                        intent.putExtra("name", name);
                        intent.putExtra("tel", tel);
                        intent.putExtra("id", id);
                        intent.putExtra("isindex", isindex);
                        SpellAddressActivity.this.setResult(RESULT_OK,intent);
                        finish();
                    }
                });
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
}
