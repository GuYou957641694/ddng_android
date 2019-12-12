package com.bigpumpkin.app.ddng_android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.FarmActivity;
import com.bigpumpkin.app.ddng_android.activity.ShopBuyActivity;
import com.bigpumpkin.app.ddng_android.bean.Shopping_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;
import com.hjq.toast.ToastUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCarAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "getAway";
    private Context context;
    private ImageView ivSelectAll;
    private Button btnOrder;
    private Button del;
    private TextView btnDelete;
    private RelativeLayout rlTotalPrice;
    private List<Shopping_Bean.DataBean> data;
    private boolean isSelectAll = false;
    private double total_price;
    private ImageView llSelectAll;
    private TextView tvTotalPrice;
    private double v;
    private double v1;

    public ShoppingCarAdapter(Context context, Button btnOrder, List<Shopping_Bean.DataBean> data, ImageView llSelectAll, TextView tvTotalPrice, TextView btnDelete, Button dels) {
        this.context = context;
        this.btnOrder = btnOrder;
        this.data = data;
        this.llSelectAll = llSelectAll;
        this.tvTotalPrice = tvTotalPrice;
        this.btnDelete = btnDelete;
        this.del = dels;
    }

    /**
     * 自定义设置数据方法；
     * 通过notifyDataSetChanged()刷新数据，可保持当前位置
     *
     * @param data 需要刷新的数据
     */
    public void setData(List<Shopping_Bean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
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
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopping_car_group, null);

            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        final Shopping_Bean.DataBean dataBean = data.get(groupPosition);
        //农场名称
        String store_name = dataBean.getTitle();
        if (store_name != null) {
            groupViewHolder.tvStoreName.setText(store_name);
        } else {
            groupViewHolder.tvStoreName.setText("");
        }

        //店铺内的商品都选中的时候，店铺的也要选中
        for (int i = 0; i < dataBean.getList().size(); i++) {
            Shopping_Bean.DataBean.ListBean goodsBean = dataBean.getList().get(i);
            boolean isSelect = goodsBean.getIsSelect();
            if (isSelect) {
                dataBean.setIsSelect_shop(true);
            } else {
                dataBean.setIsSelect_shop(false);
                break;
            }
        }

        //因为set之后要重新get，所以这一块代码要放到一起执行
        //店铺是否在购物车中被选中
        final boolean isSelect_shop = dataBean.getIsSelect_shop();
        if (isSelect_shop) {
            groupViewHolder.ivSelect.setImageResource(R.mipmap.check_true);
        } else {
            groupViewHolder.ivSelect.setImageResource(R.mipmap.check_false);
        }

        //店铺选择框的点击事件
        groupViewHolder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setIsSelect_shop(!isSelect_shop);
                List<Shopping_Bean.DataBean.ListBean> goods = dataBean.getList();
                for (int i = 0; i < goods.size(); i++) {
                    Shopping_Bean.DataBean.ListBean goodsBean = goods.get(i);
                    goodsBean.setIsSelect(!isSelect_shop);
                }
                notifyDataSetChanged();
            }
        });

        //当所有的选择框都是选中的时候，全选也要选中
        w:
        for (int i = 0; i < data.size(); i++) {
            List<Shopping_Bean.DataBean.ListBean> goods = data.get(i).getList();
            for (int y = 0; y < goods.size(); y++) {
                Shopping_Bean.DataBean.ListBean goodsBean = goods.get(y);
                boolean isSelect = goodsBean.getIsSelect();
                if (isSelect) {
                    isSelectAll = true;
                } else {
                    isSelectAll = false;
                    break w;//根据标记，跳出嵌套循环
                }
            }
        }

        //店铺点击事件
        groupViewHolder.tvStoreName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle  =new Bundle();
                bundle.putString("id",dataBean.getNc_id());
                IntentUtils.getIntents().Intent(context, FarmActivity.class, bundle);
            }
        });

        if (isSelectAll) {
            llSelectAll.setBackgroundResource(R.mipmap.check_true);
        } else {
            llSelectAll.setBackgroundResource(R.mipmap.check_false);
        }

        //全选的点击事件
        llSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelectAll = !isSelectAll;
                if (isSelectAll) {
                    for (int i = 0; i < data.size(); i++) {
                        List<Shopping_Bean.DataBean.ListBean> goods = data.get(i).getList();
                        for (int y = 0; y < goods.size(); y++) {
                            Shopping_Bean.DataBean.ListBean goodsBean = goods.get(y);
                            goodsBean.setIsSelect(true);
                        }
                    }
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        List<Shopping_Bean.DataBean.ListBean> goods = data.get(i).getList();
                        for (int y = 0; y < goods.size(); y++) {
                            Shopping_Bean.DataBean.ListBean goodsBean = goods.get(y);
                            goodsBean.setIsSelect(false);
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });

        //合计的计算
        total_price = 0.0;
        tvTotalPrice.setText("合计：" + "¥0.00");
        for (int i = 0; i < data.size(); i++) {
            List<Shopping_Bean.DataBean.ListBean> goods = data.get(i).getList();
            for (int y = 0; y < goods.size(); y++) {
                Shopping_Bean.DataBean.ListBean goodsBean = goods.get(y);
                boolean isSelect = goodsBean.getIsSelect();
                if (isSelect) {
                    String num = goodsBean.getNum();
                    String price = goodsBean.getReal_price();
                    if (num != null) {
                        v = Double.parseDouble(num);
                    }
                    if (price != null) {
                        v1 = Double.parseDouble(price);
                    }
                    total_price = total_price + v * v1;
                    //让Double类型完整显示，不用科学计数法显示大写字母E
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    tvTotalPrice.setText("合计：" + "¥" + decimalFormat.format(total_price));
                    //tvTotalPrice.setText("合计：¥" + total_price);
                }
            }
        }

        //去结算的点击事件
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建临时的List，用于存储被选中的商品
                List<Shopping_Bean.DataBean.ListBean> temp = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    List<Shopping_Bean.DataBean.ListBean> goods = data.get(i).getList();
                    for (int y = 0; y < goods.size(); y++) {
                        Shopping_Bean.DataBean.ListBean goodsBean = goods.get(y);
                        boolean isSelect = goodsBean.getIsSelect();
                        if (isSelect) {
                            temp.add(goodsBean);
                        }
                    }
                }
                if (temp != null && temp.size() > 0) {//如果有被选中的
                    Bundle bundle = new Bundle();
                    String iArray[] = new String[temp.size()];
                    for (int j = 0; j < temp.size(); j++) {
                        String id = temp.get(j).getId();
                        iArray[j] = id;
                    }
                    bundle.putStringArray("list", iArray);
                    IntentUtils.getIntents().Intent(context, ShopBuyActivity.class, bundle);
                } else {
                    ToastUtil.showShort(context, "请选择要购买的商品");
                }
            }
        });

        //删除的点击事件
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteListener.onDelete();

            }
        });


        return convertView;
    }

    static class GroupViewHolder {
        ImageView ivSelect;
        TextView tvStoreName;
        LinearLayout ll;

        GroupViewHolder(View view) {
            ivSelect = view.findViewById(R.id.iv_select);
            tvStoreName = view.findViewById(R.id.tv_store_name);
            ll = view.findViewById(R.id.ll);
        }
    }

    //------------------------------------------------------------------------------------------------
    @Override
    public int getChildrenCount(int groupPosition) {
        if (data.get(groupPosition).getList() != null && data.get(groupPosition).getList().size() > 0) {
            return data.get(groupPosition).getList().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopping_car_child, null);

            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        final Shopping_Bean.DataBean datasBean = data.get(groupPosition);

        //店铺是否在购物车中被选中
        final boolean isSelect_shop = datasBean.getIsSelect_shop();
        final Shopping_Bean.DataBean.ListBean goodsBean = datasBean.getList().get(childPosition);
        String title = goodsBean.getTitle();
        String pz_title = goodsBean.getPz_title();
        String gg_title = goodsBean.getGg_title();
        String in_stock = goodsBean.getIn_stock();
        //商品图片
        String goods_image = goodsBean.getSp_pic();
        //商品ID
        final String goods_id = goodsBean.getId();
        //商品名称
        String goods_name = goodsBean.getGg_title();
        //商品价格
        String goods_price = goodsBean.getReal_price();
        //商品数量
        String goods_num = goodsBean.getNum();
        //商品是否被选中
        final boolean isSelect = goodsBean.getIsSelect();

        GlideUtils.loadRoundCircleImagethere(context, Urls.BASEURL + goods_image, childViewHolder.ivPhoto);
        if (goods_name != null) {
            childViewHolder.tvName.setText(title);
        } else {
            childViewHolder.tvName.setText("");
        }
        childViewHolder.gg.setText(pz_title + gg_title);
        if (goods_price != null) {
            Tv_Price_Utils.initPriceTv(context, goods_price, childViewHolder.tvPriceValue);
        } else {
            childViewHolder.tvPriceValue.setText("");
        }
        if (goods_num != null) {
            childViewHolder.tvEditBuyNumber.setText(goods_num);
        } else {
            childViewHolder.tvEditBuyNumber.setText("");
        }

        //商品是否被选中
        if (isSelect) {
            childViewHolder.ivSelect.setImageResource(R.mipmap.check_true);
        } else {
            childViewHolder.ivSelect.setImageResource(R.mipmap.check_false);
        }


        //商品选择框的点击事件
        childViewHolder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsBean.setIsSelect(!isSelect);
                if (!isSelect == false) {
                    datasBean.setIsSelect_shop(false);
                }
                notifyDataSetChanged();
            }
        });

        //加号的点击事件
        childViewHolder.ivEditAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟加号操作
                String num = goodsBean.getNum();
                Integer integer = Integer.valueOf(num);
                integer++;
                goodsBean.setNum(integer + "");
                notifyDataSetChanged();
                if (mChangeCountListener != null) {
                    mChangeCountListener.onChangeCount(goods_id, integer);
                }
            }
        });
        //减号的点击事件
        childViewHolder.ivEditSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟减号操作
                String num = goodsBean.getNum();
                Integer integer = Integer.valueOf(num);
                if (integer > 1) {
                    integer--;
                    goodsBean.setNum(integer + "");

                    if (mChangeCountListener != null) {
                        mChangeCountListener.onChangeCount(goods_id, integer);
                    }
                } else {
                    ToastUtils.show("商品不能再减少了");
                }
                notifyDataSetChanged();
            }
        });

        int finalChildPosition = childPosition;
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDeleteListeners != null) {
                    //创建临时的List，用于存储被选中的商品
                    List<Shopping_Bean.DataBean.ListBean> temp = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        List<Shopping_Bean.DataBean.ListBean> goods = data.get(i).getList();
                        for (int y = 0; y < goods.size(); y++) {
                            Shopping_Bean.DataBean.ListBean goodsBean = goods.get(y);
                            boolean isSelect = goodsBean.getIsSelect();
                            if (isSelect) {
                                temp.add(goodsBean);
                            }
                        }
                    }
                    if (temp != null && temp.size() > 0) {//如果有被选中的
                        String iArray[] = new String[temp.size()];
                        for (int j = 0; j < temp.size(); j++) {
                            String id = temp.get(j).getId();
                            iArray[j] = id;
                        }
                        mDeleteListeners.onDeletes(iArray, groupPosition, finalChildPosition);
                    } else {
                        ToastUtils.show("请选择商品");
                    }
                }
            }
        });

        childViewHolder.relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.OnListener(finalChildPosition);
                }
            }
        });

        if (++childPosition == datasBean.getList().size()) {
            childViewHolder.lin.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    static class ChildViewHolder {
        ImageView ivSelect;
        ImageView ivPhoto;
        TextView tvName;
        TextView tvPriceValue;
        TextView gg;
        RelativeLayout ivEditSubtract;
        TextView tvEditBuyNumber;
        ImageView ivEditAdd;
        LinearLayout lin;
        RelativeLayout relativelayout;
        ChildViewHolder(View view) {
            ivSelect = view.findViewById(R.id.iv_select);
            ivPhoto = view.findViewById(R.id.iv_photo);
            tvName = view.findViewById(R.id.tv_name);
            tvPriceValue = view.findViewById(R.id.tv_price_value);
            ivEditSubtract = view.findViewById(R.id.iv_edit_subtract);
            tvEditBuyNumber = view.findViewById(R.id.tv_edit_buy_number);
            ivEditAdd = view.findViewById(R.id.iv_edit_add);
            gg = view.findViewById(R.id.gg);
            lin = view.findViewById(R.id.lin);
            relativelayout = view.findViewById(R.id.relativelayout);
        }
    }

    //-----------------------------------------------------------------------------------------------

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //删除的回调
    public interface OnDeleteListener {
        void onDelete();
    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        mDeleteListener = listener;
    }

    private OnDeleteListener mDeleteListener;

    //修改商品数量的回调
    public interface OnChangeCountListener {
        void onChangeCount(String goods_id, Integer num);
    }

    public void setOnChangeCountListener(OnChangeCountListener listener) {
        mChangeCountListener = listener;
    }

    private OnChangeCountListener mChangeCountListener;


    //删除的回调
    public interface OnDeleteListeners {
        void onDeletes(String iArray[], int groupPosition, int childPosition);
    }

    public void setOnDeleteListeners(OnDeleteListeners listeners) {
        mDeleteListeners = listeners;
    }

    private OnDeleteListeners mDeleteListeners;


    /**
     * 定义一个接口
     */
    public interface  onListener{
        void OnListener(int i);
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
