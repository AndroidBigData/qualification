package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.VideoNoteBean;

import java.util.List;

public interface IVideoNoteView {
    void setNote(List<VideoNoteBean.Note> list);
    void refresh();
    void showMsg(String msg);
    void refreshComplete();
    void dialogDismiss();
}
