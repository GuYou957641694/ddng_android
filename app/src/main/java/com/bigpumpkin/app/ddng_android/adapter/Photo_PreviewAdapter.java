package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.GoodBean;

import java.util.List;

/**
 * 定制选项
 */
public class Photo_PreviewAdapter extends BaseExpandableListAdapter {

    private List<GoodBean.DataBean.OptionsBean> options;
    private Context context;

    public Photo_PreviewAdapter(List<GoodBean.DataBean.OptionsBean> options, Context context) {
        this.options = options;
        this.context = context;
    }


    @Override
    public int getGroupCount() {
        return options.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (options.get(groupPosition).getCustomlist() != null && options.get(groupPosition).getCustomlist().size() > 0) {
            return options.get(groupPosition).getCustomlist().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return options.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return options.get(groupPosition).getCustomlist().get(childPosition);
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
    public View getGroupView(int arg0, boolean isExpanded, View arg2, ViewGroup arg3) {
        HodlerViewFather hodlerViewFather;
        if (arg2 == null) {
            hodlerViewFather = new HodlerViewFather();
            arg2 = View.inflate(context, R.layout.fathergood, null);
            hodlerViewFather.name = (TextView) arg2.findViewById(R.id.name);
            arg2.setTag(hodlerViewFather);
        } else {
            hodlerViewFather = (HodlerViewFather) arg2.getTag();
        }
        // 设置标题上的文本信息
        hodlerViewFather.name.setText(options.get(arg0).getTitle());

        return arg2;
    }

    // 定义一个 一级列表的view类
    private class HodlerViewFather {
        TextView name;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // 定义一个二级列表的视图类
        HolderView childrenView;
        if (convertView == null) {
            childrenView = new HolderView();
            // 获取子视图的布局文件
            convertView = View.inflate(context, R.layout.childrengood, null);
            childrenView.name = convertView.findViewById(R.id.name);
            //childrenView.recyclerview = convertView.findViewById(R.id.recyclerview);
            convertView.setTag(childrenView);
        } else {
            childrenView = (HolderView) convertView.getTag();
        }
        List<GoodBean.DataBean.OptionsBean.CustomlistBean> customlist = options.get(groupPosition).getCustomlist();
         childrenView.name.setText(customlist.get(childPosition).getTitle());
      /*  LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        childrenView.recyclerview.setLayoutManager(layoutManager);
        PhotoAdapter photoAdapter = new PhotoAdapter(customlist, context);
        childrenView.recyclerview.setAdapter(photoAdapter);*/
        return convertView;
    }

    // 保存二级列表的视图类
    private class HolderView {
        TextView name;
        RecyclerView recyclerview;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
