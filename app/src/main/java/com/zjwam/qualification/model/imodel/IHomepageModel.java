package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.RankBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IHomepageModel {
    void getRank(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<RankBean>> basicCallback);
    String Site(Context context);
    String Uid(Context context);
}
