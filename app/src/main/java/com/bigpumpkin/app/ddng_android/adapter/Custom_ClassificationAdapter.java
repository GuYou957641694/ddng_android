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
import com.bigpumpkin.app.ddng_android.bean.Custom_ClassificationBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;

import java.util.List;

public class Custom_ClassificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Custom_ClassificationBean.DataBean> data1;


    public Custom_ClassificationAdapter(Context context, List<Custom_ClassificationBean.DataBean> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        if (i == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item, viewGroup, false);
            final MyViewHolder holder = new MyViewHolder(view);
            return holder;
        } else {
            View views = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.rv_item_footer, viewGroup, false);
            FooterHolder footerHolder = new FooterHolder(views);
            return footerHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            ((MyViewHolder) viewHolder).name.setText(data1.get(i).getTitle());
            ((MyViewHolder) viewHolder).production.setText("产量：" + data1.get(i).getChanliang());
            String price = data1.get(i).getPrice();
            Tv_Price_Utils.initPriceTv(context, price, ((MyViewHolder) viewHolder).price);
            GlideUtils.loadRoundCircleImageTop(context, Urls.BASEURL + data1.get(i).getPic(), ((MyViewHolder) viewHolder).pic);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(i);
                    }
                }
            });
        } else if (viewHolder instanceof FooterHolder) {

        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == data1.size()) {
            //最后一个 是底部item
            return 1;
        } else {
            return 0;
        }
    }


    //提供给外部调用的方法 刷新数据
    public void updateData(List<Custom_ClassificationBean.DataBean> adddata) {
        data1.addAll(adddata);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return data1.size() + 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, production, price;
        private ImageView pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            pic = itemView.findViewById(R.id.pic);
            production = itemView.findViewById(R.id.production);
            price = itemView.findViewById(R.id.price);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    //底部"加载更多"item viewholder
    public class FooterHolder extends RecyclerView.ViewHolder {
        TextView ivLoad;

        public FooterHolder(View itemView) {
            super(itemView);
            ivLoad = itemView.findViewById(R.id.tv_ivLoad);
        }
    }
}
