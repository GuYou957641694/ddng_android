package com.bigpumpkin.app.ddng_android.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.MyPagerAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.orders.All_OrdersFragment;
import com.bigpumpkin.app.ddng_android.orders.For_CollectionFragment;
import com.bigpumpkin.app.ddng_android.orders.For_EvaluateFragment;
import com.bigpumpkin.app.ddng_android.orders.For_GoodsFragment;
import com.bigpumpkin.app.ddng_android.orders.For_SalesFragment;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends BaseActivity {

    private List<String> list_Title;
    private List<Fragment> fragmentList;
    private ViewPager viewpager;
    private TabLayout tablayout;
    private int index;

    @Override
    public int intiLayout() {
        return R.layout.activity_orders;
    }

    @Override
    public void initView() {
        new TitleXML(OrdersActivity.this, "我的订单", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        viewpager = findViewById(R.id.viewpager);
        tablayout = findViewById(R.id.tablayout);
        index = getIntent().getIntExtra("key", 0);

        list_Title = new ArrayList<>();
        fragmentList = new ArrayList<>();
        fragmentList.add(new All_OrdersFragment());
        fragmentList.add(new For_CollectionFragment());
        fragmentList.add(new For_GoodsFragment());
        fragmentList.add(new For_EvaluateFragment());
        fragmentList.add(new For_SalesFragment());
        list_Title.add("全部订单");
        list_Title.add("待付款");
        list_Title.add("待收货");
        list_Title.add("待评价");
        list_Title.add("退款/售后");
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), OrdersActivity.this, fragmentList, list_Title);
        viewpager.setAdapter(myPagerAdapter);
        tablayout.setupWithViewPager(viewpager);//此方法就是让tablayout和ViewPager联动
        //跳到指定页面
        tablayout.getTabAt(index).select();
    }

    @Override
    public void initData() {

    }
}
