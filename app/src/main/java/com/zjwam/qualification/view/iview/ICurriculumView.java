package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.PersonalMineCommentBean;

import java.util.List;

public interface ICurriculumView {
    void initData(List<PersonalMineCommentBean.getCommentItems> list);

    void clearRecyclerView();

    void showMsg(String msg);

    void refreshComplete();
}
