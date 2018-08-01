package com.zjwam.qualification.presenter.ipresenter;

public interface ICurriculumPresenter {
    void getData(String uid,String page,boolean isRefresh);
    int maxItems();
}
