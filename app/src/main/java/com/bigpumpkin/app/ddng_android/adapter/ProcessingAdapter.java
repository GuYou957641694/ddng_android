package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.ProcessingBean;

import java.util.List;

public class ProcessingAdapter extends RecyclerView.Adapter<ProcessingAdapter.MyViewHolder> {

    private Context context;
    private int item;
    List<ProcessingBean.DataBean.ArchiveslistBean> archiveslist;
    // recyclerView 选中Item 的颜色
    private int defaultSelectedColor = Color.parseColor("#FF1C1C1C");
    // recyclerView 未选中Item 的颜色
    private int defaultUnSelectedColor = Color.parseColor("#FF656565");
    private itemOnClick itemOnclick;

    public ProcessingAdapter(Context context, List<ProcessingBean.DataBean.ArchiveslistBean> archiveslist) {
        this.context = context;
        this.archiveslist = archiveslist;
    }
    //获得点击的下标 由外部传进来进行判断哪个要放大（即点即了哪个或者选中了哪个）
    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.processing_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tv_name.setText(archiveslist.get(i).getTitle());
        if (item==0){
            if (i + 1 == 1) {
                myViewHolder.view.setVisibility(View.VISIBLE);
                myViewHolder.tv_name.setTextColor(defaultSelectedColor);
                myViewHolder.lin.setBackgroundResource(R.color.white);
            } else {
                myViewHolder.view.setVisibility(View.GONE);
                myViewHolder.tv_name.setTextColor(defaultUnSelectedColor);
                myViewHolder.lin.setBackgroundResource(R.color.bgUnSelectedColor);
            }
        }else if (getItem() == i){
            myViewHolder.view.setVisibility(View.VISIBLE);
            myViewHolder.tv_name.setTextColor(defaultSelectedColor);
            myViewHolder.lin.setBackgroundResource(R.color.white);
        }else {
            myViewHolder.view.setVisibility(View.GONE);
            myViewHolder.tv_name.setTextColor(defaultUnSelectedColor);
            myViewHolder.lin.setBackgroundResource(R.color.bgUnSelectedColor);
        }


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item监听
                if (itemOnclick != null)
                    itemOnclick.OnClick(i);
            }
        });
    }

    //重写方法保证不会数据错乱
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return archiveslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private View view;
        private LinearLayout lin;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            tv_name = itemView.findViewById(R.id.tv_name);
            lin = itemView.findViewById(R.id.lin);
        }
    }

    //设置回调接口
    public void setItemOnClick(itemOnClick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    //点击事件回调出去
    public interface itemOnClick {
        void OnClick(int item);
    }
}
