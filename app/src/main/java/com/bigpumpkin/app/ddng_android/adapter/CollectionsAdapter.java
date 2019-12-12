package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.Spell_DetailsActivity;
import com.bigpumpkin.app.ddng_android.bean.Collections_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;

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
            GlideUtils.loadRoundCircleImagetwo(context, Urls.BASEURL + dataBean.get(i).getPic(), myViewHolder.imageView);
            myViewHolder.name.setText(dataBean.get(i).getTitle());
            myViewHolder.weight.setText(dataBean.get(i).getGg_title());
            Tv_Price_Utils.initPriceTv(context, dataBean.get(i).getPrice(), myViewHolder.price);
            if (i + 1 == dataBean.size()) {
                myViewHolder.view.setVisibility(View.GONE);
            }

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", dataBean.get(i).getId());
                    IntentUtils.getIntents().Intent(context, Spell_DetailsActivity.class, bundle);
                }
            });

            myViewHolder.menu_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (defaultlistener != null) {
                        defaultlistener.ondefaultClick(i);
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
        private final TextView price;
        private final View view;
        private LinearLayout menu_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pic);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            weight = itemView.findViewById(R.id.weight);
            view = itemView.findViewById(R.id.view);
            menu_layout = itemView.findViewById(R.id.menu_layout);
        }
    }


    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemdefaultClickListener {
        void ondefaultClick(int position);
    }

    private OnItemdefaultClickListener defaultlistener;

    public void setOnItemdefaultClickListener(OnItemdefaultClickListener defaultlistener) {
        this.defaultlistener = defaultlistener;
    }
}
