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

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.VideoCatalogAdapter;
import com.zjwam.qualification.bean.VideoCatalogBean;
import com.zjwam.qualification.presenter.VideoCatalogPresenter;
import com.zjwam.qualification.presenter.ipresenter.IVideoCatalogPresenter;
import com.zjwam.qualification.view.activity.VideoPlayerActivity;
import com.zjwam.qualification.view.iview.IVideoCatalogView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoCatalogFragment extends Fragment implements IVideoCatalogView{

    private Context context;
    private long id;
    private LRecyclerView video_catalog;
    private VideoCatalogAdapter videoCatalogAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private IVideoCatalogPresenter catalogPresenter;
    private int item;
    private FragmentInteraction listterner;

    public interface FragmentInteraction {
        void catalogPlay(String path, String title);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteraction) {
            listterner = (FragmentInteraction) context;
        } else {
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }

    public VideoCatalogFragment() {
        // Required empty public constructor
    }

    public static VideoCatalogFragment newInstance(Context context,long id) {
        Bundle args = new Bundle();
        args.putLong("id",id);
        VideoCatalogFragment fragment = new VideoCatalogFragment();
        fragment.context = context;
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_catelog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getLong("id");
        initView();
        initData();
    }

    private void initData() {
        catalogPresenter = new VideoCatalogPresenter(context,this);
        catalogPresenter.getCataog(id);
    }

    private void initView() {
        video_catalog = getActivity().findViewById(R.id.video_catalog);
    }

    @Override
    public void setAdapter(int buy) {
        videoCatalogAdapter = new VideoCatalogAdapter(getActivity(),String.valueOf(buy));
        lRecyclerViewAdapter = new LRecyclerViewAdapter(videoCatalogAdapter);
        video_catalog.setAdapter(lRecyclerViewAdapter);
        video_catalog.setLayoutManager(new LinearLayoutManager(getActivity()));
        video_catalog.setLoadMoreEnabled(false);
        video_catalog.setPullRefreshEnabled(false);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (item == position){
                    return;
                }else {
                    item = position;
                    catalogPresenter.catalogPlay(item);
                }

            }
        });
    }

    @Override
    public void setCatalog(List<VideoCatalogBean.Catalog> catalog) {
        videoCatalogAdapter.clear();
        videoCatalogAdapter.addAll(catalog);
    }

    @Override
    public void showMsg(String msg) {
        if (context instanceof VideoPlayerActivity){
            ((VideoPlayerActivity) context).error(msg);
        }
    }

    @Override
    public void catalogPlay() {
        videoCatalogAdapter.setThisPosition(item);
        lRecyclerViewAdapter.notifyDataSetChanged();
        listterner.catalogPlay(videoCatalogAdapter.getDataList().get(item).getAddress(),videoCatalogAdapter.getDataList().get(item).getVname());
    }
}
