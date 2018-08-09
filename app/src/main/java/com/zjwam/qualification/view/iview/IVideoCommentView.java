package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.VideoCommentBean;

import java.util.List;

public interface IVideoCommentView {
    void showMsg(String msg);
    void getComment(List<VideoCommentBean.Comment> list,int maxItem);
    void refreshComplete();
    void refresh();
}
