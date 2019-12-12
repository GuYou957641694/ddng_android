package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Category_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;

import java.util.List;

public class HomeItemAdapter extends BaseAdapter {
    private Context context;
    private List<Category_Bean.DataBean.SpfBean.SpflBean> spfl;

    public HomeItemAdapter(Context context, List<Category_Bean.DataBean.SpfBean.SpflBean> spfl) {
        this.context = context;
        this.spfl = spfl;
    }

    @Override
    public int getCount() {
        if (spfl != null) {
            return spfl.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return spfl.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category_Bean.DataBean.SpfBean.SpflBean spflBean = spfl.get(position);
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home_category, null);
            viewHold = new ViewHold();
            viewHold.tv_name = (TextView) convertView.findViewById(R.id.item_home_name);
            viewHold.iv_icon = (ImageView) convertView.findViewById(R.id.item_album);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.tv_name.setText(spflBean.getTitle());
        Glide.with(context).load(Urls.BASEURL + spflBean.getCategory_pic()).into(viewHold.iv_icon);
        return convertView;
    }

    private static class ViewHold {
        private TextView tv_name;
        private ImageView iv_icon;
    }
}
