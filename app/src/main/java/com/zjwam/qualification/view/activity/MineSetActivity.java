package com.zjwam.qualification.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.utils.QlftPreference;

public class MineSetActivity extends BaseActivity {

    private TextView mine_setup,setup_safe,logout,title;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_set);
        initView();
        initData();
    }

    private void initData() {
        title.setText("设置");
        mine_setup.setOnClickListener(onClickListener);
        setup_safe.setOnClickListener(onClickListener);
        logout.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.mine_setup:
                    startActivity(new Intent(getBaseContext(),MineInfoActivity.class));
                    break;
                case R.id.setup_safe:
                    startActivity(new Intent(getBaseContext(),ChangePassWorldActivity.class));
                    break;
                case R.id.logout:
                    QlftPreference.getInstance(getBaseContext()).SetUid("");
                    QlftPreference.getInstance(getBaseContext()).setSite("");
                    QlftPreference.getInstance(getBaseContext()).setVideoId("");
                    QlftPreference.getInstance(getBaseContext()).setViteoTime("");
                    QlftPreference.getInstance(getBaseContext()).SetIsFlag(false);
                    Intent intent = new Intent();
                    intent.setAction("exitapp");
                    sendBroadcast(intent);
                    startActivity(new Intent(getBaseContext(),LoginActivity.class));
                    break;
            }
        }
    };

    private void initView() {
        mine_setup = findViewById(R.id.mine_setup);
        setup_safe = findViewById(R.id.setup_safe);
        logout = findViewById(R.id.logout);
        title = findViewById(R.id.title_name);
        back = findViewById(R.id.back);
    }
}
