package com.zjwam.qualification.view.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.bean.VersionBean;
import com.zjwam.qualification.presenter.AboutPresenter;
import com.zjwam.qualification.presenter.ipresenter.IAboutPresenter;
import com.zjwam.qualification.view.iview.IAboutView;

import java.io.File;

public class AboutActivity extends BaseActivity implements IAboutView{
    private ImageView back;
    private TextView title,version_now,version_new;
    private Button download;
    private String versionname,downUrl;
    private IAboutPresenter aboutPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        initData();
    }

    private static NotificationManager mNotifiyManager;
    private static NotificationCompat.Builder mBuilder;

    private void initData() {
        aboutPresenter = new AboutPresenter(this,this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("版本信息");
        try {
            versionname = getPackageManager().
                    getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        version_now.setText("V"+versionname);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotifiyManager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                mBuilder=new  NotificationCompat.Builder(getBaseContext(),"0");
                mBuilder.setContentTitle("版本更新" )
                        .setContentText("正在下载...")
                        .setContentInfo("0%")
                        .setProgress(100,0,true)
                        .setSmallIcon(R.mipmap.ic_wxlm);
                OkGo.<File>get(downUrl)
                        .tag(this)
                        .execute(new FileCallback() {
                            @Override
                            public void onSuccess(Response<File> response) {
                                mNotifiyManager.cancel(0);
                                File body = response.body();
                                String path = body.getPath();
                                File App = new File(path);
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setDataAndType(Uri.fromFile(App),
                                        "application/vnd.android.package-archive");
                                startActivity(intent);
                            }
                            @Override
                            public void downloadProgress(Progress progress) {
                                super.downloadProgress(progress);
                                float fraction = progress.fraction;
                                mBuilder.setProgress(100,(int)(fraction*100),false);
                                mBuilder.setContentInfo((int)(fraction*100)+"%");
                                mNotifiyManager.notify(0,mBuilder.build());
                            }
                        });
            }
        });
        aboutPresenter.getVersion(versionname);
    }

    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title_name);
        version_now = findViewById(R.id.version_now);
        version_new = findViewById(R.id.version_new);
        download = findViewById(R.id.download);
    }

    @Override
    public void getVersion(VersionBean versionBean) {
        download.setVisibility(View.VISIBLE);
        version_new.setText("V"+versionBean.getVersion());
        downUrl = versionBean.getUrl();
    }

    @Override
    public void error() {
        download.setVisibility(View.GONE);
        version_new.setText("V"+versionname);
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }
}
