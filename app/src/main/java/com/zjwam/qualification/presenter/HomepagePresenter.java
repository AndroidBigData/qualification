package com.zjwam.qualification.presenter;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.RankBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.HomepageModel;
import com.zjwam.qualification.model.imodel.IHomepageModel;
import com.zjwam.qualification.presenter.ipresenter.IHomepagePresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IHomePageView;

import java.util.HashMap;
import java.util.Map;

public class HomepagePresenter implements IHomepagePresenter {
    private Context context;
    private IHomepageModel homepageModel;
    private IHomePageView homePageView;
    private Map<String, String> param;

    public HomepagePresenter(Context context, IHomePageView homePageView) {
        this.context = context;
        this.homePageView = homePageView;
        homepageModel = new HomepageModel();
    }

    @Override
    public void getRank() {
        param = new HashMap<>();
        param.put("uid", homepageModel.Uid(context));
        homepageModel.getRank(Url.url + "/api/index/index" + Url.type + homepageModel.Site(context), context, param, new BasicCallback<ResponseBean<RankBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<RankBean>> response) {
                RankBean rankBean = response.body().data;
                homePageView.setMsg(rankBean);
            }

            @Override
            public void onError(Response<ResponseBean<RankBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                homePageView.errorMsg(error);
            }

            @Override
            public void onFinish() {
                homePageView.httpOver();
            }
        });
    }

    @Override
    public String getUid() {
        return homepageModel.Uid(context);
    }

    @Override
    public String getSite() {
        return homepageModel.Site(context);
    }
}
