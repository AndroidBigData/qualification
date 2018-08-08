package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoQABean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IVideoQAModel {
    void getQA(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<VideoQABean>> basicCallback);
    String site(Context context);
    String uid(Context context);
    String vid(Context context);
    String vtime(Context context);
    void AskQuestion(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<EmptyBean>> basicCallback);
}
