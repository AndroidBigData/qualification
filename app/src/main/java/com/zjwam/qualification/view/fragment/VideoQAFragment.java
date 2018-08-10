package com.zjwam.qualification.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.VideoQAAdapter;
import com.zjwam.qualification.bean.VideoQABean;
import com.zjwam.qualification.custom.ReplayDialog;
import com.zjwam.qualification.presenter.VideoQAPresenter;
import com.zjwam.qualification.presenter.ipresenter.IVideoQAPresenter;
import com.zjwam.qualification.utils.GetVideoTime;
import com.zjwam.qualification.view.activity.VideoPlayerActivity;
import com.zjwam.qualification.view.activity.VideoQaDetailsActivity;
import com.zjwam.qualification.view.iview.IVideoQAView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoQAFragment extends Fragment implements IVideoQAView{

    private Context context;
    private long id;
    private String vid = "";
    private LRecyclerView qa_recyclerview;
    private VideoQAAdapter videoQAAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private boolean isRefresh;
    private int maxItem, mCurrentCounter,page;
    private IVideoQAPresenter videoQAPresenter;
    private TextView qa_ask;
    private GetVideoTime getVideoTime;
    private ReplayDialog replayDialog;
    private ImageView nodata;

    public VideoQAFragment() {
        // Required empty public constructor
    }

    public static VideoQAFragment newInstance(Context context,long id) {
        Bundle args = new Bundle();
        args.putLong("id",id);
        VideoQAFragment fragment = new VideoQAFragment();
        fragment.context = context;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GetVideoTime) {
            getVideoTime = (GetVideoTime) context;
        } else {
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_qa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getLong("id");
        initView();
        initData();
    }

    private void initData() {
        videoQAPresenter = new VideoQAPresenter(context,this);
        videoQAAdapter = new VideoQAAdapter(context);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(videoQAAdapter);
        qa_recyclerview.setAdapter(lRecyclerViewAdapter);
        qa_recyclerview.setLayoutManager(new LinearLayoutManager(context));
        qa_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        qa_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        qa_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        qa_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isRefresh = true;
                mCurrentCounter = 0;
                videoQAPresenter.getQA(vid,page,isRefresh);
            }
        });
        qa_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter<maxItem){
                    page++;
                    videoQAPresenter.getQA(vid,page,isRefresh);
                }else {
                    qa_recyclerview.setNoMore(true);
                }
            }
        });
        qa_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVideoTime.getTime();
                replayDialog = new ReplayDialog(getActivity());
                replayDialog.show();
                replayDialog.setOnBtnCommitClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (replayDialog.getContent().trim().length() > 0) {
                            String vtime = getTime(videoQAPresenter.getVtime());
                            videoQAPresenter.askQuestion(id, Long.parseLong(vid),vtime,replayDialog.getContent());
                        } else {
                            if (context instanceof VideoPlayerActivity) {
                                ((VideoPlayerActivity) context).error("请输入内容！");
                            }
                        }
                    }
                });
            }
        });
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(videoQAAdapter.getDataList().get(position).getId()));
                startActivity(new Intent(context, VideoQaDetailsActivity.class).putExtras(bundle));
            }
        });
    }

    private String getTime(String time) {
        if (time.length() < 4) {
            return "0";
        } else {
            return time.substring(0, time.length() - 3);
        }
    }

    private void initView() {
        qa_recyclerview = getActivity().findViewById(R.id.qa_recyclerview);
        qa_ask = getActivity().findViewById(R.id.qa_ask);
        nodata = getActivity().findViewById(R.id.qa_nodata);
    }

    @Override
    public void showMsg(String msg) {
        if (context instanceof VideoPlayerActivity){
            ((VideoPlayerActivity) context).error(msg);
        }
    }

    @Override
    public void addData(List<VideoQABean.Answer> list, int count) {
        maxItem = count;
        if (maxItem>0){
            videoQAAdapter.addAll(list);
            mCurrentCounter += list.size();
            nodata.setVisibility(View.GONE);
        }else {
            nodata.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void refreshComplete() {
        qa_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        videoQAAdapter.clear();
    }

    @Override
    public void dialogDismiss() {
        replayDialog.setContent("");
        replayDialog.dismiss();
        qa_recyclerview.refresh();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (!vid.equals(videoQAPresenter.getVid())) {
                vid = videoQAPresenter.getVid();
                qa_recyclerview.refresh();
            }
        }
    }
}
