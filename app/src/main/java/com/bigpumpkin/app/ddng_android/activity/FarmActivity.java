package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.BaseFragmentAdapters;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.FarmHeadsBean;
import com.bigpumpkin.app.ddng_android.bean.Farm_AttentionsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.fragment.Farm_Home_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Farm_Preferential_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Farm_Product_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Farm_Recommended_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Farm_Show_Fragment;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.LoginUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.AppBarStateChangeListener;
import com.bigpumpkin.app.ddng_android.weight.ScaleTransitionPagerTitleView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

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

public class FarmActivity extends BaseActivity implements MyView {

    @BindView(R.id.iv_header_pic)
    ImageView ivHeaderPic;
    @BindView(R.id.tv_farm_name)
    TextView tvFarmName;
    @BindView(R.id.rl_like)
    RelativeLayout rlLike;
    @BindView(R.id.rl_shape)
    RelativeLayout rlShape;
    @BindView(R.id.iv_back)
    RelativeLayout ivBack;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_map)
    TextView tvMap;
    @BindView(R.id.tv_location)
    ImageView tvLocation;
    AppBarLayout appbarLayout;
    MagicIndicator mMagicIndicator;
    ViewPager viewPager;
    PopupWindow mPopupWindowtwo;
    String[] mTitles = new String[]{
            "首页", "产品", "推荐", "活动", "秀场"
    };
    @BindView(R.id.iv_focus)
    ImageView ivFocus;
    @BindView(R.id.iv_focu)
    ImageView ivFocu;
    private List<String> mTitleList = Arrays.asList(mTitles);
    private CommonNavigator mCommonNavigator;
    private List<Fragment> mFragments;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private SimplePagerTitleView simplePagerTitleView;
    private TabLayout tabId;
    private UMShareListener umShareListener;
    private FarmHeadsBean.DataBean data1;
    private String id;
    private String appid;
    private String appsecret;
    private String sha1;
    private long time;
    private String atten;
    private FarmHeadsBean.DataBean.EssayBean essay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_farm;
    }


    @Override
    public void initView() {
        mMagicIndicator = findViewById(R.id.magic_indicator);
        appbarLayout = findViewById(R.id.appbar_layout);
        viewPager = findViewById(R.id.viewPager);
        id = getIntent().getStringExtra("id");
        ToastUtil.showShort(this, id);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        initMagicIndicator();
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                // 分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(FarmActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(FarmActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(FarmActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };
    }


    @Override
    public void initData() {
        map.put("fid", id);
        if (!LoginUtil.getInstance().checkLoginStatuss(this)) {
            presenter.get(Contacts.Farm_index_heads, headmap, map, FarmHeadsBean.class);
            return;
        }
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        presenter.getpost(Contacts.Farm_index_heads, headmap, map, FarmHeadsBean.class);
    }


    @Override
    public void success(Object data) {
        if (data instanceof FarmHeadsBean) {
            FarmHeadsBean farmHeadsBean = (FarmHeadsBean) data;
            data1 = farmHeadsBean.getData();
            atten = data1.getAtten();
            //头部信息
            initHead();
            setupViewPager();
        } else if (data instanceof Farm_AttentionsBean) {
            Farm_AttentionsBean farm_attentionsBean = (Farm_AttentionsBean) data;
            Farm_AttentionsBean.DataBean data2 = farm_attentionsBean.getData();
            String code = data2.getCode();
            if (code.equals("1")) {
                ToastUtil.showShort(this, "关注成功");
                ivFocu.setVisibility(View.VISIBLE);
                ivFocus.setVisibility(View.GONE);
            } else {
                ToastUtil.showShort(this, "取消成功");
                ivFocu.setVisibility(View.GONE);
                ivFocus.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void error(String error) {

    }


    @OnClick({R.id.rl_like, R.id.rl_shape, R.id.iv_back, R.id.tv_map, R.id.tv_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_like:
                //关注
                initFocus();
                break;
            case R.id.rl_shape:
                //分享
                initShape();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_map:
            case R.id.tv_location:
                //地图
                Uri uri = Uri.parse("androidamap://route?sourceApplication={你的应用名称}"
                        + "&dname=" + essay.getDizhi()////终点的显示名称
                        + "&dev=0&m=0&t=0");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                intent.addCategory("android.intent.category.DEFAULT");
                startActivity(intent);
        }
    }

    private void initFocus() {
        if (!LoginUtil.getInstance().checkLoginStatus(this)) {
            return;
        }
        if (atten.equals("1")) {
            //提示用户确认取消关注

        } else {
            //关注
        }
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        map.clear();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        map.put("fid", id);
        presenter.getpost(Contacts.Farm_Attentions, headmap, map, Farm_AttentionsBean.class);
    }

    private void initHead() {
        //	2未关注 1关注
        String atten = data1.getAtten();
        if (atten.equals("1")) {
            ivFocu.setVisibility(View.VISIBLE);
        } else if (atten.equals("2")) {
            ivFocus.setVisibility(View.VISIBLE);
        }
        //农场信息
        essay = data1.getEssay();
        String pic3 = essay.getPic3();
        String title = essay.getTitle();
        String dizhi = essay.getDizhi();
        GlideUtils.loadImage(this, Urls.BASEURL + pic3, ivHeaderPic);
        tvFarmName.setText(title);
        tvAddress.setText("地址：" + dizhi);
    }

    private void setupViewPager() {
        mFragments = new ArrayList<>();
        mFragments.add(Farm_Home_Fragment.getInstance(id));
        mFragments.add(Farm_Product_Fragment.getInstance(id));
        mFragments.add(Farm_Recommended_Fragment.getInstance(id));
        mFragments.add(new Farm_Preferential_Fragment());
        mFragments.add(Farm_Show_Fragment.getInstance(id));

        BaseFragmentAdapters adapter =
                new BaseFragmentAdapters(getSupportFragmentManager(), mFragments);

        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    private void initMagicIndicator() {
        mMagicIndicator.setBackgroundColor(getResources().getColor(R.color.farm_icon));
        mCommonNavigator = new CommonNavigator(this);
        mCommonNavigator.setAdjustMode(true);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleList == null ? 0 : mTitleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mTitleList.get(index));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setNormalColor(Color.BLACK);
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.white));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.white));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 26));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(1.8f));
                indicator.setColors(getResources().getColor(R.color.white));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(mCommonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, viewPager);
        initAppbarLayout();

    }

    private void initAppbarLayout() {
        appbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    mMagicIndicator.setBackgroundColor(getResources().getColor(R.color.farm_icon));
                    simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.white));
                    simplePagerTitleView.setNormalColor(getResources().getColor(R.color.white));
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    mMagicIndicator.setBackgroundColor(getResources().getColor(R.color.farm_icon));
                    simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.farm_icon));
                    simplePagerTitleView.setNormalColor(getResources().getColor(R.color.farm_icon));
                } else {
                    //中间状态
                }
            }
        });
    }

    private void initShape() {
        View view = getLayoutInflater().inflate(R.layout.shape_item, null);
        mPopupWindowtwo = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView wx = view.findViewById(R.id.wx);
        ImageView wx_shape = view.findViewById(R.id.wx_shape);
        ImageView wb = view.findViewById(R.id.wb);
        ImageView qq = view.findViewById(R.id.qq);
        ImageView qq_space = view.findViewById(R.id.qq_space);
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callWxLinkShare();
            }
        });
        wx_shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callWxLinkShareCircle();
            }


        });
        wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSharerWeibo();
            }


        });
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        qq_space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindowtwo.setFocusable(true);// 取得焦点
        mPopupWindowtwo.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindowtwo.setOutsideTouchable(true);
        mPopupWindowtwo.setTouchable(true);
        mPopupWindowtwo.setAnimationStyle(R.style.mypopwindow_anim_style);
        mPopupWindowtwo.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindowtwo.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void callSharerWeibo() {
        new ShareAction(this).setPlatform(SHARE_MEDIA.SINA).withMedia(getUMSharerMedia()).setCallback(umShareListener).share();
    }

    private void callWxLinkShareCircle() {
        new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(getUMSharerMedia()
        ).setCallback(umShareListener).share();
    }

    private void callWxLinkShare() {
        new ShareAction(FarmActivity.this).setPlatform(SHARE_MEDIA.WEIXIN).withMedia(getUMSharerMedia()).setCallback(umShareListener).share();
    }

    private UMWeb getUMSharerMedia() {
        UMImage image = new UMImage(this, R.drawable.ceshi);
        final UMWeb web = new UMWeb("https://www.jianshu.com/p/748862fc929f");
        web.setTitle("测试");
        web.setThumb(image);
        web.setDescription("测试");
        return web;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
