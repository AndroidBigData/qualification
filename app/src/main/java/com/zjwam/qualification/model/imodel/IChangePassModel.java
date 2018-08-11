package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import java.util.Map;

public interface IChangePassModel {
    void upNew(String url, Object context, Map<String,String> param,BasicCallback<ResponseBean<EmptyBean>> basicCallback);
    String Site(Context context);
    String Uid(Context context);
}
