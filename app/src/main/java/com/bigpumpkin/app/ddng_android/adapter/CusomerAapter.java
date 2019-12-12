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
import com.bigpumpkin.app.ddng_android.bean.CusomerBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class CusomerAapter extends RecyclerView.Adapter<CusomerAapter.MyViewHolder> {

    private Context context;
    private List<CusomerBean.DataBean> data1;

    public CusomerAapter(Context context, List<CusomerBean.DataBean> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.cusomer_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        GlideUtils.loadRoundCircleImage(context, Urls.BASEURL + data1.get(i).getPic(), myViewHolder.iv_refund_pic);
        myViewHolder.tv_refund_title_name.setText(data1.get(i).getTitle());
        myViewHolder.tv_refund_ggtitle.setText("已选：" + data1.get(i).getPz_title() + data1.get(i).getGg_title());
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_refund_pic;
        private TextView tv_refund_title_name;
        private TextView tv_refund_ggtitle;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_refund_pic = itemView.findViewById(R.id.iv_refund_pic);
            tv_refund_title_name = itemView.findViewById(R.id.tv_refund_title_name);
            tv_refund_ggtitle = itemView.findViewById(R.id.tv_refund_ggtitle);
        }
    }
}
