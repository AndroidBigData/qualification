package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.CoursesListBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface ISearchResultModel {
    void getSearch(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<CoursesListBean>> basicCallback);
    String uid(Context context);
    String site(Context context);
}
