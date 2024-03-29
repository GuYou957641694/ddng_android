package com.bigpumpkin.app.ddng_android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class BaseFragmentAdapter extends FragmentPagerAdapter {

    protected List<Fragment> mFragmentList;

    protected List<String> mTitles;

//    public BaseFragmentAdapter(FragmentManager fm) {
//        this(fm, null, null);
//    }

    public BaseFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitles) {
        super(fm);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        this.mFragmentList = fragmentList;
        this.mTitles = mTitles;
    }

//    public void add(Fragment fragment) {
//        if (isEmpty()) {
//            mFragmentList = new ArrayList<>();
//
//        }
//        mFragmentList.add(fragment);
//    }

    @Override
    public Fragment getItem(int position) {
        //        Logger.i("BaseFragmentAdapter position=" +position);
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public boolean isEmpty() {
        return mFragmentList == null;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    /*  @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }*/

}
