package com.bigpumpkin.app.ddng_android.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.MyOrdersPagerAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.fragment.MyOrderActivityFragment;
import com.bigpumpkin.app.ddng_android.fragment.MyOrderCourseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReativeActivity extends BaseActivity {

    private List<Fragment> listFragment;

    @Override
    public int intiLayout() {
        return R.layout.activity_reative;
    }

    @Override
    public void initView() {
        Toolbar mMyOrdersMainTitle = findViewById(R.id.my_collections_main_title);
        ViewPager mVpMyOrderContent = findViewById(R.id.vp_my_collection_content);
        TabLayout mTlMyOrdersMain = findViewById(R.id.tl_my_collections_main);
        mMyOrdersMainTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listFragment = new ArrayList<>();
        listFragment.add(new MyOrderCourseFragment());//我发起的
        listFragment.add(new MyOrderActivityFragment());//我参与的

        MyOrdersPagerAdapter myOrdersPagerAdapter =
                new MyOrdersPagerAdapter(getSupportFragmentManager(), listFragment);
        mVpMyOrderContent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTlMyOrdersMain));
        mTlMyOrdersMain.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mVpMyOrderContent));
        mVpMyOrderContent.setAdapter(myOrdersPagerAdapter);
    }

    @Override
    public void initData() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
