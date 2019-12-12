package com.bigpumpkin.app.ddng_android.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.BuyNow_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.Buy_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Buy_NowBean;
import com.bigpumpkin.app.ddng_android.bean.GenerateOrdersBean;
import com.bigpumpkin.app.ddng_android.bean.Settlement_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.RoundCornerDialog;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BuyActivity extends BaseActivity implements MyView {

    private MyPresenterImpl presenter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private String appid;
    private String appsecret;
    private long time;
    private String sha1;
    String str = "";
    private RelativeLayout dz, switch_address,tz;
    private ExpandableListView confirm;
    private TextView price, name, phone, address;
    private Button submit;
    private List<Settlement_Bean.DataBean> list;
    private Settlement_Bean.DataBean.AddressBean address2;
    private Buy_Adapter buy_adapter;
    private BuyNow_Adapter buyNow_adapter;
    private String[] lists;
    private String mes, type, id, num, options;
    private String shopping_all_price;
    private double all_price;
    private Buy_NowBean.DataBean data1;
    private int insurance = 1;
    private LinearLayout father_lin;


    @Override
    public int intiLayout() {
        return R.layout.activity_buy;
    }

    @Override
    public void initView() {
        new TitleXML(BuyActivity.this, "确认订单", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        switch_address = findViewById(R.id.switch_address);
        dz = findViewById(R.id.dz);
        confirm = findViewById(R.id.confirm);
        price = findViewById(R.id.price);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        submit = findViewById(R.id.submit);
        father_lin = findViewById(R.id.father_lin);
        tz = findViewById(R.id.tz);
        //购物车传过来的二级列表ID
        lists = getIntent().getExtras().getStringArray("list");
        //type
        type = getIntent().getExtras().getString("type");
        //规格id
        id = getIntent().getExtras().getString("id");
        num = getIntent().getExtras().getString("num");
        //定制维护
        options = getIntent().getExtras().getString("options");
        if (lists != null) {
            List<String> list = Arrays.asList(lists);
            for (int i = 0; i < lists.length; i++) {
                if (str == "") {
                    str = list.get(i);
                } else {
                    str = str + "," + list.get(i);
                }
            }
        }
    }

    @Override
    public void initData() {
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        //如果type==1是从商品详情进入的
        if (type != null && type.equals("1")) {
            map.put("gg_id", id);
            map.put("num", num);
            map.put("insurance", 1);
            if (options != null) {
                map.put("maintain", options);
            }
            presenter.getpost(Contacts.buy_nows, headmap, map, Buy_NowBean.class);
            //点击生成订单
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    String nums = data1.getList().get(0).getDetails().get(0).getNum();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("gg_id", id);
                    map.put("num", nums);
                    map.put("type", "1");
                    //安卓提交的订单
                    map.put("order_type", "Android");
                    map.put("insurance", insurance + "");
                    map.put("self_mail", "1");
                    if (mes != null) {
                        map.put("message", mes);
                    } else {
                        map.put("message", "无留言");
                    }
                    presenter.getpost(Contacts.buy_now_orders, headmap, map, GenerateOrdersBean.class);
                }
            });
        } else {
            //从购物车进入
            map.put("arr", str);
            presenter.getpost(Contacts.My_usettlementhopping, headmap, map, Settlement_Bean.class);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", str);
                    bundle.putString("price", shopping_all_price);
                    if (EmptyUtils.isNotEmpty(mes)) {
                        bundle.putString("mes", mes);
                    } else {
                        bundle.putString("mes", "null");
                    }
                    IntentUtils.getIntents().Intent(BuyActivity.this, Buy_OrdersActivity.class, bundle);
                }
            });
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof Settlement_Bean) {
            father_lin.setVisibility(View.VISIBLE);
            tz.setVisibility(View.VISIBLE);
            Settlement_Bean settlement_bean = (Settlement_Bean) data;
            if (settlement_bean != null) {
                //提前设置收货地址
                address2 = settlement_bean.getData().getAddress();
                if (address2 != null) {
                    Settlement_Bean.DataBean data2 = settlement_bean.getData();
                    String name = address2.getName();
                    String tel = address2.getTel();
                    String sheng = address2.getSheng();
                    String shi = address2.getShi();
                    String qu = address2.getQu();
                    String address1 = address2.getAddress();
                    shopping_all_price = settlement_bean.getData().getShopping_all_price();
                    price.setText("¥" + shopping_all_price);
                    if (name != null) {
                        this.name.setText(name);
                    }
                    if (tel != null) {
                        this.phone.setText(tel);
                    }
                    if (sheng != null && address1 != null) {
                        this.address.setText(sheng + shi + qu + address1);
                    }
                    //设置适配器
                    buy_adapter = new Buy_Adapter(this, data2);
                    confirm.setAdapter(buy_adapter);
                    dz.setVisibility(View.VISIBLE);
                    //展開
                    for (int i = 0; i < buy_adapter.getGroupCount(); i++) {
                        confirm.expandGroup(i);
                    }
                    //不能点击收缩
                    confirm.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                        @Override
                        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                            return true;
                        }
                    });
                }
            }
            switch_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击切换地址
                    Intent in = new Intent(BuyActivity.this, SpellAddressActivity.class);
                    in.putExtra("id", address2.getId());
                    startActivityForResult(in, 1);
                }
            });
        } else if (data instanceof Buy_NowBean) {
            Buy_NowBean buy_nowBean = (Buy_NowBean) data;
            if (buy_nowBean != null) {
                data1 = buy_nowBean.getData();
                Buy_NowBean.DataBean.AddressBean address = buy_nowBean.getData().getAddress();
                all_price = data1.getList().get(0).getDetails().get(0).getAll_price();
                List<Buy_NowBean.DataBean.ListBean> lists = data1.getList();
                String name = address.getName();
                String tel = address.getTel();
                String sheng = address.getSheng();
                String shi = address.getShi();
                String qu = address.getQu();
                String address1 = address.getAddress();
                price.setText("¥" + this.all_price);
                if (name != null) {
                    this.name.setText(name);
                }
                if (tel != null) {
                    this.phone.setText(tel);
                }
                if (sheng != null && address1 != null) {
                    this.address.setText(sheng + shi + qu + address1);
                }

                //设置适配器
                buyNow_adapter = new BuyNow_Adapter(lists, this);
                confirm.setAdapter(buyNow_adapter);
                dz.setVisibility(View.VISIBLE);
                //展開
                for (int i = 0; i < buyNow_adapter.getGroupCount(); i++) {
                    confirm.expandGroup(i);
                }
                //不能点击收缩
                confirm.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        return true;
                    }
                });
                switch_address.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击切换地址
                        Intent in = new Intent(BuyActivity.this, SpellAddressActivity.class);
                        in.putExtra("id", address.getId());
                        startActivityForResult(in, 1);
                    }
                });
            }
            father_lin.setVisibility(View.VISIBLE);
            tz.setVisibility(View.VISIBLE);
            //加数量
            buyNow_adapter.setListeners(new BuyNow_Adapter.onListeners() {
                @Override
                public void OnListeners(int i) {
                    num = data1.getList().get(0).getDetails().get(i).getNum();
                    int j = Integer.parseInt(num);
                    j++;
                    map.clear();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("gg_id", id);
                    map.put("num", j);
                    presenter.getpost(Contacts.buy_nows, headmap, map, Buy_NowBean.class);
                }
            });
            //减数量
            buyNow_adapter.setListenerjian(new BuyNow_Adapter.onListenerjian() {
                @Override
                public void OnListenerjian(int i) {
                    num = data1.getList().get(0).getDetails().get(i).getNum();
                    int j = Integer.parseInt(num);
                    j--;
                    map.clear();
                    map.put("appid", appid);
                    map.put("appsecret", appsecret);
                    map.put("timestamp", time);
                    map.put("sign", sha1);
                    map.put("gg_id", id);
                    map.put("num", j);
                    presenter.getpost(Contacts.buy_nows, headmap, map, Buy_NowBean.class);
                }
            });
            //准收宝
            buyNow_adapter.setListenerz(new BuyNow_Adapter.onListenerz() {
                @Override
                public void OnListenerz(boolean type) {
                    if (type == true) {
                        insurance = 1;
                        map.clear();
                        map.put("appid", appid);
                        map.put("appsecret", appsecret);
                        map.put("timestamp", time);
                        map.put("sign", sha1);
                        map.put("gg_id", id);
                        map.put("num", num);
                        map.put("insurance", 1);
                        presenter.getpost(Contacts.buy_nows, headmap, map, Buy_NowBean.class);
                    } else if (type == false) {
                        insurance = 2;
                        map.clear();
                        map.put("appid", appid);
                        map.put("appsecret", appsecret);
                        map.put("timestamp", time);
                        map.put("sign", sha1);
                        map.put("gg_id", id);
                        map.put("num", num);
                        map.put("insurance", 2);
                        presenter.getpost(Contacts.buy_nows, headmap, map, Buy_NowBean.class);
                    }
                }
            });
            //留言
            buyNow_adapter.setListener(new BuyNow_Adapter.onListener() {
                @Override
                public void OnListener(String msg) {
                    mes = msg;
                }
            });

        } else if (data instanceof GenerateOrdersBean) {
            //生成订单成功的回调
            GenerateOrdersBean generateOrdersBean = (GenerateOrdersBean) data;
            String code = generateOrdersBean.getCode();
            if (code.equals("200")) {
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("type", type);
                bundle.putString("number", generateOrdersBean.getData().getNumber());
                bundle.putString("price", generateOrdersBean.getData().getPrice());
                bundle.putString("boby", generateOrdersBean.getData().getBoy());
                Log.d(TAG, "success: " + generateOrdersBean.getData().getNumber() + "A" + generateOrdersBean.getData().getPrice() + "V" + generateOrdersBean.getData().getBoy());
                IntentUtils.getIntents().Intent(BuyActivity.this, Buy_OrdersActivity.class, bundle);
            }
        }

    }

    @Override
    public void error(String error) {

    }


    private void showDeleteDialog() {
        View view = View.inflate(this, R.layout.dialog_two_btn, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(this, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        TextView tv_logout_cancel = (TextView) view.findViewById(R.id.tv_logout_cancel);
        tv_message.setText("您还没有添加收货地址，是否去添加");

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
                IntentUtils.getIntents().Intent(BuyActivity.this, Management_addressActivity.class, null);
                finish();
            }
        });
        //取消
        tv_logout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
                finish();
            }
        });
    }

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

}
