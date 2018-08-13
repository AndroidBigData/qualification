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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.MainRankAdapter;
import com.zjwam.qualification.bean.RankBean;
import com.zjwam.qualification.custom.CustomLinearLayoutManager;
import com.zjwam.qualification.presenter.HomepagePresenter;
import com.zjwam.qualification.presenter.ipresenter.IHomepagePresenter;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;
import com.zjwam.qualification.view.iview.IHomePageView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements IHomePageView{
    private Context context;
    private ImageView main_logo,main_img;
    private LRecyclerView rank_recyclerview;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private MainRankAdapter mainRankAdapter;
    private View headerView;
    private IHomepagePresenter homepagePresenter;
    private CustomLinearLayoutManager linearLayoutManager;
    private LinearLayout rank_down;

    public HomePageFragment() {
        // Required empty public constructor
    }

    public static HomePageFragment newInstance(Context context) {

        HomePageFragment fragment = new HomePageFragment();
        fragment.context = context;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        homepagePresenter = new HomepagePresenter(context,this);
        homepagePresenter.getRank();
    }

    private void initView() {
        main_logo = getActivity().findViewById(R.id.main_logo);
        rank_recyclerview = getActivity().findViewById(R.id.rank_recyclerview);
        rank_down = getActivity().findViewById(R.id.rank_down);
        mainRankAdapter = new MainRankAdapter(context);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(mainRankAdapter);
        rank_recyclerview.setAdapter(lRecyclerViewAdapter);
        linearLayoutManager = new CustomLinearLayoutManager(getActivity());
        linearLayoutManager.setScrollEnabled(false);
        rank_recyclerview.setLayoutManager(linearLayoutManager);
        rank_recyclerview.setLoadMoreEnabled(false);
        rank_recyclerview.setPullRefreshEnabled(false);
        headerView = LayoutInflater.from(context).inflate(R.layout.main_fragment_header,(ViewGroup)getActivity().findViewById(android.R.id.content),false);
        main_img = headerView.findViewById(R.id.main_img);
        lRecyclerViewAdapter.addHeaderView(headerView);
        rank_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutManager.setScrollEnabled(true);
                rank_down.setVisibility(View.GONE);
                rank_recyclerview.scrollToPosition(mainRankAdapter.getItemCount()-1);
            }
        });
    }

    @Override
    public void setMsg(RankBean rankBean) {
        String logo = rankBean.getLogo();
        List<RankBean.Rank> datas = rankBean.getRank();
        GlideImageUtil.setImageView(context,logo,main_logo, RequestOptionsUtils.commonTransform());
        GlideImageUtil.setImageView(context,rankBean.getPic(),main_img, RequestOptionsUtils.commonTransform());
        mainRankAdapter.addAll(datas);
    }

    @Override
    public void errorMsg(String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void httpOver() {
        rank_recyclerview.refreshComplete(11);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }
}
