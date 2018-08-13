package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.MineMsgBean;

import java.util.List;

public interface IMineMSgView {
    void setMsg(List<MineMsgBean.Notice> list,int count);
    void refresh();
    void showMsg(String msg);
    void refreshComplete();
    void read();
}
