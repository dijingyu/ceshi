package com.example.dijingyu.app_demo.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dijingyu.app_demo.R;

import java.util.Timer;
import java.util.TimerTask;

public class PhoneActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvBack;
    private TextView mTvTime;

    private int time = 60;

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (time >= 0) {
                        if (time == 0) {
                            mTvTime.setText("重新发送验证码");
                        }else {
                            mTvTime.setText("重新发送 " + time);
                        }
                        time--;
                    } else {
                        mTimer.cancel();
                        mTvTime.setEnabled(true);
                    }
                    break;
            }
        }
    };
    private Timer mTimer;
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        initView();
    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        mIvBack = findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTime = findViewById(R.id.tv_time);
        mTv = findViewById(R.id.tv_tijiao);
        mTvTime.setOnClickListener(this);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(1);
            }
        };
        mTimer = new Timer();
        mTimer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void onClick(View v) {

    }
}
