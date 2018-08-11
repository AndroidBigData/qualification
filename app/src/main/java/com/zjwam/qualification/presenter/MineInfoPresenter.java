package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.MineInfoBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.MineInfoModel;
import com.zjwam.qualification.model.imodel.IMineInfoModel;
import com.zjwam.qualification.presenter.ipresenter.IMineInfoPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IMineInfoView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MineInfoPresenter implements IMineInfoPresenter {
    private Context context;
    private IMineInfoView mineInfoView;
    private IMineInfoModel mineInfoModel;
    private Map<String,String> param;

    public MineInfoPresenter(Context context, IMineInfoView mineInfoView) {
        this.context = context;
        this.mineInfoView = mineInfoView;
        mineInfoModel = new MineInfoModel();
    }

    @Override
    public void upImg(String path) {
        param = new HashMap<>();
        param.put("uid",mineInfoModel.Uid(context));
        mineInfoModel.upImg(Url.url + "/api/user/setPic" + Url.type + mineInfoModel.Site(context), context, param, "pic",new File(path), new BasicCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                mineInfoView.setTxSuccess();
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineInfoView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void getInfo() {
        param = new HashMap<>();
        param.put("uid",mineInfoModel.Uid(context));
        mineInfoModel.getInfo(Url.url + "/api/user/info" + Url.type + mineInfoModel.Site(context), context, param, new BasicCallback<ResponseBean<MineInfoBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<MineInfoBean>> response) {
                mineInfoView.setMineInfo(response.body().data);
            }

            @Override
            public void onError(Response<ResponseBean<MineInfoBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineInfoView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void upInfo(String nickname, String sign, String sex, String email) {
        param = new HashMap<>();
        param.put("uid",mineInfoModel.Uid(context));
        param.put("nickname",nickname);
        param.put("sign",sign);
        param.put("sex",sex);
        param.put("email",email);
        mineInfoModel.upInfo(Url.url + "/api/user/infoEdit" + Url.type + mineInfoModel.Site(context), context, param, new BasicCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                mineInfoView.showMsg(response.body().msg);
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineInfoView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
