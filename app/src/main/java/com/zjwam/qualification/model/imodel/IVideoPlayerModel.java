package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoPlayerBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IVideoPlayerModel {
    void getFirstVideo(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<VideoPlayerBean>> basicCallback);
    String site(Context context);
    void setVid(Context context,long vid);
    void setVtime(Context context,int vtime);
}
