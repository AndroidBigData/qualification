package com.zjwam.qualification.presenter;

import android.content.Context;

import com.zjwam.qualification.model.imodel.IWecomeModel;
import com.zjwam.qualification.model.WecomeModel;
import com.zjwam.qualification.presenter.ipresenter.IWecoomePresenter;
import com.zjwam.qualification.view.iview.IWecomeView;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomePresenter implements IWecoomePresenter {
    private IWecomeView IWecomeView;
    private Timer timer;
    private TimerTask task;
    private boolean isFlag;
    private IWecomeModel wecomeModel;
    private Context context;
    public WelcomePresenter(Context context,IWecomeView IWecomeView) {
        this.context = context;
        this.IWecomeView = IWecomeView;
        wecomeModel = new WecomeModel();
    }
    @Override
    public void manageData() {
        isFlag = wecomeModel.isFlag(context);
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                IWecomeView.initData(isFlag);
            }
        };
        timer.schedule(task, 3000);
    }
}
