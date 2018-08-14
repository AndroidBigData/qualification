package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.QADetailsBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IMineAskDetailsModel {
    void getAskDetails(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<QADetailsBean>> basicCallback);
    String Site(Context context);
    String Uid(Context context);
    void setZan(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<EmptyBean>> basicCallback);
}
