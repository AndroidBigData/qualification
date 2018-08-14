package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.MineInfoBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.io.File;
import java.util.Map;

public interface IMineInfoModel {
    void upImg(String url, Object context, Map<String,String> param, String pic,File file, BasicCallback<ResponseBean<EmptyBean>> basicCallback);
    String Site(Context context);
    String Uid(Context context);
    void getInfo(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<MineInfoBean>> basicCallback);
    void upInfo(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<EmptyBean>> basicCallback);
    void setRefresh(Context context,boolean isRefresh);
}
