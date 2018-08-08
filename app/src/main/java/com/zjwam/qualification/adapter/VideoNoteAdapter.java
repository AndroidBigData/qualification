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
import com.zjwam.qualification.bean.VideoNoteBean;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;

public class VideoNoteAdapter extends ListBaseAdapter<VideoNoteBean.Note> {
    private LayoutInflater mLayoutInflater;
    private RequestOptions optionsUtils;
    private DianZan dianZan;

    public VideoNoteAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        optionsUtils = RequestOptionsUtils.circleTransform();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.note_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        final VideoNoteBean.Note items = mDataList.get(position);
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.note_nickname.setText(items.getNickname());
        viewHolder.note_time.setText(items.getAddtime());
        viewHolder.note_content.setText(items.getNote());
        viewHolder.note_zan.setText(String.valueOf(items.getZan()));
        GlideImageUtil.setImageView(mContext, items.getPic(), viewHolder.note_tx, optionsUtils);
        if (items.getIsmy() == 1) {
            viewHolder.note_mine.setVisibility(View.VISIBLE);
        } else {
            viewHolder.note_mine.setVisibility(View.GONE);
        }
        if (items.getIszan() == 1) {
            viewHolder.note_zan.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            viewHolder.note_zan_img.setImageResource(R.drawable.zan_over);
        } else {
            viewHolder.note_zan.setTextColor(mContext.getResources().getColor(R.color.note));
            viewHolder.note_zan_img.setImageResource(R.drawable.zan);
        }
        viewHolder.note_zan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (items.getIszan() == 1) {
//                    viewHolder.note_zan.setTextColor(mContext.getResources().getColor(R.color.note));
//                    viewHolder.note_zan_img.setImageResource(R.drawable.zan);
//                    viewHolder.note_zan.setText(String.valueOf(items.getZan() - 1));
                    //请求网络
                    dianZan.qxZan(viewHolder.note_zan,viewHolder.note_zan_img,items.getZan(),position);
                } else {
//                    viewHolder.note_zan.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
//                    viewHolder.note_zan_img.setImageResource(R.drawable.zan_over);
//                    viewHolder.note_zan.setText(String.valueOf(items.getZan() + 1));
                    //请求网络
                    dianZan.setZan(viewHolder.note_zan,viewHolder.note_zan_img,items.getZan(),position);
                }

            }
        });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView note_nickname, note_mine, note_time, note_content, note_zan;
        private ImageView note_tx, note_zan_img;

        public ViewHolder(View itemView) {
            super(itemView);
            note_nickname = itemView.findViewById(R.id.note_nickname);
            note_mine = itemView.findViewById(R.id.note_mine);
            note_time = itemView.findViewById(R.id.note_time);
            note_content = itemView.findViewById(R.id.note_content);
            note_zan = itemView.findViewById(R.id.note_zan);
            note_tx = itemView.findViewById(R.id.note_tx);
            note_zan_img = itemView.findViewById(R.id.note_zan_img);
        }
    }

    public void setDianZan(DianZan dianZan) {
        this.dianZan = dianZan;
    }

    public interface DianZan {
        void setZan(TextView textView,ImageView imageView,int zanNum,int position);
        void qxZan(TextView textView,ImageView imageView,int zanNum,int podition);
    }
}
