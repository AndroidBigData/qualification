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

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.CourseAskAdapter;
import com.zjwam.qualification.bean.CourseAskBean;
import com.zjwam.qualification.presenter.MineAskPresenter;
import com.zjwam.qualification.presenter.ipresenter.IMineAskPresenter;
import com.zjwam.qualification.view.activity.MineAskDeatilsActivity;
import com.zjwam.qualification.view.activity.MineQAActivity;
import com.zjwam.qualification.view.iview.IMineAskView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineAskFragment extends Fragment implements IMineAskView{
    private Context context;
    private ImageView mineask_nodata;
    private LRecyclerView mineask_recyclerview;
    private CourseAskAdapter courseAskAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private boolean isRefresh;
    private int page,mCurrentCounter,max_items;
    private IMineAskPresenter mineAskPresenter;

    public MineAskFragment() {
        // Required empty public constructor
    }

    public static MineAskFragment newInstance(Context context) {

        MineAskFragment fragment = new MineAskFragment();
        fragment.context = context;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine_ask, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mineAskPresenter = new MineAskPresenter(context,this);
        courseAskAdapter = new CourseAskAdapter(context);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(courseAskAdapter);
        mineask_recyclerview.setAdapter(lRecyclerViewAdapter);
        mineask_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mineask_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mineask_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        mineask_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        mineask_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                page = 1;
                mCurrentCounter = 0;
                mineAskPresenter.getAsk(String.valueOf(page),isRefresh);
            }
        });
        mineask_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter<max_items){
                    page++;
                    mineAskPresenter.getAsk(String.valueOf(page),isRefresh);
                }else {
                    mineask_recyclerview.setNoMore(true);
                }
            }
        });
        mineask_recyclerview.refresh();
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(courseAskAdapter.getDataList().get(position).getId()));
                startActivity(new Intent(getActivity(), MineAskDeatilsActivity.class).putExtras(bundle));
            }
        });
    }

    private void initView() {
        mineask_nodata = getActivity().findViewById(R.id.mineask_nodata);
        mineask_recyclerview = getActivity().findViewById(R.id.mineask_recyclerview);
    }

    @Override
    public void showMsg(String msg) {
        if (context instanceof MineQAActivity){
            ((MineQAActivity) context).error(msg);
        }
    }

    @Override
    public void addData(List<CourseAskBean.Answer> list, int count) {
        max_items = count;
        if (max_items>0){
            courseAskAdapter.addAll(list);
            mCurrentCounter += list.size();
            mineask_nodata.setVisibility(View.GONE);
        }else {
            mineask_nodata.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void refreshComplete() {
        mineask_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        courseAskAdapter.clear();
    }
}
