package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Footprint_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;
import com.ditclear.swipelayout.SwipeDragLayout;

import java.util.List;

public class ExPandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater mInflater;
    // 定义一个List来保存列表数据
    private List<Footprint_Bean.DataBean.ListBean> data_list;

    public ExPandableListViewAdapter(Context context, List<Footprint_Bean.DataBean.ListBean> data_list) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.data_list = data_list;
    }

    // 刷新数据
    public void flashData(List<Footprint_Bean.DataBean.ListBean> datas) {
        this.data_list = datas;
        this.notifyDataSetChanged();
    }

    // 获取一级列表的个数
    @Override
    public int getGroupCount() {
        return data_list.size();
    }

    // 获取二级列表的数量
    @Override
    public int getChildrenCount(int groupPosition) {
        if (data_list.get(groupPosition).getDetails() != null && data_list.get(groupPosition).getDetails().size() > 0) {
            return data_list.get(groupPosition).getDetails().size();
        } else {
            return 0;
        }
    }

    // 获取一级列表的数据
    @Override
    public String getGroup(int groupPosition) {
        return String.valueOf(data_list.get(groupPosition));
    }


    // 获取二级列表的内容
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data_list.get(groupPosition).getDetails().get(childPosition).getPrice();
    }

    // 获取一级列表的ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // 获取二级列表的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    /**
     * 指定位置相应的组视图
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    // 设置一级列表的view
    @Override
    public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
        HodlerViewFather hodlerViewFather;
        if (arg2 == null) {
            hodlerViewFather = new HodlerViewFather();
            arg2 = mInflater.inflate(R.layout.father, arg3, false);
            hodlerViewFather.name = (TextView) arg2.findViewById(R.id.name);
            arg2.setTag(hodlerViewFather);
        } else {
            hodlerViewFather = (HodlerViewFather) arg2.getTag();
        }

        /**
         * 设置相应控件的内容
         */
        // 设置标题上的文本信息
        hodlerViewFather.name.setText(data_list.get(arg0).getCtime());

        return arg2;
    }

    // 定义一个 一级列表的view类
    private class HodlerViewFather {
        TextView name;
        ImageView group_state;
    }

    // 定义二级列表中的数据
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // 定义一个二级列表的视图类
        HolderView childrenView;
        if (convertView == null) {
            childrenView = new HolderView();
            // 获取子视图的布局文件
            convertView = mInflater.inflate(R.layout.children, parent, false);
            childrenView.descView = (TextView) convertView.findViewById(R.id.text);
            childrenView.imageView = (ImageView) convertView.findViewById(R.id.img);
            childrenView.trash = (TextView) convertView.findViewById(R.id.trash);
            childrenView.price = (TextView) convertView.findViewById(R.id.price);
            childrenView.mSwipeLayout = convertView.findViewById(R.id.swip_layout);
            convertView.setTag(childrenView);
        } else {
            childrenView = (HolderView) convertView.getTag();
        }

        /**
         * 设置相应控件的内容
         */
        GlideUtils.loadRoundCircleImagetwo(context,Urls.BASEURL + data_list.get(groupPosition).getDetails().get(childPosition).getPic(),childrenView.imageView);
        // 设置副标题上的文本信息
        childrenView.descView.setText(data_list.get(groupPosition).getDetails().get(childPosition).getCp_title());
        Tv_Price_Utils.initPriceTv(context,data_list.get(groupPosition).getDetails().get(childPosition).getPrice(),childrenView.price);
        childrenView.trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.OnListener(groupPosition,childPosition);
                    childrenView.mSwipeLayout.close();
                }
            }
        });
        childrenView.mSwipeLayout.addListener(new SwipeDragLayout.SwipeListener() {
            @Override
            public void onUpdate(SwipeDragLayout layout, float offsetRatio, float offset) {

            }

            @Override
            public void onOpened(SwipeDragLayout layout) {

            }

            @Override
            public void onClosed(SwipeDragLayout layout) {

            }
        });

        return convertView;
    }

    // 保存二级列表的视图类
    private class HolderView {
        ImageView imageView;
        TextView descView, price, trash;
        SwipeDragLayout mSwipeLayout;
    }

    /**
     * 当选择子节点的时候，调用该方法(点击二级列表)
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * 定义一个接口
     */
    public interface  onListener{
        void OnListener(int groupPosition,int childPosition);
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
