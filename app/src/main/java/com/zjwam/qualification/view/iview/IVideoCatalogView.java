package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.VideoCatalogBean;

import java.util.List;

public interface IVideoCatalogView {
    void setAdapter(int buy);
    void setCatalog(List<VideoCatalogBean.Catalog> catalog);
    void showMsg(String msg);
    void catalogPlay();
}
