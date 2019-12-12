package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.DetailsBean;

import java.util.ArrayList;
import java.util.List;

public class Details_Adapter extends RecyclerView.Adapter<Details_Adapter.MyViewHolder> {

    private List<DetailsBean.DataBean.VarietyBean> variety;
    private Context context;
    //1、定义一个集合，用来记录选中
    private List<Boolean> isClicks;

    public Details_Adapter(List<DetailsBean.DataBean.VarietyBean> variety, Context context) {
        this.variety = variety;
        this.context = context;
        //3、为集合添加值
        isClicks = new ArrayList<>();
        for (int i = 0; i < variety.size(); i++) {
            isClicks.add(false);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.details, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        if (variety != null) {
            myViewHolder.text.setText(variety.get(i).getTitle());

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null) {
                        int position = myViewHolder.getLayoutPosition();
                        for (int i = 0; i < isClicks.size(); i++) {
                            isClicks.set(i, false);
                        }
                        isClicks.set(position, true);
                        notifyDataSetChanged();
                        onItemClick.onItemClick(myViewHolder.itemView, position);
                    }
                }
            });

            //5、记录要更改属性的控件
            myViewHolder.itemView.setTag(myViewHolder.text);

            //6、判断改变属性
            if (isClicks.get(i)) {
                myViewHolder.text.setBackgroundResource(R.drawable.option_shape);
                myViewHolder.text.setTextColor(Color.parseColor("#FFFE5B3A"));
            } else {
                myViewHolder.text.setBackgroundResource(R.drawable.options_shaepe);
                myViewHolder.text.setTextColor(Color.parseColor("#000000"));
            }
        }
    }


    @Override
    public int getItemCount() {
        if (variety != null && variety.size() > 0) {
            return variety.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tv_ItemName);
        }
    }

    //定义一个点击事件的接口
    public interface OnItemClick {
        void onItemClick(View view, int position);
    }

    private OnItemClick onItemClick;

    //实现那个接口
    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
