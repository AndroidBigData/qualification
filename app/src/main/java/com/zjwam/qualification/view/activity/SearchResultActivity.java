package com.zjwam.qualification.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.CoursesListAdapter;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.bean.CoursesListBean;
import com.zjwam.qualification.presenter.SearchResultPresenter;
import com.zjwam.qualification.presenter.ipresenter.ISearchResultPresenter;
import com.zjwam.qualification.view.iview.ISearchResultView;

import java.util.List;

public class SearchResultActivity extends BaseActivity implements ISearchResultView {

    private ImageView back,search_result_nodata;
    private TextView title;
    private LRecyclerView search_result_recyclerview;
    private String content;
    private CoursesListAdapter coursesListAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private boolean isRefresh = true;
    private int page = 1,mCurrentCounter = 0,max_items;
    private ISearchResultPresenter resultPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        content = getIntent().getExtras().getString("content");
        initView();
        initData();
    }

    private void initData() {
        title.setText(content);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        resultPresenter = new SearchResultPresenter(this,this);
        coursesListAdapter = new CoursesListAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(coursesListAdapter);
        search_result_recyclerview.setAdapter(lRecyclerViewAdapter);
        search_result_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        search_result_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        search_result_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        search_result_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        search_result_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                mCurrentCounter = 0;
                page = 1;
                resultPresenter.getSearch(content,String.valueOf(page),isRefresh);
            }
        });
        search_result_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter<max_items){
                    page++;
                    resultPresenter.getSearch(content,String.valueOf(page),isRefresh);
                }else {
                    search_result_recyclerview.setNoMore(true);
                }
            }
        });
        search_result_recyclerview.refresh();
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                long id = coursesListAdapter.getDataList().get(position).getId();
                String name = coursesListAdapter.getDataList().get(position).getName();
                Bundle bundle = new Bundle();
                bundle.putLong("id",id);
                bundle.putString("name",name);
                startActivity(new Intent(getBaseContext(), VideoPlayerActivity.class).putExtras(bundle));
            }
        });
    }

    private void initView() {
        back = findViewById(R.id.back);
        search_result_nodata = findViewById(R.id.search_result_nodata);
        title = findViewById(R.id.title_name);
        search_result_recyclerview = findViewById(R.id.search_result_recyclerview);
    }

    @Override
    public void initData(List<CoursesListBean.classList> list,int count) {
        max_items = count;
        if (max_items>0){
            coursesListAdapter.addAll(list);
            mCurrentCounter += list.size();
            search_result_nodata.setVisibility(View.GONE);
        }else {
            search_result_nodata.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void clearRecyclerView() {
        coursesListAdapter.clear();
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }

    @Override
    public void refreshComplete() {
        search_result_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }
}
