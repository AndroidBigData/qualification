package com.zjwam.qualification.presenter.ipresenter;

public interface IMineInfoPresenter {
    void upImg(String path);
    void getInfo();
    void upInfo(String nickname,String sign,String sex,String email);
    void setRefresh(boolean isRefresh);
}
