package com.zjwam.qualification.model.imodel;

import com.zjwam.qualification.bean.PersonalMineCommentBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;

import java.util.Map;

public interface ICurriculumModel {
    void getData(String url, Object context, Map<String,String> param, BasicCallback<ResponseBean<PersonalMineCommentBean>> basicCallback);
}
