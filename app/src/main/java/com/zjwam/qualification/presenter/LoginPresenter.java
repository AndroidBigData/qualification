package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.presenter.ipresenter.ILoginPresenter;
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
    private String msg;

    public LoginPresenter(Context context,ILoginView loginView) {
        this.context = context;
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    @Override
    public void login(String name,String pass) {
        if (name.length()>0&&pass.length()>0){
            param = new HashMap<>();
            param.put("name",name);
            param.put("pass",pass);
            loginModel.OkGoHttp("http://zkw.org.cn/api/login/login", context, param,new BasicCallback<ResponseBean<EmptyBean>>() {
                @Override
                public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                    msg = response.body().msg;
                }
                @Override
                public void onError(Response<ResponseBean<EmptyBean>> response) {
                    Throwable exception = response.getException();
                    msg = HttpErrorMsg.getErrorMsg(exception);
                }
                @Override
                public void onFinish() {
                    loginView.initData(msg);
                }
            });
        }else {
            loginView.initData("请完善登录信息");
        }
    }
}
