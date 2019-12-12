package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Show_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.TimeUtils;
import com.bigpumpkin.app.ddng_android.weight.NineGridTestLayout;
import com.bigpumpkin.app.ddng_android.weight.OnItemPictureClickListener;

import java.util.ArrayList;
import java.util.List;

public class Farm_Show_Adapter extends RecyclerView.Adapter<Farm_Show_Adapter.MyViewHolder> {

    private static final String TAG = "aa";
    private Context context;
    private List<Show_Bean.DataBean> data1;
    List<String> urlList = new ArrayList<>();
    private OnItemPictureClickListener listener;

    public Farm_Show_Adapter(Context context, List<Show_Bean.DataBean> data1, OnItemPictureClickListener listener) {
        this.context = context;
        this.data1 = data1;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.farm_show_item, viewGroup, false);
        final Farm_Show_Adapter.MyViewHolder holder = new Farm_Show_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (data1 != null) {
            myViewHolder.tv_title.setText(data1.get(i).getText());
            myViewHolder.tv_time.setText( TimeUtils.getDateTimeFromMillisecond(data1.get(i).getT_time()));
            List<Show_Bean.DataBean.ImgBean> img = data1.get(i).getImg();
            myViewHolder.nineGridTestLayout.setListener(listener);
            for (int j = 0; j < data1.get(i).getImg().size(); j++) {
                urlList.add(Urls.BASEURL + img.get(i).getLink());
            }
            myViewHolder.nineGridTestLayout.setItemPosition(i);
            myViewHolder.nineGridTestLayout.setSpacing(15);
            myViewHolder.nineGridTestLayout.setUrlList(urlList);
        }
    }

    @Override
    public int getItemCount() {
        if (data1 != null && data1.size() > 0) {
            return data1.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time;
        private TextView tv_title;
        private NineGridTestLayout nineGridTestLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_title = itemView.findViewById(R.id.tv_title);
            nineGridTestLayout = itemView.findViewById(R.id.nineTestlayout);
        }
    }

}
