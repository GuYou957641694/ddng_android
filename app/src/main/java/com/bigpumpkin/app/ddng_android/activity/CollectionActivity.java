package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.CollectionsAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Cancel_collectionsBean;
import com.bigpumpkin.app.ddng_android.bean.Collections_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionActivity extends BaseActivity implements MyView {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private CollectionsAdapter collectionsAdapter;
    private String sha1;
    private long time;
    private String appid;
    private String appsecret;
    private List<Collections_Bean.DataBean> datas;
    private int i;
    private RelativeLayout relativeLayout;
    private LoadingDialog dialog;

    @Override
    public int intiLayout() {
        return R.layout.activity_collection;
    }

    @Override
    public void initView() {
        new TitleXML(CollectionActivity.this, "我的收藏", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        relativeLayout = findViewById(R.id.on_collection);
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
//显示Dialog
        dialog.show();
        presenter.getpost(Contacts.My_collections, headmap, map, Collections_Bean.class);
    }

    @Override
    public void success(final Object data) {
        if (data instanceof Collections_Bean) {
            dialog.close();
            Collections_Bean collections_bean = (Collections_Bean) data;
            datas = collections_bean.getData();
            if (datas.size() > 0) {
                collectionsAdapter = new CollectionsAdapter(datas, this);
                //添加布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerview.setLayoutManager(linearLayoutManager);
                recyclerview.setAdapter(collectionsAdapter);
                relativeLayout.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
            } else {
                relativeLayout.setVisibility(View.VISIBLE);
                recyclerview.setVisibility(View.GONE);
            }
            //点击取消 收藏
            collectionsAdapter.setOnItemdefaultClickListener(new CollectionsAdapter.OnItemdefaultClickListener() {
                @Override
                public void ondefaultClick(int position) {
                    map.put("vaid", datas.get(position).getId());
                    i = position;
                    presenter.getpost(Contacts.My_cancel_collections, headmap, map, Cancel_collectionsBean.class);
                }
            });

        } else if (data instanceof Cancel_collectionsBean) {
            Cancel_collectionsBean cancel_colledctions_bean = (Cancel_collectionsBean) data;
            String code = cancel_colledctions_bean.getCode();
            if (code.equals("200")) {
                datas.remove(i);
                collectionsAdapter.notifyItemRemoved(i);
                collectionsAdapter.notifyDataSetChanged();
                if (datas.size() <= 0) {
                    relativeLayout.setVisibility(View.VISIBLE);
                    recyclerview.setVisibility(View.GONE);
                }
            } else {
                ToastUtil.showShort(CollectionActivity.this, "取消失败");
            }
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
