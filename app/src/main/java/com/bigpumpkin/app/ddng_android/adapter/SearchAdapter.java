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
import com.bigpumpkin.app.ddng_android.bean.SearchBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bumptech.glide.Glide;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SearchBean data1;
    private Context context;
    public final static int FOOT = 1003;//正常列表的viewType
    public final static int BODY = 1002;//横向列表的viewType

    public SearchAdapter(SearchBean data1, Context context) {
        this.data1 = data1;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = data1.getViewType();
        if (viewType == FOOT) {
            return FOOT;
        } else if (viewType == BODY) {
            return BODY;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case FOOT:
                View view = LayoutInflater.from(context).inflate(R.layout.srarch_products_item, viewGroup, false);
                final MyViewHolder holder = new MyViewHolder(view);
                return holder;
            case BODY:
                View views = LayoutInflater.from(context).inflate(R.layout.srarch_products_items, viewGroup, false);
                return new MyViewHolders(views);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder myViewHolder, int i) {
        if (data1.getData().size() > 0) {
            if (myViewHolder instanceof MyViewHolder) {
                MyViewHolder myViewHolder1 = (MyViewHolder) myViewHolder;
                myViewHolder1.name.setText(data1.getData().get(i).getTitle());
                myViewHolder1.production.setText("产量：" + data1.getData().get(i).getChanliang());
                String price = data1.getData().get(i).getPrice();

                String str = "\u00a5".concat(price);
                SpannableString spannableString = new SpannableString(str);
                RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.6f);
                spannableString.setSpan(relativeSizeSpan, 1, price.length() - 2,
                        Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                AssetManager mgr = context.getAssets();
                Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
                myViewHolder1.price.setTypeface(tf);
                myViewHolder1.price.setText(spannableString);
                myViewHolder1.number.setText(data1.getData().get(i).getSales_volume() + "人购买");
                GlideUtils.loadRoundCircleImage(context, Urls.BASEURL + data1.getData().get(i).getPic(), myViewHolder1.pic);
                myViewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.OnListener(i);
                        }
                    }
                });
                myViewHolder1.onfo_farm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listenerfarm != null) {
                            listenerfarm.OnListenerfarm(i);
                        }
                    }
                });
            } else if (myViewHolder instanceof MyViewHolders) {
                MyViewHolders myViewHolders1 = (MyViewHolders) myViewHolder;
                myViewHolders1.name.setText(data1.getData().get(i).getTitle());
                myViewHolders1.production.setText("产量：" + data1.getData().get(i).getChanliang());
                String price = data1.getData().get(i).getPrice();

                String str = "\u00a5".concat(price);
                SpannableString spannableString = new SpannableString(str);
                RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.6f);
                spannableString.setSpan(relativeSizeSpan, 1, price.length() - 2,
                        Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                AssetManager mgr = context.getAssets();
                Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
                myViewHolders1.price.setTypeface(tf);
                myViewHolders1.price.setText(spannableString);
                myViewHolders1.number.setText(data1.getData().get(i).getSales_volume() + "人购买");
                Glide.with(context).load(Urls.BASEURL + data1.getData().get(i).getPic()).into(myViewHolders1.pic);
                myViewHolders1.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.OnListener(i);
                        }
                    }
                });
                myViewHolders1.onfo_farm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listenerfarm != null) {
                            listenerfarm.OnListenerfarm(i);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return data1.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, production, price, number, onfo_farm;
        private ImageView pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            pic = itemView.findViewById(R.id.pic);
            production = itemView.findViewById(R.id.production);
            price = itemView.findViewById(R.id.price);
            number = itemView.findViewById(R.id.number);
            onfo_farm = itemView.findViewById(R.id.onfo_farm);
        }
    }

    public class MyViewHolders extends RecyclerView.ViewHolder {

        private TextView name, production, price, number, onfo_farm;
        private ImageView pic;

        public MyViewHolders(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            pic = itemView.findViewById(R.id.pic);
            production = itemView.findViewById(R.id.production);
            price = itemView.findViewById(R.id.price);
            number = itemView.findViewById(R.id.number);
            onfo_farm = itemView.findViewById(R.id.onfo_farm);
        }
    }

    public interface onListener {
        void OnListener(int i);
    }

    private onListener listener;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    public interface onListenerfarm {
        void OnListenerfarm(int i);
    }

    private onListenerfarm listenerfarm;

    public void setListenerfarm(onListenerfarm listenerfarm) {
        this.listenerfarm = listenerfarm;
    }
}
