package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.CourseAnswerBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.MineAnswerModel;
import com.zjwam.qualification.model.imodel.IMineAnswerModel;
import com.zjwam.qualification.presenter.ipresenter.IMineAnswerPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IMineAnswerView;

import java.util.HashMap;
import java.util.Map;

public class MineAnswerPresenter implements IMineAnswerPresenter {
    private Context context;
    private IMineAnswerView mineAnswerView;
    private IMineAnswerModel mineAnswerModel;
    private Map<String,String> param;

    public MineAnswerPresenter(Context context, IMineAnswerView mineAnswerView) {
        this.context = context;
        this.mineAnswerView = mineAnswerView;
        mineAnswerModel = new MineAnswerModel();
    }

    @Override
    public void getAnswer(String page, final boolean isRegresh) {
        param = new HashMap<>();
        param.put("uid",mineAnswerModel.Uid(context));
        param.put("page",page);
        mineAnswerModel.getAnswer(Url.url + "/api/user/myAnswer" + Url.type + mineAnswerModel.Site(context), context, param, new BasicCallback<ResponseBean<CourseAnswerBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<CourseAnswerBean>> response) {
                if (isRegresh){
                    mineAnswerView.refresh();
                }
                CourseAnswerBean courseAnswerBean = response.body().data;
                mineAnswerView.addData(courseAnswerBean.getAnswer(),courseAnswerBean.getCount());
            }

            @Override
            public void onError(Response<ResponseBean<CourseAnswerBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineAnswerView.showMsg(error);
            }

            @Override
            public void onFinish() {
                mineAnswerView.refreshComplete();
            }
        });
    }
}
