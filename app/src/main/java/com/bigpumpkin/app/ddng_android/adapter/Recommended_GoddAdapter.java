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
import com.bigpumpkin.app.ddng_android.bean.DetailsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class Recommended_GoddAdapter extends RecyclerView.Adapter<Recommended_GoddAdapter.MyViewHolder> {

    private Context context;
    private List<DetailsBean.DataBean.RecommendBean> recommend;

    public Recommended_GoddAdapter(Context context, List<DetailsBean.DataBean.RecommendBean> recommend) {
        this.context = context;
        this.recommend = recommend;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.recommended_gooditem, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (recommend != null) {
            myViewHolder.tv_name.setText(recommend.get(i).getTitle());
            String str = "\u00a5".concat(recommend.get(i).getPrice());
            AssetManager mgr = context.getAssets();
            Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
            myViewHolder.tv_price.setTypeface(tf);
            myViewHolder.tv_price.setText(str);
            GlideUtils.loadRoundCircleImagetwo(context, Urls.BASEURL + recommend.get(i).getPic(), myViewHolder.iv_pic);
        }
    }

    @Override
    public int getItemCount() {
        if (recommend.size() > 0 && recommend != null) {
            return recommend.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_pic;
        private TextView tv_name, tv_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
