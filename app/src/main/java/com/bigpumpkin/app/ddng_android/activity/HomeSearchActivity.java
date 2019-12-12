package com.bigpumpkin.app.ddng_android.activity;


import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.weight.SearchView;
import com.bigpumpkin.app.ddng_android.weight.bCallBack;

public class HomeSearchActivity extends BaseActivity {

    private SearchView mSearchView;

    @Override
    public int intiLayout() {
        return R.layout.activity_home_search;
    }

    @Override
    public void initView() {
        mSearchView = (SearchView) findViewById(R.id.search_view);

        // 5. 设置点击返回按键后的操作（通过回调接口）
        // 5. 设置点击返回按键后的操作（通过回调接口）
        mSearchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();

            }
        });
    }

    @Override
    public void initData() {

    }


}
