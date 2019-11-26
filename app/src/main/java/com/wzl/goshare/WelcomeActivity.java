package com.wzl.goshare;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wzl.goshare.base.BaseActivity;
import com.wzl.goshare.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Create on 2019/8/27 23:46  by  wzl
 * 描述：
 * 最近修改：2019/8/27 23:46 modify by wzl
 */
public class WelcomeActivity extends BaseActivity {
    @Nullable
    @BindView(R.id.jump_btn)
    Button mjumpbtn;

    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        jumpToMainActivity();
        ScreenUtils.hideBottomUIMenu(WelcomeActivity.this);
        mjumpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMainActivity();
            }
        });
    }//create方法结束

    private void goMainActivity() {
      Intent intent=new Intent(this,MainActivity.class);
      startActivity(intent);
      finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            ScreenUtils.hideBottomUIMenu(WelcomeActivity.this);
        }
    }

    public void jumpToMainActivity() {
        countDownTimer =new MyCountDownTimer(5000+200,1000);
        countDownTimer.start();
        mjumpbtn.setVisibility(View.VISIBLE);
    }

    /**
     * 倒计时计时器
     */
    private class MyCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mjumpbtn.setText("跳过(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            mjumpbtn.setText("跳过(" + 0 + "s)");
            goMainActivity();
        }


    }

}