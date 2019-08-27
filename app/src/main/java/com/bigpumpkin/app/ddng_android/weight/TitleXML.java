package com.bigpumpkin.app.ddng_android.weight;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;

public class TitleXML implements View.OnClickListener {

    private Context context;
    private TitleXMLClick listener;

    private String title, strRightTag;
    private boolean aBoolean;
    private OnRightTagClickListener mOnRightTagClickListener;
    private TextView tvRightTag;

    public TitleXML(Context context, String title, boolean aBoolean) {
        this.context = context;
        this.title = title;
        this.aBoolean = aBoolean;
    }

    public TitleXML(Context context, String title, boolean aBoolean, String strRightTag) {
        this(context, title, aBoolean);
        this.strRightTag = strRightTag;
    }


    @Override
    public void onClick(View view) {
        if (listener == null) return;
        switch (view.getId()) {
            case R.id.return_iv:
                listener.onImage();
                break;
            case R.id.tv_right_tag:
                mOnRightTagClickListener.onClick();
                break;
        }
    }

    public interface TitleXMLClick {
        void onImage();

    }

    public void setListener(TitleXMLClick listener) {
        this.listener = listener;
    }

    public interface OnRightTagClickListener {
        void onClick();
    }


    public void setListener(TitleXMLClick listener, OnRightTagClickListener rightTagClickListener) {
        this.listener = listener;
        this.mOnRightTagClickListener = rightTagClickListener;
    }

    public TitleXML init() {
        ImageView image = ((Activity) context).findViewById(R.id.return_iv);
        tvRightTag = ((Activity) context).findViewById(R.id.tv_right_tag);
        if (aBoolean) {
            image.setOnClickListener(this);
        } else {
            image.setVisibility(View.INVISIBLE);
        }

        if (null != tvRightTag && TextUtils.isEmpty(strRightTag)) {
            tvRightTag.setVisibility(View.INVISIBLE);
        }
        TextView text = ((Activity) context).findViewById(R.id.tv_title);
        text.setText(title);
        setRightTag();
        return this;
    }

    public void setRightTag() {
        if (!TextUtils.isEmpty(strRightTag)) {
            tvRightTag.setText(strRightTag);
            tvRightTag.setOnClickListener(this);
        }
    }
}
