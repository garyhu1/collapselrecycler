package com.garyhu.citypickerdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.widget.refreshrecycler.RefreshAdapter;

import java.util.List;

/**
 * 作者： garyhu.
 * 时间： 2017/1/6.
 */

public class MyRefreshAdapter extends RefreshAdapter<MyRefreshAdapter.DefaultViewHolder>{

    private List<String> titles;

    public MyRefreshAdapter(List<String> titles){
        this.titles = titles;
    }

    @Override
    public void onBindThisViewHolder(MyRefreshAdapter.DefaultViewHolder holder, int position) {
        holder.tvTitle.setText(titles.get(position));
    }

    @Override
    public RecyclerView.ViewHolder onCreateThisViewHolder(View contentView, int viewType) {
        return new DefaultViewHolder(contentView);
    }

    @Override
    public int getThisItemCount() {
        return titles!=null?titles.size():0;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder  {
        TextView tvTitle;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
