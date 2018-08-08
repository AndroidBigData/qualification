package com.zjwam.qualification.presenter.ipresenter;

public interface IVideoQAPresenter {
    void getQA(String id,int page,boolean isRefresh);
    String getVid();
    String getVtime();
    void askQuestion(long id,long vid,String vtime,String question);
}
