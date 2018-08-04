package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.MineBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.IMineModel;
import com.zjwam.qualification.utils.OkGoUtils;
import com.zjwam.qualification.utils.QlftPreference;

import java.util.Map;

public class MineModel implements IMineModel {
    @Override
    public void getMine(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<MineBean>> basicCallback) {
        JsonCallback<ResponseBean<MineBean>> jsonCallback = new JsonCallback<ResponseBean<MineBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<MineBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<MineBean>> response) {
                super.onError(response);
                basicCallback.onError(response);
            }

            @Override
            public void onFinish() {
                super.onFinish();
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
