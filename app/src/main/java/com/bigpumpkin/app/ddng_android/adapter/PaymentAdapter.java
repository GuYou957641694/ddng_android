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
import com.bigpumpkin.app.ddng_android.bean.OrderlistsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {

    private Context context;
    private List<OrderlistsBean.DataBean.OrderlistBean> orderlist;

    public PaymentAdapter(Context context, List<OrderlistsBean.DataBean.OrderlistBean> orderlist) {
        this.context = context;
        this.orderlist = orderlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.payment_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        GlideUtils.loadRoundCircleImagethere(context, Urls.BASEURL + orderlist.get(i).getPic(), myViewHolder.ivPic);
        myViewHolder.tvFarmName.setText(orderlist.get(i).getTitle());
        myViewHolder.tvVarieties.setText("已选：" + orderlist.get(i).getPz_title() + orderlist.get(i).getGg_title());
        Tv_Price_Utils.initPrice(context, orderlist.get(i).getPrice(), myViewHolder.tvPrice);
        myViewHolder.tvNum.setText("×"+orderlist.get(i).getNum());
    }

    @Override
    public int getItemCount() {
        if (orderlist.size() > 0 && orderlist != null) {
            return orderlist.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPic;
        private TextView tvFarmName;
        private TextView tvVarieties;
        private TextView tvPrice;
        private TextView tvNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPic = itemView.findViewById(R.id.iv_pic);
            tvFarmName = itemView.findViewById(R.id.tv_farm_name);
            tvVarieties = itemView.findViewById(R.id.tv_varieties);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvNum = itemView.findViewById(R.id.tv_num);
        }
    }
}
