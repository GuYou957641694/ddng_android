package com.bigpumpkin.app.ddng_android.activity;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.HomeAdapter;
import com.bigpumpkin.app.ddng_android.adapter.MenuAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Category_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomCategoryActivity extends BaseActivity implements MyView {


    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private ListView lv_menu;
    private ListView lv_home;
    private List<String> menuList = new ArrayList<>();
    private List<Integer> showTitle;
    private MenuAdapter menuAdapter;
    private HomeAdapter homeAdapter;
    private int currentItem;

    @Override
    public int intiLayout() {
        return R.layout.activity_custom_category;
    }

    @Override
    public void initView() {
        lv_menu = (ListView) findViewById(R.id.lv_menu);
        lv_home = (ListView) findViewById(R.id.lv_home);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        presenter.get(Contacts.category, headmap, map, Category_Bean.class);
    }

    @Override
    public void initData() {
    }

    @Override
    public void success(Object data) {
        if (data instanceof Category_Bean) {
            Category_Bean category_bean = (Category_Bean) data;
            Category_Bean.DataBean data1 = category_bean.getData();
            List<Category_Bean.DataBean.SpfBean> spf = data1.getSpf();
            menuAdapter = new MenuAdapter(this, menuList);
            lv_menu.setAdapter(menuAdapter);
            homeAdapter = new HomeAdapter(this, spf);
            lv_home.setAdapter(homeAdapter);
            if (data1 != null) {
                showTitle = new ArrayList<>();
                for (int i = 0; i < data1.getSpf().size(); i++) {
                    menuList.add(data1.getSpf().get(i).getTitle());
                    showTitle.add(i);
                }
                menuAdapter.notifyDataSetChanged();
                homeAdapter.notifyDataSetChanged();

                lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        menuAdapter.setSelectItem(position);
                        menuAdapter.notifyDataSetInvalidated();
                        lv_home.setSelection(showTitle.get(position));
                    }
                });

                lv_home.setOnScrollListener(new AbsListView.OnScrollListener() {
                    private int scrollState;

                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        this.scrollState = scrollState;
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem,
                                         int visibleItemCount, int totalItemCount) {
                        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                            return;
                        }
                        int current = showTitle.indexOf(firstVisibleItem);
                        //lv_home.setSelection(current);
                        if (currentItem != current && current >= 0) {
                            currentItem = current;
                            menuAdapter.setSelectItem(currentItem);
                            menuAdapter.notifyDataSetInvalidated();
                        }
                    }
                });
            }
        }
    }

    @Override
    public void error(String error) {
        Log.d(TAG, "error: "+error);
    }
}
