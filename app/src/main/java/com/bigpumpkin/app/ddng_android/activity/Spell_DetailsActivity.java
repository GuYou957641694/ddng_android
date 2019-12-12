package com.bigpumpkin.app.ddng_android.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
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
import com.bigpumpkin.app.ddng_android.adapter.Details_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.Details_item_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.Evaluation_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.PreferentialAdapter;
import com.bigpumpkin.app.ddng_android.adapter.Recommended_GoddAdapter;
import com.bigpumpkin.app.ddng_android.adapter.SingleAdapter;
import com.bigpumpkin.app.ddng_android.adapter.SpaceItemDecoration;
import com.bigpumpkin.app.ddng_android.adapter.Spell_More_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.AddShoppingBean;
import com.bigpumpkin.app.ddng_android.bean.Address_Success_Bean;
import com.bigpumpkin.app.ddng_android.bean.Cancel_collectionsBean;
import com.bigpumpkin.app.ddng_android.bean.Commodity_Recommended_Bean;
import com.bigpumpkin.app.ddng_android.bean.DetailsBean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.fragment.GoodsPhotoFragment;
import com.bigpumpkin.app.ddng_android.fragment.GoodsVideoFragment;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.LoginUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;
import com.bigpumpkin.app.ddng_android.utils.Utils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.FlowPopWindow;
import com.bigpumpkin.app.ddng_android.weight.IdeaScrollView;
import com.bigpumpkin.app.ddng_android.weight.IdeaViewPager;
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
import com.bigpumpkin.app.ddng_android.weight.PickerView;
import com.bumptech.glide.Glide;
import com.hjq.toast.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import qiu.niorgai.StatusBarCompat;

//商品详情
public class Spell_DetailsActivity extends BaseActivity implements MyView, View.OnClickListener {


    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    //网络请求
    private String user, id, sha1, appid, appsecret;
    private long time;
    private DetailsBean.DataBean data1;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mMyPagerAdapter;
    private IdeaViewPager viewPager;
    private RelativeLayout rv_options, rv_specifications;
    //图片+视频
    private TextView tvSlideImage, tv_pager_index, tvSlideVideo;
    private int mTopVideoSum = 0;
    private LinearLayout llSlideVideo, llSlideContains;
    //滑动定位
    private IdeaScrollView ideaScrollView;
    private RadioGroup radioGroup;
    private LinearLayout headerParent;
    private RelativeLayout header, rl, lin, rl_top, shoppcollection;
    private boolean isNeedScrollTo = true;
    private ImageView tv_share, iv_spell_more, collection_icou, back;
    private float currentPercentage = 0;
    //商品信息
    private TextView name, tvprice, tv_spell, tv_service, tv_preferential, tv_full1, tv_full, farm_name, tv_spell_more;
    private int type;
    private RelativeLayout preferential, rv_coupons_gon, service, rl_preferential, father_relative, rl_share;
    private CircleImageView iv_head;
    private Button enter, bt_joincar, bt_buy;
    private List<DetailsBean.DataBean.VarietyBean> variety;
    private List<DetailsBean.DataBean.CouponBean> coupon;
    private DetailsBean.DataBean.FarmDetailsBean farm_details;
    private RecyclerView rv_recommended, rv_evaluation, rv_recommend_you;
    private RecyclerView rv_spell_list;
    private RelativeLayout v_spell;
    private TextView textview, no_evaluation, all_evaluation, maintenance;
    private WebView detailsweb;
    private int mRepaymentDay;
    private TextView tv_choose_time;
    private String price;
    //存放规格
    private StringBuffer stringBuffer = new StringBuffer();
    private String peoplenumber;
    private EditText et_name;
    private RecyclerView rvspell;
    private PopupWindow popspecifcations;
    private TextView tv_num;
    private int in_stock;
    private StringBuilder custom_id;
    private LoadingDialog dialog;
    private String option_adopt;
    private UMShareListener umShareListener;
    private String title;

    @Override
    public int intiLayout() {
        return R.layout.activity_spell__details;
    }

