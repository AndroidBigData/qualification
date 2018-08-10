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
import com.zjwam.qualification.bean.CourseAskBean;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;


public class CourseAskAdapter extends ListBaseAdapter<CourseAskBean.Answer> {
    private LayoutInflater mLayoutInflater;
    private RequestOptions options;

    public CourseAskAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        options = RequestOptionsUtils.circleTransform();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.mine_ask_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final CourseAskBean.Answer data = mDataList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        GlideImageUtil.setImageView(mContext, data.getPic(), viewHolder.mine_ask_img, options);
        viewHolder.mine_ask_nickname.setText(data.getNickname());
        viewHolder.mine_ask_time.setText(data.getAddtime());
        viewHolder.mine_ask_title.setText(data.getName());
        viewHolder.mine_ask_content.setText(data.getContent());
        viewHolder.mine_ask_count.setText(String.valueOf(data.getCount()));
        viewHolder.mine_ask_new.setText("最新回答:" + data.getSub_new());
        viewHolder.mine_ask_title_bt.setText(data.getVname());
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mine_ask_img;
        private TextView mine_ask_nickname, mine_ask_time, mine_ask_title, mine_ask_content, mine_ask_count, mine_ask_new, mine_ask_title_bt;

        public ViewHolder(View itemView) {
            super(itemView);
            mine_ask_img = itemView.findViewById(R.id.mine_ask_img);
            mine_ask_nickname = itemView.findViewById(R.id.mine_ask_nickname);
            mine_ask_time = itemView.findViewById(R.id.mine_ask_time);
            mine_ask_title = itemView.findViewById(R.id.mine_ask_title);
            mine_ask_content = itemView.findViewById(R.id.mine_ask_content);
            mine_ask_count = itemView.findViewById(R.id.mine_ask_count);
            mine_ask_new = itemView.findViewById(R.id.mine_ask_new);
            mine_ask_title_bt = itemView.findViewById(R.id.mine_ask_title_bt);
        }
    }
}