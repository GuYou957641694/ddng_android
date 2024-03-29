package com.bigpumpkin.app.ddng_android.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.DetailsBean;
import com.bigpumpkin.app.ddng_android.weight.SkuFlowLayout;

import java.util.List;

public class FlowPopListViewAdapter extends BaseAdapter {

    private Activity context;
    private List<DetailsBean.DataBean.OptionsBean> dictList;

    public FlowPopListViewAdapter(Activity context, List<DetailsBean.DataBean.OptionsBean> dictList) {
        this.context = context;
        this.dictList = dictList;
    }

    @Override
    public int getCount() {
        return dictList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_property, null);
            vh = new ViewHolder();
            vh.tvTypeName = (TextView) convertView.findViewById(R.id.tv_type_name);
            vh.layoutProperty = (SkuFlowLayout) convertView.findViewById(R.id.layout_property);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        DetailsBean.DataBean.OptionsBean optionsBean = dictList.get(position);
        vh.tvTypeName.setText(optionsBean.getTitle());

        setFlowLayoutData(optionsBean.getCustomlist(), vh.layoutProperty);

        return convertView;
    }

    private void setFlowLayoutData(final List<DetailsBean.DataBean.OptionsBean.CustomlistBean> childrenList, final SkuFlowLayout flowLayout) {

        flowLayout.removeAllViews();
        for (int x = 0; x < childrenList.size(); x++) {
            CheckBox checkBox = (CheckBox) View.inflate(context, R.layout.item_flowlayout_bill, null);
            checkBox.setText(childrenList.get(x).getTitle());

            if (childrenList.get(x).isSelected()) {
                checkBox.setChecked(true);
                childrenList.get(x).setSelected(true);
            } else {
                checkBox.setChecked(false);
                childrenList.get(x).setSelected(false);
            }

            final int finalX = x;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshCheckBox(flowLayout, finalX, childrenList);
                }
            });
            flowLayout.addView(checkBox);
        }
    }

    private void refreshCheckBox(SkuFlowLayout flowLayout, int finalX, List<DetailsBean.DataBean.OptionsBean.CustomlistBean> propBeenList) {
        for (int y = 0; y < flowLayout.getChildCount(); y++) {
            CheckBox radio = (CheckBox) flowLayout.getChildAt(y);
            radio.setChecked(false);
            propBeenList.get(y).setSelected(false);
            if (finalX == y) {
                radio.setChecked(true);
                propBeenList.get(y).setSelected(true);
            }
        }
//        context.setPropertyPrice();
    }

    class ViewHolder {
        private TextView tvTypeName;
        private SkuFlowLayout layoutProperty;
    }

}
