package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Save_More_Bean;

import java.util.List;

public class Save_Adapter extends RecyclerView.Adapter<Save_Adapter.MyViewHolder> {

    private List<Save_More_Bean.DataBean.ListBean> data1;
    private Context context;

    public Save_Adapter(List<Save_More_Bean.DataBean.ListBean> data1, Context context) {
        this.data1 = data1;
        this.context = context;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.save_more_item, viewGroup, false);
        final Save_Adapter.MyViewHolder holder = new Save_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(data1.get(i).getTitle());
    }


    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
