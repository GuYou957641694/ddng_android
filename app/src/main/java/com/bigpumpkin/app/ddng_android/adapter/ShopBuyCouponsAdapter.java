package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.ShopBuyBean;

import java.util.List;

public class ShopBuyCouponsAdapter extends RecyclerView.Adapter<ShopBuyCouponsAdapter.MyViewHolder> {

    private Context context;
    private  List<ShopBuyBean.DataBean.ShopBean.MemberCouponBean> shop;

    public ShopBuyCouponsAdapter(Context context, List<ShopBuyBean.DataBean.ShopBean.MemberCouponBean> shop) {
        this.context = context;
        this.shop = shop;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.shopbuycouponsitem, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
      myViewHolder.man_price.setText(shop.get(i).getMan());
        myViewHolder.jian_price.setText(shop.get(i).getJian());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.OnListener(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
      if (shop.size() > 0 && shop != null) {
            return shop.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView man_price;
        private TextView jian_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            man_price = itemView.findViewById(R.id.man_price);
            jian_price = itemView.findViewById(R.id.jian_price);
        }
    }

    /**
     * 定义一个接口
     */
    public interface onListener {
        void OnListener(int location);
    }

    /**
     * 定义一个变量储存数据
     */
    private onListener listener;

    /**
     * 提供公共的方法,并且初始化接口类型的数据
     */
    public void setListener(onListener listener) {
        this.listener = listener;
    }

}
