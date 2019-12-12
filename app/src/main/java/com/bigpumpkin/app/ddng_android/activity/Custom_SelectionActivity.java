package com.bigpumpkin.app.ddng_android.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Origin_Adapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.OriginBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.AutoLoadRecyclerView;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

//产区列表
public class Custom_SelectionActivity extends BaseActivity implements MyView {

    @BindView(R.id.tv_pic)
    ImageView tvPic;
    @BindView(R.id.tv_back)
    ImageView tvBack;
    TextView tvTitle;
    @BindView(R.id.rv)
    AutoLoadRecyclerView rv;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String type;
    private NestedScrollView nestedscrollview;
    private RelativeLayout rv_top;
    private RefreshLayout refreshLayout;
    private int page = 1;
    private boolean typefirst;
    private List<OriginBean.DataBean.ShopBean> shop;
    private Origin_Adapter origin_adapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_custom__selection;
    }

    @Override
    public void initView() {
        StatusBarCompat.translucentStatusBar(this);
        tvTitle = findViewById(R.id.tv_title);
        nestedscrollview = findViewById(R.id.nestedscrollview);
        rv_top = findViewById(R.id.rv_top);
        refreshLayout = findViewById(R.id.smartrefreshlayout);
        type = getIntent().getStringExtra("type");
        String id = getIntent().getStringExtra("id");
        String link = getIntent().getStringExtra("link");
        String title = getIntent().getStringExtra("title");
        tvTitle.setText(title);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        if (type.equals("1")) {
            //类型 botany（1 植物认养甄选） production（3 生产者说“甄选产区“） medicine（2 药食同源“药材产区
            map.put("type", "botany");
            map.put("id", id);
            map.put("area", link);
            map.put("page", page);
        } else if (type.equals("2")) {
            map.put("type", "medicine");
            map.put("id", id);
            map.put("area", link);
            map.put("page", page);
        } else if (type.equals("3")) {
            map.put("type", "production");
            map.put("id", id);
            map.put("area", link);
            map.put("page", page);
        }

        nestedscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int t, int i2, int i3) {
                int mHeight = rv_top.getHeight();//获取标题栏高度
                if (t <= 0) {//未滑动
                    rv_top.setBackgroundColor(Color.argb((int) 0, 31, 100, 240));
                } else if (t > 0 && t <= mHeight) { //滑动过程中 并且在mHeight之内
                    float scale = (float) t / mHeight;
                    float alpha = (255 * scale);
                    rv_top.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    rv_top.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    tvTitle.setTextColor(getResources().getColor(R.color.FF222222));
                    tvBack.setImageDrawable(getResources().getDrawable(R.mipmap.ic_backs));
                } else {//超过mHeight
                    rv_top.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                }
                if (nestedscrollview.getScrollY() == 0) {
                    //顶部
                    tvBack.setImageDrawable(getResources().getDrawable(R.mipmap.ic_back));
                    tvTitle.setTextColor(Color.argb((int) 255, 255, 255, 255));
                }
            }
        });
        //刷新
        initRefresh();

    }

    private void initRefresh() {
        //设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessageDelayed(message, 1000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessageDelayed(message, 1000);
            }
        });
    }

    @Override
    public void initData() {
        typefirst = true;
        presenter.get(Contacts.Production_areas, headmap, map, OriginBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof OriginBean) {
            OriginBean originBean = (OriginBean) data;
            OriginBean.DataBean.AreaBean area = originBean.getData().getArea();
            GlideUtils.loadImage(this, Urls.BASEURL + area.getPic(), tvPic);
            rv.setLayoutManager(new LinearLayoutManager(this));

            shop = originBean.getData().getShop();
            if (shop.size() > 1 && shop != null) {
                if (typefirst == true) {
                    origin_adapter = new Origin_Adapter(this, shop);
                    rv.setAdapter(origin_adapter);
                } else {
                    origin_adapter.updateData(shop);
                }
            } else {
                ToastUtils.show("暂无数据");
            }

            typefirst = false;

            origin_adapter.setOnItemClickListener(new Origin_Adapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    String id = shop.get(position).getId();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    IntentUtils.getIntents().Intent(Custom_SelectionActivity.this, Spell_DetailsActivity.class, bundle);
                }
            });
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:         //刷新加载
                    refreshLayout.finishRefresh(true);
                    // presenter.get(Contacts.Production_areas, headmap, map, OriginBean.class);
                    //adapter.setDatas(mList);
                    break;
                case 2:         //加载更多
                    refreshLayout.finishLoadMore(true);
                    //adapter.addMoreValue(mLoadMoreDatas);
                    ++page;
                    map.put("page", page);
                    presenter.get(Contacts.Production_areas, headmap, map, OriginBean.class);
                    break;
            }
            return false;
        }
    });

}
