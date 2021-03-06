package com.garyhu.citypickerdemo.widget.refreshrecycler;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.InvalidClassException;

/**
 * 作者： garyhu.
 * 时间： 2017/1/6.
 */

public class MyRefreshRecyclerView extends SwipeMenuRecyclerView {

    private boolean isVerticalScroll = true;
    private Context context;
    private RefreshAdapter adapter;
    //最新的touch Y坐标点
    private float touchCurrY;
    //是否正在刷新、是否正在加载更多
    private boolean refreshing = false, loading = false;
    //触发刷新的临界值
    private final float refreshHeight = 150f;
    //HeaderView最新的高度
    private int currHeaderHeight;
    //FooterView最新的高度
    private int currFooterHeight;
    //是否允许上拉加载更多
    private boolean pullLoadMoreEnable = true;
    //是否允许下拉刷新
    private boolean pullToRefreshEnable = true;
    private OnPullListener onPullListener;

    public MyRefreshRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public MyRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyRefreshRecyclerView(Context context) {
        super(context);
        init(context);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof RefreshAdapter) {
            this.adapter = (RefreshAdapter<ViewHolder>) adapter;
        } else {
            try {
                throw new InvalidClassException("所使用Adapter并非SwipeMenuAdapter的子类");
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        }
    }


    private void init(Context context) {
        this.context = context;


    }

    /**
     * 监听滚动
     *
     * @param dx
     * @param dy
     */
    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (!canScrollVertically(1)) {//已经滑到底部

            if (loading || !pullLoadMoreEnable)
                super.onScrolled(dx, dy);
            else {
                startLoadMore();//自动加载
            }
            Toast.makeText(context, "已经滑到底部", Toast.LENGTH_SHORT).show();
        }
    }

    boolean thisTouchHadDeal = false;
    private int startX, nowTouchX, startY, nowTouchY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isVerticalScroll = true;
                thisTouchHadDeal = false;
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!thisTouchHadDeal) {
                    thisTouchHadDeal = true;
                    nowTouchX = (int) ev.getX();
                    nowTouchY = (int) ev.getY();
                    isVerticalScroll = !isHorizontalScroll(startX, startY, nowTouchX, nowTouchY);
                }
                if (isVerticalScroll) {   //如果是竖直方向滑动，拦截事件自己处理
                    return true;
                } else {    //横向滑动，不做响应事件，让子View处理事件
                    return false;
                }
            case MotionEvent.ACTION_UP:
                isVerticalScroll = true;
                thisTouchHadDeal = false;
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:

                //理论上应该computeVerticalScrollOffset == 0 但是因为隐藏header的缘故，这里的判断距离增加一个item的高度
                if (getChildAt(1) != null && computeVerticalScrollOffset() <= getChildAt(1).getHeight()) {//recyclerviwe已滑动到顶部
                    if (refreshing || !pullToRefreshEnable)
                        return super.onTouchEvent(e);
                    touchCurrY = e.getY();

                    float distanceY = touchCurrY - startY;
                    if (distanceY > 0) {
                        dealPulling(distanceY);
                        return true;
                    }
                }

//                if (!canScrollVertically(1)) {//已经滑到底部
//
//                    if (loading || !pullLoadMoreEnable)
//                        return super.onTouchEvent(e);
//
//                    float distanceY = touchCurrY - startY;
//                    if (distanceY < 0) {
//                        dealDowning(distanceY);
//                        return true;
//                    }
//                }
                break;
            case MotionEvent.ACTION_UP:
                if (!refreshing) {
                    currHeaderHeight = adapter.getHeaderView().getHeight();
                    dealPullUp();
                }
