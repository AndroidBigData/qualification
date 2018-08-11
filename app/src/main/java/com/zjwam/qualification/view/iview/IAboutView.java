package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.VersionBean;

public interface IAboutView {
    void getVersion (VersionBean versionBean);
    void error();
    void showMsg(String msg);
}
