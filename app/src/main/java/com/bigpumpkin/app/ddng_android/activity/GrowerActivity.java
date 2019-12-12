package com.bigpumpkin.app.ddng_android.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Grower_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Farm_AttentionsBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;

public class GrowerActivity extends BaseActivity implements MyView, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String appid;
    private String appsecret;
    private String sha1;
    private long time;
    private String id, name;
    private ImageView iv_back;
    private TextView tv_title;
    private RecyclerView rv;
    private String zt;
    int j;
    private List<SpellGrowBean.DataBean> data1;
    private Grower_Adapter grower_adapter;
    private SmartRefreshLayout refreshLayout;
    private int page = 1;

    @Override
    public int intiLayout() {
        return R.layout.activity_grower;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        zt = sharedPreferences.getString("zt", "");
        //	1植物认养（果农说） 2家禽认养 4当季水果（种果人说） 5公益放生（发起人说） 6生产者说 8智慧农场 看人选园
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        rv = findViewById(R.id.rv);
        refreshLayout = findViewById(R.id.refreshLayout);
        tv_title.setText(name);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        initRefreshLayout();
        iv_back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        map.put("type", id);
        map.put("page", page);
        if (TextUtils.isEmpty(zt.trim())) {
            //没有登录
            presenter.get(Contacts.See_farmers, headmap, map, SpellGrowBean.class);
        } else {
            time = System.currentTimeMillis();
            appid = SpzUtils.getString("appid");
            appsecret = SpzUtils.getString("appsecret");
            String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
            sha1 = EncryptUtils.getSHA(sha);
            map.put("appid", appid);
            map.put("appsecret", appsecret);
            map.put("timestamp", time);
            map.put("sign", sha1);
            presenter.getpost(Contacts.See_farmers, headmap, map, SpellGrowBean.class);
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof SpellGrowBean) {
            SpellGrowBean growersBean = (SpellGrowBean) data;
            data1 = growersBean.getData();
            if (data1 != null) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                rv.setLayoutManager(linearLayoutManager);
                grower_adapter = new Grower_Adapter(this, data1);
                rv.setAdapter(grower_adapter);
                grower_adapter.setListener(new Grower_Adapter.onListener() {
                    @Override
                    public void OnListener(int i) {
                        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                        zt = sharedPreferences.getString("zt", "");
                        if (TextUtils.isEmpty(zt.trim())) {
                            ToastUtil.showShort(GrowerActivity.this, "请登录");
                        } else {
                            j = i;
                            map.clear();
                            time = System.currentTimeMillis();
                            appid = SpzUtils.getString("appid");
                            appsecret = SpzUtils.getString("appsecret");
                            String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                            sha1 = EncryptUtils.getSHA(sha);
                            map.put("appid", appid);
                            map.put("appsecret", appsecret);
                            map.put("timestamp", time);
                            map.put("sign", sha1);
                            map.put("fid", data1.get(i).getFid());
                            presenter.getpost(Contacts.Farm_Attentions, headmap, map, Farm_AttentionsBean.class);
                        }
                    }
                });
            }
        } else if (data instanceof Farm_AttentionsBean) {
            Farm_AttentionsBean farm_attentionsBean = (Farm_AttentionsBean) data;
            String code = farm_attentionsBean.getCode();
            if (code.equals("200")) {
                String code1 = farm_attentionsBean.getData().getCode();
                if (code1.equals("1")) {
                    ToastUtil.showShort(this, "关注成功");
                } else {
                    ToastUtil.showShort(this, "取消成功");
                }
                String attention = data1.get(j).getAttention();
                if (attention.equals("1")) {
                    data1.get(j).setAttention("2");
                } else {
                    data1.get(j).setAttention("1");
                }
                grower_adapter.setData1(data1);
            }
        }
    }

    @Override
    public void error(String error) {

    }

    private void initRefreshLayout() {
        //设置 Footer 为 球脉冲 样式

        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessageDelayed(message, 1000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessageDelayed(message, 1000);
            }
        });
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:         //刷新加载
                    refreshLayout.finishRefresh(true);
                    // presenter.get(Contacts.Production_areas, headmap, map, OriginBean.class);
                    break;
                case 2:         //加载更多
                    refreshLayout.finishLoadMore(true);
                    //++page;
                    //map.put("page", page);
                   // presenter.get(Contacts.Production_areas, headmap, map, OriginBean.class);
                    break;
            }
            return false;
        }
    });
}
