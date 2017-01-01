package com.garyhu.citypickerdemo.widget.myrecyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tangyangkai on 16/5/20.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace;

    /**
     * @param space 传入的值，其单位视为dp
     */
    public SpaceItemDecoration(int space) {
        this.mSpace = space;
    }

    /**
     * 设置item的margin值
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int pos = parent.getChildAdapterPosition(view);

        outRect.left = mSpace;
        outRect.top = 0;
        outRect.bottom = mSpace;
        outRect.right = mSpace;
    }

    /**
     * 自己绘制分割线
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}