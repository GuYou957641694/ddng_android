package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Focus_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FocusApapter extends RecyclerView.Adapter<FocusApapter.MyViewHolder> {

    private List<Focus_Bean.DataBean> dataBean;
    private Context context;

    public FocusApapter(List<Focus_Bean.DataBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.focus_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (dataBean != null) {
            Glide.with(context).load(Urls.BASEURL + dataBean.get(i).getPic()).into(myViewHolder.picture);
            myViewHolder.name.setText(dataBean.get(i).getTitle());
            myViewHolder.address.setText(dataBean.get(i).getDizhi());

            myViewHolder.focus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(i);
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

        private final TextView name, address;
        private final CircleImageView picture;
        private final Button focus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            picture = itemView.findViewById(R.id.picture);
            address = itemView.findViewById(R.id.address);
            focus = itemView.findViewById(R.id.focus);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
