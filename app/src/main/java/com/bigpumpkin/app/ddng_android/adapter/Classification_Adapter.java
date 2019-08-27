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

public class Classification_Adapter extends RecyclerView.Adapter<Classification_Adapter.MyViewHolder> {
    private View view;
    private Context context;
    private List<GoodsBean.DataBean.ClassificationBean> classification;

    public Classification_Adapter(Context context, List<GoodsBean.DataBean.ClassificationBean> classification) {
        this.context = context;
        this.classification = classification;
    }

    @NonNull
    @Override
    public Classification_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取对应的布局
        view = LayoutInflater.from(context).inflate(R.layout.home_newcourse_rv, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Classification_Adapter.MyViewHolder myViewHolder, int i) {
        Glide.with(context).load(Urls.BASEURL + classification.get(i).getPic()).into(myViewHolder.img);
        myViewHolder.type.setText(classification.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return classification.size();
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

    //自定义一个接口回掉
    public interface onCourseidListener2 {
        void onCourseidSuccess2(int posiion);

    }

    onCourseidListener2 listener;

    //提供一个公共的接口监听方法
    public void setOnCourseidListener2(onCourseidListener2 listener) {
        this.listener = listener;
    }
}
