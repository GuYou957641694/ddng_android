package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

public class GoodsPhotoFragment extends BaseFragment {

    private ImageView pic;
    private String url;

    public static GoodsPhotoFragment getInstance(String picUrl) {
        GoodsPhotoFragment goodsPhotoFragment = new GoodsPhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("picUrl", picUrl);
        goodsPhotoFragment.setArguments(bundle);
        return goodsPhotoFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.goodsphotofragment;
    }

    @Override
    protected void init(View view) {
        Bundle arguments = this.getArguments();
        url = arguments.getString("picUrl");
        pic = view.findViewById(R.id.pic);
    }

    @Override
    protected void loadData() {
        if (url!=null){
            Glide.with(getActivity()).load(Urls.BASEURL + url).into(pic);
        }
    }
}
