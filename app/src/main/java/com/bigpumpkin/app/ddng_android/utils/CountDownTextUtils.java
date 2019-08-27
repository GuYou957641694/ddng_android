package com.bigpumpkin.app.ddng_android.utils;

import android.os.CountDownTimer;
//倒计时
public class CountDownTextUtils extends CountDownTimer {

    private CountDownTextUtils.CountDownListener mCountDownListener;

    public CountDownTextUtils(long millisInFuture, long countDownInterval, CountDownListener countDownListener) {
        super(millisInFuture, countDownInterval);
        mCountDownListener = countDownListener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mCountDownListener.onCountDown(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        mCountDownListener.onCountDownFinish();
    }

    public interface CountDownListener {

        void onCountDown(long currentMill);

        void onCountDownFinish();
    }
}
