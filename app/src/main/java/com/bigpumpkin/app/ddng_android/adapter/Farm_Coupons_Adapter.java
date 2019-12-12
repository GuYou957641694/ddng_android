package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Farm_CouponsBean;

import java.util.List;

public class Farm_Coupons_Adapter extends RecyclerView.Adapter<Farm_Coupons_Adapter.MyViewHolder> {

    private Context context;
    private List<Farm_CouponsBean.DataBean.CouponBean> coupon;

    public Farm_Coupons_Adapter(Context context, List<Farm_CouponsBean.DataBean.CouponBean> coupon) {
        this.context = context;
        this.coupon = coupon;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.farm_producu_item, viewGroup, false);
        final Farm_Coupons_Adapter.MyViewHolder holder = new Farm_Coupons_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (coupon != null) {
            myViewHolder.tv_price.setText(coupon.get(i).getJian());
            myViewHolder.tv_man.setText("满" + coupon.get(i).getMan() + "可用");
        }
    }

    @Override
    public int getItemCount() {
        if (coupon != null && coupon.size() > 0) {
            return coupon.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_price;
        private TextView tv_man;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_price = itemView.findViewById(R.id.tv_name);
            tv_man = itemView.findViewById(R.id.tv_man);
        }
    }
}
