package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Address_Bean;

import java.util.List;

public class SpellAddressAdapter extends RecyclerView.Adapter<SpellAddressAdapter.MyViewHolder> {

    private List<Address_Bean.DataBean> data1;
    private Context context;
    private String id;

    public SpellAddressAdapter(List<Address_Bean.DataBean> data1, Context context, String id) {
        this.data1 = data1;
        this.context = context;
        this.id = id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.spelladdress_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (data1 != null) {
            myViewHolder.name.setText(data1.get(i).getName());
            myViewHolder.phone.setText(data1.get(i).getTel());
            myViewHolder.address.setText(data1.get(i).getSheng() + data1.get(i).getShi() + data1.get(i).getAddress());
            myViewHolder.name.setText(data1.get(i).getName());
            if (id != null) {
                if (id.equals(data1.get(i).getId())) {
                    myViewHolder.name.setTextColor(Color.parseColor("#FFFF470E"));
                    myViewHolder.phone.setTextColor(Color.parseColor("#FFFF470E"));
                    myViewHolder.iv_default.setChecked(true);
                    myViewHolder.iv_default.setVisibility(View.VISIBLE);
                } else {
                    myViewHolder.iv_default.setVisibility(View.GONE);
                }
            }

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
        if (data1 != null && data1.size() > 0) {
            return data1.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, phone, address;
        CheckBox iv_default;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            iv_default = itemView.findViewById(R.id.iv_default);
        }

    }

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

    //删除
    public interface OnItemelClickListener {
        void onClick(int position);
    }

    private OnItemelClickListener listenerel;

    public void setOnItemelClickListener(OnItemelClickListener listenerel) {
        this.listenerel = listenerel;
    }
}
