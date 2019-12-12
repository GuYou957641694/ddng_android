package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.Buy_NowBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;

import java.util.List;

public class BuyNow_Adapter extends BaseExpandableListAdapter {

    List<Buy_NowBean.DataBean.ListBean> data;
    private Context context;

    public BuyNow_Adapter(List<Buy_NowBean.DataBean.ListBean> data, Context context) {
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

        Buy_NowBean.DataBean.ListBean listBean = data.get(groupPosition);
        //农场名称
        String store_name = listBean.getTitle();
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

        List<Buy_NowBean.DataBean.ListBean.DetailsBean> details = data.get(groupPosition).getDetails();
        //产品名
        String title = details.get(childPosition).getCp_title();
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
        String jian = details.get(childPosition).getJian();
        //公益放生
        String price1 = details.get(childPosition).getWelfare().getPrice();
        String title1 = details.get(childPosition).getWelfare().getTitle();
        Buy_NowBean.DataBean.ListBean.DetailsBean.TreasureBean treasure = details.get(childPosition).getTreasure();
        if (treasure != null) {
            //这里需要判断以为 普通商品没有准收宝
            //准收宝
            double insurance = details.get(childPosition).getTreasure().getInsurance();
            //准收宝状态
            if (insurance == 0) {
                childViewHolder.switchs.setChecked(false);
            } else {
                childViewHolder.switchs.setChecked(true);
                String str = String.valueOf(insurance);
                childViewHolder.accept.setText("+" + str + "元");
                childViewHolder.accept.setVisibility(View.VISIBLE);
                childViewHolder.texts.setText(details.get(childPosition).getTreasure().getTitle());
            }
        }
        //定制维护
        String maintain = details.get(childPosition).getMaintain();
        Buy_NowBean.DataBean.ListBean listBean = data.get(groupPosition);

        GlideUtils.loadRoundCircleImagethere(context, Urls.BASEURL + pic, childViewHolder.img);
        if (title != null) {
            childViewHolder.name.setText(title);
        }
        if (pz_title != null) {
            childViewHolder.zz_name.setText("已选：" + pz_title + gg_title);
        }
        if (jian != null) {
            childViewHolder.couponss.setVisibility(View.VISIBLE);
            childViewHolder.coupons.setText(jian);
        }
        if (price != null) {
            Tv_Price_Utils.initPriceTv(context, price, childViewHolder.price);
        }
        if (num != null) {
            childViewHolder.num.setText(num);
        }
        if (price1 != null) {
            childViewHolder.welfare.setText("+" + price1 + "元");
        }
        if (title1 != null) {
            childViewHolder.welfares.setText(title1);
        }
        if (maintain != null) {
            String substring = maintain.substring(0, maintain.length() - 1);
            childViewHolder.customization.setText(substring);
        }

        childViewHolder.et_mess.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (listener != null) {
                    String s1 = s.toString();
                    listener.OnListener(s1 + "");
                }
            }
        });

        //加数量
        childViewHolder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listeners != null) {
                    listeners.OnListeners(childPosition);
                }
            }
        });
        //减数量
        childViewHolder.num_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenerjian != null) {
                    listenerjian.OnListenerjian(childPosition);
                }
            }
        });
        //准收宝
        childViewHolder.switchs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (listenerz != null) {
                        listenerz.OnListenerz(isChecked);
                    }
                } else {
                    if (listenerz != null) {
                        listenerz.OnListenerz(isChecked);
                    }
                }
            }
        });
        String lanmu = details.get(childPosition).getLanmu();
        //判断是植物认养、家禽认养还是其他类型
        if (lanmu.equals("1")) {
            //如果是植物认养或者家禽认养就显示认养协议、准收宝、定制选项
            childViewHolder.customization_options.setVisibility(View.VISIBLE);
            childViewHolder.must.setVisibility(View.VISIBLE);
            childViewHolder.adopt.setVisibility(View.VISIBLE);
            childViewHolder.relativelayout.setVisibility(View.VISIBLE);
        } else if (lanmu.equals("2")) {
            childViewHolder.rl_welfare.setVisibility(View.GONE);
            childViewHolder.view_two.setVisibility(View.GONE);
            childViewHolder.view_there.setVisibility(View.GONE);
            childViewHolder.view_fore.setVisibility(View.GONE);
            childViewHolder.view_five.setVisibility(View.GONE);
        } else if (lanmu.equals("6")) {
            //如果是植物认养或者家禽认养就显示认养协议、准收宝、定制选项
            childViewHolder.rl_num.setVisibility(View.GONE);
            childViewHolder.rl_welfare.setVisibility(View.VISIBLE);
            childViewHolder.customization_options.setVisibility(View.VISIBLE);
            childViewHolder.must.setVisibility(View.VISIBLE);
            childViewHolder.adopt.setVisibility(View.VISIBLE);
            childViewHolder.relativelayout.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    static class ChildViewHolder {
        ImageView img;
        TextView name, texts;
        TextView zz_name, welfares;
        TextView price, accept, welfare, customization;
        TextView num, coupons, real_pay;
        RelativeLayout product, must, couponss, adopt, customization_options, relativelayout, rl_num, rl_welfare, num_jian, jia;
        Switch switchs;
        EditText et_mess;
        View view_two, view_one, view_there, view_fore, view_five;

        ChildViewHolder(View view) {
            img = view.findViewById(R.id.img);
            name = view.findViewById(R.id.name);
            zz_name = view.findViewById(R.id.zz_name);
            price = view.findViewById(R.id.price);
            num = view.findViewById(R.id.num);
            rl_num = view.findViewById(R.id.rl_num);
            rl_welfare = view.findViewById(R.id.rl_welfare);
            view_one = view.findViewById(R.id.view_one);
            view_two = view.findViewById(R.id.view_two);
            view_there = view.findViewById(R.id.view_there);
            view_fore = view.findViewById(R.id.view_fore);
            view_five = view.findViewById(R.id.view_five);
            //产品服务
            product = view.findViewById(R.id.product);
            //优惠券
            coupons = view.findViewById(R.id.coupons);
            //准收宝
            switchs = view.findViewById(R.id.switchs);
            //留言
            et_mess = view.findViewById(R.id.et_mess);
            must = view.findViewById(R.id.must);
            couponss = view.findViewById(R.id.couponss);
            //认养协议
            adopt = view.findViewById(R.id.adopt);
            //准守宝价格
            accept = view.findViewById(R.id.accept);
            //公益放生
            welfare = view.findViewById(R.id.welfare);
            //定制选项
            customization = view.findViewById(R.id.customization);
            num_jian = view.findViewById(R.id.num_jian);
            jia = view.findViewById(R.id.jia);
            welfares = view.findViewById(R.id.welfares);
            texts = view.findViewById(R.id.texts);
            //定制选项
            customization_options = view.findViewById(R.id.customization_options);
            //认养协议
            adopt = view.findViewById(R.id.adopt);
            relativelayout = view.findViewById(R.id.relativelayout);
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

    private onListener listener;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    //加数量
    public interface onListeners {
        void OnListeners(int i);
    }

    private onListeners listeners;

    public void setListeners(onListeners listeners) {
        this.listeners = listeners;
    }

    //减数量
    public interface onListenerjian {
        void OnListenerjian(int i);
    }

    private onListenerjian listenerjian;

    public void setListenerjian(onListenerjian listenerjian) {
        this.listenerjian = listenerjian;
    }

    public interface onListenerz {
        void OnListenerz(boolean type);
    }

    private onListenerz listenerz;

    public void setListenerz(onListenerz listenerz) {
        this.listenerz = listenerz;
    }

}
