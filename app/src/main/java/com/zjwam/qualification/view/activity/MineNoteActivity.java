package com.zjwam.qualification.view.activity;

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
import com.zjwam.qualification.adapter.MineNoteAdapter;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.bean.MineNoteBean;
import com.zjwam.qualification.presenter.MineNotePresenter;
import com.zjwam.qualification.presenter.ipresenter.IMineNotePresenter;
import com.zjwam.qualification.view.iview.IMineNoteView;

import java.util.List;

public class MineNoteActivity extends BaseActivity implements IMineNoteView{

    private ImageView back,mine_notebook_nodata;
    private TextView title;
    private LRecyclerView mine_notebook_recyclerview;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private MineNoteAdapter mineNoteAdapter;
    private boolean isRefresh,isChecked = true;
    private int page,mCurrentCounter,max_items,deletePosition;
    private IMineNotePresenter mineNotePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_note);
        initView();
        initData();
    }

    private void initData() {
        mineNotePresenter = new MineNotePresenter(this,this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("我的笔记");
        mineNoteAdapter = new MineNoteAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(mineNoteAdapter);
        mine_notebook_recyclerview.setAdapter(lRecyclerViewAdapter);
        mine_notebook_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mine_notebook_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mine_notebook_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        mine_notebook_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        mine_notebook_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mCurrentCounter = 0;
                isRefresh = true;
                mineNotePresenter.getMineNote(String.valueOf(page),isRefresh);
            }
        });
        mine_notebook_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter < max_items){
                    page++;
                    mineNotePresenter.getMineNote(String.valueOf(page),isRefresh);
                }else {
                    mine_notebook_recyclerview.setNoMore(true);
                }
            }
        });
        mine_notebook_recyclerview.refresh();
        mineNoteAdapter.onDeleteNoteBook(new MineNoteAdapter.deleteNoteBook() {
            @Override
            public void OnDeleteNoteBook(long noteId, int position) {
                if (isChecked){
                    deletePosition = position;
                    mineNotePresenter.deleNote(String.valueOf(noteId));
                    isChecked = false;
                }
            }
        });
    }

    private void initView() {
        back = findViewById(R.id.back);
        mine_notebook_nodata = findViewById(R.id.mine_notebook_nodata);
        title = findViewById(R.id.title_name);
        mine_notebook_recyclerview = findViewById(R.id.mine_notebook_recyclerview);
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }

    @Override
    public void addData(List<MineNoteBean.Note> list, int count) {
        max_items = count;
        if (max_items > 0){
            mineNoteAdapter.addAll(list);
            mCurrentCounter += list.size();
            mine_notebook_nodata.setVisibility(View.GONE);
        }else {
            mine_notebook_nodata.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void refreshComplete() {
        mine_notebook_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        mineNoteAdapter.clear();
    }

    @Override
    public void deleteSuccess() {
        mineNoteAdapter.remove(deletePosition);
    }

    @Override
    public void deleteFinish() {
        isChecked = true;
    }
}
