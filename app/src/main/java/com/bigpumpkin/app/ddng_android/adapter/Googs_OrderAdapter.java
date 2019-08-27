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
import com.bigpumpkin.app.ddng_android.bean.All_OrdersBean;
import com.bigpumpkin.app.ddng_android.bean.For_GoodsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class Googs_OrderAdapter extends RecyclerView.Adapter<Googs_OrderAdapter.MyViewHolder> {

    private List<For_GoodsBean.DataBean.ListBean> data1;
    private Context context;

    public Googs_OrderAdapter(List<For_GoodsBean.DataBean.ListBean> data1, Context context) {
        this.data1 = data1;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.all_order_item, viewGroup, false);
        final Googs_OrderAdapter.MyViewHolder holder = new Googs_OrderAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (data1 != null) {
            //用order_type来判断订单还是拼单
            String farm_name = data1.get(i).getFarm_name();
            if (farm_name != null) {
                myViewHolder.name.setText(farm_name);
            }
          /*  String zt = data1.get(i).getZt();
            if (zt != null) {
                if (zt.equals("1")) {
                    myViewHolder.state.setText("支付成功");
                } else if (zt.equals("2")) {
                    myViewHolder.state.setText("支付失败");
                } else if (zt.equals("3")) {
                    myViewHolder.state.setText("待支付");
                } else if (zt.equals("4")) {
                    myViewHolder.state.setText("发货待收货");
                } else if (zt.equals("5")) {
                    myViewHolder.state.setText("收货待评价");
                } else if (zt.equals("6")) {
                    myViewHolder.state.setText("订单取消关闭");
                } else if (zt.equals("7")) {
                    myViewHolder.state.setText("确认收货以评价");
                } else if (zt.equals("8")) {
                    myViewHolder.state.setText("确认收货以评价");
                } else if (zt.equals("9")) {
                    myViewHolder.state.setText("退款成功");
                }
            }
            String pic = data1.get(i).getDetail().getDetails().getPic();

            if (pic != null) {
                Glide.with(context).load(Urls.BASEURL + pic).into(myViewHolder.pic);
            }
            String title = data1.get(i).getDetail().getDetails().getTitle();
            if (title != null) {
                myViewHolder.farm_name.setText(title);
            }
            String pz_title = data1.get(i).getDetail().getDetails().getPz_title();
            String gg_title = data1.get(i).getDetail().getDetails().getGg_title();
            if (pz_title != null && gg_title != null) {
                myViewHolder.varieties.setText(pz_title + gg_title);
            }
            String price = data1.get(i).getDetail().getDetails().getPrice();
            if (price != null) {
                myViewHolder.price.setText(price);
            }
            String title1 = data1.get(i).getDetail().getDetails().getTitle();
            if (title1 != null) {
                myViewHolder.farm_name.setText(title1);
            }
            String order_type = data1.get(i).getOrder_type();
            if (order_type != null) {
                if (data1.get(i).getOrder_type().equals("1")) {
                    myViewHolder.single.setText("订");
                } else if (data1.get(i).getOrder_type().equals("2")) {
                    myViewHolder.single.setText("拼");
                }
            }
            String num = data1.get(i).getDetail().getDetails().getNum();
            if (num != null) {
                myViewHolder.num.setText(num);
            }
            String numbering = data1.get(i).getDetail().getDetails().getNumbering();
            if (numbering != null) {
                myViewHolder.order.setText("订单编号：" + numbering);
            }
            String pay_time = data1.get(i).getPay_time();
            if (pay_time != null) {
                myViewHolder.ctime.setText("订单时间：" + pay_time);
            }*/


        }

    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final TextView name;
        private final TextView state;
        private final ImageView pic;
        private final TextView farm_name;
        private final TextView varieties;
        private final TextView price;
        private final TextView single;
        private final TextView num;
        private final TextView order;
        private final TextView ctime;
        private final TextView nums;
        private final TextView prices;
        private final TextView delete;
        private final TextView logistics;
        private final TextView confirm;
        private final TextView cancel;
        private final TextView evaluation;
        private final TextView order_details;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            state = itemView.findViewById(R.id.state);
            pic = itemView.findViewById(R.id.pic);
            farm_name = itemView.findViewById(R.id.farm_name);
            varieties = itemView.findViewById(R.id.varieties);
            price = itemView.findViewById(R.id.price);
            single = itemView.findViewById(R.id.single);
            num = itemView.findViewById(R.id.num);
            order = itemView.findViewById(R.id.order);
            ctime = itemView.findViewById(R.id.ctime);
            nums = itemView.findViewById(R.id.nums);
            prices = itemView.findViewById(R.id.prices);
            delete = itemView.findViewById(R.id.delete);
            logistics = itemView.findViewById(R.id.logistics);
            confirm = itemView.findViewById(R.id.confirm);
            cancel = itemView.findViewById(R.id.cancel);
            evaluation = itemView.findViewById(R.id.evaluation);
            order_details = itemView.findViewById(R.id.order_details);
        }
    }
}
