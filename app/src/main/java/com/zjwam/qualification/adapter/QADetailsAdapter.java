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
import com.zjwam.qualification.bean.QADetailsBean;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;

public class QADetailsAdapter extends ListBaseAdapter<QADetailsBean.Sub> {
    private LayoutInflater mLayoutInflater;
    private RequestOptions optionsUtils;
    private DianZan dianZan;

    public QADetailsAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        optionsUtils = RequestOptionsUtils.circleTransform();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.qa_details_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        final QADetailsBean.Sub items = mDataList.get(position);
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.qa_details_title_nickname.setText(items.getNickname());
        viewHolder.qa_details_title_time.setText(items.getAddtime());
        viewHolder.qa_details_zan.setText(String.valueOf(items.getZan()));
        viewHolder.qa_deatils_content.setText(items.getContent());
        GlideImageUtil.setImageView(mContext, items.getPic(), viewHolder.qa_details_title_tx, optionsUtils);
        if (items.getIszan() == 1) {
            viewHolder.qa_details_zan.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            viewHolder.qa_deatils_zan_img.setImageResource(R.drawable.zan_over);
        } else {
            viewHolder.qa_details_zan.setTextColor(mContext.getResources().getColor(R.color.note));
            viewHolder.qa_deatils_zan_img.setImageResource(R.drawable.zan);
        }
        viewHolder.qa_deatils_zan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dianZan.setZan(viewHolder.qa_details_zan, viewHolder.qa_deatils_zan_img, items.getZan(), position);
            }
        });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView qa_details_title_nickname, qa_details_title_time, qa_details_zan, qa_deatils_content;
        private ImageView qa_details_title_tx, qa_deatils_zan_img;

        public ViewHolder(View itemView) {
            super(itemView);
            qa_details_title_nickname = itemView.findViewById(R.id.qa_details_title_nickname);
            qa_details_title_time = itemView.findViewById(R.id.qa_details_title_time);
            qa_details_zan = itemView.findViewById(R.id.qa_details_zan);
            qa_deatils_content = itemView.findViewById(R.id.qa_deatils_content);
            qa_details_title_tx = itemView.findViewById(R.id.qa_details_title_tx);
            qa_deatils_zan_img = itemView.findViewById(R.id.qa_deatils_zan_img);
        }
    }

    public void setDianZan(DianZan dianZan) {
        this.dianZan = dianZan;
    }

    public interface DianZan {
        void setZan(TextView textView, ImageView imageView, int zanNum, int position);
    }
}
