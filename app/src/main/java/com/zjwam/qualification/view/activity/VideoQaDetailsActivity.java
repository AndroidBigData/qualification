package com.zjwam.qualification.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;

public class VideoQaDetailsActivity extends BaseActivity {

    private ImageView back;
    private TextView title;
    private LuRecyclerView qa_deatils_recyclerview;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_qa_details);
        id = getIntent().getExtras().getString("id");
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
        title.setText("问答详情");
    }

    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title_name);
        qa_deatils_recyclerview = findViewById(R.id.qa_deatils_recyclerview);
    }
}
