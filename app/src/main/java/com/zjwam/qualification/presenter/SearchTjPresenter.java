package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.SearchTJBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.SearchTJModel;
import com.zjwam.qualification.model.imodel.ISearchTJModel;
import com.zjwam.qualification.presenter.ipresenter.ISearchTjPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.ISearchTJView;

import java.util.List;

public class SearchTjPresenter implements ISearchTjPresenter {
    private Context context;
    private ISearchTJView searchTJView;
    private ISearchTJModel iSearchTJModel;

    public SearchTjPresenter(Context context, ISearchTJView searchTJView) {
        this.context = context;
        this.searchTJView = searchTJView;
        iSearchTJModel = new SearchTJModel();
    }

    @Override
    public void getTJ() {
        iSearchTJModel.getTJ(Url.url + "/api/index/getHotClass" + Url.type + iSearchTJModel.site(context), context, null, new BasicCallback<ResponseBean<List<SearchTJBean>>>() {
            @Override
            public void onSuccess(Response<ResponseBean<List<SearchTJBean>>> response) {
                searchTJView.setTJ(response.body().data);
            }

            @Override
            public void onError(Response<ResponseBean<List<SearchTJBean>>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                searchTJView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
