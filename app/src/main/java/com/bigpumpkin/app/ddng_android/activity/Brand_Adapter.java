package com.bigpumpkin.app.ddng_android.activity;

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

public class Brand_Adapter extends RecyclerView.Adapter<Brand_Adapter.MyViewHolder> {

    private Context context;
    private List<GoodsBean.DataBean.BrandFarmBean> data1;

    public Brand_Adapter(Context context, List<GoodsBean.DataBean.BrandFarmBean> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public Brand_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.brand_item, viewGroup, false);
        final Brand_Adapter.MyViewHolder holder = new Brand_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Brand_Adapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(data1.get(i).getTitle());
        Glide.with(context).load(Urls.BASEURL + data1.get(i).getPic()).into(myViewHolder.pic);

    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView pic;
        private TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
            title = itemView.findViewById(R.id.title);
        }
    }
}
