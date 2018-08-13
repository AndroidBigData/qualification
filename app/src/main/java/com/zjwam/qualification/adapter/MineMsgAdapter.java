package com.zjwam.qualification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.bean.MineMsgBean;

public class MineMsgAdapter extends ListBaseAdapter<MineMsgBean.Notice> {
    private LayoutInflater mLayoutInflater;

    public MineMsgAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.mine_msg_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        final MineMsgBean.Notice items = mDataList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        if (items.getIs_read() == 1){
            viewHolder.is_read.setVisibility(View.INVISIBLE);
        }else {
            viewHolder.is_read.setVisibility(View.VISIBLE);
        }
        viewHolder.msg_type.setText("【"+items.getType()+"】");
        viewHolder.msg_tetle.setText(items.getTitle());
        viewHolder.msg_content.setText(items.getContent());
        viewHolder.msg_time.setText(items.getAddtime());
        viewHolder.msg_from.setText("来自:"+items.getName());
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView is_read,msg_type,msg_tetle,msg_content,msg_time,msg_from;

        public ViewHolder(View itemView) {
            super(itemView);
            is_read = itemView.findViewById(R.id.is_read);
            msg_type = itemView.findViewById(R.id.msg_type);
            msg_tetle = itemView.findViewById(R.id.msg_tetle);
            msg_content = itemView.findViewById(R.id.msg_content);
            msg_time = itemView.findViewById(R.id.msg_time);
            msg_from = itemView.findViewById(R.id.msg_from);
        }
    }
}
