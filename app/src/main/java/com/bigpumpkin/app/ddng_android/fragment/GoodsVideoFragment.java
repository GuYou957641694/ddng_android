package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class GoodsVideoFragment extends BaseFragment {

    private JZVideoPlayerStandard mJC;
    private String url,picUrl;
    private boolean isViewCreated;

    public static GoodsVideoFragment getInstance(String picUrl, String videoUrl) {
        GoodsVideoFragment goodsVideoFragment = new GoodsVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("picUrl", picUrl);
        bundle.putString("videoUrl", videoUrl);
        goodsVideoFragment.setArguments(bundle);
        return goodsVideoFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.goodsvideofragment;
    }

    @Override
    protected void init(View view) {
        isViewCreated = true;
        Bundle arguments = this.getArguments();
        url = arguments.getString("videoUrl");
        picUrl = arguments.getString("picUrl");
        mJC = (JZVideoPlayerStandard) view.findViewById(R.id.mJC);
    }

    @Override
    protected void loadData() {
        mJC.setUp(Urls.BASEURL + url, JZVideoPlayerStandard.SCROLL_AXIS_HORIZONTAL, "");
        mJC.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(this).load(Urls.BASEURL +picUrl).into(mJC.thumbImageView);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreated) {
            if (isVisibleToUser) {
                JZVideoPlayerStandard.goOnPlayOnResume();
            } else {
                JZVideoPlayerStandard.goOnPlayOnPause();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
