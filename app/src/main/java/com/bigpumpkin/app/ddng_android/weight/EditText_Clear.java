package com.bigpumpkin.app.ddng_android.weight;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.bigpumpkin.app.ddng_android.R;

public class EditText_Clear extends android.support.v7.widget.AppCompatEditText {

    private Drawable clearDrawable,searchDrawable;

    public EditText_Clear(Context context) {
        super(context);
        init();
    }

    public EditText_Clear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public EditText_Clear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    /**
     * 步骤2：初始化 图标资源
     */
    private void init() {
        searchDrawable = getResources().getDrawable(R.mipmap.good_search);
        searchDrawable.setBounds(0,0,40,40);
        setCompoundDrawables(searchDrawable,null,null,null);

    }


    /**
     * 步骤3：通过监听复写EditText本身的方法来确定是否显示删除图标
     * 监听方法：onTextChanged（） & onFocusChanged（）
     * 调用时刻：当输入框内容变化时 & 焦点发生变化时
     */

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIconVisible(hasFocus() && text.length() > 0);
        // hasFocus()返回是否获得EditTEXT的焦点，即是否选中
        // setClearIconVisible（） = 根据传入的是否选中 & 是否有输入来判断是否显示删除图标->>关注1
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setClearIconVisible(focused && length() > 0);
        // focused = 是否获得焦点
        // 同样根据setClearIconVisible（）判断是否要显示删除图标
    }

    /**
     * 作用：判断是否显示删除图标
     */
    private void setClearIconVisible(boolean visible) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(searchDrawable, null,
                visible ? clearDrawable : null, null);
    }

    /**
     * 步骤4：对删除图标区域设置点击事件，即"点击 = 清空搜索框内容"
     * 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            // 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
            case MotionEvent.ACTION_UP:
             /*   Drawable drawable = clearDrawable;
                if (drawable != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {
                    setText("");
                }
                searchDrawable.setBounds(0,0,36,36);
                clearDrawable.setBounds(0,0,40,40);*/
                break;
        }
        return super.onTouchEvent(event);
    }
}
