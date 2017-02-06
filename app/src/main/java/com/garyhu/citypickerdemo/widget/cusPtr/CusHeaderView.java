package com.garyhu.citypickerdemo.widget.cusPtr;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.garyhu.citypickerdemo.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by Administrator on 2016/12/14.
 */

public class CusHeaderView extends FrameLayout implements PtrUIHandler{

    private TextView refreshTitle;
    private ImageView refreshIcon;

    private ObjectAnimator animator;

    /**
     * 状态识别
     */
    private int mState;

    /**
     * 不同的刷新状态
     * 重置
     * 准备
     * 开始
     * 结束
     */
    public static final int STATE_RESET = 0;
    public static final int STATE_PREPARE = 1;
    public static final int STATE_START = 2;
    public static final int STATE_OVER = 3;

    public CusHeaderView(Context context) {
        this(context,null);
    }

    public CusHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CusHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.head_view,null);
        refreshTitle = (TextView) view.findViewById(R.id.refresh_title);
        refreshIcon = (ImageView) view.findViewById(R.id.refresh_icon);
        addView(view);
    }

    /**
     * 重置
     * @param frame
     */
    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mState = STATE_RESET;
    }

    /**
     * 准备
     * @param frame
     */
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mState = STATE_PREPARE;
    }

    /**
     * 开始刷新
     * @param frame
     */
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mState = STATE_START;
    }

    /**
     * 完成
     * @param frame
     */
    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        mState = STATE_OVER;
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        animator = ObjectAnimator.ofFloat(refreshIcon,"rotation",0,360)
                .setDuration(1000);
        animator.setRepeatCount(4);
        animator.setRepeatMode(ValueAnimator.RESTART);
        switch (mState){
            case STATE_RESET:
                break;
            case STATE_PREPARE:
                if(ptrIndicator.getCurrentPercent()<1.2){
                    refreshTitle.setText("下拉刷新");
                }else {
                    refreshTitle.setText("松开刷新");
                }
                break;
            case STATE_START:
                animator.start();
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
                refreshTitle.setText("更新中...");
                break;
            case STATE_OVER:
//                refreshIcon.stop
                refreshTitle.setText("更新完成");
                break;
        }
    }

    public void setAnimation(){
        animator = ObjectAnimator.ofFloat(refreshIcon,"rotation",0,360);
        animator.setDuration(1000);
    }
}
