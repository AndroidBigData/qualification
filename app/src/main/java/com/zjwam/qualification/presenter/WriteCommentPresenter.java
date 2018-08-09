package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.EmptyBean;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.WriteCommentModel;
import com.zjwam.qualification.model.imodel.IWriteCommentModel;
import com.zjwam.qualification.presenter.ipresenter.IWriteCommentPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IWriteCommentView;

import java.util.HashMap;
import java.util.Map;

public class WriteCommentPresenter implements IWriteCommentPresenter {
    private Context context;
    private IWriteCommentView writeCommentView;
    private IWriteCommentModel writeCommentModel;
    private Map<String,String> param;

    public WriteCommentPresenter(Context context, IWriteCommentView writeCommentView) {
        this.context = context;
        this.writeCommentView = writeCommentView;
        writeCommentModel = new WriteCommentModel();
    }

    @Override
    public void upComment(String id, String content) {
        param = new HashMap<>();
        param.put("uid",writeCommentModel.uid(context));
        param.put("id",writeCommentModel.vid(context));
        param.put("clid",id);
        param.put("content",content);
        writeCommentModel.upComment(Url.url + "/api/play/commentAdd" + Url.type + writeCommentModel.site(context), context, param, new BasicCallback<ResponseBean<EmptyBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EmptyBean>> response) {
                writeCommentView.success();
            }

            @Override
            public void onError(Response<ResponseBean<EmptyBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                writeCommentView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
