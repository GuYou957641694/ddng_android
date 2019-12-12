package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.IntegralBean;
import com.bigpumpkin.app.ddng_android.fragment.IntegralFragment;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.MyViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//我的积分
public class IntegralActivity extends BaseActivity implements View.OnClickListener, MyView {

    private ImageView iv_back;
    private TextView tv_integral;
    private LinearLayout lin_earn_points;
    private LinearLayout lin_integral_rules;
    private LinearLayout lin_points_for;
    private MagicIndicator magic_indicator;
    private MyViewPager mViewPager;
    private static final String[] CHANNELS = new String[]{"全部", "支出", "收入"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private String uri = "/Web/Mall_index/xyzz/integral_mall/home.html";
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String sha1;
    private long time;
    private String appid;
    private String appsecret;
    private String integral;
    private IntegralBean integralBean;


    @Override
    public int intiLayout() {
        return R.layout.activity_integral;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        presenter = new MyPresenterImpl(this);
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        iv_back = findViewById(R.id.iv_back);
        tv_integral = findViewById(R.id.tv_integral);
        lin_earn_points = findViewById(R.id.lin_earn_points);
        lin_integral_rules = findViewById(R.id.lin_integral_rules);
        lin_points_for = findViewById(R.id.lin_points_for);
        magic_indicator = findViewById(R.id.magic_indicator);
        mViewPager = findViewById(R.id.view_pager);
        iv_back.setOnClickListener(this);
        lin_earn_points.setOnClickListener(this);
        lin_integral_rules.setOnClickListener(this);
        lin_points_for.setOnClickListener(this);
        initMagicIndicator3();
    }


    @Override
    public void initData() {
        map.put("type", 1);
        map.put("page", 1);
        presenter.getpost(Contacts.MembersIntegrations, headmap, map, IntegralBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_earn_points:
                //赚取积分
                IntentUtils.getIntents().Intent(this, EarnPointsActivity.class, null);
                break;
            case R.id.lin_integral_rules:
                //积分规则
                IntentUtils.getIntents().Intent(this, IntegralRulesActivity.class, null);
                break;
            case R.id.lin_points_for:
                //积分兑换
                Bundle bundle = new Bundle();
                bundle.putString("link", uri);
                IntentUtils.getIntents().Intent(this, ProducerActivity.class, bundle);
                break;
            case R.id.iv_back:
                finish();
                break;

        }
    }


    private void initMagicIndicator3() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        magicIndicator.setBackgroundResource(R.drawable.round_indicator_bg);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);

                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(mDataList.get(index));
                clipPagerTitleView.setTextColor(Color.parseColor("#FF666666"));
                clipPagerTitleView.setClipColor(Color.parseColor("#FF17D54F"));
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                badgePagerTitleView.setInnerPagerTitleView(clipPagerTitleView);

                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float navigatorHeight = context.getResources().getDimension(R.dimen.dp_40);
                float borderWidth = UIUtil.dip2px(context, 1);
                float lineHeight = navigatorHeight - 2 * borderWidth;
                indicator.setLineHeight(lineHeight);
                indicator.setRoundRadius(lineHeight / 2);
                indicator.setYOffset(borderWidth);
                indicator.setXOffset(borderWidth);
                indicator.setColors(Color.parseColor("#FFFFFFFF"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }

    @Override
    public void success(Object data) {
        if (data instanceof IntegralBean) {
            integralBean = (IntegralBean) data;
            //积分总数
            integral = integralBean.getData().getIntegral();
            tv_integral.setText(integral);
            mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        }
    }

    @Override
    public void error(String error) {

    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        //带参的构造方法
        @Override
        public int getCount() {
            return mDataList.size();
        }

        //返回选项卡的文本 ，，，添加选项卡
        @Override
        public CharSequence getPageTitle(int position) {
            return mDataList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            //创建fragment对象并返回
            Bundle bundle = new Bundle();
            bundle.putSerializable("bean", (Serializable) integralBean);
            bundle.putInt("position", position);
            //实例化Fragment
            IntegralFragment integralFragment = new IntegralFragment();
            integralFragment.setArguments(bundle);
            return integralFragment;
        }
    }


}
