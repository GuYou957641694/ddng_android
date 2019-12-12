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
import com.bigpumpkin.app.ddng_android.bean.Adopt_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class SelectionAdapter extends RecyclerView.Adapter<SelectionAdapter.MyViewHolder> {
    private List<Adopt_Bean.DataBean.AreaBean> area;
    private Context context;

    public SelectionAdapter(List<Adopt_Bean.DataBean.AreaBean> area, Context context) {
        this.area = area;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.selection_item, viewGroup, false);
        final SelectionAdapter.MyViewHolder holder = new SelectionAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (area != null) {
            myViewHolder.tv_name.setText(area.get(i).getTitle());
            Glide.with(context).load(Urls.BASEURL + area.get(i).getPic()).into(myViewHolder.iv_pic);

            myViewHolder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.OnListener(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (area != null && area.size() > 0) {
            return area.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_pic;
        private TextView tv_name;
        private RelativeLayout rl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            tv_name = itemView.findViewById(R.id.tv_name);
            rl = itemView.findViewById(R.id.rl);
        }
    }

    /**
     * 定义一个接口
     */
    public interface onListener {
        void OnListener(int id);
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
