package com.zjwam.qualification.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.MineMsgAdapter;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.bean.MineMsgBean;
import com.zjwam.qualification.presenter.MineMsgPresenter;
import com.zjwam.qualification.presenter.ipresenter.IMineMsgPresenter;
import com.zjwam.qualification.view.iview.IMineMSgView;

import java.util.List;

public class MineMsgActivity extends BaseActivity implements IMineMSgView{

    private LRecyclerView msg_recyclerview;
    private ImageView back;
    private TextView title;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private MineMsgAdapter mineMsgAdapter;
    private boolean isRefresh;
    private int maxItems, mCurrentCounter, page;
    private IMineMsgPresenter mineMsgPresenter;
    private int itemPosition;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_msg);
        initView();
        initData();
    }

    private void initData() {
        mineMsgPresenter = new MineMsgPresenter(this,this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("消息通知");
        mineMsgAdapter = new MineMsgAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(mineMsgAdapter);
        msg_recyclerview.setAdapter(lRecyclerViewAdapter);
        msg_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        msg_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        msg_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        msg_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        msg_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mCurrentCounter = 0;
                isRefresh = true;
                mineMsgPresenter.getMsg(String.valueOf(page),isRefresh);
            }
        });
        msg_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter<maxItems){
                    page++;
                    mineMsgPresenter.getMsg(String.valueOf(page),isRefresh);
                }else {
                    msg_recyclerview.setNoMore(true);
                }
            }
        });
        msg_recyclerview.refresh();

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                itemPosition = position;
                bundle = new Bundle();
                bundle.putString("type", String.valueOf(mineMsgAdapter.getDataList().get(itemPosition).getType()));
                bundle.putString("title", String.valueOf(mineMsgAdapter.getDataList().get(itemPosition).getTitle()));
                bundle.putString("time", String.valueOf(mineMsgAdapter.getDataList().get(itemPosition).getAddtime()));
                bundle.putString("content", String.valueOf(mineMsgAdapter.getDataList().get(itemPosition).getContent()));
                bundle.putString("from", String.valueOf(mineMsgAdapter.getDataList().get(itemPosition).getName()));
                if (mineMsgAdapter.getDataList().get(position).getIs_read() == 0){
                    mineMsgPresenter.setRead(String.valueOf(mineMsgAdapter.getDataList().get(position).getId()));
                }else {
                    startActivity(new Intent(getBaseContext(),MineMsgDeatilsActivity.class).putExtras(bundle));
                }
            }
        });
    }

    private void initView() {
        msg_recyclerview = findViewById(R.id.msg_recyclerview);
        back = findViewById(R.id.back);
        title = findViewById(R.id.title_name);
    }

    @Override
    public void setMsg(List<MineMsgBean.Notice> list,int count) {
        maxItems = count;
        mineMsgAdapter.addAll(list);
        mCurrentCounter += list.size();
    }

    @Override
    public void refresh() {
        mineMsgAdapter.clear();
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }

    @Override
    public void refreshComplete() {
        msg_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void read() {
        startActivity(new Intent(getBaseContext(),MineMsgDeatilsActivity.class).putExtras(bundle));
        mineMsgAdapter.getDataList().get(itemPosition).setIs_read(1);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }
}
