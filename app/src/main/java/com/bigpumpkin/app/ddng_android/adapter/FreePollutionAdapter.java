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
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class FreePollutionAdapter extends RecyclerView.Adapter<FreePollutionAdapter.MyViewHolder> {

    private List<Adopt_Bean.DataBean.PollutionBean> pollution;
    private Context context;

    public FreePollutionAdapter(List<Adopt_Bean.DataBean.PollutionBean> pollution, Context context) {
        this.pollution = pollution;
        this.context = context;
    }

    @NonNull
    @Override
    public FreePollutionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.free_pollution_item, viewGroup, false);
        final FreePollutionAdapter.MyViewHolder holder = new FreePollutionAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FreePollutionAdapter.MyViewHolder myViewHolder, int i) {
        if (pollution != null) {
            GlideUtils.loadRoundCircleImage(context, Urls.BASEURL + pollution.get(i).getPic(), myViewHolder.iv_pic);
            myViewHolder.tv_name.setText(pollution.get(i).getTitle());
            myViewHolder.tv_title.setText(pollution.get(i).getDesc());
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
        if (pollution != null && pollution.size() > 0) {
            return pollution.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_pic;
        private TextView tv_name;
        private TextView tv_title;
        private RelativeLayout rl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_title = itemView.findViewById(R.id.tv_title);
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
