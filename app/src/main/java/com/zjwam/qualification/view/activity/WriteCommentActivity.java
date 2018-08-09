package com.zjwam.qualification.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.presenter.WriteCommentPresenter;
import com.zjwam.qualification.presenter.ipresenter.IWriteCommentPresenter;
import com.zjwam.qualification.view.iview.IWriteCommentView;

public class WriteCommentActivity extends BaseActivity implements IWriteCommentView{

    private TextView comment_save;
    private EditText comment_text;
    private ImageView comment_back;
    private IWriteCommentPresenter writeCommentPresenter;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);
        id = getIntent().getExtras().getString("id");
        initView();
        initData();
    }

    private void initData() {
        writeCommentPresenter = new WriteCommentPresenter(this,this);
        comment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        comment_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comment_text.getText().toString().trim().length()>0){
                    writeCommentPresenter.upComment(id,comment_text.getText().toString());
                }else {
                    showMsg("输入内容为空！");
                }
            }
        });
    }

    private void initView() {
        comment_save = findViewById(R.id.comment_save);
        comment_text = findViewById(R.id.comment_text);
        comment_back = findViewById(R.id.comment_back);
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
