package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
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
            myViewHolder.address.setText("[默认]" + data1.get(i).getSheng() + data1.get(i).getShi() + data1.get(i).getAddress());
            myViewHolder.name.setText(data1.get(i).getName());
            int isindex = data1.get(i).getIsindex();
            if (isindex == 1) {
                myViewHolder.defaultsss.setChecked(true);
                myViewHolder.address.setText("[默认]" + data1.get(i).getSheng() + data1.get(i).getShi() + data1.get(i).getAddress());
            } else {
                myViewHolder.address.setText(data1.get(i).getSheng() + data1.get(i).getShi() + data1.get(i).getAddress());

            }


            //删除收货地址
            myViewHolder.dele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(i);
                    }
                }
            });
            //设置默认
            myViewHolder.defaultsss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (defaultlistener != null) {
                        defaultlistener.ondefaultClick(i);
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

        private final TextView name, phone, address, default_address;
        private final CheckBox defaultsss;
        private final ImageView dele, manuscripts;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            defaultsss = itemView.findViewById(R.id.defaultsss);
            default_address = itemView.findViewById(R.id.default_address);
            dele = itemView.findViewById(R.id.dele);
            manuscripts = itemView.findViewById(R.id.manuscripts);
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

    //设置默认
    public interface OnItemdefaultClickListener {
        void ondefaultClick(int position);
    }

    private OnItemdefaultClickListener defaultlistener;

    public void setOnItemdefaultClickListener(OnItemdefaultClickListener defaultlistener) {
        this.defaultlistener = defaultlistener;
    }
}
