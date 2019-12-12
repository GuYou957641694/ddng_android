package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.VideoBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bumptech.glide.Glide;

import java.util.HashMap;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import de.hdodenhof.circleimageview.CircleImageView;

public class VideoActivity extends BaseActivity implements View.OnClickListener, MyView {


    private ImageView ivBack, iv_like_no, iv_like, iv_give, iv_give_true;
    private LinearLayout rlLike;
    private LinearLayout rlFocus;
    private LinearLayout rlShape;
    private TextView tvName;
    private CircleImageView cvHead;
    private TextView tvTitle, tv_give_num;
    private Button btEnter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String appid, user;
    private String appsecret;
    private String sha1;
    private long time;
    private String id;
    private JZVideoPlayerStandard mJC;


    @Override
    public int intiLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        user = sharedPreferences.getString("zt", "");
        id = getIntent().getStringExtra("id");
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        ToastUtil.showShort(this, id);
        ivBack = findViewById(R.id.iv_back);
        rlLike = findViewById(R.id.rl_like);
        rlFocus = findViewById(R.id.rl_focus);
        rlShape = findViewById(R.id.rl_shape);
        tvName = findViewById(R.id.tv_name);
        cvHead = findViewById(R.id.cv_head);
        tvTitle = findViewById(R.id.tv_title);
        btEnter = findViewById(R.id.bt_enter);
        tv_give_num = findViewById(R.id.tv_give_num);
        mJC = findViewById(R.id.mJC);
        iv_like_no = findViewById(R.id.iv_like_no);
        iv_like = findViewById(R.id.iv_like);
        iv_give = findViewById(R.id.iv_give);
        iv_give_true = findViewById(R.id.iv_give_true);
        ivBack.setOnClickListener(this);
        rlLike.setOnClickListener(this);
        rlFocus.setOnClickListener(this);
        rlShape.setOnClickListener(this);
        btEnter.setOnClickListener(this);
    }

    @Override
    public void initData() {
        map.put("id", id);
        if (TextUtils.isEmpty(user.trim())) {
            //没有登录
            presenter.get(Contacts.See_farmer_lists, headmap, map, VideoBean.class);
        } else {
            //登录
            time = System.currentTimeMillis();
            appid = SpzUtils.getString("appid");
            appsecret = SpzUtils.getString("appsecret");
            String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
            sha1 = EncryptUtils.getSHA(sha);
            map.put("appid", appid);
            map.put("appsecret", appsecret);
            map.put("timestamp", time);
            map.put("sign", sha1);
            presenter.getpost(Contacts.See_farmer_lists, headmap, map, VideoBean.class);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_like:
                ToastUtil.showShort(this, "喜欢");
                break;
            case R.id.rl_focus:
                ToastUtil.showShort(this, "关注");
                break;
            case R.id.rl_shape:
                ToastUtil.showShort(this, "分享");
                break;

        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof VideoBean) {
            VideoBean videoBean = (VideoBean) data;
            VideoBean.DataBean data1 = videoBean.getData();
            if (data1 != null) {
                GlideUtils.loadImage(this, data1.getPic(), cvHead);
                tvName.setText(data1.getName());
                tvTitle.setText(data1.getDes());
                tv_give_num.setText(data1.getPraise());
                mJC.setUp(Urls.BASEURL + data1.getVideo(), JZVideoPlayerStandard.SCROLL_AXIS_HORIZONTAL, "");
                mJC.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(this).load(Urls.BASEURL + data1.getImage_pic()).into(mJC.thumbImageView);
                //1关注 2未关注
                if (data1.getAttention().equals("1")) {
                    iv_like.setVisibility(View.VISIBLE);
                    iv_like_no.setVisibility(View.GONE);
                } else {
                    iv_like.setVisibility(View.GONE);
                    iv_like_no.setVisibility(View.VISIBLE);
                }
                //	1已点赞 2未点赞
                if (data1.getPraise_u().equals("1")) {
                    iv_give_true.setVisibility(View.VISIBLE);
                    iv_give.setVisibility(View.GONE);
                } else {
                    iv_give_true.setVisibility(View.GONE);
                    iv_give.setVisibility(View.VISIBLE);
                }

                btEnter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", data1.getFid());
                        IntentUtils.getIntents().Intent(VideoActivity.this, FarmActivity.class, bundle);
                    }
                });
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
