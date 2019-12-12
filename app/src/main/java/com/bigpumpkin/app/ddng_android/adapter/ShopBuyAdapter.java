package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.bean.ShopBuyBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;

import java.util.List;

public class ShopBuyAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "Asas";
    private List<ShopBuyBean.DataBean.ShopBean> data;
    private Context context;
    String allpostage;
    boolean isChecked;

    public ShopBuyAdapter(List<ShopBuyBean.DataBean.ShopBean> data, Context context, String allpostage) {
        this.data = data;
        this.context = context;
        this.allpostage = allpostage;
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
        if (data.get(groupPosition).getS_list() != null && data.get(groupPosition).getS_list().size() > 0) {
            return data.get(groupPosition).getS_list().size();
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
        return data.get(groupPosition).getS_list().get(childPosition);
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
            convertView = View.inflate(context, R.layout.item_shopping_buy_group, null);

            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.tvStoreName.setText(data.get(groupPosition).getF_title());
        return convertView;
    }

    static class GroupViewHolder {
        TextView tvStoreName;

        GroupViewHolder(View view) {
            tvStoreName = view.findViewById(R.id.tv_store_name);

        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopping_buy_child, null);

            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        ShopBuyBean.DataBean.ShopBean.SListBean sListBean = data.get(groupPosition).getS_list().get(childPosition);
        String s_title = sListBean.getS_title();
        String s_pic = sListBean.getS_pic();
        String v_title = sListBean.getV_title();
        String sp_title = sListBean.getSp_title();
        String s_lanmu = sListBean.getS_lanmu();
        String s_price = sListBean.getS_price();
        String c_num = sListBean.getC_num();
        String maintain = sListBean.getMaintain();
        List<ShopBuyBean.DataBean.ShopBean.MemberCouponBean> member_coupon = data.get(groupPosition).getMember_coupon();
        ShopBuyBean.DataBean.ShopBean.MemberCouponUseBean member_coupon_use = data.get(groupPosition).getMember_coupon_Use();
        if (s_title != null && s_pic != null && v_title != null && sp_title != null && s_lanmu != null && c_num != null) {
            GlideUtils.loadRoundCircleImagethere(context, Urls.BASEURL + s_pic, childViewHolder.ivPhoto);
            childViewHolder.tvName.setText(s_title);
            childViewHolder.gg.setText(v_title + sp_title);
            childViewHolder.tvPriceValue.setText(s_price);
            childViewHolder.tv_num.setText("数量：×" + c_num);
            //需要判断商品是什么类型的 1是认养
            if (s_lanmu.equals("1")) {
                if (maintain.equals("0")) {
                    //等于是默认认养
                    childViewHolder.tv_adopt.setText("常规维护");
                } else {
                    childViewHolder.tv_adopt.setText(maintain);
                }
                Log.d(TAG, "getChildView: " + maintain);
                childViewHolder.rl_adopt.setVisibility(View.VISIBLE);
            } else {
                childViewHolder.rl_adopt.setVisibility(View.GONE);
            }
            //这里判断的东西都是在最后一条的下面显示 是跟着店铺走 而不是商品
            if (++childPosition == data.get(groupPosition).getS_list().size()) {
                //这里是必须在最后一个商品显示
                childViewHolder.lin_id.setVisibility(View.VISIBLE);
                //这里判断优惠券有没有
                if (member_coupon != null) {
                    //这是使用优惠券
                    if (member_coupon_use.getId().equals("0")) {
                        childViewHolder.tv_coupons.setText("没使用优惠券");
                    } else {
                        childViewHolder.tv_coupons.setText("-" + member_coupon_use.getJian() + "元");
                    }

                    childViewHolder.rl_coupons.setVisibility(View.VISIBLE);
                    childViewHolder.one_view.setVisibility(View.VISIBLE);
                    int finalChildPosition = childPosition;
                    //优惠券
                    childViewHolder.rl_coupons.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onRightClickListener != null) {
                                onRightClickListener.onClick(groupPosition, finalChildPosition);
                            }
                        }
                    });
                } else {
                    //如果没有就隐藏
                    childViewHolder.rl_coupons.setVisibility(View.GONE);
                    childViewHolder.one_view.setVisibility(View.GONE);
                }
                //运费 如果不等于null的话 就是有运费 等于的话 就是包邮
                if (data.get(groupPosition).getPostage__price().equals("0")) {
                    childViewHolder.tv_postage_prices.setText("包邮");
                } else {
                    childViewHolder.tv_postage_prices.setText(data.get(groupPosition).getPostage__price());
                }

                //判断有没有准收宝
                if (data.get(groupPosition).getF_num_insurance() != null) {

                    childViewHolder.lin_must.setVisibility(View.VISIBLE);
                    int insurance_enable_e = data.get(groupPosition).getInsurance_enable_e();
                    if (insurance_enable_e == 1) {
                        //1是有准收宝
                        childViewHolder.switchs.setChecked(true);
                        isChecked = true;
                        childViewHolder.tv_must_price.setText("+" + data.get(groupPosition).getF_num_insurance() + "元");
                    } else {
                        //1是有没有准收宝
                        childViewHolder.switchs.setChecked(false);
                        isChecked = false;
                        childViewHolder.tv_must_price.setText("+0元");
                    }
                    int finalChildPosition1 = childPosition;
                    childViewHolder.switchs.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.OnListener(groupPosition, finalChildPosition1, isChecked);
                            }
                        }
                    });

                } else {
                    //如果没有就隐藏
                    childViewHolder.lin_must.setVisibility(View.GONE);
                }

                //公益放生
                if (data.get(groupPosition).getWelfare() != null) {
                    childViewHolder.tv_welfare_price.setText("+" + data.get(groupPosition).getWelfare() + "元");
                } else {
                    //如果没有就隐藏
                    childViewHolder.lin_welfare.setVisibility(View.GONE);
                }

                childViewHolder.tv_store_price.setText(data.get(groupPosition).getF_num_price());
            } else {
                childViewHolder.lin_id.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    static class ChildViewHolder {
        ImageView ivPhoto;
        TextView tvName;
        TextView tvPriceValue;
        TextView gg;
        TextView tv_num;
        TextView tv_adopt;
        TextView tv_coupons;
        TextView tv_welfare_price;
        TextView tv_must_price;
        TextView tv_store_price;
        TextView tv_postage_prices;
        LinearLayout lin, lin_welfare;
        RelativeLayout rl_adopt, rl_coupons;
        LinearLayout lin_id, lin_must;
        Switch switchs;
        View one_view;

        ChildViewHolder(View view) {
            ivPhoto = view.findViewById(R.id.iv_photo);
            tvName = view.findViewById(R.id.tv_name);
            tvPriceValue = view.findViewById(R.id.tv_price_value);
            gg = view.findViewById(R.id.gg);
            lin = view.findViewById(R.id.lin);
            tv_num = view.findViewById(R.id.tv_num);
            tv_adopt = view.findViewById(R.id.tv_adopt);
            rl_adopt = view.findViewById(R.id.rl_adopt);
            tv_coupons = view.findViewById(R.id.tv_coupons);
            one_view = view.findViewById(R.id.one_view);
            rl_coupons = view.findViewById(R.id.rl_coupons);
            lin_id = view.findViewById(R.id.lin_id);
            tv_welfare_price = view.findViewById(R.id.tv_welfare_price);
            tv_must_price = view.findViewById(R.id.tv_must_price);
            tv_store_price = view.findViewById(R.id.tv_store_price);
            lin_must = view.findViewById(R.id.lin_must);
            tv_postage_prices = view.findViewById(R.id.tv_postage_prices);
            lin_welfare = view.findViewById(R.id.lin_welfare);
            switchs = view.findViewById(R.id.switchs);
        }
    }

    //-----------------------------------------------------------------------------------------------

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    /**
     * 准收宝
     */
    public interface onListener {
        void OnListener(int group, int child, boolean isChecked);
    }


    private onListener listener;


    public void setListener(onListener listener) {
        this.listener = listener;
    }

    private OnRightClickListener onRightClickListener;

    public interface OnRightClickListener {
        void onClick(int group, int child);
    }

    public void setOnRightClickListener(OnRightClickListener onRightClickListener) {
        this.onRightClickListener = onRightClickListener;
    }

}
