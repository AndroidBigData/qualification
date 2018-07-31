package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.CallBack;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.utils.OkGoUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginModel implements ILoginModel {
    private Map<String, String> param;

    @Override
    public void OkGoHttp(String name,String pass,Context context, final CallBack<EmptyBean> callBack) {
        param = new HashMap<>();
        param.put("name", name);
        param.put("pass", pass);
        OkGoUtils.postRequets("http://zkw.org.cn/api/login/login", context, param, new JsonCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                callBack.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                super.onError(response);
                callBack.onError(response);
            }
        });
    }

}
