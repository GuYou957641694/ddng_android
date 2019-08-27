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
import com.bigpumpkin.app.ddng_android.bean.Coupons_Bean;

import java.util.List;

public class CouponsApadter extends RecyclerView.Adapter<CouponsApadter.MyViewHolder> {

    private List<Coupons_Bean.DataBean.ListBean> dataBean;
    private Context context;

    public CouponsApadter(List<Coupons_Bean.DataBean.ListBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @NonNull
    @Override
    public CouponsApadter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.coupons_item, viewGroup, false);
        final CouponsApadter.MyViewHolder holder = new CouponsApadter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CouponsApadter.MyViewHolder myViewHolder, int i) {
        if (dataBean != null) {
            myViewHolder.name.setText(dataBean.get(i).getTitle());
            String time = dataBean.get(i).getTime();
            String jian = dataBean.get(i).getJian();
            myViewHolder.time.setText("使用期限" + "" + time);
            myViewHolder.jian.setText("¥" + jian + ".00");
            myViewHolder.weight.setText("满" + dataBean.get(i).getMan() + "使用");
        }
    }

    @Override
    public int getItemCount() {
        return dataBean.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, weight;
        private final TextView time;
        private final TextView jian;
        private final TextView collection;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            jian = itemView.findViewById(R.id.money);
            weight = itemView.findViewById(R.id.uer);
            collection = itemView.findViewById(R.id.with);

        }
    }
}
