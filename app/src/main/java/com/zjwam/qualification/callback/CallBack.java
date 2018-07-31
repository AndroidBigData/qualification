package com.zjwam.qualification.callback;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;

public interface CallBack<T> {
    void onSuccess(Response<ResponseBean<T>> response);
    void onError(Response<ResponseBean<T>> response);
    void onFinish();
}
