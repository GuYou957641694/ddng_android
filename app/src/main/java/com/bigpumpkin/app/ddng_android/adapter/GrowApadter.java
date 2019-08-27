package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Coupons_Bean;
import com.bigpumpkin.app.ddng_android.bean.Grow_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class GrowApadter extends RecyclerView.Adapter<GrowApadter.MyViewHolder> {

    private List<Grow_Bean.DataBean> dataBean;
    private Context context;

    public GrowApadter(List<Grow_Bean.DataBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @NonNull
    @Override
    public GrowApadter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.grow_item, viewGroup, false);
        final GrowApadter.MyViewHolder holder = new GrowApadter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GrowApadter.MyViewHolder myViewHolder, final int i) {
        if (dataBean != null) {
            Glide.with(context).load(Urls.BASEURL + dataBean.get(i).getPic()).into(myViewHolder.pic);
            myViewHolder.name.setText(dataBean.get(i).getFidname());
            myViewHolder.varieties.setText(dataBean.get(i).getPz_title());
            myViewHolder.price.setText(dataBean.get(i).getPrice());
            myViewHolder.ctime.setText(dataBean.get(i).getCtime());
            myViewHolder.etime.setText(dataBean.get(i).getFidname());
            myViewHolder.type.setText(dataBean.get(i).getType());
            //订单详情
            myViewHolder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(i);
                    }
                }
            });
            //查看信息
            myViewHolder.information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (informationlister != null) {
                        informationlister.onClick(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataBean.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView pic;
        private final TextView name, varieties, price, ctime, etime, type;
        private final Button details, information;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
            name = itemView.findViewById(R.id.name);
            varieties = itemView.findViewById(R.id.varieties);
            price = itemView.findViewById(R.id.price);
            ctime = itemView.findViewById(R.id.ctime);
            etime = itemView.findViewById(R.id.etime);
            type = itemView.findViewById(R.id.type);
            details = itemView.findViewById(R.id.details);
            information = itemView.findViewById(R.id.information);

        }
    }

    //订单详情
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //查询信息
    public interface OninformationClickListener {
        void onClick(int position);
    }

    private OninformationClickListener informationlister;

    public void OninformationClickListener(OninformationClickListener informationlister) {
        this.informationlister = informationlister;
    }
}
