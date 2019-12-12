package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.DetailsActivity;
import com.bigpumpkin.app.ddng_android.adapter.FruitAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Fruit_Bean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import java.util.HashMap;
import java.util.List;

public class FragmentFruit extends BaseLazyLoadFragment implements MyView, OnLoadMoreListener {
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RecyclerView recyclerView;
    private List<Fruit_Bean.DataBean> data1;
    private SmartRefreshLayout mRefreshLayout;

    public static FragmentFruit newInstance(String id) {
        FragmentFruit mFragment = new FragmentFruit();
        Bundle bundle = new Bundle();
        bundle.putString("DATA", id);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        presenter = new MyPresenterImpl(this);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setOnLoadMoreListener(this);
        /**
         * 上滑  将底部加载状态置为默认状态
         */
        mRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                if (isDragging) {
                    mRefreshLayout.closeHeaderOrFooter();
                }
            }
        });
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("type", 7);
        map.put("pag", "1");
    }

    @Override
    public void loadData() {
        presenter.get(Contacts.plant, headmap, map, Fruit_Bean.class);
    }
    @Override
    public void success(Object data) {
        if (data instanceof Fruit_Bean) {
            Fruit_Bean fruit_bean = (Fruit_Bean) data;
            if (fruit_bean.getCode().equals("200")) {
                data1 = fruit_bean.getData();
                FruitAdapter fruitAdapter = new FruitAdapter(data1, getActivity());
                recyclerView.setAdapter(fruitAdapter);
                StaggeredGridLayoutManager staggeredGridLayoutManager =
                        new StaggeredGridLayoutManager(2,
                                StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);

                fruitAdapter.setOnItemClickListener(new FruitAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        //商品id
                        String id1 = data1.get(position).getId();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id1);
                        //跳转详情页
                        IntentUtils.getIntents().Intent(getActivity(), DetailsActivity.class, bundle);
                    }
                });
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    public int getLayout() {
        return R.layout.fragmentfruit;
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }
}
