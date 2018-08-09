package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.SearchTJBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.ISearchTJModel;
import com.zjwam.qualification.utils.OkGoUtils;
import com.zjwam.qualification.utils.QlftPreference;

import java.util.List;
import java.util.Map;

public class SearchTJModel implements ISearchTJModel {
    @Override
    public void getTJ(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<List<SearchTJBean>>> basicCallback) {
        JsonCallback<ResponseBean<List<SearchTJBean>>> jsonCallback = new JsonCallback<ResponseBean<List<SearchTJBean>>>() {
            @Override
            public void onSuccess(Response<ResponseBean<List<SearchTJBean>>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<List<SearchTJBean>>> response) {
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
    public String site(Context context) {
        return QlftPreference.getInstance(context).getSite();
    }
}
