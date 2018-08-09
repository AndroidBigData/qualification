package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.QADetailsBean;

import java.util.List;

public interface IQADetailsView {
    void showMsg(String msg);
    void setDetails(String nickname,String pic,String addtime,String content,int count,long id);
    void setAnswerList(List<QADetailsBean.Sub> list);
    void refreshComplete();
    void dianZan();
    void setEnabled();
}
