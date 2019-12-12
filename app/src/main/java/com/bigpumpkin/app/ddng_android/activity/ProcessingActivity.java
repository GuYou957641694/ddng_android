package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.ProcessingAdapter;
import com.bigpumpkin.app.ddng_android.adapter.ProcessingShopAdapter;
import com.bigpumpkin.app.ddng_android.bean.AddShoppingBean;
import com.bigpumpkin.app.ddng_android.bean.ProcessingBean;
import com.bigpumpkin.app.ddng_android.bean.ProcessingShopBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.LoginUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.StatusBarsUtil;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
import com.bumptech.glide.Glide;
import com.hjq.toast.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProcessingActivity extends AppCompatActivity implements MyView, OnBannerListener {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.check1)
    CheckBox check1;
    @BindView(R.id.check2)
    CheckBox check2;
    @BindView(R.id.check3)
    CheckBox check3;
    @BindView(R.id.check4)
    CheckBox check4;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.relative)
    RelativeLayout relative;

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private HashMap<String, Object> addmap;
    private HashMap<String, Object> addheadmap;
    private List<ProcessingBean.DataBean.BannerBean> banner1;
    private List<String> images = new ArrayList<>();
    private String sha1, appid, appsecret;
    private long time;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);
        ButterKnife.bind(this);
        StatusBarsUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarsUtil.setTranslucentStatus(this);
        if (!StatusBarsUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarsUtil.setStatusBarColor(this, 0x55000000);
        }
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        addheadmap = new HashMap<>();
        addmap = new HashMap<>();
        dialog = new LoadingDialog(this, "加载中...");
        dialog.show();
        presenter.get(Contacts.Processing_indexs, headmap, map, ProcessingBean.class);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void success(Object data) {
        if (data instanceof ProcessingBean) {
            ProcessingBean processingBean = (ProcessingBean) data;
            banner1 = processingBean.getData().getBanner();
            List<ProcessingBean.DataBean.ArchiveslistBean> archiveslist = processingBean.getData().getArchiveslist();
            ProcessingAdapter processingAdapter = new ProcessingAdapter(this, archiveslist);
            recyclerview.setAdapter(processingAdapter);
            processingAdapter.setItemOnClick(new ProcessingAdapter.itemOnClick() {
                @Override
                public void OnClick(int item) {
                    processingAdapter.setItem(item);
                    //刷新视图
                    processingAdapter.notifyDataSetChanged();
                    map.clear();
                    map.put("type", archiveslist.get(item).getId());
                    map.put("types", 1);
                    presenter.get(Contacts.Processing_types, headmap, map, ProcessingShopBean.class);
                    check1.setChecked(true);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                }
            });
            banners();
            map.clear();
            map.put("type", archiveslist.get(0).getId());
            map.put("types", 1);
            relative.setVisibility(View.VISIBLE);
            dialog.close();
            presenter.get(Contacts.Processing_types, headmap, map, ProcessingShopBean.class);
        } else if (data instanceof ProcessingShopBean) {
            ProcessingShopBean processingShopBean = (ProcessingShopBean) data;
            List<ProcessingShopBean.DataBean> data1 = processingShopBean.getData();
            ProcessingShopAdapter processingShopAdapter = new ProcessingShopAdapter(this, data1);
            rv.setAdapter(processingShopAdapter);
            processingShopAdapter.setListener(new ProcessingShopAdapter.onListener() {
                @Override
                public void OnListener(int i) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", data1.get(i).getId());
                    IntentUtils.getIntents().Intent(ProcessingActivity.this, Spell_DetailsActivity.class, bundle);
                }
            });

            processingShopAdapter.AddsetListener(new ProcessingShopAdapter.onAddListener() {
                @Override
                public void OnAddListener(int i) {
                    if (!LoginUtil.getInstance().checkLoginStatus(ProcessingActivity.this)) {
                        return;
                    }
                    time = System.currentTimeMillis();
                    appid = SpzUtils.getString("appid");
                    appsecret = SpzUtils.getString("appsecret");
                    String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                    sha1 = EncryptUtils.getSHA(sha);
                    addmap.put("appid", appid);
                    addmap.put("appsecret", appsecret);
                    addmap.put("timestamp", time);
                    addmap.put("sign", sha1);
                    addmap.put("num", "1");
                    addmap.put("gg_id", data1.get(i).getGg_id());
                    presenter.getpost(Contacts.add_shopping_carts, addheadmap, addmap, AddShoppingBean.class);
                }
            });
        } else if (data instanceof AddShoppingBean) {
            AddShoppingBean addShoppingBean = (AddShoppingBean) data;
            String code = addShoppingBean.getCode();
            if (code.equals("200")) {
                ToastUtils.show("加入成功");
            }
        }
    }


    @Override
    public void error(String error) {

    }

    private void banners() {
        images.clear();
        for (int i = 0; i < banner1.size(); i++) {
            String pic = banner1.get(i).getPic();
            String substring = pic.substring(1);
            images.add(Urls.BASEURL + substring);
        }
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置轮播间隔时间
        banner.setDelayTime(2000);
        //设置指示器的位置，小点点，左中右。
        //.setEmptyImageRes(R.mipmap.no_banner) // banner为空时占位图
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        MyImageLoader myImageLoader = new MyImageLoader();
        banner.setImageLoader(myImageLoader);
        //设置图片集合
        banner.setImages(images)
                .setOnBannerListener(ProcessingActivity.this)
                .start();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void OnBannerClick(int position) {
        ToastUtil.showShort(ProcessingActivity.this, position + "");
    }

    @OnClick({R.id.check1, R.id.check2, R.id.check3, R.id.check4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check1:
                check1.setChecked(true);
                check2.setChecked(false);
                check3.setChecked(false);
                check4.setChecked(false);
                map.put("types", 1);
                presenter.get(Contacts.Processing_types, headmap, map, ProcessingShopBean.class);
                break;
            case R.id.check2:
                check1.setChecked(false);
                check2.setChecked(true);
                check3.setChecked(false);
                check4.setChecked(false);
                map.put("types", 2);
                presenter.get(Contacts.Processing_types, headmap, map, ProcessingShopBean.class);
                break;
            case R.id.check3:
                check1.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(true);
                check4.setChecked(false);
                map.put("types", 3);
                presenter.get(Contacts.Processing_types, headmap, map, ProcessingShopBean.class);
                break;
            case R.id.check4:
                check1.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(false);
                check4.setChecked(true);
                map.put("types", 4);
                presenter.get(Contacts.Processing_types, headmap, map, ProcessingShopBean.class);
                break;
        }
    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(ProcessingActivity.this)
                    .load(path)
                    .into(imageView);
        }
    }

}
