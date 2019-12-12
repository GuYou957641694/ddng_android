package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.FarmActivity;
import com.bigpumpkin.app.ddng_android.bean.GoodsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;

import java.util.List;

public class Farm_Adapter extends RecyclerView.Adapter<Farm_Adapter.MyViewHolder> {

    private List<GoodsBean.DataBean.FarmRescueBean> dataBean;
    private Context context;

    public Farm_Adapter(List<GoodsBean.DataBean.FarmRescueBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.farm_item, viewGroup, false);
        final Farm_Adapter.MyViewHolder holder = new Farm_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        GlideUtils.loadRoundCircleImagetwo(context, Urls.BASEURL + dataBean.get(i).getPic(), myViewHolder.imageView);
        myViewHolder.name.setText(dataBean.get(i).getTitle());
        myViewHolder.synopsis.setText(dataBean.get(i).getDes());
        myViewHolder.sponsor.setText(dataBean.get(i).getSponsor());
        if (i+1 == dataBean.size()) {
            myViewHolder.view.setVisibility(View.GONE);
        }
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Bundle bundle = new Bundle();
               bundle.putString("id",dataBean.get(i).getId());
                IntentUtils.getIntents().Intent(context, FarmActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBean.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView name, synopsis, sponsor;
        private View view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pic);
            name = itemView.findViewById(R.id.name);
            synopsis = itemView.findViewById(R.id.synopsis);
            sponsor = itemView.findViewById(R.id.sponsor);
            view = itemView.findViewById(R.id.view);
        }
    }
}
