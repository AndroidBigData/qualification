package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.QADetailsBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.MineAskDetasilsModel;
import com.zjwam.qualification.model.imodel.IMineAskDetailsModel;
import com.zjwam.qualification.presenter.ipresenter.IMineAskDetailsPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IMineAskDetailsView;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MineAskDetailsPresenter implements IMineAskDetailsPresenter {
    private Context context;
    private IMineAskDetailsView mineAskDetailsView;
    private IMineAskDetailsModel mineAskDetailsModel;
    private Map<String,String> param;

    public MineAskDetailsPresenter(Context context, IMineAskDetailsView mineAskDetailsView) {
        this.context = context;
        this.mineAskDetailsView = mineAskDetailsView;
        mineAskDetailsModel = new MineAskDetasilsModel();
    }

    @Override
    public void getAskDetails(String id, String page, final boolean isRefresh) {
        param = new HashMap<>();
        param.put("uid",mineAskDetailsModel.Uid(context));
        param.put("id",id);
        param.put("page",page);
        mineAskDetailsModel.getAskDetails(Url.url + "/api/user/answerDetial" + Url.type + mineAskDetailsModel.Site(context), context, param, new BasicCallback<ResponseBean<QADetailsBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<QADetailsBean>> response) {
                if (isRefresh){
                    mineAskDetailsView.refresh();
                }
                QADetailsBean qaDetailsBean = response.body().data;
                mineAskDetailsView.setDetails(qaDetailsBean.getNickname(),qaDetailsBean.getPic(),qaDetailsBean.getAddtime(),qaDetailsBean.getName(),
                        qaDetailsBean.getVname(),qaDetailsBean.getContent(),qaDetailsBean.getCount(),String.valueOf(qaDetailsBean.getId()));
                mineAskDetailsView.setAnswerList(qaDetailsBean.getSub());
            }

            @Override
            public void onError(Response<ResponseBean<QADetailsBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineAskDetailsView.showMsg(error);
            }

            @Override
            public void onFinish() {
                mineAskDetailsView.refreshComplete();
            }
        });
    }

    @Override
    public void setZan(String id, String type) {
        param = new HashMap<>();
        param.put("uid",mineAskDetailsModel.Uid(context));
        param.put("id",id);
        param.put("type",type);
        mineAskDetailsModel.setZan(Url.url + "/api/play/zan" + Url.type + mineAskDetailsModel.Site(context), context, param, new BasicCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                mineAskDetailsView.dianZan();
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineAskDetailsView.showMsg(error);
            }

            @Override
            public void onFinish() {
                mineAskDetailsView.setEnabled();
            }
        });
    }
}
