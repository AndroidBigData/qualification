package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.QADetailsBean;

import java.util.List;

public interface IMineAskDetailsView {
    void showMsg(String msg);
    void setDetails(String nickname,String pic,String addtime,String name,String vname,String content,int count,String id);
    void setAnswerList(List<QADetailsBean.Sub> list);
    void refreshComplete();
    void dianZan();
    void setEnabled();
    void refresh();
}
