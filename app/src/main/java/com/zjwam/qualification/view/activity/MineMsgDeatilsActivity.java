package com.zjwam.qualification.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;

public class MineMsgDeatilsActivity extends BaseActivity {
    private TextView msg_details_type,msg_details_title,msg_details_content,msg_details_from,msg_details_time,msg_title;
    private String type,title,time,content,from;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_msg_deatils);
        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("type");
        title = bundle.getString("title");
        content = bundle.getString("content");
        time = bundle.getString("time");
        from = bundle.getString("from");
        initView();
        initData();
    }

    private void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        msg_title.setText("信息详情");
        msg_details_type.setText("【"+type+"】");
        msg_details_title.setText(title);
        msg_details_time.setText(time);
        msg_details_content.setText(content);
        msg_details_from.setText("来自:"+from);
    }

    private void initView() {
        back = findViewById(R.id.back);
        msg_title = findViewById(R.id.title_name);
        msg_details_type = findViewById(R.id.msg_details_type);
        msg_details_title = findViewById(R.id.msg_details_title);
        msg_details_content = findViewById(R.id.msg_details_content);
        msg_details_from = findViewById(R.id.msg_details_from);
        msg_details_time = findViewById(R.id.msg_details_time);
    }
}
