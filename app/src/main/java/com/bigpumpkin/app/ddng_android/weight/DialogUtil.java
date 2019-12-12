package com.bigpumpkin.app.ddng_android.weight;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;

public class DialogUtil {

    private static AlertDialog dialog;

    /**
     * @param activity                    Context
     * @param iconRes                     提示图标
     * @param title                       提示标题
     * @param msg                         提示内容
     * @param positiveText                确认
     * @param negativeText                取消
     * @param cancelableTouchOut          点击外部是否隐藏提示框
     * @param alertDialogBtnClickListener 点击监听
     */
    public static void showAlertDialog(Activity activity, String title, String msg,
                                       String positiveText, String negativeText, boolean
                                               cancelableTouchOut, final AlertDialogBtnClickListener
                                               alertDialogBtnClickListener) {
        View view = LayoutInflater.from(activity).inflate(R.layout.custom_dialog_layout, null);
        TextView mTitle = view.findViewById(R.id.title);
        TextView mMessage = view.findViewById(R.id.message);
        Button positiveButton = view.findViewById(R.id.positiveButton);
        Button negativeButton = view.findViewById(R.id.negativeButton);
        mTitle.setText(title);
        mMessage.setText(msg);
        positiveButton.setText(positiveText);
        negativeButton.setText(negativeText);
        positiveButton.setOnClickListener(v -> {
            alertDialogBtnClickListener.clickPositive();
            dialog.dismiss();
        });
        negativeButton.setOnClickListener(v -> {
            alertDialogBtnClickListener.clickNegative();
            dialog.dismiss();
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view);

        builder.setCancelable(true);   //返回键dismiss
        //创建对话框
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//去掉圆角背景背后的棱角
        dialog.setCanceledOnTouchOutside(cancelableTouchOut);   //失去焦点dismiss
        dialog.show();
    }

    public interface AlertDialogBtnClickListener {
        void clickPositive();

        void clickNegative();
    }
}
