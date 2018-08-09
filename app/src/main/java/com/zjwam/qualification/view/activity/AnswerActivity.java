package com.zjwam.qualification.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.presenter.AnswerPresenter;
import com.zjwam.qualification.presenter.ipresenter.IAnswerPresenter;
import com.zjwam.qualification.view.iview.IAnswerView;

public class AnswerActivity extends BaseActivity implements IAnswerView {

    private ImageView back;
    private TextView title,answer_up;
    private EditText answer_content;
    private String id;
    private IAnswerPresenter answerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        id = getIntent().getExtras().getString("id");
        initView();
        initData();
    }

    private void initData() {
        answerPresenter = new AnswerPresenter(this,this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("问题回复");
        answer_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer_content.getText().toString().trim().length()>0){
                    answerPresenter.upAnswer(id,answer_content.getText().toString());
                }else {
                    error("回复内容不得为空");
                }
            }
        });
    }

    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title_name);
        answer_up = findViewById(R.id.answer_up);
        answer_content = findViewById(R.id.answer_content);
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }

    @Override
    public void success() {
        finish();
    }
}
