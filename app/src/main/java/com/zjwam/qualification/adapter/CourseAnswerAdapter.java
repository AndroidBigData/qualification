package com.zjwam.qualification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.zjwam.qualification.bean.CourseAnswerBean;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;
import com.zjwam.qualification.R;


public class CourseAnswerAdapter extends ListBaseAdapter<CourseAnswerBean.Answer> {
    private LayoutInflater mLayoutInflater;
    private RequestOptions options;

    public CourseAnswerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        options = RequestOptionsUtils.circleTransform();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.mine_answer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final CourseAnswerBean.Answer data = mDataList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        GlideImageUtil.setImageView(mContext,data.getPic(),viewHolder.mine_answer_img,options);
        viewHolder.mine_answer_nickname.setText(data.getNickname());
        viewHolder.mine_answer_time.setText(data.getAddtime());
        viewHolder.mine_answer_title.setText(data.getName());
        viewHolder.mine_answer_content.setText(data.getContent());
        viewHolder.mine_answer_content_nickname.setText(data.getSub_nickname());
        viewHolder.mine_answer_content_time.setText("发布于" + data.getSub_addtime());
        viewHolder.mine_answer_content_content.setText(data.getSub_content());
        GlideImageUtil.setImageView(mContext,data.getSub_pic(),viewHolder.mine_answer_img_ask,options);
        viewHolder.mine_answer_title_bt.setText(data.getVname());
        viewHolder.mine_answer_content_count.setText(String.valueOf(data.getSub_count())+"条回答");
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mine_answer_img,mine_answer_img_ask;
        private TextView mine_answer_nickname, mine_answer_time, mine_answer_title, mine_answer_content_nickname, mine_answer_content_time, mine_answer_content_content, mine_answer_content,
                mine_answer_title_bt,mine_answer_content_count;

        public ViewHolder(View itemView) {
            super(itemView);
            mine_answer_img = itemView.findViewById(R.id.mine_answer_img);
            mine_answer_nickname = itemView.findViewById(R.id.mine_answer_nickname);
            mine_answer_time = itemView.findViewById(R.id.mine_answer_time);
            mine_answer_title = itemView.findViewById(R.id.mine_answer_title);
            mine_answer_content_nickname = itemView.findViewById(R.id.mine_answer_content_nickname);
            mine_answer_content_time = itemView.findViewById(R.id.mine_answer_content_time);
            mine_answer_content_content = itemView.findViewById(R.id.mine_answer_content_content);
            mine_answer_content = itemView.findViewById(R.id.mine_answer_content);
            mine_answer_img_ask = itemView.findViewById(R.id.mine_answer_img_ask);
            mine_answer_title_bt = itemView.findViewById(R.id.mine_answer_title_bt);
            mine_answer_content_count = itemView.findViewById(R.id.mine_answer_content_count);
        }
    }
}
