package com.zjwam.qualification.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.MineCommentAdapter;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.bean.MineCommentBean;
import com.zjwam.qualification.presenter.MineCommentPresenter;
import com.zjwam.qualification.presenter.ipresenter.IMineCommentPresenter;
import com.zjwam.qualification.view.iview.IMineCommentView;

import java.util.List;

public class MineCommnetActivity extends BaseActivity implements IMineCommentView{

    private ImageView back,mine_comment_nodata;
    private TextView title;
    private LRecyclerView mine_comment_recyclerview;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private MineCommentAdapter mineCommentAdapter;
    private boolean isRefresh;
    private int page,mCurrentCounter,max_items;
    private IMineCommentPresenter mineCommentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_commnet);
        initView();
        initData();
    }

    private void initData() {
        mineCommentPresenter = new MineCommentPresenter(this,this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("我的评价");
        mineCommentAdapter = new MineCommentAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(mineCommentAdapter);
        mine_comment_recyclerview.setAdapter(lRecyclerViewAdapter);
        mine_comment_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mine_comment_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mine_comment_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        mine_comment_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        mine_comment_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mCurrentCounter = 0;
                isRefresh = true;
                mineCommentPresenter.getComment(String.valueOf(page),isRefresh);
            }
        });
        mine_comment_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter<max_items){
                    page++;
                    mineCommentPresenter.getComment(String.valueOf(page),isRefresh);
                }else {
                    mine_comment_recyclerview.setNoMore(true);
                }
            }
        });
        mine_comment_recyclerview.refresh();
    }

    private void initView() {
        back = findViewById(R.id.back);
        mine_comment_nodata = findViewById(R.id.mine_comment_nodata);
        title = findViewById(R.id.title_name);
        mine_comment_recyclerview = findViewById(R.id.mine_comment_recyclerview);
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }

    @Override
    public void addData(List<MineCommentBean.Comment> list, int count) {
        max_items = count;
        if (max_items>0){
            mineCommentAdapter.addAll(list);
            mCurrentCounter += list.size();
            mine_comment_nodata.setVisibility(View.GONE);
        }else {
            mine_comment_nodata.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void refreshComplete() {
        mine_comment_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        mineCommentAdapter.clear();
    }
}
