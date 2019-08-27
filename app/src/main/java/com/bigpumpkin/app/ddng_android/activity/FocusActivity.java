package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.FocusApapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Cancel_Colledctions_Bean;
import com.bigpumpkin.app.ddng_android.bean.Focus_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FocusActivity extends BaseActivity implements MyView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String s;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private FocusApapter focusApapter;
    private long curTimeLong;
    private List<Focus_Bean.DataBean> datas;
    private int i;
    private String appid;
    private String appsecret;
    private String sha1;
    private RelativeLayout relativeLayout;

    @Override
    public int intiLayout() {
        return R.layout.activity_focus;
    }

    @Override
    public void initView() {
        new TitleXML(FocusActivity.this, "我的关注", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        relativeLayout = findViewById(R.id.no_focus);
        presenter = new MyPresenterImpl(this);
        curTimeLong = getCurTimeLong();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + curTimeLong;
        sha1 = EncryptUtils.getSHA(sha);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", curTimeLong);
        map.put("sign", sha1);
        presenter.getpost(Contacts.My_Focuss, headmap, map, Focus_Bean.class);
    }

    @Override
    public void initData() {

    }

    public long getCurTimeLong() {
        long time = System.currentTimeMillis();
        return time;
    }


    @Override
    public void success(Object data) {
        if (data instanceof Focus_Bean) {
            Focus_Bean focus_bean = (Focus_Bean) data;
            datas = focus_bean.getData();
            if (datas.size() > 0) {
                focusApapter = new FocusApapter(datas, this);
                //添加布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerview.setLayoutManager(linearLayoutManager);
                recyclerview.setAdapter(focusApapter);
                recyclerview.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
            } else {
                recyclerview.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }
            focusApapter.setOnItemClickListener(new FocusApapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    HashMap<String, Object> map = new HashMap<>();
                    HashMap<String, Object> headmap = new HashMap<>();
                    String vaid = datas.get(position).getId();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", curTimeLong);
                    map.put("sign", sha1);
                    map.put("id", vaid);
                    if (vaid != null) {
                        presenter.getpost(Contacts.My_cancel_Focuss, headmap, map, Cancel_Colledctions_Bean.class);
                    }
                    i = position;
                }
            });
        } else if (data instanceof Cancel_Colledctions_Bean) {
            Cancel_Colledctions_Bean cancel_colledctions_bean = (Cancel_Colledctions_Bean) data;
            String code = cancel_colledctions_bean.getCode();
            if (EmptyUtils.isNotEmpty(code)) {
                if (code.equals("200")) {
                    datas.remove(i);
                    focusApapter.notifyItemRemoved(i);
                } else {
                    ToastUtil.showShort(FocusActivity.this, "取消失败");
                }
            }
        }
    }

    @Override
    public void error(String error) {
        Log.d(TAG, "error: " + error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
