package com.zjwam.qualification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.bean.VideoCatalogBean;


public class VideoCatalogAdapter extends ListBaseAdapter<VideoCatalogBean.Catalog> {
    private int thisPosition;
    private String buy;
    public int getthisPosition() {
        return thisPosition;
    }
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

    public VideoCatalogAdapter(Context context, String buy) {
        mContext = context;
        this.buy = buy;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.video_catalog_item,parent,false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setTag(position);
        VideoCatalogBean.Catalog item = mDataList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.title.setText((position+1)+"."+item.getVname());
        if (position == getthisPosition()) {
            viewHolder.title.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        } else {
            viewHolder.title.setTextColor(mContext.getResources().getColor(R.color.text_color_gray));
        }

        if ("1".equals(buy)){
            viewHolder.free.setVisibility(View.GONE);
        }else {
            if (position<2){
                viewHolder.free.setVisibility(View.VISIBLE);
            }else {
                viewHolder.free.setVisibility(View.GONE);
            }
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title,free;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.video_catalog_item_title);
            free = itemView.findViewById(R.id.free_learn);
        }
    }

}
