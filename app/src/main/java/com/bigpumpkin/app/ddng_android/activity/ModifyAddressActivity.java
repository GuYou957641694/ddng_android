package com.bigpumpkin.app.ddng_android.activity;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.AddressPickerView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;

public class ModifyAddressActivity extends BaseActivity implements MyView, View.OnClickListener {

    private Button add;
    private EditText personalName;
    private EditText personalPhone;
    private EditText personalAddress;
    private TextView personalDress;
    private Switch switchs;
    private String id;
    private int i;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String sha1;
    private long time;
    private String addsheng;
    private String addshi;
    private String addqu;
    private String shengcode;
    private String shicode;
    private String qucode;
    int isindex;

    @Override
    public int intiLayout() {
        return R.layout.activity_modify_address;
    }

    @Override
    public void initView() {
        new TitleXML(this, "编辑地址", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        add = findViewById(R.id.add);
        switchs = findViewById(R.id.switchs);
        personalName = findViewById(R.id.personal_name);
        personalPhone = findViewById(R.id.personal_phone);
        personalAddress = findViewById(R.id.personal_address);
        personalDress = findViewById(R.id.personal_dress);
        add.setOnClickListener(this);
        personalDress.setOnClickListener(this);
        //判断是修改还是新增
        String type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        addsheng = getIntent().getStringExtra("sheng");
        addshi = getIntent().getStringExtra("shi");
        addqu = getIntent().getStringExtra("qu");
        shengcode = getIntent().getStringExtra("sheng_code");
        shicode = getIntent().getStringExtra("shi_code");
        qucode = getIntent().getStringExtra("qu_code");
        String diqu = getIntent().getStringExtra("diqu");
        i = getIntent().getIntExtra("is", 0);
        if (name != null) {
            personalName.setText(name);
        }
        if (phone != null) {
            personalPhone.setText(phone + "");
        }
        if (addsheng != null && addshi != null && addqu != null) {
            personalDress.setText(addsheng + "-" + addshi + "-" + addqu);
        }
        if (diqu != null) {
            personalAddress.setText(diqu);
        }
        if (i == 1) {
            switchs.setChecked(true);
            isindex = 1;
        } else {
            switchs.setChecked(false);
            isindex = 2;
        }
        headmap = new HashMap<>();
        map = new HashMap<>();
        //判断是否选中默认
        switchs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Todo如果默认
                    isindex = 1;
                } else {
                    //Todo
                    isindex = 2;
                }
            }
        });
    }

    @Override
    public void initData() {

    }


    @Override
    public void success(Object data) {
        if (data instanceof Zfb_Bean) {
            Zfb_Bean address_success_bean = (Zfb_Bean) data;
            String code = address_success_bean.getCode();
            if (code.equals("200")) {
                finish();
            } else if (code.equals("001")) {
                //001是未改动
                finish();
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_dress:
                adddress();
                break;
            case R.id.add:
                dress();
                break;
        }
    }

    private void dress() {
        if (EmptyUtils.isNotEmpty(personalName.getText().toString()) && EmptyUtils.isNotEmpty(personalPhone.getText().toString()) && EmptyUtils.isNotEmpty(personalAddress.getText().toString())) {
            presenter = new MyPresenterImpl(this);
            time = System.currentTimeMillis();
            appid = SpzUtils.getString("appid");
            appsecret = SpzUtils.getString("appsecret");
            String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
            sha1 = EncryptUtils.getSHA(sha);
            map.put("appid", appid);
            map.put("appsecret", appsecret);
            map.put("timestamp", time);
            map.put("sign", sha1);
            map.put("id", id);
            map.put("tel", personalPhone.getText().toString());
            map.put("name", personalName.getText().toString());
            map.put("sheng", addsheng);
            map.put("shi", addshi);
            map.put("qu", addqu);
            map.put("sheng_code", shengcode);
            map.put("shi_code", shicode);
            map.put("qu_code", qucode);
            map.put("address", personalAddress.getText().toString());
            map.put("isindex", isindex);
            presenter.getpost(Contacts.My_add_address, headmap, map, Zfb_Bean.class);
        } else {
            ToastUtil.showShort(this, "请输入完整");
        }
    }

    private void adddress() {
        View view = getLayoutInflater().inflate(R.layout.pop_addresss, null);
        PopupWindow mPopupWindowtwo = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        AddressPickerView addressPickerView = view.findViewById(R.id.apvAddress);
        addressPickerView.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
            @Override
            public void onSureClick(String sheng, String shi, String qu, String provinceCode, String cityCode, String districtCode) {
                addsheng = sheng;
                addshi = shi;
                addqu = qu;
                shengcode = provinceCode;
                shicode = cityCode;
                qucode = districtCode;
                personalDress.setText(sheng + "-" + shi + "-" + qu);
                mPopupWindowtwo.dismiss();
            }
        });
        //产生背景变暗效果
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindowtwo.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        mPopupWindowtwo.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        mPopupWindowtwo.setOutsideTouchable(true);
        //设置可以点击
        mPopupWindowtwo.setTouchable(true);
        //mPopupWindow，指定刚才定义的style
        mPopupWindowtwo.setAnimationStyle(R.style.mypopwindow_anim_style);

        //设置popupWindow消失时的监听
        mPopupWindowtwo.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindowtwo.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }
}
