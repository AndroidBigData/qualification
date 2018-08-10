package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.MineCommentBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.MineCommentModel;
import com.zjwam.qualification.model.imodel.IMineCommentModel;
import com.zjwam.qualification.presenter.ipresenter.IMineCommentPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IMineCommentView;

import java.util.HashMap;
import java.util.Map;

public class MineCommentPresenter implements IMineCommentPresenter {
    private Context context;
    private IMineCommentView mineCommentView;
    private IMineCommentModel mineCommentModel;
    private Map<String,String> param;

    public MineCommentPresenter(Context context, IMineCommentView mineCommentView) {
        this.context = context;
        this.mineCommentView = mineCommentView;
        mineCommentModel = new MineCommentModel();
    }

    @Override
    public void getComment(String page, final boolean isRefresh) {
        param = new HashMap<>();
        param.put("uid",mineCommentModel.Uid(context));
        param.put("page",page);
        mineCommentModel.getComment(Url.url + "/api/user/comment" + Url.type + mineCommentModel.Site(context), context, param, new BasicCallback<ResponseBean<MineCommentBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<MineCommentBean>> response) {
                if (isRefresh){
                    mineCommentView.refresh();
                }
                MineCommentBean mineCommentBean = response.body().data;
                mineCommentView.addData(mineCommentBean.getComment(),mineCommentBean.getCount());
            }

            @Override
            public void onError(Response<ResponseBean<MineCommentBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineCommentView.showMsg(error);
            }

            @Override
            public void onFinish() {
                mineCommentView.refreshComplete();
            }
        });
    }
}
