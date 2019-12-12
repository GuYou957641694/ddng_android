package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Dimensions_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.ExamplePagerAdapter;
import com.bigpumpkin.app.ddng_android.adapter.Hot_FramAdapter;
import com.bigpumpkin.app.ddng_android.adapter.TeaAdapter;
import com.bigpumpkin.app.ddng_android.adapter.ntaminatedFarmAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.WisdomBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.fragment.WisdomsFragmnet;
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
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Wisdoms_Activity extends BaseActivity implements MyView {


    @BindView(R.id.banner)
    Banner banner;
    FlingNestedScrollView scroll;
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.rl_farm_hot)
    RecyclerView rlFarmHot;
    @BindView(R.id.no_pollution)
    RecyclerView noPollution;
    @BindView(R.id.rv_play)
    RecyclerView rvPlay;
    @BindView(R.id.tv_one)
    ImageView tvOne;
    @BindView(R.id.tv_two)
    ImageView tvTwo;
    @BindView(R.id.tv_there)
    ImageView tvThere;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_frou)
    ImageView tvFrou;
    @BindView(R.id.tv_five)
    ImageView tvFive;
    @BindView(R.id.bn_two)
    Banner bnTwo;
    @BindView(R.id.tv_processing)
    ImageView tvProcessing;
    @BindView(R.id.tv_processingone)
    ImageView tvProcessingone;
    @BindView(R.id.tv_processingtwo)
    ImageView tvProcessingtwo;
    @BindView(R.id.iv_zerollution)
    ImageView iv_zerollution;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.iv_processing)
    ImageView ivProcessing;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.viewpager)
    MyViewPager viewpager;
    @BindView(R.id.rl_layout)
    RelativeLayout rl_layout;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.re)
    RelativeLayout re;
    @BindView(R.id.iv_clingy_bear)
    ImageView ivClingyBear;
    @BindView(R.id.iv_farm_house)
    ImageView ivFarmHouse;
    @BindView(R.id.iv_look_for_garden)
    ImageView ivLookForGarden;
    @BindView(R.id.iv_me_speak)
    ImageView ivMeSpeak;
    @BindView(R.id.iv_map)
    ImageView ivMap;
    @BindView(R.id.iv_near)
    ImageView ivNear;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private List<WisdomBean.DataBean.BannerBean> banner1;
    private List<String> images = new ArrayList();
    private List<String> image = new ArrayList();
    private List<WisdomBean.DataBean.DimensionBean> dimension;
    private List<WisdomBean.DataBean.Essay1Bean> essay1;
    private List<WisdomBean.DataBean.Essay2Bean> essay2;
    private List<WisdomBean.DataBean.Essay3Bean> essay3;
    private List<WisdomBean.DataBean.Essay4Bean> essay4;
    private List<WisdomBean.DataBean.BannersBean> banners;
    private List<WisdomBean.DataBean.ProcessingBean> processing;
    private CommonNavigator mCommonNavigator;
    private List<WisdomBean.DataBean.DiquBean> diqu;
    // private List<String> mTitleList = new ArrayList<>();
    private static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH", "JELLY_BEAN", "KITKAT", "LOLLIPOP", "M", "NOUGAT"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private ExamplePagerAdapter mExamplePagerAdapter = new ExamplePagerAdapter(mDataList);
    private LoadingDialog dialog;

    @Override
    public int intiLayout() {
        return R.layout.activity_wisdoms_;
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        scroll = findViewById(R.id.scroll);
        refreshLayout = findViewById(R.id.refreshLayout);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        initRefreshLayout();
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        dialog = new LoadingDialog(this, "加载中...");
        dialog.show();
        presenter.get(Contacts.Wisdomfarm_indexs, headmap, map, WisdomBean.class);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_one, R.id.tv_two, R.id.tv_there, R.id.tv_frou, R.id.tv_five, R.id.iv_back, R.id.tv_processing, R.id.tv_processingone, R.id.iv_clingy_bear, R.id.iv_farm_house, R.id.iv_look_for_garden, R.id.iv_me_speak, R.id.iv_map, R.id.iv_near, R.id.tv_processingtwo, R.id.iv_processing, R.id.iv_zerollution, R.id.iv_play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_one:
                Bundle bundle = new Bundle();
                bundle.putString("id", essay3.get(0).getId());
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmActivity.class, bundle);
                break;
            case R.id.tv_two:
                Bundle bundle1 = new Bundle();
                bundle1.putString("id", essay3.get(1).getId());
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmActivity.class, bundle1);
                break;
            case R.id.tv_there:
                Bundle bundle2 = new Bundle();
                bundle2.putString("id", essay3.get(2).getId());
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmActivity.class, bundle2);
                break;
            case R.id.tv_frou:
                Bundle bundle3 = new Bundle();
                bundle3.putString("id", essay3.get(3).getId());
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmActivity.class, bundle3);
                break;
            case R.id.tv_five:
                Bundle bundle4 = new Bundle();
                bundle4.putString("id", essay3.get(4).getId());
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmActivity.class, bundle4);
                break;
            case R.id.tv_processing:
                Bundle bundle5 = new Bundle();
                bundle5.putString("id", processing.get(0).getId());
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmActivity.class, bundle5);
                break;
            case R.id.tv_processingone:
                Bundle bundle6 = new Bundle();
                bundle6.putString("id", processing.get(1).getId());
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmActivity.class, bundle6);
                break;
            case R.id.tv_processingtwo:
                Bundle bundle7 = new Bundle();
                bundle7.putString("id", processing.get(2).getId());
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmActivity.class, bundle7);
                break;
            case R.id.iv_zerollution:
                Bundle zerollution = new Bundle();
                zerollution.putString("id", "19");
                zerollution.putString("name", "0污染区");
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmTypeActivity.class, zerollution);
                break;
            case R.id.iv_play:
                Bundle play = new Bundle();
                play.putString("id", "18");
                play.putString("name", "娱乐游玩");
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmTypeActivity.class, play);
                break;
            case R.id.iv_processing:
             /*   Bundle processing = new Bundle();
                processing.putString("id", "18");
                processing.putString("name", "娱乐游玩");
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmTypeActivity.class, processing);*/
                break;
            case R.id.iv_clingy_bear:
                //粘人熊
                IntentUtils.getIntents().Intent(this, HauntedActivity.class, null);
                break;
            case R.id.iv_farm_house:
                //农场入驻
                IntentUtils.getIntents().Intent(this, Farm_inActivity.class, null);
                break;
            case R.id.iv_look_for_garden:
                //看人选园
                Bundle bundles = new Bundle();
                bundles.putString("id", "8");
                bundles.putString("name", "看人选园");
                IntentUtils.getIntents().Intent(this, GrowerActivity.class, bundles);
                break;
            case R.id.iv_me_speak:
                //我来代言
                IntentUtils.getIntents().Intent(this, EndorseSmartFarmActivity.class, null);
                break;
            case R.id.iv_map:
                //地图指认
                IntentUtils.getIntents().Intent(this, MapDentifyActivity.class, null);
                break;
            case R.id.iv_near:
                //搜附近
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 成功回调
     *
     * @param data
     */
    @Override
    public void success(Object data) {
        if (data instanceof WisdomBean) {
            WisdomBean wisdomBean = (WisdomBean) data;
            banner1 = wisdomBean.getData().getBanner();
            dimension = wisdomBean.getData().getDimension();
            essay1 = wisdomBean.getData().getEssay1();
            essay2 = wisdomBean.getData().getEssay2();
            essay3 = wisdomBean.getData().getEssay3();
            essay4 = wisdomBean.getData().getEssay4();
            banners = wisdomBean.getData().getBanners();
            processing = wisdomBean.getData().getProcessing();
            diqu = wisdomBean.getData().getDiqu();
            //banner
            banners();
            //模块
            dimensions();
            //网红农场
            hotfram();
            //0污染农场
            contaminatedfarm();
            //茶园农场
            teafarm();
            //娱乐游玩
            pleasurefarm();
            //banner2
            bannertwo();
            //加工型农场
            processingfarm();
            //吸顶
            scroll.setOnScrollChangeListener(new FlingNestedScrollView.ScrollChangeListener() {
                @Override
                public void onScrollChange(FlingNestedScrollView fnslv, int l, int t, int oldl, int oldt) {
                    int mHeight = re.getHeight();//获取标题栏高度
                    if (t > rl.getHeight() - 100 && mMagicIndicator.getParent() == layout) {
                        layout.removeView(mMagicIndicator);
                        rl_layout.addView(mMagicIndicator);
                    } else if (t < rl.getHeight() - 100 && mMagicIndicator.getParent() == rl_layout) {
                        rl_layout.removeView(mMagicIndicator);
                        layout.addView(mMagicIndicator);
                    }
                    //向下滑
                    if (t > 0 && t <= mHeight) {//向下滚动
                        float scale = (float) t / mHeight;
                        float alpha = (255 * scale);
                        re.setBackgroundColor(Color.argb((int) alpha, 21, 226, 81));
                    }
                    if (t > mHeight) {
                        re.setBackgroundResource(R.color.theme_orange);
                    }
                    if (t == 0) {// 滚动到顶
                        re.setBackgroundResource(R.color.wisdims_title);
                    }
                }

            });
            initMagicIndicator();
            //动态添加fragment
            initviewfragment();
            dialog.close();
            scroll.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void error(String error) {

    }


    private void initviewfragment() {
        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    private void processingfarm() {
        GlideUtils.loadRoundCircleImagetwo(this, Urls.BASEURL + processing.get(0).getPic(), tvProcessing);
        GlideUtils.loadRoundCircleImagetwo(this, Urls.BASEURL + processing.get(1).getPic(), tvProcessingone);
        GlideUtils.loadRoundCircleImagetwo(this, Urls.BASEURL + processing.get(2).getPic(), tvProcessingtwo);
    }


    private void bannertwo() {
        for (int i = 0; i < banners.size(); i++) {
            image.add(Urls.BASEURL + banners.get(i).getPic());
        }
        //设置是否为自动轮播，默认是“是”。
        bnTwo.isAutoPlay(true);
        //设置轮播间隔时间
        bnTwo.setDelayTime(3000);
        //设置指示器的位置，小点点，左中右。
        //.setEmptyImageRes(R.mipmap.no_banner) // banner为空时占位图
        //设置图片加载器
        bnTwo.setImageLoader(new MyImageLoader());
        //设置图片集合
        bnTwo.setImages(image)
                // .setOnBannerListener(Wisdoms_Activity.this)
                .start();
    }

    private void pleasurefarm() {
        TeaAdapter teaAdapter = new TeaAdapter(this, essay4);
        rvPlay.setAdapter(teaAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvPlay.setLayoutManager(linearLayoutManager);
        teaAdapter.setListener(new TeaAdapter.onListener() {
            @Override
            public void OnListener(int i) {
                Bundle bundle = new Bundle();
                bundle.putString("id", essay4.get(i).getId());
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmActivity.class, bundle);
            }
        });
    }

    private void teafarm() {
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + essay3.get(0).getPic(), tvOne);
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + essay3.get(1).getPic(), tvTwo);
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + essay3.get(2).getPic(), tvThere);
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + essay3.get(3).getPic(), tvFrou);
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + essay3.get(4).getPic(), tvFive);
    }

    private void contaminatedfarm() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        noPollution.setLayoutManager(gridLayoutManager);
        noPollution.addItemDecoration(new SpaceItemDecorations(20));
        if (essay2 != null) {
            ntaminatedFarmAdapter ntaminatedFarmAdapter = new ntaminatedFarmAdapter(essay2, this);
            noPollution.setAdapter(ntaminatedFarmAdapter);
            ntaminatedFarmAdapter.setListener(new ntaminatedFarmAdapter.onListener() {
                @Override
                public void OnListener(int i) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", essay2.get(i).getId());
                    IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmActivity.class, bundle);
                }
            });
        }
    }

    private void hotfram() {
        Hot_FramAdapter hot_framAdapter = new Hot_FramAdapter(essay1, this);
        rlFarmHot.setAdapter(hot_framAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlFarmHot.setLayoutManager(linearLayoutManager);
        hot_framAdapter.setListener(new Hot_FramAdapter.onListener() {
            @Override
            public void OnListener(int i) {
                Bundle bundle = new Bundle();
                bundle.putString("id", essay1.get(i).getId());
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmActivity.class, bundle);
            }
        });
    }

    private void dimensions() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerview.setLayoutManager(gridLayoutManager);
        Dimensions_Adapter dimensions_adapter = new Dimensions_Adapter(dimension, this);
        recyclerview.setAdapter(dimensions_adapter);
        dimensions_adapter.setListener(new Dimensions_Adapter.onListener() {
            @Override
            public void OnListener(int i) {
                Bundle bundle = new Bundle();
                bundle.putString("id", dimension.get(i).getId());
                bundle.putString("name", dimension.get(i).getTitle());
                IntentUtils.getIntents().Intent(Wisdoms_Activity.this, FarmTypeActivity.class, bundle);
            }
        });
    }

    //banner
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
                // .setOnBannerListener(Wisdoms_Activity.this)
                .start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 切换
     */
    private void initMagicIndicator() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(1.4f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return diqu == null ? 0 : diqu.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(diqu.get(index).getTitle());
                simplePagerTitleView.setNormalColor(Color.parseColor("#FF323232"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#FFE72929"));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewpager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(1.6f));
                indicator.setYOffset(UIUtil.dip2px(context, 3));
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setColors(Color.parseColor("#FFE72929"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer();
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(this, 17));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.wisdoms_shape));
        ViewPagerHelper.bind(magicIndicator, viewpager);
    }


    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            // 圆角处理
            RequestOptions myOptions = new RequestOptions().optionalTransform
                    (new GlideRoundedCornersTransform(Utils.dip2px(Wisdoms_Activity.this, 6f)
                            , GlideRoundedCornersTransform.CornerType.ALL));
            Glide.with(Wisdoms_Activity.this)
                    .load(path)
                    .into(imageView);
        }
    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        //带参的构造方法
        @Override
        public int getCount() {
            return diqu.size();
        }

        //返回选项卡的文本 ，，，添加选项卡
        @Override
        public CharSequence getPageTitle(int position) {
            return diqu.get(position).getTitle();
        }

        @Override
        public Fragment getItem(int position) {
            //创建fragment对象并返回
            Bundle bundle = new Bundle();
            bundle.putString("url", diqu.get(position).getId());
            //实例化Fragment
            WisdomsFragmnet wisdomsFragmnet = new WisdomsFragmnet();
            wisdomsFragmnet.setArguments(bundle);
            return wisdomsFragmnet;
        }
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
