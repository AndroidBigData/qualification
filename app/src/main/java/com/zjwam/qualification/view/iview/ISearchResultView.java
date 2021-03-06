package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.CoursesListBean;

import java.util.List;

public interface ISearchResultView {
    void initData(List<CoursesListBean.classList> list,int count);

    void clearRecyclerView();

    void showMsg(String msg);

    void refreshComplete();
}
