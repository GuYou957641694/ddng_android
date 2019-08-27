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
import com.bigpumpkin.app.ddng_android.bean.Collections_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class CollectionsAdapter extends RecyclerView.Adapter<CollectionsAdapter.MyViewHolder> {

    private List<Collections_Bean.DataBean> dataBean;
    private Context context;

    public CollectionsAdapter(List<Collections_Bean.DataBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.sollections_item, viewGroup, false);
        final CollectionsAdapter.MyViewHolder holder = new CollectionsAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (dataBean != null) {
            Glide.with(context).load(Urls.BASEURL + dataBean.get(i).getPic()).into(myViewHolder.imageView);
            myViewHolder.name.setText(dataBean.get(i).getTitle());
            myViewHolder.weight.setText(dataBean.get(i).getGg_title());
            myViewHolder.type.setText(dataBean.get(i).getPz_title());
            myViewHolder.price.setText(dataBean.get(i).getPrice());
            myViewHolder.collection.setOnClickListener(new View.OnClickListener() {
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

        private final ImageView imageView;
        private final TextView name, weight;
        private final TextView type;
        private final TextView price;
        private final TextView collection;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pic);
            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            price = itemView.findViewById(R.id.price);
            weight = itemView.findViewById(R.id.weight);
            collection = itemView.findViewById(R.id.collection);
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
