package com.zjwam.qualification.presenter.ipresenter;

public interface ICurriculumPresenter {
    void getData(String cid,String page,boolean isRefresh);
    int maxItems();
    void getClassification();
}
