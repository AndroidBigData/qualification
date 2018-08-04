package com.zjwam.qualification.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.CoursesListAdapter;
import com.zjwam.qualification.bean.ClassificationBean;
import com.zjwam.qualification.bean.CoursesListBean;
import com.zjwam.qualification.presenter.CurriculumPresenter;
import com.zjwam.qualification.presenter.ipresenter.ICurriculumPresenter;
import com.zjwam.qualification.view.activity.VideoPlayerActivity;
import com.zjwam.qualification.view.iview.ICurriculumView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurriculumFragment extends Fragment implements ICurriculumView{

    private LRecyclerView curriculum_recyclerview;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private Context context;
    private CoursesListAdapter coursesListAdapter;
    private boolean isRefresh = true;
    private int page = 1,mCurrentCounter = 0,max_items;
    private ICurriculumPresenter curriculumPresenter;
    private TabLayout curriculum_tab;
    private long cid = 0;
    private List<ClassificationBean> classification;
    private ImageView curriculum_nodata;

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

        curriculum_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (coursesListAdapter != null){
                    coursesListAdapter.clear();
                    cid = classification.get(tab.getPosition()).getId();
                    curriculum_recyclerview.refresh();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        curriculumPresenter.getClassification();
//        Reflex.setReflex(curriculum_tab,66);
        coursesListAdapter = new CoursesListAdapter(getActivity());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(coursesListAdapter);
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
                curriculumPresenter.getData(String.valueOf(cid),String.valueOf(page),isRefresh);
            }
        });
        curriculum_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter < max_items) {
                    page++;
                    curriculumPresenter.getData(String.valueOf(cid),String.valueOf(page),isRefresh);
                } else {
                    curriculum_recyclerview.setNoMore(true);
                }
            }
        });

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), VideoPlayerActivity.class));
            }
        });

    }

    private void initView() {
        curriculum_recyclerview = getActivity().findViewById(R.id.curriculum_recyclerview);
        curriculum_tab = getActivity().findViewById(R.id.curriculum_tab);
        curriculum_nodata = getActivity().findViewById(R.id.curriculum_nodata);
        curriculumPresenter = new CurriculumPresenter(context,this);
    }

    @Override
    public void initData(List<CoursesListBean.classList> list) {
        max_items = curriculumPresenter.maxItems();
        curriculum_nodata.setVisibility(View.GONE);
        coursesListAdapter.addAll(list);
        mCurrentCounter += list.size();
    }

    @Override
    public void clearRecyclerView() {
        coursesListAdapter.clear();
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

    @Override
    public void setClassification(List<ClassificationBean> classification) {
        this.classification = classification;
        for (int i=0;i<classification.size();i++){
            curriculum_tab.addTab(curriculum_tab.newTab().setText(classification.get(i).getName()));
        }
        cid = classification.get(0).getId();
        curriculum_recyclerview.refresh();
    }

    @Override
    public void setNoData() {
        curriculum_nodata.setVisibility(View.VISIBLE);
    }
}
