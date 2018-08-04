package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ClassificationBean;
import com.zjwam.qualification.bean.CoursesListBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.CurriculumModel;
import com.zjwam.qualification.model.imodel.ICurriculumModel;
import com.zjwam.qualification.presenter.ipresenter.ICurriculumPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.ICurriculumView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurriculumPresenter implements ICurriculumPresenter {
    private Context context;
    private ICurriculumView curriculumView;
    private Map<String, String> param;
    private ICurriculumModel curriculumModel;
    private int max_items;

    public CurriculumPresenter(Context context, ICurriculumView curriculumView) {
        this.context = context;
        this.curriculumView = curriculumView;
        curriculumModel = new CurriculumModel();
    }

    @Override
    public void getData(String cid, String page, final boolean isRefresh) {
        param = new HashMap<>();
        param.put("uid", curriculumModel.Uid(context));
        param.put("cid",cid);
        param.put("page", page);
        curriculumModel.getData(Url.url+"/api/index/getList"+Url.type+curriculumModel.Site(context), context, param, new BasicCallback<ResponseBean<CoursesListBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<CoursesListBean>> response) {
                max_items = response.body().data.getCount();
                if (isRefresh) {
                    curriculumView.clearRecyclerView();
                }
                if (max_items>0){
                    curriculumView.initData(response.body().data.getClassList());
                }else {
                    curriculumView.setNoData();
                }
            }
            @Override
            public void onError(Response<ResponseBean<CoursesListBean>> response) {
                Throwable exception = response.getException();
                String msg = HttpErrorMsg.getErrorMsg(exception);
                curriculumView.showMsg(msg);
            }

            @Override
            public void onFinish() {
                curriculumView.refreshComplete();
            }
        });
    }

    @Override
    public int maxItems() {
        return max_items;
    }

    @Override
    public void getClassification() {
        curriculumModel.getClassification(Url.url + "/api/index/cate" + Url.type + curriculumModel.Site(context), context, null, new BasicCallback<ResponseBean<List<ClassificationBean>>>() {
            @Override
            public void onSuccess(Response<ResponseBean<List<ClassificationBean>>> response) {
                curriculumView.setClassification(response.body().data);
            }

            @Override
            public void onError(Response<ResponseBean<List<ClassificationBean>>> response) {
                Throwable exception = response.getException();
                String msg = HttpErrorMsg.getErrorMsg(exception);
                curriculumView.showMsg(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
