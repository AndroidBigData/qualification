package com.zjwam.qualification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.zjwam.qualification.R;
import com.zjwam.qualification.bean.CoursesListBean;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;


public class CoursesListAdapter extends ListBaseAdapter<CoursesListBean.classList> {
    private LayoutInflater mLayoutInflater;
    private RequestOptions options;

    public CoursesListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        options = RequestOptionsUtils.roundTransform(6);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.curriculum_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        CoursesListBean.classList item = mDataList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        GlideImageUtil.setImageView(mContext,item.getImg(),viewHolder.curriculum_img,options);
        viewHolder.curriculum_type.setText(item.getType());
        viewHolder.curriculum_name.setText(item.getName().trim());
        viewHolder.curriculum_label.setText(item.getIntro().trim());
        viewHolder.curriculum_learning.setText(item.getNum()+"课时 "+item.getSnum()+"人已学习");
        viewHolder.curriculumg_pro.setProgress((int)(Double.parseDouble(item.getRatio()))*10);
        viewHolder.curriculum_pro_text.setText("已学"+item.getRatio()+"%");
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView curriculum_img;
        private TextView curriculum_type, curriculum_name,curriculum_label,curriculum_learning,curriculum_pro_text;
        private ProgressBar curriculumg_pro;
        public ViewHolder(View itemView) {
            super(itemView);
            curriculum_img = itemView.findViewById(R.id.curriculum_img);
            curriculum_type = itemView.findViewById(R.id.curriculum_type);
            curriculum_name = itemView.findViewById(R.id.curriculum_name);
            curriculum_label = itemView.findViewById(R.id.curriculum_label);
            curriculum_learning = itemView.findViewById(R.id.curriculum_learning);
            curriculum_pro_text = itemView.findViewById(R.id.curriculum_pro_text);
            curriculumg_pro = itemView.findViewById(R.id.curriculumg_pro);
        }
    }
}
