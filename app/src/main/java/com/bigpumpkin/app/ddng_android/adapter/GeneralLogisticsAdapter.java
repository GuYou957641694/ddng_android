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
import com.bigpumpkin.app.ddng_android.bean.GeneralLogisticsBean;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.List;

public class GeneralLogisticsAdapter extends RecyclerView.Adapter<GeneralLogisticsAdapter.MyViewHolder> {

    private Context context;
    List<GeneralLogisticsBean.DataBeanX.ListBean.DataBean> data1;

    public GeneralLogisticsAdapter(Context context, List<GeneralLogisticsBean.DataBeanX.ListBean.DataBean> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.general_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tv.setText(data1.get(i).getContext());
        myViewHolder.time.setText(data1.get(i).getTime());

        if (i == 0) {
            //红色的圆点
            myViewHolder.iv_status.setImageResource(R.drawable.garden_shape);
            RelativeLayout.LayoutParams pointParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(20), DensityUtil.dp2px(20));
            pointParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            myViewHolder.iv_status.setLayoutParams(pointParams);

            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            myViewHolder.iv_line.setLayoutParams(lineParams);

        } else {
//                holder.iv_status.setBackgroundResource(R.mipmap.ic_logistics_bottom);
            myViewHolder.iv_status.setImageResource(R.drawable.garden_shape);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(10), DensityUtil.dp2px(10));
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);

            myViewHolder.iv_status.setLayoutParams(lp);

            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            myViewHolder.iv_line.setLayoutParams(lineParams);
        }
    }

    @Override
    public int getItemCount() {
        if (data1 != null && data1.size() > 0) {
            return data1.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv,time;
        ImageView iv_status;
        ImageView iv_line;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            tv = itemView.findViewById(R.id.tv_status);
            iv_status = itemView.findViewById(R.id.iv_status);
            iv_line = itemView.findViewById(R.id.iv_line);
        }
    }
}
