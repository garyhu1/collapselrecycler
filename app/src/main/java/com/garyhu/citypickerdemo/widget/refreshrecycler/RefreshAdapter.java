package com.garyhu.citypickerdemo.widget.refreshrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

/**
 * 作者： garyhu.
 * 时间： 2017/1/6.
 */

public abstract class RefreshAdapter<V extends RecyclerView.ViewHolder> extends SwipeMenuAdapter<RecyclerView.ViewHolder> {

    private HeaderView headerView;
    private FooterView footerView;
    public static final int HeaderType = 0x1099, FooterType = 0x1101;
    private HeaderFooterViewHolder headerViewHolder, footerViewHolder;
//    private boolean footerViewEnable = true;

    public class HeaderFooterViewHolder extends RecyclerView.ViewHolder {
        public HeaderFooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        if (viewType == HeaderType) {
            headerView = headerView == null ? new DefaultHeaderView(realContentView.getContext()) : headerView;
            headerViewHolder = new HeaderFooterViewHolder(headerView);
            return headerViewHolder;
        }
        if (viewType == FooterType) {
            footerView = footerView == null ? new DefaultFooterView(realContentView.getContext()) : footerView;
            footerViewHolder = new HeaderFooterViewHolder(footerView);
//            if(!footerViewEnable) { //不允许上拉加载更多，隐藏FooterView
//                FooterView footerView = (FooterView) footerViewHolder.itemView;
//                footerView.setNowState(FooterView.STATE.HIND);
//            }
            return footerViewHolder;
        }
        return onCreateThisViewHolder(realContentView, viewType);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position != 0 && position != getItemCount() - 1)
            onBindThisViewHolder((V) holder, position - 1);
    }

    public abstract void onBindThisViewHolder(V holder, int position);

    /**
     * 创建ViewHolder
     *
     * @param contentView 已经在createContentView()中创建好，然后经过再次包裹了侧滑菜单布局的itemview
     * @param viewType
     * @return
     */
    public abstract RecyclerView.ViewHolder onCreateThisViewHolder(View contentView, int viewType);

    @Override
    public final int getItemCount() {
        //添加Header和Footer的数目
        return getThisItemCount() + 2;
    }

    /**
     * 此方法执行RecyclerView.Adapter中getItemCount()的逻辑
     *
     * @return
     */
    public abstract int getThisItemCount();

    /**
     * 重写此方法时请注意保留父类方法的逻辑，否则导致header计数混乱，下拉刷新出错
     * 使用position时注意减1(减去header的位置)
     *
     * @param position
     * @return
     */
    @Override
    public final int getItemViewType(int position) {
        if (position == 0)
            return HeaderType;
        else if (position == getThisItemCount() + 1)
            return FooterType;
        else
            return getThisItemViewType(position - 1);//减1减去header位置
    }

    /**
     * 此方法替代原Adapter中的getItemViewType方法
     * 使用与
     * @return
     */
    public int getThisItemViewType(int position) {
        return 0;
    }


    /**
     * 获取HeaderView
     *
     * @return
     */
    public HeaderView getHeaderView() {
        return (HeaderView) headerViewHolder.itemView;
    }

    /**
     * 获取FooterView
     *
     * @return
     */
    public FooterView getFooterView() {
//        if (null == footerViewHolder)
//            return null;
        return (FooterView) footerViewHolder.itemView;
    }

    /**
     * 设置HeaderView
     *
     * @return
     */
    public void setHeaderView(HeaderView headerView) {
        this.headerView = headerView;
        notifyDataSetChanged();
    }

    /**
     * 设置FooterView
     *
     * @return
     */
    public void setFooterView(FooterView footerView) {
        this.footerView = footerView;
        notifyDataSetChanged();
    }

//    public void setFooterViewEnable(boolean enable) {
//        this.footerViewEnable = enable;
//    }
}
