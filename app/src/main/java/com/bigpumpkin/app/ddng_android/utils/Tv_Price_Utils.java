package com.bigpumpkin.app.ddng_android.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

public class Tv_Price_Utils {


    public static void initPriceTv(Context context, String price, TextView textView) {
        String str = "\u00a5".concat(price);
        SpannableString spannableString = new SpannableString(str);
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.6f);
        spannableString.setSpan(relativeSizeSpan, 1, price.length() - 2,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        AssetManager mgr = context.getAssets();
        Typeface tf = Typeface.createFromAsset(mgr, "Avenir Heavy.ttf");
        textView.setTypeface(tf);
        textView.setText(spannableString);
    }

    public static void initPrice( Context context, String price, TextView textView) {
        String str = "\u00a5".concat(price);
        SpannableString spannableString = new SpannableString(str);
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.3f);
        spannableString.setSpan(relativeSizeSpan,  1, price.length() - 2,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }
}
