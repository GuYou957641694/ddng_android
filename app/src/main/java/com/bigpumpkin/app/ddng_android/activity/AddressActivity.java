package com.bigpumpkin.app.ddng_android.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Address_Success_Bean;
import com.bigpumpkin.app.ddng_android.bean.JsonBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.GetJsonDataUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity implements MyView {
    @BindView(R.id.personal_dress)
    TextView personalDress;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.personal_name)
    EditText personalName;
    @BindView(R.id.personal_phone)
    EditText personalPhone;
    @BindView(R.id.personal_address)
    EditText personalAddress;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String sha1;
    private long time;
    private ArrayList<JsonBean> options1Items = new ArrayList<>(); //省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private String shi;
    private String pickerViewText;

    @Override
    public int intiLayout() {
        return R.layout.activity_address;
    }

    @Override
    public void initView() {
        new TitleXML(this, "编辑地址", true, "").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        //解析json城市
        initJsonData();

    }


    @Override
    public void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.personal_dress, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_dress:
                showPickerView();
                break;
            case R.id.add:
                if (EmptyUtils.isNotEmpty(personalName.getText().toString()) && EmptyUtils.isNotEmpty(personalPhone.getText().toString()) && EmptyUtils.isNotEmpty(personalAddress.getText().toString())) {
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
                    map.put("tel", personalPhone.getText().toString());
                    map.put("name", personalName.getText().toString());
                    map.put("sheng", shi);
                    map.put("shi", shi);
                    map.put("qu", pickerViewText);
                    map.put("address", personalAddress.getText().toString());
                    map.put("youbian", "123");
                    presenter.getpost(Contacts.My_add_address, headmap, map, Address_Success_Bean.class);
                } else {
                    ToastUtil.showShort(this, "请输入完整");
                }
                break;
        }
    }


    @Override
    public void success(Object data) {
        if (data instanceof Address_Success_Bean) {
            Address_Success_Bean address_success_bean = (Address_Success_Bean) data;
            if (address_success_bean != null) {
                if (address_success_bean.getCode().equals("200")) {
                    ToastUtil.showShort(this, address_success_bean.getMsg() + "");
                    finish();
                }
            }
        }
    }

    @Override
    public void error(String error) {

    }

    private void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {


            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                pickerViewText = options1Items.get(options1).getPickerViewText();
                shi = options2Items.get(options1).get(options2);
                personalDress.setText(options1Items.get(options1).getPickerViewText() + "  "
                        + options2Items.get(options1).get(options2) + "  "
                        + options3Items.get(options1).get(options2).get(options3));
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(18)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器


        pvOptions.show();
    }

    private void initJsonData() {
/**
 * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
 * 关键逻辑在于循环体
 *
 * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json
        // 文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三级）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);
            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
}
