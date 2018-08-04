package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.MineBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IMineModel {
    void getMine(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<MineBean>> basicCallback);
    String Site(Context context);
    String Uid(Context context);
}
