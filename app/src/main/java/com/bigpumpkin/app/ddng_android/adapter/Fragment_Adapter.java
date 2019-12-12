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
import com.bigpumpkin.app.ddng_android.bean.Fragment_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class Fragment_Adapter extends RecyclerView.Adapter<Fragment_Adapter.MyViewHolder> {

    private List<Fragment_Bean.DataBean.ShopBean> dataBean;
    private Context context;
    private View view;

    public Fragment_Adapter(List<Fragment_Bean.DataBean.ShopBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        view = LayoutInflater.from(context).inflate(R.layout.fragmentone_itme, viewGroup, false);
        final Fragment_Adapter.MyViewHolder holder = new Fragment_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (dataBean != null) {
            Glide.with(context).load(Urls.BASEURL + dataBean.get(i).getPic()).into(myViewHolder.pic);
            myViewHolder.name.setText(dataBean.get(i).getTitle());
            myViewHolder.price.setText(dataBean.get(i).getPic() + "元");
            view.setOnClickListener(new View.OnClickListener() {
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
        return dataBean.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView pic;
        private final TextView name, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
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
