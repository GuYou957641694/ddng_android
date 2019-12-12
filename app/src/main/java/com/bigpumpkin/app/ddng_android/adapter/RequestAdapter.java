package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.FarmActivity;
import com.bigpumpkin.app.ddng_android.bean.PoultryBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.MyViewHolder> {

    private Context context;
    private List<PoultryBean.DataBean.PoultryIndexBean> poultry_index;

    public RequestAdapter(Context context, List<PoultryBean.DataBean.PoultryIndexBean> poultry_index) {
        this.context = context;
        this.poultry_index = poultry_index;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.request_item, viewGroup, false);
        final RequestAdapter.MyViewHolder holder = new RequestAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (poultry_index != null) {
            GlideUtils.loadRoundCircleImagetwo(context, Urls.BASEURL + poultry_index.get(i).getPic(),myViewHolder.iv_pic);
            myViewHolder.tv_name.setText(poultry_index.get(i).getDesc());
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", poultry_index.get(i).getId());
                    IntentUtils.getIntents().Intent(context, FarmActivity.class, bundle);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return poultry_index.size();
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
}
