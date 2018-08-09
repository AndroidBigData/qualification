package com.zjwam.qualification.view.activity;

import android.content.Intent;
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
import com.zjwam.qualification.presenter.QADetailsPresenter;
import com.zjwam.qualification.presenter.ipresenter.IQADetalisPresenter;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.view.iview.IQADetailsView;

import java.util.List;

public class VideoQaDetailsActivity extends BaseActivity implements IQADetailsView{

    private ImageView back,qa_details_tx,zanImg;
    private TextView title,qa_details_nickname,qa_details_time,qa_details_content,qa_details_answer,qa_details_count,zan;
    private LRecyclerView qa_deatils_recyclerview;
    private String id;
    private int maxItem, mCurrentCounter, page,zanCou,itemPosition;
    private QADetailsAdapter detailsAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private boolean isRefresh;
    private IQADetalisPresenter detalisPresenter;
    private long qid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_qa_details);
        id = getIntent().getExtras().getString("id");
        initView();
        initData();
    }

    private void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("问答详情");
        detailsAdapter = new QADetailsAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(detailsAdapter);
        qa_deatils_recyclerview.setAdapter(lRecyclerViewAdapter);
        qa_deatils_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        qa_deatils_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        qa_deatils_recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        qa_deatils_recyclerview.setFooterViewHint("拼命加载中...", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧");
        detalisPresenter = new QADetailsPresenter(this,this);
        qa_deatils_recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrentCounter = 0;
                page = 1;
                isRefresh = true;
                detalisPresenter.getQADetails(id,page,isRefresh);
            }
        });
        qa_deatils_recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = false;
                if (mCurrentCounter < maxItem){
                    page++;
                    detalisPresenter.getQADetails(id,page,isRefresh);
                }else {
                    qa_deatils_recyclerview.setNoMore(true);
                }
            }
        });
        detailsAdapter.setDianZan(new QADetailsAdapter.DianZan() {
            @Override
            public void setZan(TextView textView, ImageView imageView, int zanNum, int position) {
                zanImg = imageView;
                zan = textView;
                zanCou = zanNum;
                itemPosition = position;
                zanImg.setEnabled(false);
                detalisPresenter.dianZan(detailsAdapter.getDataList().get(position).getId(),"answer");
            }
        });
        qa_details_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(qid));
                startActivity(new Intent(getBaseContext(),AnswerActivity.class).putExtras(bundle));
            }
        });
    }

    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title_name);
        qa_deatils_recyclerview = findViewById(R.id.qa_deatils_recyclerview);
        qa_details_tx = findViewById(R.id.qa_details_tx);
        qa_details_time = findViewById(R.id.qa_details_time);
        qa_details_content = findViewById(R.id.qa_details_content);
        qa_details_answer = findViewById(R.id.qa_details_answer);
        qa_details_count = findViewById(R.id.qa_details_count);
        qa_details_nickname = findViewById(R.id.qa_details_nickname);
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }

    @Override
    public void setDetails(String nickname, String pic, String addtime, String content, int count,long id) {
        GlideImageUtil.setImageView(getBaseContext(),pic,qa_details_tx, RequestOptions.circleCropTransform());
        qa_details_nickname.setText(nickname);
        qa_details_time.setText(addtime);
        qa_details_content.setText(content);
        qa_details_count.setText(String.valueOf(count));
        maxItem = count;
        qid = id;
    }

    @Override
    public void setAnswerList(List<QADetailsBean.Sub> list) {
        detailsAdapter.setDataList(list);
        mCurrentCounter += list.size();
    }

    @Override
    public void refreshComplete() {
        qa_deatils_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void dianZan() {
        if (detailsAdapter.getDataList().get(itemPosition).getIszan() == 0){
            zan.setText(String.valueOf(detailsAdapter.getDataList().get(itemPosition).getZan()+1));
            zan.setTextColor(getResources().getColor(R.color.colorAccent));
            zanImg.setImageResource(R.drawable.zan_over);
            detailsAdapter.getDataList().get(itemPosition).setIszan(1);
            detailsAdapter.getDataList().get(itemPosition).setZan(zanCou+1);
        }else {
            zan.setText(String.valueOf(detailsAdapter.getDataList().get(itemPosition).getZan()-1));
            zan.setTextColor(getResources().getColor(R.color.note));
            zanImg.setImageResource(R.drawable.zan);
            detailsAdapter.getDataList().get(itemPosition).setIszan(0);
            detailsAdapter.getDataList().get(itemPosition).setZan(zanCou-1);
        }
    }

    @Override
    public void setEnabled() {
        zanImg.setEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        qa_deatils_recyclerview.refresh();
    }
}
