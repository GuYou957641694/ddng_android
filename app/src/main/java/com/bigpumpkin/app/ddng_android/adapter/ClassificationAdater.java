package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Plants_AdoptBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class ClassificationAdater extends RecyclerView.Adapter<ClassificationAdater.MyViewHolder> {

    private List<Plants_AdoptBean.DataBean.JournalismlenBean> journalismlen;
    private Context context;

    public ClassificationAdater(List<Plants_AdoptBean.DataBean.JournalismlenBean> journalismlen, Context context) {
        this.journalismlen = journalismlen;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.classificationitem, viewGroup, false);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int widthPixels = metrics.widthPixels;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width=widthPixels/4;
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (journalismlen != null) {
            myViewHolder.name.setText(journalismlen.get(i).getTitle());
            Glide.with(context).load(Urls.BASEURL + journalismlen.get(i).getPic()).into(myViewHolder.pic);
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
        return journalismlen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            pic = itemView.findViewById(R.id.pic);
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
