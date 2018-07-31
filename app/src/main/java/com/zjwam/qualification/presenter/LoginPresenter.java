package com.zjwam.qualification.presenter;

import android.content.Context;
import android.widget.Toast;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.CallBack;
import com.zjwam.qualification.model.ILoginModel;
import com.zjwam.qualification.model.LoginModel;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.view.BaseView;

public class LoginPresenter implements ILoginPresenter {
    private Context context;
    private ILoginModel loginModel;
    private BaseView baseView;

    public LoginPresenter(Context context,BaseView baseView) {
        this.context = context;
        this.baseView = baseView;
        loginModel = new LoginModel();
    }

    public void loginBtn(){
        baseView.initData();
    }

    @Override
    public void login(String name,String pass) {
        loginModel.OkGoHttp(name,pass,context, new CallBack<EmptyBean>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                Toast.makeText(context,response.body().msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {

            }
        });
    }

}
