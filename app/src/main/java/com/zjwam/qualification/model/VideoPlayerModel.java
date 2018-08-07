package com.zjwam.qualification.model;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoPlayerBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.IVideoPlayerModel;
import com.zjwam.qualification.utils.OkGoUtils;
import com.zjwam.qualification.utils.QlftPreference;

import java.util.Map;

public class VideoPlayerModel implements IVideoPlayerModel {
    @Override
    public void getFirstVideo(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<VideoPlayerBean>> basicCallback) {
        JsonCallback<ResponseBean<VideoPlayerBean>> jsonCallback = new JsonCallback<ResponseBean<VideoPlayerBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<VideoPlayerBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<VideoPlayerBean>> response) {
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
    public void setVid(Context context,long vid) {
        QlftPreference.getInstance(context).setVideoId(String.valueOf(vid));
    }

    @Override
    public void setVtime(Context context, int vtime) {
        QlftPreference.getInstance(context).setViteoTime(String.valueOf(vtime));
    }
}
