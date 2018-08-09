package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.QADetailsBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.IQADetailsModel;
import com.zjwam.qualification.utils.OkGoUtils;
import com.zjwam.qualification.utils.QlftPreference;

import java.util.Map;

public class QADetailsModel implements IQADetailsModel {
    @Override
    public void getQADetails(String url, Object context, Map<String,String> param, final BasicCallback<ResponseBean<QADetailsBean>> basicCallback) {
        JsonCallback<ResponseBean<QADetailsBean>> jsonCallback = new JsonCallback<ResponseBean<QADetailsBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<QADetailsBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<QADetailsBean>> response) {
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

    @Override
    public void dianZan(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<EmptyBean>> basicCallback) {
        JsonCallback<ResponseBean<EmptyBean>> jsonCallback = new JsonCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
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
}