    @Override
    public void initView() {
        //判断有没有登录
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        user = sharedPreferences.getString("zt", "");
        id = getIntent().getStringExtra("id");
        ToastUtil.showShort(this, id);
        father_relative = findViewById(R.id.father_relative);
        viewPager = findViewById(R.id.viewPager);
        tv_pager_index = findViewById(R.id.tv_pager_index);
        llSlideVideo = findViewById(R.id.llSlideVideo);
        llSlideContains = findViewById(R.id.llSlideContains);
        tvSlideVideo = findViewById(R.id.tvSlideVideo);
        tvSlideImage = findViewById(R.id.tvSlideImage);
        radioGroup = findViewById(R.id.radioGroup);
        ideaScrollView = findViewById(R.id.ideaScrollView);
        headerParent = findViewById(R.id.headerParent);
        lin = findViewById(R.id.lin);
        header = findViewById(R.id.header);
        rl = findViewById(R.id.rl);
        rl_top = findViewById(R.id.rl_top);
        tv_share = findViewById(R.id.tv_share);
        tvprice = findViewById(R.id.price);
        name = findViewById(R.id.name);
        maintenance = findViewById(R.id.maintenance);
        rv_options = findViewById(R.id.rv_options);
        rv_specifications = findViewById(R.id.rv_specifications);
        tv_spell = findViewById(R.id.tv_spell);
        tv_service = findViewById(R.id.tv_service);
        tv_preferential = findViewById(R.id.tv_preferential);
        preferential = findViewById(R.id.preferential);
        tv_full1 = findViewById(R.id.tv_full1);
        tv_full = findViewById(R.id.tv_full);
        rv_coupons_gon = findViewById(R.id.rv_coupons_gon);
        iv_head = findViewById(R.id.iv_head);
        farm_name = findViewById(R.id.farm_name);
        enter = findViewById(R.id.enter);
        rv_recommended = findViewById(R.id.rv_recommended);
        service = findViewById(R.id.service);
        rv_spell_list = findViewById(R.id.rv_spell_list);
        v_spell = findViewById(R.id.v_spell);
        rv_evaluation = findViewById(R.id.rv_evaluation);
        textview = findViewById(R.id.textview);
        no_evaluation = findViewById(R.id.no_evaluation);
        all_evaluation = findViewById(R.id.all_evaluation);
        detailsweb = findViewById(R.id.web);
        rv_recommend_you = findViewById(R.id.rv_recommend_you);
        rl_preferential = findViewById(R.id.rl_preferential);
        bt_joincar = findViewById(R.id.bt_joincar);
        bt_buy = findViewById(R.id.bt_buy);
        iv_spell_more = findViewById(R.id.iv_spell_more);
        shoppcollection = findViewById(R.id.shoppcollection);
        collection_icou = findViewById(R.id.collection_icou);
        back = findViewById(R.id.back);
        rl_share = findViewById(R.id.rl_share);
        rl_share.setOnClickListener(this);
        rl_top.setOnClickListener(this);
        back.setOnClickListener(this);
        shoppcollection.setOnClickListener(this);
        iv_spell_more.setOnClickListener(this);
        bt_buy.setOnClickListener(this);
        bt_joincar.setOnClickListener(this);
        rl_preferential.setOnClickListener(this);
        all_evaluation.setOnClickListener(this);
        service.setOnClickListener(this);
        enter.setOnClickListener(this);
        rv_options.setOnClickListener(this);
        tvSlideVideo.setOnClickListener(this);
        tvSlideImage.setOnClickListener(this);
        rv_specifications.setOnClickListener(this);
        rv_recommend_you.setNestedScrollingEnabled(false);
        initSvGr();
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                // 分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(Spell_DetailsActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(Spell_DetailsActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(Spell_DetailsActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };
    }


