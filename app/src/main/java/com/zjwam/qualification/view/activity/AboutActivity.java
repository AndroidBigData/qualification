package com.zjwam.qualification.view.activity;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.bean.VersionBean;
import com.zjwam.qualification.presenter.AboutPresenter;
import com.zjwam.qualification.presenter.ipresenter.IAboutPresenter;
import com.zjwam.qualification.view.iview.IAboutView;

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

    private void initData() {
        aboutPresenter = new AboutPresenter(this,this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("版本更新");
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
                error("xiazai");
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
