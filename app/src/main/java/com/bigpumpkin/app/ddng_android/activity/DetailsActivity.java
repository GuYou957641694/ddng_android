package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Commodity_RecommendedsAdapter;
import com.bigpumpkin.app.ddng_android.adapter.Photo_PreviewAdapter;
import com.bigpumpkin.app.ddng_android.adapter.PreferentialAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.AddShoppingBean;
import com.bigpumpkin.app.ddng_android.bean.Address_Success_Bean;
import com.bigpumpkin.app.ddng_android.bean.Cancel_collectionsBean;
import com.bigpumpkin.app.ddng_android.bean.Commodity_Recommended_Bean;
import com.bigpumpkin.app.ddng_android.bean.GoodBean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.fragment.GoodsPhotoFragment;
import com.bigpumpkin.app.ddng_android.fragment.GoodsVideoFragment;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.LoginUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.FlowPopWindow;
import com.bigpumpkin.app.ddng_android.weight.IdeaScrollView;
import com.bigpumpkin.app.ddng_android.weight.IdeaViewPager;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import qiu.niorgai.StatusBarCompat;

/**
 * 商品详情
 */
public class DetailsActivity extends BaseActivity implements MyView, View.OnClickListener {

    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String sha1;
    private long time;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mMyPagerAdapter;
    private IdeaViewPager viewPager;
    private IdeaScrollView ideaScrollView;
    private RelativeLayout header, lin, options, specifications, service, preferential, main, rl_top, rl_share;
    private RadioGroup radioGroup;
    private LinearLayout headerParent, llSlideVideo;
    private float currentPercentage = 0;
    private ImageView back, tv_share;
    private TextView tvSlideImage, tv_pager_index, tvSlideVideo, name, tv_price, textview, ncname, pf;
    private Button buy, enter, joincar;
    private RadioGroup.OnCheckedChangeListener radioGroupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor(currentPercentage) : getRadioAlphaColor(currentPercentage));
                if (radioButton.isChecked() && isNeedScrollTo) {
                    ideaScrollView.setPosition(i);
                }
            }
        }
    };
    private boolean isNeedScrollTo = true;
    private String id;
    private String user;
    private int mTopVideoSum = 0;
    private ExpandableListView edpandablelistview;
    private Photo_PreviewAdapter photo_previewAdapter;
    private List<GoodBean.DataBean.OptionsBean> options1;
    private RecyclerView recyclerview, all;
    private CircleImageView head;
    private WebView web;
    private WebView wbCertExam;
    private List<GoodBean.DataBean.VarietyBean> variety;
    private RecyclerView recyclerview1;
    private String title;
    private ImageView pic, collection_icou;
    private GoodBean.DataBean data1;
    private TextView kc, tv_coupons, tv_full1, tv_full;
    private TextView cy;
    private TextView cp;
    private TextView num, no_evaluation, all_evaluation;
    private GoodBean goodBean;
    private TextView price, maintenance;
    private String gg_id;
    private List<GoodBean.DataBean.CouponBean> coupon;
    private RelativeLayout shoppcollection, rv_coupons_gon;
    private String collection;
    //规格
    private StringBuilder sb, skutitle;
    private String s1;
    private RelativeLayout rl;
    private GoodBean.DataBean.FarmDetailsBean farm_details;
    private FlowPopWindow flowPopWindow;
    private boolean WHETHER = true;
    private int COUPONSID;
    private PreferentialAdapter preferentialAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_details;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
        presenter = new MyPresenterImpl(this);
        //判断有没有登录
        SharedPreferences sharedPreferences = getSharedPreferences("user",
                Context.MODE_PRIVATE);
        user = sharedPreferences.getString("zt", "");
        id = getIntent().getStringExtra("id");
        ToastUtil.showShort(this, id + "");
        header = (RelativeLayout) findViewById(R.id.header);
        back = findViewById(R.id.back);
        service = findViewById(R.id.service);
        preferential = findViewById(R.id.preferential);
        buy = findViewById(R.id.buy);
        llSlideVideo = findViewById(R.id.llSlideVideo);
        tvSlideImage = findViewById(R.id.tvSlideImage);
        tv_coupons = findViewById(R.id.tv_coupons);
        tv_full1 = findViewById(R.id.tv_full1);
        tv_full = findViewById(R.id.tv_full);
        tv_pager_index = findViewById(R.id.tv_pager_index);
        rv_coupons_gon = findViewById(R.id.rv_coupons_gon);
        tvSlideVideo = findViewById(R.id.tvSlideVideo);
        recyclerview = findViewById(R.id.recyclerview);
        all_evaluation = findViewById(R.id.all_evaluation);
        joincar = findViewById(R.id.joincar);
        no_evaluation = findViewById(R.id.no_evaluation);
        collection_icou = findViewById(R.id.collection_icou);
        specifications = findViewById(R.id.specifications);
        maintenance = findViewById(R.id.maintenance);
        enter = findViewById(R.id.enter);
        textview = findViewById(R.id.textview);
        pf = findViewById(R.id.pf);
        tv_price = findViewById(R.id.price);
        rl_share = findViewById(R.id.rl_share);
        tv_share = findViewById(R.id.tv_share);
        name = findViewById(R.id.name);
        rl_top = findViewById(R.id.rl_top);
        shoppcollection = findViewById(R.id.shoppcollection);
        ncname = findViewById(R.id.ncname);
        lin = findViewById(R.id.lin);
        head = findViewById(R.id.head);
        web = findViewById(R.id.web);
        rl = findViewById(R.id.rl);
        all = findViewById(R.id.all);
        main = (RelativeLayout) findViewById(R.id.main);
        options = findViewById(R.id.options);
        headerParent = (LinearLayout) findViewById(R.id.headerParent);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        ideaScrollView = (IdeaScrollView) findViewById(R.id.ideaScrollView);
        viewPager = (IdeaViewPager) findViewById(R.id.viewPager);
        back.setOnClickListener(this);
        options.setOnClickListener(this);
        specifications.setOnClickListener(this);
        service.setOnClickListener(this);
        rl_share.setOnClickListener(this);
        buy.setOnClickListener(this);
        llSlideVideo.setOnClickListener(this);
        preferential.setOnClickListener(this);
        shoppcollection.setOnClickListener(this);
        joincar.setOnClickListener(this);
        tv_share.setOnClickListener(this);
        tvSlideImage.setOnClickListener(this);
        enter.setOnClickListener(this);
        rl_top.setOnClickListener(this);
        StatusBarCompat.translucentStatusBar(this);
        Rect rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        ideaScrollView.setViewPager(viewPager, getMeasureHeight(headerParent) - rectangle.top);
        radioGroup.setAlpha(0);
        radioGroup.check(radioGroup.getChildAt(0).getId());
        View one = findViewById(R.id.one);
        View two = findViewById(R.id.two);
        View four = findViewById(R.id.four);
        View three = findViewById(R.id.three);
        ArrayList<Integer> araryDistance = new ArrayList<>();
        araryDistance.add(0);
        araryDistance.add(getMeasureHeight(one) - getMeasureHeight(headerParent));
        araryDistance.add(getMeasureHeight(one) + getMeasureHeight(two) - getMeasureHeight(headerParent));
        araryDistance.add(getMeasureHeight(one) + getMeasureHeight(two) + getMeasureHeight(three) - getMeasureHeight(headerParent));
        ideaScrollView.setArrayDistance(araryDistance);
        ideaScrollView.setOnScrollChangedColorListener(new IdeaScrollView.OnScrollChangedColorListener() {
            @Override
            public void onChanged(float percentage) {
                int color = getAlphaColor(percentage > 0.9f ? 1.0f : percentage);
                header.setBackgroundDrawable(new ColorDrawable(color));
                radioGroup.setBackgroundDrawable(new ColorDrawable(color));
                radioGroup.setAlpha((percentage > 0.9f ? 1.0f : percentage) * 255);
                rl.setAlpha((percentage > 0.9f ? 1.0f : percentage) * 255);
                lin.setAlpha((percentage > 0.9f ? 1.0f : percentage) * 255);
                rl.setBackgroundDrawable(new ColorDrawable(color));
                lin.setBackgroundDrawable(new ColorDrawable(color));
                setRadioButtonTextColor(percentage);
            }

            @Override
            public void onChangedFirstColor(float percentage) {

            }

            @Override
            public void onChangedSecondColor(float percentage) {

            }
        });

        ideaScrollView.setOnSelectedIndicateChangedListener(new IdeaScrollView.OnSelectedIndicateChangedListener() {
            @Override
            public void onSelectedChanged(int position) {
                isNeedScrollTo = false;
                radioGroup.check(radioGroup.getChildAt(position).getId());
                isNeedScrollTo = true;
            }
        });

        radioGroup.setOnCheckedChangeListener(radioGroupListener);
        ideaScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (ideaScrollView.getScrollY() == 0) {
                    //顶部
                    rl_top.setVisibility(View.GONE);
                    tv_share.setVisibility(View.VISIBLE);
                } else {
                    rl_top.setVisibility(View.VISIBLE);
                }
            }
        });
        initImgVideoVp();
    }

    @Override
    public void initData() {
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("id", id);
        if (TextUtils.isEmpty(user.trim())) {
            presenter.get(Contacts.good, headmap, map, GoodBean.class);
        } else {
            time = System.currentTimeMillis();
            appid = SpzUtils.getString("appid");
            appsecret = SpzUtils.getString("appsecret");
            String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
            sha1 = EncryptUtils.getSHA(sha);
            map.put("appid", appid);
            map.put("appsecret", appsecret);
            map.put("timestamp", time);
            map.put("sign", sha1);
            presenter.getpost(Contacts.good, headmap, map, GoodBean.class);
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof GoodBean) {
            goodBean = (GoodBean) data;
            if (goodBean != null) {
                //图片视频
                String mp4 = goodBean.getData().getMp4();
                data1 = goodBean.getData();
                mFragments.clear();
                if (mp4 != null) {
                    mFragments.add(GoodsVideoFragment.getInstance(goodBean.getData().getWheel().get(1).getPic(), mp4));
                }
                for (int i = 0; i < goodBean.getData().getWheel().size(); i++) {
                    mFragments.add(GoodsPhotoFragment.getInstance(goodBean.getData().getWheel().get(i).getPic()));
                }
                mMyPagerAdapter.notifyDataSetChanged();
                if (data1.getTitle() != null) {
                    name.setText(data1.getTitle());
                }
                if (data1.getPrice() != null) {
                    String price = data1.getPrice();
                    String str = "\u00a5".concat(price);
                    SpannableString spannableString = new SpannableString(str);
                    RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.6f);
                    spannableString.setSpan(relativeSizeSpan, 1, price.length() - 2,
                            Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    AssetManager mgr = getAssets();
                    Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
                    tv_price.setTypeface(tf);
                    tv_price.setText(spannableString);
                }
                //定制选项
                options1 = goodBean.getData().getOptions();
                //规格
                variety = goodBean.getData().getVariety();
                //评价
                List<GoodBean.DataBean.EvaluationBean.LabelBean> label = goodBean.getData().getEvaluation().getLabel();
                if (goodBean.getData().getEvaluation().getLabel().get(0).getId().equals("0")) {
                    recyclerview.setVisibility(View.GONE);
                    textview.setText("宝贝评价" + "(" + goodBean.getData().getEvaluation().getNum() + ")");
                    no_evaluation.setText("暂无评价");
                    no_evaluation.setVisibility(View.VISIBLE);
                } else {
                    textview.setText("宝贝评价" + "(" + goodBean.getData().getEvaluation().getNum() + ")");
                    recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    //Evaluation_Adapter evaluation_adapter = new Evaluation_Adapter(label, this);
                   // recyclerview.setAdapter(evaluation_adapter);
                    recyclerview.setVisibility(View.VISIBLE);
                    all_evaluation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            IntentUtils.getIntents().Intent(DetailsActivity.this, All_EvaluationActivity.class, null);
                        }
                    });
                }

                //农场
                farm_details = goodBean.getData().getFarm_details();
                Glide.with(this).load(Urls.BASEURL + farm_details.getPic()).into(head);
                ncname.setText(farm_details.getTitle());
                //详情
                String details = goodBean.getData().getDetails();
                String uri = Urls.BASEURL + details;
                Log.d(TAG, "success: " + uri);
                web.loadUrl(uri);
                //优惠
                coupon = goodBean.getData().getCoupon();
                //判断是否有优惠券
                if (coupon.size() < 1) {
                    preferential.setVisibility(View.GONE);
                } else {
                    if (coupon.size() > 1) {
                        tv_full.setText("满" + coupon.get(0).getMan() + "减" + coupon.get(0).getJian());
                        tv_full1.setText("满" + coupon.get(1).getMan() + "减" + coupon.get(1).getJian());
                    } else {
                        tv_full.setText("满" + coupon.get(1).getMan() + "减" + coupon.get(1).getJian());
                        rv_coupons_gon.setVisibility(View.GONE);
                    }
                }
                //收藏
                collection = goodBean.getData().getCollection();
                if (collection.equals("1")) {
                    //收藏
                    Glide.with(this).load(R.drawable.collection_true).into(collection_icou);
                } else {
                    //未收藏
                    Glide.with(this).load(R.drawable.collection_false).into(collection_icou);
                }
                map.clear();
                map.put("s_id", id);
                presenter.get(Contacts.Commodity_Recommendeds, headmap, map, Commodity_Recommended_Bean.class);
            }
        } else if (data instanceof Commodity_Recommended_Bean) {

            Commodity_Recommended_Bean commodity_recommended_bean = (Commodity_Recommended_Bean) data;
            List<Commodity_Recommended_Bean.DataBean> data2 = commodity_recommended_bean.getData();
            Commodity_RecommendedsAdapter commodity_recommendedsAdapter = new Commodity_RecommendedsAdapter(this, data2);
            StaggeredGridLayoutManager staggeredGridLayoutManager =
                    new StaggeredGridLayoutManager(2,
                            StaggeredGridLayoutManager.VERTICAL);
            all.setLayoutManager(staggeredGridLayoutManager);
            all.setAdapter(commodity_recommendedsAdapter);

        } else if (data instanceof AddShoppingBean) {
            AddShoppingBean addShoppingBean = (AddShoppingBean) data;
            if (addShoppingBean.getCode().equals("200")) {
                ToastUtil.showShort(this, "加入成功");
                mPopupWindowone.dismiss();
            }
        } else if (data instanceof Zfb_Bean) {
            Zfb_Bean zfb_bean = (Zfb_Bean) data;
            if (zfb_bean.getCode().equals("200")) {
                coupon.get(COUPONSID).setGet(1);
                ToastUtil.showShort(this, "领取成功");
               // preferentialAdapter.setCoupon(coupon);
                preferentialAdapter.notifyDataSetChanged();
            }

        } else if (data instanceof Address_Success_Bean) {
            Address_Success_Bean address_success_bean = (Address_Success_Bean) data;
            if (address_success_bean.getCode().equals("200")) {
                Toast toast = new Toast(DetailsActivity.this);
                View view = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.toast_custom, null);
                toast.setView(view);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Glide.with(this).load(R.drawable.collection_true).into(collection_icou);
                goodBean.getData().setCollection("1");
            }
        } else if (data instanceof Cancel_collectionsBean) {
            Cancel_collectionsBean cancel_collectionsBean = (Cancel_collectionsBean) data;
            if (cancel_collectionsBean.getCode().equals("200")) {
                ToastUtil.showShort(this, "成功");
                Glide.with(this).load(R.drawable.collection_false).into(collection_icou);
                goodBean.getData().setCollection("2");
            }
        }
    }

    @Override
    public void error(String error) {
        Log.d(TAG, "error: " + error);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.options:
                //选项常规、定制
                if (options1 != null) {
                    //flowPopWindow = new FlowPopWindow(DetailsActivity.this, options1);
                    flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
                        @Override
                        public void onConfirmClick() {
                            sb = new StringBuilder();
                            skutitle = new StringBuilder();
                            for (GoodBean.DataBean.OptionsBean fb : options1) {
                                List<GoodBean.DataBean.OptionsBean.CustomlistBean> cdList = fb.getCustomlist();
                                for (int x = 0; x < cdList.size(); x++) {
                                    GoodBean.DataBean.OptionsBean.CustomlistBean children = cdList.get(x);
                                    if (children.isSelected()) {
                                        sb.append(children.getId() + ",");
                                        //选中规格的id需要传入后台
                                        s1 = sb.toString();
                                        String title = children.getTitle();
                                        //规格名展示
                                        skutitle.append(title + "、");
                                    }
                                }
                            }
                            String sku = skutitle.toString();
                            if (sku.length() < 1) {
                                maintenance.setText("常规维护");
                            } else {
                                maintenance.setText(sku);
                            }
                        }
                    });
                }
                break;
            case R.id.specifications:
                //规格
                //specificationspop();
                break;
            case R.id.service:
                //服务
                servicepop();
                break;
            case R.id.preferential:
                //优惠
                if (!LoginUtil.getInstance().checkLoginStatus(this)) {
                    return;
                }
                preferentialpop();
                break;
            case R.id.buy:
                //立即购买
                if (!LoginUtil.getInstance().checkLoginStatus(this)) {
                    return;
                }
                //specificationspop();
                break;
            case R.id.joincar:
                //加入购物车
                if (!LoginUtil.getInstance().checkLoginStatus(this)) {
                    return;
                }
                //规格
               // specificationspop();
                break;
            case R.id.car:
                //购物车
                if (!LoginUtil.getInstance().checkLoginStatus(this)) {
                    return;
                }
                break;
            case R.id.shoppcollection:
                //收藏
                if (!LoginUtil.getInstance().checkLoginStatus(this)) {
                    return;
                }
                map.clear();
                time = System.currentTimeMillis();
                appid = SpzUtils.getString("appid");
                appsecret = SpzUtils.getString("appsecret");
                String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                sha1 = EncryptUtils.getSHA(sha);
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                if (goodBean.getData().getCollection().equals("1")) {
                    map.put("vaid", id);
                    presenter.getpost(Contacts.My_cancel_collections, headmap, map, Cancel_collectionsBean.class);
                } else {
                    map.put("id", id);
                    presenter.getpost(Contacts.collections, headmap, map, Address_Success_Bean.class);
                }
                break;
            case R.id.from:
                //农场
                break;
            case R.id.tvSlideImage:
                //点击图片
                viewPager.setCurrentItem(1);
                break;
            case R.id.llSlideVideo:
                //点击视频
                viewPager.setCurrentItem(0);
                break;
            case R.id.enter:
                Bundle bundle = new Bundle();
                bundle.putString("id", farm_details.getId());
                IntentUtils.getIntents().Intent(this, FarmActivity.class, bundle);
                break;
            case R.id.rl_top:
                ideaScrollView.fullScroll(View.FOCUS_UP);
                break;
            case R.id.tv_share:
                ToastUtil.showShort(this, "分享");
                break;
            case R.id.rl_share:
                ToastUtil.showShort(this, "分享");
                break;
        }
    }


    public void setRadioButtonTextColor(float percentage) {
        if (Math.abs(percentage - currentPercentage) >= 0.1f) {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor(percentage) : getRadioAlphaColor(percentage));
            }
            this.currentPercentage = percentage;
        }
    }

    public int getMeasureHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }

    public int getAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0x09, 0xc1, 0xf4);
    }

    public int getLayerAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0x09, 0xc1, 0xf4);
    }

    public int getRadioCheckedAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0x44, 0x44, 0x44);
    }

    public int getRadioAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0xFF, 0xFF, 0xFF);
    }

    private PopupWindow mPopupWindow;

    private void pop() {
        View view = getLayoutInflater().inflate(R.layout.photo_preview, null);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //edpandablelistview = view.findViewById(R.id.edpandablelistviews);
        if (options1 != null) {
            /*photo_previewAdapter = new Photo_PreviewAdapter(options1, this);
            edpandablelistview.setAdapter(photo_previewAdapter);*/
        }
        //产生背景变暗效果
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        mPopupWindow.setOutsideTouchable(true);
        //设置可以点击
        mPopupWindow.setTouchable(true);
        //mPopupWindow，指定刚才定义的style
        mPopupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

        //设置popupWindow消失时的监听
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    PopupWindow mPopupWindowone;
    private int j;
    private int index;
    private String fidname;

    //规格
  /*  private void specificationspop() {
        View view = getLayoutInflater().inflate(R.layout.specificationspop, null);
        mPopupWindowone = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        recyclerview1 = view.findViewById(R.id.recyclerview);
        pic = view.findViewById(R.id.pic);
        price = view.findViewById(R.id.tv_price);
        ImageView jia = view.findViewById(R.id.jia);
        ImageView jian = view.findViewById(R.id.jian);
        num = view.findViewById(R.id.num);
        cy = view.findViewById(R.id.cy);
        cp = view.findViewById(R.id.cp);
        Button tv_sure = view.findViewById(R.id.tv_sure);
        kc = view.findViewById(R.id.kc);
        if (variety != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv_sku.setLayoutManager(layoutManager);
            LinearLayoutManager layoutManagers = new LinearLayoutManager(this);
            layoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerview1.setLayoutManager(layoutManagers);
            Details_Adapter details_adapter = new Details_Adapter(variety, this);
            rv_sku.setAdapter(details_adapter);
            final Details_item_Adapter details_item_adapter = new Details_item_Adapter(this, variety.get(0).getVoo());
            recyclerview1.setAdapter(details_item_adapter);
            //一级列表
            details_adapter.setOnItemClick(new Details_Adapter.OnItemClick() {
                @Override
                public void onItemClick(View view1, int position) {
                    j = position;
                    title = variety.get(position).getTitle();
                    details_item_adapter.setVariety(variety.get(position).getVoo());
                    details_item_adapter.notifyDataSetChanged();
                    //已选为空
                    if (title != null) {
                        cy.setText("已选择：" + title);
                        cp.setText("");
                        num.setText("1");
                    }
                }
            });
            //二级列表
            details_item_adapter.setOnItemClicks(new Details_item_Adapter.OnItemClicks() {

                @Override
                public void onItemClicks(View view2, int i) {
                    fidname = variety.get(j).getVoo().get(i).getFidname();
                    kc.setText("库存" + variety.get(j).getVoo().get(i).getIn_stock() + "棵");
                    if (fidname != null) {
                        cp.setText(fidname);
                    }
                    String prices = variety.get(j).getVoo().get(i).getPrice();
                    String str = "\u00a5".concat(prices);
                    SpannableString spannableString = new SpannableString(str);
                    RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.6f);
                    spannableString.setSpan(relativeSizeSpan, 1, prices.length() - 2,
                            Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    AssetManager mgr = getAssets();
                    Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
                    price.setTypeface(tf);
                    price.setText(spannableString);
                    gg_id = variety.get(j).getVoo().get(i).getId();
                }
            });
            GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + data1.getPic(), pic);
            //加数量
            jia.setOnClickListener(new View.OnClickListener() {
                private int j;

                @Override
                public void onClick(View v) {
                    String s = num.getText().toString();
                    String s1 = kc.getText().toString();
                    int i = Integer.parseInt(s);
                    if (s1 != null) {
                        j = Integer.parseInt(s1);
                    }
                    if (j == 1) {
                        ToastUtil.showShort(DetailsActivity.this, "库存不足");
                    } else {
                        i++;
                        int o = j - 1;
                        String s2 = String.valueOf(i);
                        num.setText(s2);
                        kc.setText(o + "");
                    }
                }
            });
            //减数量
            jian.setOnClickListener(new View.OnClickListener() {
                private int j;

                @Override
                public void onClick(View v) {
                    String s = num.getText().toString();
                    String s1 = kc.getText().toString();
                    int i = Integer.parseInt(s);
                    if (s1 != null) {
                        j = Integer.parseInt(s1);
                    }
                    if (i == 1) {
                        ToastUtil.showShort(DetailsActivity.this, "商品数量最少为1");
                    } else {
                        i--;
                        int o = j + 1;
                        num.setText(i + "");
                        kc.setText(o + "");
                    }
                }
            });
            //加入购物车
            *//*infor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = num.getText().toString();
                    map.clear();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("num", s);
                    Log.d(TAG, "onClickssss: " + s);
                    if (s1 != null) {
                        String substring = s1.substring(0, s1.length() - 1);
                        map.put("maintain", substring);
                        Log.d(TAG, "onClick: " + substring);
                    }
                    if (gg_id != null) {
                        map.put("gg_id", gg_id);
                        ToastUtil.showShort(DetailsActivity.this, gg_id);
                        presenter.getpost(Contacts.add_shopping_carts, headmap, map, AddShoppingBean.class);
                    } else {
                        ToastUtil.showShort(DetailsActivity.this, "请选择规格");
                    }
                }
            });*//*
            //立即购买
            tv_sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!LoginUtil.getInstance().checkLoginStatus(DetailsActivity.this)) {
                        return;
                    }
                    String s = num.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("num", s);
                    //做一个标识type=1的时候是从立即购买进入的
                    bundle.putString("type", "1");
                    if (s1 != null) {
                        String substring = s1.substring(0, s1.length() - 1);
                        bundle.putString("options", substring);
                    } else {
                        bundle.putString("options", "1");
                    }
                    if (gg_id != null) {
                        bundle.putString("id", gg_id);
                        IntentUtils.getIntents().Intent(DetailsActivity.this, BuyActivity.class, bundle);
                        mPopupWindowone.dismiss();
                    } else {
                        ToastUtil.showShort(DetailsActivity.this, "请选择规格");
                    }
                }
            });
        }

        //产生背景变暗效果
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindowone.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        mPopupWindowone.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        mPopupWindowone.setOutsideTouchable(true);
        //设置可以点击
        mPopupWindowone.setTouchable(true);
        //mPopupWindow，指定刚才定义的style
        mPopupWindowone.setAnimationStyle(R.style.mypopwindow_anim_style);

        //设置popupWindow消失时的监听
        mPopupWindowone.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindowone.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }*/


    PopupWindow mPopupWindowtwo;

    private void servicepop() {
        View view = getLayoutInflater().inflate(R.layout.mpopupwindowtwo, null);
        mPopupWindowtwo = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        wbCertExam = view.findViewById(R.id.webview);
        String service = goodBean.getData().getService();
        wbCertExam.loadUrl(Urls.BASEURL + service);
        //web();
        //产生背景变暗效果
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindowtwo.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        mPopupWindowtwo.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        mPopupWindowtwo.setOutsideTouchable(true);
        //设置可以点击
        mPopupWindowtwo.setTouchable(true);
        //mPopupWindow，指定刚才定义的style
        mPopupWindowtwo.setAnimationStyle(R.style.mypopwindow_anim_style);

        //设置popupWindow消失时的监听
        mPopupWindowtwo.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindowtwo.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }


    PopupWindow mPopupWindowthere;

    private void preferentialpop() {
        View view = getLayoutInflater().inflate(R.layout.mpopupwindowthere, null);
        mPopupWindowthere = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView preferential = view.findViewById(R.id.preferential);
       // preferentialAdapter = new PreferentialAdapter(coupon, this);
        if (coupon != null) {
            preferential.setAdapter(preferentialAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            preferential.setLayoutManager(layoutManager);
            //领优惠卷
            preferentialAdapter.setListener(new PreferentialAdapter.onListener() {
                @Override
                public void OnListener(int i) {
                    COUPONSID = i;
                    map.clear();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("id", coupon.get(i).getId());
                    presenter.getpost(Contacts.buy_collects, headmap, map, Zfb_Bean.class);
                }
            });
        }
        //产生背景变暗效果
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindowthere.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        mPopupWindowthere.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        mPopupWindowthere.setOutsideTouchable(true);
        //设置可以点击
        mPopupWindowthere.setTouchable(true);
        //mPopupWindow，指定刚才定义的style
        mPopupWindowthere.setAnimationStyle(R.style.mypopwindow_anim_style);

        //设置popupWindow消失时的监听
        mPopupWindowthere.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindowthere.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void initImgVideoVp() {
        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mMyPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mFragments.get(position) instanceof GoodsVideoFragment) {
                    tv_pager_index.setVisibility(View.GONE);
                    llSlideVideo.setBackgroundResource(R.drawable.radiobutton_background_checked);
                    tvSlideVideo.setText(getString(R.string.video));
                    tvSlideVideo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    tvSlideImage.setText(getString(R.string.pop));
                    tvSlideImage.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
                    tvSlideImage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.c_222222));
                } else if (mFragments.get(position) instanceof GoodsPhotoFragment) {
                    tvSlideImage.setText(getString(R.string.pop));
                    tvSlideImage.setBackgroundResource(R.drawable.radiobutton_background_checked);
                    tvSlideVideo.setText(getString(R.string.video));
                    tvSlideImage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    tvSlideVideo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.c_222222));
                    llSlideVideo.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
                    tv_pager_index.setVisibility(View.VISIBLE);
                    tv_pager_index.setText((position - mTopVideoSum) + "/" + (mFragments.size() - mTopVideoSum - 1));
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 顶部轮播Adapter,资源包括视频、图片
     */
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
