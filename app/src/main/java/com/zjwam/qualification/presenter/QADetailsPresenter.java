package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.QADetailsBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.QADetailsModel;
import com.zjwam.qualification.model.imodel.IQADetailsModel;
import com.zjwam.qualification.presenter.ipresenter.IQADetalisPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IQADetailsView;

import java.util.HashMap;
import java.util.Map;

public class QADetailsPresenter implements IQADetalisPresenter {
    private Context context;
    private IQADetailsModel detailsModel;
    private IQADetailsView detailsView;
    private Map<String,String> param;

    public QADetailsPresenter(Context context, IQADetailsView detailsView) {
        this.context = context;
        this.detailsView = detailsView;
        detailsModel = new QADetailsModel();
    }

    @Override
    public void getQADetails(String id, int page,boolean isRefresh) {
        param = new HashMap<>();
        param.put("uid",detailsModel.uid(context));
        param.put("id", String.valueOf(id));
        param.put("page", String.valueOf(page));
        detailsModel.getQADetails(Url.url + "/api/play/answerDetial" + Url.type + detailsModel.site(context), context, param, new BasicCallback<ResponseBean<QADetailsBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<QADetailsBean>> response) {
                QADetailsBean detailsBean = response.body().data;
                detailsView.setDetails(detailsBean.getNickname(),detailsBean.getPic(),detailsBean.getAddtime(),detailsBean.getContent(),detailsBean.getCount(),detailsBean.getId());
                detailsView.setAnswerList(detailsBean.getSub());
            }

            @Override
            public void onError(Response<ResponseBean<QADetailsBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                detailsView.showMsg(error);
            }

            @Override
            public void onFinish() {
                detailsView.refreshComplete();
            }
        });
    }

    @Override
    public void dianZan(long id, String type) {
        param = new HashMap<>();
        param.put("uid",detailsModel.uid(context));
        param.put("id", String.valueOf(id));
        param.put("type",type);
        detailsModel.dianZan(Url.url + "/api/play/zan" + Url.type + detailsModel.site(context), context, param, new BasicCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                detailsView.dianZan();
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                detailsView.showMsg(error);
            }

            @Override
            public void onFinish() {
                detailsView.setEnabled();
            }
        });
    }
}
