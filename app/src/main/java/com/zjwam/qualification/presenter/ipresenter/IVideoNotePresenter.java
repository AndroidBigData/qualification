package com.zjwam.qualification.presenter.ipresenter;

public interface IVideoNotePresenter {
    void getNote(long id,int page,boolean isRefresh);
    int getCount();
    long getVid();
    String getVtime();
    void writeNote(long id,long vid,String vtime,String note);
}
