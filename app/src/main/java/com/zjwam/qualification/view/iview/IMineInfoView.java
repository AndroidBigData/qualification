package com.zjwam.qualification.view.iview;

import com.zjwam.qualification.bean.MineInfoBean;

public interface IMineInfoView {
    void setTxSuccess();
    void showMsg(String msg);
    void setMineInfo(MineInfoBean mineInfoBean);
}
