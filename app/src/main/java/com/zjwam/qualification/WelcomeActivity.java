package com.zjwam.qualification;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import com.jaeger.library.StatusBarUtil;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    private Timer timer;
    private TimerTask task;
    private ConstraintLayout wecomeimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init() {
        wecomeimg = findViewById(R.id.wecomeimg);

        ViewGroup.LayoutParams params = wecomeimg.getLayoutParams();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        params.width = dm.widthPixels;
        params.height = (params.width) * 1920 / 1080;
        wecomeimg.setLayoutParams(params);

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                goLogin();
            }
        };
        timer.schedule(task, 2000);
    }

    private void goLogin() {
        Intent intent = (new Intent(this, LoginActivity.class));
        startActivity(intent);
        finish();
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
    }
}
