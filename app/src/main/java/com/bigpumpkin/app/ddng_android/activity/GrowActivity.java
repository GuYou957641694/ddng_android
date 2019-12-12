package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.GrowApadter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Grow_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GrowActivity extends BaseActivity implements MyView {

    @BindView(R.id.grow)
    RecyclerView grow;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private GrowApadter growApadter;
    private List<Grow_Bean.DataBean> data1;
    private RelativeLayout relativeLayout;
    private String sha1;
    private long time;

    @Override
    public int intiLayout() {
        return R.layout.activity_grow;
    }

    @Override
    public void initView() {
        new TitleXML(GrowActivity.this, "生长中", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        relativeLayout = findViewById(R.id.no_grow);
        presenter = new MyPresenterImpl(this);
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        headmap = new HashMap<>();
        map = new HashMap<>();

    }

    @Override
    public void initData() {
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        presenter.getpost(Contacts.My_growing, headmap, map, Grow_Bean.class);
    }


    @Override
    public void success(Object data) {
        if (data instanceof Grow_Bean) {
            Grow_Bean grow_bean = (Grow_Bean) data;
            if (grow_bean.getCode().equals("200")) {
                if (grow_bean.getData().size() > 0 && grow_bean.getData() != null) {
                    data1 = grow_bean.getData();
                    growApadter = new GrowApadter(data1, GrowActivity.this);
                    //添加布局管理器
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    grow.setLayoutManager(linearLayoutManager);
                    grow.setAdapter(growApadter);
                    relativeLayout.setVisibility(View.GONE);
                    grow.setVisibility(View.VISIBLE);
                }
            } else {
                //没有数据
                relativeLayout.setVisibility(View.VISIBLE);
                grow.setVisibility(View.GONE);
            }
            growApadter.setOnItemClickListener(new GrowApadter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    Bundle bundle = new Bundle();
                    String id = data1.get(position).getId();
                    String type_v = data1.get(position).getType_v();
                    bundle.putString("id", id);
                    bundle.putString("type_v", type_v);
                    IntentUtils.getIntents().Intent(GrowActivity.this, Order_Details_Activity.class, bundle);
                }
            });

            growApadter.OninformationClickListener(new GrowApadter.OninformationClickListener() {
                @Override
                public void onClick(int position) {
                    //查看信息
                    Bundle bundle = new Bundle();
                    String id = data1.get(position).getId();
                    bundle.putString("id", id);
                    IntentUtils.getIntents().Intent(GrowActivity.this, GrowthInformationActivity.class, bundle);
                }
            });


        }
    }

    @Override
    public void error(String error) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
