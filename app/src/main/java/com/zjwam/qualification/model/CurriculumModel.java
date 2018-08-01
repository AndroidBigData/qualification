package com.zjwam.qualification.model;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.PersonalMineCommentBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.callback.JsonCallback;
import com.zjwam.qualification.model.imodel.ICurriculumModel;
import com.zjwam.qualification.utils.OkGoUtils;

import java.util.Map;

public class CurriculumModel implements ICurriculumModel {
    @Override
    public void getData(String url, Object context, Map<String, String> param, final BasicCallback<ResponseBean<PersonalMineCommentBean>> basicCallback) {
        JsonCallback<ResponseBean<PersonalMineCommentBean>> jsonCallback = new JsonCallback<ResponseBean<PersonalMineCommentBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<PersonalMineCommentBean>> response) {
                basicCallback.onSuccess(response);
            }

            @Override
            public void onError(Response<ResponseBean<PersonalMineCommentBean>> response) {
                super.onError(response);
                basicCallback.onError(response);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                basicCallback.onFinish();
            }
        };
        OkGoUtils.postRequets(url,context,param,jsonCallback);
    }
}
