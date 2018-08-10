package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.CourseAskBean;

import java.util.List;

public interface IMineAskView {
    void showMsg(String msg);
    void addData(List<CourseAskBean.Answer> list, int count);
    void refreshComplete();
    void refresh();
}
