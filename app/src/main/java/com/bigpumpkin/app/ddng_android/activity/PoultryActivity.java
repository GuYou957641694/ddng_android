package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.BaseFragmentAdapters;
import com.bigpumpkin.app.ddng_android.adapter.EggAdapetr;
import com.bigpumpkin.app.ddng_android.adapter.PoultryaAdapter;
import com.bigpumpkin.app.ddng_android.adapter.RequestAdapter;
import com.bigpumpkin.app.ddng_android.adapter.Rv_FarmingAdapter;
import com.bigpumpkin.app.ddng_android.adapter.SpaceItemDecoration;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Fresh_Fragment;
import com.bigpumpkin.app.ddng_android.bean.PoultryBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.fragment.Meat_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Products_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Recommended_Fragment;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.bigpumpkin.app.ddng_android.utils.Utils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.ColorFlipPagerTitleView;
import com.bigpumpkin.app.ddng_android.weight.FlingNestedScrollView;
import com.bigpumpkin.app.ddng_android.weight.GlideRoundedCornersTransform;
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
import com.bigpumpkin.app.ddng_android.weight.MyViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yhy.gvp.widget.GridViewPager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PoultryActivity extends BaseActivity implements MyView, View.OnClickListener {

    MyViewPager mViewPager;
    List<Fragment> mFragments;

    String[] mTitles = new String[]{
            "推荐领养", "我要吃肉", "肉类制品", "鲜蛋类"
    };
    private List<String> mTitleList = Arrays.asList(mTitles);
    //private TabLayout mTabLayout;
    private FlingNestedScrollView scroll;
    private LinearLayout layout;
    private RelativeLayout rl_layout;
    private LinearLayout iv;
    private SmartRefreshLayout refreshLayout;
    private MagicIndicator mMagicIndicator;
    private CommonNavigator mCommonNavigator;
    private GridViewPager gridViewPager;
    private MagicIndicator indicatorcontainer;
    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private List<PoultryBean.DataBean.SpfBean> spf;
    private ImageView iv_back, iv_milk;
    private RecyclerView rv_farming, rv_egg, rv_features;
    private ImageView advertising, iv_farming_more;
    private List<String> images = new ArrayList<>();
    private List<PoultryBean.DataBean.BannerBean> banner;
    private Banner banner1;
    private List<PoultryBean.DataBean.PoultryIndexBean> poultry_index;
    private List<PoultryBean.DataBean.FarmerListBean> farmer_list;
    private List<PoultryBean.DataBean.PoultryIndex2Bean> poultry_index2;
    private LoadingDialog dialog;
    private TextView tv_strategy, tv_rules;

    @Override
    public int intiLayout() {
        return R.layout.activity_poultry;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        iv_milk = findViewById(R.id.iv_milk);
        mViewPager = findViewById(R.id.viewpager);
        refreshLayout = findViewById(R.id.refreshLayout);
        banner1 = findViewById(R.id.banner);
        mMagicIndicator = findViewById(R.id.magic_indicator);
        layout = (LinearLayout) findViewById(R.id.layout);
        rl_layout = (RelativeLayout) findViewById(R.id.rl_layout);
        scroll = findViewById(R.id.scroll);
        iv = findViewById(R.id.iv);
        gridViewPager = findViewById(R.id.gridviewpager);
        indicatorcontainer = findViewById(R.id.indicator_container);
        rv_farming = findViewById(R.id.rv_farming);
        advertising = findViewById(R.id.advertising);
        rv_egg = findViewById(R.id.rv_egg);
        rv_features = findViewById(R.id.rv_features);
        iv_back = findViewById(R.id.iv_back);
        iv_farming_more = findViewById(R.id.iv_farming_more);
        tv_strategy = findViewById(R.id.tv_strategy);
        tv_rules = findViewById(R.id.tv_rules);
        tv_strategy.setOnClickListener(this);
        tv_rules.setOnClickListener(this);
        iv_milk.setOnClickListener(this);
        advertising.setOnClickListener(this);
        iv_farming_more.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(true);
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
        initMagicIndicator();


    }

    @Override
    public void initData() {
        dialog = new LoadingDialog(this, "加载中...");
        dialog.show();
        presenter.get(Contacts.Poultry_indexs, headmap, map, PoultryBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                //返回
                finish();
            case R.id.tv_strategy:
                //攻略
                IntentUtils.getIntents().Intent(this, PoultryStrategyActivity.class, null);
                break;
            case R.id.tv_rules:
                //规则
                IntentUtils.getIntents().Intent(this, PoultryRulesActivity.class, null);
                break;
            case R.id.iv_farming_more:
                //果农说1植物认养（果农说） 2家禽认养 4当季水果（种果人说） 5公益放生（发起人说） 6生产者说
                Bundle bundle = new Bundle();
                bundle.putString("id", "2");
                bundle.putString("name", "养殖者说");
                IntentUtils.getIntents().Intent(PoultryActivity.this, GrowerActivity.class, bundle);
                break;
            case R.id.advertising:
                //广告页
                IntentUtils.getIntents().Intent(PoultryActivity.this, AdvertisingActivity.class, null);
                break;
            case R.id.iv_milk:
                //奶制品更多
                IntentUtils.getIntents().Intent(PoultryActivity.this, GeneralMoreActivity.class, null);
                break;
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof PoultryBean) {
            PoultryBean poultryBean = (PoultryBean) data;
            banner = poultryBean.getData().getBanner();
            spf = poultryBean.getData().getSpf();
            poultry_index = poultryBean.getData().getPoultry_index();
            farmer_list = poultryBean.getData().getFarmer_list();
            PoultryBean.DataBean.PublicityBean publicity = poultryBean.getData().getPublicity();
            poultry_index2 = poultryBean.getData().getPoultry_index2();
            //轮播图
            initBanner();
            //分类
            PoultryaAdapter poultryaAdapter = new PoultryaAdapter(R.layout.home_modul, spf, PoultryActivity.this);
            gridViewPager.setGVPAdapter(poultryaAdapter);
            poultryaAdapter.setListener(new PoultryaAdapter.onListener() {
                @Override
                public void OnListener(int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", spf.get(position).getId());
                    bundle.putSerializable("bean", (Serializable) spf);
                    IntentUtils.getIntents().Intent(PoultryActivity.this, AdoptActivity.class, bundle);
                }
            });
            CommonNavigator commonNavigator = new CommonNavigator(this);
            setIndicator(commonNavigator, spf, indicatorcontainer, gridViewPager);
            //特色养殖
            request();
            //养殖者说
            rvfarming();
            //广告
            GlideUtils.loadRoundCircleImagetwo(PoultryActivity.this, Urls.BASEURL + publicity.getPic(), advertising);
            // 奶制品蛋类
            iegg();
            setupViewPager();
            dialog.close();
            scroll.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void error(String error) {

    }

    /**
     * 奶制列表页
     */
    private void iegg() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv_egg.setLayoutManager(gridLayoutManager);
        rv_egg.addItemDecoration(new SpaceItemDecoration(15));
        EggAdapetr eggAdapetr = new EggAdapetr(this, poultry_index2);
        rv_egg.setAdapter(eggAdapetr);
        eggAdapetr.setListener(new EggAdapetr.onListener() {
            @Override
            public void OnListener(int i) {
                Bundle bundle = new Bundle();
                bundle.putString("id", poultry_index2.get(i).getLink());
                IntentUtils.getIntents().Intent(PoultryActivity.this, Spell_DetailsActivity.class, bundle);
            }
        });
    }

    /**
     * 养殖说
     */
    private void rvfarming() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv_farming.setLayoutManager(gridLayoutManager);
        rv_farming.addItemDecoration(new SpaceItemDecoration(15));
        Rv_FarmingAdapter rv_farmingAdapter = new Rv_FarmingAdapter(farmer_list, this);
        rv_farming.setAdapter(rv_farmingAdapter);
        rv_farmingAdapter.setListener(new Rv_FarmingAdapter.onListener() {
            @Override
            public void OnListener(int i) {
                Bundle bundle = new Bundle();
                bundle.putString("id", farmer_list.get(i).getId());
                IntentUtils.getIntents().Intent(PoultryActivity.this, VideoActivity.class, bundle);
            }
        });
    }

    /**
     * 特色养殖
     */
    private void request() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv_features.setLayoutManager(gridLayoutManager);
        rv_features.addItemDecoration(new SpaceItemDecoration(15));
        RequestAdapter requestAdapter = new RequestAdapter(this, poultry_index);
        rv_features.setAdapter(requestAdapter);
    }

    /**
     * 轮播图
     */
    private void initBanner() {
        images.clear();
        for (int i = 0; i < banner.size(); i++) {
            String pic = banner.get(i).getPic();
            String substring = pic.substring(1);
            images.add(Urls.BASEURL + substring);
        }
        //设置是否为自动轮播，默认是“是”。
        banner1.isAutoPlay(true);
        //设置轮播间隔时间
        banner1.setDelayTime(2000);
        //设置指示器的位置，小点点，左中右。
        //.setEmptyImageRes(R.mipmap.no_banner) // banner为空时占位图
        banner1.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载器
        MyImageLoader myImageLoader = new MyImageLoader();
        banner1.setImageLoader(myImageLoader);
        //设置图片集合
        banner1.setImages(images)
                .start();
    }


    private void setupViewPager() {
        mFragments = new ArrayList<>();
        mFragments.add(new Recommended_Fragment());
        mFragments.add(new Meat_Fragment());
        mFragments.add(new Products_Fragment());
        mFragments.add(new Fresh_Fragment());

        // 第二步：为ViewPager设置适配器
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

    //设置选择器
    private void setIndicator(CommonNavigator commonNavigator,
                              final List<PoultryBean.DataBean.SpfBean> spf, MagicIndicator
                                      indicatorContainer, GridViewPager gridViewpager) {
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                int num = spf.size() / 6;
                if (spf.size() % 6 > 0) {
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

    private void initMagicIndicator() {
        mMagicIndicator.setBackgroundColor(getResources().getColor(R.color.poultry_bgs));
        mCommonNavigator = new CommonNavigator(this);
        mCommonNavigator.setAdjustMode(true);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleList == null ? 0 : mTitleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mTitleList.get(index));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setNormalColor(Color.BLACK);
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.white));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.poultry_sele_fales));
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
                indicator.setColors(getResources().getColor(R.color.white));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(mCommonNavigator);
        LinearLayout titleContainer = mCommonNavigator.getTitleContainer();
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(this, 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.poulty_shape));
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            // 圆角处理
            RequestOptions myOptions = new RequestOptions().optionalTransform
                    (new GlideRoundedCornersTransform(Utils.dip2px(PoultryActivity.this, 6f)
                            , GlideRoundedCornersTransform.CornerType.ALL));
            Glide.with(PoultryActivity.this)
                    .load(path)
                    .apply(myOptions)
                    .into(imageView);
        }
    }
}
