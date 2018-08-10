package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.MineCommentBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IMineCommentModel {
    void getComment(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<MineCommentBean>> basicCallback);
    String Site(Context context);
    String Uid(Context context);
}
