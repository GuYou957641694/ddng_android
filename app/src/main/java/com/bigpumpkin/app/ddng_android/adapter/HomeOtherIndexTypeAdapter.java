package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.GoodsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;
import com.yhy.gvp.adapter.GVPAdapter;

import java.util.List;

/**
 * 八大模块
 */
public class HomeOtherIndexTypeAdapter extends GVPAdapter<GoodsBean.DataBean.ModulBean> {

    private static final String TAG ="aa" ;
    //上下文对象
    private Context mcontext;

    public HomeOtherIndexTypeAdapter(int layoutId, List<GoodsBean.DataBean.ModulBean> dataList, Context mcontext) {
        super(layoutId, dataList);
        this.mcontext = mcontext;
    }

    @Override
    public void bind(View item, int position, GoodsBean.DataBean.ModulBean data) {
        ImageView iv_image = item.findViewById(R.id.pics);
        TextView tv_classify_item = item.findViewById(R.id.name);
        Log.d(TAG, "bind: "+Urls.BASEURL + data.getPic());
        //分类图标
        Glide.with(mcontext).load(Urls.BASEURL + data.getPic()).into(iv_image);
        //分类名称
        tv_classify_item.setText(data.getTitle());
    }
}
