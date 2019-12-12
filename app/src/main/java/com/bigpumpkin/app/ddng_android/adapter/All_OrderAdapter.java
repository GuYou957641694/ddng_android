package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.OrderDetailsActivity;
import com.bigpumpkin.app.ddng_android.bean.All_OrdersBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;

import java.util.List;

public class All_OrderAdapter extends RecyclerView.Adapter<All_OrderAdapter.MyViewHolder> {

    private List<All_OrdersBean.DataBean> data1;
    private Context context;

    public All_OrderAdapter(List<All_OrdersBean.DataBean> data1, Context context) {
        this.data1 = data1;
        this.context = context;
    }

    //  删除数据
    public void removeData(int position) {
        data1.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.all_order_item, viewGroup, false);
        final All_OrderAdapter.MyViewHolder holder = new All_OrderAdapter.MyViewHolder(view);
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
            String zt = data1.get(i).getZt();
            if (zt != null) {
                if (zt.equals("1")) {
                    myViewHolder.state.setText("支付成功");
                } else if (zt.equals("2")) {
                    myViewHolder.state.setText("支付失败");
                } else if (zt.equals("3")) {
                    myViewHolder.qx.setVisibility(View.VISIBLE);
                    myViewHolder.pay.setVisibility(View.VISIBLE);
                    myViewHolder.state.setText("等待付款");
                } else if (zt.equals("4")) {
                    myViewHolder.logistics.setVisibility(View.VISIBLE);
                    myViewHolder.state.setText("发货待收货");
                } else if (zt.equals("5")) {
                    myViewHolder.del.setVisibility(View.VISIBLE);
                    myViewHolder.state.setText("收货待评价");
                } else if (zt.equals("6")) {
                    myViewHolder.del.setVisibility(View.VISIBLE);
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
                GlideUtils.loadRoundCircleImagethere(context,Urls.BASEURL + pic,myViewHolder.pic);
            }

            String title = data1.get(i).getDetail().getDetails().getTitle();
            if (title != null) {
                myViewHolder.farm_name.setText(title);
            }
            String pz_title = data1.get(i).getDetail().getDetails().getPz_title();

            String gg_title = data1.get(i).getDetail().getDetails().getGg_title();
            if (pz_title != null && gg_title != null) {
                myViewHolder.varieties.setText("已选："+pz_title + gg_title);
            }
            String price = data1.get(i).getDetail().getDetails().getPrice();
            if (price != null) {
                Tv_Price_Utils.initPrice(context,price,  myViewHolder.price);
            }
            String title1 = data1.get(i).getDetail().getDetails().getTitle();
            if (title1 != null) {
                myViewHolder.farm_name.setText(title1);
            }

            String num = data1.get(i).getDetail().getDetails().getNum();
            String nums = data1.get(i).getNum();
            if (num != null) {
                myViewHolder.num.setText("×" + num);
            }

            myViewHolder.prices.setText("实付：" + data1.get(i).getPrice());
            myViewHolder.nums.setText("共" + nums + "件");

            //删除订单
            myViewHolder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.OnListener(i);
                    }
                }
            });
            //取消订单
            myViewHolder.qx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listeners != null) {
                        listeners.OnListeners(i);
                    }
                }
            });
            //待支付订单立即支付
            myViewHolder.pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listenerspay != null) {
                        listenerspay.OnListenerspay(i);
                    }
                }
            });
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", data1.get(i).getDetail().getDetails().getFid());
                    IntentUtils.getIntents().Intent(context, OrderDetailsActivity.class, bundle);
                }
            });
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
        private final TextView nums;
        private final TextView prices;
        private final TextView delete;
        private final TextView logistics;
        private final TextView confirm;
        private final TextView cancel;
        private final TextView evaluation;
        private final TextView order_details;
        private final TextView del;
        private final TextView qx;
        private final TextView pay;

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
            nums = itemView.findViewById(R.id.nums);
            prices = itemView.findViewById(R.id.prices);
            delete = itemView.findViewById(R.id.delete);
            logistics = itemView.findViewById(R.id.logistics);
            confirm = itemView.findViewById(R.id.confirm);
            cancel = itemView.findViewById(R.id.cancel);
            evaluation = itemView.findViewById(R.id.evaluation);
            order_details = itemView.findViewById(R.id.order_details);
            del = itemView.findViewById(R.id.del);
            qx = itemView.findViewById(R.id.qx);
            pay = itemView.findViewById(R.id.pay);
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

    public interface onListenerspay {
        void OnListenerspay(int i);
    }

    private onListenerspay listenerspay;

    public void setListenerspay(onListenerspay listenerspay) {
        this.listenerspay = listenerspay;
    }
}
