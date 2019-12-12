package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Farm_TypeBean;

import java.util.List;

public class FarmType_Adapter extends RecyclerView.Adapter<FarmType_Adapter.ViewHolder> {

    private Context context;
    private List<Farm_TypeBean> list;

    public FarmType_Adapter(Context context, List<Farm_TypeBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_farmtype, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.cb_id.setText(list.get(i).getName());
        viewHolder.cb_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.get(i).setRype(true);
                } else {
                    list.get(i).setRype(false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb_id = itemView.findViewById(R.id.cb_id);
        }
    }
}
