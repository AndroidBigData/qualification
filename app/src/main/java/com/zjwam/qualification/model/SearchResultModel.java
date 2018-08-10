package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.CoursesListBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.ISearchResultModel;
import com.zjwam.qualification.utils.OkGoUtils;
import com.zjwam.qualification.utils.QlftPreference;

import java.util.Map;

public class SearchResultModel implements ISearchResultModel {
    @Override
    public void getSearch(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<CoursesListBean>> basicCallback) {
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
    public String uid(Context context) {
        return QlftPreference.getInstance(context).getUid();
    }

    @Override
    public String site(Context context) {
        return QlftPreference.getInstance(context).getSite();
    }
}
