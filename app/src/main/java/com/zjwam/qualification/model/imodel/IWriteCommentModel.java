package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IWriteCommentModel {
    void upComment(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<EmptyBean>> basicCallback);
    String site(Context context);
    String uid(Context context);
    String vid(Context context);
}
