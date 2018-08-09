package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.SearchTJBean;

import java.util.List;

public interface ISearchTJView {
    void showMsg(String msg);
    void setTJ(List<SearchTJBean> list);
    void refreshComplete();
}
