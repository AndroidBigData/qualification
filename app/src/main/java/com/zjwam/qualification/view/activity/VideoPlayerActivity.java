package com.zjwam.qualification.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.utils.Reflex;
import com.zjwam.qualification.view.fragment.VideoCatalogFragment;
import com.zjwam.qualification.view.fragment.VideoCommentFragment;
import com.zjwam.qualification.view.fragment.VideoNoteFragment;
import com.zjwam.qualification.view.fragment.VideoQAFragment;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayerActivity extends BaseActivity {

    private TabLayout video_tab;
    private VideoCatalogFragment videoCatalogFragment;
    private VideoNoteFragment videoNoteFragment;
    private VideoQAFragment videoQAFragment;
    private VideoCommentFragment videoCommentFragment;
    private ViewPager video_viewpager;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private viewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        initView();
        initData();
    }

    private void initData() {

        titleList = new ArrayList<>();
        titleList.add("目录");
        titleList.add("笔记");
        titleList.add("问答");
        titleList.add("评价");

        fragmentList = new ArrayList<>();
        videoCatalogFragment = new VideoCatalogFragment();
        fragmentList.add(videoCatalogFragment);
        videoNoteFragment = new VideoNoteFragment();
        fragmentList.add(videoNoteFragment);
        videoQAFragment = new VideoQAFragment();
        fragmentList.add(videoQAFragment);
        videoCommentFragment = new VideoCommentFragment();
        fragmentList.add(videoCommentFragment);
        adapter = new viewPagerAdapter(getSupportFragmentManager());
        video_viewpager.setAdapter(adapter);
        video_tab.setupWithViewPager(video_viewpager);
        video_viewpager.setOffscreenPageLimit(4);
        video_tab.getTabAt(0).select();
        Reflex.setReflex(video_tab,25);
    }

    private void initView() {
        video_tab = findViewById(R.id.video_tab);
        video_viewpager = findViewById(R.id.video_viewpager);
    }
    class viewPagerAdapter extends FragmentPagerAdapter {
        public viewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
