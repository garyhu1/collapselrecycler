package com.garyhu.citypickerdemo.widget.drager;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Toast;

import com.garyhu.citypickerdemo.R;

import java.util.ArrayList;
import java.util.List;

public class DragPhotoActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ArrayList<Integer> mList,heights,widths,tops,lefts;
    private DragPhotoView[] mPhotoViews;
    private int num,finishNum;

    int mOriginLeft,fOriginLeft;
    int mOriginTop,fOriginTop;
    int mOriginHeight,fOriginHeight;
    int mOriginWidth,fOriginWidth;
    int mOriginCenterX,fOriginCenterX;
    int mOriginCenterY,fOriginCenterY;
    private float mTargetHeight,fTargetHeight;
    private float mTargetWidth,fTargetWidth;
    private float mScaleX,fScaleX;
    private float mScaleY,fScaleY;
    private float mTranslationX,fTranslationX;
    private float mTranslationY,fTranslationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_drag_photo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        num = getIntent().getIntExtra("num",0);
        finishNum = num;

        mList = getIntent().getIntegerArrayListExtra("imgs");
        heights = getIntent().getIntegerArrayListExtra("heights");
        widths = getIntent().getIntegerArrayListExtra("widths");
        tops = getIntent().getIntegerArrayListExtra("tops");
        lefts = getIntent().getIntegerArrayListExtra("lefts");

        mPhotoViews = new DragPhotoView[mList.size()];

        for (int i = 0; i < mPhotoViews.length; i++) {
            mPhotoViews[i] = (DragPhotoView) View.inflate(this, R.layout.item_viewpager, null);
            mPhotoViews[i].setImageResource(mList.get(i));
            mPhotoViews[i].setOnTapListener(new DragPhotoView.OnTapListener() {
                @Override
                public void onTap(DragPhotoView view) {
                    finishWithAnimation();
                }
            });

            mPhotoViews[i].setOnExitListener(new DragPhotoView.OnExitListener() {
                @Override
                public void onExit(DragPhotoView view, float x, float y, float w, float h) {
                    performExitAnimation(view, x, y, w, h);
                }
            });
        }

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mPhotoViews[position]);
                return mPhotoViews[position];
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mPhotoViews[position]);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });

        mViewPager.setCurrentItem(num);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                finishNum = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mViewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        mOriginLeft = lefts.get(num);
                        mOriginTop = tops.get(num);
