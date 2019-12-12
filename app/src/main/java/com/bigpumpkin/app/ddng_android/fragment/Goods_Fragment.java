package com.bigpumpkin.app.ddng_android.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.HauntedActivity;
import com.bigpumpkin.app.ddng_android.activity.HomeSearchActivity;
import com.bigpumpkin.app.ddng_android.activity.SaveFoodActivity;
import com.bigpumpkin.app.ddng_android.activity.SaveMoreActivity;
import com.bigpumpkin.app.ddng_android.adapter.BaseFragmentAdapter;
import com.bigpumpkin.app.ddng_android.adapter.Classification_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.Farm_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.Food_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.HomeBrandTypeAdapter;
import com.bigpumpkin.app.ddng_android.adapter.HomeOtherIndexTypeAdapter;
import com.bigpumpkin.app.ddng_android.adapter.Plant_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.Seconds_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.bean.GoodsBean;
import com.bigpumpkin.app.ddng_android.bean.Recommended_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.Utils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.AttachButton;
import com.bigpumpkin.app.ddng_android.weight.BannerImageLoader;
import com.bigpumpkin.app.ddng_android.weight.ColorInfo;
import com.bigpumpkin.app.ddng_android.weight.FlingNestedScrollView;
import com.bigpumpkin.app.ddng_android.weight.GlideRoundedCornersTransform;
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
import com.bigpumpkin.app.ddng_android.weight.MDGridRvDividerDecoration;
import com.bigpumpkin.app.ddng_android.weight.MyViewPager;
import com.bigpumpkin.app.ddng_android.weight.ScrollTextView;
import com.bigpumpkin.app.ddng_android.weight.UniversalItemDecoration;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hjq.toast.ToastUtils;
import com.yhy.gvp.widget.GridViewPager;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

//商城首页
public class Goods_Fragment extends BaseFragment implements MyView, EasyPermissions.PermissionCallbacks {

