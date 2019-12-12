package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.DetailsBean;
import com.bigpumpkin.app.ddng_android.utils.TimeUtils;

import java.util.List;

public class PreferentialAdapter extends RecyclerView.Adapter<PreferentialAdapter.MyViewHolder> {

    private List<DetailsBean.DataBean.CouponBean> coupon;
    Context context;

    public PreferentialAdapter(List<DetailsBean.DataBean.CouponBean> coupon, Context context) {
        this.coupon = coupon;
        this.context = context;
    }

    public void setCoupon(List<DetailsBean.DataBean.CouponBean> coupon) {
        this.coupon = coupon;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.preferential_item, viewGroup, false);
        final PreferentialAdapter.MyViewHolder holder = new PreferentialAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        if (coupon != null) {
            myViewHolder.jian.setText(coupon.get(i).getJian());
            myViewHolder.man.setText("满" + coupon.get(i).getMan() + "元减"+coupon.get(i).getJian()+"元");
            String dateTimeFromMillisecond = TimeUtils.getDateTimeFromMillisecond(coupon.get(i).getCtime());
            String dateTimeFromMillisecond1 = TimeUtils.getDateTimeFromMillisecond(coupon.get(i).getEtime());
            String substring = dateTimeFromMillisecond.substring(0,10);
            String substring1 = dateTimeFromMillisecond1.substring(0,10);
            myViewHolder.time.setText("有效期："+substring + "~" +substring1);
            int get = coupon.get(i).getGet();
            if (get == 1) {
                myViewHolder.li_id.setBackgroundResource(R.mipmap.icon_coupons_bgget);
            } else {
                myViewHolder.ling.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.OnListener(i);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return coupon.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView jian, man, time, ling;
        private RelativeLayout li_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            jian = itemView.findViewById(R.id.jian);
            man = itemView.findViewById(R.id.man);
            time = itemView.findViewById(R.id.time);
            ling = itemView.findViewById(R.id.ling);
            li_id = itemView.findViewById(R.id.li_id);
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
