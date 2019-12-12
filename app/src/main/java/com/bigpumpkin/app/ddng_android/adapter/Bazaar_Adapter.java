package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Bazaar_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;

import java.util.List;

public class Bazaar_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Bazaar_Bean.DataBean.ShopBean> dataBean;
    private Context context;


    // 普通布局
    private final int TYPE_ITEM = 1;
    // 脚布局
    private final int TYPE_FOOTER = 2;
    // 当前加载状态，默认为加载完成
    private int loadState = 2;
    // 正在加载
    public final int LOADING = 1;
    // 加载完成
    public final int LOADING_COMPLETE = 2;
    // 加载到底
    public final int LOADING_END = 3;

    public Bazaar_Adapter(List<Bazaar_Bean.DataBean.ShopBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为FooterView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //获取相应的布局

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.bazaar_itme, viewGroup, false);
            return new RecyclerViewHolder(view);

        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.layout_refresh_footer, viewGroup, false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder myViewHolder, final int i) {
        if (dataBean != null) {
            if (myViewHolder instanceof RecyclerViewHolder) {
                RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) myViewHolder;
                GlideUtils.loadRoundCircleImageTop(context,Urls.BASEURL + dataBean.get(i).getPic(),recyclerViewHolder.pic);
                recyclerViewHolder.name.setText(dataBean.get(i).getTitle());
                Tv_Price_Utils.initPriceTv(context,dataBean.get(i).getPrice(),recyclerViewHolder.price);
                recyclerViewHolder.title.setText(dataBean.get(i).getPrice());
                recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.OnListener(i);
                        }
                    }
                });
            } else if (myViewHolder instanceof FootViewHolder) {
                FootViewHolder footViewHolder = (FootViewHolder) myViewHolder;
                switch (loadState) {
                    case LOADING: // 正在加载
                        footViewHolder.tvLoading.setVisibility(View.VISIBLE);
                        break;

                    case LOADING_COMPLETE: // 加载完成
                        footViewHolder.tvLoading.setVisibility(View.VISIBLE);
                        break;

                    case LOADING_END: // 加载到底
                        footViewHolder.tvLoading.setVisibility(View.VISIBLE);
                        footViewHolder.tvLoading.setText("加载到底");
                        break;
                    default:
                        break;
                }
            }

        }
    }

    @Override
    public int getItemCount() {
        return dataBean.size() + 1;
    }


    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final ImageView pic;
        private final TextView name, price, title;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }
    }

    private class FootViewHolder extends RecyclerView.ViewHolder {

        TextView tvLoading;

        FootViewHolder(View itemView) {
            super(itemView);
            tvLoading = (TextView) itemView.findViewById(R.id.tv_loading);
        }
    }

    /**
     * 定义一个接口
     */
    public interface onListener {
        void OnListener(int id);
    }

    /**
     * 定义一个变量储存数据
     */
    private onListener listener;

    /**
     * 提供公共的方法,并且初始化接口类型的数据
     */
    public void setListener(onListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                    return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 设置上拉加载状态
     *
     * @param loadState 0.正在加载 1.加载完成 2.加载到底
     */
    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }
}
