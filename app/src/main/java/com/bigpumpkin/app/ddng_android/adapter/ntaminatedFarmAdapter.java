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
import com.bigpumpkin.app.ddng_android.bean.WisdomBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class ntaminatedFarmAdapter extends RecyclerView.Adapter<ntaminatedFarmAdapter.MyViewHolder> {

    private List<WisdomBean.DataBean.Essay2Bean> essay2;
    private Context context;

    public ntaminatedFarmAdapter(List<WisdomBean.DataBean.Essay2Bean> essay2, Context context) {
        this.essay2 = essay2;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.ntaminatedfarm_item, viewGroup, false);
        final ntaminatedFarmAdapter.MyViewHolder holder = new ntaminatedFarmAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (essay2 != null) {
            GlideUtils.loadRoundCircleImagetwo(context,Urls.BASEURL + essay2.get(i).getPic(),myViewHolder.iv_pic);
            myViewHolder.tv_name.setText(essay2.get(i).getTitle());
            myViewHolder.tv_title.setText(essay2.get(i).getDes());
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
        if (essay2.size() > 0 && essay2 != null) {
            return essay2.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pic;
        private TextView tv_name;
        private TextView tv_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
    public interface  onListener{
        void OnListener(int i);
    }
    private onListener listener;
    public void setListener( onListener listener){
        this.listener = listener;
    }
}
