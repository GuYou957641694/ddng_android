package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.ExPandableListViewAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Footprint_Bean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
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
    private int groupposition;
    private int childposition;
    private List<Footprint_Bean.DataBean.ListBean> list;
    private LoadingDialog dialog;

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
    }

    @Override
    public void initData() {
        dialog = new LoadingDialog(this,"玩命加载中...");
        dialog.show();
        presenter.getpost(Contacts.My_Footprint, headmap, map, Footprint_Bean.class);
    }


    @Override
    public void success(Object data) {
        if (data instanceof Footprint_Bean) {
            dialog.close();
            Footprint_Bean footprint_bean = (Footprint_Bean) data;
            Footprint_Bean.DataBean data1 = footprint_bean.getData();
            if (data1.getList().size() > 0) {
                // 遍历所有group,将所有项设置成默认展开
                list = data1.getList();
                adapter = new ExPandableListViewAdapter(this, list);
                edpandablelistview.setAdapter(adapter);
                edpandablelistview.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
                edpandablelistview.setGroupIndicator(null);
                for (int i = 0; i < list.size(); i++) {
                    edpandablelistview.expandGroup(i);
                }
                edpandablelistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", list.get(groupPosition).getDetails().get(childPosition).getCp_id());
                        IntentUtils.getIntents().Intent(FootprintActivity.this, DetailsActivity.class, bundle);
                        return true;
                    }
                });
                adapter.setListener(new ExPandableListViewAdapter.onListener() {
                    @Override
                    public void OnListener(int groupPosition, int childPosition) {
                        map.put("id", list.get(groupPosition).getDetails().get(childPosition).getId());
                        presenter.getpost(Contacts.del_footprints, headmap, map, Zfb_Bean.class);
                    }
                });
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
        } else if (data instanceof Zfb_Bean) {
            Zfb_Bean zfb_bean = (Zfb_Bean) data;
            String code = zfb_bean.getCode();
            if (code.equals("200")) {
                ToastUtil.showShort(this, "成功");
                list.get(groupposition).getDetails().remove(childposition);
                if (list.get(groupposition).getDetails().size() <= 1) {
                    list.remove(groupposition);
                }
                adapter.notifyDataSetChanged();
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
