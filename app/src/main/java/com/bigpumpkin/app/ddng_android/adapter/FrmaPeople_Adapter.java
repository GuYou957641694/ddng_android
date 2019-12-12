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
import com.bigpumpkin.app.ddng_android.bean.FrmaPeopleBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class FrmaPeople_Adapter extends RecyclerView.Adapter<FrmaPeople_Adapter.MyViewHolder> {

    List<FrmaPeopleBean.DataBean> data1;
    Context context;

    public FrmaPeople_Adapter(List<FrmaPeopleBean.DataBean> data1, Context context) {
        this.data1 = data1;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.ftam_people_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (data1 != null) {
            myViewHolder.name.setText(data1.get(i).getU_name());
            myViewHolder.title.setText(data1.get(i).getUserl());
            Glide.with(context).load(Urls.BASEURL + data1.get(i).getImage_pic()).into(myViewHolder.pic);
        }
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, title;
        private final ImageView pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
