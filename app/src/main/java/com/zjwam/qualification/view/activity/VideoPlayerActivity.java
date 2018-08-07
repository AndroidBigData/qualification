package com.zjwam.qualification.view.activity;

import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.ViewPagerAdapter;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.custom.LandLayoutVideo;
import com.zjwam.qualification.presenter.VideoPlayerPresenter;
import com.zjwam.qualification.presenter.ipresenter.IVideoPlayerPresenter;
import com.zjwam.qualification.utils.GetVideoTime;
import com.zjwam.qualification.utils.QlftPreference;
import com.zjwam.qualification.utils.Reflex;
import com.zjwam.qualification.view.fragment.VideoCatalogFragment;
import com.zjwam.qualification.view.fragment.VideoCommentFragment;
import com.zjwam.qualification.view.fragment.VideoNoteFragment;
import com.zjwam.qualification.view.fragment.VideoQAFragment;
import com.zjwam.qualification.view.iview.IVideoPlayerView;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayerActivity extends BaseActivity implements IVideoPlayerView, VideoCatalogFragment.FragmentInteraction,GetVideoTime {

    private TabLayout video_tab;
    private VideoCatalogFragment videoCatalogFragment;
    private VideoNoteFragment videoNoteFragment;
    private VideoQAFragment videoQAFragment;
    private VideoCommentFragment videoCommentFragment;
    private ViewPager video_viewpager;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private ViewPagerAdapter adapter;
    private long id;
    private String name;
    private LandLayoutVideo detailPlayer;
    private OrientationUtils orientationUtils;
    private GSYVideoOptionBuilder gsyVideoOption;
    private boolean isPlay, isPause;
    private IVideoPlayerPresenter videoPlayerPresenter;
    private TextView title_name;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        initView();
        initData();
    }

    private void initData() {
        id = getIntent().getExtras().getLong("id");
        name = getIntent().getExtras().getString("name");
        title_name.setText(name);
        initVideo();
        titleList = new ArrayList<>();
        titleList.add("目录");
        titleList.add("笔记");
        titleList.add("问答");
        titleList.add("评价");
        fragmentList = new ArrayList<>();
        videoCatalogFragment = VideoCatalogFragment.newInstance(this, id);
        fragmentList.add(videoCatalogFragment);
        videoNoteFragment = VideoNoteFragment.newInstance(this,id);
        fragmentList.add(videoNoteFragment);
        videoQAFragment = VideoQAFragment.newInstance(this,id);
        fragmentList.add(videoQAFragment);
        videoCommentFragment = VideoCommentFragment.newInstance(this, id);
        fragmentList.add(videoCommentFragment);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), titleList, fragmentList);
        video_viewpager.setAdapter(adapter);
        video_tab.setupWithViewPager(video_viewpager);
        video_viewpager.setOffscreenPageLimit(4);
        video_tab.getTabAt(0).select();
        Reflex.setReflex(video_tab, 25);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initVideo() {
        resolveNormalVideoUI();
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption
                .setIsTouchWiget(true)
                .setRotateViewAuto(true)
                .setLockLand(false)
                .setAutoFullWithSize(true)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                })
                .setLockClickListener(new LockClickListener() {
                    @Override
                    public void onClick(View view, boolean lock) {
                        if (orientationUtils != null) {
                            //配合下方的onConfigurationChanged
                            orientationUtils.setEnable(!lock);
                        }
                    }
                });
        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(VideoPlayerActivity.this, true, true);
            }
        });
        videoPlayerPresenter = new VideoPlayerPresenter(this, this);
        videoPlayerPresenter.getFirstVideo(id);
    }

    private void initView() {
        video_tab = findViewById(R.id.video_tab);
        video_viewpager = findViewById(R.id.video_viewpager);
        detailPlayer = findViewById(R.id.video_player);
        title_name = findViewById(R.id.title_name);
        back = findViewById(R.id.back);
    }

    @Override
    public void play(String url, String title) {
        if (url != null && url.length() > 0) {
            gsyVideoOption.setUrl(url)
                    .setVideoTitle(title)
                    .build(detailPlayer);
        } else {
            showMsg("视频地址请求失败！");
        }
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }

    @Override
    public void catalogPlay(String path, String title) {
        initViewVideo(path, title);
    }

    private void initViewVideo(String url, String title) {
        detailPlayer.onVideoReset();
        gsyVideoOption
                .setUrl(url)
                .setVideoTitle(title)
                .build(detailPlayer);
        detailPlayer.startPlayLogic();
    }

    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);
    }

    private GSYVideoPlayer getCurPlay() {
        if (detailPlayer.getFullWindowPlayer() != null) {
            return detailPlayer.getFullWindowPlayer();
        }
        return detailPlayer;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            detailPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 当前为横屏
            StatusBarUtil.hideFakeStatusBarView(VideoPlayerActivity.this);
        }
    }

    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        getCurPlay().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    public void onResume() {
        getCurPlay().onVideoResume(false);
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            getCurPlay().release();
        }
        if (orientationUtils != null)
            orientationUtils.releaseListener();
        GSYVideoManager.clearAllDefaultCache(VideoPlayerActivity.this);
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public void getTime() {
        int time = getCurPlay().getCurrentPositionWhenPlaying();
        videoPlayerPresenter.setVtime(time);
    }
}
