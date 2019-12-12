package com.bigpumpkin.app.ddng_android.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.AnswerActivity;
import com.bigpumpkin.app.ddng_android.activity.CollectionActivity;
import com.bigpumpkin.app.ddng_android.activity.CouponsActivity;
import com.bigpumpkin.app.ddng_android.activity.CustomerActivity;
import com.bigpumpkin.app.ddng_android.activity.Farm_inActivity;
import com.bigpumpkin.app.ddng_android.activity.FocusActivity;
import com.bigpumpkin.app.ddng_android.activity.FootprintActivity;
import com.bigpumpkin.app.ddng_android.activity.GrowActivity;
import com.bigpumpkin.app.ddng_android.activity.IntegralActivity;
import com.bigpumpkin.app.ddng_android.activity.JoinUsActivity;
import com.bigpumpkin.app.ddng_android.activity.Management_addressActivity;
import com.bigpumpkin.app.ddng_android.activity.MessageActivity;
import com.bigpumpkin.app.ddng_android.activity.OrdersActivity;
import com.bigpumpkin.app.ddng_android.activity.PromotionsActivity;
import com.bigpumpkin.app.ddng_android.activity.ReativeActivity;
import com.bigpumpkin.app.ddng_android.activity.RechargeActivity;
import com.bigpumpkin.app.ddng_android.activity.SetActivity;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.LoginUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtilUser;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class
My_Fragment extends BaseFragment {
    private static final String TAG = "My_Fragment";
    @BindView(R.id.setting)
    ImageView setting;
    @BindView(R.id.all_orders)
    TextView allOrders;
    @BindView(R.id.obligation)
    LinearLayout obligation;
    Unbinder unbinder;
    @BindView(R.id.receiving)
    LinearLayout receiving;
    @BindView(R.id.evaluated)
    LinearLayout evaluated;
    @BindView(R.id.after)
    LinearLayout after;
    @BindView(R.id.linera)
    LinearLayout linera;
    @BindView(R.id.recharge)
    LinearLayout recharge;
    @BindView(R.id.message)
    LinearLayout message;
    @BindView(R.id.focus)
    LinearLayout focus;
    @BindView(R.id.collection)
    LinearLayout collection;
    @BindView(R.id.footprint)
    LinearLayout footprint;
    @BindView(R.id.farm_in)
    RelativeLayout farmIn;
    /*@BindView(R.id.integrals)
    LinearLayout integrals;*/
    @BindView(R.id.promotions)
    LinearLayout promotions;
    @BindView(R.id.integral)
    LinearLayout integral;
    @BindView(R.id.coupons)
    LinearLayout coupons;
    @BindView(R.id.answer)
    LinearLayout answer;
    @BindView(R.id.customer)
    LinearLayout customer;
    @BindView(R.id.grow)
    RelativeLayout grow;
    @BindView(R.id.reative)
    RelativeLayout reative;
    @BindView(R.id.set)
    RelativeLayout set;
    @BindView(R.id.farm)
    TextView farm;
    @BindView(R.id.information)
    RelativeLayout information;
    @BindView(R.id.not_log)
    TextView notLog;
    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.name)
    TextView names;
    @BindView(R.id.myscrollviews)
    NestedScrollView myscrollviews;
    @BindView(R.id.relativelayout)
    RelativeLayout relativelayout;
    @BindView(R.id.rv_hind)
    RelativeLayout rvHind;
    @BindView(R.id.tv_hind_name)
    TextView tvHindName;
    @BindView(R.id.lin_address)
    LinearLayout linAddress;
    @BindView(R.id.rl_join)
    RelativeLayout rlJoin;
    private String name;
    private int height;


    @Override
    protected int getLayoutId() {
        return R.layout.my_fragment;
    }

    @Override
    protected void init(View view) {
        setStatusBarTransparent();
        if (!LoginUtil.getInstance().checkLoginStatuss(getActivity())) {
            notLog.setVisibility(View.VISIBLE);
        } else {
            information.setVisibility(View.VISIBLE);
            notLog.setVisibility(View.GONE);
            name = SpzUtilUser.getString("name");
            String zt = SpzUtilUser.getString("zt");
            String pic = SpzUtilUser.getString("pic");
            if (EmptyUtils.isNotEmpty(name) && EmptyUtils.isNotEmpty(zt) && EmptyUtils.isNotEmpty(pic)) {
                names.setText(name);
                //tvHindName.setText(name);
                Glide.with(this).load(pic).into(head);
            }
        }

        ViewTreeObserver vto = rvHind.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                rvHind.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = rvHind.getHeight();

            }
        });

    }

    @Override
    protected void loadData() {

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

    @OnClick({R.id.setting, R.id.lin_address, R.id.all_orders, R.id.obligation, R.id.rl_join, R.id.receiving, R.id.evaluated, R.id.after, R.id.linera, R.id.recharge, R.id.message, R.id.focus, R.id.collection, R.id.footprint, R.id.farm_in, R.id.promotions, R.id.integral, R.id.coupons, R.id.answer, R.id.customer, R.id.grow, R.id.reative, R.id.set, R.id.farm})
    public void onViewClicked(View view) {
        if (!LoginUtil.getInstance().checkLoginStatus(getActivity())) {
            return;
        }
        switch (view.getId()) {
            case R.id.setting:
                //设置
                //IntentUtils.getIntents().Intent(getActivity(), SettingActivity.class, null);
                IntentUtils.getIntents().Intent(getActivity(), SetActivity.class, null);
                break;
            case R.id.all_orders:
                //全部订单
                Bundle all_orders = new Bundle();
                all_orders.putInt("key", 0);
                IntentUtils.getIntents().Intent(getActivity(), OrdersActivity.class, all_orders);
                break;
            case R.id.obligation:
                //待付款
                Bundle obligation = new Bundle();
                obligation.putInt("key", 1);
                IntentUtils.getIntents().Intent(getActivity(), OrdersActivity.class, obligation);
                break;
            case R.id.receiving:
                //待收货
                Bundle receiving = new Bundle();
                receiving.putInt("key", 2);
                IntentUtils.getIntents().Intent(getActivity(), OrdersActivity.class, receiving);
                break;
            case R.id.evaluated:
                //待评价
                Bundle evaluated = new Bundle();
                evaluated.putInt("key", 3);
                IntentUtils.getIntents().Intent(getActivity(), OrdersActivity.class, evaluated);
                break;
            case R.id.after:
                //售后
                Bundle after = new Bundle();
                after.putInt("key", 4);
                IntentUtils.getIntents().Intent(getActivity(), OrdersActivity.class, after);
                break;
            case R.id.linera:
                //生长中
                IntentUtils.getIntents().Intent(getActivity(), OrdersActivity.class, null);
                break;
            case R.id.recharge:
                //充值中心
                IntentUtils.getIntents().Intent(getActivity(), RechargeActivity.class, null);
                break;
            case R.id.message:
                //意见反馈
                IntentUtils.getIntents().Intent(getActivity(), MessageActivity.class, null);
                break;
            case R.id.focus:
                //我的关注
                IntentUtils.getIntents().Intent(getActivity(), FocusActivity.class, null);
                break;
            case R.id.collection:
                //收藏
                IntentUtils.getIntents().Intent(getActivity(), CollectionActivity.class, null);
                break;
            case R.id.footprint:
                //足迹
                IntentUtils.getIntents().Intent(getActivity(), FootprintActivity.class, null);
                break;
            case R.id.farm_in:
                //农场入驻
                IntentUtils.getIntents().Intent(getActivity(), Farm_inActivity.class, null);
                break;
            //积分
            // IntentUtils.getIntents().Intent(getActivity(), IntegralsActivity.class, null);
            case R.id.promotions:
                //去升官PromotionsActivity
                IntentUtils.getIntents().Intent(getActivity(), PromotionsActivity.class, null);
                break;
            case R.id.integral:
                //我的积分
                IntentUtils.getIntents().Intent(getActivity(), IntegralActivity.class, null);
                break;
            case R.id.coupons:
                //优惠券
                IntentUtils.getIntents().Intent(getActivity(), CouponsActivity.class, null);
                break;
            case R.id.answer:
                //智能解答
                IntentUtils.getIntents().Intent(getActivity(), AnswerActivity.class, null);
                break;
            case R.id.customer:
                //客服
                IntentUtils.getIntents().Intent(getActivity(), CustomerActivity.class, null);
                break;
            case R.id.grow:
                //生长中
                IntentUtils.getIntents().Intent(getActivity(), GrowActivity.class, null);
                break;
            case R.id.reative:
                //拼单
                IntentUtils.getIntents().Intent(getActivity(), ReativeActivity.class, null);
                break;
            case R.id.farm:
                String zt = SpzUtils.getString("zt");
                if (EmptyUtils.isNotEmpty(zt)) {
                    if (zt.equals("1")) {
                        IntentUtils.getIntents().Intent(getActivity(), Farm_inActivity.class, null);
                    } else if (zt.equals("2")) {
                        ToastUtil.showShort(getActivity(), "功能暂未开放");
                    }
                } else {
                    ToastUtil.showShort(getActivity(), "功能暂未开放");
                }
                break;
            case R.id.lin_address:
                IntentUtils.getIntents().Intent(getActivity(), Management_addressActivity.class, null);
                break;
            case R.id.rl_join:
                //加入我们
                IntentUtils.getIntents().Intent(getActivity(), JoinUsActivity.class, null);
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!LoginUtil.getInstance().checkLoginStatuss(getActivity())) {
            notLog.setVisibility(View.VISIBLE);
            information.setVisibility(View.GONE);
            int pic = R.mipmap.personal_heading;
            Glide.with(this).load(pic).into(head);
        } else {
            information.setVisibility(View.VISIBLE);
            notLog.setVisibility(View.GONE);
            String name = SpzUtilUser.getString("name");
            String zt = SpzUtilUser.getString("zt");
            String pic = SpzUtilUser.getString("pic");
            if (EmptyUtils.isNotEmpty(name) && EmptyUtils.isNotEmpty(zt) && EmptyUtils.isNotEmpty(pic)) {
                names.setText(name);
                Glide.with(this).load(pic).into(head);
            }
        }
    }

    /**
     * 设置透明状态栏
     */
    private void setStatusBarTransparent(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window =  getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }
}
