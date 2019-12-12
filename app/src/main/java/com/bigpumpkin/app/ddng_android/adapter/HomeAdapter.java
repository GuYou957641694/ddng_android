package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Category_Bean;
import com.bigpumpkin.app.ddng_android.weight.GridViewForScrollView;

import java.util.List;

public class HomeAdapter extends BaseAdapter {

    private Context context;
    private List<Category_Bean.DataBean.SpfBean> spf;

    public HomeAdapter(Context context, List<Category_Bean.DataBean.SpfBean> spf) {
        this.context = context;
        this.spf = spf;
    }

    @Override
    public int getCount() {
        if (spf != null) {
            return spf.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return spf.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category_Bean.DataBean.SpfBean spfBean = spf.get(position);
        List<Category_Bean.DataBean.SpfBean.SpflBean> spfl = spfBean.getSpfl();
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home, null);
            viewHold = new ViewHold();
            viewHold.gridView = (GridViewForScrollView) convertView.findViewById(R.id.gridView);
            viewHold.blank = (TextView) convertView.findViewById(R.id.blank);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        HomeItemAdapter adapter = new HomeItemAdapter(context, spfl);
        viewHold.blank.setText(spfl.get(position).getTitle());
        viewHold.gridView.setAdapter(adapter);
        return convertView;
    }

    private static class ViewHold {
        private GridViewForScrollView gridView;
        private TextView blank;
    }
}
