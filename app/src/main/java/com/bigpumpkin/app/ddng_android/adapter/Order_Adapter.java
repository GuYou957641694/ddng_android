package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Collections_Bean;
import com.bigpumpkin.app.ddng_android.bean.Order_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.MyViewHolder> {

    private List<Order_Bean.DataBean.ListBean> dataBean;
    private Context context;

    public Order_Adapter(List<Order_Bean.DataBean.ListBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.grow_item, viewGroup, false);
        final Order_Adapter.MyViewHolder holder = new Order_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (dataBean != null) {
            Glide.with(context).load(Urls.BASEURL + dataBean.get(i).getImg_pic()).into(myViewHolder.pic);
            myViewHolder.name.setText(dataBean.get(i).getTitle());
            myViewHolder.varieties.setText(dataBean.get(i).getCp_title());
            myViewHolder.price.setText("拼单人数：" + dataBean.get(i).getNum() + "人");
            myViewHolder.ctime.setText("拼单价格：" + dataBean.get(i).getAmount() + "元");
            myViewHolder.etime.setVisibility(View.GONE);
            myViewHolder.type.setVisibility(View.GONE);
            //查看信息
            myViewHolder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataBean.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView pic;
        private final TextView name, varieties, price, ctime, etime, type;
        private final Button details, information;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
            name = itemView.findViewById(R.id.name);
            varieties = itemView.findViewById(R.id.varieties);
            price = itemView.findViewById(R.id.price);
            ctime = itemView.findViewById(R.id.ctime);
            etime = itemView.findViewById(R.id.etime);
            type = itemView.findViewById(R.id.type);
            details = itemView.findViewById(R.id.details);
            information = itemView.findViewById(R.id.information);
        }
    }


    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
