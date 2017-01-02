package com.garyhu.citypickerdemo.widget.myrecyclerview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.garyhu.citypickerdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangyangkai on 16/6/12.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {


    private Context context;
    private LayoutInflater inflater;
    private List<Integer> lists = new ArrayList<>();
    private int screenWidth;

    public RecyclerAdapter(Context context, List<Integer> lists) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.lists = lists;
        this.screenWidth = context.getResources().getDisplayMetrics().widthPixels;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(inflater.inflate(R.layout.item_recycler, null, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.txt.setText("第" + String.valueOf(lists.get(position)) + "项");
        viewHolder.layout.scrollTo(0, 0);
        final TextView tv = (TextView) viewHolder.layout.findViewById(R.id.item_delete_txt);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tv.setVisibility(View.GONE);
//                startAnim(viewHolder.layout,position);
                removeRecycle(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (lists != null) {
            return lists.size();
        } else {
            return 0;
        }
    }

    /**
     * 删除数据的操作
     * @param position
     */
    public void removeRecycle(int position) {
        lists.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
        if (lists.size() == 0) {
            Toast.makeText(context, "已经没数据啦", Toast.LENGTH_SHORT).show();
        }
    }

}
