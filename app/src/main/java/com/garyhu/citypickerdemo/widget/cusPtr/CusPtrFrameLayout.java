package com.garyhu.citypickerdemo.widget.cusPtr;

import android.content.Context;
import android.util.AttributeSet;

import com.garyhu.citypickerdemo.widget.cusPtr.CusHeaderView;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2016/12/14.
 */

public class CusPtrFrameLayout extends PtrFrameLayout {

    private CusHeaderView headerView;

    public CusPtrFrameLayout(Context context) {
        super(context);
        initView();
    }

    public CusPtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CusPtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public void initView(){
        headerView = new CusHeaderView(getContext());
        setHeaderView(headerView);
        addPtrUIHandler(headerView);
    }
}
