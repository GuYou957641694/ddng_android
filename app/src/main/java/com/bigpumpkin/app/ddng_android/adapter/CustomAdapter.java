package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.CategoryActivity;
import com.bigpumpkin.app.ddng_android.activity.Custom_classificationActivity;
import com.bigpumpkin.app.ddng_android.activity.GrowerActivity;
import com.bigpumpkin.app.ddng_android.bean.Adopt_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bumptech.glide.Glide;
import com.yhy.gvp.adapter.GVPAdapter;

import java.util.List;

public class CustomAdapter extends GVPAdapter<Adopt_Bean.DataBean.JournalismlenBean> {
    private static final String TAG = "aa";
    //上下文对象
    private Context mcontext;

    public CustomAdapter(int layoutId, List<Adopt_Bean.DataBean.JournalismlenBean> dataList, Context mcontext) {
        super(layoutId, dataList);
        this.mcontext = mcontext;
    }

    @Override
    public void bind(View item, int position, Adopt_Bean.DataBean.JournalismlenBean data) {
        LinearLayout module = item.findViewById(R.id.module);
        ImageView iv_image = item.findViewById(R.id.pics);
        TextView tv_classify_item = item.findViewById(R.id.name);
        //分类图标
        Glide.with(mcontext).load(Urls.BASEURL + data.getPic()).into(iv_image);
        //分类名称
        tv_classify_item.setText(data.getTitle());

        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getId().equals("14")) {
                    //果农说1植物认养（果农说） 2家禽认养 4当季水果（种果人说） 5公益放生（发起人说） 6生产者说
                    Bundle bundle = new Bundle();
                    bundle.putString("id", "1");
                    bundle.putString("name", "果农说");
                    IntentUtils.getIntents().Intent(mcontext, GrowerActivity.class, bundle);
                } else if (data.getId().equals("12")) {
                    //植物品类
                    Bundle bundle = new Bundle();
                    bundle.putString("id", "1");
                    IntentUtils.getIntents().Intent(mcontext, CategoryActivity.class, bundle);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", data.getId());
                    IntentUtils.getIntents().Intent(mcontext, Custom_classificationActivity.class, bundle);
                }
            }
        });
    }
}
