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
import com.bigpumpkin.app.ddng_android.bean.PoultryBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class Rv_FarmingAdapter extends RecyclerView.Adapter<Rv_FarmingAdapter.MyViewHolder> {

    private List<PoultryBean.DataBean.FarmerListBean> farmer_list;
    private Context context;

    public Rv_FarmingAdapter(List<PoultryBean.DataBean.FarmerListBean> farmer_list, Context context) {
        this.farmer_list = farmer_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_farming_item, viewGroup, false);
        final Rv_FarmingAdapter.MyViewHolder holder = new Rv_FarmingAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (farmer_list != null) {
            GlideUtils.loadRoundCircleImage(context, Urls.BASEURL + farmer_list.get(i).getImage_pic(), myViewHolder.iv_pic);
            myViewHolder.tv_name.setText(farmer_list.get(i).getName());
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * 在合适的位置给其调用接口，给其赋值
                     */
                    if (listener!=null) {
                        listener.OnListener(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (farmer_list != null && farmer_list.size() > 0) {
            return farmer_list.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pic;
        private TextView tv_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
    /**
     * 定义一个接口
     */
    public interface  onListener{
        void OnListener(int i);
    }
    /**
     *定义一个变量储存数据
     */
    private onListener listener;
    /**
     *提供公共的方法,并且初始化接口类型的数据
     */
    public void setListener( onListener listener){
        this.listener = listener;
    }
}
