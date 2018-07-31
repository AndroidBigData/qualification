package com.zjwam.qualification.model;

import android.content.Context;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.callback.CallBack;

public interface ILoginModel {
    void OkGoHttp(String name,String pass,Context context, CallBack<EmptyBean> callBack);
}