    @Override
    public void initData() {
        dialog = new LoadingDialog(this, "玩命加载中...");
        dialog.show();
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("id", id);
        if (TextUtils.isEmpty(user.trim())) {
            //没有登录
            presenter.get(Contacts.commodity_lists, headmap, map, DetailsBean.class);
        } else {
            //登录
            time = System.currentTimeMillis();
            appid = SpzUtils.getString("appid");
            appsecret = SpzUtils.getString("appsecret");
            String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
            sha1 = EncryptUtils.getSHA(sha);
            map.put("appid", appid);
            map.put("appsecret", appsecret);
            map.put("timestamp", time);
            map.put("sign", sha1);
            presenter.getpost(Contacts.commodity_lists, headmap, map, DetailsBean.class);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_joincar:
                if (!LoginUtil.getInstance().checkLoginStatu(this)) {
                    return;
                }
                if (type == 3) {
                    //拼单单独购买
                    initSpellLB();
                } else {
                    //普通商品+定制商品
                    initSpecifcations(3);
                }
                break;
            case R.id.bt_buy:
                if (!LoginUtil.getInstance().checkLoginStatu(this)) {
                    return;
                }
                if (type == 3) {
                    //拼单发起拼单
                    initSinglePurchase();
                } else {
                    //普通商品+定制商品
                    initSpecifcations(2);
                }
                break;
            case R.id.tvSlideImage:
                //点击图片
                viewPager.setCurrentItem(1);
                break;
            case R.id.llSlideVideo:
                //点击视频
                viewPager.setCurrentItem(0);
                break;
            case R.id.service:
                //服务
                initService();
                break;
            case R.id.rl_preferential:
                //优惠券
                if (coupon != null) {
                    initPreferential();
                }
                break;
            case R.id.all_evaluation:
                //评价
                IntentUtils.getIntents().Intent(Spell_DetailsActivity.this, All_EvaluationActivity.class, null);
                break;
            case R.id.enter:
                if (farm_details.getId() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", farm_details.getId());
                    IntentUtils.getIntents().Intent(this, FarmActivity.class, bundle);
                }
                break;
            case R.id.rv_options:
                if (!LoginUtil.getInstance().checkLoginStatu(this)) {
                    return;
                }
                //定制
                if (type == 3) {
                    //等于3的时候是拼单
                    return;
                } else {
                    //1是认养
                    initSku();
                }
                break;
            case R.id.rv_specifications:
                if (!LoginUtil.getInstance().checkLoginStatu(this)) {
                    return;
                }
                if (type == 3) {
                    //等于3的时候是拼单
                    initSinglePurchase();
                } else {
                    //普通商品+定制商品
                    initSpecifcations(1);
                }
                break;
            case R.id.iv_spell_more:
            case R.id.tv_spell_more:
                //拼单列表
                initSpellSingle();
                break;
            case R.id.shoppcollection:
                //收藏
                if (!LoginUtil.getInstance().checkLoginStatu(this)) {
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
                if (data1.getCollection().equals("1")) {
                    map.put("vaid", id);
                    presenter.getpost(Contacts.My_cancel_collections, headmap, map, Cancel_collectionsBean.class);
                } else {
                    map.put("id", id);
                    presenter.getpost(Contacts.collections, headmap, map, Address_Success_Bean.class);
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.rl_top:
                ideaScrollView.fullScroll(View.FOCUS_UP);
                break;
            case R.id.rl_share:
                initShape();
                break;
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof DetailsBean) {
            DetailsBean detailsBean = (DetailsBean) data;
            data1 = detailsBean.getData();
            if (data1 != null) {
                //type1植物 2水果 3拼单 展示不同的列表
                type = data1.getType();
                //判断商品是什么类型
                initType();
                //视频 图片
                initHead();
                //商品信息
                initGoods();
                //规格选择
                variety = data1.getVariety();
                //服务
                tv_service.setText(data1.getRefund().getTitle());
                //优惠券
                coupon = data1.getCoupon();
                if (coupon != null) {
                    initCoupon();
                    rl_preferential.setVisibility(View.VISIBLE);
                } else {
                    rl_preferential.setVisibility(View.GONE);
                    tv_preferential.setVisibility(View.GONE);
                }
                //拼单信息
                initSingle();
                //农场信息
                farm_details = data1.getFarm_details();
                if (farm_details.getPic() != null && farm_details.getTitle() != null) {
                    Glide.with(this).load(farm_details.getPic()).into(iv_head);
                    farm_name.setText(farm_details.getTitle());
                }
                //收藏
                String collection = data1.getCollection();
                if (collection.equals("1")) {
                    //收藏
                    Glide.with(this).load(R.drawable.collection_true).into(collection_icou);
                } else {
                    //未收藏
                    Glide.with(this).load(R.drawable.collection_false).into(collection_icou);
                }
                //本店推荐
                initRecommended();
                //拼单
                initSpell();
                //评价
                initEvaluation();
                //\(^o^)/~h5详情
                String details = data1.getDetails();
                String uri = Urls.BASEURL + details;
                detailsweb.loadUrl(uri);
                map.clear();
                map.put("s_id", id);
                presenter.get(Contacts.Commodity_Recommendeds, headmap, map, Commodity_Recommended_Bean.class);
                dialog.close();
                father_relative.setVisibility(View.VISIBLE);
            }
        } else if (data instanceof Commodity_Recommended_Bean) {
            //为你推荐
            Commodity_Recommended_Bean commodity_recommended_bean = (Commodity_Recommended_Bean) data;
            List<Commodity_Recommended_Bean.DataBean> data2 = commodity_recommended_bean.getData();
            Commodity_RecommendedsAdapter commodity_recommendedsAdapter = new Commodity_RecommendedsAdapter(this, data2);
      /*      StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            rv_recommend_you.setLayoutManager(staggeredGridLayoutManager);*/
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            rv_recommend_you.addItemDecoration(new SpaceItemDecoration(Utils.dip2px(this, 10)));
            rv_recommend_you.setLayoutManager(gridLayoutManager);
            rv_recommend_you.setAdapter(commodity_recommendedsAdapter);
        } else if (data instanceof Cancel_collectionsBean) {
            Cancel_collectionsBean cancel_collectionsBean = (Cancel_collectionsBean) data;
            if (cancel_collectionsBean.getCode().equals("200")) {
                ToastUtil.showShort(this, "成功");
                Glide.with(this).load(R.drawable.collection_false).into(collection_icou);
                data1.setCollection("2");
            }
        } else if (data instanceof Address_Success_Bean) {
            Address_Success_Bean address_success_bean = (Address_Success_Bean) data;
            if (address_success_bean.getCode().equals("200")) {
                Toast toast = new Toast(Spell_DetailsActivity.this);
                View view = LayoutInflater.from(Spell_DetailsActivity.this).inflate(R.layout.toast_custom, null);
                toast.setView(view);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Glide.with(this).load(R.drawable.collection_true).into(collection_icou);
                data1.setCollection("1");
            }
        } else if (data instanceof AddShoppingBean) {
            AddShoppingBean addShoppingBean = (AddShoppingBean) data;
            String code = addShoppingBean.getCode();
            if (code.equals("200")) {
                ToastUtils.show("加入成功");
            }
        }
    }

    private void initSingle() {
        List<DetailsBean.DataBean.SingleBean> single = data1.getSingle();
        //判断有没有拼单
        if (single != null && single.size() > 0) {
            rv_spell_list.setVisibility(View.VISIBLE);
            v_spell.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rv_spell_list.setLayoutManager(linearLayoutManager);
            rv_spell_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            SingleAdapter singleAdapter = new SingleAdapter(this, single);
            rv_spell_list.setAdapter(singleAdapter);
            singleAdapter.setListener(new SingleAdapter.onListener() {
                @Override
                public void OnListener(int i) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", single.get(i).getId());
                    bundle.putString("s_id", id);
                    IntentUtils.getIntents().Intent(Spell_DetailsActivity.this, ParticipateSpellActivity.class, bundle);
                }
            });
        } else {
            rv_spell_list.setVisibility(View.GONE);
            v_spell.setVisibility(View.GONE);
        }
    }

    private int oneposition;
    private String gg_id;
    private int sb_length;

    private void initSinglePurchase() {
        //发起拼单
        peoplenumber = "";
        View view = getLayoutInflater().inflate(R.layout.popsinglepurchase_layout, null);
        PopupWindow mPopupWindowthere = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView iv_dismiss = view.findViewById(R.id.iv_dismiss);
        Button tv_sure = view.findViewById(R.id.tv_sure);
        Button tv_independent = view.findViewById(R.id.tv_independent);
        et_name = view.findViewById(R.id.et_name);
        //规格
        RecyclerView sku_one = view.findViewById(R.id.sku_one);
        RecyclerView sku_two = view.findViewById(R.id.sku_two);
        TextView tv_gg = view.findViewById(R.id.tv_gg);
        //价格
        TextView sku_tv_price = view.findViewById(R.id.sku_tv_price);
        TextView tv_spell_price = view.findViewById(R.id.tv_spell_price);
        tv_spell_price.setText("¥" + price);
        Tv_Price_Utils.initPriceTv(this, price, sku_tv_price);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        sku_one.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManagers = new LinearLayoutManager(this);
        layoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
        sku_two.setLayoutManager(layoutManagers);
        Details_Adapter details_adapter = new Details_Adapter(variety, this);
        sku_one.setAdapter(details_adapter);
        Details_item_Adapter details_item_adapter = new Details_item_Adapter(this, variety.get(0).getVoo());
        sku_two.setAdapter(details_item_adapter);
        details_adapter.setOnItemClick(new Details_Adapter.OnItemClick() {
            @Override
            public void onItemClick(View view1, int position) {
                sb_length = stringBuffer.length();
                stringBuffer.delete(0, sb_length);
                oneposition = position;
                if (gg_id != null) {
                    gg_id = null;
                }
                String title = variety.get(position).getTitle();
                stringBuffer.append(title);
                details_item_adapter.setVariety(variety.get(position).getVoo());
                details_item_adapter.notifyDataSetChanged();
                tv_gg.setText(stringBuffer.toString());
            }
        });
        details_item_adapter.setOnItemClicks(new Details_item_Adapter.OnItemClicks() {
            @Override
            public void onItemClicks(View view2, int i) {
                String fidname = variety.get(oneposition).getVoo().get(i).getTitle();
                String gg_price = variety.get(oneposition).getVoo().get(i).getPrice();
                gg_id = variety.get(oneposition).getVoo().get(i).getId();
                tv_gg.setText(stringBuffer.toString() + " " + fidname);
                tv_spell_price.setText("¥" + gg_price);
                Tv_Price_Utils.initPriceTv(Spell_DetailsActivity.this, gg_price, sku_tv_price);
            }
        });
        //公开拼单
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(user.trim())) {
                    //没有登录
                    return;
                }
                if (gg_id != null && et_name.getText().toString().length() <= 6 && tv_choose_time.getText().toString() != "选择时间" && peoplenumber.length() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("gg_id", gg_id);
                    bundle.putString("group", et_name.getText().toString());
                    bundle.putString("choose_time", tv_choose_time.getText().toString());
                    bundle.putString("peoplenumber", peoplenumber);
                    //公开是1  私密是2
                    bundle.putString("spell", "1");
                    bundle.putString("type", "4");
                    IntentUtils.getIntents().Intent(Spell_DetailsActivity.this, SpellBuyActivity.class, bundle);
                    mPopupWindowthere.dismiss();
                } else {
                    ToastUtils.show("请选择完整");
                }
            }
        });
        //自主拼单
        tv_independent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(user.trim())) {
                    //没有登录
                    return;
                }
                if (gg_id != null && et_name.getText().toString().length() <= 6 && tv_choose_time.getText().toString() != "选择时间" && peoplenumber.length() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("gg_id", gg_id);
                    bundle.putString("group", et_name.getText().toString());
                    bundle.putString("choose_time", tv_choose_time.getText().toString());
                    bundle.putString("peoplenumber", peoplenumber);
                    //标识是拼单
                    bundle.putString("spell", "2");
                    bundle.putString("type", "4");
                    IntentUtils.getIntents().Intent(Spell_DetailsActivity.this, SpellBuyActivity.class, bundle);
                    mPopupWindowthere.dismiss();
                } else {
                    ToastUtils.show("请选择完整");
                }
            }
        });
        //选择时间
        tv_choose_time = view.findViewById(R.id.tv_choose_time);
        tv_choose_time.setOnClickListener(this);
        ImageView sku_pic = view.findViewById(R.id.sku_pic);
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + data1.getPic(), sku_pic);
        RadioGroup rg = view.findViewById(R.id.rg);
        RadioButton cb_one = view.findViewById(R.id.cb_one);
        RadioButton cb_two = view.findViewById(R.id.cb_two);
        RadioButton cb_frou = view.findViewById(R.id.cb_frou);
        RadioButton cb_there = view.findViewById(R.id.cb_there);
        RadioButton cb_five = view.findViewById(R.id.cb_five);
        //选择人数
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cb_one:
                        peoplenumber = "2";
                        cb_one.setChecked(true);
                        cb_two.setChecked(false);
                        cb_frou.setChecked(false);
                        cb_there.setChecked(false);
                        cb_five.setChecked(false);
                        break;
                    case R.id.cb_two:
                        peoplenumber = "3";
                        cb_one.setChecked(false);
                        cb_two.setChecked(true);
                        cb_frou.setChecked(false);
                        cb_there.setChecked(false);
                        cb_five.setChecked(false);
                        break;
                    case R.id.cb_frou:
                        peoplenumber = "4";
                        cb_one.setChecked(false);
                        cb_two.setChecked(false);
                        cb_frou.setChecked(true);
                        cb_there.setChecked(false);
                        cb_five.setChecked(false);
                        break;
                    case R.id.cb_there:
                        peoplenumber = "5";
                        cb_one.setChecked(false);
                        cb_two.setChecked(false);
                        cb_frou.setChecked(false);
                        cb_there.setChecked(true);
                        cb_five.setChecked(false);
                        break;
                    case R.id.cb_five:
                        peoplenumber = "6";
                        cb_one.setChecked(false);
                        cb_two.setChecked(false);
                        cb_frou.setChecked(false);
                        cb_there.setChecked(false);
                        cb_five.setChecked(true);
                        break;
                }
            }
        });
        tv_choose_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initChooseTime();
            }
        });
        iv_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindowthere.dismiss();
            }
        });
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

    private void initSku() {
        //选项常规、定制
        List<DetailsBean.DataBean.OptionsBean> options = data1.getOptions();
        if (options != null) {
            FlowPopWindow flowPopWindow = new FlowPopWindow(Spell_DetailsActivity.this, options);
            flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
                @Override
                public void onConfirmClick() {
                    new StringBuilder();
                    custom_id = new StringBuilder();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (DetailsBean.DataBean.OptionsBean fb : options) {
                        List<DetailsBean.DataBean.OptionsBean.CustomlistBean> cdList = fb.getCustomlist();
                        if (cdList != null) {
                            for (int x = 0; x < cdList.size(); x++) {
                                DetailsBean.DataBean.OptionsBean.CustomlistBean children = cdList.get(x);
                                if (children.isSelected()) {
                                    custom_id.append(children.getId() + ",");
                                    //选中规格的id需要传入后台
                                    String name = children.getTitle();
                                    //规格名展示
                                    stringBuilder.append(name + "、");
                                }
                            }
                        }
                    }
                    String sku = stringBuilder.toString();
                    option_adopt = sku.substring(0, sku.length() - 1);
                    maintenance.setText(option_adopt);
                }
            });
            flowPopWindow.setListener(new FlowPopWindow.onListener() {
                @Override
                public void OnListener() {
                    maintenance.setText("常规维护");
                }
            });
        }
    }


    private void initChooseTime() {
        mRepaymentDay = 1;
        final Dialog dialog = new Dialog(this, R.style.dialog_fullscreen);
        dialog.setContentView(R.layout.repayment_day_dialog_layout);
        dialog.setCanceledOnTouchOutside(true);
        TextView closeBtn = (TextView) dialog.findViewById(R.id.closeBtn);
        TextView okBtn = (TextView) dialog.findViewById(R.id.okBtn);
        PickerView minute_pv = (PickerView) dialog.findViewById(R.id.minute_pv);
        List<String> data = new ArrayList<>();
        for (int i = 1; i < 25; i++) {
            data.add(i + "小时");
        }
        minute_pv.setData(data);
        final int[] selectedDay = new int[1];

        if (mRepaymentDay <= 0) {
            minute_pv.setSelected(1);
        } else {
            minute_pv.setSelected(mRepaymentDay - 1);
        }

        minute_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(int date) {
                selectedDay[0] = date;
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                tv_choose_time.setText("选择时间");
                mRepaymentDay = 1;
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (selectedDay[0] == 0) {
                    if (mRepaymentDay == 1) {
                        tv_choose_time.setText(getDate(1));
                        mRepaymentDay = 1;
                    }
                } else {
                    tv_choose_time.setText(getDate(selectedDay[0]));
                    mRepaymentDay = selectedDay[0];
                }
            }
        });
        dialog.show();
    }

    public String getDate(int date) {
        if (date < 26) {
            return String.format(getString(R.string.day_every_month), date);
        } else {
            return String.format(getString(R.string.day_every_month), 1);
        }
    }

    private void initSpellSingle() {
        //订单列表
        View view = getLayoutInflater().inflate(R.layout.spellsinglelayout, null);
        PopupWindow mPopupWindowthere = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rvspell = view.findViewById(R.id.rv_spell);
        ImageView ivdismiss = view.findViewById(R.id.iv_dismiss);
        List<DetailsBean.DataBean.SingleBean> single = data1.getSingle();
        if (single != null && single.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rvspell.setLayoutManager(linearLayoutManager);
            Spell_More_Adapter spell_more_adapter = new Spell_More_Adapter(this, single);
            rvspell.setAdapter(spell_more_adapter);
            spell_more_adapter.setListener(new Spell_More_Adapter.onListener() {
                @Override
                public void OnListener(int i) {
                    if (!LoginUtil.getInstance().checkLoginStatu(Spell_DetailsActivity.this)) {
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("id", single.get(i).getId());
                    bundle.putString("s_id", id);
                    bundle.putString("s_num", single.get(i).getS_num());
                    IntentUtils.getIntents().Intent(Spell_DetailsActivity.this, ParticipateSpellActivity.class, bundle);
                }
            });
        }
        ivdismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindowthere.dismiss();
            }
        });
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

    private void initPreferential() {
        //优惠券
        View view = getLayoutInflater().inflate(R.layout.mpopupwindowthere, null);
        PopupWindow mPopupWindowthere = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView preferential = view.findViewById(R.id.preferential);
        PreferentialAdapter preferentialAdapter = new PreferentialAdapter(coupon, this);
        if (coupon != null) {
            preferential.setAdapter(preferentialAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            preferential.setLayoutManager(layoutManager);
            //领优惠卷
            preferentialAdapter.setListener(new PreferentialAdapter.onListener() {
                @Override
                public void OnListener(int i) {
                    if (!LoginUtil.getInstance().checkLoginStatu(Spell_DetailsActivity.this)) {
                        return;
                    }
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

    private void initEvaluation() {
        List<DetailsBean.DataBean.EvaluationBean.LabelBean> label = data1.getEvaluation().getLabel();
        if (data1.getEvaluation().getLabel().get(0).getId().equals("0")) {
            rv_evaluation.setVisibility(View.GONE);
            textview.setText("宝贝评价" + "(" + data1.getEvaluation().getNum() + ")");
            no_evaluation.setText("暂无评价");
            no_evaluation.setVisibility(View.VISIBLE);
        } else {
            textview.setText("宝贝评价" + "(" + data1.getEvaluation().getNum() + ")");
            rv_evaluation.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            Evaluation_Adapter evaluation_adapter = new Evaluation_Adapter(label, this);
            rv_evaluation.setAdapter(evaluation_adapter);
            rv_evaluation.setVisibility(View.VISIBLE);
            all_evaluation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.getIntents().Intent(Spell_DetailsActivity.this, All_EvaluationActivity.class, null);
                }
            });
        }
    }

    private String spgg_id, v_id;
    private int onepositions;

    private void initSpellLB() {
        //拼单单独购买
        View view = getLayoutInflater().inflate(R.layout.poppublic_layout, null);
        PopupWindow mPopupWindowthere = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button tv_sure = view.findViewById(R.id.tv_sure);
        ImageView iv_dismiss = view.findViewById(R.id.iv_dismiss);
        //减数量
        RelativeLayout rl_reduction = view.findViewById(R.id.rl_reduction);
        //加数量
        RelativeLayout rl_add = view.findViewById(R.id.rl_add);
        //数量
        TextView tv_num = view.findViewById(R.id.tv_num);
        //库存
        TextView tv_kc = view.findViewById(R.id.tv_kc);
        iv_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindowthere.dismiss();
            }
        });
        //规格
        RecyclerView sku_one = view.findViewById(R.id.sku_one);
        RecyclerView sku_two = view.findViewById(R.id.sku_two);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        sku_one.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManagers = new LinearLayoutManager(this);
        layoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
        sku_two.setLayoutManager(layoutManagers);
        Details_Adapter details_adapter = new Details_Adapter(variety, this);
        sku_one.setAdapter(details_adapter);
        Details_item_Adapter details_item_adapter = new Details_item_Adapter(this, variety.get(0).getVoo());
        sku_two.setAdapter(details_item_adapter);
        //默认第一个库存
        in_stock = variety.get(onepositions).getVoo().get(secondary).getIn_stock();
        tv_kc.setText("库存" + variety.get(0).getVoo().get(0).getIn_stock());
        //价格
        ImageView sku_pic = view.findViewById(R.id.sku_pic);
        TextView sku_tv_price = view.findViewById(R.id.sku_tv_price);
        TextView tv_gg = view.findViewById(R.id.tv_gg);
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + data1.getPic(), sku_pic);
        Tv_Price_Utils.initPriceTv(this, price, sku_tv_price);

        details_adapter.setOnItemClick(new Details_Adapter.OnItemClick() {
            @Override
            public void onItemClick(View view1, int position) {
                int length = stringBuffer.length();
                stringBuffer.delete(0, length);
                onepositions = position;
                if (spgg_id != null) {
                    spgg_id = null;
                }
                v_id = variety.get(position).getId();
                String title = variety.get(position).getTitle();
                stringBuffer.append(title);
                details_item_adapter.setVariety(variety.get(position).getVoo());
                details_item_adapter.notifyDataSetChanged();
                tv_gg.setText(stringBuffer.toString());
                tv_kc.setText("库存" + variety.get(position).getVoo().get(0).getIn_stock());
            }
        });
        details_item_adapter.setOnItemClicks(new Details_item_Adapter.OnItemClicks() {
            @Override
            public void onItemClicks(View view2, int i) {
                String fidname = variety.get(onepositions).getVoo().get(i).getTitle();
                String gg_price = variety.get(onepositions).getVoo().get(i).getPrice();
                spgg_id = variety.get(onepositions).getVoo().get(i).getId();
                tv_gg.setText(stringBuffer.toString() + " " + fidname);
                sku_tv_price.setText("¥" + gg_price);
                Tv_Price_Utils.initPriceTv(Spell_DetailsActivity.this, gg_price, sku_tv_price);
                tv_kc.setText("库存" + variety.get(onepositions).getVoo().get(i).getIn_stock());
                in_stock = variety.get(onepositions).getVoo().get(secondary).getIn_stock();
            }
        });

        rl_reduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvNum = tv_num.getText().toString();
                if (tvNum.equals("1")) {
                    ToastUtil.showShort(Spell_DetailsActivity.this, "最低数量为1");
                } else {
                    String tvNums = tv_num.getText().toString();
                    int tv = Integer.parseInt(tvNums);
                    int i = --tv;
                    tv_num.setText(i + "");
                    tv_kc.setText("库存" + ++in_stock + "");
                }
            }
        });
        rl_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvNum = tv_num.getText().toString();
                if (tvNum.equals(in_stock)) {
                    ToastUtil.showShort(Spell_DetailsActivity.this, "数量超出范围");
                } else {
                    int tv = Integer.parseInt(tvNum);
                    int i = ++tv;
                    tv_num.setText(i + "");
                    tv_kc.setText("库存" + --in_stock + "");
                }
            }
        });


        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转确认订单页面
                String num = tv_num.getText().toString();
                if (id != null && spgg_id != null && spgg_id.length() > 0 && v_id.length() > 0 && v_id != null && num != null) {
                    Bundle bundle = new Bundle();
                    //规格id
                    bundle.putString("id", id);
                    bundle.putString("gg_id", spgg_id);
                    //品种id
                    bundle.putString("v_id", v_id);
                    bundle.putString("num", num);
                    Log.d(TAG, "onClick: " + spgg_id + "v_id" + v_id);
                    IntentUtils.getIntents().Intent(Spell_DetailsActivity.this, NewBuyNowActivity.class, bundle);
                    mPopupWindowthere.dismiss();
                } else {
                    ToastUtils.show("请选择完整！");
                }
            }
        });

        //产生背景变暗效果
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindowthere.setFocusable(true);// 取得焦点
        mPopupWindowthere.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindowthere.setOutsideTouchable(true);
        mPopupWindowthere.setTouchable(true);
        mPopupWindowthere.setAnimationStyle(R.style.mypopwindow_anim_style);

        mPopupWindowthere.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
                //关闭的时候要清空品种id 和规格id
                spgg_id = "";
                v_id = "";
            }
        });
        mPopupWindowthere.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void initRecommended() {
        List<DetailsBean.DataBean.RecommendBean> recommend = data1.getRecommend();
        if (recommend != null && recommend.size() > 0) {
            Recommended_GoddAdapter recommended_goddAdapter = new Recommended_GoddAdapter(this, recommend);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            rv_recommended.setLayoutManager(gridLayoutManager);
            rv_recommended.addItemDecoration(new SpaceItemDecoration(15));
            rv_recommended.setAdapter(recommended_goddAdapter);
        }
    }

    private void initCoupon() {
        if (coupon != null && coupon.size() > 0) {
            if (coupon.size() > 1) {
                tv_full.setText("满" + coupon.get(0).getMan() + "减" + coupon.get(0).getJian());
                tv_full1.setText("满" + coupon.get(1).getMan() + "减" + coupon.get(1).getJian());
            } else {
                tv_full.setText("满" + coupon.get(1).getMan() + "减" + coupon.get(1).getJian());
                rv_coupons_gon.setVisibility(View.GONE);
            }
        } else {
            preferential.setVisibility(View.GONE);
            tv_preferential.setVisibility(View.GONE);
        }
    }


    int secondary;

    private void initSpecifcations(int type) {
        //普通商品选规格
        View view = getLayoutInflater().inflate(R.layout.specificationspop, null);
        popspecifcations = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView tvdismiss = view.findViewById(R.id.iv_dismiss);
        TextView prices = view.findViewById(R.id.sku_tv_price);
        TextView sku_tv_price = view.findViewById(R.id.sku_tv_price);
        tv_num = view.findViewById(R.id.num);
        Button tv_sure = view.findViewById(R.id.tv_sure);
        Button tv_independent = view.findViewById(R.id.tv_independent);
        Button tv_determine = view.findViewById(R.id.tv_determine);
        if (type == 1) {
            tv_sure.setVisibility(View.VISIBLE);
            tv_independent.setVisibility(View.VISIBLE);
            tv_determine.setVisibility(View.GONE);
        } else {
            tv_sure.setVisibility(View.GONE);
            tv_independent.setVisibility(View.GONE);
            tv_determine.setVisibility(View.VISIBLE);
        }
        TextView cy = view.findViewById(R.id.cy);
        TextView kc = view.findViewById(R.id.kc);
        ImageView pic = view.findViewById(R.id.sku_pic);
        RecyclerView sku_one = view.findViewById(R.id.sku_one);
        RecyclerView sku_two = view.findViewById(R.id.sku_two);
        RelativeLayout jian = view.findViewById(R.id.jian);
        RelativeLayout jia = view.findViewById(R.id.jia);
        GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + data1.getPic(), pic);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        sku_one.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManagers = new LinearLayoutManager(this);
        layoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
        sku_two.setLayoutManager(layoutManagers);
        Details_Adapter details_adapter = new Details_Adapter(variety, this);
        sku_one.setAdapter(details_adapter);
        Details_item_Adapter details_item_adapter = new Details_item_Adapter(this, variety.get(0).getVoo());
        sku_two.setAdapter(details_item_adapter);
        Tv_Price_Utils.initPriceTv(this, price, prices);
        in_stock = variety.get(onepositions).getVoo().get(secondary).getIn_stock();
        //默认第一个库存
        kc.setText("库存" + variety.get(0).getVoo().get(0).getIn_stock());
        //一级
        details_adapter.setOnItemClick(new Details_Adapter.OnItemClick() {
            @Override
            public void onItemClick(View view1, int position) {
                int length = stringBuffer.length();
                stringBuffer.delete(0, length);
                onepositions = position;
                if (spgg_id != null) {
                    spgg_id = null;
                }
                String title = variety.get(position).getTitle();
                v_id = variety.get(position).getId();
                stringBuffer.append(title);
                details_item_adapter.setVariety(variety.get(position).getVoo());
                details_item_adapter.notifyDataSetChanged();
                cy.setText(stringBuffer.toString());
                kc.setText("库存" + variety.get(position).getVoo().get(0).getIn_stock());
            }
        });
        //二级
        details_item_adapter.setOnItemClicks(new Details_item_Adapter.OnItemClicks() {
            @Override
            public void onItemClicks(View view2, int i) {
                secondary = i;
                String fidname = variety.get(onepositions).getVoo().get(i).getTitle();
                String gg_price = variety.get(onepositions).getVoo().get(i).getPrice();
                spgg_id = variety.get(onepositions).getVoo().get(i).getId();
                cy.setText(stringBuffer.toString() + " " + fidname);
                sku_tv_price.setText("¥" + gg_price);
                Tv_Price_Utils.initPriceTv(Spell_DetailsActivity.this, gg_price, sku_tv_price);
                kc.setText("库存" + variety.get(onepositions).getVoo().get(i).getIn_stock());
                in_stock = variety.get(onepositions).getVoo().get(secondary).getIn_stock();
                String tvNum = tv_num.getText().toString();
                double one = Double.parseDouble(tvNum);
                double two = in_stock;
                double there = two - one;
                String str = String.valueOf(there);
                kc.setText("库存" + str.substring(0, str.length() - 2) + "");
            }
        });

        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvNum = tv_num.getText().toString();
                if (tvNum.equals("1")) {
                    ToastUtil.showShort(Spell_DetailsActivity.this, "最低数量为1");
                } else {
                    String tvNums = tv_num.getText().toString();
                    int tv = Integer.parseInt(tvNums);
                    int i = --tv;
                    tv_num.setText(i + "");
                    kc.setText("库存" + ++in_stock + "");
                }
            }
        });
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvNum = tv_num.getText().toString();
                if (tvNum.equals(in_stock)) {
                    ToastUtil.showShort(Spell_DetailsActivity.this, "数量超出范围");
                } else {
                    int tv = Integer.parseInt(tvNum);
                    int i = ++tv;
                    tv_num.setText(i + "");
                    kc.setText("库存" + --in_stock + "");
                }
            }
        });
        //加入购物车
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = tv_num.getText().toString();
                if (num != null && spgg_id != null) {
                    map.clear();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("num", num);
                    map.put("gg_id", spgg_id);
                    presenter.getpost(Contacts.add_shopping_carts, headmap, map, AddShoppingBean.class);
                    popspecifcations.dismiss();
                } else {
                    ToastUtil.showShort(Spell_DetailsActivity.this, "请选择规格");
                }
            }
        });

        //立即购买
        tv_independent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String custom = maintenance.getText().toString();
                String num = tv_num.getText().toString();
                if (num != null && spgg_id != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("v_id", v_id);
                    bundle.putString("gg_id", spgg_id);
                    bundle.putString("num", num);
                    if (custom != "常规维护") {
                        //用于区分是普通商品还是认养
                        String customid = String.valueOf(custom_id);
                        String substring = customid.substring(0, customid.length() - 1);
                        //选项id
                        bundle.putString("maintain", substring);
                        //文字
                        bundle.putString("optionAdopt", option_adopt);
                    }
                    IntentUtils.getIntents().Intent(Spell_DetailsActivity.this, NewBuyNowActivity.class, bundle);
                    popspecifcations.dismiss();
                } else {
                    ToastUtils.show("请选择规格");
                }
            }
        });

        tv_determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是什么用途  是进入购物车 还是立即购买
                if (type == 2) {
                    //立即购买的确认按钮
                    String custom = maintenance.getText().toString();
                    String num = tv_num.getText().toString();
                    if (num != null && spgg_id != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id);
                        bundle.putString("v_id", v_id);
                        bundle.putString("gg_id", spgg_id);
                        bundle.putString("num", num);
                        if (custom != "常规维护") {
                            String customid = String.valueOf(custom_id);
                            String substring = customid.substring(0, customid.length() - 1);
                            bundle.putString("maintain", substring);
                            //文字
                            bundle.putString("optionAdopt", option_adopt);
                        }
                        IntentUtils.getIntents().Intent(Spell_DetailsActivity.this, NewBuyNowActivity.class, bundle);
                        popspecifcations.dismiss();
                    } else {
                        ToastUtils.show("请选择规格");
                    }
                } else if (type == 3) {
                    //加入购物车的确认按钮
                    String num = tv_num.getText().toString();
                    if (num != null && spgg_id != null) {
                        map.clear();
                        map.put("appid", appid);
                        map.put("appsecret", appsecret);
                        map.put("timestamp", time);
                        map.put("sign", sha1);
                        map.put("num", num);
                        map.put("gg_id", spgg_id);
                        presenter.getpost(Contacts.add_shopping_carts, headmap, map, AddShoppingBean.class);
                        popspecifcations.dismiss();
                    } else {
                        ToastUtils.show("请选择规格");
                    }
                }
            }
        });
        tvdismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popspecifcations.dismiss();
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        popspecifcations.setFocusable(true);
        popspecifcations.setBackgroundDrawable(new BitmapDrawable());
        popspecifcations.setOutsideTouchable(true);
        popspecifcations.setTouchable(true);
        popspecifcations.setAnimationStyle(R.style.mypopwindow_anim_style);
        popspecifcations.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        popspecifcations.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void initSpell() {

    }

    private void initService() {
        //服务
        View view = getLayoutInflater().inflate(R.layout.mpopupwindowtwo, null);
        PopupWindow mPopupWindowtwo = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        WebView wbCertExam = view.findViewById(R.id.webview);
        String service = data1.getService();
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

    private void initGoods() {
        title = data1.getTitle();
        price = data1.getPrice();
        if (title != null && price != null) {
            name.setText(data1.getTitle());
            Tv_Price_Utils.initPriceTv(this, price, tvprice);
        }
    }

    private void initHead() {
        String mp4 = data1.getMp4();
        //判断是否有视频 false后台反
        if (mp4.equals("false")) {
            //没有视频
            for (int i = 0; i < data1.getWheel().size(); i++) {
                if (data1.getWheel().get(i).getPic() != null) {
                    mFragments.add(GoodsPhotoFragment.getInstance(data1.getWheel().get(i).getPic()));
                }
            }
            initImgVp();
            mMyPagerAdapter.notifyDataSetChanged();
        } else {
            //视频+图片
            if (mp4 != null && data1.getPic() != null) {
                //缩略图+map4的链接
                mFragments.add(GoodsVideoFragment.getInstance(data1.getPic(), mp4));
                for (int i = 0; i < data1.getWheel().size(); i++) {
                    if (data1.getWheel().get(i).getPic() != null) {
                        mFragments.add(GoodsPhotoFragment.getInstance(data1.getWheel().get(i).getPic()));
                    }
                }
                //设置适配器
                initImgVideoVp();
                mMyPagerAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initImgVp() {
        llSlideContains.setVisibility(View.GONE);
        tv_pager_index.setVisibility(View.VISIBLE);
        //这里直接调用适配器 因为没视频不需要判断
        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mMyPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_pager_index.setText((position) + "/" + mFragments.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initImgVideoVp() {
        llSlideContains.setVisibility(View.VISIBLE);
        //这里是一个内部类MyPagerAdapter
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

    private void initType() {
        //type 1植物认养 2普通 3拼单 展示不同的列表
        if (type == 1) {
            //植物
            rv_options.setVisibility(View.VISIBLE);
            tv_spell.setVisibility(View.GONE);
            bt_joincar.setText("加入购物车");
            bt_buy.setText("立即购买");
            maintenance.setText("常规维护");
        } else if (type == 2) {
            //普通订单
            rv_options.setVisibility(View.GONE);
            bt_joincar.setText("加入购物车");
            bt_buy.setText("立即购买");
        } else if (type == 3) {
            //拼单
            tv_spell.setVisibility(View.VISIBLE);
            rv_options.setVisibility(View.VISIBLE);
            bt_joincar.setText("单独购买");
            bt_buy.setText("发起拼单");
            maintenance.setText("常规维护");
        }
    }

    @Override
    public void error(String error) {

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

    @TargetApi(Build.VERSION_CODES.M)
    private void initSvGr() {
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
    }


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

    public int getRadioCheckedAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0x44, 0x44, 0x44);
    }

    public int getRadioAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0xFF, 0xFF, 0xFF);
    }


    private void initShape() {
        View view = getLayoutInflater().inflate(R.layout.shape_item, null);
        PopupWindow mPopupWindowtwo = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
        new ShareAction(Spell_DetailsActivity.this).setPlatform(SHARE_MEDIA.WEIXIN).withMedia(getUMSharerMedia()).setCallback(umShareListener).share();
    }

    private UMWeb getUMSharerMedia() {
        String pic = data1.getWheel().get(1).getPic();
        UMImage image = new UMImage(this, pic);
        final UMWeb web = new UMWeb("http://weilailingdi.weilailingdi.com/Web/Mall_index/xyzz/share/details.html?id=" + id);
        web.setTitle("商品");
        web.setThumb(image);
        web.setDescription(title);
        return web;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


}
