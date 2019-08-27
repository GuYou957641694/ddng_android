package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.GoodsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class Plant_Adapter extends RecyclerView.Adapter<Plant_Adapter.MyViewHolder> {


    private List<GoodsBean.DataBean.TherapyBean> dataBean;
    private Context context;

    public Plant_Adapter(List<GoodsBean.DataBean.TherapyBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.plant_item, viewGroup, false);
        final Plant_Adapter.MyViewHolder holder = new Plant_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (dataBean != null) {
            Glide.with(context).load(Urls.BASEURL + dataBean.get(i).getPic()).into(myViewHolder.img);
            myViewHolder.type.setText(dataBean.get(i).getTitle());


        }
    }

    @Override
    public int getItemCount() {
        return dataBean.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.home_newcourse_rv_img);
            type = itemView.findViewById(R.id.home_newcourse_rv_coursetype);
        }
    }

}
