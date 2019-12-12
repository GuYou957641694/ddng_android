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
import com.bigpumpkin.app.ddng_android.bean.GrowersBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class GrowersAdapter extends RecyclerView.Adapter<GrowersAdapter.MyViewHolder> {

    private Context context;
    private  List<GrowersBean.DataBean> data1;

    public GrowersAdapter(Context context, List<GrowersBean.DataBean> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_growers, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (data1 != null) {
            myViewHolder.tv_name.setText(data1.get(i).getName());
            myViewHolder.tv_title.setText(data1.get(i).getDes());
            GlideUtils.loadCircleImage(context, data1.get(i).getPic(), myViewHolder.tv_pic);
            GlideUtils.loadRoundCircleImage(context, Urls.BASEURL + data1.get(i).getImage_pic(), myViewHolder.tv_head);
            String attention = data1.get(i).getAttention();
            //1已关注 2未关注
            if (attention.equals("1")) {
                myViewHolder.tv_focus.setText("已关注");
            } else if (attention.equals("2")) {
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
        }
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_title, tv_focus;
        private ImageView tv_pic, tv_head;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_head = itemView.findViewById(R.id.tv_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_pic = itemView.findViewById(R.id.tv_pic);
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
