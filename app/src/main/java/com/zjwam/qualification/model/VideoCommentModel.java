package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoCommentBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.IVideoCommentModel;
import com.zjwam.qualification.utils.OkGoUtils;
import com.zjwam.qualification.utils.QlftPreference;

import java.util.Map;

public class VideoCommentModel implements IVideoCommentModel {
    @Override
    public void getComment(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<VideoCommentBean>> basicCallback) {
        JsonCallback<ResponseBean<VideoCommentBean>> jsonCallback = new JsonCallback<ResponseBean<VideoCommentBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<VideoCommentBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<VideoCommentBean>> response) {
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

}
