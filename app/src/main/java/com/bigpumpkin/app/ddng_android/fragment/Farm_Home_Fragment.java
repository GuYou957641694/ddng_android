package com.bigpumpkin.app.ddng_android.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.Spell_DetailsActivity;
import com.bigpumpkin.app.ddng_android.adapter.Farm_Coupons_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.Farm_RecommendedAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Farm_CouponsBean;
import com.bigpumpkin.app.ddng_android.bean.Farm_Recommend_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class Farm_Home_Fragment extends BaseLazyLoadFragment implements MyView {

    @BindView(R.id.tv_rainfall)
    TextView tvRainfall;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_humidity)
    TextView tvHumidity;
    @BindView(R.id.tv_light)
    TextView tvLight;
    Unbinder unbinder;
    private JZVideoPlayerStandard mJC;
    private RecyclerView rv_coupons, rv_recommended;
    private boolean isViewCreated;
    private String id;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String pic2;
    private WebView wb_id;
    private List<Farm_CouponsBean.DataBean.CouponBean> coupon;
    private String url = "http://weilailingdi.weilailingdi.com/Web/Farm_index_recommend.html";
    private WebSettings webSettings = null;
    private List<Farm_Recommend_Bean.DataBean.ShopBean> shop;
    private Farm_CouponsBean.DataBean.WeatherBean weather;

    public static Farm_Home_Fragment getInstance(String id) {
        Farm_Home_Fragment farm_home_fragment = new Farm_Home_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        farm_home_fragment.setArguments(bundle);
        return farm_home_fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.farm_home_fragment;
    }

    @Override
    public void initViews(View view) {
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        isViewCreated = true;
        Bundle arguments = this.getArguments();
        id = arguments.getString("id");
        mJC = view.findViewById(R.id.mJC);
        rv_coupons = view.findViewById(R.id.rv_coupons);
        wb_id = view.findViewById(R.id.wb_id);
        rv_recommended = view.findViewById(R.id.rv_recommended);
    }

    @Override
    public void loadData() {
        map.put("fid", id);
        presenter.get(Contacts.Farm_index_homes, headmap, map, Farm_CouponsBean.class);
    }


    @Override
    public void success(Object data) {
        if (data instanceof Farm_CouponsBean) {
            Farm_CouponsBean farm_couponsBean = (Farm_CouponsBean) data;
            Farm_CouponsBean.DataBean data1 = farm_couponsBean.getData();
            Farm_CouponsBean.DataBean.EssayBean essay = data1.getEssay();
            pic2 = essay.getPic2();
            coupon = data1.getCoupon();
            weather = data1.getWeather();
            //视频
            initVidel();
            //优惠券
            initCoupn();
            //农场介绍
            initIntroduce();
            map.put("page", "1");
            presenter.get(Contacts.Farm_index_signboards, headmap, map, Farm_Recommend_Bean.class);

        } else if (data instanceof Farm_Recommend_Bean) {
            Farm_Recommend_Bean farm_recommend_bean = (Farm_Recommend_Bean) data;
            shop = farm_recommend_bean.getData().getShop();
            //为你推荐
            initRecommended();
        }
    }


    @Override
    public void error(String error) {

    }

    private void initRecommended() {
        Farm_RecommendedAdapter farm_recommendedAdapter = new Farm_RecommendedAdapter(shop, getActivity());
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        rv_recommended.setLayoutManager(staggeredGridLayoutManager);
        rv_recommended.setAdapter(farm_recommendedAdapter);
        farm_recommendedAdapter.setOnItemClickListener(new Farm_RecommendedAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //商品id
                String id = shop.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                //跳转详情页
                IntentUtils.getIntents().Intent(getActivity(), Spell_DetailsActivity.class, bundle);
            }
        });
    }

    private void initIntroduce() {
        wb_id.loadUrl(url);
        wb_id.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        webSettings = wb_id.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        wb_id.setWebChromeClient(client);
        wb_id.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 加载完成
                wb_id.loadUrl("javascript:recommend(" + id + " )");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // 加载开始5
            }
        });
    }

    WebChromeClient client = new WebChromeClient() {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }
    };

    private void initCoupn() {
        if (coupon != null) {
            Farm_Coupons_Adapter farm_coupons_adapter = new Farm_Coupons_Adapter(getActivity(), coupon);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv_coupons.setLayoutManager(manager);
            rv_coupons.setAdapter(farm_coupons_adapter);
        }
        tvRainfall.setText(weather.getWater());
        tvTemperature.setText(weather.getWet());
        tvHumidity.setText(weather.getHum());
        tvLight.setText(weather.getLight());
    }

    private void initVidel() {
        mJC.setUp(Urls.BASEURL + pic2, JZVideoPlayerStandard.SCROLL_AXIS_HORIZONTAL, "");
        mJC.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(this).load(R.drawable.ceshi).into(mJC.thumbImageView);
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreated) {
            if (isVisibleToUser) {
                JZVideoPlayerStandard.goOnPlayOnResume();
            } else {
                JZVideoPlayerStandard.goOnPlayOnPause();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
