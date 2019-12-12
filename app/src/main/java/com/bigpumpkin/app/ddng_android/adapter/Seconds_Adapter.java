package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
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
import com.bigpumpkin.app.ddng_android.bean.GoodsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class Seconds_Adapter extends RecyclerView.Adapter<Seconds_Adapter.MyViewHolder> {

    private List<GoodsBean.DataBean.AddonspecBean.ShopBean> dataBean;
    private Context context;

    public Seconds_Adapter(List<GoodsBean.DataBean.AddonspecBean.ShopBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.seconds_item, viewGroup, false);
        final Seconds_Adapter.MyViewHolder holder = new Seconds_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (dataBean != null) {
            Glide.with(context).load(Urls.BASEURL + dataBean.get(i).getPic()).into(myViewHolder.pic);
            String v_price = dataBean.get(i).getV_price();
            String spike_price = dataBean.get(i).getSpike_price();
            String str = "￥" + spike_price;
            SpannableString spannableString = new SpannableString(str);
            RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.4f);
            spannableString.setSpan(relativeSizeSpan, 1, spike_price.length()-1,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            AssetManager mgr = context.getAssets();
            Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
            myViewHolder.prices.setTypeface(tf);
            myViewHolder.prices.setText(spannableString);
            myViewHolder.price.setText("￥" + v_price);
            myViewHolder.price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中划线
        }
    }

    @Override
    public int getItemCount() {
        return dataBean.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView pic;
        private final TextView prices, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
            prices = itemView.findViewById(R.id.prices);
            price = itemView.findViewById(R.id.price);
        }
    }

}
