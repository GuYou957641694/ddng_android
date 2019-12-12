package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.PoultryBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class EggAdapetr extends RecyclerView.Adapter<EggAdapetr.MyViewHolder> {

    private Context context;
    private List<PoultryBean.DataBean.PoultryIndex2Bean> egg;

    public EggAdapetr(Context context, List<PoultryBean.DataBean.PoultryIndex2Bean> egg) {
        this.context = context;
        this.egg = egg;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.egg_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (egg != null) {
            GlideUtils.loadRoundCircleImagetwo(context, Urls.BASEURL + egg.get(i).getPic(),myViewHolder.iv_pic);
            myViewHolder.tv_name.setText(egg.get(i).getTitle());
            String price = egg.get(i).getDesc();
            String str = "\u00a5".concat(price);
            AssetManager mgr = context.getAssets();
            Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
            myViewHolder.tv_peice.setTypeface(tf);
            myViewHolder.tv_peice.setText(str);
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null) {
                        listener.OnListener(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return egg.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pic;
        private TextView tv_name;
        private TextView tv_peice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_peice = itemView.findViewById(R.id.tv_peice);
        }
    }


    /**
     * 定义一个接口
     */
    public interface  onListener{
        void OnListener(int i);
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
