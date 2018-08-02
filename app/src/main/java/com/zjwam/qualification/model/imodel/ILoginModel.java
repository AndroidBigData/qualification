package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.LoginBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface ILoginModel {
    void OkGoHttp(String url, Object context, Map<String,String> param,BasicCallback<ResponseBean<LoginBean>> basicCallback);
    void saveLoginMsg(Context context,String uid, String site);
}
