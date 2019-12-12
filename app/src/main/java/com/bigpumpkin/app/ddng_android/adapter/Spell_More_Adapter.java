package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.DetailsBean;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Spell_More_Adapter extends RecyclerView.Adapter<Spell_More_Adapter.MyViewHolder> {

    private Context context;
    private List<DetailsBean.DataBean.SingleBean> single;
    HashMap<String, CountDownTimer> timerMap = new HashMap<>();
    ArrayList<String> timerKeyList = new ArrayList<>();

    public Spell_More_Adapter(Context context, List<DetailsBean.DataBean.SingleBean> single) {
        this.context = context;
        this.single = single;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取相应的布局
        View view = LayoutInflater.from(context).inflate(R.layout.spellmore_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (single!=null){
            long etimes = single.get(i).getEtime();
            long time = System.currentTimeMillis();
            long ctime = etimes * 1000;
            long seconds = ctime - time;
            GlideUtils.loadImage(context, single.get(i).getPic(), myViewHolder.cv_head);
            myViewHolder.tv_name.setText(single.get(i).getTitle());
            myViewHolder.tv_farm_name.setText("名称：" + single.get(i).getName());
            myViewHolder.num.setText(single.get(i).getS_num() + "人");

            String id = single.get(i).getId();
            if (!timerKeyList.contains(id)) {
                timerKeyList.add(id);
            }

            // -----一开始初始化数据
            if (myViewHolder.countDownTimer != null) {
                myViewHolder.countDownTimer.cancel();
            }
            myViewHolder.countDownTimer = new CountDownTimer(seconds, 1000) {
                public void onTick(long l) {
                    myViewHolder.etime.setText("剩余" + getTimeStr(l));
                }

                public void onFinish() {

                }
            }.start();
            timerMap.put(id, myViewHolder.countDownTimer);
            myViewHolder.bt_participate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.OnListener(i);
                    }

                }
            });
        }
    }

    private String getTimeStr(long l) {
        long day = l / (1000 * 24 * 60 * 60); //单位天
        long hour = (l - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60); //单位时
        long minute = (l - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60)) / (1000 * 60); //单位分
        long second = (l - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;//单位秒

        String hourStr = String.valueOf(hour);
        if (hourStr.length() == 1) {
            hourStr = "0" + hourStr;
        }
        String minStr = String.valueOf(minute);
        if (minStr.length() == 1) {
            minStr = "0" + minStr;
        }
        String secondStr = String.valueOf(second);
        if (secondStr.length() == 1) {
            secondStr = "0" + secondStr;
        }
        //如果day为0的时候天不显示
        if (day == 0) {
            return hourStr + ":" + minStr + ":" + secondStr;
        } else {
            return day + "天" + " " + hourStr + ":" + minStr + ":" + secondStr;
        }
    }

    public void onDestroy() {
        for (int i = 0; i < timerKeyList.size(); i++) {
            if (timerKeyList.get(i) != null) {
                if (timerMap.get(timerKeyList.get(i)) != null) {
                    CountDownTimer timer = timerMap.get(timerKeyList.get(i));
                    if (timer != null) {
                        timer.cancel();
                    }
                }
            }
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
        private CircleImageView cv_head;
        private TextView tv_name, tv_farm_name, num, etime;
        private Button bt_participate;
        private CountDownTimer countDownTimer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_head = itemView.findViewById(R.id.cv_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_farm_name = itemView.findViewById(R.id.tv_farm_name);
            num = itemView.findViewById(R.id.num);
            etime = itemView.findViewById(R.id.etime);
            bt_participate = itemView.findViewById(R.id.bt_participate);
        }
    }

    /**
     * 定义一个接口
     */
    public interface onListener {
        void OnListener(int i);
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
}
