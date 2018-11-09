package com.zjwam.qualification.view.activity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.utils.QlftPreference;

import cn.jpush.android.api.JPushInterface;

public class LogoutNotifiActivity extends Activity {

    private TextView dialog_qx,dialog_qr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_notifi);
        initView();
        initData();
    }

    private void initData() {
        JPushInterface.setAlias(getBaseContext(), 1, "");
        JPushInterface.deleteAlias(getBaseContext(), 1);
        QlftPreference.getInstance(getBaseContext()).SetUid("");
        QlftPreference.getInstance(getBaseContext()).setSite("");
        QlftPreference.getInstance(getBaseContext()).setVideoId("");
        QlftPreference.getInstance(getBaseContext()).setViteoTime("");
        QlftPreference.getInstance(getBaseContext()).SetIsFlag(false);
        dialog_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("exitapp");
                sendBroadcast(intent);
                finish();
            }
        });
        dialog_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("exitapp");
                sendBroadcast(intent);
                startActivity(new Intent(getBaseContext(),LoginActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        dialog_qx = findViewById(R.id.dialog_qx);
        dialog_qr = findViewById(R.id.dialog_qr);
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
