package com.bigpumpkin.app.ddng_android.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.Farm_ProductAdapter;
import com.bigpumpkin.app.ddng_android.adapter.SearchAdapter;
import com.bigpumpkin.app.ddng_android.adapter.Store_SearchAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.SearchBean;
import com.bigpumpkin.app.ddng_android.bean.Store_SearchBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.EditText_Clear;

import java.util.HashMap;
import java.util.List;

public class DetailsSearchActivity extends BaseActivity implements View.OnClickListener, MyView {

    private int TYPE = 0;
    private ImageView iv_id, iv_back;
    private TextView rb_niu1, rb_niu2, rb_niu4;
    private TextView rb_niu3;
    private ColorStateList cs;
    private ColorStateList csl;
    private EditText_Clear et_search;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String trim;
    private RecyclerView recyclerview;
    private ImageView tv_switch;
    int lastPostion;
    private List<Store_SearchBean.DataBean> data1;
    private ExpandableListView expandad;

    @Override
    public int intiLayout() {
        return R.layout.activity_details_search;
    }

    @Override
    public void initView() {
        trim = getIntent().getStringExtra("trim");
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        et_search = findViewById(R.id.et_search);
        tv_switch = findViewById(R.id.tv_switch);
        recyclerview = findViewById(R.id.recyclerview);
        expandad = findViewById(R.id.expandad);
        et_search.setText(trim);
        rb_niu1 = findViewById(R.id.rb_niu1);
        rb_niu2 = findViewById(R.id.rb_niu2);
        rb_niu3 = findViewById(R.id.rb_niu3);
        rb_niu4 = findViewById(R.id.rb_niu4);
        iv_id = findViewById(R.id.iv_id);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        rb_niu1.setOnClickListener(this);
        rb_niu2.setOnClickListener(this);
        rb_niu3.setOnClickListener(this);
        rb_niu4.setOnClickListener(this);
        csl = getResources().getColorStateList(R.color.textFF323232);
        cs = getResources().getColorStateList(R.color.textFFE72929);
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String str = et_search.getText().toString().trim();
                    if (str!=null) {
                        trim=str;
                        map.put("title", str);
                        map.put("page", "1");
                        presenter.get(Contacts.Botany_Searchs, headmap, map, SearchBean.class);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {
        map.put("title", trim);
        map.put("page", "1");
        presenter.get(Contacts.Botany_Searchs, headmap, map, SearchBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_niu1:
                rb_niu1.setTextColor(cs);
                rb_niu2.setTextColor(csl);
                rb_niu3.setTextColor(csl);
                rb_niu4.setTextColor(csl);
                iv_id.setImageResource(R.mipmap.icon_default_price);
                map.clear();
                map.put("title", trim);
                map.put("page", "1");
                presenter.get(Contacts.Botany_Searchs, headmap, map, SearchBean.class);
                expandad.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
                tv_switch.setImageDrawable(ContextCompat.getDrawable(DetailsSearchActivity.this, R.mipmap.search_classification));
                break;
            case R.id.rb_niu2:
                rb_niu1.setTextColor(csl);
                rb_niu2.setTextColor(cs);
                rb_niu3.setTextColor(csl);
                rb_niu4.setTextColor(csl);
                iv_id.setImageResource(R.mipmap.icon_default_price);
                map.clear();
                map.put("title", trim);
                map.put("page", "1");
                map.put("sorting", "sales");
                presenter.get(Contacts.Botany_Searchs, headmap, map, SearchBean.class);
                expandad.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
                tv_switch.setImageDrawable(ContextCompat.getDrawable(DetailsSearchActivity.this, R.mipmap.search_classification));
                break;
            case R.id.rb_niu4:
                rb_niu1.setTextColor(csl);
                rb_niu2.setTextColor(csl);
                rb_niu3.setTextColor(csl);
                rb_niu4.setTextColor(cs);
                iv_id.setImageResource(R.mipmap.icon_default_price);
                map.clear();
                map.put("title", trim);
                map.put("page", "1");
                map.put("type", "Farm");
                presenter.get(Contacts.Botany_Searchs, headmap, map, Store_SearchBean.class);
                expandad.setVisibility(View.VISIBLE);
                recyclerview.setVisibility(View.GONE);
                break;
            case R.id.rb_niu3:
                expandad.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
                tv_switch.setImageDrawable(ContextCompat.getDrawable(DetailsSearchActivity.this, R.mipmap.search_classification));
                if (TYPE == 0) {
                    //价格升序
                    ToastUtil.showShort(this, "价格升序");
                    iv_id.setImageResource(R.mipmap.icon_default_price_true);
                    TYPE = 1;
                    map.clear();
                    map.put("title", trim);
                    map.put("page", "1");
                    map.put("sorting", "sortingL");
                    presenter.get(Contacts.Botany_Searchs, headmap, map, SearchBean.class);
                } else {
                    //价格降序
                    ToastUtil.showShort(this, "价格降序");
                    iv_id.setImageResource(R.mipmap.icon_default_price_faler);
                    TYPE = 0;
                    map.clear();
                    map.put("title", trim);
                    map.put("page", "1");
                    map.put("sorting", "sortingD");
                    presenter.get(Contacts.Botany_Searchs, headmap, map, SearchBean.class);
                }
                rb_niu1.setTextColor(csl);
                rb_niu2.setTextColor(csl);
                rb_niu3.setTextColor(cs);
                rb_niu4.setTextColor(csl);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof SearchBean) {
            SearchBean searchBean = (SearchBean) data;
            List<SearchBean.DataBean> data2 = searchBean.getData();
            searchBean.setViewType(Farm_ProductAdapter.FOOT);
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
            SearchAdapter searchAdapter = new SearchAdapter(searchBean, this);
            recyclerview.setAdapter(searchAdapter);
            tv_switch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerview.getLayoutManager() instanceof GridLayoutManager) {
                        lastPostion = ((GridLayoutManager) recyclerview.getLayoutManager()).findFirstVisibleItemPosition();
                        recyclerview.setLayoutManager(new LinearLayoutManager(DetailsSearchActivity.this));
                        tv_switch.setImageDrawable(ContextCompat.getDrawable(DetailsSearchActivity.this, R.mipmap.search_classifications));
                        searchBean.setViewType(Farm_ProductAdapter.FOOT);
                        recyclerview.setAdapter(searchAdapter);
                        recyclerview.scrollToPosition(lastPostion);
                    } else {
                        lastPostion = ((LinearLayoutManager) recyclerview.getLayoutManager()).findFirstVisibleItemPosition();
                        recyclerview.setLayoutManager(new GridLayoutManager(DetailsSearchActivity.this, 2));
                        tv_switch.setImageDrawable(ContextCompat.getDrawable(DetailsSearchActivity.this, R.mipmap.search_classification));
                        searchBean.setViewType(Farm_ProductAdapter.BODY);
                        recyclerview.setAdapter(searchAdapter);
                        recyclerview.scrollToPosition(lastPostion);
                    }
                }
            });
            searchAdapter.setListener(new SearchAdapter.onListener() {
                @Override
                public void OnListener(int i) {
                    String id = data2.get(i).getId();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    IntentUtils.getIntents().Intent(DetailsSearchActivity.this, Spell_DetailsActivity.class, bundle);
                }
            });
            searchAdapter.setListenerfarm(new SearchAdapter.onListenerfarm() {
                @Override
                public void OnListenerfarm(int i) {

                }
            });
        } else if (data instanceof Store_SearchBean) {
            Store_SearchBean store_searchBean = (Store_SearchBean) data;
            data1 = store_searchBean.getData();
            Store_SearchAdapter store_searchAdapter = new Store_SearchAdapter(data1, this);
            expandad.setAdapter(store_searchAdapter);

        }
    }

    @Override
    public void error(String error) {

    }
}
