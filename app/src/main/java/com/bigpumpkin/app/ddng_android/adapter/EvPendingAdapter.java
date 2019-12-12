package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.PendingPay_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;

import java.util.List;

//代付款
public class EvPendingAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<PendingPay_Bean.DataBean> data;

    public EvPendingAdapter(Context context, List<PendingPay_Bean.DataBean> data) {
        this.context = context;
        this.data = data;
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
            convertView = View.inflate(context, R.layout.item_shopping_buy_group, null);

            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.tvStoreName.setText(data.get(groupPosition).getFarm_name());
        return convertView;
    }

    static class GroupViewHolder {
        TextView tvStoreName;

        GroupViewHolder(View view) {
            tvStoreName = view.findViewById(R.id.tv_store_name);

        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.pendingpay_item, null);

            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        PendingPay_Bean.DataBean.ListBean data1 = data.get(groupPosition).getList().get(childPosition);

        String num = data1.getNum();
        if (num != null) {
            childViewHolder.num.setText("×" + num);
        }

        String price = data1.getPrice();
        if (price != null) {
            Tv_Price_Utils.initPrice(context, price, childViewHolder.price);
        }

        String pic = data1.getPic();
        if (pic != null) {
            GlideUtils.loadRoundCircleImagethere(context, Urls.BASEURL + pic, childViewHolder.pic);
        }

        String title = data1.getTitle();
        if (title != null) {
            childViewHolder.farm_name.setText(title);
        }

        String pz_title = data1.getPz_title();
        String gg_title = data1.getGg_title();
        if (gg_title != null && gg_title != null) {
            childViewHolder.varieties.setText("已选：" + pz_title + gg_title);
        }

        String all_num =  data.get(groupPosition).getNum();
        if (all_num != null) {
            childViewHolder.nums.setText("共" + all_num + "件 实付: ");
        }

        String price1 = data.get(groupPosition).getNum_price();
        if (price1 != null) {
            Tv_Price_Utils.initPrice(context, price1, childViewHolder.prices);
        }
        //取消订单
        int finalChildPosition = childPosition;
        childViewHolder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenercancel != null) {
                    listenercancel.OnListenercancel(groupPosition, finalChildPosition);
                }
            }
        });
        //立即支付
        childViewHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.OnListener(groupPosition, finalChildPosition);
                }
            }
        });
        //详情
        int finalChildPosition1 = childPosition;
        childViewHolder.rl_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenerdetails != null) {
                    listenerdetails.OnListenerdetails(groupPosition, finalChildPosition1
                    );
                }
            }
        });
        if (++childPosition == data.get(groupPosition).getList().size()) {
            childViewHolder.lv_cancel.setVisibility(View.VISIBLE);
            childViewHolder.lin.setVisibility(View.VISIBLE);
            childViewHolder.rl.setVisibility(View.VISIBLE);
        } else {
            childViewHolder.lv_cancel.setVisibility(View.GONE);
            childViewHolder.rl.setVisibility(View.GONE);
            childViewHolder.lin.setVisibility(View.GONE);
        }
        return convertView;
    }


    static class ChildViewHolder {
        ImageView pic;
        TextView farm_name;
        TextView varieties;
        TextView price;
        TextView num;
        TextView nums;
        TextView prices;
        TextView confirm;
        TextView cancel;
        LinearLayout lv_cancel, lin;
        RelativeLayout rl, rl_payment;

        ChildViewHolder(View view) {
            pic = view.findViewById(R.id.pic);
            farm_name = view.findViewById(R.id.farm_name);
            varieties = view.findViewById(R.id.varieties);
            price = view.findViewById(R.id.price);
            num = view.findViewById(R.id.num);
            nums = view.findViewById(R.id.nums);
            prices = view.findViewById(R.id.prices);
            confirm = view.findViewById(R.id.confirm);
            cancel = view.findViewById(R.id.cancel);
            lv_cancel = view.findViewById(R.id.lv_cancel);
            lin = view.findViewById(R.id.lin);
            rl = view.findViewById(R.id.rl);
            rl_payment = view.findViewById(R.id.rl_payment);

        }
    }


    //详情
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    //立即支付
    public interface onListener {
        void OnListener(int groupposition, int childposition);
    }

    private onListener listener;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    //取消订单
    public interface onListenercancel {
        void OnListenercancel(int groupposition, int childposition);
    }

    private onListenercancel listenercancel;

    public void setListenercancel(onListenercancel listenercancel) {
        this.listenercancel = listenercancel;
    }

    //详情
    public interface onListenerdetails {
        void OnListenerdetails(int groupposition, int childposition);
    }

    private onListenerdetails listenerdetails;

    public void setListenerdetails(onListenerdetails listenerdetails) {
        this.listenerdetails = listenerdetails;
    }
}
