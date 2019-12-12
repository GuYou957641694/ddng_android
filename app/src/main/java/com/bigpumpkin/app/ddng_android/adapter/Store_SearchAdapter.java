package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Store_SearchBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class Store_SearchAdapter extends BaseExpandableListAdapter {

    private List<Store_SearchBean.DataBean> data1;
    private Context context;

    public Store_SearchAdapter(List<Store_SearchBean.DataBean> data1, Context context) {
        this.data1 = data1;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        if (data1 != null && data1.size() > 0) {
            return data1.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (data1.get(groupPosition).getShop() != null && data1.get(groupPosition).getShop().size() > 0) {
            return data1.get(groupPosition).getShop().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (data1 != null && data1.size() > 0) {
            return data1.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (data1.get(groupPosition).getShop() != null && data1.get(groupPosition).getShop().size() > 0) {
            return data1.get(groupPosition).getShop().size();
        } else {
            return 0;
        }
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
            convertView = View.inflate(context, R.layout.item_store_search, null);

            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        Store_SearchBean.DataBean dataBean = data1.get(groupPosition);
        //农场名
        String title = dataBean.getTitle();
        //头像
        String pic3 = dataBean.getPic3();
        groupViewHolder.tv_farm_name.setText(title);
        GlideUtils.loadRoundCircleImage(context, Urls.BASEURL + pic3, groupViewHolder.tv_pic);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_store_searchs, null);

            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        List<Store_SearchBean.DataBean.ShopBean> shop = data1.get(groupPosition).getShop();
        String pic = shop.get(childPosition).getPic();
        String price = shop.get(childPosition).getPrice();
        childViewHolder.tv_price.setText(price);
        GlideUtils.loadRoundCircleImage(context, Urls.BASEURL + pic, childViewHolder.tv_img);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupViewHolder {
        ImageView tv_pic;
        TextView tv_farm_name;
        RelativeLayout rl;

        GroupViewHolder(View view) {
            tv_pic = view.findViewById(R.id.tv_pic);
            tv_farm_name = view.findViewById(R.id.tv_farm_name);
            rl = view.findViewById(R.id.rl);
        }
    }

    static class ChildViewHolder {
        ImageView tv_img;
        TextView tv_price;

        ChildViewHolder(View view) {
            tv_price = view.findViewById(R.id.tv_price);
            tv_img = view.findViewById(R.id.tv_img);
        }
    }
}
