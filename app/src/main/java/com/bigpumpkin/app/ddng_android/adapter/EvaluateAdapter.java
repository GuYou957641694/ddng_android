package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.EvaluationDetailsActivity;
import com.bigpumpkin.app.ddng_android.activity.OrdinaryActivity;
import com.bigpumpkin.app.ddng_android.bean.EvaluateBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;

import java.util.List;

//待评价
public class EvaluateAdapter extends BaseExpandableListAdapter {

    private List<EvaluateBean.DataBean> data;
    private Context context;

    public EvaluateAdapter(List<EvaluateBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (data.get(groupPosition).getList() != null && data.get(groupPosition).getList().size() > 0) {
            return data.get(groupPosition).getList().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.good_order_group, null);

            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.tvStoreName.setText(data.get(groupPosition).getFarm_name());
        groupViewHolder.state.setText("交易成功");
        return convertView;
    }

    static class GroupViewHolder {
        TextView tvStoreName;
        TextView state;

        GroupViewHolder(View view) {
            tvStoreName = view.findViewById(R.id.tv_store_name);
            state = view.findViewById(R.id.state);

        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.evaluate_item, null);

            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        EvaluateBean.DataBean.ListBean listBean = data.get(groupPosition).getList().get(childPosition);
        String title = listBean.getTitle();
        String pic = listBean.getPic();
        String gg_title = listBean.getGg_title();
        String pz_title = listBean.getPz_title();
        String pay_price = listBean.getPay_price();
        String num = listBean.getNum();
        GlideUtils.loadRoundCircleImagethere(context, Urls.BASEURL + pic, childViewHolder.pic);
        childViewHolder.farm_name.setText(title);
        childViewHolder.varieties.setText("已选：" + pz_title + gg_title);
        Tv_Price_Utils.initPrice(context, pay_price, childViewHolder.price);
        childViewHolder.num.setText("×" + num);

        //待评价详情
        childViewHolder.rl_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  1 订单 2拼单发起 3拼单参与 4认养 5公益
                String type = data.get(groupPosition).getType();
                    Bundle bundle = new Bundle();
                    bundle.putString("id",data.get(groupPosition).getOrder_id());
                    IntentUtils.getIntents().Intent(context, OrdinaryActivity.class, bundle);
                    //IntentUtils.getIntents().Intent(context, AdoptionEvaluationDetailsActivity.class, null);

            }
        });
        //评价
        childViewHolder.evaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //评价
                Bundle bundle = new Bundle();
                IntentUtils.getIntents().Intent(context, EvaluationDetailsActivity.class, null);
            }
        });

        String all_num = data.get(groupPosition).getNum();
        if (all_num != null) {
            childViewHolder.nums.setText("共" + all_num + "件 实付: ");
        }

        String price1 = data.get(groupPosition).getPrice();
        if (price1 != null) {
            Tv_Price_Utils.initPrice(context, price1, childViewHolder.prices);
        }

        if (++childPosition == data.get(groupPosition).getList().size()) {
            childViewHolder.lv_cancel.setVisibility(View.VISIBLE);
            childViewHolder.lin.setVisibility(View.VISIBLE);
            childViewHolder.rl.setVisibility(View.VISIBLE);
        } else {
            childViewHolder.lv_cancel.setVisibility(View.GONE);
            childViewHolder.lin.setVisibility(View.GONE);
            childViewHolder.rl.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class ChildViewHolder {
        ImageView pic;
        TextView farm_name;
        TextView varieties;
        TextView price;
        TextView num;
        TextView delete;
        TextView evaluation;
        TextView nums;
        TextView prices;
        LinearLayout lv_cancel, lin;
        RelativeLayout rl_payment, rl;

        ChildViewHolder(View view) {
            pic = view.findViewById(R.id.pic);
            farm_name = view.findViewById(R.id.farm_name);
            varieties = view.findViewById(R.id.varieties);
            price = view.findViewById(R.id.price);
            num = view.findViewById(R.id.num);
            lv_cancel = view.findViewById(R.id.lv_cancel);
            lin = view.findViewById(R.id.lin);
            rl_payment = view.findViewById(R.id.rl_payment);
            evaluation = view.findViewById(R.id.evaluation);
            nums = view.findViewById(R.id.nums);
            prices = view.findViewById(R.id.prices);
            rl = view.findViewById(R.id.rl);

        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    //删除
    public interface ondeleteListener {
        void OndeleteListener(int order_status, String orderid);
    }

    private ondeleteListener deletelistener;

    public void setdeleteListener(ondeleteListener deletelistener) {
        this.deletelistener = deletelistener;
    }


}
