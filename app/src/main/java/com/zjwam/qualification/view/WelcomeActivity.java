package com.zjwam.qualification.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import com.jaeger.library.StatusBarUtil;
import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.login.LoginActivity;
import com.zjwam.qualification.presenter.WelcomePresenter;


public class WelcomeActivity extends BaseActivity implements BaseView {

    private ConstraintLayout wecomeimg;
    private WelcomePresenter welcomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
        welcomePresenter = new WelcomePresenter(this);
        welcomePresenter.manageData();
    }

    private void init() {
        wecomeimg = findViewById(R.id.wecomeimg);
        ViewGroup.LayoutParams params = wecomeimg.getLayoutParams();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        params.width = dm.widthPixels;
        params.height = (params.width) * 1920 / 1080;
        wecomeimg.setLayoutParams(params);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
    }

    @Override
    public void initData() {
        Intent intent = (new Intent(this, LoginActivity.class));
        startActivity(intent);
        finish();
    }
}
