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
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.PersonalMineCommentAdapter;
import com.zjwam.qualification.bean.PersonalMineCommentBean;
import com.zjwam.qualification.presenter.ipresenter.CurriculumPresenter;
import com.zjwam.qualification.presenter.ipresenter.ICurriculumPresenter;
import com.zjwam.qualification.view.iview.ICurriculumView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurriculumFragment extends Fragment implements ICurriculumView{

    private LRecyclerView curriculum_recyclerview;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private Context context;
    private PersonalMineCommentAdapter mineCommentAdapter;
    private boolean isRefresh = true;
    private int page = 1,mCurrentCounter = 0,max_items;
    private ICurriculumPresenter curriculumPresenter;

    public CurriculumFragment() {
        // Required empty public constructor
    }

    public static CurriculumFragment newInstance(Context context) {

        CurriculumFragment fragment = new CurriculumFragment();
        fragment.context = context;
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_curriculum, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mineCommentAdapter = new PersonalMineCommentAdapter(getActivity());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(mineCommentAdapter);
        curriculum_recyclerview.setAdapter(lRecyclerViewAdapter);
        curriculum_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        curriculum_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        curriculum_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        curriculum_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        curriculum_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                page = 1;
                mCurrentCounter = 0;
                curriculumPresenter.getData("885",String.valueOf(page),isRefresh);
            }
        });
        curriculum_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter < max_items) {
                    page++;
                    curriculumPresenter.getData("885",String.valueOf(page),isRefresh);
                } else {
                    curriculum_recyclerview.setNoMore(true);
                }
            }
        });
        curriculum_recyclerview.refresh();
    }

    private void initView() {
        curriculum_recyclerview = getActivity().findViewById(R.id.curriculum_recyclerview);
        curriculumPresenter = new CurriculumPresenter(context,this);
    }

    @Override
    public void initData(List<PersonalMineCommentBean.getCommentItems> list) {
        max_items = curriculumPresenter.maxItems();
        Log.i("---list:",list.toString());
        mineCommentAdapter.addAll(list);
        mCurrentCounter += list.size();
    }

    @Override
    public void clearRecyclerView() {
        mineCommentAdapter.clear();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshComplete() {
        curriculum_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }
}
