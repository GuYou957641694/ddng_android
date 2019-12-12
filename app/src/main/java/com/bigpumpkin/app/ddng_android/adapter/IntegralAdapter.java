package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.IntegralBean;
import com.bigpumpkin.app.ddng_android.utils.TimeUtils;

import java.util.List;

public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.MyViewHolder> {

    private Context context;
    private IntegralBean integralBean;
    private int position;

    public IntegralAdapter(Context context, IntegralBean integralBean, int position) {
        this.context = context;
        this.integralBean = integralBean;
        this.position = position;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.integreal_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        List<IntegralBean.DataBean.ListBean> list = integralBean.getData().getList();
        myViewHolder.tv_id_title.setText(list.get(i).getTitle());
        //	积分类型 1加 2减
        String types = list.get(i).getTypes();
        String fen = list.get(i).getFen();
        if (types.equals("1")) {
            myViewHolder.tv_integral.setText("+" + fen);
        } else {
            myViewHolder.tv_integral.setText("-" + fen);
        }
        long ctime = list.get(i).getCtime();
        String time = TimeUtils.time(ctime);
        myViewHolder.tv_time.setText(time);
    }

    @Override
    public int getItemCount() {
        if (integralBean.getData().getList() != null && integralBean.getData().getList().size() > 0) {
            return integralBean.getData().getList().size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id_title;
        TextView tv_time;
        TextView tv_integral;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id_title = itemView.findViewById(R.id.tv_id_title);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_integral = itemView.findViewById(R.id.tv_integral);
        }
    }
}
