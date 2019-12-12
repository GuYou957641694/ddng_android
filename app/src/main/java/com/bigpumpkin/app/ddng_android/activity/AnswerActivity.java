package com.bigpumpkin.app.ddng_android.activity;

import android.webkit.WebView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

public class AnswerActivity extends BaseActivity implements MyView {

    private String uri = "http://weilailingdi.weilailingdi.com/Web/Mall_index/xyzz/th/Intelligent_solution";

    @Override
    public int intiLayout() {
        return R.layout.activity_answer;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        new TitleXML(AnswerActivity.this, "智能解答", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        WebView webView = findViewById(R.id.web);
        webView.loadUrl(uri);
    }

    @Override
    public void initData() {

    }

    @Override
    public void success(Object data) {

    }

    @Override
    public void error(String error) {

    }
}
