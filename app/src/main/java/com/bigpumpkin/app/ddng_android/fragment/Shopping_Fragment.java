package com.bigpumpkin.app.ddng_android.fragment;

import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.ShoppingCarAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.bean.Del_Address_Bean;
import com.bigpumpkin.app.ddng_android.bean.Del_Shooppoing_Bean;
import com.bigpumpkin.app.ddng_android.bean.Shopping_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.LoginUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.DialogUtils;
import com.hjq.toast.ToastUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 购物车
 */
public class Shopping_Fragment extends BaseFragment implements MyView {

    private static final String TAG = "Shopping_Fragment";
    private RelativeLayout relativeLayout, rela;
    private HashMap<String, Object> map;
    private HashMap<String, Object> maps;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> headmaps;
    private MyPresenterImpl presenter;
    private ExpandableListView allshopp;
    private ShoppingCarAdapter shopp_adapter;
    private ImageView llSelectAll;
    private TextView tvTotalPrice, cancel;
    private Button btnOrder, del;
    private TextView editor, text;
    private RelativeLayout relativelayout;
    private String appid;
    private String appsecret;
    private long time;
    private String sha1;
    private Dialog mWeiboDialog;
    String str = "";
    int groupPositions, childPositions;
    private List<Shopping_Bean.DataBean> data1;
    private List<String> list;
    private Shopping_Bean shopping_bean;

    @Override
    protected int getLayoutId() {
        return R.layout.shopping_fragment;
    }

