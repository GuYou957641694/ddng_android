package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
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
import com.bigpumpkin.app.ddng_android.activity.Spell_DetailsActivity;
import com.bigpumpkin.app.ddng_android.bean.Farm_index_productBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bumptech.glide.Glide;

public class Farm_ProductAdapter extends RecyclerView.Adapter<Farm_ProductAdapter.MyViewHolder> {

    private Context context;
    private  Farm_index_productBean farm_index_productBean;
    public final static int FOOT = 1003;//正常列表的viewType
    public final static int BODY = 1002;//横向列表的viewType
    public Farm_ProductAdapter(Context context,  Farm_index_productBean farm_index_productBean) {
        this.context = context;
        this.farm_index_productBean = farm_index_productBean;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = farm_index_productBean.getViewType();
        if (viewType == FOOT){
            return FOOT;
        }else if (viewType == BODY){
            return BODY;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i){
            case FOOT:
               View   view = LayoutInflater.from(context).inflate(R.layout.farm_products_item, viewGroup, false);
                final MyViewHolder holder = new MyViewHolder(view);
                return holder;
            case BODY:
                 View  views= LayoutInflater.from(context).inflate(R.layout.farm_producu_items, viewGroup, false);
                final MyViewHolder holders = new MyViewHolder(views);
                return holders;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (farm_index_productBean.getData() != null) {
            myViewHolder.name.setText(farm_index_productBean.getData().get(i).getTitle());
            myViewHolder.production.setText("产量：" + farm_index_productBean.getData().get(i).getChanliang());
            String price = farm_index_productBean.getData().get(i).getPrice();

            String str = "\u00a5".concat(price);
            SpannableString spannableString = new SpannableString(str);
            RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.6f);
            spannableString.setSpan(relativeSizeSpan, 1, price.length() - 2,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            AssetManager mgr = context.getAssets();
            Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
            myViewHolder.price.setTypeface(tf);
            myViewHolder.price.setText(spannableString);
            Glide.with(context).load(Urls.BASEURL + farm_index_productBean.getData().get(i).getPic()).into(myViewHolder.pic);
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id",farm_index_productBean.getData().get(i).getId());
                    IntentUtils.getIntents().Intent(context, Spell_DetailsActivity.class,bundle);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return farm_index_productBean.getData().size();
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


    /**
     * 定义一个接口
     */
    public interface  onListener{
        void OnListener(int i);
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
