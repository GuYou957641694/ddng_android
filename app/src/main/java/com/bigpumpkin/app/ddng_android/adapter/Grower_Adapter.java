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
import com.bigpumpkin.app.ddng_android.activity.SpellGrowBean;
import com.bigpumpkin.app.ddng_android.activity.VideoActivity;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Grower_Adapter extends RecyclerView.Adapter<Grower_Adapter.MyViewHolder> {

    private Context context;
    private List<SpellGrowBean.DataBean> data1;

    public Grower_Adapter(Context context, List<SpellGrowBean.DataBean> data1) {
        this.context = context;
        this.data1 = data1;
    }


    public void setData1(List<SpellGrowBean.DataBean> data1) {
        this.data1 = data1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.grower_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tv_name.setText(data1.get(i).getName());
        myViewHolder.tv_title.setText(data1.get(i).getDes());
        GlideUtils.loadImage(context, data1.get(i).getPic(), myViewHolder.cg_head);
        GlideUtils.loadRoundCircleImagetwo(context, Urls.BASEURL + data1.get(i).getImage_pic(), myViewHolder.tv_pic);
        //	1已关注 2未关注
        if (data1.get(i).getAttention().equals("1")) {
            myViewHolder.tv_focus.setText("已关注");
        } else if (data1.get(i).getAttention().equals("2")) {
            myViewHolder.tv_focus.setText("关注");
        }
        myViewHolder.tv_focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.OnListener(i);
                }
            }
        });
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id",data1.get(i).getFid());
                IntentUtils.getIntents().Intent(context, VideoActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data1 != null && data1.size() > 0) {
            return data1.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView tv_pic;
        private CircleImageView cg_head;
        private TextView tv_name;
        private TextView tv_title;
        private TextView tv_focus;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pic = itemView.findViewById(R.id.tv_pic);
            cg_head = itemView.findViewById(R.id.cg_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_focus = itemView.findViewById(R.id.tv_focus);
        }
    }
    /**
     * 定义一个接口
     */
    public interface  onListener{
        void OnListener(int i);
    }
    /**
     *定义一个变量储存数据
     */
    private onListener listener;
    /**
     *提供公共的方法,并且初始化接口类型的数据
     */
    public void setListener( onListener listener){
        this.listener = listener;
    }
}
