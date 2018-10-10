package com.zjwam.qualification.model.imodel;

import android.content.Context;

import com.zjwam.qualification.bean.ClassSearchBean;
import com.zjwam.qualification.bean.ClassificationBean;
import com.zjwam.qualification.bean.CoursesListBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.List;
import java.util.Map;

public interface ICurriculumModel {
    void getData(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<CoursesListBean>> basicCallback);
    void getClassification(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<List<ClassificationBean>>> basicCallback);
    String Site(Context context);
    String Uid(Context context);
    void getLinkageData(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<List<ClassSearchBean>>> basicCallback);
    void getLinkageClass(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<CoursesListBean>> basicCallback);
}
