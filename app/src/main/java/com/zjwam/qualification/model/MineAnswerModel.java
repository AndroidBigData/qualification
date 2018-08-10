package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.CourseAnswerBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.IMineAnswerModel;
import com.zjwam.qualification.utils.OkGoUtils;
import com.zjwam.qualification.utils.QlftPreference;

import java.util.Map;

public class MineAnswerModel implements IMineAnswerModel {
    @Override
    public void getAnswer(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<CourseAnswerBean>> basicCallback) {
        JsonCallback<ResponseBean<CourseAnswerBean>> jsonCallback = new JsonCallback<ResponseBean<CourseAnswerBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<CourseAnswerBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<CourseAnswerBean>> response) {
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
    public String Site(Context context) {
        return QlftPreference.getInstance(context).getSite();
    }

    @Override
    public String Uid(Context context) {
        return QlftPreference.getInstance(context).getUid();
    }
}
