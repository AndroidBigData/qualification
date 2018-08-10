package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.CourseAnswerBean;

import java.util.List;

public interface IMineAnswerView {
    void showMsg(String msg);
    void addData(List<CourseAnswerBean.Answer> list, int count);
    void refreshComplete();
    void refresh();
}
