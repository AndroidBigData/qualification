package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IAnswerModel {
    void upAnswer(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<EmptyBean>> basicCallback);
    String uid(Context context);
    String site(Context context);
}
