package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Custom_ClassificationAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Custom_ClassificationBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.EndlessRecyclerOnScrollListener;
import com.bigpumpkin.app.ddng_android.weight.SpaceItemDecorations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Custom_classificationActivity extends BaseActivity implements MyView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    TextView tvTitle;
    RecyclerView recyclerview;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String id;
    private Custom_ClassificationAdapter custom_classificationAdapter;
    private int page = 1;
    private List<Custom_ClassificationBean.DataBean> data1;
    private List<Custom_ClassificationBean.DataBean> all;
    private boolean type = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_custom_classification;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        tvTitle = findViewById(R.id.tv_title);
        recyclerview = findViewById(R.id.recyclerview);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        all = new ArrayList<>();
        id = getIntent().getStringExtra("id");
        if (id.equals("1")) {
            tvTitle.setText("定制认养");
        } else if (id.equals("2")) {
            tvTitle.setText("拼单认养");
        } else if (id.equals("3")) {
            tvTitle.setText("团队认养");
        } else if (id.equals("4")) {
            tvTitle.setText("给娃认养");
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.addItemDecoration(new SpaceItemDecorations(25));
    }

    @Override
    public void initData() {
        map.put("id", id);
        map.put("page", page);
        presenter.get(Contacts.Vegetation_dimensionss, headmap, map, Custom_ClassificationBean.class);
        recyclerview.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                map.clear();
                ++page;
                map.put("id", id);
                map.put("page", page);
                presenter.get(Contacts.Vegetation_dimensionss, headmap, map, Custom_ClassificationBean.class);
            }
        });
    }

    @Override
    public void success(Object data) {
        if (data instanceof Custom_ClassificationBean) {
            Custom_ClassificationBean custom_classificationBean = (Custom_ClassificationBean) data;
            data1 = custom_classificationBean.getData();
            all.clear();
            all.addAll(data1);
            if (type == false) {
                custom_classificationAdapter = new Custom_ClassificationAdapter(this, data1);
                recyclerview.setAdapter(custom_classificationAdapter);
            } else {
                custom_classificationAdapter.updateData(all);
            }
            type = true;
            custom_classificationAdapter.setOnItemClickListener(new Custom_ClassificationAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    String id1 = data1.get(position).getId();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id1);
                    //跳转详情页
                    IntentUtils.getIntents().Intent(Custom_classificationActivity.this, Spell_DetailsActivity.class, bundle);
                }
            });
        }
    }

    @Override
    public void error(String error) {

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

}
