package com.bigpumpkin.app.ddng_android.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.ViewPagersAdapter;
import com.bigpumpkin.app.ddng_android.fragment.CeshiFragment;
import com.bigpumpkin.app.ddng_android.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomsActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private List<Fragment> mFragments;

    private String[] mTitles = new String[]{"动态", "文章", "更多"};
    private List<String> mTitleList = Arrays.asList(mTitles);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customs);
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
        initView();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        //只是提供演示传值
        mFragments.add(CeshiFragment.newInstance("1"));
    }

    private void initView() {

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        initFragment();

        ViewPagersAdapter myAdapter = new ViewPagersAdapter(getFragmentManager(), mFragments, mTitleList);
        viewPager.setAdapter(myAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
