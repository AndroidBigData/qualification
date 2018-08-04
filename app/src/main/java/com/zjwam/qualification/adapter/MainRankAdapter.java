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
import com.zjwam.qualification.bean.RankBean;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;

public class MainRankAdapter extends ListBaseAdapter<RankBean.Rank> {
    private LayoutInflater mLayoutInflater;
    private RequestOptions options;

    public MainRankAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        options = RequestOptionsUtils.circleTransform();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == 0){
            itemView = mLayoutInflater.inflate(R.layout.main_item_type0,parent,false);
            viewHolder = new MineRankViewHolder(itemView);
        }else if (viewType == 1 || viewType == 2){
            itemView = mLayoutInflater.inflate(R.layout.main_item_type1,parent,false);
            viewHolder = new OtherRankViewHolder(itemView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final RankBean.Rank data = mDataList.get(position);
        if (holder instanceof MineRankViewHolder){
            MineRankViewHolder viewHolder = (MineRankViewHolder) holder;
            GlideImageUtil.setImageView(mContext,data.getPic(),viewHolder.mine_rank_tx,options);
            if (data.getRanking() == 0){
                viewHolder.mine_rank.setText("暂无");
            }else {
                viewHolder.mine_rank.setText(String.valueOf(data.getRanking()));
            }
            viewHolder.mine_rank_name.setText(data.getNickname());
            viewHolder.mine_rank_gy.setText(data.getSign());
            viewHolder.mine_rank_num.setText(String.valueOf(data.getNum()));
        }else if (holder instanceof OtherRankViewHolder){
            OtherRankViewHolder viewHolder = (OtherRankViewHolder) holder;
            GlideImageUtil.setImageView(mContext,data.getPic(),viewHolder.main_rank_tx,options);
            viewHolder.main_rank_name.setText(data.getNickname());
            viewHolder.main_rank_gy.setText(data.getSign());
            viewHolder.main_rank_num.setText(String.valueOf(data.getNum()));
            if (position == 1){
                viewHolder.main_rank_img.setImageResource(R.drawable.rank1);
                viewHolder.main_rank_noimg.setVisibility(View.GONE);
            }else if (position == 2){
                viewHolder.main_rank_img.setImageResource(R.drawable.rank2);
                viewHolder.main_rank_noimg.setVisibility(View.GONE);
            }else if (position == 3){
                viewHolder.main_rank_img.setImageResource(R.drawable.rank3);
                viewHolder.main_rank_noimg.setVisibility(View.GONE);
            }else {
                viewHolder.main_rank_noimg.setText(String.valueOf(position));
                viewHolder.main_rank_img.setVisibility(View.GONE);
            }

        }
    }

    private class MineRankViewHolder extends RecyclerView.ViewHolder{
        private ImageView mine_rank_tx;
        private TextView mine_rank,mine_rank_name,mine_rank_gy,mine_rank_num;
        public MineRankViewHolder(View itemView) {
            super(itemView);
            mine_rank_tx = itemView.findViewById(R.id.mine_rank_tx);
            mine_rank = itemView.findViewById(R.id.mine_rank);
            mine_rank_name = itemView.findViewById(R.id.mine_rank_name);
            mine_rank_gy = itemView.findViewById(R.id.mine_rank_gy);
            mine_rank_num = itemView.findViewById(R.id.mine_rank_num);;
        }
    }
    private class OtherRankViewHolder extends RecyclerView.ViewHolder{
        private ImageView main_rank_img,main_rank_tx;
        private TextView main_rank_name,main_rank_gy,main_rank_num,main_rank_noimg;
        public OtherRankViewHolder(View itemView) {
            super(itemView);
            main_rank_img = itemView.findViewById(R.id.main_rank_img);
            main_rank_tx = itemView.findViewById(R.id.main_rank_tx);
            main_rank_name = itemView.findViewById(R.id.main_rank_name);
            main_rank_gy = itemView.findViewById(R.id.main_rank_gy);
            main_rank_num = itemView.findViewById(R.id.main_rank_num);
            main_rank_noimg = itemView.findViewById(R.id.main_rank_noimg);
        }
    }
}
