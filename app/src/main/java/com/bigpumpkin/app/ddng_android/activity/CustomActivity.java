package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapOptions;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.BaseFragmentAdapters;
import com.bigpumpkin.app.ddng_android.adapter.CustomAdapter;
import com.bigpumpkin.app.ddng_android.adapter.FreePollutionAdapter;
import com.bigpumpkin.app.ddng_android.adapter.RvSpell_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.SelectionAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Adopt_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.fragment.FragmentFruit;
import com.bigpumpkin.app.ddng_android.fragment.FragmentRare;
import com.bigpumpkin.app.ddng_android.fragment.Fragment_Hundred;
import com.bigpumpkin.app.ddng_android.fragment.Fragment_Plant;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.bigpumpkin.app.ddng_android.utils.Utils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.FlingNestedScrollView;
import com.bigpumpkin.app.ddng_android.weight.GlideRoundedCornersTransform;
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
import com.bigpumpkin.app.ddng_android.weight.MyViewPager;
import com.bigpumpkin.app.ddng_android.weight.ScaleTransitionPagerTitleView;
import com.bigpumpkin.app.ddng_android.weight.SpaceItemDecorations;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.yhy.gvp.widget.GridViewPager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//植物认养

public class CustomActivity extends BaseActivity implements MyView, LocationSource, AMapLocationListener {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.gridviewpager)
    GridViewPager gridviewpager;
    @BindView(R.id.indicator_container)
    MagicIndicator indicatorContainer;
    @BindView(R.id.rv_farmers)
    RecyclerView rvFarmers;
    @BindView(R.id.iv_free_php)
    ImageView ivFreePhp;
    @BindView(R.id.tv_free_name)
    TextView tvFreeName;
    @BindView(R.id.tv_free_title)
    TextView tvFreeTitle;
    @BindView(R.id.iv_free_two)
    ImageView ivFreeTwo;
    @BindView(R.id.tv_free_name_two)
    TextView tvFreeNameTwo;
    @BindView(R.id.tv_free_title_two)
    TextView tvFreeTitleTwo;
    @BindView(R.id.iv_free_there)
    ImageView ivFreeThere;
    @BindView(R.id.tv_free_name_there)
    TextView tvFreeNameThere;
    @BindView(R.id.tv_free_title_there)
    TextView tvFreeTitleThere;
    @BindView(R.id.rv_free_pollution)
    RecyclerView rvFreePollution;
    @BindView(R.id.rv_spell)
    RecyclerView rvSpell;
    @BindView(R.id.iv_tea_one)
    ImageView ivTeaOne;
    @BindView(R.id.tv_tea_name_one)
    TextView tvTeaNameOne;
    @BindView(R.id.tv_tea_title_one)
    TextView tvTeaTitleOne;
    @BindView(R.id.iv_tea_two)
    ImageView ivTeaTwo;
    @BindView(R.id.tv_tea_name_two)
    TextView tvTeaNameTwo;
    @BindView(R.id.tv_tea_title_two)
    TextView tvTeaTitleTwo;
    @BindView(R.id.iv_tea_there)
    ImageView ivTeaThere;
    @BindView(R.id.tv_tea_name_there)
    TextView tvTeaNameThere;
    @BindView(R.id.tv_tea_title_there)
    TextView tvTeaTitleThere;
    @BindView(R.id.iv_tea_frou)
    ImageView ivTeaFrou;
    @BindView(R.id.tv_tea_name_frou)
    TextView tvTeaNameFrou;
    @BindView(R.id.tv_tea_title_frou)
    TextView tvTeaTitleFrou;
    @BindView(R.id.iv_tea_five)
    ImageView ivTeaFive;
    @BindView(R.id.tv_tea_name_five)
    TextView tvTeaNameFive;
    @BindView(R.id.tv_tea_title_five)
    TextView tvTeaTitleFive;
    @BindView(R.id.iv_crops_one)
    ImageView ivCropsOne;
    @BindView(R.id.tv_crops_name_one)
    TextView tvCropsNameOne;
    @BindView(R.id.tv_crops_title_one)
    TextView tvCropsTitleOne;
    @BindView(R.id.iv_crops_two)
    ImageView ivCropsTwo;
    @BindView(R.id.tv_crops_name_two)
    TextView tvCropsNameTwo;
    @BindView(R.id.tv_crops_title_two)
    TextView tvCropsTitleTwo;
    @BindView(R.id.iv_crops_there)
    ImageView ivCropsThere;
    @BindView(R.id.tv_crops_name_there)
    TextView tvCropsNameThere;
    @BindView(R.id.tv_crops_title_there)
    TextView tvCropsTitleThere;
    @BindView(R.id.iv_crops_frou)
    ImageView ivCropsFrou;
    @BindView(R.id.tv_crops_name_frou)
    TextView tvCropsNameFrou;
    @BindView(R.id.tv_crops_title_frou)
    TextView tvCropsTitleFrou;
    MagicIndicator mMagicIndicator;
    MyViewPager mViewPager;
    FlingNestedScrollView scroll;
    @BindView(R.id.iv)
    LinearLayout iv;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.rl_layout)
    RelativeLayout rl_layout;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_all_pollution)
    ImageView ivAllPollution;
    @BindView(R.id.iv_all_tea)
    ImageView ivAllTea;
    @BindView(R.id.iv_all_crops)
    ImageView ivAllCrops;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private List<Adopt_Bean.DataBean.BannerBean> banner1;
    private List<Adopt_Bean.DataBean.JournalismlenBean> journalismlen;
    private List<Adopt_Bean.DataBean.AreaBean> area;
    private List<Adopt_Bean.DataBean.ZeroBean> zero;
    private List<Adopt_Bean.DataBean.PollutionBean> pollution;
    private List<Adopt_Bean.DataBean.SingleBean> single;
    private List<Adopt_Bean.DataBean.TeaBean> tea;
    private List<Adopt_Bean.DataBean.CropsBean> crops;
    private List<String> images = new ArrayList<>();
    private List<Fragment> mFragments;
    private CommonNavigator mCommonNavigator;
    String[] mTitles = new String[]{
            "推荐认养", "百年果树", "稀有果树", "甘肃瓜田"
    };
    private List<String> mTitleList = Arrays.asList(mTitles);
    private MapView mapView;
    private AMap aMap;
    //声明AMapLocationClient类对象，定位发起端
    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //声明mListener对象，定位监听器
    private LocationSource.OnLocationChangedListener mListener = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    MyLocationStyle myLocationStyle;
    private Bitmap bitmap;
    private LoadingDialog dialog;
    private SmartRefreshLayout refreshLayout;
    @Override
    public int intiLayout() {
        return R.layout.activity_custom;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        scroll = findViewById(R.id.scroll);
        mMagicIndicator = findViewById(R.id.magic_indicator);
        mViewPager = findViewById(R.id.viewpager);
        mapView = findViewById(R.id.mv_id);
        View vwid = findViewById(R.id.vwid);
        refreshLayout  = findViewById(R.id.refreshLayout);
        vwid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    IntentUtils.getIntents().Intent(CustomActivity.this,MapActivity.class,null);
            }
        });
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map);

        //吸顶
        initsrc();
        initMagicIndicator();
        initMaps();
        initRefreshLayout();


    }



    @Override
    public void initData() {
        dialog = new LoadingDialog(this,"加载中...");
        dialog.show();
        presenter.get(Contacts.renyang, headmap, map, Adopt_Bean.class);
    }


    @OnClick({R.id.iv_back, R.id.tv_strategy, R.id.tv_rules, R.id.iv_free_php, R.id.iv_free_two, R.id.iv_tea_one, R.id.iv_tea_two, R.id.iv_tea_there, R.id.iv_tea_frou, R.id.iv_tea_five, R.id.iv_crops_one, R.id.iv_crops_two, R.id.iv_crops_there, R.id.iv_crops_frou, R.id.iv_all_pollution, R.id.iv_all_tea, R.id.iv_all_crops})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_strategy:
                //攻略
                IntentUtils.getIntents().Intent(CustomActivity.this, StrategyActivity.class, null);
                break;
            case R.id.tv_rules:
                //规则
                IntentUtils.getIntents().Intent(CustomActivity.this, RulesActivity.class, null);
                break;
            case R.id.iv_free_php:

                break;
            case R.id.iv_free_two:

                break;
            case R.id.iv_tea_one:
                Bundle bundle = new Bundle();
                bundle.putString("id", tea.get(0).getLink());
                IntentUtils.getIntents().Intent(CustomActivity.this, Spell_DetailsActivity.class, bundle);
                break;
            case R.id.iv_tea_two:
                Bundle tea_two = new Bundle();
                tea_two.putString("id", tea.get(1).getLink());
                IntentUtils.getIntents().Intent(CustomActivity.this, Spell_DetailsActivity.class, tea_two);
                break;
            case R.id.iv_tea_there:
                Bundle tea_there = new Bundle();
                tea_there.putString("id", tea.get(2).getLink());
                IntentUtils.getIntents().Intent(CustomActivity.this, Spell_DetailsActivity.class, tea_there);
                break;
            case R.id.iv_tea_frou:
                Bundle tea_frou = new Bundle();
                tea_frou.putString("id", tea.get(3).getLink());
                IntentUtils.getIntents().Intent(CustomActivity.this, Spell_DetailsActivity.class, tea_frou);
                break;
            case R.id.iv_tea_five:
                Bundle tea_five = new Bundle();
                tea_five.putString("id", tea.get(4).getLink());
                IntentUtils.getIntents().Intent(CustomActivity.this, Spell_DetailsActivity.class, tea_five);
                break;
            case R.id.iv_crops_one:
                Bundle crops_one = new Bundle();
                crops_one.putString("id", crops.get(0).getLink());
                IntentUtils.getIntents().Intent(CustomActivity.this, Spell_DetailsActivity.class, crops_one);
                break;
            case R.id.iv_crops_two:
                Bundle crops_two = new Bundle();
                crops_two.putString("id", crops.get(1).getLink());
                IntentUtils.getIntents().Intent(CustomActivity.this, Spell_DetailsActivity.class, crops_two);
                break;
            case R.id.iv_crops_there:
                Bundle crops_there = new Bundle();
                crops_there.putString("id", crops.get(2).getLink());
                IntentUtils.getIntents().Intent(CustomActivity.this, Spell_DetailsActivity.class, crops_there);
                break;
            case R.id.iv_crops_frou:
                Bundle crops_frou = new Bundle();
                crops_frou.putString("id", crops.get(3).getLink());
                IntentUtils.getIntents().Intent(CustomActivity.this, Spell_DetailsActivity.class, crops_frou);
                break;
            case R.id.iv_all_pollution:
                IntentUtils.getIntents().Intent(CustomActivity.this, MoreActivity.class, null);
                break;
            case R.id.iv_all_tea:
                IntentUtils.getIntents().Intent(CustomActivity.this, MoreActivity.class, null);
                break;
            case R.id.iv_all_crops:
                IntentUtils.getIntents().Intent(CustomActivity.this, MoreActivity.class, null);
                break;
        }
    }


    @Override
    public void success(Object data) {
        if (data instanceof Adopt_Bean) {
            Adopt_Bean adopt_bean = (Adopt_Bean) data;
            Adopt_Bean.DataBean data1 = adopt_bean.getData();
            banner1 = data1.getBanner();
            journalismlen = data1.getJournalismlen();
            area = data1.getArea();
            zero = data1.getZero();
            pollution = data1.getPollution();
            single = data1.getSingle();
            tea = data1.getTea();
            crops = data1.getCrops();
            //banner图
            initBanner();
            //分类
            initClassiFication();
            //产品甄选
            initSelection();
            //0元领养植物
            initFreePlants();
            //0污染
            initFreePollution();
            //拼单
            initSpell();
            //茶树认养
            initTea();
            //农作物认养
            initCrops();
            //分类
            setupViewPager();
            dialog.close();
            scroll.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void error(String error) {

    }


    private void setupViewPager() {
        mFragments = new ArrayList<>();
        mFragments.add(new Fragment_Plant());//推荐认养
        mFragments.add(new Fragment_Hundred());//百年
        mFragments.add(new FragmentFruit());//稀有
        mFragments.add(new FragmentRare());//甘肃

        BaseFragmentAdapters adapter =
                new BaseFragmentAdapters(getSupportFragmentManager(), mFragments);

        mViewPager.setAdapter(adapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mMagicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mMagicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mMagicIndicator.onPageScrollStateChanged(state);

            }
        });
    }

    private void initCrops() {
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + crops.get(0).getPic(), ivCropsOne);
        tvCropsNameOne.setText(crops.get(0).getTitle());
        tvCropsTitleOne.setText(crops.get(0).getDesc());
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + crops.get(1).getPic(), ivCropsTwo);
        tvCropsNameTwo.setText(crops.get(1).getTitle());
        tvCropsTitleTwo.setText(crops.get(1).getDesc());
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + crops.get(2).getPic(), ivCropsThere);
        tvCropsNameThere.setText(crops.get(2).getTitle());
        tvCropsTitleThere.setText(crops.get(2).getDesc());
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + crops.get(3).getPic(), ivCropsFrou);
        tvCropsNameFrou.setText(crops.get(3).getTitle());
        tvCropsTitleFrou.setText(crops.get(3).getDesc());
    }

    private void initTea() {
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + tea.get(0).getPic(), ivTeaOne);
        tvTeaNameOne.setText(tea.get(0).getTitle());
        tvTeaTitleOne.setText(tea.get(0).getDesc());
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + tea.get(1).getPic(), ivTeaTwo);
        tvTeaNameTwo.setText(tea.get(1).getTitle());
        tvTeaTitleTwo.setText(tea.get(1).getDesc());
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + tea.get(2).getPic(), ivTeaThere);
        tvTeaNameThere.setText(tea.get(2).getTitle());
        tvTeaTitleThere.setText(tea.get(2).getDesc());
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + tea.get(3).getPic(), ivTeaFrou);
        tvTeaNameFrou.setText(tea.get(3).getTitle());
        tvTeaTitleFrou.setText(tea.get(3).getDesc());
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + tea.get(4).getPic(), ivTeaFive);
        tvTeaNameFive.setText(tea.get(4).getTitle());
        tvTeaTitleFive.setText(tea.get(4).getDesc());
    }

    private void initSpell() {
        RvSpell_Adapter rvSpell_adapter = new RvSpell_Adapter(single, this);
        rvSpell.setAdapter(rvSpell_adapter);
        LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(this);
        linearLayoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSpell.setLayoutManager(linearLayoutManagers);
        rvSpell_adapter.setListener(new RvSpell_Adapter.onListener() {
            @Override
            public void OnListener(int id) {
                Bundle bundle = new Bundle();
                bundle.putString("id", single.get(id).getId());
                IntentUtils.getIntents().Intent(CustomActivity.this, Spell_DetailsActivity.class, bundle);
            }
        });
    }

    private void initFreePollution() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvFreePollution.setLayoutManager(gridLayoutManager);
        rvFreePollution.addItemDecoration(new SpaceItemDecorations(15));
        FreePollutionAdapter freePollutionAdapter = new FreePollutionAdapter(pollution, this);
        rvFreePollution.setAdapter(freePollutionAdapter);
        freePollutionAdapter.setListener(new FreePollutionAdapter.onListener() {
            @Override
            public void OnListener(int id) {
                Bundle bundle = new Bundle();
                bundle.putString("id", pollution.get(id).getLink());
                IntentUtils.getIntents().Intent(CustomActivity.this, Spell_DetailsActivity.class, bundle);
            }
        });
    }

    private void initFreePlants() {
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + zero.get(0).getPic(), ivFreePhp);
        tvFreeName.setText(zero.get(0).getTitle());
        tvFreeTitle.setText(zero.get(0).getDesc());
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + zero.get(1).getPic(), ivFreeTwo);
        tvFreeNameTwo.setText(zero.get(1).getTitle());
        tvFreeTitleTwo.setText(zero.get(1).getDesc());
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + zero.get(2).getPic(), ivFreeThere);
        tvFreeNameThere.setText(zero.get(2).getTitle());
        tvFreeTitleThere.setText(zero.get(2).getDesc());
    }

    private void initSelection() {
        SelectionAdapter selectionAdapter = new SelectionAdapter(area, this);
        rvFarmers.setAdapter(selectionAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvFarmers.setLayoutManager(linearLayoutManager);
        selectionAdapter.setListener(new SelectionAdapter.onListener() {
            @Override
            public void OnListener(int id) {
                Bundle bundle = new Bundle();
                bundle.putString("id", area.get(id).getId());
                bundle.putString("title", area.get(id).getTitle());
                bundle.putString("link", area.get(id).getLink());
                bundle.putString("type", "1");
                IntentUtils.getIntents().Intent(CustomActivity.this, Custom_SelectionActivity.class, bundle);
            }
        });
    }

    private void initClassiFication() {
        CustomAdapter customAdapter = new CustomAdapter(R.layout.home_modul, journalismlen, CustomActivity.this);
        gridviewpager.setGVPAdapter(customAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        setIndicator(commonNavigator, journalismlen, indicatorContainer, gridviewpager);
    }

    private void initBanner() {
        images.clear();
        for (int i = 0; i < banner1.size(); i++) {
            String pic = banner1.get(i).getPic();
            String substring = pic.substring(1);
            images.add(Urls.BASEURL + substring);
        }
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置指示器的位置，小点点，左中右。
        //.setEmptyImageRes(R.mipmap.no_banner) // banner为空时占位图
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        MyImageLoader myImageLoader = new MyImageLoader();
        banner.setImageLoader(myImageLoader);
        //设置图片集合
        banner.setImages(images)
                .start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("link",banner1.get(position).getLink());
                IntentUtils.getIntents().Intent(CustomActivity.this,BannerActivity.class,bundle);
            }
        });
    }




    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            // 圆角处理
            RequestOptions myOptions = new RequestOptions().optionalTransform
                    (new GlideRoundedCornersTransform(Utils.dip2px(CustomActivity.this, 6f)
                            , GlideRoundedCornersTransform.CornerType.ALL));
            Glide.with(CustomActivity.this)
                    .load(path)
                    .apply(myOptions)
                    .into(imageView);
        }
    }

    private void setIndicator(CommonNavigator commonNavigator,
                              final List<Adopt_Bean.DataBean.JournalismlenBean> spf, MagicIndicator
                                      indicatorContainer, GridViewPager gridViewpager) {
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                int num = spf.size() / 4;
                if (spf.size() % 4 > 0) {
                    num++;
                }
                //返回分类指示器的数量
                return spf == null ? 0 : num;
            }

            @Override
            public IPagerTitleView getTitleView(Context mContext, final int i) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(mContext);
                View view = View.inflate(mContext, R.layout.single_image_layout, null);
                final ImageView iv_image = view.findViewById(R.id.img_indicator_show);
                iv_image.setImageResource(R.drawable.rg_false_bg);

                //指示器引入外部布局，可知指示器内容可根据需求设置，多样化
                commonPagerTitleView.setContentView(view);

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int i, int i1) {
                        //指示器选中样式
                        iv_image.setImageResource(R.drawable.rg_true_bg);
                    }

                    @Override
                    public void onDeselected(int i, int i1) {
                        //指示器未选中样式
                        iv_image.setImageResource(R.drawable.rg_false_bg);
                    }

                    @Override
                    public void onLeave(int i, int i1, float v, boolean b) {
                    }

                    @Override
                    public void onEnter(int i, int i1, float v, boolean b) {

                    }
                });
                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        indicatorContainer.setNavigator(commonNavigator);
        ViewPagerHelper.bind(indicatorContainer, gridViewpager);
    }

    private void initsrc() {
        scroll.setOnScrollChangeListener(new FlingNestedScrollView.ScrollChangeListener() {
            @Override
            public void onScrollChange(FlingNestedScrollView fnslv, int l, int t, int oldl, int oldt) {
                //吸顶
                if (t > iv.getHeight() && mMagicIndicator.getParent() == layout) {
                    layout.removeView(mMagicIndicator);
                    rl_layout.addView(mMagicIndicator);
                } else if (t < iv.getHeight() && mMagicIndicator.getParent() == rl_layout) {
                    rl_layout.removeView(mMagicIndicator);
                    layout.addView(mMagicIndicator);
                }
            }
        });
    }

    private void initMagicIndicator() {
        mMagicIndicator.setBackgroundColor(getResources().getColor(R.color.home_bg));
        mCommonNavigator = new CommonNavigator(this);
        mCommonNavigator.setAdjustMode(true);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleList == null ? 0 : mTitleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mTitleList.get(index));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setNormalColor(Color.BLACK);
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.custos_selected_title));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.custos_title));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 30));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(getResources().getColor(R.color.custos_selected_title));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(mCommonNavigator);
        LinearLayout titleContainer = mCommonNavigator.getTitleContainer();
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(this, 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.wisdoms_shape));
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);
    }

    private void initMaps() {
        myLocationStyle = new MyLocationStyle();
        if (aMap == null) {
            aMap = mapView.getMap();
            //设置显示定位按钮 并且可以点击
            aMap.setLocationSource(this);//设置了定位的监听

            UiSettings settings = aMap.getUiSettings();
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.title("西安市");
            markerOption.snippet("气泡的文字");
            markerOption.draggable(true);

            settings.setZoomControlsEnabled(false);
            //是否允许收拾手势缩放地图
            settings.setZoomGesturesEnabled(false);
            //设置双击地图放大在地图中心位置放大，false则是在点击位置放大
            settings.setZoomInByScreenCenter(false);
            settings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_RIGHT);
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
            myLocationStyle.anchor(0.5f, 0.5f);
            myLocationStyle.strokeColor(Color.GREEN);
            myLocationStyle.strokeWidth(0);
            myLocationStyle.radiusFillColor(Color.TRANSPARENT);
            myLocationStyle.strokeColor(Color.TRANSPARENT);
            //为Map添加定位
            aMap.setMyLocationStyle(myLocationStyle);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
            aMap.addMarker(markerOption);
        }
        //开始定位
        location();
    }

    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
                    mListener.onLocationChanged(aMapLocation);
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;


                }

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }

    }
    private Marker addMarker(LatLng point) {
        Bitmap bMap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.map);
        BitmapDescriptor des = BitmapDescriptorFactory.fromBitmap(bMap);
        Marker marker = aMap.addMarker(new MarkerOptions().position(point).icon(des)
                .anchor(0.5f, 0.5f));
        return marker;
    }
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }
    @Override
    protected void onPause() {
        super.onPause();
        //暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //重新绘制加载地图
        mapView.onResume();
    }

    private void initRefreshLayout() {
        scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (refreshLayout != null) {
                    refreshLayout.setEnabled(scroll.getScrollY() == 0);
                }
            }
        });
        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
    }

}
