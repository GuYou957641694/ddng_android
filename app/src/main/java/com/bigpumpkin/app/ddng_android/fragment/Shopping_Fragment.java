package com.bigpumpkin.app.ddng_android.fragment;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseFragment;
import com.bigpumpkin.app.ddng_android.bean.Del_Address_Bean;
import com.bigpumpkin.app.ddng_android.bean.Shopping_Bean;
import com.bigpumpkin.app.ddng_android.config.ShoppingCarAdapter;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.LoginUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.DialogUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * 购物车
 */
public class Shopping_Fragment extends BaseFragment implements MyView {

    private static final String TAG = "Shopping_Fragment";
    private RelativeLayout relativeLayout, rela;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
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
//获取当前时间

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
        }
    }

    @Override
    public void success(final Object data) {
        DialogUtils.closeDialog(mWeiboDialog);
        if (data instanceof Shopping_Bean) {
            //购物车赋值
            Shopping_Bean shopping_bean = (Shopping_Bean) data;
            final List<Shopping_Bean.DataBean> data1 = shopping_bean.getData();
            if (data1 != null) {
                int size = data1.size();
                if (size > 0) {
                    shopp_adapter = new ShoppingCarAdapter(getActivity(), btnOrder, data1, llSelectAll, tvTotalPrice, editor);
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
                    /**
                     * 实际开发中，在此请求删除接口，删除成功后，
                     * 通过initExpandableListViewData（）方法刷新购物车数据。
                     * 注：通过bean类中的DatasBean的isSelect_shop属性，判断店铺是否被选中；
                     *                  GoodsBean的isSelect属性，判断商品是否被选中，
                     *                  （true为选中，false为未选中）
                     */
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
        } else if (data instanceof Del_Address_Bean) {
            //删除成功
            Del_Address_Bean del_address_bean = (Del_Address_Bean) data;
            if (del_address_bean.getCode().equals("200")) {
                Log.d(TAG, "success: " + del_address_bean.getMsg());
            }
        }
    }

    @Override
    public void error(String error) {
        Log.d(TAG, "error: " + error);
    }


    /**
     * 初始化ExpandableListView的数据
     * 并在数据刷新时，页面保持当前位置
     *
     * @param datas 购物车的数据
     */
   /* private void initExpandableListViewData(List<ShoppingCarDataBean.DatasBean> datas) {
        if (datas != null && datas.size() > 0) {
            //刷新数据时，保持当前位置
            shoppingCarAdapter.setData(datas);

            //使所有组展开
            for (int i = 0; i < shoppingCarAdapter.getGroupCount(); i++) {
                elvShoppingCar.expandGroup(i);
            }

            //使组点击无效果
            elvShoppingCar.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {
                    return true;
                }
            });

            tvTitlebarRight.setVisibility(View.VISIBLE);
            tvTitlebarRight.setText("编辑");
            rlNoContant.setVisibility(View.GONE);
            elvShoppingCar.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            rlTotalPrice.setVisibility(View.VISIBLE);
            btnOrder.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.GONE);
        } else {
            tvTitlebarRight.setVisibility(View.GONE);
            rlNoContant.setVisibility(View.VISIBLE);
            elvShoppingCar.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);
        }
    }*/
}
