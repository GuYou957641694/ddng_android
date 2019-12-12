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
import com.bigpumpkin.app.ddng_android.bean.DetailassGoodBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.MyViewHolder> {

    List<DetailassGoodBean.DataBean.ListBean.DetailsBean> details;
    Context context;

    public ReceiptAdapter(List<DetailassGoodBean.DataBean.ListBean.DetailsBean> details, Context context) {
        this.details = details;
        this.context = context;
    }

    //  删除数据
    public void removeData(int position) {
        details.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.receiot_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (details != null) {
            Glide.with(context).load(Urls.BASEURL + details.get(i).getPic()).into(myViewHolder.pic);
            myViewHolder.name.setText(details.get(i).getTitle());
            myViewHolder.title.setText(details.get(i).getPz_title() + details.get(i).getGg_title());
            myViewHolder.price.setText(details.get(i).getPrice());

      /*      myViewHolder.shouh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.OnListener(i);
                    }
                }
            });*/
            /*myViewHolder.logistics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listeners != null) {
                        listeners.OnListeners(i);
                    }
                }
            });*/
        }
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, title, price, logistics;
        private final ImageView pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            logistics = itemView.findViewById(R.id.logistics);
        }
    }

    /**
     * 定义一个接口
     */
    public interface onListener {
        void OnListener(int i);
    }

    private onListener listener;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    public interface onListeners {
        void OnListeners(int i);
    }

    private onListeners listeners;

    public void setListeners(onListeners listeners) {
        this.listeners = listeners;
    }
}
