package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoCatalogBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface IVideoCatalogModel {
    void getCatalog(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<VideoCatalogBean>> basicCallback);
    String site(Context context);
    String uid(Context context);
    void setVid(Context context,long vid);
}
