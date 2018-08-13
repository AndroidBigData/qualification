package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VersionBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.IAboutModel;
import com.zjwam.qualification.utils.OkGoUtils;
import com.zjwam.qualification.utils.QlftPreference;

import java.io.File;
import java.util.Map;

public class AboutModel implements IAboutModel{
    @Override
    public void getVersion(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<VersionBean>> basicCallback) {
        JsonCallback<ResponseBean<VersionBean>> jsonCallback = new JsonCallback<ResponseBean<VersionBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<VersionBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<VersionBean>> response) {
                super.onError(response);
                basicCallback.onError(response);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                basicCallback.onFinish();
            }
        };
        OkGoUtils.postRequets(url,context,param,jsonCallback);
    }

    @Override
    public String uid(Context context) {
        return QlftPreference.getInstance(context).getUid();
    }

    @Override
    public String site(Context context) {
        return QlftPreference.getInstance(context).getSite();
    }

}
