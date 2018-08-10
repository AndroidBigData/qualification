package com.zjwam.qualification.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.ViewPagerAdapter;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.utils.Reflex;
import com.zjwam.qualification.view.fragment.MineAnswerFragment;
import com.zjwam.qualification.view.fragment.MineAskFragment;

import java.util.ArrayList;
import java.util.List;

public class MineQAActivity extends BaseActivity {

    private List<String> titleList;
    private List<Fragment> fragmentList;
    private TabLayout courseanswer_tablayout;
    private ViewPager courseanswer_viewpager;
    private ImageView back;
    private TextView title;
    private MineAskFragment mineAskFragment;
    private MineAnswerFragment mineAnswerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_qa);
        initView();
        initData();
    }

    private void initData() {
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList.add("我提问的");
        titleList.add("我回答的");
        mineAskFragment = MineAskFragment.newInstance(this);
        fragmentList.add(mineAskFragment);
        mineAnswerFragment = MineAnswerFragment.newInstance(this);
        fragmentList.add(mineAnswerFragment);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),titleList,fragmentList);
        courseanswer_viewpager.setAdapter(viewPagerAdapter);
        courseanswer_tablayout.setupWithViewPager(courseanswer_viewpager);
        courseanswer_viewpager.setOffscreenPageLimit(2);
        Reflex.setReflex(courseanswer_tablayout,56);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("课程答疑");
    }

    private void initView() {
        courseanswer_tablayout = findViewById(R.id.courseanswer_tablayout);
        courseanswer_viewpager = findViewById(R.id.courseanswer_viewpager);
        back = findViewById(R.id.back);
        title = findViewById(R.id.title_name);
    }
}
