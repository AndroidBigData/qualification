package com.zjwam.qualification.presenter.ipresenter;

public interface IQADetalisPresenter {
    void getQADetails(String id,int page,boolean isRefresh);
    void dianZan(long id,String type);
}
