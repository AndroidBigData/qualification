package com.zjwam.qualification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.zjwam.qualification.R;
import com.zjwam.qualification.bean.VideoCommentBean;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;

public class VideoCommentAdapter extends ListBaseAdapter<VideoCommentBean.Comment> {
    private LayoutInflater mLayoutInflater;
    private RequestOptions options;

    public VideoCommentAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        options = RequestOptionsUtils.circleTransform();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.comment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        VideoCommentBean.Comment item = mDataList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        GlideImageUtil.setImageView(mContext,item.getPic(),viewHolder.comment_item_tx,options);
        viewHolder.comment_item_nick.setText(item.getNickname());
        viewHolder.comment_item_time.setText(item.getAddtime());
        viewHolder.comment_item_content.setText(item.getContent());
        viewHolder.comment_item_rating.setNumStars(item.getStar());

    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView comment_item_tx;
        private TextView comment_item_nick,comment_item_time,comment_item_content;
        private RatingBar comment_item_rating;
        public ViewHolder(View itemView) {
            super(itemView);
            comment_item_tx = itemView.findViewById(R.id.comment_item_tx);
            comment_item_nick = itemView.findViewById(R.id.comment_item_nick);
            comment_item_rating = itemView.findViewById(R.id.comment_item_rating);
            comment_item_time = itemView.findViewById(R.id.comment_item_time);
            comment_item_content = itemView.findViewById(R.id.comment_item_content);
        }
    }
}
