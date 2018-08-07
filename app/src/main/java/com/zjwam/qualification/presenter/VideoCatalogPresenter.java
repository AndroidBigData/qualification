package com.zjwam.qualification.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.VideoCatalogBean;
import com.zjwam.qualification.callback.BasicCallback;
import com.zjwam.qualification.model.VideoCatalogModel;
import com.zjwam.qualification.model.imodel.IVideoCatalogModel;
import com.zjwam.qualification.presenter.ipresenter.IVideoCatalogPresenter;
import com.zjwam.qualification.utils.HttpErrorMsg;
import com.zjwam.qualification.utils.Url;
import com.zjwam.qualification.view.iview.IVideoCatalogView;

import java.util.HashMap;
import java.util.Map;

public class VideoCatalogPresenter implements IVideoCatalogPresenter {
    private Context context;
    private IVideoCatalogModel catalogModel;
    private IVideoCatalogView catalogView;
    private Map<String, String> param;
    private VideoCatalogBean catalogBean;
    private int buy;

    public VideoCatalogPresenter(Context context, IVideoCatalogView catalogView) {
        this.context = context;
        this.catalogView = catalogView;
        catalogModel = new VideoCatalogModel();
    }

    @Override
    public void getCataog(long id) {
        param = new HashMap<>();
        param.put("uid", catalogModel.uid(context));
        param.put("id", String.valueOf(id));
        catalogModel.getCatalog(Url.url + "/api/play/videoList" + Url.type + catalogModel.site(context), context, param, new BasicCallback<ResponseBean<VideoCatalogBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<VideoCatalogBean>> response) {
                catalogBean = response.body().data;
                buy = catalogBean.getBuy();
                catalogView.setAdapter(buy);
                catalogView.setCatalog(catalogBean.getVideo());
            }

            @Override
            public void onError(Response<ResponseBean<VideoCatalogBean>> response) {
                Throwable exception = response.getException();
                String error = HttpErrorMsg.getErrorMsg(exception);
                catalogView.showMsg(error);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void catalogPlay(int position) {
        if (buy == 1 || position < 2) {
            catalogView.catalogPlay();
            catalogModel.setVid(context, catalogBean.getVideo().get(position).getId());
        } else {
            catalogView.showMsg("暂无权限！");
        }
    }
}
