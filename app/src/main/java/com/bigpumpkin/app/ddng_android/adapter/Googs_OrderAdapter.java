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
import com.bigpumpkin.app.ddng_android.activity.PendingReceiptDetailssActivity;
import com.bigpumpkin.app.ddng_android.bean.For_GoodsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;

import java.util.List;

//待收货
public class Googs_OrderAdapter extends BaseExpandableListAdapter {

    private List<For_GoodsBean.DataBean> data;
    private Context context;

    public Googs_OrderAdapter(List<For_GoodsBean.DataBean> data, Context context) {
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
    public boolean isChildSelectable(int groupPosition, int childPosition) {
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
        int statu = data.get(groupPosition).getStatu();
        //当前状态 1代发货 2 部分发货 3待收货
        if (statu == 1) {
            groupViewHolder.state.setText("代发货");
        } else if (statu == 2) {
            groupViewHolder.state.setText("部分发货");
        } else if (statu == 3) {
            groupViewHolder.state.setText("待收货");
        }
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
            convertView = View.inflate(context, R.layout.for_good_fragmnet_item, null);

            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        For_GoodsBean.DataBean.ListBean data1 = data.get(groupPosition).getList().get(childPosition);
        int statu = data.get(groupPosition).getStatu();

        if (statu == 3) {
            childViewHolder.cancel.setVisibility(View.VISIBLE);
        }

        String num = data1.getNum();
        if (num != null) {
            childViewHolder.num.setText("×" + num);
        }

        String price = data1.getPay_price();
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

        //查看物流
        childViewHolder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    //1普通商品物流页面 2认养物流页面
                    listener.OnListener(data.get(groupPosition).getView_Logistics(), data.get(groupPosition).getOrder_id());
                }
            }
        });

        //确认收货
        childViewHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancellistener != null) {
                    //1普通商品物流页面 2认养物流页面
                    cancellistener.OncancelListener(data.get(groupPosition).getView_Logistics(), data.get(groupPosition).getOrder_id());
                }
            }
        });
        //判断售后是否显示 过一小时就隐藏按钮


        //售后
        childViewHolder.sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saleslistener != null) {
                    //1普通商品物流页面 2认养物流页面
                    saleslistener.OnsalesListener(data.get(groupPosition).getView_Logistics(), data.get(groupPosition).getOrder_id());
                }
            }
        });

        //详情
        childViewHolder.rl_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order_id = data.get(groupPosition).getOrder_id();
                Bundle bundle = new Bundle();
                bundle.putString("id", order_id);
                IntentUtils.getIntents().Intent(context, PendingReceiptDetailssActivity.class, bundle);
            }
        });
        String price1 = data.get(groupPosition).getTotal_price();
        if (price1 != null) {
            Tv_Price_Utils.initPrice(context, price1, childViewHolder.prices);
        }
        String all_num =  data.get(groupPosition).getSum();
        if (all_num != null) {
            childViewHolder.nums.setText("共" + all_num + "件 实付: ");
        }
        if (++childPosition == data.get(groupPosition).getList().size()) {
            //当前状态 1代发货 2 部分发货 3待收货
            if (data.get(groupPosition).getStatu() == 1) {
                childViewHolder.sales.setVisibility(View.VISIBLE);
                childViewHolder.confirm.setVisibility(View.GONE);
                childViewHolder.cancel.setVisibility(View.GONE);
                childViewHolder.delivery.setVisibility(View.VISIBLE);
            } else if (data.get(groupPosition).getStatu() == 3) {
                childViewHolder.sales.setVisibility(View.GONE);
                childViewHolder.confirm.setVisibility(View.VISIBLE);
                childViewHolder.cancel.setVisibility(View.VISIBLE);
            }
            childViewHolder.lin.setVisibility(View.VISIBLE);
            childViewHolder.rl.setVisibility(View.VISIBLE);
        } else {
            childViewHolder.lv_cancel.setVisibility(View.GONE);
            childViewHolder.lin.setVisibility(View.GONE);
            childViewHolder.sales.setVisibility(View.GONE);
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
        TextView confirm;
        TextView sales;
        TextView cancel;
        TextView nums;
        TextView prices;
        TextView delivery;
        LinearLayout lv_cancel, lin;
        RelativeLayout rl_payment,rl;

        ChildViewHolder(View view) {
            pic = view.findViewById(R.id.pic);
            farm_name = view.findViewById(R.id.farm_name);
            varieties = view.findViewById(R.id.varieties);
            price = view.findViewById(R.id.price);
            num = view.findViewById(R.id.num);
            confirm = view.findViewById(R.id.confirm);
            sales = view.findViewById(R.id.sales);
            cancel = view.findViewById(R.id.cancel);
            lv_cancel = view.findViewById(R.id.lv_cancel);
            lin = view.findViewById(R.id.lin);
            rl_payment = view.findViewById(R.id.rl_payment);
            nums = view.findViewById(R.id.nums);
            prices = view.findViewById(R.id.prices);
            rl = view.findViewById(R.id.rl);
            delivery = view.findViewById(R.id.delivery);

        }
    }

    public interface onListener {
        void OnListener(int order_status, String orderid);
    }

    private onListener listener;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    //确认收货
    public interface oncancelListener {
        void OncancelListener(int order_status, String orderid);
    }

    private oncancelListener cancellistener;

    public void setcancelListener(oncancelListener cancellistener) {
        this.cancellistener = cancellistener;
    }
    //售后
    public interface onsalesListener {
        void OnsalesListener(int order_status, String orderid);
    }

    private onsalesListener saleslistener;

    public void setsalesListener(onsalesListener saleslistener) {
        this.saleslistener = saleslistener;
    }

}
