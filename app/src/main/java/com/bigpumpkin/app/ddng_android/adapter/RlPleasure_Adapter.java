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
import com.bigpumpkin.app.ddng_android.bean.WisdomBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class RlPleasure_Adapter extends RecyclerView.Adapter<RlPleasure_Adapter.MyViewHolder> {

    private List<WisdomBean.DataBean.Essay3Bean> essay3;
    private Context context;

    public RlPleasure_Adapter(List<WisdomBean.DataBean.Essay3Bean> essay3, Context context) {
        this.essay3 = essay3;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rlpleasure_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (essay3 != null) {
            Glide.with(context).load(Urls.BASEURL + essay3.get(i).getPic()).into(myViewHolder.iv_pic);
            myViewHolder.tv_name.setText(essay3.get(i).getTitle());
           // myViewHolder.tv_title.setText(essay3.get(i).getDizhi());
        }
    }

    @Override
    public int getItemCount() {
        if (essay3 != null && essay3.size() > 0) {
            return essay3.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pic;
        private TextView tv_name;
        private TextView tv_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_title = itemView.findViewById(R.id.tv_title);

        }
    }
}
