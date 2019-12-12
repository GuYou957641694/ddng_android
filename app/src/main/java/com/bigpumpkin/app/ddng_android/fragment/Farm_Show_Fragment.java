package com.bigpumpkin.app.ddng_android.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.ImagePreviewActivity;
import com.bigpumpkin.app.ddng_android.adapter.Farm_Show_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Show_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.P;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.OnItemPictureClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Farm_Show_Fragment extends BaseLazyLoadFragment implements MyView {

    private RecyclerView recyclerview;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String id;
    private int itemPosition;

    public static Farm_Show_Fragment getInstance(String id) {
        Farm_Show_Fragment farm_show_fragment = new Farm_Show_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        farm_show_fragment.setArguments(bundle);
        return farm_show_fragment;
    }


    @Override
    public int getLayout() {
        return R.layout.farm_show_fragment;
    }

    @Override
    public void initViews(View view) {
        Bundle arguments = this.getArguments();
        id = arguments.getString("id");
        recyclerview = view.findViewById(R.id.recyclerview);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
    }

    @Override
    public void loadData() {
        map.put("fid", id);
        presenter.get(Contacts.Farm_index_dynamics, headmap, map, Show_Bean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof Show_Bean) {
            Show_Bean show_bean = (Show_Bean) data;
            List<Show_Bean.DataBean> data1 = show_bean.getData();
            Farm_Show_Adapter farm_show_adapter =  new Farm_Show_Adapter(getActivity(), data1, new OnItemPictureClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onItemPictureClick(int itemPostion, int i, String url, List<String> urlList, ImageView imageView) {
                    itemPosition = itemPostion;
                    Intent intent = new Intent(getActivity(), ImagePreviewActivity.class);
                    intent.putStringArrayListExtra("imageList", (ArrayList<String>) urlList);
                    intent.putExtra(P.START_ITEM_POSITION, itemPosition);
                    intent.putExtra(P.START_IAMGE_POSITION, i);
                    ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation(getActivity(), imageView, imageView.getTransitionName());
                    startActivity(intent, compat.toBundle());
                }
            });

            LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerview.setLayoutManager(layoutManager);
            recyclerview.setAdapter(farm_show_adapter);

        }
    }

    @Override
    public void error(String error) {

    }
}
