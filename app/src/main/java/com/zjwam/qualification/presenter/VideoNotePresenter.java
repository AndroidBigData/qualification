package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoNoteBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.VideoNoteModel;
import com.zjwam.qualification.model.imodel.IVideoNoteModel;
import com.zjwam.qualification.presenter.ipresenter.IVideoNotePresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IVideoNoteView;

import java.util.HashMap;
import java.util.Map;

public class VideoNotePresenter implements IVideoNotePresenter {
    private Context context;
    private IVideoNoteView videoNoteView;
    private IVideoNoteModel videoNoteModel;
    private Map<String,String> param;
    private VideoNoteBean videoNoteBean;

    public VideoNotePresenter(Context context, IVideoNoteView videoNoteView) {
        this.context = context;
        this.videoNoteView = videoNoteView;
        videoNoteModel = new VideoNoteModel();
    }

    @Override
    public void getNote(long id, int page, final boolean isRefresh) {
        param = new HashMap<>();
        param.put("uid",videoNoteModel.uid(context));
        param.put("page", String.valueOf(page));
        param.put("id", String.valueOf(id));
        videoNoteModel.getNote(Url.url + "/api/play/note" + Url.type + videoNoteModel.site(context), context, param, new BasicCallback<ResponseBean<VideoNoteBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<VideoNoteBean>> response) {
                videoNoteBean = response.body().data;
                if (isRefresh){
                    videoNoteView.refresh();
                }
                videoNoteView.setNote(videoNoteBean.getNote());
            }

            @Override
            public void onError(Response<ResponseBean<VideoNoteBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                videoNoteView.showMsg(error);
            }

            @Override
            public void onFinish() {
                videoNoteView.refreshComplete();
            }
        });
    }

    @Override
    public int getCount() {
        return videoNoteBean.getCount();
    }

    @Override
    public long getVid() {
        return videoNoteModel.getVid(context);
    }

    @Override
    public String getVtime() {
        return videoNoteModel.getVtime(context);
    }

    @Override
    public void writeNote(long id, long vid, String vtime, String note) {
        param = new HashMap<>();
        param.put("uid",videoNoteModel.uid(context));
        param.put("clid", String.valueOf(id));
        param.put("id", String.valueOf(vid));
        param.put("vtime",vtime);
        param.put("content",note);
        videoNoteModel.getWriteNoteMsg(Url.url + "/api/play/noteAdd" + Url.type + videoNoteModel.site(context), context, param, new BasicCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                videoNoteView.showMsg(response.body().msg);
                videoNoteView.dialogDismiss();
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                videoNoteView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
