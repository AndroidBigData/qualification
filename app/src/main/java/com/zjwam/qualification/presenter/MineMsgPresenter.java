package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.MineMsgBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.MineMsgModel;
import com.zjwam.qualification.model.imodel.IMineMsgModel;
import com.zjwam.qualification.presenter.ipresenter.IMineMsgPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.MyException;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IMineMSgView;

import java.util.HashMap;
import java.util.Map;

public class MineMsgPresenter implements IMineMsgPresenter {
    private Context context;
    private IMineMSgView mineMSgView;
    private IMineMsgModel mineMsgModel;
    private Map<String, String> param;

    public MineMsgPresenter(Context context, IMineMSgView mineMSgView) {
        this.context = context;
        this.mineMSgView = mineMSgView;
        mineMsgModel = new MineMsgModel();
    }

    @Override
    public void getMsg(String page, final boolean isRefresh) {
        param = new HashMap<>();
        param.put("uid", mineMsgModel.Uid(context));
        param.put("page", page);
        mineMsgModel.getMsg(Url.url + "/api/user/notice" + Url.type + mineMsgModel.Site(context), context, param, new BasicCallback<ResponseBean<MineMsgBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<MineMsgBean>> response) {
                if (isRefresh) {
                    mineMSgView.refresh();
                }
                MineMsgBean mineMsgBean = response.body().data;
                mineMSgView.setMsg(mineMsgBean.getNotice(), mineMsgBean.getCount());
            }

            @Override
            public void onError(Response<ResponseBean<MineMsgBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineMSgView.showMsg(error);
            }

            @Override
            public void onFinish() {
                mineMSgView.refreshComplete();
            }
        });
    }

    @Override
    public void setRead(String id) {
        param = new HashMap<>();
        param.put("uid", mineMsgModel.Uid(context));
        param.put("id", id);
        mineMsgModel.setRead(Url.url + "/api/user/noticeShow" + Url.type + mineMsgModel.Site(context), context, param, new BasicCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                mineMSgView.read();
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineMSgView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
