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
import com.bigpumpkin.app.ddng_android.bean.Wisdoms_AreasBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class Wisdoms_Areas_Adapter extends RecyclerView.Adapter<Wisdoms_Areas_Adapter.MyViewHolder> {

    private Context context;
    private List<Wisdoms_AreasBean.DataBean> data1;

    public Wisdoms_Areas_Adapter(Context context, List<Wisdoms_AreasBean.DataBean> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.wisdoms_areas_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (data1 != null) {
            GlideUtils.loadBlurImage(context, Urls.BASEURL + data1.get(i).getPic(), myViewHolder.iv_pic, 15);
            GlideUtils.loadRoundCircleImage(context, Urls.BASEURL + data1.get(i).getPic(), myViewHolder.pic);
            myViewHolder.tv_name.setText(data1.get(i).getTitle());
            myViewHolder.tv_title.setText(data1.get(i).getDes());
            myViewHolder.tv_add.setText(data1.get(i).getDizhi());
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
        private ImageView iv_pic;
        private ImageView pic;
        private TextView tv_name;
        private TextView tv_title;
        private TextView tv_add;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            pic = itemView.findViewById(R.id.pic);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_add = itemView.findViewById(R.id.tv_add);
        }
    }
}
