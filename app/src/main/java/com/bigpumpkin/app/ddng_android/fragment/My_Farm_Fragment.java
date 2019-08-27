package com.bigpumpkin.app.ddng_android.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class My_Farm_Fragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView wbCertExam;
    Unbinder unbinder;
    String url = "http://dng.shiduweb.com/Index/indexs.html";

    @Override
    protected int getLayoutId() {
        return R.layout.my_farm_fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void init(View view) {
        WebSettings settings = wbCertExam.getSettings();
        wbCertExam.setHorizontalScrollBarEnabled(false);
        wbCertExam.getSettings().setAllowUniversalAccessFromFileURLs(true);
        wbCertExam.getSettings().setAllowFileAccess(true);
        wbCertExam.getSettings().setDomStorageEnabled(true);
        wbCertExam.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        wbCertExam.getSettings().setAllowFileAccess(true);
        wbCertExam.getSettings().setAppCacheEnabled(true);
        //图片不显示
        wbCertExam.getSettings().setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wbCertExam.getSettings().setJavaScriptEnabled(true);
        wbCertExam.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wbCertExam.loadUrl(url);
        setStatusBarColor(getActivity(), Color.WHITE);
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


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        setStatusBarColor(getActivity(), Color.WHITE);
    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //状态栏改变颜色。
            window.setStatusBarColor(color);
        }
    }
}
