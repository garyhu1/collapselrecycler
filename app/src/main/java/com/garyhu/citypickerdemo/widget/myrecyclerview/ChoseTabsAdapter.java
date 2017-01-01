package com.garyhu.citypickerdemo.widget.myrecyclerview;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.fragment.SecondFragment;

import java.util.Collections;

/**
 * Created by tangyangkai on 16/5/20.
 */
public class ChoseTabsAdapter extends RecyclerView.Adapter implements onMoveAndSwipedListener {


    private Context context;
    private LayoutInflater layoutInflater;
    private AllTabsAdapter adapter;


    public ChoseTabsAdapter(Context context,AllTabsAdapter adapter) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.adapter = adapter;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChoseTabsViewHolder(layoutInflater.inflate(R.layout.item_tabs, null, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ChoseTabsViewHolder viewHolder = (ChoseTabsViewHolder) holder;
        viewHolder.txt.setText(SecondFragment.choseTabs.get(position));

    }

    @Override
    public int getItemCount() {
        return SecondFragment.choseTabs.size();
    }


    public class ChoseTabsViewHolder extends RecyclerView.ViewHolder {


        private TextView txt;


        public ChoseTabsViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.tabs_txt);

        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换mItems数据的位置
        Collections.swap(SecondFragment.choseTabs, fromPosition, toPosition);
        //交换RecyclerView列表中item的位置
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        //添加数据
        SecondFragment.allTabs.add(0,SecondFragment.choseTabs.get(position));
        adapter.notifyDataSetChanged();
        adapter.notifyItemInserted(0);
        //删除mItems数据
        SecondFragment.choseTabs.remove(position);
        //先更新数据
//        adapter.notifyDataSetChanged();
//        notifyDataSetChanged();

        //再更新动画
        notifyItemRemoved(position);
    }

}
