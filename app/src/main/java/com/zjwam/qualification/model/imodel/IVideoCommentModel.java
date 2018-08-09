package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoCommentBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IVideoCommentModel {
    void getComment(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<VideoCommentBean>> basicCallback);
    String site(Context context);
    String uid(Context context);
}
