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
import com.bigpumpkin.app.ddng_android.bean.FarmTypeBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class FarmTypeAdapter extends RecyclerView.Adapter<FarmTypeAdapter.MyViewHolder> {

    private Context context;
    private List<FarmTypeBean.DataBean> data1;

    public FarmTypeAdapter(Context context, List<FarmTypeBean.DataBean> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.farmtype_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (data1 != null) {
            myViewHolder.tv_name.setText(data1.get(i).getTitle());
            myViewHolder.tv_title.setText(data1.get(i).getDes());
            myViewHolder.tv_dizhi.setText("地址:" + data1.get(i).getDizhi());
            GlideUtils.loadRoundCircleImagetwo(context, Urls.BASEURL + data1.get(i).getPic(), myViewHolder.tv_pic);
            if (i + 1 == data1.size()) {
                myViewHolder.view.setVisibility(View.GONE);
            }

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null) {
                        listener.OnListener(i);
                    }
                }
            });
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
        private ImageView tv_pic;
        private TextView tv_name;
        private TextView tv_title;
        private TextView tv_dizhi;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pic = itemView.findViewById(R.id.tv_pic);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_dizhi = itemView.findViewById(R.id.tv_dizhi);
            view = itemView.findViewById(R.id.view);
        }
    }

    public interface onListener {
        void OnListener(int i);
    }

    private onListener listener;

    public void setListener(onListener listener) {
        this.listener = listener;
    }
}
