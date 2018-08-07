package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoCatalogBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.IVideoCatalogModel;
import com.zjwam.qualification.utils.OkGoUtils;
import com.zjwam.qualification.utils.QlftPreference;

import java.util.Map;

public class VideoCatalogModel implements IVideoCatalogModel {


    @Override
    public void getCatalog(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<VideoCatalogBean>> basicCallback) {
        JsonCallback<ResponseBean<VideoCatalogBean>> jsonCallback = new JsonCallback<ResponseBean<VideoCatalogBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<VideoCatalogBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<VideoCatalogBean>> response) {
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

    @Override
    public String uid(Context context) {
        return QlftPreference.getInstance(context).getUid();
    }

    @Override
    public void setVid(Context context,long vid) {
        QlftPreference.getInstance(context).setVideoId(String.valueOf(vid));
    }
}
