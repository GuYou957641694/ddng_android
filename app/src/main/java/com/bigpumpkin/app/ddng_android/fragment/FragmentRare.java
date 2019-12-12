package com.bigpumpkin.app.ddng_android.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.DetailsActivity;
import com.bigpumpkin.app.ddng_android.adapter.PlantAdapter;
import com.bigpumpkin.app.ddng_android.adapter.RareAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Plant_Bean;
import com.bigpumpkin.app.ddng_android.bean.RareBean;
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

public class FragmentRare extends BaseLazyLoadFragment implements MyView, OnLoadMoreListener {

    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RecyclerView recyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private List<RareBean.DataBean> data1;

    public static FragmentRare newInstance(String id) {
        FragmentRare mFragment = new FragmentRare();
        Bundle bundle = new Bundle();
        bundle.putString("DATA", id);
        mFragment.setArguments(bundle);
        return mFragment;
    }


    @Override
    public int getLayout() {
        return R.layout.rare_fragment;
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
        map.put("type", 4);
        map.put("pag", 1);
    }

    @Override
    public void loadData() {
        presenter.get(Contacts.plant, headmap, map, RareBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof RareBean){
            RareBean rareBean = (RareBean) data;
            data1 = rareBean.getData();
            RareAdapter rareAdapter = new RareAdapter(data1,getActivity());
            recyclerView.setAdapter(rareAdapter);

            StaggeredGridLayoutManager staggeredGridLayoutManager =
                    new StaggeredGridLayoutManager(2,
                            StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);

            rareAdapter.setOnItemClickListener(new PlantAdapter.OnItemClickListener() {
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

    @Override
    public void error(String error) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore(2000);
        map.clear();
        map.put("type", 4);
        map.put("pag", 2);
        presenter.get(Contacts.plant, headmap, map, Plant_Bean.class);
    }
}
