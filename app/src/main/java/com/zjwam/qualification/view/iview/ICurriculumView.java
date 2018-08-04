package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.ClassificationBean;
import com.zjwam.qualification.bean.CoursesListBean;

import java.util.List;

public interface ICurriculumView {
    void initData(List<CoursesListBean.classList> list);

    void clearRecyclerView();

    void showMsg(String msg);

    void refreshComplete();

    void setClassification(List<ClassificationBean> classification);

    void setNoData();
}
