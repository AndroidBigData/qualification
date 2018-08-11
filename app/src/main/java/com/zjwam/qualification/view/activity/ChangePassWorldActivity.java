package com.zjwam.qualification.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.presenter.ChangePassPresenter;
import com.zjwam.qualification.presenter.ipresenter.IChangePassPresenter;
import com.zjwam.qualification.view.iview.IChangePassView;

public class ChangePassWorldActivity extends BaseActivity implements IChangePassView{

    private EditText pass_old,pass_new,pass_new2;
    private ImageView back;
    private TextView title;
    private Button change_pass;
    private IChangePassPresenter changePassPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_world);
        initView();
        initData();
    }

    private void initData() {
        changePassPresenter = new ChangePassPresenter(this,this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("修改密码");
        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassPresenter.upNew(pass_old.getText().toString(),pass_new.getText().toString(),pass_new2.getText().toString());
            }
        });
    }

    private void initView() {
        pass_old = findViewById(R.id.pass_old);
        pass_new = findViewById(R.id.pass_new);
        pass_new2 = findViewById(R.id.pass_new2);
        back = findViewById(R.id.back);
        title = findViewById(R.id.title_name);
        change_pass = findViewById(R.id.change_pass);
    }

    @Override
    public void changeSuccess() {
        pass_old.setText("");
        pass_new.setText("");
        pass_new2.setText("");
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }
}
