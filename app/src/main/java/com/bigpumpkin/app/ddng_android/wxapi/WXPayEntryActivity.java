package com.bigpumpkin.app.ddng_android.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.AllActivity;
import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.config.WXConFig;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);

        // should place after UI initialize
        api = WXAPIFactory.createWXAPI(this, WXConFig.APP_ID);
        api.registerApp(WXConFig.APP_ID);
        boolean b = api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (baseResp.errCode == 0) {
                String payType = getIntent().getStringExtra("_wxapi_payresp_extdata");
                ToastUtil.showShort(WXPayEntryActivity.this, "支付成功");
                if (App.WXPAY_TYPE_CLASS.equals(payType)) {
                    IntentUtils.getIntents().Intent(WXPayEntryActivity.this,
                            AllActivity.class, null);
                    finish();
                }
            } else {
                ToastUtil.showShort(WXPayEntryActivity.this, baseResp.errCode + "");
                finish();
            }
        }
    }
}
