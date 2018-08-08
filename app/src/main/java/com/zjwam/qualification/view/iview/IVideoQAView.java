package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.VideoQABean;

import java.util.List;

public interface IVideoQAView {
    void showMsg(String msg);
    void addData(List<VideoQABean.Answer> list,int count);
    void refreshComplete();
    void refresh();
    void dialogDismiss();
}
