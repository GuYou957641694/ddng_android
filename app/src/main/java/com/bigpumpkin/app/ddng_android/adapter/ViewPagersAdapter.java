package com.bigpumpkin.app.ddng_android.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagersAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mStringTitle;

    public ViewPagersAdapter(FragmentManager fm, List<Fragment> fragments, List<String> stringTitle) {
        super(fm);
        this.mFragments = fragments;
        this.mStringTitle = stringTitle;
        notifyDataSetChanged();
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStringTitle.get(position);
    }
}