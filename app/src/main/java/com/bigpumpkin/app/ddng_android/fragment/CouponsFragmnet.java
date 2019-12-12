package com.bigpumpkin.app.ddng_android.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.CouponsApadter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Coupons_Bean;
import com.bigpumpkin.app.ddng_android.view.MyView;

public class CouponsFragmnet extends BaseLazyLoadFragment implements MyView {


    private RecyclerView recyclerview;
    private RelativeLayout il_layout;

    @Override
    public int getLayout() {
        return R.layout.couponsfragment_item;
    }

    @Override
    public void initViews(View view) {
        Coupons_Bean.DataBean data1 = (Coupons_Bean.DataBean) getArguments().getSerializable("bean");
        int position = getArguments().getInt("position");
        RelativeLayout lin = view.findViewById(R.id.lin);
        recyclerview = view.findViewById(R.id.recyclerview);
        il_layout = view.findViewById(R.id.il_layout);

        if (position == 0) {
            if (data1.getUnused() != null && data1.getUnused().size() > 0) {
                CouponsApadter couponsApadter = new CouponsApadter(data1, getActivity(), position);
                recyclerview.setAdapter(couponsApadter);
                recyclerview.setVisibility(View.VISIBLE);
                il_layout.setVisibility(View.GONE);
            }  else {
                recyclerview.setVisibility(View.GONE);
                il_layout.setVisibility(View.VISIBLE);
            }
        } else if (position == 1) {
            if (data1.getUsed() != null && data1.getUsed().size() > 0) {
                CouponsApadter couponsApadter = new CouponsApadter(data1, getActivity(), position);
                recyclerview.setAdapter(couponsApadter);
                recyclerview.setVisibility(View.VISIBLE);
                il_layout.setVisibility(View.GONE);
            } else {
                recyclerview.setVisibility(View.GONE);
                il_layout.setVisibility(View.VISIBLE);
            }
        } else if (position == 2) {
            if (data1.getOverdue() != null && data1.getOverdue().size() > 0) {
                CouponsApadter couponsApadter = new CouponsApadter(data1, getActivity(), position);
                recyclerview.setAdapter(couponsApadter);
                recyclerview.setVisibility(View.VISIBLE);
                il_layout.setVisibility(View.GONE);
            } else {
                recyclerview.setVisibility(View.GONE);
                il_layout.setVisibility(View.VISIBLE);
            }
        }

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void loadData() {

    }

    @Override
    public void success(Object data) {

    }

    @Override
    public void error(String error) {

    }
}
