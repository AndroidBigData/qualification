package com.zjwam.qualification.presenter;

import com.zjwam.qualification.view.BaseView;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomePresenter implements BasePresenter {
    private BaseView baseView;
    private Timer timer;
    private TimerTask task;
    public WelcomePresenter(BaseView baseView) {
        this.baseView = baseView;
    }
    @Override
    public void manageData() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                baseView.initData();
            }
        };
        timer.schedule(task, 2000);
    }
}
