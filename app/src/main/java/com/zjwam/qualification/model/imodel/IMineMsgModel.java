package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.MineMsgBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IMineMsgModel {
    void getMsg(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<MineMsgBean>> basicCallback);
    String Site(Context context);
    String Uid(Context context);
    void setRead(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<EmptyBean>> basicCallback);
}
