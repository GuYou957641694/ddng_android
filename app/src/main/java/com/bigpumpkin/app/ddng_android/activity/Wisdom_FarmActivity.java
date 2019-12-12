package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Dimensions_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.Hot_FramAdapter;
import com.bigpumpkin.app.ddng_android.adapter.RlPleasure_Adapter;
import com.bigpumpkin.app.ddng_android.bean.WisdomBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.StatusBarsUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.BannerImage;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Wisdom_FarmActivity extends AppCompatActivity implements MyView, OnBannerListener {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.rl_farm_hot)
    RecyclerView rlFarmHot;
    @BindView(R.id.rl_pleasure)
    RecyclerView rlPleasure;
    @BindView(R.id.iv_advertising)
    ImageView ivAdvertising;
    private List<String> images = new ArrayList();
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private List<WisdomBean.DataBean.BannerBean> banner1;
    private List<WisdomBean.DataBean.DimensionBean> dimension;
    private List<WisdomBean.DataBean.Essay1Bean> essay1;
    private List<WisdomBean.DataBean.Essay3Bean> essay3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisdom__farm);
        ButterKnife.bind(this);
        StatusBarsUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarsUtil.setTranslucentStatus(this);
        if (!StatusBarsUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarsUtil.setStatusBarColor(this, 0x55000000);
        }
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        presenter.get(Contacts.Wisdomfarm_indexs, headmap, map, WisdomBean.class);

    }

    @Override
    public void success(Object data) {
        if (data instanceof WisdomBean) {
            WisdomBean wisdomBean = (WisdomBean) data;
            banner1 = wisdomBean.getData().getBanner();
            dimension = wisdomBean.getData().getDimension();
            essay1 = wisdomBean.getData().getEssay1();
            List<WisdomBean.DataBean.Essay2Bean> essay2 = wisdomBean.getData().getEssay2();
            essay3 = wisdomBean.getData().getEssay3();
            banners();
            dimensions();
            //网红
            hotfram();
            //娱乐游玩
            entertainment();
            Glide.with(this).load(Urls.BASEURL + wisdomBean.getData().getPublicity().getPic()).into(ivAdvertising);
        }
    }

    private void entertainment() {
        RlPleasure_Adapter rlPleasure_adapter = new RlPleasure_Adapter(essay3, this);
        rlPleasure.setAdapter(rlPleasure_adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlPleasure.setLayoutManager(linearLayoutManager);
    }


    private void hotfram() {
        Hot_FramAdapter hot_framAdapter = new Hot_FramAdapter(essay1, this);
        rlFarmHot.setAdapter(hot_framAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlFarmHot.setLayoutManager(linearLayoutManager);
    }

    private void dimensions() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        Dimensions_Adapter dimensions_adapter = new Dimensions_Adapter(dimension, this);
        recyclerview.setAdapter(dimensions_adapter);
        recyclerview.setLayoutManager(linearLayoutManager);
    }

    private void banners() {
        for (int i = 0; i < banner1.size(); i++) {
            images.add(Urls.BASEURL + banner1.get(i).getPic());
        }
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置指示器的位置，小点点，左中右。
        //.setEmptyImageRes(R.mipmap.no_banner) // banner为空时占位图
        //设置图片加载器
        banner.setImageLoader(new MyImageLoader());
        //设置图片集合
        banner.setImages(images)
                .setOnBannerListener(Wisdom_FarmActivity.this)
                .start();
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void OnBannerClick(int position) {

    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            Glide.with(Wisdom_FarmActivity.this)
                    .load(path)
                    .into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            //自己自定义的具有图片加载功能的ImageView
            BannerImage arcImageView = new BannerImage(context);
            return arcImageView;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        banner.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
