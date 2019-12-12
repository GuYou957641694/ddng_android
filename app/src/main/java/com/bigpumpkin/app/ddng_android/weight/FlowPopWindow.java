package com.bigpumpkin.app.ddng_android.weight;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.FlowPopListViewAdapter;
import com.bigpumpkin.app.ddng_android.bean.DetailsBean;

import java.util.List;

public class FlowPopWindow extends PopupWindow {

    private final Activity context;
    private final List<DetailsBean.DataBean.OptionsBean> dictList;
    private CustomHeightListView mListView;
    private TextView tvConfirm;
    private View nullView;
    private FlowPopListViewAdapter adapter;
    private OnConfirmClickListener onConfirmClickListener;

    public FlowPopWindow(Activity context, List<DetailsBean.DataBean.OptionsBean> dictList) {
        this.context = context;
        this.dictList = dictList;
        initPop();
    }


    private void initPop() {
        View popView = View.inflate(context, R.layout.flow_pop_listview, null);
        //设置view
        this.setContentView(popView);
        //设置宽高（也可设置为LinearLayout.LayoutParams.MATCH_PARENT或者LinearLayout.LayoutParams.MATCH_PARENT）
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow的焦点
        this.setFocusable(true);
        //设置窗口以外的地方点击可关闭pop
        this.setOutsideTouchable(true);
        //设置背景透明
        this.showAtLocation(popView, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.4f;
        context.getWindow().setAttributes(lp);
        this.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        //设置popupWindow消失时的监听
        this.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 1f;
                context.getWindow().setAttributes(lp);
            }
        });

        mListView = (CustomHeightListView) popView.findViewById(R.id.listview);
        tvConfirm = (TextView) popView.findViewById(R.id.tv_confirm);
        CheckBox ck_conventional = (CheckBox) popView.findViewById(R.id.ck_conventional);
        CheckBox ck_custom = (CheckBox) popView.findViewById(R.id.ck_custom);

        boolean type = dictList.get(0).isType();
        if (type == false) {
            ck_conventional.setChecked(true);
            ck_custom.setChecked(false);
        } else if (type == true) {
            ck_custom.setChecked(true);
            ck_conventional.setChecked(false);
        }

        ck_conventional.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //常规
                if (isChecked) {
                    ck_custom.setChecked(false);
                    ck_conventional.setChecked(true);
                    for (int x = 0; x < dictList.size(); x++) {
                        List<DetailsBean.DataBean.OptionsBean.CustomlistBean> childrenBeen = dictList.get(x).getCustomlist();
                        for (int y = 0; y < childrenBeen.size(); y++) {
                            if (childrenBeen.get(y).isSelected())
                                childrenBeen.get(y).setSelected(false);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    dictList.get(0).setType(false);
                }
            }
        });
        ck_custom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //定制
                if (isChecked) {
                    ck_conventional.setChecked(false);
                    ck_custom.setChecked(true);
                    dictList.get(0).setType(true);
                }
            }
        });

        adapter = new FlowPopListViewAdapter(context, dictList);
        mListView.setAdapter(adapter);

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ck_custom.isChecked()) {
                    //自定义监听第三步
                    onConfirmClickListener.onConfirmClick();
                    dismiss();
                } else {
                    for (int x = 0; x < dictList.size(); x++) {
                        List<DetailsBean.DataBean.OptionsBean.CustomlistBean> childrenBeen = dictList.get(x).getCustomlist();
                        for (int y = 0; y < childrenBeen.size(); y++) {
                            if (childrenBeen.get(y).isSelected())
                                childrenBeen.get(y).setSelected(false);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    if (listener != null) {
                        listener.OnListener();
                    }
                    dismiss();
                }
            }
        });
    }

    //自定义监听第二步
    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    //自定义监听第一步
    public interface OnConfirmClickListener {
        void onConfirmClick();
    }


    public interface onListener {
        void OnListener();
    }

    private onListener listener;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

}
