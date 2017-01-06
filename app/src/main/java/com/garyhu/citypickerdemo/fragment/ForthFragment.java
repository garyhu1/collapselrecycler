package com.garyhu.citypickerdemo.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ListView;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.adapter.MyRefreshAdapter;
import com.garyhu.citypickerdemo.base.BaseFragment;
import com.garyhu.citypickerdemo.widget.refreshrecycler.MyRefreshRecyclerView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： garyhu.
 * 时间： 2016/12/22.
 */

public class ForthFragment extends BaseFragment {

    @BindView(R.id.swipe_recycler)
    MyRefreshRecyclerView swipeRecycler;

    private List<String> mStrings;
    private MyRefreshAdapter mAdapter;
    private Context mContext;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_forth;
    }

    @Override
    protected void initData() {
        mContext = getActivity();
        setData();
        swipeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
        swipeRecycler.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        swipeRecycler.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
//        swipeRecycler.setSwipeMenuItemClickListener(menuItemClickListener);
        //设置不允许上拉加载更多
//        swipeRecycler.setPullLoadMoreEnable(false);
//        //设置不允许下拉刷新
//        swipeRecycler.setPullToRefreshEnable(false);
        mAdapter = new MyRefreshAdapter(mStrings);
        swipeRecycler.setAdapter(mAdapter);
        swipeRecycler.setOnPullListener(onPullListener);
    }

    /**
     * 设置数据
     */
    public void setData(){
        mStrings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mStrings.add("我是第" + i + "个。");
        }
    }

    /**
     * 上拉加载、下拉刷新监听
     */
    private MyRefreshRecyclerView.OnPullListener onPullListener = new MyRefreshRecyclerView.OnPullListener() {
        @Override
        public void onRefresh() {
            swipeRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(swipeRecycler!=null){
                        swipeRecycler.onRefreshComplete();
                    }
                }
            }, 3000);
        }

        @Override
        public void onLoadMore() {
            swipeRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(swipeRecycler!=null){
                        swipeRecycler.onLoadMoreComplete();
                    }
                }
            }, 3000);
        }
    };

    @Override
    protected void setListener() {

    }

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.d60);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
                        .setImage(R.mipmap.ic_action_add) // 图标。
                        .setWidth(width) // 宽度。
                        .setHeight(height); // 高度。
                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);

                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.mipmap.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。

                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_green)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
            }
        }
    };
}
