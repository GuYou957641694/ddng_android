package com.bigpumpkin.app.ddng_android.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.Brand_Adapter;
import com.bigpumpkin.app.ddng_android.activity.HomeSearchActivity;
import com.bigpumpkin.app.ddng_android.adapter.Classification_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.Farm_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.Food_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.HomeOtherIndexTypeAdapter;
import com.bigpumpkin.app.ddng_android.adapter.MyPagerAdapter;
import com.bigpumpkin.app.ddng_android.adapter.Plant_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.bean.Bazaar_Bean;
import com.bigpumpkin.app.ddng_android.bean.GoodsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.BannerImageLoader;
import com.bigpumpkin.app.ddng_android.weight.ColorInfo;
import com.bigpumpkin.app.ddng_android.weight.GlideGifLoader;
import com.bigpumpkin.app.ddng_android.weight.MyViewPager;
import com.bigpumpkin.app.ddng_android.weight.ScrollTextView;
import com.yhy.gvp.widget.GridViewPager;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Goods_Fragment extends BaseFragment implements MyView {

    private String TAG = "Goods_Fragment";
    private List<ColorInfo> colorList = new ArrayList<>();
    private BannerImageLoader imageLoader;
    private ImageView ivBannerHeadBg;
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
    private MyPagerAdapter adapter;
    private GridViewPager gridViewPager;
    private RecyclerView classifications, brand, food, rescue, plant;
    private HomeOtherIndexTypeAdapter homeOtherIndexTypeAdapter;
    private MagicIndicator indicatorcontainer;
    //秒杀
    private TextView tv_seckill_hour, tv_seckill_minute, tv_seckill_second;
    private TabLayout tabLayout;
    private MyViewPager viewPager;
    private ScrollTextView scrolltextview;
    private EditText home_ev;
    private RelativeLayout rela;
    private SwipeRefreshLayout mSrv;
    //使用handler用于更新UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countDown();
            sendEmptyMessageDelayed(0, 1000);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.goods_fragment;
    }

    @Override
    protected void init(View view) {
        handler.sendEmptyMessage(0);
        presenter = new MyPresenterImpl(this);
        mSrv = view.findViewById(R.id.home_srv);
        home_ev = view.findViewById(R.id.home_ev);
        ivBannerHeadBg = view.findViewById(R.id.iv_banner_head_bg);
        banner = view.findViewById(R.id.banner);
        gridViewPager = view.findViewById(R.id.gridviewpager);
        indicatorcontainer = view.findViewById(R.id.indicator_container);
        classifications = view.findViewById(R.id.classification);
        tv_seckill_hour = view.findViewById(R.id.tv_seckill_hour);
        tv_seckill_minute = view.findViewById(R.id.tv_seckill_minute);
        tv_seckill_second = view.findViewById(R.id.tv_seckill_second);
        //秒杀
        rela = view.findViewById(R.id.rela);
        //跑马灯
        scrolltextview = view.findViewById(R.id.scrolltextview);
        //品牌农场街
        brand = view.findViewById(R.id.brand);
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
        //首页
        headmap = new HashMap<>();
        map = new HashMap<>();
        presenter.get(Contacts.fruitmall, headmap, map, GoodsBean.class);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        classifications.setLayoutManager(gridLayoutManager);
        //品牌农场街
        GridLayoutManager gridLayoutManagers = new GridLayoutManager(getActivity(), 4);
        brand.setLayoutManager(gridLayoutManagers);
        //定制食物
        food.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //救助农场
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rescue.setLayoutManager(linearLayoutManager);
        //植物食疗
        GridLayoutManager gridLayoutManagerz = new GridLayoutManager(getActivity(), 2);
        plant.setLayoutManager(gridLayoutManagerz);
        //点击搜索  跳转到搜索的页面
        home_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getIntents().Intent(getActivity(), HomeSearchActivity.class, null);
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
    }

    @Override
    protected void loadData() {
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
                GoodsBean.DataBean.AddonspecBean.TimeBean time = goodsBean.getData().getAddonspec().getTime();
                String type = goodsBean.getData().getAddonspec().getType();
                List<GoodsBean.DataBean.BrandFarmBean> brand_farm = goodsBean.getData().getBrand_farm();
                List<GoodsBean.DataBean.AdOneBean> ad_one = goodsBean.getData().getAd_one();
                List<GoodsBean.DataBean.RecommendedAdoptionBean> recommended_adoption = goodsBean.getData().getRecommended_adoption();
                List<GoodsBean.DataBean.FarmRescueBean> farm = goodsBean.getData().getFarm_rescue();
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
                scrolltextview.startScroll();
                //设置模块
                homeOtherIndexTypeAdapter = new HomeOtherIndexTypeAdapter(R.layout.home_modul, modul, getActivity());
                gridViewPager.setGVPAdapter(homeOtherIndexTypeAdapter);
                CommonNavigator commonNavigator = new CommonNavigator(getActivity());
                setIndicator(commonNavigator, modul, indicatorcontainer, gridViewPager);
                //
                Classification_Adapter homenewCourseAdapter = new Classification_Adapter(getActivity(), classification);
                classifications.setAdapter(homenewCourseAdapter);
                //开启倒计时秒杀
                if (type.equals("1")) {
                    rela.setVisibility(View.VISIBLE);
                } else if (type.equals("2")) {
                    rela.setVisibility(View.GONE);
                }
                //品牌农场街
                Brand_Adapter brand_adapter = new Brand_Adapter(getActivity(), brand_farm);
                brand.setAdapter(brand_adapter);
                //广告1
                guanggao.clear();
                for (int i = 0; i < ad_one.size(); i++) {
                    guanggao.add(ad_one.get(i).getPic());
                }
                advertising.setImageLoader(new GlideGifLoader());
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
                    guanggao1.add(ad_one.get(i).getPic());
                }
                advertisings.setImageLoader(new GlideGifLoader());
                advertisings.isAutoPlay(true);
                advertisings.setDelayTime(3000);
                advertisings.setImages(guanggao1);
                advertisings.start();
                //幸运
                presenter.get(Contacts.xingyun, headmap, map, Bazaar_Bean.class);
            } else {
                mSrv.setVisibility(View.GONE);
            }
        } else if (data instanceof Bazaar_Bean) {
            Bazaar_Bean bazaar_bean = (Bazaar_Bean) data;
            if (bazaar_bean != null) {
                datas = new ArrayList<>();
                fragments = new ArrayList<>();
                for (int i = 0; i < bazaar_bean.getData().getType().size(); i++) {
                    datas.add(bazaar_bean.getData().getType().get(i).getTitle());
                }
                fragments.add(new Fragment_One());
                fragments.add(new Fragment_Two());
                fragments.add(new Fragment_There());
                fragments.add(new Fragment_One());
                fragments.add(new Fragment_One());
                fragments.add(new Fragment_One());
                fragments.add(new Fragment_One());
                fragments.add(new Fragment_One());
                adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), fragments, datas);
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        //选择时触发
                        int position = tab.getPosition();
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        //未选择是触发
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        //选中之后再次点击即复选时触发
                    }
                });
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
                setStatusBarColor(getActivity(), vibrantColor);
            }

            @Override
            public void onPageSelected(final int position) {
                if (isInit) {// 第一次,延时加载才能拿到颜色
                    isInit = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int vibrantColor = imageLoader.getVibrantColor(1);
                            ivBannerHeadBg.setBackgroundColor(vibrantColor);
                            ivBannerHeadBg.setVisibility(View.VISIBLE);
                            setStatusBarColor(getActivity(), vibrantColor);
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

    /**
     * 秒杀
     */
    private void countDown() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        Log.d("ccc", substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour % 2 == 0) {
            //mMiaoshaTimeTv.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            //mMiaoshaTimeTv.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        Log.d(TAG, "countDown: " + totime);

        try {
            java.util.Date date = df.parse(totime);
            java.util.Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            tv_seckill_hour.setText("0" + hours + "");
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
