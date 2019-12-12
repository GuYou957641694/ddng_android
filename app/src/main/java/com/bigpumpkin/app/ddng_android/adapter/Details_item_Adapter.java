package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.DetailsBean;

import java.util.ArrayList;
import java.util.List;

public class Details_item_Adapter extends RecyclerView.Adapter<Details_item_Adapter.MyViewHolder> {

    //1、定义一个集合，用来记录选中
    private List<Boolean> isClicks;
    private Context context;
    List<DetailsBean.DataBean.VarietyBean.VooBean> variety;

    public Details_item_Adapter(Context context, List<DetailsBean.DataBean.VarietyBean.VooBean> variety) {
        this.context = context;
        this.variety = variety;
        isClicks = new ArrayList<>();
        for (int i = 0; i < variety.size(); i++) {
            isClicks.add(false);
        }
    }

    public void setVariety(List<DetailsBean.DataBean.VarietyBean.VooBean> variety) {
        this.variety = variety;
        //3、为集合添加值
        isClicks = new ArrayList<>();
        for (int i = 0; i < variety.size(); i++) {
            isClicks.add(false);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.text, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.item.setText(variety.get(i).getTitle());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClicks != null) {
                    int position = myViewHolder.getLayoutPosition();
                    for (int i = 0; i < isClicks.size(); i++) {
                        isClicks.set(i, false);
                    }
                    isClicks.set(position, true);
                    notifyDataSetChanged();
                    //此处取出值
                    onItemClicks.onItemClicks(myViewHolder.itemView, i);
                }
            }
        });
        //5、记录要更改属性的控件
        myViewHolder.itemView.setTag(myViewHolder.item);
        //6、判断改变属性
        if (isClicks.get(i)) {
            myViewHolder.item.setBackgroundResource(R.drawable.option_shape);
            myViewHolder.item.setTextColor(Color.parseColor("#FFFE5B3A"));
        } else {
            myViewHolder.item.setBackgroundResource(R.drawable.options_shaepe);
            myViewHolder.item.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return variety.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
        }
    }

    //定义一个点击事件的接口
    public interface OnItemClicks {
        void onItemClicks(View view, int i);
    }

    private OnItemClicks onItemClicks;

    //实现那个接口
    public void setOnItemClicks(OnItemClicks onItemClicks) {
        this.onItemClicks = onItemClicks;
    }
}
