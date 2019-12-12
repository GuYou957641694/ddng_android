package com.bigpumpkin.app.ddng_android.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.ClassificationAdater;
import com.bigpumpkin.app.ddng_android.adapter.ViewPagersAdapter;
import com.bigpumpkin.app.ddng_android.base.BasesFragment;
import com.bigpumpkin.app.ddng_android.bean.Plants_AdoptBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.ScreenUtil;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.ColorFlipPagerTitleView;
import com.bigpumpkin.app.ddng_android.weight.GlideRoundedCornersTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
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
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CeshiFragment extends BasesFragment implements OnRefreshListener, AppBarLayout.OnOffsetChangedListener, MyView, OnBannerListener {

    ImageView mIvHeader;
    Toolbar toolbar;
    MagicIndicator mMagicIndicator;
    AppBarLayout mAppbarLayout;
    ViewPager mViewPager;
    SmartRefreshLayout mRefreshLayout;
    ImageView ivBack;
    TextView mToolbarUsername;
    Toolbar mToolbar1;
    Banner banner;
    RecyclerView rvAdopt;
    RecyclerView rvSelection;
    ImageView ivAdvertising;
    ImageView ivAdvertising1;
    ImageView ivAdvertising2;
    ImageView ivOne;
    ImageView ivTwo;
    ImageView ivThere;
    ImageView ivFour;
    ImageView ivFive;
    ImageView ivSix;
    RecyclerView rvSpell;
    private List<Fragment> mFragments;
    private ViewPagersAdapter mViewPagerAdapter;
    private CommonNavigator mCommonNavigator;
    private String[] mTitles = new String[]{"推荐认养", "百年果树", "稀有果树", "想吃水果"};
    private List<String> mTitleList = Arrays.asList(mTitles);
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    /**
     * 屏幕宽度
     */
    private int mScreenWidth = 0;
    private List<Plants_AdoptBean.DataBean.BannerBean> banner1;
    private List<String> images = new ArrayList<>();
    private List<Plants_AdoptBean.DataBean.JournalismlenBean> journalismlen;
    private List<Plants_AdoptBean.DataBean.AreaBean> area;

    public static CeshiFragment newInstance(String title) {
        CeshiFragment mFragment = new CeshiFragment();
        Bundle bundle = new Bundle();
        bundle.putString("DATA", title);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public void initViews(View view) {
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        mIvHeader = view.findViewById(R.id.iv_header);
        toolbar = view.findViewById(R.id.toolbar);
        mMagicIndicator = view.findViewById(R.id.magic_indicator);
        mAppbarLayout = view.findViewById(R.id.appbar_layout);
        mViewPager = view.findViewById(R.id.viewPager);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        ivBack = view.findViewById(R.id.iv_back);
        mToolbarUsername = view.findViewById(R.id.toolbar_username);
        mToolbar1 = view.findViewById(R.id.toolbar1);
        banner = view.findViewById(R.id.banner);
        rvAdopt = view.findViewById(R.id.rv_adopt);
        rvSelection = view.findViewById(R.id.rv_selection);
        ivAdvertising = view.findViewById(R.id.iv_advertising);
        ivAdvertising1 = view.findViewById(R.id.iv_advertising1);
        ivAdvertising2 = view.findViewById(R.id.iv_advertising2);
        ivOne = view.findViewById(R.id.iv_one);
        ivTwo = view.findViewById(R.id.iv_two);
        ivThere = view.findViewById(R.id.iv_there);
        ivFour = view.findViewById(R.id.iv_four);
        ivFive = view.findViewById(R.id.iv_five);
        ivSix = view.findViewById(R.id.iv_six);
        rvSpell = view.findViewById(R.id.rv_spell);
        //禁止上拉加载
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(this);
        //上滑移动 设置监听
        mAppbarLayout.addOnOffsetChangedListener(this);
        //获得屏幕宽度
        mScreenWidth = ScreenUtil.getScreenWidth(getActivity());
        initMagicIndicator();


    }


    private void initView() {
        mRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                //设置图片向下移动
                mIvHeader.setTranslationY(offset / 2);
                //设置title渐变效果
                mToolbar1.setAlpha(1 - Math.min(percent, 1));
            }
        });
    }


    @Override
    public void success(Object data) {
        if (data instanceof Plants_AdoptBean) {
            Plants_AdoptBean plants_adoptBean = (Plants_AdoptBean) data;
            banner1 = plants_adoptBean.getData().getBanner();
            journalismlen = plants_adoptBean.getData().getJournalismlen();
            area = plants_adoptBean.getData().getArea();
            List<Plants_AdoptBean.DataBean.PublicityBean> publicity = plants_adoptBean.getData().getPublicity();
            List<Plants_AdoptBean.DataBean.PollutionBean> pollution = plants_adoptBean.getData().getPollution();
            List<Plants_AdoptBean.DataBean.SingleBean> single = plants_adoptBean.getData().getSingle();
            banners();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvAdopt.setLayoutManager(linearLayoutManager);
            if (journalismlen != null) {
                ClassificationAdater classificationAdater = new ClassificationAdater(journalismlen, getActivity());
                rvAdopt.setAdapter(classificationAdater);
            }
            //产品甄选
            rvSelection.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            //SelectionAdapter selectionAdapter = new SelectionAdapter(area, getActivity());
            //rvSelection.setAdapter(selectionAdapter);
            //0元领植物
            String pic = publicity.get(0).getPic();
            Glide.with(getActivity()).load(Urls.BASEURL + pic).into(ivAdvertising);
            Glide.with(getActivity()).load(Urls.BASEURL + publicity.get(1).getPic()).into(ivAdvertising1);
            Glide.with(getActivity()).load(Urls.BASEURL + publicity.get(2).getPic()).into(ivAdvertising2);
            //0污染
            Glide.with(getActivity()).load(Urls.BASEURL + pollution.get(0).getPic()).into(ivOne);
            Glide.with(getActivity()).load(Urls.BASEURL + pollution.get(1).getPic()).into(ivTwo);
            Glide.with(getActivity()).load(Urls.BASEURL + pollution.get(2).getPic()).into(ivThere);
            Glide.with(getActivity()).load(Urls.BASEURL + pollution.get(3).getPic()).into(ivFour);
            Glide.with(getActivity()).load(Urls.BASEURL + pollution.get(4).getPic()).into(ivFive);
            Glide.with(getActivity()).load(Urls.BASEURL + pollution.get(5).getPic()).into(ivSix);
            //拼单
           /* RvSpell_Adapter rvSpell_adapter = new RvSpell_Adapter(single, getActivity());
            rvSpell.setAdapter(rvSpell_adapter);
            LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity());
            linearLayoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvSpell.setLayoutManager(linearLayoutManagers);*/
            //只是提供演示传值
            mFragments = new ArrayList<>();
           /* mFragments.add(Fragment_Plant.newInstance("1"));//推荐认养
            mFragments.add(Fragment_Hundred.newInstance("1"));//百年果树
            mFragments.add(FragmentFruit.newInstance("1"));//稀有果树
            mFragments.add(FragmentRare.newInstance("1"));//想吃水果*/
            mViewPagerAdapter = new ViewPagersAdapter(getChildFragmentManager(), mFragments, mTitleList);
            mViewPager.setAdapter(mViewPagerAdapter);

            initView();

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
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载器
        MyImageLoader myImageLoader = new MyImageLoader();
        banner.setImageLoader(myImageLoader);
        //设置图片集合
        banner.setImages(images)
                .setOnBannerListener(this)
                .start();

    }

    private void initMagicIndicator() {
        mMagicIndicator.setBackgroundColor(getResources().getColor(R.color.white));
        mCommonNavigator = new CommonNavigator(getActivity());
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
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.theme_orange));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.color_323232));
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
                indicator.setColors(getResources().getColor(R.color.theme_orange));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(mCommonNavigator);
        LinearLayout titleContainer = mCommonNavigator.getTitleContainer();
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(getActivity(), 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(1500);
        presenter.get(Contacts.renyang, headmap, map, Plants_AdoptBean.class);
    }

    /**
     * 上滑移动 监听
     *
     * @param appBarLayout
     * @param verticalOffset
     */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int scrollRangle = appBarLayout.getTotalScrollRange();
        mIvHeader.setTranslationY(verticalOffset);
        /**
         * 这个数值可以自己定义
         */
        if (verticalOffset == 0) {
            //mIvBack.setImageResource(R.drawable.back_black);
            //mIvMenu.setImageResource(R.drawable.icon_menu_black);01000000
        } else {
            //mIvBack.setImageResource(R.drawable.back_white);
            //mIvMenu.setImageResource(R.drawable.icon_menu_white);
        }

        int mAlpha = (int) Math.abs(255f / scrollRangle * verticalOffset);
        //顶部title渐变效果
        //mToolbar1.setBackgroundColor(Color.argb(mAlpha, 255, 255, 255));
    /*    mToolbar1.setBackgroundColor(Color.parseColor("#00000000"));
        mToolbarUsername.setTextColor(Color.parseColor("#ff000000"));*/
        //setStatusBarColor(getActivity(), R.color.theme_orange);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_ceshi;
    }


    @Override
    public void loadData() {
        presenter.get(Contacts.renyang, headmap, map, Plants_AdoptBean.class);

    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    @Override
    public void OnBannerClick(int position) {
        ToastUtil.showShort(getActivity(), position + "");
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
}
