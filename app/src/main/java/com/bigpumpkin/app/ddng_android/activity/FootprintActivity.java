package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.ExPandableListViewAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Footprint_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootprintActivity extends BaseActivity implements MyView {

    @BindView(R.id.edpandablelistview)
    ExpandableListView edpandablelistview;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String sha1;
    private long time;
    private ExPandableListViewAdapter adapter;
    private String appid;
    private String appsecret;
    private RelativeLayout relativeLayout;

    @Override
    public int intiLayout() {
        return R.layout.activity_footprint;
    }

    @Override
    public void initView() {
        new TitleXML(FootprintActivity.this, "我的足迹", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        relativeLayout = findViewById(R.id.no_footpirnt);
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
        presenter.getpost(Contacts.My_Footprint, headmap, map, Footprint_Bean.class);

    }

    @Override
    public void initData() {

    }


    @Override
    public void success(Object data) {
        if (data instanceof Footprint_Bean) {
            Footprint_Bean footprint_bean = (Footprint_Bean) data;
            Footprint_Bean.DataBean data1 = footprint_bean.getData();
            if (data1.getList().size() > 0) {
                // 遍历所有group,将所有项设置成默认展开
                List<Footprint_Bean.DataBean.ListBean> list = data1.getList();
                adapter = new ExPandableListViewAdapter(this, list);
                edpandablelistview.setAdapter(adapter);
                edpandablelistview.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
                edpandablelistview.setGroupIndicator(null);
                for (int i = 0; i < list.size(); i++) {
                    edpandablelistview.expandGroup(i);
                }

            } else {
                edpandablelistview.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }
            //不能点击收缩
            edpandablelistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    return true;
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
