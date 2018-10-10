package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VersionBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.AboutModel;
import com.zjwam.qualification.model.imodel.IAboutModel;
import com.zjwam.qualification.presenter.ipresenter.IAboutPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.MyException;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.activity.AboutActivity;
import com.zjwam.qualification.view.iview.IAboutView;

import java.util.HashMap;
import java.util.Map;

public class AboutPresenter implements IAboutPresenter {
    private Context context;
    private IAboutView aboutView;
    private IAboutModel aboutModel;
    private Map<String,String> param;

    public AboutPresenter(Context context, IAboutView aboutView) {
        this.context = context;
        this.aboutView = aboutView;
        aboutModel = new AboutModel();
    }

    @Override
    public void getVersion(String nowVersion) {
        param = new HashMap<>();
        param.put("type","android");
        param.put("version",nowVersion);
        aboutModel.getVersion(Url.url + "/api/user/version" + Url.type + aboutModel.site(context), context, param, new BasicCallback<ResponseBean<VersionBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<VersionBean>> response) {
                aboutView.getVersion(response.body().data);
            }

            @Override
            public void onError(Response<ResponseBean<VersionBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                if (!(exception instanceof MyException)){
                    aboutView.showMsg(error);
                }
                aboutView.error();
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
