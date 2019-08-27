package com.bigpumpkin.app.ddng_android.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Buy_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
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
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private String appid;
    private String appsecret;
    private long time;
    private String sha1;
    private MyPresenterImpl presenter;
    String str = "";
    private RelativeLayout dz;
    private ExpandableListView confirm;
    private TextView price, name, phone, address;
    private Button submit;
    private List<Settlement_Bean.DataBean.ListBean> list;
    private Settlement_Bean.DataBean.AddressBean address2;
    private Buy_Adapter buy_adapter;
    private String[] lists;
    private String mes;
    private String shopping_all_price;

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
        //购物车传过来的二级列表ID
        lists = getIntent().getExtras().getStringArray("list");
        List<String> list = Arrays.asList(lists);
        for (int i = 0; i < lists.length; i++) {
            if (str == "") {
                str = list.get(i);
            } else {
                str = str + "," + list.get(i);
            }
        }
        dz = findViewById(R.id.dz);
        //二级列表
        confirm = findViewById(R.id.confirm);
        //总价
        price = findViewById(R.id.price);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        //提交订单
        submit = findViewById(R.id.submit);
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
        map.put("arr", str);
        presenter.getpost(Contacts.My_usettlementhopping, headmap, map, Settlement_Bean.class);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy_adapter.setListener(new Buy_Adapter.onListener() {
                    @Override
                    public void OnListener(String msg) {
                        //留言
                        mes = msg;
                    }
                });
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

    @Override
    public void initData() {

    }

    @Override
    public void success(Object data) {
        if (data instanceof Settlement_Bean) {
            Settlement_Bean settlement_bean = (Settlement_Bean) data;
            if (settlement_bean != null) {
                if (settlement_bean.getCode().equals("200")) {
                    //提前设置收货地址
                    address2 = settlement_bean.getData().getAddress();
                    list = settlement_bean.getData().getList();
                    String name = address2.getName();
                    String tel = address2.getTel();
                    String sheng = address2.getSheng();
                    String shi = address2.getShi();
                    String qu = address2.getQu();
                    String address1 = address2.getAddress();
                    shopping_all_price = settlement_bean.getData().getShopping_all_price();

                    price.setText("合计金额：" + shopping_all_price + "元");
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
                    buy_adapter = new Buy_Adapter(this, list);
                    confirm.setAdapter(buy_adapter);
                    dz.setVisibility(View.VISIBLE);
                } else if (settlement_bean.getCode().equals("003")) {
                    showDeleteDialog();
                    dz.setVisibility(View.GONE);
                }
            }
        }
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
