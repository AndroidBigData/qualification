package com.zjwam.qualification.presenter;

import android.content.Context;
import android.content.Intent;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.LoginBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.presenter.ipresenter.ILoginPresenter;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.ILoginView;
import com.zjwam.qualification.model.imodel.ILoginModel;
import com.zjwam.qualification.model.LoginModel;
import com.zjwam.qualification.utils.HttpErrorMsg;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenter implements ILoginPresenter {
    private Context context;
    private ILoginModel loginModel;
    private ILoginView loginView;
    private Map<String,String> param;
    private String msg,uid,site;

    public LoginPresenter(Context context,ILoginView loginView) {
        this.context = context;
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    @Override
    public void login(String name,String pass) {
        if (name.length()>0&&pass.length()>0){
            param = new HashMap<>();
            param.put("username",name);
            param.put("password",pass);
            loginModel.OkGoHttp(Url.url+"/api/login/index", context, param,new BasicCallback<ResponseBean<LoginBean>>() {
                @Override
                public void onSuccess(Response<ResponseBean<LoginBean>> response) {
                    msg = response.body().msg;
                    uid = String.valueOf(response.body().data.getUid());
                    site = response.body().data.getSite();
                    loginModel.saveLoginMsg(context,uid,site);
                    loginView.pushMsg();
                }
                @Override
                public void onError(Response<ResponseBean<LoginBean>> response) {
                    Throwable exception = response.getException();
                    msg = HttpErrorMsg.getErrorMsg(exception);
                }
                @Override
                public void onFinish() {
                    loginView.showMsg(msg);
                }
            });
        }else {
            loginView.showMsg("请完善登录信息");
        }
    }

    @Override
    public void exitApp() {
        Intent intent = new Intent();
        intent.setAction("exitapp");
        context.sendBroadcast(intent);
    }

    @Override
    public void pushMsg() {
        param = new HashMap<>();
        param.put("utype",site+uid);
        loginModel.pushMsg(Url.url + "/api/Login/push", context, param, new BasicCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {

            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {

            }

            @Override
            public void onFinish() {
                loginView.jumpToMainActivity(site+uid);
            }
        });
    }
}
