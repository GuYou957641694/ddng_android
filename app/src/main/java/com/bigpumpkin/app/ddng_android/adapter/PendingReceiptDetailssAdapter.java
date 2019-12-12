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
import com.bigpumpkin.app.ddng_android.bean.PendingReceiptDetailssBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;

import java.util.List;


public class PendingReceiptDetailssAdapter extends RecyclerView.Adapter<PendingReceiptDetailssAdapter.MyViewHolder> {

    private Context context;
    private List<PendingReceiptDetailssBean.DataBean.ListBean> list;

    public PendingReceiptDetailssAdapter(Context context, List<PendingReceiptDetailssBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.pending_receipt_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        GlideUtils.loadRoundCircleImagethere(context, Urls.BASEURL + list.get(i).getPic(), myViewHolder.tvPic);
        myViewHolder.farm_name.setText(list.get(i).getTitle());
        myViewHolder.varieties.setText("已选：" + list.get(i).getPz_title() + list.get(i).getGg_title());
        Tv_Price_Utils.initPriceTv(context, list.get(i).getPrice(), myViewHolder.price);
        myViewHolder.num.setText("×" + list.get(i).getNum());
        String lanmu = list.get(i).getLanmu();
        if (list.size() > 1) {
            //如果size大于1 商品的申请售后显示
            myViewHolder.sales.setVisibility(View.VISIBLE);
            myViewHolder.sales.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.OnListener(i);
                    }
                }
            });
        } else {
            myViewHolder.sales.setVisibility(View.GONE);
        }
        if (lanmu.equals("1")) {
            myViewHolder.rl_lanmu.setVisibility(View.VISIBLE);
            myViewHolder.tv_maintenance.setText(list.get(i).getMaintain());
        } else if (lanmu.equals("6")) {
            myViewHolder.rl_lanmu.setVisibility(View.VISIBLE);
            myViewHolder.tv_maintenance.setText(list.get(i).getMaintain());
        } else {
            myViewHolder.rl_lanmu.setVisibility(View.GONE);
        }

        String after = list.get(i).getAfter();
        if (after != null) {
            if (after.equals("0")) {
                myViewHolder.sales.setVisibility(View.GONE);
            }
        }


    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView tvPic;
        TextView farm_name;
        TextView varieties;
        TextView price;
        TextView num;
        TextView tv_maintenance;
        TextView sales;
        RelativeLayout rl_lanmu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPic = itemView.findViewById(R.id.tv_pic);
            farm_name = itemView.findViewById(R.id.farm_name);
            varieties = itemView.findViewById(R.id.varieties);
            price = itemView.findViewById(R.id.price);
            num = itemView.findViewById(R.id.num);
            rl_lanmu = itemView.findViewById(R.id.rl_lanmu);
            tv_maintenance = itemView.findViewById(R.id.tv_maintenance);
            sales = itemView.findViewById(R.id.sales);
        }
    }

    /**
     * 定义一个接口
     */
    public interface onListener {
        void OnListener(int i);
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