    private boolean types = true;
    private String TAG = "Goods_Fragment";
    private List<ColorInfo> colorList = new ArrayList<>();
    private BannerImageLoader imageLoader;
    private ImageView ivBannerHeadBg, save_food, save_diet;
    private int count;
    private boolean isInit = true;
    private Banner banner, advertising, advertisings;
    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private List<GoodsBean.DataBean.BannerBean> banner1;
    private List<String> images = new ArrayList<>();
    private List<String> guanggao = new ArrayList<>();
    private List<String> guanggao1 = new ArrayList<>();
    private List<String> pmd = new ArrayList<>();
    private List<String> datas;
    private List<Fragment> fragments;
    private BaseFragmentAdapter adapter;
    private GridViewPager gridViewPager, brand_gridviewpager;
    private RecyclerView classifications, brand, food, rescue, plant, seconds;
    private HomeOtherIndexTypeAdapter homeOtherIndexTypeAdapter;
    private MagicIndicator indicatorcontainer, brand_indicator_container;
    //秒杀
    private TextView tv_seckill_hour, tv_seckill_minute, tv_seckill_second, tv_kill;
    private TabLayout tabLayout;
    private MyViewPager viewPager;
    private ScrollTextView scrolltextview, scrolltextviews;
    private EditText home_ev;
    private RelativeLayout rela, rl_layout, relative, relativelayout;
    private LinearLayout linearlayout;
    private SwipeRefreshLayout mSrv;
    private FlingNestedScrollView scroll_view;
    private int vibrantColor;
    private ImageView save_more;
    /**
     * 分割线
     */
    private int space;
    //使用handler用于更新UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countDown();
            sendEmptyMessageDelayed(0, 1000);
        }
    };
    private long etime;
    private long stime;
    private long now;
    private GoodsBean.DataBean.AddonspecBean.TimeBean time;
    private LoadingDialog dialog;
    private AttachButton attached;

    @Override
    protected int getLayoutId() {
        return R.layout.goods_fragment;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void init(View view) {
        setStatusBarTransparent();
        handler.sendEmptyMessage(0);
        presenter = new MyPresenterImpl(this);
        mSrv = view.findViewById(R.id.home_srv);
        relativelayout = view.findViewById(R.id.relativelayout);
        scroll_view = view.findViewById(R.id.scroll_view);
        home_ev = view.findViewById(R.id.home_ev);
        ivBannerHeadBg = view.findViewById(R.id.iv_banner_head_bg);
        banner = view.findViewById(R.id.banner);
        gridViewPager = view.findViewById(R.id.gridviewpager);
        brand_gridviewpager = view.findViewById(R.id.brand_gridviewpager);
        indicatorcontainer = view.findViewById(R.id.indicator_container);
        brand_indicator_container = view.findViewById(R.id.brand_indicator_container);
        classifications = view.findViewById(R.id.classification);
        tv_seckill_hour = view.findViewById(R.id.tv_seckill_hour);
        tv_seckill_minute = view.findViewById(R.id.tv_seckill_minute);
        tv_seckill_second = view.findViewById(R.id.tv_seckill_second);
        rl_layout = view.findViewById(R.id.rl_layout);
        relative = view.findViewById(R.id.relative);
        linearlayout = view.findViewById(R.id.linearlayout);
        save_food = view.findViewById(R.id.save_food);
        //save_diet = view.findViewById(R.id.save_diet);
        save_more = view.findViewById(R.id.save_more);
        //秒杀
        rela = view.findViewById(R.id.rela);
        seconds = view.findViewById(R.id.seconds);
        tv_kill = view.findViewById(R.id.tv_kill);
        seconds.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //跑马灯
        scrolltextview = view.findViewById(R.id.scrolltextview);
        scrolltextviews = view.findViewById(R.id.scrolltextviews);
        //广告
        advertising = view.findViewById(R.id.advertising);
        //定制食物
        food = view.findViewById(R.id.food);
        //救助农民
        rescue = view.findViewById(R.id.rescue);
        //植物食疗
        plant = view.findViewById(R.id.plant);
        advertisings = view.findViewById(R.id.advertisings);
        //集市
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        attached = view.findViewById(R.id.attached);
        //首页
        headmap = new HashMap<>();
        map = new HashMap<>();
        //熊出没
        attached.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getIntents().Intent(getActivity(), HauntedActivity.class, null);
            }
        });
        //定制食物
        food.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        food.addItemDecoration(new UniversalItemDecoration() {
            @Override
            public Decoration getItemOffsets(int position) {
                ColorDecoration decoration = new ColorDecoration();
                decoration.right = 4;
                decoration.decorationColor = Color.WHITE;
                return decoration;
            }
        });
        //救助农场
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rescue.setLayoutManager(linearLayoutManager);
        //植物食疗
        plant.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //点击搜索  跳转到搜索的页面
        home_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   IntentUtils.getIntents().Intent(getActivity(), HomeSearchActivity.class, null);
                startActivity(new Intent(getActivity(), HomeSearchActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });
        //刷新
        mSrv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.get(Contacts.fruitmall, headmap, map, GoodsBean.class);
                mSrv.setRefreshing(false);
            }
        });
        //救助更多更多
        save_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getIntents().Intent(getActivity(), SaveMoreActivity.class, null);
            }
        });
        //预约定制更多
        save_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getIntents().Intent(getActivity(), SaveFoodActivity.class, null);
            }
        });
        //植物食疗更多
       /* save_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getIntents().Intent(getActivity(), SaveDietActivity.class, null);
            }
        });*/
        //瀑布流的布局方式: 2列,垂直方向
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        classifications.setLayoutManager(staggeredGridLayoutManager);
        space = Utils.px2dip(getActivity(), 40);
        classifications.addItemDecoration(new MDGridRvDividerDecoration(space, 4));
        seconds.addItemDecoration(new UniversalItemDecoration() {
            @Override
            public Decoration getItemOffsets(int position) {
                ColorDecoration decoration = new ColorDecoration();
                decoration.right = 4;
                decoration.decorationColor = Color.WHITE;
                return decoration;
            }
        });

        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {
            // 已经申请过权限，做想做的事
        } else {
            // 没有申请过权限，现在去申请
            EasyPermissions.requestPermissions(this, "申请权限定位权限",
                    0, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void loadData() {
        dialog = new LoadingDialog(getActivity(), "加载中...");
        dialog.show();
        presenter.get(Contacts.fruitmall, headmap, map, GoodsBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof GoodsBean) {
            GoodsBean goodsBean = (GoodsBean) data;
            if (goodsBean.getData() != null) {
                mSrv.setVisibility(View.VISIBLE);
                banner1 = goodsBean.getData().getBanner();
                List<GoodsBean.DataBean.ModulBean> modul = goodsBean.getData().getModul();
                List<GoodsBean.DataBean.ClassificationBean> classification = goodsBean.getData().getClassification();
                List<GoodsBean.DataBean.PumpkinHeadlineBean> pumpkin_headline = goodsBean.getData().getPumpkin_headline();
                List<GoodsBean.DataBean.AddonspecBean.ShopBean> shop = goodsBean.getData().getAddonspec().getShop();
                time = goodsBean.getData().getAddonspec().getTime();
                if (time != null) {
                    etime = time.getEtime();
                    stime = time.getStime();
                    now = time.getNow();
                }
                final String type = goodsBean.getData().getAddonspec().getType();
                List<GoodsBean.DataBean.BrandFarmBean> brand_farm = goodsBean.getData().getBrand_farm();
                List<GoodsBean.DataBean.AdOneBean> ad_one = goodsBean.getData().getAd_one();
                List<GoodsBean.DataBean.RecommendedAdoptionBean> recommended_adoption = goodsBean.getData().getRecommended_adoption();
                final List<GoodsBean.DataBean.FarmRescueBean> farm = goodsBean.getData().getFarm_rescue();
                List<GoodsBean.DataBean.TherapyBean> therapy = goodsBean.getData().getTherapy();
                List<GoodsBean.DataBean.AdTwoBean> ad_two = goodsBean.getData().getAd_two();

                //设置banner包括背景
                banner();
                initBanner();
                //跑马灯
                for (int i = 0; i < pumpkin_headline.size(); i++) {
                    pmd.add(pumpkin_headline.get(i).getTitle());
                }
                scrolltextview.setList(pmd);
                scrolltextviews.setList(pmd);
                scrolltextview.startScroll();
                scrolltextviews.startScroll();
                //设置模块
                homeOtherIndexTypeAdapter = new HomeOtherIndexTypeAdapter(R.layout.home_modul, modul, getActivity());
                gridViewPager.setGVPAdapter(homeOtherIndexTypeAdapter);
                CommonNavigator commonNavigator = new CommonNavigator(getActivity());
                setIndicator(commonNavigator, modul, indicatorcontainer, gridViewPager);
                //广告
                Classification_Adapter homenewCourseAdapter = new Classification_Adapter(getActivity(), classification);
                classifications.setAdapter(homenewCourseAdapter);
                //开启倒计时秒杀
                if (type.equals("1")) {
                    rela.setVisibility(View.VISIBLE);
                    Seconds_Adapter seconds_adapter = new Seconds_Adapter(shop, getActivity());
                    seconds.setAdapter(seconds_adapter);
                } else if (type.equals("2")) {
                    rela.setVisibility(View.GONE);
                }
                //品牌农场街
                HomeBrandTypeAdapter homeBrandTypeAdapter = new HomeBrandTypeAdapter(R.layout.home_brand_modul, brand_farm, getActivity());
                brand_gridviewpager.setGVPAdapter(homeBrandTypeAdapter);
                CommonNavigator commonNavigators = new CommonNavigator(getActivity());
                setIndicators(commonNavigators, brand_farm, brand_indicator_container, brand_gridviewpager);
                //广告1
                guanggao.clear();
                for (int i = 0; i < ad_one.size(); i++) {
                    guanggao.add(Urls.BASEURL + ad_one.get(i).getPic());
                }
                advertising.setImageLoader(new MyImageLoader());
                advertising.isAutoPlay(true);
                advertising.setDelayTime(3000);
                advertising.setImages(guanggao);
                advertising.start();
                //定制食物
                Food_Adapter food_adapter = new Food_Adapter(recommended_adoption, getActivity());
                food.setAdapter(food_adapter);
                //救助农民
                Farm_Adapter farm_adapter = new Farm_Adapter(farm, getActivity());
                rescue.setAdapter(farm_adapter);
                //植物食疗
                Plant_Adapter plant_Adapter = new Plant_Adapter(therapy, getActivity());
                plant.setAdapter(plant_Adapter);
                //广告位2
                guanggao1.clear();
                for (int i = 0; i < ad_one.size(); i++) {
                    guanggao1.add(Urls.BASEURL + ad_one.get(i).getPic());
                }
                advertisings.setImageLoader(new MyImageLoader());
                advertisings.isAutoPlay(true);
                advertisings.setDelayTime(3000);
                advertisings.setImages(guanggao1);
                advertisings.start();
                //监听吸顶
                scroll_view.setOnScrollChangeListener(new FlingNestedScrollView.ScrollChangeListener() {
                    @Override
                    public void onScrollChange(FlingNestedScrollView fnslv, int l, int t, int oldl, int oldt) {
                        int mHeight = relativelayout.getHeight();//获取标题栏高度
                        if (t > relative.getHeight() && tabLayout.getParent() == linearlayout) {
                            linearlayout.removeView(tabLayout);
                            rl_layout.addView(tabLayout);
                        } else if (t < relative.getHeight() && tabLayout.getParent() == rl_layout) {
                            rl_layout.removeView(tabLayout);
                            linearlayout.addView(tabLayout);
                        }
                        //向下滑
                        if (t > 0 && t <= mHeight) {//向下滚动
                            float scale = (float) t / mHeight;
                            float alpha = (255 * scale);
                            ivBannerHeadBg.setVisibility(View.GONE);
                            //relativelayout.setBackgroundResource(R.color.theme_orange);
                            relativelayout.setBackgroundColor(Color.argb((int) alpha, 32, 191, 85));
                            //setStatusBarColor(getActivity(), R.color.theme_orange);
                            types = false;
                        }
                        if (t == 0) {// 滚动到顶
                            ivBannerHeadBg.setVisibility(View.VISIBLE);
                            relativelayout.setBackgroundResource(R.color.whits);
                            //setStatusBarColor(getActivity(), vibrantColor);
                            types = true;
                        }

                    }
                });
                dialog.close();
                //幸运集市
                presenter.get(Contacts.jishi, headmap, map, Recommended_Bean.class);
            } else {
                mSrv.setVisibility(View.GONE);
            }
        } else if (data instanceof Recommended_Bean) {
            Recommended_Bean bazaar_bean = (Recommended_Bean) data;
            //判断是否展开
            if (bazaar_bean != null) {
                datas = new ArrayList<>();
                fragments = new ArrayList<>();
                for (int i = 0; i < bazaar_bean.getData().size(); i++) {
                    datas.add(bazaar_bean.getData().get(i).getTitle());
                }
                fragments.add(new Fragment_One());
                fragments.add(new Fragment_Two());
                fragments.add(new Fragment_There());
                fragments.add(new Fragment_Four());
                fragments.add(new Fragment_One());
                fragments.add(new Fragment_One());
                fragments.add(new Fragment_One());
                fragments.add(new Fragment_One());
                adapter = new BaseFragmentAdapter(getActivity().getSupportFragmentManager(), fragments, datas);
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        }
    }

    @Override
    public void error(String error) {
        Log.d(TAG, "error: " + error);
    }


    /**
     * 初始化banner
     */
    private void initBanner() {
        count = banner1.size();
        colorList.clear();
        images.clear();
        for (GoodsBean.DataBean.BannerBean bannerBean : banner1) {
            images.add(Urls.BASEURL + bannerBean.getPic());
        }
        for (int i = 0; i <= count + 1; i++) {
            ColorInfo info = new ColorInfo();
            if (i == 0) {
                info.setImgUrl(Urls.BASEURL + banner1.get(count - 1).getPic());
            } else if (i == count + 1) {
                info.setImgUrl(Urls.BASEURL + banner1.get(0).getPic());
            } else {
                info.setImgUrl(Urls.BASEURL + banner1.get(i - 1).getPic());
            }
            colorList.add(info);
        }

        imageLoader = new BannerImageLoader(colorList);
        banner.setImageLoader(imageLoader);
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        // banner.setBannerAnimation(Transformer.DepthPage);
        //设置轮播时间
        banner.setDelayTime(2000);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    private void banner() {
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 1) {//会出现极个别大于1的数据
                    return;
                }
                //修正position，解决两头颜色错乱，来自Banner控件源码
                if (position == 0) {
                    position = count;
                }
                if (position > count) {
                    position = 1;
                }
                int pos = (position + 1) % count;//很关键

                int vibrantColor = ColorUtils.blendARGB(imageLoader.getVibrantColor(pos), imageLoader.getVibrantColor(pos + 1), positionOffset);
                ivBannerHeadBg.setBackgroundColor(vibrantColor);
                if (types == true) {
                    //setStatusBarColor(getActivity(), vibrantColor);
                } else {
                    //setStatusBarColor(getActivity(), R.color.whits);
                    //setStatusBarColor(getActivity(), R.color.theme_orange);
                }
            }

            @Override
            public void onPageSelected(final int position) {
                if (isInit) {// 第一次,延时加载才能拿到颜色
                    isInit = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            vibrantColor = imageLoader.getVibrantColor(1);
                            ivBannerHeadBg.setBackgroundColor(vibrantColor);
                            ivBannerHeadBg.setVisibility(View.VISIBLE);
                            if (types == true) {
                                // setStatusBarColor(getActivity(), vibrantColor);
                            } else {
                                //setStatusBarColor(getActivity(), R.color.whits);
                                //setStatusBarColor(getActivity(), R.color.theme_orange);
                            }
                        }
                    }, 200);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     */
    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //状态栏改变颜色。
            window.setStatusBarColor(color);
        }
    }

    //设置选择器
    private void setIndicator(CommonNavigator commonNavigator, final List<GoodsBean.DataBean.ModulBean> typeDatas, MagicIndicator indicatorContainer, GridViewPager gridViewpager) {
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                int num = typeDatas.size() / 8;
                if (typeDatas.size() % 8 > 0) {
                    num++;
                }
                //返回分类指示器的数量
                return typeDatas == null ? 0 : num;
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

    //设置选择器s
    private void setIndicators(CommonNavigator commonNavigators, final List<GoodsBean.DataBean.BrandFarmBean> brandFarmBeans, MagicIndicator indicatorContainers, GridViewPager gridViewpager) {
        commonNavigators.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                int num = brandFarmBeans.size() / 8;
                if (brandFarmBeans.size() % 8 > 0) {
                    num++;
                }
                //返回分类指示器的数量
                return brandFarmBeans == null ? 0 : num;
            }

            @Override
            public IPagerTitleView getTitleView(Context mContext, final int i) {
                CommonPagerTitleView commonPagerTitleViews = new CommonPagerTitleView(mContext);
                View view = View.inflate(mContext, R.layout.single_image_layouts, null);
                final ImageView iv_image = view.findViewById(R.id.img_indicator_shows);
                iv_image.setImageResource(R.drawable.rg_false_bg);

                //指示器引入外部布局，可知指示器内容可根据需求设置，多样化
                commonPagerTitleViews.setContentView(view);

                commonPagerTitleViews.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
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
                return commonPagerTitleViews;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        indicatorContainers.setNavigator(commonNavigators);
        ViewPagerHelper.bind(indicatorContainers, gridViewpager);
    }

    /**
     * 秒杀
     */
    private void countDown() {
        if (time != null) {
            if (now > stime) {
                //开始中
                tv_kill.setText("抢购中");
            } else {
                //未开始
                tv_kill.setText("即将开始");
            }
            //取系统的时间
            long time = System.currentTimeMillis();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = new Date(time);
            Date d2 = new Date(etime * 1000);
            String format = df.format(d1);
            String format1 = df.format(d2);
            StringBuffer buffer = new StringBuffer();
            buffer.append(format1);
            String totime = buffer.toString();
            try {
                java.util.Date date = df.parse(totime);
                java.util.Date date1 = df.parse(format);
                long defferenttime = date.getTime() - date1.getTime();
                long days = defferenttime / (1000 * 60 * 60 * 24);
                long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                long seconds = defferenttime % 60000;
                long second = Math.round((float) seconds / 1000);
                if (hours >= 10) {
                    tv_seckill_hour.setText(hours + "");
                } else {
                    tv_seckill_hour.setText("0" + hours + "");
                }
                if (minute >= 10) {
                    tv_seckill_minute.setText(minute + "");
                } else {
                    tv_seckill_minute.setText("0" + minute + "");
                }
                if (second >= 10) {
                    tv_seckill_second.setText(second + "");
                } else {
                    tv_seckill_second.setText("0" + second + "");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case 0:
                break;

        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case 0:
                ToastUtils.show("拒绝定位权限");
                break;
        }
    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            // 圆角处理
            RequestOptions myOptions = new RequestOptions().optionalTransform
                    (new GlideRoundedCornersTransform(dip2px(getActivity(), 6f)
                            , GlideRoundedCornersTransform.CornerType.ALL));
            Glide.with(getActivity())
                    .load(path)
                    .apply(myOptions)
                    .into(imageView);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    /**
     * 设置透明状态栏
     */
    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }
}