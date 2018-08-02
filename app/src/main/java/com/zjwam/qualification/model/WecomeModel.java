package com.zjwam.qualification.model;

import android.content.Context;

import com.zjwam.qualification.model.imodel.IWecomeModel;
import com.zjwam.qualification.utils.QlftPreference;

public class WecomeModel implements IWecomeModel {
    private boolean isFlag;
    @Override
    public boolean isFlag(Context context) {
        isFlag = QlftPreference.getInstance(context).IsFlag();
        return isFlag;
    }
}
