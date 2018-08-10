package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.MineCommentBean;

import java.util.List;

public interface IMineCommentView {
    void showMsg(String msg);
    void addData(List<MineCommentBean.Comment> list, int count);
    void refreshComplete();
    void refresh();
}
