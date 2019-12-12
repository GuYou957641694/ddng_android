package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Address_Bean;

import java.util.List;

public class Address_Adapter extends RecyclerView.Adapter<Address_Adapter.MyViewHolder> {

    private List<Address_Bean.DataBean> data1;
    private Context context;

    public Address_Adapter(List<Address_Bean.DataBean> data1, Context context) {
        this.data1 = data1;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.address_item, viewGroup, false);
        final Address_Adapter.MyViewHolder holder = new Address_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (data1 != null) {
            myViewHolder.name.setText(data1.get(i).getName());
            myViewHolder.phone.setText(data1.get(i).getTel());
            myViewHolder.address.setText(data1.get(i).getSheng() + data1.get(i).getShi() + data1.get(i).getQu() + data1.get(i).getAddress());
            myViewHolder.name.setText(data1.get(i).getName());
            int isindex = data1.get(i).getIsindex();
            if (isindex == 1) {
                myViewHolder.rv_default.setVisibility(View.VISIBLE);
            } else {
                myViewHolder.rv_default.setVisibility(View.GONE);
            }
            myViewHolder.tv_modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(i);
                    }
                }
            });
            myViewHolder.menu_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (defaultlistener != null) {
                        defaultlistener.ondefaultClick(i);
                    }
                }
            });
            if (i + 1 == data1.size()) {
                myViewHolder.view.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, phone, address;
        private RelativeLayout rv_default;
        private ImageView tv_modify;
        private LinearLayout menu_layout;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            rv_default = itemView.findViewById(R.id.rv_default);
            tv_modify = itemView.findViewById(R.id.tv_modify);
            menu_layout = itemView.findViewById(R.id.menu_layout);
            view = itemView.findViewById(R.id.view);
        }
    }

    //删除
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemdefaultClickListener {
        void ondefaultClick(int position);
    }

    private OnItemdefaultClickListener defaultlistener;

    public void setOnItemdefaultClickListener(OnItemdefaultClickListener defaultlistener) {
        this.defaultlistener = defaultlistener;
    }
}
