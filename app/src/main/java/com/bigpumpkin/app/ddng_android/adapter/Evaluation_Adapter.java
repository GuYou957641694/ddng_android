package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.DetailsBean;

import java.util.List;

public class Evaluation_Adapter extends RecyclerView.Adapter<Evaluation_Adapter.MyViewHolder> {

    List<DetailsBean.DataBean.EvaluationBean.LabelBean> label;
    Context context;

    public Evaluation_Adapter(List<DetailsBean.DataBean.EvaluationBean.LabelBean> label, Context context) {
        this.label = label;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.evaluationitem, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (label != null) {
            myViewHolder.text.setText(label.get(i).getTitle() + "(" + label.get(i).getNum() + ")");
        }
    }

    @Override
    public int getItemCount() {
        if (label != null) {
            return label.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }
}
