package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.MineBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.MineModel;
import com.zjwam.qualification.model.imodel.IMineModel;
import com.zjwam.qualification.presenter.ipresenter.IMinePresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IMineView;

import java.util.HashMap;
import java.util.Map;

public class MinePresenter implements IMinePresenter {

    private Context context;
    private IMineModel mineModel;
    private IMineView mineView;
    private Map<String,String> param;

    public MinePresenter(Context context, IMineView mineView) {
        this.context = context;
        this.mineView = mineView;
        mineModel = new MineModel();
    }

    @Override
    public void getMine() {
        param = new HashMap<>();
        param.put("uid",mineModel.Uid(context));
        mineModel.getMine(Url.url + "/api/User/index" + Url.type + mineModel.Site(context), context, param, new BasicCallback<ResponseBean<MineBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<MineBean>> response) {
                MineBean mineBean = response.body().data;
                mineView.setMine(mineBean);
            }

            @Override
            public void onError(Response<ResponseBean<MineBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
