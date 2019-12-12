package com.bigpumpkin.app.ddng_android.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyScrollviews extends ScrollView {

    private ScrollViewListener scrollViewListener = null;

    public MyScrollviews(Context context) {
        super(context);
    }

    public MyScrollviews(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollviews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public interface ScrollViewListener {
        void onScrollChanged(MyScrollviews scrollView, int l, int t, int oldl, int oldt);
    }
}
