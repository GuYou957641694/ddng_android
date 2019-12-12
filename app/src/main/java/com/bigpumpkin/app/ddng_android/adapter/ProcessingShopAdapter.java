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
import com.bigpumpkin.app.ddng_android.bean.ProcessingShopBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;

import java.util.List;

public class ProcessingShopAdapter extends RecyclerView.Adapter<ProcessingShopAdapter.MyViewHolder> {

    private Context context;
    List<ProcessingShopBean.DataBean> data1;

    public ProcessingShopAdapter(Context context, List<ProcessingShopBean.DataBean> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.processingshop_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        GlideUtils.loadRoundCircleImagetwo(context, Urls.BASEURL + data1.get(i).getPic(), myViewHolder.iv_pic);
        myViewHolder.tv_name.setText(data1.get(i).getTitle());
        myViewHolder.production.setText(data1.get(i).getChanliang());
        Tv_Price_Utils.initPriceTv(context, data1.get(i).getPrice(), myViewHolder.tv_price);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.OnListener(i);
                }
            }
        });
        myViewHolder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Addlistener!=null) {
                    Addlistener.OnAddListener(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pic,iv_add;
        private TextView tv_name;
        private TextView production;
        private TextView tv_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            iv_add = itemView.findViewById(R.id.iv_add);
            tv_name = itemView.findViewById(R.id.tv_name);
            production = itemView.findViewById(R.id.production);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
    public interface  onListener{
        void OnListener(int i);
    }
    private onListener listener;
    public void setListener( onListener listener){
        this.listener = listener;
    }

 public interface  onAddListener{
        void OnAddListener(int i);
    }
    private onAddListener Addlistener;

    public void AddsetListener( onAddListener Addlistener){
        this.Addlistener = Addlistener;
    }


}
