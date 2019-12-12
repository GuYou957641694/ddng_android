package com.bigpumpkin.app.ddng_android.adapter;

import android.annotation.SuppressLint;
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
import com.bigpumpkin.app.ddng_android.bean.EvaluationDetailsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.donkingliang.labels.LabelsView;

import java.util.ArrayList;
import java.util.List;

public class EvaluationDetailsAdapter extends RecyclerView.Adapter<EvaluationDetailsAdapter.MyViewHolder> {

    private Context context;
    private List<EvaluationDetailsBean.DataBean> data1;
    private ArrayList<String> label = new ArrayList<>();
    private GridImageAdapter adapter;
    int maxSelectNum = 3;
    public EvaluationDetailsAdapter(Context context, List<EvaluationDetailsBean.DataBean> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.evaluationdetails_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        GlideUtils.loadRoundCircleImage(context, Urls.BASEURL + data1.get(i).getPic(), myViewHolder.pic);
        myViewHolder.farm_name.setText(data1.get(i).getTitle());
        myViewHolder.gg_title.setText(data1.get(i).getPz_title() + data1.get(i).getGg_title());
        List<EvaluationDetailsBean.DataBean.EvaluationDimensionBean> evaluation_dimension = data1.get(i).getEvaluation_dimension();
        for (int j = 0; j < data1.get(i).getEvaluation_dimension().size(); j++) {
            label.add(data1.get(i).getEvaluation_dimension().get(j).getTitle());
            myViewHolder.labeled.setLabels(label);
        }
        //标签的点击监听
        myViewHolder.labeled.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                //label是被点击的标签，data是标签所对应的数据，position是标签的位置。
            }
        });
        FullyGridLayoutManager manager = new FullyGridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);
        myViewHolder.add_images.setLayoutManager(manager);
        adapter = new GridImageAdapter(context, onAddPicClickListener);
        adapter.setSelectMax(maxSelectNum);
        myViewHolder.add_images.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (listener!=null) {
                    listener.OnListener();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView pic;
        TextView farm_name;
        TextView gg_title;
        LabelsView labeled;
        RecyclerView add_images;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
            farm_name = itemView.findViewById(R.id.farm_name);
            gg_title = itemView.findViewById(R.id.gg_title);
            labeled = itemView.findViewById(R.id.labeled);
            ///add_images = itemView.findViewById(R.id.add_images);
        }
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @SuppressLint("CheckResult")
        @Override
        public void onAddPicClick() {

        }
    };

    /**
     * 定义一个接口
     */
    public interface  onListener{
        void OnListener();
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
