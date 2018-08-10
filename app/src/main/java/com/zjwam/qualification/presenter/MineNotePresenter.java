package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.MineNoteBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.MineNoteModel;
import com.zjwam.qualification.model.imodel.IMineNoteModel;
import com.zjwam.qualification.presenter.ipresenter.IMineNotePresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IMineNoteView;

import java.util.HashMap;
import java.util.Map;

public class MineNotePresenter implements IMineNotePresenter {
    private Context context;
    private IMineNoteModel mineNoteModel;
    private IMineNoteView mineNoteView;
    private Map<String,String> param;

    public MineNotePresenter(Context context, IMineNoteView mineNoteView) {
        this.context = context;
        this.mineNoteView = mineNoteView;
        mineNoteModel = new MineNoteModel();
    }

    @Override
    public void getMineNote(String page, final boolean isRefresh) {
        param = new HashMap<>();
        param.put("uid",mineNoteModel.uid(context));
        param.put("page",page);
        mineNoteModel.getMineNote(Url.url + "/api/user/note" + Url.type + mineNoteModel.site(context), context, param, new BasicCallback<ResponseBean<MineNoteBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<MineNoteBean>> response) {
                if (isRefresh){
                    mineNoteView.refresh();
                }
                MineNoteBean mineNoteBean = response.body().data;
                mineNoteView.addData(mineNoteBean.getNote(),mineNoteBean.getCount());
            }

            @Override
            public void onError(Response<ResponseBean<MineNoteBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineNoteView.showMsg(error);
            }

            @Override
            public void onFinish() {
                mineNoteView.refreshComplete();
            }
        });
    }

    @Override
    public void deleNote(String id) {
        param = new HashMap<>();
        param.put("uid",mineNoteModel.uid(context));
        param.put("id",id);
        mineNoteModel.deleteNote(Url.url + "/api/user/noteDel" + Url.type + mineNoteModel.site(context), context, param, new BasicCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                mineNoteView.showMsg(response.body().msg);
                mineNoteView.deleteSuccess();
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                mineNoteView.showMsg(error);
            }

            @Override
            public void onFinish() {
                mineNoteView.deleteFinish();
            }
        });
    }
}
