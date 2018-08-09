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

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.VideoCommentAdapter;
import com.zjwam.qualification.bean.VideoCommentBean;
import com.zjwam.qualification.custom.ReplayDialog;
import com.zjwam.qualification.presenter.VideoCommentPresenter;
import com.zjwam.qualification.presenter.ipresenter.IVideoCommentPresenter;
import com.zjwam.qualification.view.activity.VideoPlayerActivity;
import com.zjwam.qualification.view.activity.WriteCommentActivity;
import com.zjwam.qualification.view.iview.IVideoCommentView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoCommentFragment extends Fragment implements IVideoCommentView{

    private Context context;
    private long id;
    private TextView comment;
    private LRecyclerView comment_recyclerview;
    private VideoCommentAdapter commentAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private boolean isRefresh;
    private int maxItems, mCurrentCounter, page;
    private IVideoCommentPresenter commentPresenter;
    private ImageView comment_nodata;

    public VideoCommentFragment() {
        // Required empty public constructor
    }

    public static VideoCommentFragment newInstance(Context context, long id) {
        Bundle args = new Bundle();
        args.putLong("id",id);
        VideoCommentFragment fragment = new VideoCommentFragment();
        fragment.context = context;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getLong("id");
        initView();
        initData();
    }

    private void initData() {
        commentPresenter = new VideoCommentPresenter(context,this);
        commentAdapter = new VideoCommentAdapter(context);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(commentAdapter);
        comment_recyclerview.setAdapter(lRecyclerViewAdapter);
        comment_recyclerview.setLayoutManager(new LinearLayoutManager(context));
        comment_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        comment_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        comment_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        comment_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isRefresh = true;
                mCurrentCounter = 0;
                commentPresenter.getComment(String.valueOf(id),page,isRefresh);
            }
        });
        comment_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter < maxItems){
                    page ++;
                    commentPresenter.getComment(String.valueOf(id),page,isRefresh);
                }else {
                    comment_recyclerview.setNoMore(true);
                }
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(id));
                startActivity(new Intent(getActivity(), WriteCommentActivity.class).putExtras(bundle));
            }
        });
    }

    private void initView() {
        comment = getActivity().findViewById(R.id.comment);
        comment_recyclerview = getActivity().findViewById(R.id.comment_recyclerview);
        comment_nodata = getActivity().findViewById(R.id.comment_nodata);
    }

    @Override
    public void showMsg(String msg) {
        if (context instanceof VideoPlayerActivity){
            ((VideoPlayerActivity) context).error(msg);
        }
    }

    @Override
    public void getComment(List<VideoCommentBean.Comment> list,int maxItem) {
        maxItems = maxItem;
        if (maxItems>0){
            commentAdapter.addAll(list);
            mCurrentCounter += list.size();
            comment_nodata.setVisibility(View.GONE);
        }else {
            comment_nodata.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void refreshComplete() {
        comment_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        commentAdapter.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        comment_recyclerview.refresh();
    }
}
