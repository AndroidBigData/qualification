package com.zjwam.qualification.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.QADetailsAdapter;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.bean.QADetailsBean;
import com.zjwam.qualification.presenter.MineAskDetailsPresenter;
import com.zjwam.qualification.presenter.ipresenter.IMineAskDetailsPresenter;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;
import com.zjwam.qualification.view.iview.IMineAskDetailsView;

import java.util.List;

public class MineAskDeatilsActivity extends BaseActivity implements IMineAskDetailsView {

    private String id;
    private TextView title, ask_details_nickname, ask_details_time, ask_details_title, ask_details_title_bt, ask_details_content, ask_details_count,zan_text;
    private ImageView back, ask_details_tx,zan_img;
    private LRecyclerView ask_details_recyclerview;
    private QADetailsAdapter detailsAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private boolean isRefresh;
    private int page, mCurrentCounter, max_items,zan_num,zan_position;
    private IMineAskDetailsPresenter mineAskDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mime_ask_deatils);
        id = getIntent().getExtras().getString("id");
        initView();
        initData();
    }

    private void initData() {
        mineAskDetailsPresenter = new MineAskDetailsPresenter(this,this);
        title.setText("问答详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        detailsAdapter = new QADetailsAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(detailsAdapter);
        ask_details_recyclerview.setAdapter(lRecyclerViewAdapter);
        ask_details_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        ask_details_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        ask_details_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        ask_details_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        ask_details_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isRefresh = true;
                mCurrentCounter = 0;
                mineAskDetailsPresenter.getAskDetails(id, String.valueOf(page),isRefresh);
            }
        });
        ask_details_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter < max_items) {
                    page++;
                    mineAskDetailsPresenter.getAskDetails(id, String.valueOf(page),isRefresh);
                } else {
                    ask_details_recyclerview.setNoMore(true);
                }
            }
        });
        ask_details_recyclerview.refresh();
        detailsAdapter.setDianZan(new QADetailsAdapter.DianZan() {
            @Override
            public void setZan(TextView textView, ImageView imageView, int zanNum, int position) {
                zan_text = textView;
                zan_img = imageView;
                zan_num = zanNum;
                zan_position = position;
                zan_img.setEnabled(false);
                mineAskDetailsPresenter.setZan(String.valueOf(detailsAdapter.getDataList().get(position).getId()),"answer");
            }
        });
    }

    private void initView() {
        title = findViewById(R.id.title_name);
        ask_details_nickname = findViewById(R.id.ask_details_nickname);
        ask_details_time = findViewById(R.id.ask_details_time);
        ask_details_title = findViewById(R.id.ask_details_title);
        ask_details_title_bt = findViewById(R.id.ask_details_title_bt);
        ask_details_content = findViewById(R.id.ask_details_content);
        ask_details_count = findViewById(R.id.ask_details_count);
        back = findViewById(R.id.back);
        ask_details_tx = findViewById(R.id.ask_details_tx);
        ask_details_recyclerview = findViewById(R.id.ask_details_recyclerview);
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }

    @Override
    public void setDetails(String nickname, String pic, String addtime, String name, String vname, String content, int count,String id) {
        ask_details_nickname.setText(nickname);
        GlideImageUtil.setImageView(getBaseContext(), pic, ask_details_tx, RequestOptions.circleCropTransform());
        ask_details_time.setText(addtime);
        ask_details_title.setText(name);
        ask_details_title_bt.setText(vname);
        ask_details_content.setText(content);
        ask_details_count.setText(String.valueOf(count));
        max_items = count;
    }

    @Override
    public void setAnswerList(List<QADetailsBean.Sub> list) {
        detailsAdapter.addAll(list);
        mCurrentCounter += list.size();
    }

    @Override
    public void refreshComplete() {
        ask_details_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void dianZan() {
        if (detailsAdapter.getDataList().get(zan_position).getIszan() == 0){
            zan_text.setText(String.valueOf(detailsAdapter.getDataList().get(zan_position).getZan()+1));
            zan_text.setTextColor(getResources().getColor(R.color.colorAccent));
            zan_img.setImageResource(R.drawable.zan_over);
            detailsAdapter.getDataList().get(zan_position).setIszan(1);
            detailsAdapter.getDataList().get(zan_position).setZan(zan_num+1);
        }else {
            zan_text.setText(String.valueOf(detailsAdapter.getDataList().get(zan_position).getZan()-1));
            zan_text.setTextColor(getResources().getColor(R.color.note));
            zan_img.setImageResource(R.drawable.zan);
            detailsAdapter.getDataList().get(zan_position).setIszan(0);
            detailsAdapter.getDataList().get(zan_position).setZan(zan_num-1);
        }
    }

    @Override
    public void setEnabled() {
        zan_img.setEnabled(true);
    }

    @Override
    public void refresh() {
        detailsAdapter.clear();
    }
}
