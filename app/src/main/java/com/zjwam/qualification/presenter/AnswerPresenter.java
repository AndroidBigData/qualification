package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.AnswerModel;
import com.zjwam.qualification.model.imodel.IAnswerModel;
import com.zjwam.qualification.presenter.ipresenter.IAnswerPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IAnswerView;

import java.util.HashMap;
import java.util.Map;

public class AnswerPresenter implements IAnswerPresenter{
    private Context context;
    private IAnswerModel answerModel;
    private IAnswerView answerView;
    private Map<String,String> param;

    public AnswerPresenter(Context context, IAnswerView answerView) {
        this.context = context;
        this.answerView = answerView;
        answerModel = new AnswerModel();
    }

    @Override
    public void upAnswer(String id, String content) {
        param = new HashMap<>();
        param.put("id",id);
        param.put("uid",answerModel.uid(context));
        param.put("content",content);
        answerModel.upAnswer(Url.url + "/api/play/answerTo" + Url.type + answerModel.site(context), context, param, new BasicCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                answerView.success();
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                answerView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