//                if(!loading){
//                    currFooterHeight = adapter.getFooterView().getHeight();
//                    dealPullDown();
//                }
                break;
        }
        return super.onTouchEvent(e);
    }

    /**
     * 开始加载更多
     */
    private void startLoadMore() {
        loading = true;
        if (null != onPullListener)
            onPullListener.onLoadMore();

        adapter.getFooterView().setNowState(FooterView.STATE.LOADING);
    }

    /**
     * 上拉
     */
    public void dealPullDown(){
        FooterView footView = adapter.getFooterView();
        int height = footView.getHeight();
        if (height < refreshHeight) {
            startScrollFooterBackAnim();
        } else {
            scrollToReadyAndLoad();
        }
    }

    /**
     * 下拉
     */
    private void dealPullUp() {
        HeaderView headerView = adapter.getHeaderView();
        int height = headerView.getHeight();
        if (height < refreshHeight) {
            startScrollBackAnim();
        } else {
            scrollToReadyAndRefresh();
        }
    }


    /**
     * 回滚到刷新状态的位置并开始刷新
     */
    private void scrollToReadyAndRefresh() {
        if (null != onPullListener)
            onPullListener.onRefresh();
        refreshing = true;
        ValueAnimator animator = ObjectAnimator.ofFloat(1, refreshHeight / currHeaderHeight);
        animator.setDuration(200);
        animator.addUpdateListener(scrollToReadyUpdateListener);
        animator.addListener(scrollToReadyAnimatorListener);
        animator.start();
    }

    private ValueAnimator.AnimatorUpdateListener scrollToReadyUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float value = (float) valueAnimator.getAnimatedValue();
            adapter.getHeaderView().setViewHeight(currHeaderHeight * value);
        }
    };

    private Animator.AnimatorListener scrollToReadyAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {
            adapter.getHeaderView().setNowState(HeaderView.STATE.REFRESHING);
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    };

    /**
     * 回滚到加载状态的位置并开始加载
     */
    private void scrollToReadyAndLoad() {
        if (null != onPullListener)
            onPullListener.onRefresh();
        loading = true;
        ValueAnimator animator = ObjectAnimator.ofFloat(0, -refreshHeight / currFooterHeight);
        animator.setDuration(200);
        animator.addUpdateListener(scrollToReadyFootListener);
        animator.addListener(scrollToReadyFootAnimatorListener);
        animator.start();
    }

    private ValueAnimator.AnimatorUpdateListener scrollToReadyFootListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float value = (float) valueAnimator.getAnimatedValue();
            adapter.getFooterView().setViewHeight(currFooterHeight * value);
        }
    };

    private Animator.AnimatorListener scrollToReadyFootAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {
            adapter.getFooterView().setNowState(FooterView.STATE.LOADING);
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    };


    /**
     * 开启HeaderView回滚回原位的动画
     */
    private void startScrollBackAnim() {
        ValueAnimator animator = ObjectAnimator.ofFloat(1, 0);
        animator.setDuration(150);
        animator.addUpdateListener(scrollBackAnimatUpdateListener);
        animator.start();
    }

    private ValueAnimator.AnimatorUpdateListener scrollBackAnimatUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float value = (float) valueAnimator.getAnimatedValue();
            adapter.getHeaderView().setViewHeight(currHeaderHeight * value);
            if (value == 0) {
                refreshing = false;
            }
        }
    };

    /**
     * 开启FooterView回滚回原位的动画
     */
    private void startScrollFooterBackAnim() {
        ValueAnimator animator = ObjectAnimator.ofFloat(0, 1);
        animator.setDuration(150);
        animator.addUpdateListener(scrollBackAnimatLoadListener);
        animator.start();
    }

    private ValueAnimator.AnimatorUpdateListener scrollBackAnimatLoadListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float value = (float) valueAnimator.getAnimatedValue();
            adapter.getFooterView().setViewHeight(currFooterHeight * value);
            if (value == 0) {
                loading = false;
            }
        }
    };

    /**
     * 处理上拉过程
     *
     * @param distanceY 手指上拉的距离
     */
    public void dealDowning(float distanceY){
        float moveDistance = -distanceY * 0.6f;
        adapter.getFooterView().setViewHeight(moveDistance);
        if (moveDistance < refreshHeight) {
            adapter.getFooterView().setNowState(FooterView.STATE.LOADING);
        } else {
            adapter.getFooterView().setNowState(FooterView.STATE.READY);
        }
    }

    /**
     * 处理下拉过程
     *
     * @param distanceY 手指下拉的距离
     */
    private void dealPulling(float distanceY) {

        float moveDistance = distanceY * 0.6f;
        adapter.getHeaderView().setViewHeight(moveDistance);
        if (moveDistance < refreshHeight) {
            adapter.getHeaderView().setNowState(HeaderView.STATE.PULLING);
        } else {
            adapter.getHeaderView().setNowState(HeaderView.STATE.READY);
        }
    }

    /**
     * 结束下拉刷新
     */
    public void onRefreshComplete() {
        currHeaderHeight = (int) refreshHeight;
        startScrollBackAnim();
    }

    /**
     * 结束上拉加载
     */
    public void onLoadMoreComplete() {
        currFooterHeight = (int) refreshHeight;
        startScrollFooterBackAnim();
//        loading = false;
//        adapter.getFooterView().setNowState(FooterView.STATE.HIND);
    }


    /**
     * 设置下拉刷新和上拉加载的事件监听
     *
     * @param onPullListener
     */
    public void setOnPullListener(OnPullListener onPullListener) {
        this.onPullListener = onPullListener;
    }

    /**
     * 设置是否允许上拉加载更多
     *
     * @param enable true 允许上拉加载更多;false 不允许上拉加载更多
     */
    public void setPullLoadMoreEnable(boolean enable) {
        this.pullLoadMoreEnable = enable;

    }

    /**
     * 设置是否允许下拉刷新
     *
     * @param enable true 允许下拉刷新;false 不允许下拉刷新
     */
    public void setPullToRefreshEnable(boolean enable) {
        this.pullToRefreshEnable = enable;
    }

    public interface OnPullListener {
        /**
         * 下拉刷新触发执行方法
         */
        void onRefresh();

        /**
         * 加载更多触发执行方法
         */
        void onLoadMore();
    }

    /**
     * 判断是横向滑动还是纵向滑动
     *
     * @param startX
     * @param startY
     * @param secondX
     * @param secondY
     * @return
     */

    private boolean isHorizontalScroll(int startX, int startY, int secondX, int secondY) {
        boolean ret = false;
        int distanceX = Math.abs(startX - secondX);
        int distanceY = Math.abs(startY - secondY);

        if (distanceX * Math.tan(2 * Math.PI / 360 * 30) > distanceY)//水平30度角度内算作水平滑动
            ret = true;
        return ret;
    }
}
