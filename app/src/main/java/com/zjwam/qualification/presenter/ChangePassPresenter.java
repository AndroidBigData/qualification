package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.ChangePassModel;
import com.zjwam.qualification.model.imodel.IChangePassModel;
import com.zjwam.qualification.presenter.ipresenter.IChangePassPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IChangePassView;

import java.util.HashMap;
import java.util.Map;

public class ChangePassPresenter implements IChangePassPresenter {
    private Context context;
    private IChangePassView changePassView;
    private IChangePassModel changePassModel;
    private Map<String ,String> param;

    public ChangePassPresenter(Context context, IChangePassView changePassView) {
        this.context = context;
        this.changePassView = changePassView;
        changePassModel = new ChangePassModel();
    }

    @Override
    public void upNew(String old, String new1, String new2) {
        param = new HashMap<>();
        if (new1.length()>0&&new2.length()>0&&old.length()>0){
            param.put("uid",changePassModel.Uid(context));
            param.put("newPwd",new1);
            param.put("reNewPwd",new2);
            param.put("oldPwd",old);
            changePassModel.upNew(Url.url + "/api/user/setPwd" + Url.type + changePassModel.Site(context), context, param, new BasicCallback<ResponseBean<EmptyBean>>() {
                @Override
                public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                    changePassView.showMsg(response.body().msg);
                    changePassView.changeSuccess();
                }

                @Override
                public void onError(Response<ResponseBean<EmptyBean>> response) {
                    Throwable exception = response.getException();
                    String error = HttpErrorMsg.getErrorMsg(exception);
                    changePassView.showMsg(error);
                }

                @Override
                public void onFinish() {

                }
            });
        }else {
            changePassView.showMsg("请完善信息！");
        }

    }
}
