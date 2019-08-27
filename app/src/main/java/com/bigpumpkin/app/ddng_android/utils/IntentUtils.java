package com.bigpumpkin.app.ddng_android.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class IntentUtils {

    private static IntentUtils intents;

    public static IntentUtils getIntents(){
        if(intents == null)
            intents = new IntentUtils();
        return intents;
    }
    // context this, cs跳转对象 bundle 传递参数
    public void Intent(Context context, Class<?> cs, Bundle bundle) {
        Intent i = new Intent(context, cs);
        if (bundle != null)
            i.putExtras(bundle);
        context.startActivity(i);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
