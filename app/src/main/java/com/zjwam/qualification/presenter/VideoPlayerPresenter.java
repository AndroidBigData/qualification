package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoPlayerBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.VideoPlayerModel;
import com.zjwam.qualification.model.imodel.IVideoPlayerModel;
import com.zjwam.qualification.presenter.ipresenter.IVideoPlayerPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IVideoPlayerView;

import java.util.HashMap;
import java.util.Map;

public class VideoPlayerPresenter implements IVideoPlayerPresenter {
    private Context context;
    private IVideoPlayerModel videoPlayerModel;
    private IVideoPlayerView videoPlayerView;
    private Map<String,String> param;
    private VideoPlayerBean videoPlayerBean;

    public VideoPlayerPresenter(Context context, IVideoPlayerView videoPlayerView) {
        this.context = context;
        this.videoPlayerView = videoPlayerView;
        videoPlayerModel = new VideoPlayerModel();
    }

    @Override
    public void getFirstVideo(long id) {
        param = new HashMap<>();
        param.put("id",String.valueOf(id));
        videoPlayerModel.getFirstVideo(Url.url + "/api/play/index" + Url.type + videoPlayerModel.site(context), context, param, new BasicCallback<ResponseBean<VideoPlayerBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<VideoPlayerBean>> response) {
                videoPlayerBean = response.body().data;
                videoPlayerView.play(videoPlayerBean.getAddress(),videoPlayerBean.getVname());
                videoPlayerModel.setVid(context,videoPlayerBean.getId());
            }

            @Override
            public void onError(Response<ResponseBean<VideoPlayerBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                videoPlayerView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void setVtime(int vtime) {
        videoPlayerModel.setVtime(context,vtime);
    }
}
