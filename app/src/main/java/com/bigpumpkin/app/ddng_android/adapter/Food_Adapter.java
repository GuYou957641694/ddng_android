package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.Spell_DetailsActivity;
import com.bigpumpkin.app.ddng_android.bean.GoodsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bumptech.glide.Glide;

import java.util.List;

public class Food_Adapter extends RecyclerView.Adapter<Food_Adapter.MyViewHolder> {
    private List<GoodsBean.DataBean.RecommendedAdoptionBean> dataBean;
    private Context context;

    public Food_Adapter(List<GoodsBean.DataBean.RecommendedAdoptionBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.food_item, viewGroup, false);
        final Food_Adapter.MyViewHolder holder = new Food_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (dataBean != null) {
            Glide.with(context).load(Urls.BASEURL + dataBean.get(i).getPic()).into(myViewHolder.imageView);
            String price = dataBean.get(i).getPrice();
            myViewHolder.name.setText(dataBean.get(i).getTitle());
            myViewHolder.title.setText(dataBean.get(i).getChanliang());
            String concat = "\u00a5".concat(price);
            AssetManager mgr = context.getAssets();
            Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
            myViewHolder.price.setTypeface(tf);
            myViewHolder.price.setText(concat);
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle =new Bundle();
                    bundle.putString("id",dataBean.get(i).getId());
                    IntentUtils.getIntents().Intent(context, Spell_DetailsActivity.class,bundle);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (dataBean != null && dataBean.size() > 0) {
            return dataBean.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView name;
        private final TextView price;
        private final TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pic);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            title = itemView.findViewById(R.id.title);
        }
    }
}
