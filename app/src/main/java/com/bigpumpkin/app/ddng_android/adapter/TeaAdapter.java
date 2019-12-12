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
import com.bigpumpkin.app.ddng_android.bean.WisdomBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class TeaAdapter extends RecyclerView.Adapter<TeaAdapter.MyViewHolder> {

    private Context context;
    private List<WisdomBean.DataBean.Essay4Bean> essay4;

    public TeaAdapter(Context context, List<WisdomBean.DataBean.Essay4Bean> essay4) {
        this.context = context;
        this.essay4 = essay4;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.tes_item, viewGroup, false);
        final TeaAdapter.MyViewHolder holder = new TeaAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (essay4 != null) {
            GlideUtils.loadRoundCircleImagetwo(context, Urls.BASEURL + essay4.get(i).getPic(), myViewHolder.tv_pic);
            myViewHolder.tv_name.setText(essay4.get(i).getTitle());
            myViewHolder.tv_title.setText(essay4.get(i).getDes());
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
        if (essay4 != null && essay4.size() > 0) {
            return essay4.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView tv_pic;
        private TextView tv_name,tv_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pic = itemView.findViewById(R.id.tv_pic);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
    public interface  onListener{
        void OnListener(int  i);
    }
    private onListener listener;
    public void setListener( onListener listener){
        this.listener = listener;
    }
}
