package com.bigpumpkin.app.ddng_android.callback;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.bigpumpkin.app.ddng_android.bean.Fresh_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Meat_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Products_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Recommended_Fragment;

import java.util.HashMap;

public class PageAdapter extends FragmentPagerAdapter {

    private int num;
    private HashMap<Integer, Fragment> mFragmentHashMap = new HashMap<>();

    public PageAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {

        return createFragment(position);
    }

    @Override
    public int getCount() {
        return num;
    }

    private Fragment createFragment(int pos) {
        Fragment fragment = mFragmentHashMap.get(pos);

        if (fragment == null) {
            switch (pos) {
                case 0:
                    fragment = new Recommended_Fragment();
                    Log.i("fragment", "fragment1");
                    break;
                case 1:
                    fragment = new Meat_Fragment();
                    Log.i("fragment", "fragment2");
                    break;
                case 2:
                    fragment = new Products_Fragment();
                    Log.i("fragment", "fragment3");
                    break;
                case 3:
                    fragment = new Fresh_Fragment();
                    Log.i("fragment", "fragment4");
                    break;
            }
            mFragmentHashMap.put(pos, fragment);
        }
        return fragment;
    }
}
