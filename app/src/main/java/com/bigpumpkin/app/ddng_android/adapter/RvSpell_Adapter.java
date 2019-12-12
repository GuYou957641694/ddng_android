package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Adopt_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

//拼单更实惠
public class RvSpell_Adapter extends RecyclerView.Adapter<RvSpell_Adapter.MyViewHolder> {


    private List<Adopt_Bean.DataBean.SingleBean> single;
    private Context context;

    public RvSpell_Adapter(List<Adopt_Bean.DataBean.SingleBean> single, Context context) {
        this.single = single;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.rvspell_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (single != null) {
            GlideUtils.loadRoundCircleImage(context,Urls.BASEURL + single.get(i).getPic(),myViewHolder.iv_pic);
            myViewHolder.iv_name.setText(single.get(i).getTitle());
            myViewHolder.iv_gg.setText(single.get(i).getChanliang());
            myViewHolder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.OnListener(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (single != null && single.size() > 0) {
            return single.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pic;
        private TextView iv_name;
        private TextView iv_gg;
        private RelativeLayout rl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            iv_name = itemView.findViewById(R.id.tv_name);
            iv_gg = itemView.findViewById(R.id.tv_gg);
            rl = itemView.findViewById(R.id.rl);
        }
    }
    /**
     * 定义一个接口
     */
    public interface  onListener{
        void OnListener(int id);
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
