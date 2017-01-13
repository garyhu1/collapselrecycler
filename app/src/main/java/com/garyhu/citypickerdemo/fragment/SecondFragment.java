package com.garyhu.citypickerdemo.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.base.BaseFragment;
import com.garyhu.citypickerdemo.widget.myrecyclerview.AllTabsAdapter;
import com.garyhu.citypickerdemo.widget.myrecyclerview.ChoseTabsAdapter;
import com.garyhu.citypickerdemo.widget.myrecyclerview.SimpleItemTouchHelperCallback;
import com.garyhu.citypickerdemo.widget.myrecyclerview.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： garyhu.
 * 时间： 2016/12/22.
 */

public class SecondFragment extends BaseFragment implements AllTabsAdapter.onAllTabsListener {

    @BindView(R.id.tbs_ll)
    LinearLayout linearLayout;
    @BindView(R.id.chose_recycle)
    RecyclerView choseRecycle;
    @BindView(R.id.all_recycle)
    RecyclerView allRecycle;

    public static List<String> choseTabs = new ArrayList<>();
    public static List<String> allTabs = new ArrayList<>();


    private ChoseTabsAdapter choseAdapter;
    private AllTabsAdapter allAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initData() {
        initviews();
        initdatas();
    }

    @Override
    protected void setListener() {

    }

    private void initdatas() {
        choseTabs.add("头条");
        choseTabs.add("科技");
        choseTabs.add("热点");
        choseTabs.add("情感");
        choseTabs.add("移动互联");
        choseTabs.add("军事");
        choseTabs.add("历史");
        choseTabs.add("社会");
        choseTabs.add("语录");
        choseTabs.add("娱乐");


        allTabs.add("体育");
        allTabs.add("时尚");
        allTabs.add("房产");
        allTabs.add("论坛");
        allTabs.add("博客");
        allTabs.add("健康");
        allTabs.add("轻松一刻");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("股票");
        allTabs.add("小说");
        allTabs.add("财经");
        allTabs.add("特卖");
        allTabs.add("美食");
        allTabs.add("数码");
        allTabs.add("养生");
        allTabs.add("手机");
        allTabs.add("旅游");
        allTabs.add("教育");
        allTabs.add("三农");
        allTabs.add("美图");
        allTabs.add("星座");
        allTabs.add("辟谣");
        allTabs.add("政务");
        allTabs.add("火山直播");
        allTabs.add("彩票");

    }


    private void initviews() {

        choseTabs.clear();
        allTabs.clear();
        allAdapter = new AllTabsAdapter(getActivity());
        choseAdapter = new ChoseTabsAdapter(getActivity(),allAdapter);
        allAdapter.setListener(this);

        choseRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        allRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        choseRecycle.addItemDecoration(new SpaceItemDecoration(15));
        allRecycle.addItemDecoration(new SpaceItemDecoration(15));

        choseRecycle.setAdapter(choseAdapter);
        allRecycle.setAdapter(allAdapter);


        //关联ItemTouchHelper和RecyclerView
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(choseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(choseRecycle);

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void allTabsItemClick(final View view, final int position) {


        final PathMeasure mPathMeasure;
        final float[] mCurrentPosition = new float[2];
        int parentLoc[] = new int[2];
        linearLayout.getLocationInWindow(parentLoc);//获取容器的绝对值

        Log.d("ddd","parentLoc[0]------------------>"+parentLoc[0]);
        Log.d("ddd","parentLoc[1]------------------>"+parentLoc[1]);
        int startLoc[] = new int[2];
        view.getLocationInWindow(startLoc);

        final View startView = view;
        startView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        // RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        Log.e("tag", startView.getWidth() + "#" + startView.getHeight());
        allRecycle.removeView(view);
        linearLayout.addView(startView);


//        ImageView imageView = new ImageView(this);
//        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//        imageView.setImageResource(R.drawable.location_located);
//        linearLayout.addView(imageView);


        final View endView;
        float toX, toY;
        int endLoc[] = new int[2];
        //进行判断
        int i = choseTabs.size();


        if (i == 0) {
            toX = view.getWidth();
            toY = view.getHeight();
        } else if (i % 4 == 0) {
            endView = choseRecycle.getChildAt(i - 4);
            endView.getLocationInWindow(endLoc);
            toX = endLoc[0] - parentLoc[0];
            toY = endLoc[1] + view.getHeight() - parentLoc[1];
        } else {
            endView = choseRecycle.getChildAt(i - 1);
            endView.getLocationInWindow(endLoc);
            toX = endLoc[0] + view.getWidth() - parentLoc[0];
            toY = endLoc[1] - parentLoc[1];
        }


        float startX = startLoc[0] - parentLoc[0];
        float startY = startLoc[1] - parentLoc[1];

        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(toX, toY);
        mPathMeasure = new PathMeasure(path, false);

        //属性动画实现
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        // 匀速插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                // 获取当前点坐标封装到mCurrentPosition
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                startView.setTranslationX(mCurrentPosition[0]);
                startView.setTranslationY(mCurrentPosition[1]);
                Log.d("garyhu", mCurrentPosition[0] + "@" + mCurrentPosition[1]);

            }
        });
        valueAnimator.start();


        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                //默认recyclerviewe的动画
                allRecycle.setItemAnimator(new DefaultItemAnimator());
                choseRecycle.setItemAnimator(new DefaultItemAnimator());
                choseTabs.add(choseTabs.size(), allTabs.get(position));
                allTabs.remove(position);
                //先更新数据
                allAdapter.notifyDataSetChanged();
                choseAdapter.notifyDataSetChanged();
                //再更新动画
                allAdapter.notifyItemRemoved(position);
                choseAdapter.notifyItemInserted(choseTabs.size());
                linearLayout.removeView(startView);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
}
