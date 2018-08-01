package com.zjwam.qualification.presenter;

import com.zjwam.qualification.presenter.ipresenter.IWecoomePresenter;
import com.zjwam.qualification.view.iview.IWecomeView;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomePresenter implements IWecoomePresenter {
    private IWecomeView IWecomeView;
    private Timer timer;
    private TimerTask task;
    public WelcomePresenter(IWecomeView IWecomeView) {
        this.IWecomeView = IWecomeView;
    }
    @Override
    public void manageData() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                IWecomeView.initData();
            }
        };
        timer.schedule(task, 2000);
    }
}
