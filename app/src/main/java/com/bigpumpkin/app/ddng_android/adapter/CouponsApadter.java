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
import com.bigpumpkin.app.ddng_android.bean.Coupons_Bean;
import com.bigpumpkin.app.ddng_android.utils.TimeUtils;

import java.util.List;


public class CouponsApadter extends RecyclerView.Adapter<CouponsApadter.MyViewHolder> {

    private Coupons_Bean.DataBean dataBean;
    private Context context;
    private int type;

    public CouponsApadter(Coupons_Bean.DataBean dataBean, Context context, int type) {
        this.dataBean = dataBean;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public CouponsApadter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.coupons_item, viewGroup, false);
        final CouponsApadter.MyViewHolder holder = new CouponsApadter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CouponsApadter.MyViewHolder myViewHolder, int i) {
        if (type == 0) {
            List<Coupons_Bean.DataBean.UnusedBean> unused = dataBean.getUnused();
            if (unused != null) {
                String man = unused.get(i).getMan();
                String jian = unused.get(i).getJian();
                String title = unused.get(i).getTitle();
                long ctime = unused.get(i).getCtime();
                long etime = unused.get(i).getEtime();
                myViewHolder.tv_id.setText(title);
                myViewHolder.man.setText("满" + man + "元减" + jian + "元");
                myViewHolder.jian.setText(jian);
                String timet = TimeUtils.time(ctime);
                String time = TimeUtils.time(etime);
                myViewHolder.time.setText("有效期：" + timet + "-" + time);
            }
        } else if (type == 1) {
            myViewHolder.li_id.setBackgroundResource(R.mipmap.icon_use_the);
            //已使用
            List<Coupons_Bean.DataBean.UsedBean> used = dataBean.getUsed();
            if (used != null) {
                String man = used.get(i).getMan();
                String jian = used.get(i).getJian();
                String title = used.get(i).getTitle();
                long ctime = used.get(i).getCtime();
                long etime = used.get(i).getEtime();
                myViewHolder.tv_id.setText(title);
                myViewHolder.man.setText("满" + man + "元减" + jian + "元");
                myViewHolder.jian.setText(jian);
                String timet = TimeUtils.time(ctime);
                String time = TimeUtils.time(etime);
                myViewHolder.time.setText("有效期：" + timet + "-" + time);
            }
        } else if (type == 2) {
            //以过期
            myViewHolder.li_id.setBackgroundResource(R.mipmap.icon_overdue);
            List<Coupons_Bean.DataBean.OverdueBean> overdue = dataBean.getOverdue();
            if (overdue != null) {
                String man = overdue.get(i).getMan();
                String jian = overdue.get(i).getJian();
                String title = overdue.get(i).getTitle();
                long ctime = overdue.get(i).getCtime();
                long etime = overdue.get(i).getEtime();
                myViewHolder.tv_id.setText(title);
                myViewHolder.man.setText("满" + man + "元减" + jian + "元");
                myViewHolder.jian.setText(jian);
                String timet = TimeUtils.time(ctime);
                String time = TimeUtils.time(etime);
                myViewHolder.time.setText("有效期：" + timet + "-" + time);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (type == 0) {
            if (dataBean.getUnused() != null && dataBean.getUnused().size() > 0) {
                return dataBean.getUnused().size();
            } else {
                return 0;
            }
        } else if (type == 1) {
            if (dataBean != null) {
                if (dataBean.getUsed() != null && dataBean.getUsed().size() > 0) {
                    return dataBean.getUsed().size();
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else if (type == 2) {
            if (dataBean != null) {
                if (dataBean.getOverdue() != null && dataBean.getOverdue().size() > 0) {
                    return dataBean.getOverdue().size();
                } else {
                    return 0;
                }
            } else {
                return 0;
            }

        } else {
            return 0;
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView jian;
        private final TextView tv_id;
        private final TextView man;
        private final TextView time;
        private final TextView ling;
        private final RelativeLayout li_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            jian = itemView.findViewById(R.id.jian);
            tv_id = itemView.findViewById(R.id.tv_id);
            man = itemView.findViewById(R.id.man);
            time = itemView.findViewById(R.id.time);
            ling = itemView.findViewById(R.id.ling);
            li_id = itemView.findViewById(R.id.li_id);

        }
    }
}
