package com.zjwam.qualification.callback;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;

public interface BasicCallback<T> {
    void onSuccess(Response<T> response);
    void onError(Response<T> response);
    void onFinish();
}