    @Override
    protected void init(View view) {
        relativeLayout = view.findViewById(R.id.no_log);
        allshopp = view.findViewById(R.id.recyclerview);
        rela = view.findViewById(R.id.rela);
        llSelectAll = view.findViewById(R.id.all);
        tvTotalPrice = view.findViewById(R.id.price);
        btnOrder = view.findViewById(R.id.settlement);
        del = view.findViewById(R.id.del);
        editor = view.findViewById(R.id.editor);
        text = view.findViewById(R.id.text);
        cancel = view.findViewById(R.id.cancel);
        relativelayout = view.findViewById(R.id.relativelayout);
        //判断有误登录
        if (!LoginUtil.getInstance().checkLoginStatuss(getActivity())) {
            relativelayout.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            allshopp.setVisibility(View.GONE);
            rela.setVisibility(View.GONE);
        } else {
            //如果没登录提示用户登录
            relativeLayout.setVisibility(View.GONE);
            rela.setVisibility(View.VISIBLE);
            relativelayout.setVisibility(View.VISIBLE);
            //提示用户加载中
            mWeiboDialog = DialogUtils.createLoadingDialog(getActivity(), "加载中...");
        }
        presenter = new MyPresenterImpl(this);
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        presenter.getpost(Contacts.My_shopping, headmap, map, Shopping_Bean.class);

        //编辑点击取消
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel.setVisibility(View.GONE);
                editor.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                tvTotalPrice.setVisibility(View.VISIBLE);
                btnOrder.setVisibility(View.VISIBLE);
                del.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void loadData() {

    }

    //每次刷新购物车
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!LoginUtil.getInstance().checkLoginStatuss(getActivity())) {
            relativeLayout.setVisibility(View.VISIBLE);
            allshopp.setVisibility(View.GONE);
            relativelayout.setVisibility(View.GONE);
            rela.setVisibility(View.GONE);
        } else {
            time = System.currentTimeMillis();
            String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
            sha1 = EncryptUtils.getSHA(sha);
            map.put("appid", appid);
            map.put("appsecret", appsecret);
            map.put("timestamp", time);
            map.put("sign", sha1);
            presenter.getpost(Contacts.My_shopping, headmap, map, Shopping_Bean.class);
            relativeLayout.setVisibility(View.GONE);
            rela.setVisibility(View.VISIBLE);
            relativelayout.setVisibility(View.VISIBLE);
            //提示用户加载中
        }
    }

    @Override
    public void success(final Object data) {
        DialogUtils.closeDialog(mWeiboDialog);
        if (data instanceof Shopping_Bean) {
            //购物车赋值
            shopping_bean = (Shopping_Bean) data;
            data1 = shopping_bean.getData();
            if (data1 != null) {
                int size = data1.size();
                if (size > 0) {
                    shopp_adapter = new ShoppingCarAdapter(getActivity(), btnOrder, data1, llSelectAll, tvTotalPrice, editor, del);
                    allshopp.setAdapter(shopp_adapter);
                    allshopp.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.GONE);
                    rela.setVisibility(View.VISIBLE);
                    for (int i = 0; i < data1.size(); i++) {
                        allshopp.expandGroup(i);
                    }
                } else {
                    del.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    allshopp.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                }
                map.clear();
            }
            //删除
            shopp_adapter.setOnDeleteListener(new ShoppingCarAdapter.OnDeleteListener() {

                @Override
                public void onDelete() {
                    if (data1.size() > 0) {
                        editor.setVisibility(View.GONE);
                        text.setVisibility(View.GONE);
                        tvTotalPrice.setVisibility(View.GONE);
                        btnOrder.setVisibility(View.GONE);
                        del.setVisibility(View.VISIBLE);
                        cancel.setVisibility(View.VISIBLE);
                    }
                }
            });
            shopp_adapter.setOnDeleteListeners(new ShoppingCarAdapter.OnDeleteListeners() {

                @Override
                public void onDeletes(String[] iArray, int groupPosition, int childPosition) {
                    if (iArray != null) {
                        //购物车传过来的二级列表ID
                        list = Arrays.asList(iArray);
                        for (int i = 0; i < iArray.length; i++) {
                            if (str == "") {
                                str = list.get(i);
                            } else {
                                str = str + "," + list.get(i);
                            }
                        }
                        headmaps = new HashMap<>();
                        maps = new HashMap<>();
                        maps.put("appid", appid);
                        maps.put("appsecret", appsecret);
                        maps.put("timestamp", time);
                        maps.put("sign", sha1);
                        maps.put("id", str);
                        groupPositions = groupPosition;
                        childPositions = childPosition;
                        //服务端删除
                        presenter.getpost(Contacts.My_delshopping, headmaps, maps, Del_Shooppoing_Bean.class);
                    } else {
                        ToastUtils.show("请选择商品");
                    }
                }
            });
            //修改商品数量的回调
            shopp_adapter.setOnChangeCountListener(new ShoppingCarAdapter.OnChangeCountListener() {
                @Override
                public void onChangeCount(String goods_id, Integer num) {
                    headmap = new HashMap<>();
                    map = new HashMap<>();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("id", goods_id);
                    map.put("num", num);
                    //服务端修改数量
                    presenter.getpost(Contacts.My_upshopping, headmap, map, Del_Address_Bean.class);
                }
            });
            //不能点击收缩
            allshopp.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    return true;
                }
            });


            shopp_adapter.setListener(new ShoppingCarAdapter.onListener() {
                @Override
                public void OnListener(int i) {
                    ToastUtils.show(i+"");
                    initSpecifcations();
                }
            });
        } else if (data instanceof Del_Address_Bean) {
            //删除成功
            Del_Address_Bean del_address_bean = (Del_Address_Bean) data;
            if (del_address_bean.getCode().equals("200")) {
                Log.d(TAG, "success: " + del_address_bean.getMsg());
            }
        } else if (data instanceof Del_Shooppoing_Bean) {
            Del_Shooppoing_Bean del_shooppoing_bean = (Del_Shooppoing_Bean) data;
            if (del_shooppoing_bean.getCode().equals("200")) {
                ToastUtils.show("删除成功");
                editor.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                tvTotalPrice.setVisibility(View.VISIBLE);
                btnOrder.setVisibility(View.VISIBLE);
                del.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
               /* for (int i = 0; i < list.size(); i++) {
                    data1.get(groupPositions).getList().remove(childPositions);
                    Log.d(TAG, "success: " + groupPositions + childPositions);
                }
                if (data1.get(groupPositions).getList().size() <= 0) {
                    data1.remove(groupPositions);
                }
                shopp_adapter.notifyDataSetChanged();*/
                time = System.currentTimeMillis();
                String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                sha1 = EncryptUtils.getSHA(sha);
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                presenter.getpost(Contacts.My_shopping, headmap, map, Shopping_Bean.class);
            }
        }
    }

    @Override
    public void error(String error) {
        Log.d(TAG, "error: " + error);
    }


    private void initSpecifcations() {
        //普通商品选规格
        View view = getLayoutInflater().inflate(R.layout.specificationspop, null);
        PopupWindow   popspecifcations = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      /*  ImageView tvdismiss = view.findViewById(R.id.iv_dismiss);
        TextView prices = view.findViewById(R.id.sku_tv_price);
        TextView sku_tv_price = view.findViewById(R.id.sku_tv_price);
        Button tv_sure = view.findViewById(R.id.tv_sure);
        Button tv_independent = view.findViewById(R.id.tv_independent);
        Button tv_determine = view.findViewById(R.id.tv_determine);
        if (type == 1) {
            tv_sure.setVisibility(View.VISIBLE);
            tv_independent.setVisibility(View.VISIBLE);
            tv_determine.setVisibility(View.GONE);
        } else {
            tv_sure.setVisibility(View.GONE);
            tv_independent.setVisibility(View.GONE);
            tv_determine.setVisibility(View.VISIBLE);
        }
        TextView cy = view.findViewById(R.id.cy);
        TextView kc = view.findViewById(R.id.kc);
        ImageView pic = view.findViewById(R.id.sku_pic);
        RecyclerView sku_one = view.findViewById(R.id.sku_one);
        RecyclerView sku_two = view.findViewById(R.id.sku_two);
        RelativeLayout jian = view.findViewById(R.id.jian);
        RelativeLayout jia = view.findViewById(R.id.jia);*/
       /* GlideUtils.loadRoundCircleImage(this, Urls.BASEURL + data1.getPic(), pic);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        sku_one.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManagers = new LinearLayoutManager(this);
        layoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
        sku_two.setLayoutManager(layoutManagers);
        Details_Adapter details_adapter = new Details_Adapter(variety, this);
        sku_one.setAdapter(details_adapter);
        Details_item_Adapter details_item_adapter = new Details_item_Adapter(this, variety.get(0).getVoo());
        sku_two.setAdapter(details_item_adapter);
        Tv_Price_Utils.initPriceTv(this, price, prices);
        in_stock = variety.get(onepositions).getVoo().get(secondary).getIn_stock();
        //默认第一个库存
        kc.setText("库存" + variety.get(0).getVoo().get(0).getIn_stock());
        //一级
        details_adapter.setOnItemClick(new Details_Adapter.OnItemClick() {
            @Override
            public void onItemClick(View view1, int position) {
                int length = stringBuffer.length();
                stringBuffer.delete(0, length);
                onepositions = position;
                if (spgg_id != null) {
                    spgg_id = null;
                }
                String title = variety.get(position).getTitle();
                stringBuffer.append(title);
                details_item_adapter.setVariety(variety.get(position).getVoo());
                details_item_adapter.notifyDataSetChanged();
                cy.setText(stringBuffer.toString());
                kc.setText("库存" + variety.get(position).getVoo().get(0).getIn_stock());
            }
        });
        //二级
        details_item_adapter.setOnItemClicks(new Details_item_Adapter.OnItemClicks() {
            @Override
            public void onItemClicks(View view2, int i) {
                secondary = i;
                String fidname = variety.get(onepositions).getVoo().get(i).getTitle();
                String gg_price = variety.get(onepositions).getVoo().get(i).getPrice();
                spgg_id = variety.get(onepositions).getVoo().get(i).getId();
                cy.setText(stringBuffer.toString() + " " + fidname);
                sku_tv_price.setText("¥" + gg_price);
                Tv_Price_Utils.initPriceTv(Spell_DetailsActivity.this, gg_price, sku_tv_price);
                kc.setText("库存" + variety.get(onepositions).getVoo().get(i).getIn_stock());
                in_stock = variety.get(onepositions).getVoo().get(secondary).getIn_stock();
                String tvNum = tv_num.getText().toString();
                double one = Double.parseDouble(tvNum);
                double two = in_stock;
                double there = two - one;
                String str = String.valueOf(there);
                kc.setText("库存" + str.substring(0, str.length() - 2) + "");
            }
        });

        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvNum = tv_num.getText().toString();
                if (tvNum.equals("1")) {
                    ToastUtil.showShort(Spell_DetailsActivity.this, "最低数量为1");
                } else {
                    String tvNums = tv_num.getText().toString();
                    int tv = Integer.parseInt(tvNums);
                    int i = --tv;
                    tv_num.setText(i + "");
                    kc.setText("库存" + ++in_stock + "");
                }
            }
        });
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvNum = tv_num.getText().toString();
                if (tvNum.equals(in_stock)) {
                    ToastUtil.showShort(Spell_DetailsActivity.this, "数量超出范围");
                } else {
                    int tv = Integer.parseInt(tvNum);
                    int i = ++tv;
                    tv_num.setText(i + "");
                    kc.setText("库存" + --in_stock + "");
                }
            }
        });
        //加入购物车
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = tv_num.getText().toString();
                if (num != null && spgg_id != null) {
                    map.clear();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("num", num);
                    map.put("gg_id", spgg_id);
                    presenter.getpost(Contacts.add_shopping_carts, headmap, map, AddShoppingBean.class);
                } else {
                    ToastUtil.showShort(Spell_DetailsActivity.this, "请选择规格");
                }
            }
        });

        //立即购买
        tv_independent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String custom = maintenance.getText().toString();
                String num = tv_num.getText().toString();
                if (num != null && spgg_id != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", spgg_id);
                    bundle.putString("num", num);
                    bundle.putString("type", "1");
                    if (custom.equals("常规维护")) {
                        String customid = String.valueOf(custom_id);
                        String substring = customid.substring(0, customid.length() - 1);
                        bundle.putString("options", substring);
                        Log.d(TAG, "onClick: " + "ggid" + spgg_id + "num" + num + "substring" + substring);
                    }
                    Log.d(TAG, "onClick: " + "ggid" + spgg_id + "num" + num);
                    IntentUtils.getIntents().Intent(Spell_DetailsActivity.this, BuyActivity.class, bundle);
                    popspecifcations.dismiss();
                } else {
                    ToastUtil.showShort(Spell_DetailsActivity.this, "请选择规格");
                }
            }
        });

        tv_determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 2) {
                    //立即购买的确认按钮
                    String custom = maintenance.getText().toString();
                    String num = tv_num.getText().toString();
                    if (num != null && spgg_id != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", spgg_id);
                        bundle.putString("num", num);
                        bundle.putString("type", "1");
                        if (custom.equals("常规维护")) {
                            String customid = String.valueOf(custom_id);
                            String substring = customid.substring(0, customid.length() - 1);
                            bundle.putString("options", substring);
                            Log.d(TAG, "onClick: " + "ggid" + spgg_id + "num" + num + "substring" + substring);
                        }
                        Log.d(TAG, "onClick: " + "ggid" + spgg_id + "num" + num);
                        IntentUtils.getIntents().Intent(Spell_DetailsActivity.this, BuyActivity.class, bundle);
                        popspecifcations.dismiss();
                    } else {
                        ToastUtil.showShort(Spell_DetailsActivity.this, "请选择规格");
                    }
                } else if (type == 3) {
                    //加入购物车的确认按钮
                    String num = tv_num.getText().toString();
                    if (num != null && spgg_id != null) {
                        map.clear();
                        map.put("appid", appid);
                        map.put("appsecret", appsecret);
                        map.put("timestamp", time);
                        map.put("sign", sha1);
                        map.put("num", num);
                        map.put("gg_id", spgg_id);
                        presenter.getpost(Contacts.add_shopping_carts, headmap, map, AddShoppingBean.class);
                    } else {
                        ToastUtil.showShort(Spell_DetailsActivity.this, "请选择规格");
                    }
                }
            }
        });*/
        /*tvdismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popspecifcations.dismiss();
                WindowManager.LayoutParams lp =   getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });*/
        WindowManager.LayoutParams lp =   getActivity().getWindow().getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().setAttributes(lp);
        popspecifcations.setFocusable(true);
        popspecifcations.setBackgroundDrawable(new BitmapDrawable());
        popspecifcations.setOutsideTouchable(true);
        popspecifcations.setTouchable(true);
        popspecifcations.setAnimationStyle(R.style.mypopwindow_anim_style);
        popspecifcations.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp =   getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        popspecifcations.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

}
