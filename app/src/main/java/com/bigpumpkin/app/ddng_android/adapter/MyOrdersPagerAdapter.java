package com.bigpumpkin.app.ddng_android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> listFragment = new ArrayList<>();

    public MyOrdersPagerAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;
    }


    @Override
    public Fragment getItem(int position) {

        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment != null ? listFragment.size() : 0;
    }

}
