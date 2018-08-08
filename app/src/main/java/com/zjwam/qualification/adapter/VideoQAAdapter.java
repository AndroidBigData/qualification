package com.zjwam.qualification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.zjwam.qualification.R;
import com.zjwam.qualification.bean.VideoQABean;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;

public class VideoQAAdapter extends ListBaseAdapter<VideoQABean.Answer> {
    private LayoutInflater mLayoutInflater;
    private RequestOptions optionsUtils;

    public VideoQAAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        optionsUtils = RequestOptionsUtils.circleTransform();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.qa_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        final VideoQABean.Answer items = mDataList.get(position);
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.qa_nickname.setText(items.getNickname());
        viewHolder.qa_time.setText(items.getAddtime());
        viewHolder.qa_content.setText(items.getContent());
        viewHolder.qa_new_answer.setText("最新回答："+items.getSub_new());
        viewHolder.qa_count.setText(String.valueOf(items.getCount()));
        GlideImageUtil.setImageView(mContext, items.getPic(), viewHolder.qa_tx, optionsUtils);

    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView qa_nickname, qa_time, qa_content, qa_new_answer, qa_count;
        private ImageView qa_tx;

        public ViewHolder(View itemView) {
            super(itemView);
            qa_nickname = itemView.findViewById(R.id.qa_nickname);
            qa_time = itemView.findViewById(R.id.qa_time);
            qa_content = itemView.findViewById(R.id.qa_content);
            qa_new_answer = itemView.findViewById(R.id.qa_new_answer);
            qa_count = itemView.findViewById(R.id.qa_count);
            qa_tx = itemView.findViewById(R.id.qa_tx);
        }
    }
}
