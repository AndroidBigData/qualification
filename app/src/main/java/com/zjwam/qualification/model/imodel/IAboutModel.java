package com.zjwam.qualification.model.imodel;

import android.content.Context;


import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VersionBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IAboutModel {
    void getVersion(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<VersionBean>> basicCallback);
    String uid(Context context);
    String site(Context context);
}
