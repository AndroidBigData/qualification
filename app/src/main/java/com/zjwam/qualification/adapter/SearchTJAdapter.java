package com.zjwam.qualification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.bean.SearchTJBean;

public class SearchTJAdapter extends ListBaseAdapter<SearchTJBean> {
    private LayoutInflater mLayoutInflater;

    public SearchTJAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.search_tj_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        final SearchTJBean items = mDataList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tj_name.setText(items.getName());
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tj_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tj_name = itemView.findViewById(R.id.tj_name);
        }
    }
}
