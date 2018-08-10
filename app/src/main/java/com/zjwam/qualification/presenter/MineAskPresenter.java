package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.CourseAskBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.MineAskModel;
import com.zjwam.qualification.model.imodel.IMineAskModel;
import com.zjwam.qualification.presenter.ipresenter.IMineAskPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IMineAskView;

import java.util.HashMap;
import java.util.Map;

public class MineAskPresenter implements IMineAskPresenter {
    private Context context;
    private IMineAskView mineAskView;
    private IMineAskModel mineAskModel;
    private Map<String,String> param;

    public MineAskPresenter(Context context, IMineAskView mineAskView) {
        this.context = context;
        this.mineAskView = mineAskView;
        mineAskModel = new MineAskModel();
    }

    @Override
    public void getAsk(String page, final boolean isRefresh) {
        param = new HashMap<>();
        param.put("uid",mineAskModel.Uid(context));
        param.put("page",page);
        mineAskModel.getAsk(Url.url + "/api/user/answer" + Url.type + mineAskModel.Site(context), context, param, new BasicCallback<ResponseBean<CourseAskBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<CourseAskBean>> response) {
                if (isRefresh){
                    mineAskView.refresh();
                }
                CourseAskBean courseAskBean = response.body().data;
                mineAskView.addData(courseAskBean.getAnswer(),courseAskBean.getCount());
            }

            @Override
            public void onError(Response<ResponseBean<CourseAskBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineAskView.showMsg(error);
            }

            @Override
            public void onFinish() {
                mineAskView.refreshComplete();
            }
        });
    }
}
