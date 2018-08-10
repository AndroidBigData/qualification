package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.MineNoteBean;

import java.util.List;

public interface IMineNoteView{
    void showMsg(String msg);
    void addData(List<MineNoteBean.Note> list, int count);
    void refreshComplete();
    void refresh();
    void deleteSuccess();
    void deleteFinish();
}
