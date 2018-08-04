package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.RankBean;

public interface IHomePageView {
    void setMsg(RankBean rankBean);
    void errorMsg(String msg);
    void httpOver();
}
