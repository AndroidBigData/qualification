package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ClassSearchBean;
import com.zjwam.qualification.bean.ClassificationBean;
import com.zjwam.qualification.bean.CoursesListBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.ICurriculumModel;
import com.zjwam.qualification.utils.OkGoUtils;
import com.zjwam.qualification.utils.QlftPreference;

import java.util.List;
import java.util.Map;

public class CurriculumModel implements ICurriculumModel {
    @Override
    public void getData(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<CoursesListBean>> basicCallback) {
        JsonCallback<ResponseBean<CoursesListBean>> jsonCallback = new JsonCallback<ResponseBean<CoursesListBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<CoursesListBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<CoursesListBean>> response) {
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
    public void getClassification(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<List<ClassificationBean>>> basicCallback) {
        JsonCallback<ResponseBean<List<ClassificationBean>>> jsonCallback = new JsonCallback<ResponseBean<List<ClassificationBean>>>() {
            @Override
            public void onSuccess(Response<ResponseBean<List<ClassificationBean>>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<List<ClassificationBean>>> response) {
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

    @Override
    public void getLinkageData(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<List<ClassSearchBean>>> basicCallback) {
        JsonCallback<ResponseBean<List<ClassSearchBean>>> jsonCallback = new JsonCallback<ResponseBean<List<ClassSearchBean>>>() {
            @Override
            public void onSuccess(Response<ResponseBean<List<ClassSearchBean>>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<List<ClassSearchBean>>> response) {
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
    public void getLinkageClass(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<CoursesListBean>> basicCallback) {
        JsonCallback<ResponseBean<CoursesListBean>> jsonCallback = new JsonCallback<ResponseBean<CoursesListBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<CoursesListBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<CoursesListBean>> response) {
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
