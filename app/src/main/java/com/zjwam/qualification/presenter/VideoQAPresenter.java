package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoQABean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.VideoQAModel;
import com.zjwam.qualification.model.imodel.IVideoQAModel;
import com.zjwam.qualification.presenter.ipresenter.IVideoQAPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IVideoQAView;

import java.util.HashMap;
import java.util.Map;

public class VideoQAPresenter implements IVideoQAPresenter{
    private Context context;
    private IVideoQAView videoQAView;
    private IVideoQAModel videoQAModel;
    private Map<String,String> param;

    public VideoQAPresenter(Context context, IVideoQAView videoQAView) {
        this.context = context;
        this.videoQAView = videoQAView;
        videoQAModel = new VideoQAModel();
    }

    @Override
    public void getQA(String id, int page, final boolean isRefresh) {
        param = new HashMap<>();
        param.put("id", id);
        param.put("uid",videoQAModel.uid(context));
        param.put("page", String.valueOf(page));
        videoQAModel.getQA(Url.url + "/api/play/answer" + Url.type + videoQAModel.site(context), context, param, new BasicCallback<ResponseBean<VideoQABean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<VideoQABean>> response) {
                if (isRefresh){
                    videoQAView.refresh();
                }
                VideoQABean videoQABean = response.body().data;
                videoQAView.addData(videoQABean.getAnswer(),videoQABean.getCount());
            }

            @Override
            public void onError(Response<ResponseBean<VideoQABean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                videoQAView.showMsg(error);
            }

            @Override
            public void onFinish() {
                videoQAView.refreshComplete();
            }
        });
    }

    @Override
    public String getVid() {
        return videoQAModel.vid(context);
    }

    @Override
    public String getVtime() {
        return videoQAModel.vtime(context);
    }

    @Override
    public void askQuestion(long id, long vid, String vtime, String question) {
        param = new HashMap<>();
        param.put("id", String.valueOf(vid));
        param.put("clid", String.valueOf(id));
        param.put("vtime",vtime);
        param.put("uid",videoQAModel.uid(context));
        param.put("content",question);
        videoQAModel.AskQuestion(Url.url + "/api/play/answerAdd" + Url.type + videoQAModel.site(context), context, param, new BasicCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                videoQAView.dialogDismiss();
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                videoQAView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
