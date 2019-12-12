package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.OrderDetailsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder> {

    private List<OrderDetailsBean.DataBean.ListBean.DetailsBean> details;
    private Context context;

    public OrderDetailsAdapter(List<OrderDetailsBean.DataBean.ListBean.DetailsBean> details, Context context) {
        this.details = details;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.order_details_item, viewGroup, false);
        final OrderDetailsAdapter.MyViewHolder holder = new OrderDetailsAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (details != null) {
            String pic = details.get(i).getPic();
            String title = details.get(i).getTitle();
            String pz_title = details.get(i).getPz_title();
            String gg_title = details.get(i).getGg_title();
            String price = details.get(i).getPrice();
            String num = details.get(i).getNum();
            Glide.with(context).load(Urls.BASEURL + pic).into(myViewHolder.pic);
            myViewHolder.name.setText(title);
            myViewHolder.zz_name.setText(pz_title + gg_title);
            myViewHolder.price.setText(price);
            myViewHolder.tv_num.setText(num);

        }
    }

    @Override
    public int getItemCount() {
        if (details != null && details.size() > 0) {
            return details.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView pic;
        private TextView name, zz_name, price, tv_num, tv_mail, tv_service, tv_coupons, tv_welfare, tv_accept, tv_customization, tv_mes;
        private RelativeLayout rl_mes, rl_customization_options, rl_must, rl_welfare, rl_couponss;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.img);
            //商品名
            name = itemView.findViewById(R.id.name);
            //规格名
            zz_name = itemView.findViewById(R.id.zz_name);
            //价格
            price = itemView.findViewById(R.id.price);
            //数量
            tv_num = itemView.findViewById(R.id.tv_num);
            //包邮
            tv_mail = itemView.findViewById(R.id.tv_mail);
            //服务
            tv_service = itemView.findViewById(R.id.tv_service);
            //优惠券
            tv_coupons = itemView.findViewById(R.id.tv_coupons);
            rl_couponss = itemView.findViewById(R.id.rl_couponss);
            //公益放生
            tv_welfare = itemView.findViewById(R.id.tv_welfare);
            rl_welfare = itemView.findViewById(R.id.rl_welfare);
            //准收宝
            tv_accept = itemView.findViewById(R.id.tv_accept);
            rl_must = itemView.findViewById(R.id.rl_must);
            //定制选项
            tv_customization = itemView.findViewById(R.id.tv_customization);
            rl_customization_options = itemView.findViewById(R.id.rl_customization_options);
            //留言
            tv_mes = itemView.findViewById(R.id.tv_mes);
            rl_mes = itemView.findViewById(R.id.rl_mes);
        }
    }
}
