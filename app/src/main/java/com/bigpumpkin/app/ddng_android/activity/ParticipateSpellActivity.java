package com.bigpumpkin.app.ddng_android.activity;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.ParticipationCollagesBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.utils.Tv_Price_Utils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

//参与拼单
public class ParticipateSpellActivity extends BaseActivity implements MyView {


    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    //网络请求
    private String s_id, id, sha1, appid, appsecret,s_num;
    private long time;
    private ImageView tv_pic, iv_one, iv_two, iv_there, iv_frou, iv_five;
    private CircleImageView iv_head_portrait;
    private TextView tvname, tv_gg, tv_num, tv_original_price, tv_Spell_price, tv_places, tv_time, tv_number_of, tv_farm_name;
    private CountDownTimer countDownTimer;
    private Button bt_Participate;


    @Override
    public int intiLayout() {
        return R.layout.activity_participate_spell;
    }

    @Override
    public void initView() {
        new TitleXML(ParticipateSpellActivity.this, "参与拼单", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        id = getIntent().getStringExtra("id");
        s_id = getIntent().getStringExtra("s_id");
        s_num = getIntent().getStringExtra("s_num");
        ToastUtil.showShort(this,s_num);
        tv_pic = findViewById(R.id.tv_pic);
        tvname = findViewById(R.id.tv_name);
        tv_gg = findViewById(R.id.tv_gg);
        tv_num = findViewById(R.id.tv_num);
        bt_Participate = findViewById(R.id.bt_Participate);
        tv_farm_name = findViewById(R.id.tv_farm_name);
        tv_original_price = findViewById(R.id.tv_original_price);
        tv_Spell_price = findViewById(R.id.tv_Spell_price);
        tv_places = findViewById(R.id.tv_places);
        tv_time = findViewById(R.id.tv_time);
        iv_head_portrait = findViewById(R.id.iv_head_portrait);
        iv_one = findViewById(R.id.iv_one);
        iv_two = findViewById(R.id.iv_two);
        iv_there = findViewById(R.id.iv_there);
        iv_frou = findViewById(R.id.iv_frou);
        iv_five = findViewById(R.id.iv_five);
        tv_number_of = findViewById(R.id.tv_number_of);
    }

    @Override
    public void initData() {
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        time = System.currentTimeMillis();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        map.put("id", id);
        map.put("s_id", s_id);
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        presenter.getpost(Contacts.Participation_Collages, headmap, map, ParticipationCollagesBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof ParticipationCollagesBean) {
            bt_Participate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("s_id", s_id);
                    IntentUtils.getIntents().Intent(ParticipateSpellActivity.this, ParticipateDetailsActivity.class, bundle);
                    finish();
                }
            });
            ParticipationCollagesBean participationCollagesBean = (ParticipationCollagesBean) data;
            ParticipationCollagesBean.DataBean data1 = participationCollagesBean.getData();
            GlideUtils.loadImage(this, Urls.BASEURL + data1.getShop().getPic(), tv_pic);
           GlideUtils.loadImage(this, data1.getPic().get(0).getPic(), iv_head_portrait);
            tvname.setText(data1.getShop().getTitle());
            tv_gg.setText("已选：" + data1.getShop().getFruit_tree_varietylist().getTitle());
            Tv_Price_Utils.initPriceTv(this, data1.getShop().getFruit_tree_varietylist().getPrice(), tv_original_price);
            String str = "\u00a5".concat(data1.getPrice());
            SpannableString spannableString = new SpannableString(str);
            RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.6f);
            spannableString.setSpan(relativeSizeSpan, 1, data1.getPrice().length() - 2,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            AssetManager mgr = getAssets();
            Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
            tv_Spell_price.setTypeface(tf);
            tv_Spell_price.setText("拼单价" + spannableString);

    /*        SpannableString spannable= new SpannableString("仅剩" + s_num + "个名额，");
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFF72323")), 3, spannableString.length() - 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_places.setText(spannable);*/

            tv_number_of.setText("拼主:" + data1.getPic().get(0).getName());
            tv_farm_name.setText("团名:" + data1.getTitle());
            long etimes = data1.getTime();
            long time = System.currentTimeMillis();
            long ctime = etimes * 1000;
            long seconds = ctime - time;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            countDownTimer = new CountDownTimer(seconds, 1000) {
                public void onTick(long l) {
                    tv_time.setText(getTimeStr(l));
                }

                public void onFinish() {

                }
            }.start();
            if (s_num.equals("2")) {
                iv_two.setVisibility(View.GONE);
                iv_there.setVisibility(View.GONE);
                iv_frou.setVisibility(View.GONE);
                iv_five.setVisibility(View.GONE);
            } else if (s_num.equals("3")) {
                iv_there.setVisibility(View.GONE);
                iv_frou.setVisibility(View.GONE);
                iv_five.setVisibility(View.GONE);
            } else if (s_num.equals("4")) {
                iv_frou.setVisibility(View.GONE);
                iv_five.setVisibility(View.GONE);
            } else if (s_num.equals("5")) {
                iv_five.setVisibility(View.GONE);
            }


        }
    }

    private String getTimeStr(long l) {
        long day = l / (1000 * 24 * 60 * 60); //单位天
        long hour = (l - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60); //单位时
        long minute = (l - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60)) / (1000 * 60); //单位分
        long second = (l - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;//单位秒

        String hourStr = String.valueOf(hour);
        if (hourStr.length() == 1) {
            hourStr = "0" + hourStr;
        }
        String minStr = String.valueOf(minute);
        if (minStr.length() == 1) {
            minStr = "0" + minStr;
        }
        String secondStr = String.valueOf(second);
        if (secondStr.length() == 1) {
            secondStr = "0" + secondStr;
        }
        //如果day为0的时候天不显示
        if (day == 0) {
            return hourStr + ":" + minStr + ":" + secondStr;
        } else {
            return day + "天" + " " + hourStr + ":" + minStr + ":" + secondStr;
        }
    }

    @Override
    public void error(String error) {

    }
}
