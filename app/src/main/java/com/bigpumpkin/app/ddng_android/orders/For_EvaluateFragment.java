package com.bigpumpkin.app.ddng_android.orders;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.EvaluateAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseLazyLoadFragment;
import com.bigpumpkin.app.ddng_android.bean.Address_Success_Bean;
import com.bigpumpkin.app.ddng_android.bean.EvaluateBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;

import java.util.HashMap;
import java.util.List;

//待评价
public class For_EvaluateFragment extends BaseLazyLoadFragment implements MyView {


    private static final String TAG = "aa";
    private ExpandableListView ev_for_evalutefragment;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private RelativeLayout relativeLayout;
    private String sha1;
    private long time;
    private String numbering;
    private int j;
    private List<EvaluateBean.DataBean> list;
    private EvaluateAdapter evaluateAdapter;
    private LoadingDialog dialog;

    @Override
    public int getLayout() {
        return R.layout.for_evalutefragment;
    }

    @Override
    public void initViews(View view) {
        relativeLayout = view.findViewById(R.id.no_orders);
        ev_for_evalutefragment = view.findViewById(R.id.ev_for_evalutefragment);
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
    }

    @Override
    public void loadData() {
        dialog = new LoadingDialog(getActivity(), "玩命加载中...");
        dialog.show();
        presenter.getpost(Contacts.my_comments, headmap, map, EvaluateBean.class);
    }


    @Override
    public void success(Object data) {
        if (data instanceof EvaluateBean) {
            EvaluateBean evaluateBean = (EvaluateBean) data;
            list = evaluateBean.getData();
            dialog.close();
            EvaluateAdapter evaluateAdapter = new EvaluateAdapter(list, getActivity());
            ev_for_evalutefragment.setAdapter(evaluateAdapter);


            //展開
            for (int i = 0; i < list.size(); i++) {
                ev_for_evalutefragment.expandGroup(i);
            }

            //不能点击收缩
            ev_for_evalutefragment.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    return true;
                }
            });

            relativeLayout.setVisibility(View.GONE);
            ev_for_evalutefragment.setVisibility(View.VISIBLE);
        } else if (data instanceof Address_Success_Bean) {

        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
