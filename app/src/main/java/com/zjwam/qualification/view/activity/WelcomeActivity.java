package com.zjwam.qualification.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import com.jaeger.library.StatusBarUtil;
import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.presenter.WelcomePresenter;
import com.zjwam.qualification.view.iview.IWecomeView;


public class WelcomeActivity extends BaseActivity implements IWecomeView {

    private WelcomePresenter welcomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomePresenter = new WelcomePresenter(this,this);
        welcomePresenter.manageData();
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
    }

    @Override
    public void initData(boolean isFlag) {
        if (isFlag){
            Intent intent = new Intent(this, MainActivity.class);
            if(getIntent().getBundleExtra("jpush") != null){
                intent.putExtra("jpush", getIntent().getBundleExtra("jpush"));
            }
            startActivity(intent);
            finish();
        }else {
            Intent intent = (new Intent(this, LoginActivity.class));
            startActivity(intent);
            finish();
        }

    }
}
