package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.PoultryBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;
import com.yhy.gvp.adapter.GVPAdapter;

import java.util.List;

public class PoultryaAdapter extends GVPAdapter<PoultryBean.DataBean.SpfBean> {

    private static final String TAG = "aa";
    //上下文对象
    private Context mcontext;

    public PoultryaAdapter(int layoutId, List<PoultryBean.DataBean.SpfBean> dataList, Context mcontext) {
        super(layoutId, dataList);
        this.mcontext = mcontext;
    }

    @Override
    public void bind(View item, int position, PoultryBean.DataBean.SpfBean data) {
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
                /**
                 * 在合适的位置给其调用接口，给其赋值
                 */
                if (listener!=null) {
                    listener.OnListener(position);
                }
            }
        });
    }
    /**
     * 定义一个接口
     */
    public interface  onListener{
        void OnListener(int position);
    }
    /**
     *定义一个变量储存数据
     */
    private onListener listener;
    /**
     *提供公共的方法,并且初始化接口类型的数据
     */
    public void setListener( onListener listener){
        this.listener = listener;
    }
}
