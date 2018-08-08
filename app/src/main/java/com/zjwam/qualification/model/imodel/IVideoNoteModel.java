package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoNoteBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IVideoNoteModel {
    void getNote(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<VideoNoteBean>> basicCallback);
    String site(Context context);
    String uid(Context context);
    long getVid(Context context);
    String getVtime(Context context);
    void getWriteNoteMsg(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<EmptyBean>> basicCallback);
    void dianZan(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<EmptyBean>> basicCallback);
}
