package com.bigpumpkin.app.ddng_android.weight;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;

public class CustomPopWindow extends PopupWindow {

    private View mMenuView;
    private ListView listView;
    private TextView tv_ok;
    private TextView tv_pop_title;
    private LinearLayout liner_ok;
    private TextView redpacket_tip;
    private RelativeLayout liner_hasuse_redpacket;

    public CustomPopWindow(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //pop_bottom为自定义布局
        mMenuView = inflater.inflate(R.layout.pop_bottom, null);
        tv_ok = (TextView) mMenuView.findViewById(R.id.tv_ok);

      /*  listView = (ListView) mMenuView.findViewById(R.id.listview);
        tv_ok = (TextView) mMenuView.findViewById(R.id.tv_ok);
        tv_pop_title = (TextView) mMenuView.findViewById(R.id.tv_pop_title);
        liner_ok = (LinearLayout) mMenuView.findViewById(R.id.liner_ok);
        redpacket_tip = (TextView) mMenuView.findViewById(R.id.redpacket_tip);
        liner_hasuse_redpacket = (RelativeLayout) mMenuView.findViewById(R.id.liner_hasuse_redpacket);*/
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popupAnimation);

        //实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        //this.setBackgroundDrawable(dw);
        this.getBackground().setAlpha(0);
        final Activity activity = (Activity) context;
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = 0.7f;
        activity.getWindow().setAttributes(params);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    dismiss();
                    WindowManager.LayoutParams params = activity.getWindow().getAttributes();
                    params.alpha = 1f;
                    activity.getWindow().setAttributes(params);
                }
                return true;
            }
        });
        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = activity.getWindow().getAttributes();
                params.alpha = 1f;
                activity.getWindow().setAttributes(params);
            }
        });

    }

    public View getView() {
        return mMenuView;
    }

    public ListView getListView() {
        return listView;
    }


    public TextView getTv_pop_title() {
        return tv_pop_title;
    }

    public LinearLayout getLiner_ok() {
        return liner_ok;
    }

    public TextView getRedpacket_tip() {
        return redpacket_tip;
    }

    public RelativeLayout getLiner_hasuse_redpacket() {
        return liner_hasuse_redpacket;
    }
}