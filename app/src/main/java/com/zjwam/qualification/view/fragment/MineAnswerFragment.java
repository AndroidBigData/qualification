package com.zjwam.qualification.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.CourseAnswerAdapter;
import com.zjwam.qualification.bean.CourseAnswerBean;
import com.zjwam.qualification.presenter.MineAnswerPresenter;
import com.zjwam.qualification.presenter.ipresenter.IMineAnswerPresenter;
import com.zjwam.qualification.view.activity.MineQAActivity;
import com.zjwam.qualification.view.iview.IMineAnswerView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineAnswerFragment extends Fragment implements IMineAnswerView{
    private Context context;
    private LRecyclerView mine_answer_recyclerview;
    private ImageView mine_answer_nodata;
    private CourseAnswerAdapter courseAnswerAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private boolean isRefresh;
    private int page,mCurrentCounter,max_items;
    private IMineAnswerPresenter mineAnswerPresenter;
    public MineAnswerFragment() {
        // Required empty public constructor
    }

    public static MineAnswerFragment newInstance(Context context) {
        MineAnswerFragment fragment = new MineAnswerFragment();
        fragment.context = context;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine_answer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mineAnswerPresenter = new MineAnswerPresenter(context,this);
        courseAnswerAdapter = new CourseAnswerAdapter(context);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(courseAnswerAdapter);
        mine_answer_recyclerview.setAdapter(lRecyclerViewAdapter);
        mine_answer_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mine_answer_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mine_answer_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        mine_answer_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        mine_answer_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mCurrentCounter = 0;
                isRefresh = true;
                mineAnswerPresenter.getAnswer(String.valueOf(page),isRefresh);
            }
        });
        mine_answer_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter<max_items){
                    page++;
                    mineAnswerPresenter.getAnswer(String.valueOf(page),isRefresh);
                }else {
                    mine_answer_recyclerview.setNoMore(true);
                }
            }
        });
        mine_answer_recyclerview.refresh();
    }

    private void initView() {
        mine_answer_recyclerview = getActivity().findViewById(R.id.mine_answer_recyclerview);
        mine_answer_nodata = getActivity().findViewById(R.id.mine_answer_nodata);
    }

    @Override
    public void showMsg(String msg) {
        if (context instanceof MineQAActivity){
            ((MineQAActivity) context).error(msg);
        }
    }

    @Override
    public void addData(List<CourseAnswerBean.Answer> list, int count) {
        max_items = count;
        if (count>0){
            courseAnswerAdapter.addAll(list);
            mCurrentCounter += list.size();
            mine_answer_nodata.setVisibility(View.GONE);
        }else {
            mine_answer_nodata.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void refreshComplete() {
        mine_answer_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        courseAnswerAdapter.clear();
    }
}
