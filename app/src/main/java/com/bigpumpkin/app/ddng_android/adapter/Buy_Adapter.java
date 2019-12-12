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
    private Settlement_Bean.DataBean data2;

    public Buy_Adapter(Context context, Settlement_Bean.DataBean data2) {
        this.context = context;
        this.data2 = data2;
    }

    @Override
    public int getGroupCount() {
        if (data2.getList() != null && data2.getList().size() > 0) {
            return data2.getList().size();
        } else {
            return 0;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (data2.getList().get(groupPosition).getDetails() != null && data2.getList().get(groupPosition).getDetails().size() > 0) {
            return data2.getList().get(groupPosition).getDetails().size();
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
        return data2.getList().get(groupPosition).getDetails().get(childPosition);
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

        final Settlement_Bean.DataBean.ListBean dataBean = data2.getList().get(groupPosition);
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

        List<Settlement_Bean.DataBean.ListBean.DetailsBean> details = data2.getList().get(groupPosition).getDetails();
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
        Settlement_Bean.DataBean.ListBean listBean = data2.getList().get(groupPosition);


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
        childViewHolder.welfare.setText("+" + details.get(childPosition).getWelfare() + "元");
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
        childViewHolder.relativelayout.setVisibility(View.VISIBLE);
        String total_price = data2.getList().get(groupPosition).getTotal_price();
        if (total_price != null) {
            childViewHolder.pay_price.setText("实付金额：¥" + total_price + "元");
        }
        //判断是植物认养、家禽认养还是其他类型
        if (details.get(childPosition).getLanmu().equals("1")) {
            //如果是植物认养或者家禽认养就显示认养协议、准收宝、定制选项
            childViewHolder.customization_options.setVisibility(View.VISIBLE);
            childViewHolder.must.setVisibility(View.VISIBLE);
            childViewHolder.adopt.setVisibility(View.VISIBLE);

        }
        if (details.get(childPosition).getLanmu().equals("6")) {
            //如果是植物认养或者家禽认养就显示认养协议、准收宝、定制选项
            childViewHolder.customization_options.setVisibility(View.VISIBLE);
            childViewHolder.must.setVisibility(View.VISIBLE);
            childViewHolder.adopt.setVisibility(View.VISIBLE);

        }
        if (data2.getList().get(childPosition).getJian().equals("false")) {
            childViewHolder.couponss.setVisibility(View.GONE);

        } else {
            childViewHolder.couponss.setVisibility(View.GONE);
            childViewHolder.coupons.setText("-" + data2.getList().get(childPosition).getJian() + "元");
        }
        return convertView;
    }

    static class ChildViewHolder {
        ImageView img;
        TextView name;
        TextView zz_name;
        TextView pay_price;
        TextView price;
        TextView num, coupons, real_pay, welfare;
        CheckBox defaults, defaultsss;
        RelativeLayout product, must, customization_options, adopt, relativelayout, couponss;
        Switch switchs;
        EditText et_mess;
        View view_two,view_one,view_there,view_fore,view_five;
        ChildViewHolder(View view) {
            img = view.findViewById(R.id.img);
            name = view.findViewById(R.id.name);
            zz_name = view.findViewById(R.id.zz_name);
            price = view.findViewById(R.id.price);
            num = view.findViewById(R.id.num);
            view_one = view.findViewById(R.id.view_one);
            view_two = view.findViewById(R.id.view_two);
            view_there = view.findViewById(R.id.view_there);
            view_fore = view.findViewById(R.id.view_fore);
            view_five = view.findViewById(R.id.view_five);
            //包邮
            // 不包邮
            //产品服务
            product = view.findViewById(R.id.product);
            //优惠券
            coupons = view.findViewById(R.id.coupons);
            //准收宝
            switchs = view.findViewById(R.id.switchs);
            //留言
            et_mess = view.findViewById(R.id.et_mess);
            must = view.findViewById(R.id.must);
            //定制选项
            customization_options = view.findViewById(R.id.customization_options);
            //认养协议
            adopt = view.findViewById(R.id.adopt);
            pay_price = view.findViewById(R.id.pay_price);
            relativelayout = view.findViewById(R.id.relativelayout);
            welfare = view.findViewById(R.id.welfare);
            couponss = view.findViewById(R.id.couponss);

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
