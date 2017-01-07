package com.garyhu.citypickerdemo.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import uk.co.senab.photoview.PhotoView;


public class BossZoomHelper {

    private Animator animator;
    private PhotoView copyImageView;
    private RelativeLayout r;//当图片比较小的时候显示背景，默认黑色
    private long duration;
    private Activity activity;
    private View originalView;
    private ContentFrameLayout container;

    private Rect startRect,endRect;
    private float startScale;

    public BossZoomHelper(final Activity activity, final View originalView) {
        this(activity, originalView, 400);
    }
    public BossZoomHelper(Activity activity,View originalView, long duration) {
        this.duration = duration;
        this.activity = activity;
        this.originalView = originalView;
        init();
    }

    public void init(){
        if (animator != null) {
            animator.cancel();
        }
        container = (ContentFrameLayout) activity.findViewById(android.R.id.content);
        copyImageView = new PhotoView(activity);
        int matchParent = FrameLayout.LayoutParams.MATCH_PARENT;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(matchParent, matchParent);
        copyImageView.setLayoutParams(params);
        r = new RelativeLayout(activity);
        r.setLayoutParams(params);
        r.setBackgroundColor(Color.BLACK);
        if (originalView instanceof ImageView) {
            copyImageView.setImageDrawable(((ImageView) originalView).getDrawable());
        } else {
            Bitmap bm = view2Bitmap(originalView);
            if (bm != null) {
                copyImageView.setImageBitmap(bm);
            }
        }
        copyImageView.setVisibility(View.GONE);
        r.setVisibility(View.GONE);
        container.addView(r);
        container.addView(copyImageView);
        startRect = new Rect();
        endRect = new Rect();
        Point offsetPoint = new Point();
        originalView.getGlobalVisibleRect(startRect);
        /**
         * r 是 绝对（global）坐标参数，包含状态栏、ActionBar、底部虚拟键
         * globalOffset 用于将global坐标转换成local坐标，这里的local坐标，是相对于内容区的坐标，也就是除了状态栏和action bar和虚拟按键的区域。
         * */
        container.getGlobalVisibleRect(endRect, offsetPoint);
        startRect.offset(-offsetPoint.x, -offsetPoint.y);
        endRect.offset(-offsetPoint.x, -offsetPoint.y);
        float scaleSize;
        float startScaleFinal;
        Log.d("garyhu","width == "+endRect.width());
        Log.d("garyhu","height == "+endRect.height());
        if ((float) endRect.width() / (float) endRect.height() > (float) startRect.width() / (float) startRect.height()) {
            startScale = (float) startRect.height() / (float) endRect.height();
            scaleSize = startScale * (float) endRect.width();
            startScaleFinal = (scaleSize - (float) startRect.width()) / 2.0F;
            startRect.left = (int) ((float) startRect.left - startScaleFinal);
            startRect.right = (int) ((float) startRect.right + startScaleFinal);
        } else {
            startScale = (float) startRect.width() / (float) endRect.width();
            scaleSize = startScale * (float) endRect.height();
            startScaleFinal = (scaleSize - (float) startRect.height()) / 2.0F;
            startRect.top = (int) ((float) startRect.top - startScaleFinal);
            startRect.bottom = (int) ((float) startRect.bottom + startScaleFinal);
        }
    }

    public void showAnim(){
        copyImageView.setPivotX(0.0F);
        copyImageView.setPivotY(0.0F);
        r.setPivotX(0.0F);
        r.setPivotY(0.0F);
        AnimatorSet showAnimatorSet = new AnimatorSet();
        showAnimatorSet.setDuration(duration);
        showAnimatorSet.play(ObjectAnimator.ofFloat(copyImageView, View.X, new float[]{(float) startRect.left, (float) endRect.left}))
                .with(ObjectAnimator.ofFloat(copyImageView, View.Y, new float[]{(float) startRect.top, (float) endRect.top}))
                .with(ObjectAnimator.ofFloat(r, View.X, new float[]{(float) startRect.left, (float) endRect.left}))
                .with(ObjectAnimator.ofFloat(r, View.Y, new float[]{(float) startRect.top, (float) endRect.top}))
                .with(ObjectAnimator.ofFloat(copyImageView, View.SCALE_X, new float[]{startScale, 1.0F}))
                .with(ObjectAnimator.ofFloat(r, View.SCALE_X, new float[]{startScale, 1.0F}))
                .with(ObjectAnimator.ofFloat(r, View.SCALE_Y, new float[]{startScale, 1.0F}))
                .with(ObjectAnimator.ofFloat(copyImageView, View.SCALE_Y, new float[]{startScale, 1.0F}));
        showAnimatorSet.setInterpolator(new DecelerateInterpolator());
        showAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                r.setVisibility(View.VISIBLE);
                copyImageView.setVisibility(View.VISIBLE);
            }
            public void onAnimationEnd(Animator animation) {
                animator = null;
            }
            public void onAnimationCancel(Animator animation) {
                destroy(originalView, container);
            }
        });
        showAnimatorSet.start();
        animator = showAnimatorSet;
    }

    public void hintAnim(){
        if (animator != null) {
            animator.cancel();
        }
        copyImageView.setBackgroundColor(0);
        AnimatorSet hiddenAnimatorSet = new AnimatorSet();
        hiddenAnimatorSet.play(ObjectAnimator.ofFloat(copyImageView, View.X, new float[]{(float) startRect.left}))
                .with(ObjectAnimator.ofFloat(copyImageView, View.Y, new float[]{(float) startRect.top}))
                .with(ObjectAnimator.ofFloat(r, View.X, new float[]{(float) startRect.left}))
                .with(ObjectAnimator.ofFloat(r, View.Y, new float[]{(float) startRect.top}))
                .with(ObjectAnimator.ofFloat(r, View.SCALE_X, new float[]{startScale}))
                .with(ObjectAnimator.ofFloat(r, View.SCALE_Y, new float[]{startScale}))
                .with(ObjectAnimator.ofFloat(copyImageView, View.SCALE_X, new float[]{startScale}))
                .with(ObjectAnimator.ofFloat(copyImageView, View.SCALE_Y, new float[]{startScale}));
        hiddenAnimatorSet.setInterpolator(new DecelerateInterpolator());
        hiddenAnimatorSet.setDuration(duration);
        hiddenAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
            public void onAnimationEnd(Animator animation) {
                if (animator != null) {
                    animator.cancel();
                }
                destroy(originalView, container);
            }
            public void onAnimationCancel(Animator animation) {
                destroy(originalView, container);
            }
        });
        hiddenAnimatorSet.start();
        animator = hiddenAnimatorSet;
    }

    private void destroy(View originalView, ContentFrameLayout container) {
        originalView.setAlpha(1.0F);
        copyImageView.setVisibility(View.GONE);
        r.setVisibility(View.GONE);
        container.removeView(copyImageView);
        container.removeView(r);
        animator = null;
    }
    private Bitmap view2Bitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        if (bmp == null) {
            return null;
        }
        Bitmap bp;
        bp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
        view.destroyDrawingCache();
        return bp;
    }
}
