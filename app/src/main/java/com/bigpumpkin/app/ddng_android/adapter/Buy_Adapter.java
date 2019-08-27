package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Settlement_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class Buy_Adapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Settlement_Bean.DataBean.ListBean> data;

    public Buy_Adapter(Context context, List<Settlement_Bean.DataBean.ListBean> data) {
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
        if (data.get(groupPosition).getDetails() != null && data.get(groupPosition).getDetails().size() > 0) {
            return data.get(groupPosition).getDetails().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getDetails().get(childPosition);
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
            convertView = View.inflate(context, R.layout.item_buy_group, null);

            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        final Settlement_Bean.DataBean.ListBean dataBean = data.get(groupPosition);
        //农场名称
        String store_name = dataBean.getTitle();
        if (store_name != null) {
            groupViewHolder.tvStoreName.setText(store_name);
        } else {
            groupViewHolder.tvStoreName.setText("");
        }


        return convertView;
    }

    //一级
    static class GroupViewHolder {
        TextView tvStoreName;

        GroupViewHolder(View view) {
            tvStoreName = view.findViewById(R.id.farm_name);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_buy_child, null);

            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        List<Settlement_Bean.DataBean.ListBean.DetailsBean> details = data.get(groupPosition).getDetails();
        //产品名
        String title = details.get(childPosition).getTitle();
        //品种名
        String pz_title = details.get(childPosition).getPz_title();
        //产品规格名
        String gg_title = details.get(childPosition).getGg_title();
        //产品价格
        String price = details.get(childPosition).getPrice();
        //数量
        String num = details.get(childPosition).getNum();
        //图片
        String pic = details.get(childPosition).getPic();
        Settlement_Bean.DataBean.ListBean listBean = data.get(groupPosition);
        int total_price = listBean.getTotal_price();
        childViewHolder.real_pay.setText("实付金额：" + total_price + "元");

        Glide.with(context).load(Urls.BASEURL + pic).into(childViewHolder.img);
        if (title != null) {
            childViewHolder.name.setText(title);
        }
        if (pz_title != null) {
            childViewHolder.zz_name.setText(pz_title);
        }
        if (gg_title != null) {
            childViewHolder.coupons.setText(gg_title);
        }
        if (price != null) {
            childViewHolder.price.setText(price);
        }
        if (num != null) {
            childViewHolder.num.setText(num);
        }
        childViewHolder.et_mess.addTextChangedListener(new TextWatcher() {
            private static final String TAG = "aaa";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (listener != null) {
                    listener.OnListener(s + "");
                }
            }
        });

        return convertView;
    }

    static class ChildViewHolder {
        ImageView img;
        TextView name;
        TextView zz_name;
        TextView price;
        TextView num, coupons, real_pay;
        CheckBox defaults, defaultsss;
        RelativeLayout product, must;
        Switch switchs;
        EditText et_mess;

        ChildViewHolder(View view) {
            img = view.findViewById(R.id.img);
            name = view.findViewById(R.id.name);
            zz_name = view.findViewById(R.id.zz_name);
            price = view.findViewById(R.id.price);
            num = view.findViewById(R.id.num);
            //包邮
            defaults = view.findViewById(R.id.defaults);
            // 不包邮
            defaultsss = view.findViewById(R.id.defaultsss);
            //产品服务
            product = view.findViewById(R.id.product);
            //优惠券
            coupons = view.findViewById(R.id.coupons);
            //准收宝
            switchs = view.findViewById(R.id.switchs);
            //留言
            et_mess = view.findViewById(R.id.et_mess);
            must = view.findViewById(R.id.must);
            real_pay = view.findViewById(R.id.real_pay);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    /**
     * 定义一个接口
     */
    public interface onListener {
        void OnListener(String msg);
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
