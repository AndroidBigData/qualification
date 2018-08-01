package com.zjwam.qualification.model.imodel;

import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface ILoginModel {
    void OkGoHttp(String url, Object context, Map<String,String> param,BasicCallback<ResponseBean<EmptyBean>> basicCallback);
}
