package com.bigpumpkin.app.ddng_android.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class FlingNestedScrollView extends NestedScrollView {
    private static final String TAG = "FlingNestedScrollView";

    private ScrollBottomListener onBottomListener;
    private ScrollChangeListener onScrollChangeListener;
    private int downY, downX, mTouchSlop;
    private boolean mEnable = true;

    public FlingNestedScrollView(@NonNull Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public FlingNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public FlingNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mEnable) {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getRawX();
                downY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY != 0 && clampedY && null != onBottomListener) {
            onBottomListener.onScrollBottom();
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);



        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChange(this, l, t, oldl, oldt);
        }
    }

    public void setScrollBottomListener(ScrollBottomListener listener) {
        this.onBottomListener = listener;
    }

    public void setOnScrollChangeListener(ScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public interface ScrollBottomListener {
        void onScrollBottom();
    }



    public interface ScrollChangeListener {
        void onScrollChange(FlingNestedScrollView fnslv, int l, int t, int oldl, int oldt);
    }

    public void setScrollEnable(boolean enable) {
        this.mEnable = enable;
    }
}
