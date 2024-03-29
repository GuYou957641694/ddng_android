package com.bigpumpkin.app.ddng_android.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class CustomViewPagers extends ViewPager {

    public CustomViewPagers(Context context) {

        this(context, null);
    }


    public CustomViewPagers(Context context, AttributeSet attrs) {

        super(context, attrs);

    }


    @Override

    public boolean onInterceptTouchEvent(MotionEvent ev) {

        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            Log.i("TAG", "onInterceptTouchEvent: 多点触摸系统Bug");
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.i("TAG", "onInterceptTouchEvent: 多点触摸系统Bug");
        }

        return false;

    }

}
