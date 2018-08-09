package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.SearchTJBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.List;
import java.util.Map;

public interface ISearchTJModel {
    void getTJ(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<List<SearchTJBean>>> basicCallback);
    String site(Context context);
}
