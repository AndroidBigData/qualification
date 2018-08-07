package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoNoteBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.IVideoNoteModel;
import com.zjwam.qualification.utils.OkGoUtils;
import com.zjwam.qualification.utils.QlftPreference;

import java.util.Map;

public class VideoNoteModel implements IVideoNoteModel {
    @Override
    public void getNote(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<VideoNoteBean>> basicCallback) {
        JsonCallback<ResponseBean<VideoNoteBean>> jsonCallback = new JsonCallback<ResponseBean<VideoNoteBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<VideoNoteBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<VideoNoteBean>> response) {
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
    public long getVid(Context context) {
        return Long.parseLong(QlftPreference.getInstance(context).getVideoId());
    }

    @Override
    public String getVtime(Context context) {
        return QlftPreference.getInstance(context).getViteoTime();
    }

    @Override
    public void getWriteNoteMsg(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<EmptyBean>> basicCallback) {
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
