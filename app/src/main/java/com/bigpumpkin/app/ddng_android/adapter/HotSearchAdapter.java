package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;

import java.util.List;

public class HotSearchAdapter  extends RecyclerView.Adapter<HotSearchAdapter.MyViewHolder>{

    private Context context;
    private List<String> lists;

    public HotSearchAdapter(Context context, List<String> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取对应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.home_search_hotrv_layout, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                if (listener != null) {
                    listener.onSearchWordsSuccess(position);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(lists.get(i));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.home_search_hotrv_name);

        }
    }
    //自定义一个接口回掉
    public interface onSearchWordsListener {
        void onSearchWordsSuccess(int posiion);

    }

    onSearchWordsListener listener;

    //提供一个公共的接口监听方法
    public void setOnSearchWordsListener(onSearchWordsListener listener) {
        this.listener = listener;
    }

}
