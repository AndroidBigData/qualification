package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.PersonalMineCommentBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.CurriculumModel;
import com.zjwam.qualification.model.imodel.ICurriculumModel;
import com.zjwam.qualification.presenter.ipresenter.ICurriculumPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.view.iview.ICurriculumView;

import java.util.HashMap;
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
    public void getData(String uid, String page, final boolean isRefresh) {
        param = new HashMap<>();
        param.put("uid", uid);
        param.put("page", page);
        curriculumModel.getData("http://zkw.org.cn/api/user/comment", context, param, new BasicCallback<ResponseBean<PersonalMineCommentBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<PersonalMineCommentBean>> response) {
                max_items = response.body().data.getCount();
                if (isRefresh) {
                    curriculumView.clearRecyclerView();
                }
                curriculumView.initData(response.body().data.getComment());
            }

            @Override
            public void onError(Response<ResponseBean<PersonalMineCommentBean>> response) {
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
}
