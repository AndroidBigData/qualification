package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.CoursesListBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.SearchResultModel;
import com.zjwam.qualification.model.imodel.ISearchResultModel;
import com.zjwam.qualification.presenter.ipresenter.ISearchResultPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.ISearchResultView;

import java.util.HashMap;
import java.util.Map;

public class SearchResultPresenter implements ISearchResultPresenter {
    private Context context;
    private ISearchResultModel resultModel;
    private ISearchResultView resultView;
    private Map<String,String> param;

    public SearchResultPresenter(Context context, ISearchResultView resultView) {
        this.context = context;
        this.resultView = resultView;
        resultModel = new SearchResultModel();
    }

    @Override
    public void getSearch(String content, String page, final boolean isRefresh) {
        param = new HashMap<>();
        param.put("uid",resultModel.uid(context));
        param.put("classname",content);
        param.put("page",page);
        resultModel.getSearch(Url.url + "/api/index/searchClass" + Url.type + resultModel.site(context), context, param, new BasicCallback<ResponseBean<CoursesListBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<CoursesListBean>> response) {
                if (isRefresh){
                    resultView.clearRecyclerView();
                }
                CoursesListBean coursesListBean = response.body().data;
                resultView.initData(coursesListBean.getClassList(),coursesListBean.getCount());
            }

            @Override
            public void onError(Response<ResponseBean<CoursesListBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                resultView.showMsg(error);
            }

            @Override
            public void onFinish() {
                resultView.refreshComplete();
            }
        });
    }
}
