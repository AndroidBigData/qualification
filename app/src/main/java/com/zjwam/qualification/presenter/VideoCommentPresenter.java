package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoCommentBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.VideoCommentModel;
import com.zjwam.qualification.model.imodel.IVideoCommentModel;
import com.zjwam.qualification.presenter.ipresenter.IVideoCommentPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IVideoCommentView;

import java.util.HashMap;
import java.util.Map;

public class VideoCommentPresenter implements IVideoCommentPresenter {
    private Context context;
    private IVideoCommentView commentView;
    private IVideoCommentModel commentModel;
    private Map<String,String> param;

    public VideoCommentPresenter(Context context, IVideoCommentView commentView) {
        this.context = context;
        this.commentView = commentView;
        commentModel = new VideoCommentModel();
    }

    @Override
    public void getComment(String id, int page, final boolean isRefresh) {
        param = new HashMap<>();
        param.put("id",id);
        param.put("page", String.valueOf(page));
        commentModel.getComment(Url.url + "/api/play/comment" + Url.type + commentModel.site(context), context, param, new BasicCallback<ResponseBean<VideoCommentBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<VideoCommentBean>> response) {
                if (isRefresh){
                    commentView.refresh();
                }
                commentView.getComment(response.body().data.getComment(),response.body().data.getCount());
            }

            @Override
            public void onError(Response<ResponseBean<VideoCommentBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                commentView.showMsg(error);
            }

            @Override
            public void onFinish() {
                commentView.refreshComplete();
            }
        });
    }
}
