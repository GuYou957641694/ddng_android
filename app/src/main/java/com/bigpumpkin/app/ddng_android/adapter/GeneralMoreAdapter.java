package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.MilkMoreBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class GeneralMoreAdapter  extends RecyclerView.Adapter<GeneralMoreAdapter.MyViewHolder>{

    private Context context;
    private  List<MilkMoreBean.DataBean> data1;

    public GeneralMoreAdapter(Context context, List<MilkMoreBean.DataBean> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (data1 != null) {
            myViewHolder.name.setText(data1.get(i).getTitle());
            myViewHolder.production.setText("产量：" + data1.get(i).getChanliang());
            String price = data1.get(i).getPrice();

            String str = "\u00a5".concat(price);
            SpannableString spannableString = new SpannableString(str);
            RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.6f);
            spannableString.setSpan(relativeSizeSpan, 1, price.length() - 2,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            AssetManager mgr = context.getAssets();
            Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
            myViewHolder.price.setTypeface(tf);
            myViewHolder.price.setText(spannableString);
            GlideUtils.loadRoundCircleImageTop(context, Urls.BASEURL + data1.get(i).getPic(),myViewHolder.pic);
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
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, production, price;
        private ImageView pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            pic = itemView.findViewById(R.id.pic);
            production = itemView.findViewById(R.id.production);
            price = itemView.findViewById(R.id.price);
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
