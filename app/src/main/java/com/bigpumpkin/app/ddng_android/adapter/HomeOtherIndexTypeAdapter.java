package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.CustomActivity;
import com.bigpumpkin.app.ddng_android.activity.PoultryActivity;
import com.bigpumpkin.app.ddng_android.activity.ProcessingActivity;
import com.bigpumpkin.app.ddng_android.activity.ProducerActivity;
import com.bigpumpkin.app.ddng_android.activity.Wisdoms_Activity;
import com.bigpumpkin.app.ddng_android.bean.GoodsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bumptech.glide.Glide;
import com.yhy.gvp.adapter.GVPAdapter;

import java.util.List;

/**
 * 八大模块
 */
public class HomeOtherIndexTypeAdapter extends GVPAdapter<GoodsBean.DataBean.ModulBean> {

    private static final String TAG = "aa";
    //上下文对象
    private Context mcontext;

    public HomeOtherIndexTypeAdapter(int layoutId, List<GoodsBean.DataBean.ModulBean> dataList, Context mcontext) {
        super(layoutId, dataList);
        this.mcontext = mcontext;
    }

    @Override
    public void bind(View item, int position, final GoodsBean.DataBean.ModulBean data) {
        String link = data.getLink();
        LinearLayout module = item.findViewById(R.id.module);
        ImageView iv_image = item.findViewById(R.id.pics);
        TextView tv_classify_item = item.findViewById(R.id.name);
        //分类图标
        Glide.with(mcontext).load(Urls.BASEURL + data.getPic()).into(iv_image);
        //分类名称
        tv_classify_item.setText(data.getTitle());

        module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getLink().equals("1")) {
                    //定制认养
                    IntentUtils.getIntents().Intent(mcontext, CustomActivity.class, null);
                } else if (data.getLink().equals("2")) {
                    //家禽领养
                    IntentUtils.getIntents().Intent(mcontext, PoultryActivity.class, null);
                } else if (data.getLink().equals("4")) {
                    //农场深加工
                    IntentUtils.getIntents().Intent(mcontext, ProcessingActivity.class, null);
                } else if (data.getLink().equals("6")) {
                    //智慧农场
                    IntentUtils.getIntents().Intent(mcontext, Wisdoms_Activity.class, null);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("link", link);
                    IntentUtils.getIntents().Intent(mcontext, ProducerActivity.class, bundle);
                }


               /* else if (data.getLink().equals("7")) {
                    //生产者说
                    IntentUtils.getIntents().Intent(mcontext, ProducerActivity.class, null);
                } else if (data.getLink().equals("8")) {
                    //药食同源
                    IntentUtils.getIntents().Intent(mcontext, PoultryActivity.class, null);
                } else if (data.getLink().equals("9")) {
                    //礼盒定制
                    IntentUtils.getIntents().Intent(mcontext, PoultryActivity.class, null);
                } else if (data.getLink().equals("10")) {
                    //企业福利
                    IntentUtils.getIntents().Intent(mcontext, PoultryActivity.class, null);
                } else if (data.getLink().equals("11")) {
                    //我来代言
                    IntentUtils.getIntents().Intent(mcontext, PoultryActivity.class, null);
                    //当季水果
                    //Bundle bundle =new Bundle();
                    //IntentUtils.getIntents().Intent(mcontext, FruitActivity.class, null);
                    //公益放生
                    //IntentUtils.getIntents().Intent(mcontext, ReleaseActivity.class, null);
                } else if (data.getLink().equals("12")) {
                    //积分商城
                    IntentUtils.getIntents().Intent(mcontext, PoultryActivity.class, null);
                } else if (data.getLink().equals("13")) {
                    //加入种子
                    IntentUtils.getIntents().Intent(mcontext, CustomActivity.class, null);
                } else if (data.getLink().equals("14")) {
                    //农场入驻
                    IntentUtils.getIntents().Intent(mcontext, CustomActivity.class, null);
                }*/
            }
        });
    }
}
