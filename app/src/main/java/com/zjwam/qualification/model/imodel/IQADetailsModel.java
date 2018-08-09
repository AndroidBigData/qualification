package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.QADetailsBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IQADetailsModel {
    void getQADetails(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<QADetailsBean>> basicCallback);
    String uid(Context context);
    String site(Context context);
    void dianZan(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<EmptyBean>> basicCallback);
}