//                        mOriginLeft = getIntent().getIntExtra("left", 0);
//                        mOriginTop = getIntent().getIntExtra("top", 0);
//                        mOriginHeight = getIntent().getIntExtra("height", 0);
//                        mOriginWidth = getIntent().getIntExtra("width", 0);
                        mOriginHeight = heights.get(num);
                        mOriginWidth = widths.get(num);
                        mOriginCenterX = mOriginLeft + mOriginWidth / 2;
                        mOriginCenterY = mOriginTop + mOriginHeight / 2;

                        int[] location = new int[2];

                        final DragPhotoView photoView = mPhotoViews[num];
                        photoView.getLocationOnScreen(location);

                        mTargetHeight = (float) photoView.getHeight();
                        mTargetWidth = (float) photoView.getWidth();
                        mScaleX = (float) mOriginWidth / mTargetWidth;
                        mScaleY = (float) mOriginHeight / mTargetHeight;

                        float targetCenterX = location[0] + mTargetWidth / 2;
                        float targetCenterY = location[1] + mTargetHeight / 2;

                        mTranslationX = mOriginCenterX - targetCenterX;
                        mTranslationY = mOriginCenterY - targetCenterY;
                        photoView.setTranslationX(mTranslationX);
                        photoView.setTranslationY(mTranslationY);

                        photoView.setScaleX(mScaleX);
                        photoView.setScaleY(mScaleY);

                        performEnterAnimation();

                        for (int i = 0; i < mPhotoViews.length; i++) {
                            mPhotoViews[i].setMinScale(mScaleX);
                        }
                    }
                });
    }

    /**
     * ===================================================================================
     * <p>
     * 底下是低版本"共享元素"实现   不需要过分关心  如有需要 可作为参考.
     * <p>
     * Code  under is shared transitions in all android versions implementation
     */
    private void performExitAnimation(final DragPhotoView view, float x, float y, float w, float h) {
        view.finishAnimationCallBack();
        fOriginLeft = lefts.get(finishNum);
        fOriginTop = tops.get(finishNum);
//                        mOriginLeft = getIntent().getIntExtra("left", 0);
//                        mOriginTop = getIntent().getIntExtra("top", 0);
//                        mOriginHeight = getIntent().getIntExtra("height", 0);
//                        mOriginWidth = getIntent().getIntExtra("width", 0);
        fOriginHeight = heights.get(finishNum);
        fOriginWidth = widths.get(finishNum);
        fOriginCenterX = fOriginLeft + fOriginWidth / 2;
        fOriginCenterY = fOriginTop + fOriginHeight / 2;

        int[] location = new int[2];

        view.getLocationOnScreen(location);

        fTargetHeight = (float) view.getHeight();
        fTargetWidth = (float) view.getWidth();
        fScaleX = (float) fOriginWidth / fTargetWidth;
        fScaleY = (float) fOriginHeight / fTargetHeight;

        float targetCenterX = location[0] + fTargetWidth / 2;
        float targetCenterY = location[1] + fTargetHeight / 2;

        fTranslationX = fOriginCenterX - targetCenterX;
        fTranslationY = fOriginCenterY - targetCenterY;
        float viewX = fTargetWidth / 2 + x - fTargetWidth * fScaleX / 2;
        float viewY = fTargetHeight / 2 + y - fTargetHeight * fScaleY / 2;
        view.setX(viewX);
        view.setY(viewY);

        float centerX = view.getX() + fOriginWidth / 2;
        float centerY = view.getY() + fOriginHeight / 2;

        float translateX = fOriginCenterX - centerX;
        float translateY = fOriginCenterY - centerY;


        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(view.getX(), view.getX() + translateX);
        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setX((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateXAnimator.setDuration(300);
        translateXAnimator.start();
        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(view.getY(), view.getY() + translateY);
        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateYAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animator.removeAllListeners();
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        translateYAnimator.setDuration(300);
        translateYAnimator.start();
    }

    private void finishWithAnimation() {

        final DragPhotoView photoView = mPhotoViews[finishNum];
        fOriginLeft = lefts.get(finishNum);
        fOriginTop = tops.get(finishNum);
//                        mOriginLeft = getIntent().getIntExtra("left", 0);
//                        mOriginTop = getIntent().getIntExtra("top", 0);
//                        mOriginHeight = getIntent().getIntExtra("height", 0);
//                        mOriginWidth = getIntent().getIntExtra("width", 0);
        fOriginHeight = heights.get(finishNum);
        fOriginWidth = widths.get(finishNum);
        fOriginCenterX = fOriginLeft + fOriginWidth / 2;
        fOriginCenterY = fOriginTop + fOriginHeight / 2;

        int[] location = new int[2];

        photoView.getLocationOnScreen(location);

        fTargetHeight = (float) photoView.getHeight();
        fTargetWidth = (float) photoView.getWidth();
        fScaleX = (float) fOriginWidth / fTargetWidth;
        fScaleY = (float) fOriginHeight / fTargetHeight;

        float targetCenterX = location[0] + fTargetWidth / 2;
        float targetCenterY = location[1] + fTargetHeight / 2;

        fTranslationX = fOriginCenterX - targetCenterX;
        fTranslationY = fOriginCenterY - targetCenterY;
        Log.d("garyhu","fTranslationX = "+fTranslationX);
        Log.d("garyhu","fTranslationY = "+fTranslationY);
        Log.d("garyhu","fScaleX = "+fScaleX);
        Log.d("garyhu","fScaleY = "+fScaleY);
        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(0, fTranslationX);
        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setX((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateXAnimator.setDuration(300);
        translateXAnimator.start();

        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(0, fTranslationY);
        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateYAnimator.setDuration(300);
        translateYAnimator.start();

        ValueAnimator scaleYAnimator = ValueAnimator.ofFloat(1, fScaleY);
        scaleYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleY((Float) valueAnimator.getAnimatedValue());
            }
        });
        scaleYAnimator.setDuration(300);
        scaleYAnimator.start();

        ValueAnimator scaleXAnimator = ValueAnimator.ofFloat(1, fScaleX);
        scaleXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });

        scaleXAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animator.removeAllListeners();
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        scaleXAnimator.setDuration(300);
        scaleXAnimator.start();
    }

    private void performEnterAnimation() {
        final DragPhotoView photoView = mPhotoViews[num];
        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(photoView.getX(), 0);
        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setX((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateXAnimator.setDuration(300);
        translateXAnimator.start();

        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(photoView.getY(), 0);
        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateYAnimator.setDuration(300);
        translateYAnimator.start();

        ValueAnimator scaleYAnimator = ValueAnimator.ofFloat(mScaleY, 1);
        scaleYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleY((Float) valueAnimator.getAnimatedValue());
            }
        });
        scaleYAnimator.setDuration(300);
        scaleYAnimator.start();

        ValueAnimator scaleXAnimator = ValueAnimator.ofFloat(mScaleX, 1);
        scaleXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });
        scaleXAnimator.setDuration(300);
        scaleXAnimator.start();
    }

    @Override
    public void onBackPressed() {
        finishWithAnimation();
    }
}
