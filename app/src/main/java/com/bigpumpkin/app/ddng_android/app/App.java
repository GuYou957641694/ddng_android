package com.bigpumpkin.app.ddng_android.app;

import android.app.Application;
import android.content.Context;

import com.android.tony.defenselib.handler.IExceptionHandler;
import com.bigpumpkin.app.ddng_android.config.WXConFig;
import com.bigpumpkin.app.ddng_android.utils.SpzUtilUser;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.hjq.toast.ToastUtils;
import com.mob.MobSDK;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends Application implements IExceptionHandler {

    public static final String WXPAY_TYPE_COIN = "coin";
    public static final String WXPAY_TYPE_CLASS = "course";
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        //短信
        MobSDK.init(this);
        SpzUtils.init(this);
        SpzUtilUser.init(this);
        UMConfigure.init(this, "5d4e50853fc195c072000b8e", "umeng",
                UMConfigure.DEVICE_TYPE_PHONE, "");
        PlatformConfig.setWeixin(WXConFig.APP_ID, WXConFig.APP_SECRET);
        ToastUtils.init(this);
    }
    //防止奔溃
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       /* DefenseCrash.initialize();
        DefenseCrash.install(this);*/
    }

    @Override
    public void onCaughtException(Thread thread, Throwable throwable, boolean b) {
        throwable.printStackTrace();
    }

    @Override
    public void onEnterSafeMode() {

    }

    @Override
    public void onMayBeBlackScreen(Throwable throwable) {

    }
}
