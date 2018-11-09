package com.zjwam.qualification.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.VideoNoteAdapter;
import com.zjwam.qualification.bean.VideoNoteBean;
import com.zjwam.qualification.custom.ReplayDialog;
import com.zjwam.qualification.presenter.VideoNotePresenter;
import com.zjwam.qualification.presenter.ipresenter.IVideoNotePresenter;
import com.zjwam.qualification.utils.GetVideoTime;
import com.zjwam.qualification.utils.QlftPreference;
import com.zjwam.qualification.utils.Reflex;
import com.zjwam.qualification.view.activity.VideoPlayerActivity;
import com.zjwam.qualification.view.iview.IVideoNoteView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoNoteFragment extends Fragment implements IVideoNoteView {

    private Context context;
    private long vid, id;
    private LRecyclerView note_recyclerview;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private VideoNoteAdapter videoNoteAdapter;
    private boolean isRefresh;
    private int maxItem, mCurrentCounter, page,zanCou,itemPosition;
    private IVideoNotePresenter videoNotePresenter;
    private ImageView note_nodata,zanImg;
    private TextView note_write;
    private GetVideoTime getVideoTime;
    private ReplayDialog replayDialog;
    private TextView zan;
    private View rootView;

    public VideoNoteFragment() {
        // Required empty public constructor
    }

    public static VideoNoteFragment newInstance(Context context, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", String.valueOf(id));
        VideoNoteFragment fragment = new VideoNoteFragment();
        fragment.setArguments(bundle);
        fragment.context = context;
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
        rootView = inflater.inflate(R.layout.fragment_video_note, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getLong("id");
        initView();
        initData();
    }

    private void initData() {
        videoNotePresenter = new VideoNotePresenter(context, this);
        videoNoteAdapter = new VideoNoteAdapter(context);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(videoNoteAdapter);
        note_recyclerview.setAdapter(lRecyclerViewAdapter);
        note_recyclerview.setLayoutManager(new LinearLayoutManager(context));
        note_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        note_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        note_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        note_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mCurrentCounter = 0;
                isRefresh = true;
                videoNotePresenter.getNote(vid, page, isRefresh);
            }
        });
        note_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter < maxItem) {
                    page++;
                    videoNotePresenter.getNote(vid, page, isRefresh);
                } else {
                    note_recyclerview.setNoMore(true);
                }
            }
        });

        note_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVideoTime.getTime();
                replayDialog = new ReplayDialog(getActivity());
                replayDialog.show();
                replayDialog.setOnBtnCommitClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (replayDialog.getContent().trim().length() > 0) {
                            String vtime = getTime(videoNotePresenter.getVtime());
                            videoNotePresenter.writeNote(id, vid, vtime, replayDialog.getContent());
                        } else {
                            if (context instanceof VideoPlayerActivity) {
                                ((VideoPlayerActivity) context).error("请输入内容！");
                            }
                        }
                    }
                });
            }
        });

        videoNoteAdapter.setDianZan(new VideoNoteAdapter.DianZan() {
            @Override
            public void setZan(TextView textView,ImageView imageView,int zanNum,int position) {
                zanImg = imageView;
                zan = textView;
                zanCou = zanNum;
                itemPosition = position;
                zanImg.setEnabled(false);
                videoNotePresenter.dianZan(videoNoteAdapter.getDataList().get(position).getId(),"note");
            }
            @Override
            public void qxZan(TextView textView, ImageView imageView, int zanNum,int position) {
                zanImg = imageView;
                zan = textView;
                zanCou = zanNum;
                itemPosition = position;
                zanImg.setEnabled(false);
                videoNotePresenter.dianZan(videoNoteAdapter.getDataList().get(position).getId(),"note");
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
        note_recyclerview = getActivity().findViewById(R.id.note_recyclerview);
        note_nodata = getActivity().findViewById(R.id.note_nodata);
        note_write = getActivity().findViewById(R.id.note_write);
    }

    @Override
    public void setNote(List<VideoNoteBean.Note> list) {
        maxItem = videoNotePresenter.getCount();
        if (maxItem > 0) {
            videoNoteAdapter.addAll(list);
            mCurrentCounter += list.size();
            note_nodata.setVisibility(View.GONE);
        } else {
            note_nodata.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void refresh() {
        videoNoteAdapter.clear();
    }

    @Override
    public void showMsg(String msg) {
        if (context instanceof VideoPlayerActivity) {
            ((VideoPlayerActivity) context).error(msg);
        }
    }

    @Override
    public void refreshComplete() {
        note_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void dialogDismiss() {
        replayDialog.setContent("");
        replayDialog.dismiss();
        note_recyclerview.refresh();
    }

    @Override
    public void setZan() {
        if (videoNoteAdapter.getDataList().get(itemPosition).getIszan() == 0){
            zan.setText(String.valueOf(videoNoteAdapter.getDataList().get(itemPosition).getZan()+1));
            zan.setTextColor(context.getResources().getColor(R.color.colorAccent));
            zanImg.setImageResource(R.drawable.zan_over);
            videoNoteAdapter.getDataList().get(itemPosition).setIszan(1);
            videoNoteAdapter.getDataList().get(itemPosition).setZan(zanCou+1);
        }else {
            zan.setText(String.valueOf(videoNoteAdapter.getDataList().get(itemPosition).getZan()-1));
            zan.setTextColor(context.getResources().getColor(R.color.note));
            zanImg.setImageResource(R.drawable.zan);
            videoNoteAdapter.getDataList().get(itemPosition).setIszan(0);
            videoNoteAdapter.getDataList().get(itemPosition).setZan(zanCou-1);
        }
    }

    @Override
    public void setEnabled() {
        zanImg.setEnabled(true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (vid != videoNotePresenter.getVid()) {
                vid = videoNotePresenter.getVid();
                note_recyclerview.refresh();
            }
        }
    }
}
